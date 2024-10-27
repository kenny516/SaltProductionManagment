INSERT INTO NatureCentre (nom) VALUES 
('NatureCentre1'),
('NatureCentre2'),
('NatureCentre3');

INSERT INTO Centre (nom, idNature) VALUES 
('Centre1', 1),
('Centre2', 2),
('Centre3', 3);

INSERT INTO UniteOeuvre (nom) VALUES 
('UniteOeuvre1'),
('UniteOeuvre2'),
('UniteOeuvre3');

INSERT INTO Produit (nom, idUniteOeuvre) VALUES 
('Produit1', 1),
('Produit2', 2),
('Produit3', 3);

INSERT INTO Production (idProduit, date, quantite) VALUES 
(1, '2024-09-01', 100.50),
(2, '2024-09-02', 200.75),
(3, '2024-09-03', 300.00);

INSERT INTO Rubrique (nom, estVariable, idUniteOeuvre, dateInsertion) VALUES 
('Rubrique1', TRUE, 1, '2024-09-01'),
('Rubrique2', FALSE, 2, '2024-09-02'),
('Rubrique3', TRUE, 3, '2024-09-03');

INSERT INTO PartsParCentre (idRubrique, idCentre, valeur, dateInsertion) VALUES 
(1, 1, 10.00, '2024-09-01'),
(1, 2, 50.00, '2024-09-01'),
(1, 3, 40.00, '2024-09-01'),
(2, 1, 10.00, '2024-09-01'),
(2, 2, 50.00, '2024-09-01'),
(2, 3, 40.00, '2024-09-01'),
(3, 1, 10.00, '2024-09-01'),
(3, 2, 50.00, '2024-09-01'),
(3, 3, 40.00, '2024-09-01');

INSERT INTO Depenses (dateDepense, montant) VALUES 
('2024-09-01', 1000),
('2024-09-02', 2000),
('2024-09-03', 3000);

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

--- Map<Centre, double>[ ] des sommes par centre

SELECT Centre.*, PartsParCentre.valeur as part, SUM(depenses.montant*(PartsParCentre.valeur/100)) as total FROM Depenses
JOIN AssoDepensesParts ON Depenses.id = AssoDepensesParts.idDepense 
JOIN PartsParCentre ON AssoDepensesParts.idPart = PartsParCentre.id
JOIN Centre ON Centre.id = PartsParCentre.idCentre
JOIN Rubrique ON Rubrique.id = PartsParCentre.idRubrique
WHERE dateDepense BETWEEN '2024-08-01' AND '2024-09-21' AND Rubrique.dateInsertion BETWEEN '2024-08-01' AND '2024-09-21' AND Rubrique.id = 1
GROUP BY Centre.id,PartsParCentre.idRubrique;


SELECT Centre.*, SUM(depenses.montant*(PartsParCentre.valeur/100)) as total FROM Depenses
JOIN AssoDepensesParts ON Depenses.id = AssoDepensesParts.idDepense 
JOIN PartsParCentre ON AssoDepensesParts.idPart = PartsParCentre.id
JOIN Centre ON Centre.id = PartsParCentre.idCentre
JOIN Rubrique ON Rubrique.id = PartsParCentre.idRubrique
WHERE dateDepense BETWEEN '2024-08-01' AND '2024-09-21' AND Rubrique.dateInsertion BETWEEN '2024-08-01' AND '2024-09-21'
GROUP BY Centre.id;

SELECT Depenses.* FROM Depenses 
JOIN AssoDepensesParts ON Depenses.id = AssoDepensesParts.idDepense 
JOIN PartsParCentre ON AssoDepensesParts.idPart = PartsParCentre.id
JOIN Rubrique ON Rubrique.id = PartsParCentre.idRubrique
WHERE dateDepense BETWEEN '2024-08-01' AND '2024-09-21' AND Rubrique.dateInsertion BETWEEN '2024-08-01' AND '2024-09-21'
AND Rubrique.estVariable IS False;


SELECT Rubrique.*, SUM(Depenses.montant) as total FROM Depenses 
JOIN AssoDepensesParts ON Depenses.id = AssoDepensesParts.idDepense 
JOIN PartsParCentre ON AssoDepensesParts.idPart = PartsParCentre.id
JOIN Rubrique ON Rubrique.id = PartsParCentre.idRubrique
WHERE dateDepense BETWEEN '2024-08-01' AND '2024-09-21' AND Rubrique.dateInsertion BETWEEN '2024-08-01' AND '2024-09-21'
GROUP BY Rubrique.id;



