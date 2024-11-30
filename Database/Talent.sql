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
    telephone VARCHAR(20)
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

ALTER TABLE Employes ADD COLUMN poste_id int REFERENCES Postes(id);

-- Créer une table d'association pour les candidatures
CREATE TABLE Postulations (
    id SERIAL PRIMARY KEY,
    candidat_id INT REFERENCES Candidats(id) ON DELETE CASCADE,
    poste_id INT REFERENCES Postes(id) ON DELETE CASCADE,
    date_postulation DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'En attente'
);

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
   id_formation SERIAL PRIMARY KEY,
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
