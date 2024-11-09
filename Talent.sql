CREATE DATABASE Gestion_talent;

CREATE TABLE Postes (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    description TEXT,
    departement VARCHAR(100),
);

CREATE TABLE Candidats (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(20),
    date_candidature DATE DEFAULT CURRENT_DATE,
    poste_id INT REFERENCES Postes(id),
);

CREATE TABLE Employes (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(20),
    date_embauche DATE DEFAULT CURRENT_DATE,
    poste_id INT REFERENCES Postes(id),
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
    niveau INT CHECK (niveau >= 1 AND niveau <= 5)
    PRIMARY KEY (employe_id, competence_id)
);

CREATE TABLE CompetencesCandidats (
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    competence_id INT REFERENCES Competences(id) ON DELETE CASCADE,
    niveau INT CHECK (niveau >= 1 AND niveau <= 5)
    PRIMARY KEY (candidat_id, competence_id)
);