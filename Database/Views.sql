---Pour le filtre
CREATE OR REPLACE VIEW V_detailsCandidat AS
SELECT
    c.id AS candidat_id,
    c.nom,
    c.prenom,
    c.email,
    c.telephone,
    COALESCE(SUM(
        (EXTRACT(YEAR FROM AGE(COALESCE(e.date_fin, CURRENT_DATE), e.date_debut)) * 12 +
        EXTRACT(MONTH FROM AGE(COALESCE(e.date_fin, CURRENT_DATE), e.date_debut))
        )::INTEGER), 0) AS duree_experience_mois,
    COALESCE(MAX(d.niveau), 0) AS plus_haut_niveau_diplome
FROM
    Candidats c
LEFT JOIN
    experience e ON c.id = e.candidat_id
LEFT JOIN
    CandidatsDiplomes cd ON c.id = cd.candidat_id
LEFT JOIN
    Diplome d ON cd.diplome_id = d.id_diplome
GROUP BY
    c.id, c.nom, c.prenom, c.email, c.telephone;

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