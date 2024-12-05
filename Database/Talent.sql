\c postgres;
drop DATABASE gestion_talent;

CREATE DATABASE gestion_talent;

\c gestion_talent;

CREATE TABLE Postes (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    description TEXT,
    departement VARCHAR(100)
);

CREATE TABLE Candidats (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(20),
    mot_de_passe VARCHAR(255) NOT NULL
);

CREATE TABLE Offre_emploi (
    id SERIAL PRIMARY KEY,
    description TEXT,
    status BOOLEAN,
    Date_publication DATE DEFAULT CURRENT_DATE,
    poste_id INT REFERENCES Postes(id) ON DELETE CASCADE,
	salaire numeric(20,2),
    nbr_candidat_dm INT
);

-- Créer une table d'association pour les candidatures
CREATE TABLE Postulations (
    id SERIAL PRIMARY KEY,
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    Offre_emploi_id INT REFERENCES Offre_emploi(id) ON DELETE CASCADE,
    date_postulation DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'En attente'
);

CREATE TABLE typeNote(
    id SERIAL PRIMARY KEY,
    nomType VARCHAR(50) NOT NULL
);

CREATE TABLE noteCandidat(
    id_postulation INT REFERENCES Postulations(id),
    idTypeNote int REFERENCES TypeNote(id),
    note int ,
    PRIMARY KEY (id_postulation, idTypeNote) 
);

CREATE TABLE Employes (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(20),
    date_embauche DATE DEFAULT CURRENT_DATE,
    poste_id int REFERENCES Postes(id)
);

CREATE TABLE PostEmploye(
    id INT PRIMARY KEY,
    idEmploye int REFERENCES Employes(id),
    idPoste INT REFERENCES Postes(id),
    dateDebut date,
    dateFin date default null
);

