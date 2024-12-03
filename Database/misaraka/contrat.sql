
drop table PostEmploye ;

alter table contratemploye rename column idcontrat to id_type_contrat;
alter table contratemploye rename column datedebut to date_debut;
alter table contratemploye rename column datefin to date_fin;
alter table contratemploye rename column idemploye to id_employe;

alter table contratemploye add column id_poste integer references postes(id);
alter table contratemploye add column salaire numeric(20,2);
alter table contratemploye add column taux_horaire numeric(20,2);
alter table contratemploye add column id serial primary key;

alter table employes drop column poste_id;
alter table employes add column id_contrat_actuel integer references contratemploye(id);


CREATE TABLE CategoriePersonnel(
	id SERIAL,
	nom VARCHAR(30) NOT NULL,
	description VARCHAR(50),
	PRIMARY KEY(id)
);

ALTER TABLE Postes ADD COLUMN id_categorie_personnel INTEGER NOT NULL REFERENCES CategoriePersonnel(id);

CREATE OR REPLACE FUNCTION update_taux_horaire()
RETURNS TRIGGER AS $$
BEGIN
    -- Ensure salaire is not null to avoid division errors
    IF NEW.salaire IS NOT NULL THEN
        NEW.taux_horaire := ROUND(NEW.salaire / 173.33, 2);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER calculate_taux_horaire
BEFORE INSERT OR UPDATE
ON contratemploye
FOR EACH ROW
EXECUTE FUNCTION update_taux_horaire();

CREATE OR REPLACE FUNCTION update_id_contrat_actuel()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the id_contrat_actuel for the corresponding employee
    UPDATE employes
    SET id_contrat_actuel = NEW.id
    WHERE id = NEW.id_employe;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_id_contrat_actuel
AFTER INSERT
ON ContratEmploye
FOR EACH ROW
EXECUTE FUNCTION update_id_contrat_actuel();


