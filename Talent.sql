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
    date_embauche DATE DEFAULT CURRENT_DATE,
    poste_id INT REFERENCES Postes(id)
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
