-- Insertion des données dans la table Postes
INSERT INTO Postes (titre, description, departement)
VALUES 
    ('Développeur Java', 'Responsable du développement des applications Java', 'Informatique'),
    ('Analyste de données', 'Analyse des données et création de rapports', 'Marketing'),
    ('Chef de projet', 'Gestion des projets de développement', 'Gestion de projet');

-- Insertion des données dans la table Candidats
INSERT INTO Candidats (nom, prenom, email, telephone, date_candidature, poste_id)
VALUES 
    ('Dupont', 'Jean', 'jean.dupont@example.com', '0612345678', CURRENT_DATE, 1),
    ('Martin', 'Marie', 'marie.martin@example.com', '0698765432', CURRENT_DATE, 2),
    ('Durand', 'Paul', 'paul.durand@example.com', '0712345678', CURRENT_DATE, 3);

-- Insertion des données dans la table typeNote
INSERT INTO typeNote (nomType)
VALUES 
    ('Technique'),
    ('Entretien'),
    ('Expérience');

-- Insertion des données dans la table noteCandidat
INSERT INTO noteCandidat (idCandidat, idTypeNote, note)
VALUES 
    (1, 1, 85),
    (1, 2, 90),
    (2, 1, 78),
    (3, 3, 88);

-- Insertion des données dans la table Employes
INSERT INTO Employes (nom, prenom, email, telephone, date_embauche, poste_id)
VALUES 
    ('Leroy', 'Alice', 'alice.leroy@example.com', '0623456789', CURRENT_DATE, 1),
    ('Bernard', 'Luc', 'luc.bernard@example.com', '0678901234', CURRENT_DATE, 2),
    ('Moreau', 'Emma', 'emma.moreau@example.com', '0609876543', CURRENT_DATE, 3);

-- Insertion des données dans la table PostEmploye
INSERT INTO PostEmploye (id, idEmploye, idPoste, dateDebut, dateFin)
VALUES 
    (1, 1, 1, '2024-01-01', NULL),
    (2, 2, 2, '2023-05-01', '2024-05-01'),
    (3, 3, 3, '2022-02-01', NULL);

-- Insertion des données dans la table TypeContrat
INSERT INTO TypeContrat (id, nomType, dureeMois)
VALUES 
    (1, 'CDI', NULL),
    (2, 'CDD', 12),
    (3, 'Stage', 6);

-- Insertion des données dans la table ContratEmploye
INSERT INTO ContratEmploye (idEmploye, idContrat, dateDebut, dateFin)
VALUES 
    (1, 1, '2024-01-01', NULL),
    (2, 2, '2023-05-01', '2024-05-01'),
    (3, 3, '2023-02-01', '2023-08-01');

-- Insertion des données dans la table Competences
INSERT INTO Competences (nom, description)
VALUES 
    ('Java', 'Compétences en développement Java'),
    ('SQL', 'Compétences en gestion de bases de données SQL'),
    ('Gestion de projet', 'Compétences en gestion de projet');

-- Insertion des données dans la table detailsPoste
INSERT INTO detailsPoste (idPoste, idCompetence)
VALUES 
    (1, 1), -- Développeur Java nécessite la compétence Java
    (2, 2), -- Analyste de données nécessite SQL
    (3, 3); -- Chef de projet nécessite la compétence en gestion de projet

-- Insertion des données dans la table CompetencesEmployes
INSERT INTO CompetencesEmployes (employe_id, competence_id, niveau)
VALUES 
    (1, 1, 5), -- Alice Leroy a un niveau de compétence élevé en Java
    (2, 2, 4), -- Luc Bernard est compétent en SQL
    (3, 3, 5); -- Emma Moreau est compétente en gestion de projet

-- Insertion des données dans la table CompetencesCandidats
INSERT INTO CompetencesCandidats (candidat_id, competence_id, niveau)
VALUES 
    (1, 1, 4), -- Jean Dupont a une compétence en Java
    (2, 2, 3), -- Marie Martin a une compétence en SQL
    (3, 3, 5); -- Paul Durand a une compétence élevée en gestion de projet
