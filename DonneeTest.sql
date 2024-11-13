-- Insérer des postes
INSERT INTO Postes (titre, description, departement) VALUES
('Développeur Java', 'Responsable du développement des applications Java', 'Informatique'),
('Chef de Projet', 'Gestion des projets informatiques', 'Gestion de projets'),
('Technicien Support', 'Assistance technique aux utilisateurs', 'Support');

-- Insérer des types de contrat
INSERT INTO TypeContrat (id, nomType, dureeMois) VALUES
(1, 'ESSAI', 3),
(2, 'CDI', 12),
(3, 'CDD', 6);

-- Insérer des compétences
INSERT INTO Competences (nom, description) VALUES
('Programmation Java', 'Capacité à développer en Java'),
('Gestion de projet', 'Capacité à gérer des projets informatiques'),
('Support Technique', 'Capacité à fournir une assistance technique');

-- Insérer des détails de poste
INSERT INTO detailsPoste (idPoste, idCompetence) VALUES
(1, 1), -- Développeur Java doit avoir la compétence Programmation Java
(2, 2), -- Chef de Projet doit avoir la compétence Gestion de projet
(3, 3); -- Technicien Support doit avoir la compétence Support Technique

-- Insérer des candidats
INSERT INTO Candidats (nom, prenom, email, telephone, poste_id) VALUES
('Martin', 'Pierre', 'pierre.martin@example.com', '0601020304', 1),
('Durand', 'Sophie', 'sophie.durand@example.com', '0611121314', 2),
('Dupont', 'Jacques', 'jacques.dupont@example.com', '0622233344', 3);

-- Insérer des notes pour les candidats
INSERT INTO typeNote (nomType) VALUES
('Test Technique'),
('Entretien HR'),
('Entretien Technique');

INSERT INTO noteCandidat (idCandidat, idTypeNote, note) VALUES
(1, 1, 4),
(1, 2, 5),
(2, 1, 3),
(2, 2, 2),
(3, 1, 4),
(3, 2, 4);

-- Insérer des compétences pour les candidats
INSERT INTO CompetencesCandidats (candidat_id, competence_id, niveau) VALUES
(1, 1, 4), -- Pierre Martin a un bon niveau en Programmation Java
(2, 2, 3), -- Sophie Durand a un niveau moyen en Gestion de projet
(3, 3, 4); -- Jacques Dupont a un bon niveau en Support Technique

-- Insérer des employés
INSERT INTO Employes (nom, prenom, email, telephone, date_embauche, poste_id) VALUES
('Leroy', 'Alice', 'alice.leroy@example.com', '0623456789', CURRENT_DATE, 1),
('Bernard', 'Luc', 'luc.bernard@example.com', '0678901234', CURRENT_DATE, 2),
('Moreau', 'Emma', 'emma.moreau@example.com', '0609876543', CURRENT_DATE, 3);

-- Assurez-vous que les entrées dans TypeContrat et autres tables ont bien été insérées et correspondent aux IDs utilisés dans vos fonctions et triggers.


-- Insérer plus de postes
INSERT INTO Postes (titre, description, departement) VALUES
('Analyste Business', 'Analyse des processus métier et des besoins', 'Consulting'),
('Administrateur Système', 'Gestion et maintenance des systèmes informatiques', 'IT Infrastructure');

-- Insérer plus de types de contrat
INSERT INTO TypeContrat (id, nomType, dureeMois) VALUES
(4, 'Stage', 2),
(5, 'Freelance', 6);

-- Insérer plus de compétences
INSERT INTO Competences (nom, description) VALUES
('Analyse de données', 'Capacité à analyser et interpréter des données complexes'),
('Administration Réseau', 'Compétence en gestion des réseaux informatiques'),
('Communication', 'Capacité à communiquer efficacement dans un environnement professionnel');

-- Insérer plus de détails de poste
INSERT INTO detailsPoste (idPoste, idCompetence) VALUES
(4, 1), -- L'Analyste Business doit avoir la compétence Programmation Java
(4, 3), -- L'Analyste Business doit aussi avoir la compétence Communication
(5, 2), -- L'Administrateur Système doit avoir la compétence Administration Réseau
(5, 3); -- L'Administrateur Système doit avoir la compétence Communication

-- Insérer plus de candidats
INSERT INTO Candidats (nom, prenom, email, telephone, poste_id) VALUES
('Roux', 'Hélène', 'helene.roux@example.com', '0633344556', 4),
('Lambert', 'Julien', 'julien.lambert@example.com', '0644455566', 5);

-- Insérer des notes pour les nouveaux candidats
INSERT INTO noteCandidat (idCandidat, idTypeNote, note) VALUES
(4, 1, 4),
(4, 2, 5),
(5, 1, 3),
(5, 2, 4);

-- Insérer des compétences pour les nouveaux candidats
INSERT INTO CompetencesCandidats (candidat_id, competence_id, niveau) VALUES
(4, 1, 4), -- Hélène Roux a un bon niveau en Programmation Java
(4, 3, 5), -- Hélène Roux a un excellent niveau en Communication
(5, 2, 3), -- Julien Lambert a un niveau moyen en Administration Réseau
(5, 3, 4); -- Julien Lambert a un bon niveau en Communication

-- Insérer des employés supplémentaires
INSERT INTO Employes (nom, prenom, email, telephone, date_embauche, poste_id) VALUES
('Durant', 'Paul', 'paul.durant@example.com', '0625556677', CURRENT_DATE - INTERVAL '6 months', 4),
('Leclerc', 'Marie', 'marie.leclerc@example.com', '0655566778', CURRENT_DATE - INTERVAL '1 year', 5);

-- Insérer des compétences pour les employés
INSERT INTO CompetencesEmployes (employe_id, competence_id, niveau) VALUES
(1, 1, 5), -- Alice Leroy a un excellent niveau en Programmation Java
(2, 2, 4), -- Luc Bernard a un bon niveau en Gestion de projet
(3, 3, 5), -- Emma Moreau a un excellent niveau en Support Technique
(4, 1, 4), -- Paul Durant a un bon niveau en Programmation Java
(4, 3, 5), -- Paul Durant a un excellent niveau en Communication
(5, 2, 4), -- Marie Leclerc a un bon niveau en Administration Réseau
(5, 3, 4); -- Marie Leclerc a un bon niveau en Communication
