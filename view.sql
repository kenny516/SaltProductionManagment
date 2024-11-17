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

create or replace view candidats_postules as
SELECT c.*
FROM Candidats c
WHERE c.id IN (
    SELECT DISTINCT p.candidat_id
    FROM Postulations p
);