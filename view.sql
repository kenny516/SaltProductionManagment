create or replace view candidats_elligibles as
SELECT p.*
FROM Postulations p
WHERE p.status = 'Retenu'
AND p.candidat_id IN (
    SELECT nc.idCandidat
    FROM noteCandidat nc
    GROUP BY nc.idCandidat
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote)
);