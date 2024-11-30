CREATE OR REPLACE VIEW candidats_elligibles AS 
SELECT c.*
FROM Candidats c
JOIN (
    SELECT nc.id_postulation
    FROM noteCandidat nc 
    GROUP BY nc.id_postulation
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote)
) AS subquery ON c.id = subquery.id_postulation;

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

create or replace candidats_postules as select * from postulation 
