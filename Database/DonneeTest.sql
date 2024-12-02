-- Insérer des types de note
INSERT INTO typeNote (nomType) VALUES
('Technique'),
('Soft Skills'),
('Tests Pratiques');

-- Insérer des candidats
INSERT INTO Candidats (nom, prenom, email, telephone, mot_de_passe)
VALUES 
    ('Dupont', 'Jean', 'jean.dupont@example.com', '0123456789', 'motdepasse123'),
    ('Martin', 'Marie', 'marie.martin@example.com', '0987654321', 'mdpsecure456'),
    ('Durand', 'Pierre', 'pierre.durand@example.com', '0147258369', 'password789'),
    ('Lemoine', 'Claire', 'claire.lemoine@example.com', '0712345678', 'mypassword101'),
    ('Benoit', 'Lucie', 'lucie.benoit@example.com', '0685743920', 'securepass202');

-- Insérer des postes
INSERT INTO Postes (titre, description, departement) VALUES
('Développeur Java', 'Développement backend', 'Informatique'),
('Chargé de recrutement', 'Recrutement des candidats', 'Ressources Humaines');

-- Insérer des offres d'emploi
INSERT INTO Offre_emploi (description, status, poste_id, nbr_candidat_dm) VALUES
('Recrutement pour un poste de Développeur Java. Expérience en développement backend requise.', TRUE, 1, 5),
('Recherche d un Chargé de recrutement pour gérer les candidatures et organiser les entretiens.', TRUE, 2, 3);

-- Insérer des compétences
INSERT INTO Competences (nom, description) VALUES
('Java', 'Développement Java'),
('SQL', 'Gestion des bases de données SQL'),
('Communication', 'Compétence en communication'),
('Gestion de projet', 'Gestion des projets');

-- Insérer des relations compétences-poste
INSERT INTO detailsPoste (idPoste, idCompetence) VALUES
(1, 1),  -- Développeur Java -> Java
(1, 2),  -- Développeur Java -> SQL
(2, 3),  -- Chargé de recrutement -> Communication
(2, 4);  -- Chargé de recrutement -> Gestion de projet

-- Insérer des candidats dans la table de compétences candidats avec niveaux
INSERT INTO CompetencesCandidats (id_candidat, competence_id, niveau) VALUES
(1, 1, 4),  -- Jean -> Java (niveau 4)
(1, 2, 5),  -- Jean -> SQL (niveau 5)
(1, 3, 3),  -- Jean -> Communication (niveau 3)
(2, 1, 2),  -- Alice -> Java (niveau 2)
(2, 2, 3),  -- Alice -> SQL (niveau 3)
(2, 4, 4),  -- Alice -> Gestion de projet (niveau 4)
(3, 1, 3),  -- Pierre -> Java (niveau 3)
(3, 3, 2);  -- Pierre -> Communication (niveau 2)

-- Insérer des postulants
INSERT INTO Postulations (candidat_id, Offre_emploi_id, date_postulation, status) VALUES
(1, 1, '2024-11-01', 'En attente'),
(2, 2, '2024-11-02', 'En attente');

-- Insérer des notes pour les candidats
INSERT INTO noteCandidat (id_postulation, idTypeNote, note) VALUES
(1, 1, 8),  -- Jean, Technique (8)
(1, 2, 6),  -- Jean, Soft Skills (6)
(1, 3, 7),  -- Jean, Tests Pratiques (7)
(2, 1, 5),  -- Alice, Technique (5)
(2, 2, 7),  -- Alice, Soft Skills (7)
(2, 3, 6);  -- Alice, Tests Pratiques (6)

-- Insérer des notifications
INSERT INTO Notifications (candidat_id, message, statut_notification) VALUES
(1, 'Votre candidature pour le poste de Développeur Java a été retenue.', 'lu'),
(1, 'Votre candidature pour le poste de Chargé de recrutement a été refusée.', 'non_lu'),
(1, 'Votre candidature pour le poste de Développeur Java a été refusée.', 'non_lu');

-- Insérer des expériences
INSERT INTO experience (date_debut, date_fin, description, candidat_id) VALUES
('2020-01-01', '2022-01-01', 'Développement de logiciels pour une entreprise', 1),
('2021-03-01', '2023-03-01', 'Recrutement et gestion des entretiens', 2),
('2019-06-01', '2021-06-01', 'Gestion de projet informatique', 3);

-- Insérer des formations
INSERT INTO formation (date_debut, date_fin, description, candidat_id) VALUES
('2017-09-01', '2020-06-01', 'Licence en informatique', 1),
('2018-09-01', '2021-06-01', 'Master en Ressources Humaines', 2),
('2016-09-01', '2019-06-01', 'Master en Management', 3);

-- Insérer des diplômes
INSERT INTO Diplome (diplome, niveau) VALUES
('Licence en Informatique', 3),
('Master en Ressources Humaines', 5),
('Master en Management', 5);

-- Insérer des relations candidats-diplômes
INSERT INTO CandidatsDiplomes (candidat_id, diplome_id) VALUES
(1, 1),  -- Jean -> Licence en Informatique
(2, 2),  -- Alice -> Master en Ressources Humaines
(3, 3);  -- Pierre -> Master en Management

-- Insérer des types de contrat
INSERT INTO TypeContrat (id, nomType, dureeMois) VALUES 
(1, 'CDD', 24),
(2, 'ESSAI', 3),
(3, 'CDI', 0);
