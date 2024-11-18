create or replace view candidats_elligibles as
SELECT c.*
FROM Postulations p
join candidats_postules c on p.candidat_id = c.id
WHERE p.status = 'Retenu'
AND p.candidat_id IN (
    SELECT nc.idCandidat
    FROM noteCandidat nc
    GROUP BY nc.idCandidat
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote)
);

create or replace view candidats_valides as 
select c.* from candidats c where c.id in (
	select distinct candidat_id from postulations p where p.status <> 'Employe'
);

create or replace view candidats_postules as
SELECT c.*
FROM candidats_valides c
WHERE c.id IN (
    SELECT DISTINCT p.candidat_id
    FROM Postulations p
);