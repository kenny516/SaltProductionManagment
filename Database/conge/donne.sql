INSERT INTO public.employes (id, nom, prenom, email, telephone, date_embauche, id_contrat_actuel) VALUES (1, 'kenny', 'kenny', 'kenny', '2002202', '2024-11-30', null);
INSERT INTO public.employes (id, nom, prenom, email, telephone, date_embauche, id_contrat_actuel) VALUES (2, 'kenshi', 'kenshi', 'kenshill', '21615384', '2020-11-06', null);


-- Insertion de types de congé
INSERT INTO TypeConge (nom, est_paye, cumulable, duree_max)
VALUES
    ('Conge normal', true, true, 3.00),
    ('Conge maladie', true, false, 0.00),
    ('Conge exceptionnel', false, false, 0.00);

INSERT INTO SoldeConge (id_employe, id_type_conge, mois, annee, annee_fin)
VALUES
-- Employé 1
(1, 1, 1, 2021, 2023),
(1, 1, 2, 2021, 2023),
(1, 1, 3, 2021, 2023),
(1, 1, 4, 2021, 2023),
(1, 1, 5, 2021, 2023),
(1, 1, 6, 2021, 2023),
(1, 1, 7, 2021, 2023),
(1, 1, 8, 2021, 2023),
(1, 1, 9, 2021, 2023),
(1, 1, 10, 2021, 2023),
(1, 1, 11, 2021, 2023),
(1, 1, 12, 2021, 2023),
-- Employé 2
(2, 1, 1, 2021, 2023),
(2, 1, 2, 2021, 2023),
(2, 1, 3, 2021, 2023),
(2, 1, 4, 2021, 2023),
(2, 1, 5, 2021, 2023),
(2, 1, 6, 2021, 2023),
(2, 1, 7, 2021, 2023),
(2, 1, 8, 2021, 2023),
(2, 1, 9, 2021, 2023),
(2, 1, 10, 2021, 2023),
(2, 1, 11, 2021, 2023),
(2, 1, 12, 2021, 2023),
-- Employé 1
(1, 1, 1, 2022, 2024),
(1, 1, 2, 2022, 2024),
(1, 1, 3, 2022, 2024),
(1, 1, 4, 2022, 2024),
(1, 1, 5, 2022, 2024),
(1, 1, 6, 2022, 2024),
(1, 1, 7, 2022, 2024),
(1, 1, 8, 2022, 2024),
(1, 1, 9, 2022, 2024),
(1, 1, 10, 2022, 2024),
(1, 1, 11, 2022, 2024),
(1, 1, 12, 2022, 2024),
-- Employé 2
(2, 1, 1, 2022, 2024),
(2, 1, 2, 2022, 2024),
(2, 1, 3, 2022, 2024),
(2, 1, 4, 2022, 2024),
(2, 1, 5, 2022, 2024),
(2, 1, 6, 2022, 2024),
(2, 1, 7, 2022, 2024),
(2, 1, 8, 2022, 2024),
(2, 1, 9, 2022, 2024),
(2, 1, 10, 2022, 2024),
(2, 1, 11, 2022, 2024),
(2, 1, 12, 2022, 2024);


INSERT INTO Conge (id_employe, id_type_conge, date_debut, date_fin,motif, status)
VALUES
(1, 1, '2022-03-10', '2022-03-15','vacance', 'En attente'),
(2, 1, '2022-08-01', '2022-08-05','famille', 'En attente'),
(1, 1, '2022-03-10', '2023-03-15','vacance', 'En attente');