CREATE TABLE TypeContrat (
    id INT PRIMARY KEY,
    nomType VARCHAR(50) UNIQUE NOT NULL,
    dureeMois INT 
);
CREATE TABLE ContratEmploye(
    idEmploye INT REFERENCES Employes(id),
    idContrat INT REFERENCES TypeContrat(id),
    dateDebut DATE,
    dateFin DATE 
);
CREATE TABLE Competences (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE detailsPoste(
    idPoste INT REFERENCES Postes (id) ON DELETE CASCADE,
    idCompetence INT REFERENCES Competences (id) ON DELETE CASCADE
);

CREATE TABLE CompetencesEmployes (
    employe_id INT REFERENCES Employes(id) ON DELETE CASCADE,
    competence_id INT REFERENCES Competences(id) ON DELETE CASCADE,
    niveau INT CHECK (niveau >= 0 AND niveau <= 5),
    PRIMARY KEY (employe_id, competence_id)
);

CREATE TABLE CompetencesCandidats (
    id_candidat INT REFERENCES Candidats(id) ON DELETE CASCADE,
    competence_id INT REFERENCES Competences(id) ON DELETE CASCADE,
    niveau INT CHECK (niveau >= 0 AND niveau <= 5),
    PRIMARY KEY (id_candidat, competence_id)
);

CREATE TABLE Notifications (
    id SERIAL PRIMARY KEY,
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    message TEXT NOT NULL,
    date_heure TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut_notification VARCHAR(20) DEFAULT 'non_lu'
);

CREATE TABLE formation(
   id_formation SERIAL PRIMARY KEY,
   date_debut DATE NOT NULL,
   date_fin DATE,
   description TEXT NOT NULL,
   candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE
);

CREATE TABLE experience(
   experience_id SERIAL PRIMARY KEY,
   date_debut DATE NOT NULL,
   date_fin DATE,
   description TEXT NOT NULL,
   candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE
);

CREATE TABLE Diplome(
   id_diplome SERIAL,
   diplome VARCHAR(255)  NOT NULL,
   niveau INTEGER NOT NULL,
   PRIMARY KEY(id_diplome)
);

CREATE TABLE CandidatsDiplomes (
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    diplome_id INT REFERENCES Diplome(id_diplome) ON DELETE CASCADE,
    PRIMARY KEY (candidat_id, diplome_id)
);

-- Mise à jour de la fonction evaluer_statut_candidat pour utiliser la table Postulations
CREATE OR REPLACE FUNCTION evaluer_statut_candidat()
RETURNS TRIGGER AS $$
DECLARE
    moyenne_niveau FLOAT;
BEGIN
    -- Calculer la moyenne des niveaux de compétence pour le candidat et le poste postulé
    SELECT AVG(cc.niveau) INTO moyenne_niveau
    FROM CompetencesCandidats cc
    JOIN detailsPoste dp ON cc.competence_id = dp.idCompetence
    JOIN Offre_emploi oe ON dp.idPoste = oe.poste_id
    WHERE cc.id_candidat = NEW.candidat_id
    AND oe.id = NEW.Offre_emploi_id;

    -- Mettre à jour le statut de la candidature en fonction de la moyenne
    IF moyenne_niveau IS NOT NULL AND moyenne_niveau >= 3 THEN
        -- Vérifier si le statut n'est pas déjà 'Retenu' pour éviter des mises à jour inutiles
        IF NEW.status != 'Retenu' THEN
            UPDATE Postulations SET status = 'Retenu' WHERE id = NEW.id;
        END IF;
    ELSE
        -- Vérifier si le statut n'est pas déjà 'Refus' pour éviter des mises à jour inutiles
        IF NEW.status != 'Refus' THEN
            UPDATE Postulations SET status = 'Refus' WHERE id = NEW.id;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Créer un trigger pour évaluer le statut d'une candidature
CREATE OR REPLACE TRIGGER trigger_evaluer_statut_candidat
AFTER INSERT OR UPDATE ON Postulations
FOR EACH ROW
EXECUTE FUNCTION evaluer_statut_candidat();

--- INSERTION DateFin dans contrat

CREATE OR REPLACE FUNCTION calculate_date_fin()
RETURNS TRIGGER AS $$
DECLARE
    contract_duration INT;  -- Variable temporaire pour stocker dureeMois
BEGIN
    -- Récupérer la durée en mois à partir du type de contrat
    SELECT dureeMois
    INTO STRICT contract_duration
    FROM TypeContrat
    WHERE id = NEW.id_type_contrat;

    -- Calculer la date de fin en ajoutant la durée en mois à la date de début
    NEW.date_fin := NEW.date_debut  + INTERVAL '1 month' * contract_duration;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Appliquer le trigger à la table ContratEmploye avant insertion
CREATE OR REPLACE TRIGGER trg_calculate_date_fin
BEFORE INSERT ON ContratEmploye
FOR EACH ROW
EXECUTE FUNCTION calculate_date_fin();

-- Create the trigger function
CREATE OR REPLACE FUNCTION update_offre_status()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the status was updated to 'employe'
    IF NEW.status = 'employe' THEN
        -- Check if the number of applicants meets or exceeds the available seats
        IF (SELECT COUNT(*) FROM Postulations WHERE Offre_emploi_id = NEW.Offre_emploi_id AND status = 'employe') >=
           (SELECT nbr_candidat_dm FROM Offre_emploi WHERE id = NEW.Offre_emploi_id) THEN
            -- Update the status of the Offre_emploi to its opposite
            UPDATE Offre_emploi
            SET status = false
            WHERE id = NEW.Offre_emploi_id;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger on the Postulations table
CREATE TRIGGER trigger_update_offre_status
AFTER UPDATE OF status ON Postulations
FOR EACH ROW
EXECUTE FUNCTION update_offre_status();



CREATE OR REPLACE VIEW V_detailsCandidat AS
SELECT 
    c.id AS candidat_id,
    c.nom,
    c.prenom,
    c.email,
    c.telephone,
    COALESCE(SUM(
        (EXTRACT(YEAR FROM AGE(COALESCE(e.date_fin, CURRENT_DATE), e.date_debut)) * 12 +
        EXTRACT(MONTH FROM AGE(COALESCE(e.date_fin, CURRENT_DATE), e.date_debut))
        )::INTEGER), 0) AS duree_experience_mois,
    COALESCE(MAX(d.niveau), 0) AS plus_haut_niveau_diplome
FROM
    Candidats c
LEFT JOIN
    experience e ON c.id = e.candidat_id
LEFT JOIN
    CandidatsDiplomes cd ON c.id = cd.candidat_id
LEFT JOIN
    Diplome d ON cd.diplome_id = d.id_diplome
GROUP BY
    c.id, c.nom, c.prenom, c.email, c.telephone;

-- TODO : implementrr dans les listes
CREATE or replace VIEW v_candidat_postulation AS
SELECT
    P.id AS id,
    C.id AS candidat_id,
    O.id AS offre_id,
    C.nom,
    C.prenom,
    C.email,
    C.telephone,
    C.mot_de_passe,
		O.poste_id,
		O.status as offre_status,
    P.date_postulation,
    P.status AS status
FROM
    Postulations P
JOIN
    Candidats C ON P.candidat_id = C.id
JOIN
    Offre_emploi O ON P.Offre_emploi_id = O.id;

CREATE OR REPLACE VIEW candidats_elligibles AS 
SELECT c.*
FROM v_candidat_postulation c
JOIN (
    SELECT nc.id_postulation
    FROM noteCandidat nc 
    GROUP BY nc.id_postulation
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote) and avg(nc.note) >= 5
) AS subquery ON c.id = subquery.id_postulation where c.offre_status is true;

create or replace view candidats_postules as select * from v_candidat_postulation where status  <> 'Employe' and offre_status is true;


CREATE TABLE HeuresSup (
    id SERIAL,
    id_employe INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    total_heures_sup DOUBLE PRECISION,
    taux_horaire DOUBLE PRECISION,
    montant DOUBLE PRECISION,
    PRIMARY KEY(id),
    FOREIGN KEY(id_employe) REFERENCES Employes(id)
);

CREATE OR REPLACE VIEW vue_heures_sup_semaine AS
SELECT 
    id_employe,
    DATE_TRUNC('week', date_debut)::DATE AS semaine,  -- Conversion explicite à DATE
    SUM(total_heures_sup) AS total_heures_sup,
    SUM(montant) AS total_montant
FROM HeuresSup
GROUP BY id_employe, DATE_TRUNC('week', date_debut);


-- 1. Fonction pour calculer total_heures_sup
CREATE OR REPLACE FUNCTION calculate_total_heures_sup()
RETURNS TRIGGER AS $$
BEGIN
    -- Calculer la durée en heures entre date_debut et date_fin
    NEW.total_heures_sup := EXTRACT(EPOCH FROM (NEW.date_fin - NEW.date_debut)) / 3600;

    -- Calculer le montant en utilisant taux_horaire et total_heures_sup
    NEW.montant := NEW.total_heures_sup * NEW.taux_horaire;

    -- Retourner la nouvelle ligne avec les valeurs calculées
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 2. Trigger pour appeler la fonction avant insertion
CREATE TRIGGER before_insert_calculate_total_heures_sup
BEFORE INSERT ON HeuresSup
FOR EACH ROW
EXECUTE FUNCTION calculate_total_heures_sup();


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
