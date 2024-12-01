
drop table PostEmploye ;

alter table contratemploye rename column idcontrat to id_type_contrat;
alter table contratemploye rename column datedebut to date_debut;
alter table contratemploye rename column datefin to date_fin;
alter table contratemploye rename column idemploye to id_employe;

alter table contratemploye add column id_poste integer not null references postes(id);
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
	preavis_effectue BOOLEAN NOT NULL,
	motif VARCHAR(50),
	indemnite_verse NUMERIC(15, 2) NOT NULL,
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

CREATE OR REPLACE VIEW v_employes_actuels AS
SELECT
    e.id,
    e.id_contrat_actuel,
    c.date_debut,
    c.salaire,
    c.taux_horaire
FROM
    Employes e
JOIN
    ContratEmploye c
ON
    e.id_contrat_actuel = c.id
WHERE
    c.date_fin IS NULL OR c.date_fin >= NOW();