CREATE TABLE TypeRupture(
	id SERIAL,
	nom VARCHAR(20) NOT NULL,
	description VARCHAR(50),
	preavis_requis BOOLEAN,
	indemnite BOOLEAN NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE RuptureContrat(
	id SERIAL,
	id_type_rupture INTEGER NOT NULL,
	id_employe INTEGER NOT NULL,
	date_notification DATE NOT NULL,
	date_fin_contrat DATE NOT NULL,
	preavis_employe BOOLEAN NOT NULL,
	preavis_entreprise BOOLEAN NOT NULL,
	motif VARCHAR(50),
	indemnite_verse NUMERIC(15, 2) default 0,
	PRIMARY KEY(id),
	FOREIGN KEY(id_type_rupture) REFERENCES TypeRupture(id),
	FOREIGN KEY(id_employe) REFERENCES employes(id)
);

-- Trigger Function
CREATE OR REPLACE FUNCTION update_contrat_date_fin()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the date_fin in ContratEmploye for the current contract of the employee
    UPDATE ContratEmploye
    SET date_fin = NEW.date_fin_contrat
    WHERE id = (SELECT id_contrat_actuel FROM employes WHERE id = NEW.id_employe);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger
CREATE TRIGGER trg_update_contrat_date_fin
AFTER INSERT OR UPDATE ON RuptureContrat
FOR EACH ROW
EXECUTE FUNCTION update_contrat_date_fin();

-- Trigger Function
CREATE OR REPLACE FUNCTION update_employe_id_contrat_actuel()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the id_contrat_actuel in Employes for the employee of the current contract
    UPDATE Employes
    SET id_contrat_actuel = NEW.id
    WHERE id = NEW.id_employe;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger
CREATE TRIGGER trg_update_employe_id_contrat_actuel
AFTER INSERT ON ContratEmploye
FOR EACH ROW
EXECUTE FUNCTION update_employe_id_contrat_actuel();

CREATE OR REPLACE FUNCTION set_date_fin_contrat()
RETURNS TRIGGER AS $$
DECLARE
    contract_duration INT;  -- Variable temporaire pour stocker dureeMois
BEGIN
    -- Récupérer la durée en mois à partir du type de contrat
    SELECT dureeMois
    INTO contract_duration
    FROM TypeContrat
    WHERE id = NEW.id_type_contrat;

    -- Si aucune durée n'est trouvée, lever une exception ou définir une valeur par défaut
    IF NOT FOUND THEN
        RAISE EXCEPTION 'TypeContrat introuvable pour id_type_contrat=%', NEW.id_type_contrat;
    END IF;

    -- Si la durée est null, la date de fin est null
    IF contract_duration IS NULL THEN
        NEW.date_fin := NULL;
    ELSE
        -- Calculer la date de fin en ajoutant la durée en mois à la date de début
        NEW.date_fin := NEW.date_debut + INTERVAL '1' MONTH * contract_duration;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger
CREATE TRIGGER trg_set_date_fin_contrat
BEFORE INSERT ON ContratEmploye
FOR EACH ROW
EXECUTE FUNCTION set_date_fin_contrat();


CREATE OR REPLACE VIEW v_employes_actuels AS
SELECT e.*, c.salaire
FROM
    Employes e
JOIN
    ContratEmploye c
ON
    e.id_contrat_actuel = c.id
WHERE
    c.date_fin IS NULL OR c.date_fin >= NOW();


SELECT dureeMois FROM TypeContrat WHERE id = 1;

CREATE OR REPLACE VIEW v_rupture_contrat_actuel AS
SELECT 
    rc.*,
		CASE 
        WHEN rc.preavis_employe = rc.preavis_entreprise THEN rc.date_fin_contrat
        ELSE rc.date_notification
    END AS date_effective
FROM 
    RuptureContrat rc
WHERE rc.indemnite_verse <> 0 and
    DATE_PART('month', 
        CASE 
            WHEN rc.preavis_employe = rc.preavis_entreprise THEN rc.date_fin_contrat
            ELSE rc.date_notification
        END
    ) = DATE_PART('month', CURRENT_DATE)
    AND 
    DATE_PART('year', 
        CASE 
            WHEN rc.preavis_employe = rc.preavis_entreprise THEN rc.date_fin_contrat
            ELSE rc.date_notification
        END
    ) = DATE_PART('year', CURRENT_DATE);


CREATE OR REPLACE FUNCTION handle_rupture_contrat()
RETURNS TRIGGER AS $$
DECLARE
    salaire_actuel NUMERIC(20, 2); -- Stocke le salaire actuel de l'employé
    est_preavis_requis BOOLEAN;       -- Indique si le préavis est requis pour le type de rupture
    est_indemnite BOOLEAN;       -- Indique si le préavis est requis pour le type de rupture
BEGIN
    -- Récupérer le salaire actuel de l'employé
    SELECT salaire
    INTO salaire_actuel 
    FROM ContratEmploye
    WHERE id = (SELECT id_contrat_actuel FROM Employes WHERE id = NEW.id_employe);

    -- Récupérer le statut du préavis requis du type de rupture
    SELECT preavis_requis, indemnite
    INTO est_preavis_requis, est_indemnite
    FROM TypeRupture
    WHERE id = NEW.id_type_rupture;

    -- Calculer la date de fin du contrat
    IF est_preavis_requis THEN
        NEW.date_fin_contrat := NEW.date_notification + INTERVAL '1 MONTH';
    ELSE
        NEW.date_fin_contrat := NEW.date_notification;
    END IF;

    -- Si un préavis est requis
    IF est_preavis_requis and est_indemnite THEN
        IF NEW.preavis_employe = NEW.preavis_entreprise THEN
            -- Les deux parties respectent ou ignorent le préavis, rien à faire
            RETURN NEW;
        ELSIF NOT NEW.preavis_employe THEN
            -- L'employé ne respecte pas le préavis
            NEW.indemnite_verse := NEW.indemnite_verse + salaire_actuel;
        ELSE
            -- L'entreprise ne respecte pas le préavis
            NEW.indemnite_verse := NEW.indemnite_verse - salaire_actuel;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

drop trigger before_insert_rupture_contrat on RuptureContrat;

CREATE TRIGGER before_insert_rupture_contrat
BEFORE INSERT
ON RuptureContrat
FOR EACH ROW
EXECUTE FUNCTION handle_rupture_contrat();
