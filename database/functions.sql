DELIMITER //

CREATE PROCEDURE GetDepenseSumByCentre(IN startDate DATE, IN endDate DATE)
BEGIN
    SELECT SUM(Depenses.montant * (PartsParCentre.valeur / 100)) AS total, Centre.*
    FROM Depenses
    JOIN AssoDepensesParts ON Depenses.id = AssoDepensesParts.idDepense 
    JOIN PartsParCentre ON AssoDepensesParts.idPart = PartsParCentre.id
    JOIN Centre ON Centre.id = PartsParCentre.idCentre
    WHERE Depenses.dateDepense BETWEEN startDate AND endDate
    GROUP BY Centre.id;
END //

DELIMITER ;


