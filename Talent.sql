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
    date_candidature DATE DEFAULT CURRENT_DATE
);

CREATE TABLE typeNote(
    id SERIAL PRIMARY KEY,
    nomType VARCHAR(50) NOT NULL
);

CREATE TABLE noteCandidat(
    idCandidat INT REFERENCES Candidats(id),
    idTypeNote int REFERENCES TypeNote(id),
    note int ,
    PRIMARY KEY (idCandidat, idTypeNote) 
);

CREATE TABLE Employes (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(20),
    date_embauche DATE DEFAULT CURRENT_DATE
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
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    competence_id INT REFERENCES Competences(id) ON DELETE CASCADE,
    niveau INT CHECK (niveau >= 0 AND niveau <= 5),
    PRIMARY KEY (candidat_id, competence_id)
);

ALTER TABLE Candidats ADD COLUMN status VARCHAR(20) DEFAULT 'En attente';
ALTER TABLE Employes ADD COLUMN poste_id int REFERENCES Postes(id);

-- Créer une table d'association pour les candidatures
CREATE TABLE Postulations (
    id SERIAL PRIMARY KEY,
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    poste_id INT REFERENCES Postes(id) ON DELETE CASCADE,
    date_postulation DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'En attente'
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
    WHERE cc.candidat_id = NEW.candidat_id
    AND dp.idPoste = NEW.poste_id;

    -- Mettre à jour le statut de la candidature en fonction de la moyenne
    IF moyenne_niveau IS NOT NULL AND moyenne_niveau >= 3 THEN
        UPDATE Postulations SET status = 'Retenu' WHERE id = NEW.id;
    ELSE
        UPDATE Postulations SET status = 'Refus' WHERE id = NEW.id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Créer un trigger pour évaluer le statut d'une candidature
CREATE OR REPLACE TRIGGER trigger_evaluer_statut_candidat
AFTER INSERT ON CompetencesCandidats
FOR EACH ROW
EXECUTE FUNCTION evaluer_statut_candidat();


CREATE OR REPLACE TRIGGER trigger_evaluer_statut_candidat
AFTER INSERT ON CompetencesCandidats
FOR EACH ROW
EXECUTE FUNCTION evaluer_statut_candidat();


create or replace view candidats_elligibles as SELECT c.*
FROM Candidats c
JOIN (
    SELECT nc.idCandidat
    FROM noteCandidat nc
    GROUP BY nc.idCandidat
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote)
) AS subquery ON c.id = subquery.idCandidat;

CREATE TABLE Notifications (
    id SERIAL PRIMARY KEY,
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    message TEXT NOT NULL,
    date_heure TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut_notification VARCHAR(20) DEFAULT 'non_lu'
);

--ajout 

CREATE TABLE experience(
   experience_id SERIAL PRIMARY KEY,
   date_debut DATE NOT NULL,
   date_fin DATE,
   description TEXT NOT NULL,
   candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE
);

CREATE TABLE formation(
   id_formation SERIAL,
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

CREATE TABLE CandidatsFormations (
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    formation_id INT REFERENCES formation(id_formation) ON DELETE CASCADE,
    PRIMARY KEY (candidat_id, formation_id)
);

CREATE TABLE CandidatsExperiences (
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    experience_id INT REFERENCES experience(experience_id) ON DELETE CASCADE,
    PRIMARY KEY (candidat_id, experience_id)
);
