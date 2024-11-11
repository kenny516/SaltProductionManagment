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
    date_candidature DATE DEFAULT CURRENT_DATE,
    poste_id INT REFERENCES Postes(id)
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
    niveau INT CHECK (niveau >= 1 AND niveau <= 5),
    PRIMARY KEY (employe_id, competence_id)
);

CREATE TABLE CompetencesCandidats (
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    competence_id INT REFERENCES Competences(id) ON DELETE CASCADE,
    niveau INT CHECK (niveau >= 1 AND niveau <= 5),
    PRIMARY KEY (candidat_id, competence_id)
);

ALTER TABLE Candidats ADD COLUMN status VARCHAR(20) DEFAULT 'En attente';
ALTER TABLE Employes ADD COLUMN poste_id int REFERENCES Postes(id);

CREATE OR REPLACE FUNCTION evaluer_statut_candidat()
RETURNS TRIGGER AS $$
DECLARE
    moyenne_niveau FLOAT;
BEGIN
    -- Calculer la moyenne des niveaux de compétence pour le candidat et son poste
    SELECT AVG(cc.niveau) INTO moyenne_niveau
    FROM CompetencesCandidats cc
    JOIN detailsPoste dp ON cc.competence_id = dp.idCompetence
    WHERE cc.candidat_id = NEW.candidat_id
    AND dp.idPoste = (SELECT poste_id FROM Candidats WHERE id = NEW.candidat_id);

    -- Mettre à jour le statut du candidat en fonction de la moyenne
    IF moyenne_niveau IS NOT NULL AND moyenne_niveau >= 3 THEN
        UPDATE Candidats SET status = 'Retenu' WHERE id = NEW.candidat_id;
    ELSE
        UPDATE Candidats SET status = 'Refusé' WHERE id = NEW.candidat_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER trigger_evaluer_statut_candidat
AFTER INSERT ON CompetencesCandidats
FOR EACH ROW
EXECUTE FUNCTION evaluer_statut_candidat();
