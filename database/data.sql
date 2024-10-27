-- Insertion des types de centres (exemples réels)
INSERT INTO NatureCentre (nom) VALUES 
('Structure'), 
('Fonctionnel');

-- Insertion des centres de production
INSERT INTO Centre (nom, idNature) VALUES 
('Usine principale', 1), 
('Fournisseur de sel brut', 1), 
('Centre logistique', 2);

-- Insertion des unités d'œuvre (exemples réels : production de sel, transport)
INSERT INTO UniteOeuvre (nom) VALUES 
('KG'), 
('Cout periodique'), 
('Litre');

-- Insertion des produits (exemples réels)
INSERT INTO Produit (nom, idUniteOeuvre) VALUES 
('Sel brut', 1), 
('Sel raffiné', 1), 
('Sel en vrac', 1);

-- Insertion des données de production
INSERT INTO Production (idProduit, date, quantite) VALUES 
(1, '2024-09-01', 150.50), 
(2, '2024-09-02', 250.75), 
(3, '2024-09-03', 350.00);

-- Insertion des rubriques (exemples : coûts fixes, variables)
INSERT INTO Rubrique (nom, estVariable, idUniteOeuvre, dateInsertion) VALUES 
('Cout ouvriers', TRUE, 2, '2024-09-01'), 
('Achat carburant', FALSE, 3, '2024-09-02'), 
('Maintenance machines', TRUE, 2, '2024-09-03');

-- Insertion des parts par centre
INSERT INTO PartsParCentre (idRubrique, idCentre, valeur, dateInsertion) VALUES 
(1, 1, 20.00, '2024-09-01'), 
(1, 2, 40.00, '2024-09-01'), 
(1, 3, 40.00, '2024-09-01'), 
(2, 1, 30.00, '2024-09-02'), 
(2, 2, 50.00, '2024-09-02'), 
(2, 3, 20.00, '2024-09-02'), 
(3, 1, 25.00, '2024-09-03'), 
(3, 2, 45.00, '2024-09-03'), 
(3, 3, 30.00, '2024-09-03');

-- Insertion des dépenses
INSERT INTO Depenses (dateDepense, prixU, quantite, idRubrique) VALUES 
('2024-09-01', 1400000,30,1), 
('2024-09-02', 4900,1000,2), 
('2024-09-03', 340000,15,3);

-- Association des dépenses avec les parts
INSERT INTO AssoDepensesParts (idDepense, idPart) VALUES 
(1, 1), 
(1, 2), 
(1, 3), 
(2, 4), 
(2, 5), 
(2, 6), 
(3, 7), 
(3, 8), 
(3, 9);
