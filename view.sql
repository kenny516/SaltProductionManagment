create or replace view candidats_elligibles as SELECT c.*
FROM Candidats c
JOIN (
    SELECT nc.idCandidat
    FROM noteCandidat nc
    GROUP BY nc.idCandidat
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote)
) AS subquery ON c.id = subquery.idCandidat and c.status = 'Retenu';
