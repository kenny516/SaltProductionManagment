
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

-- TODO : implementrr dans les listes
CREATE or replace VIEW v_candidat_postulation AS
SELECT
    P.id AS id,
    C.id AS candidat_id,
    O.id AS offre_id,
    C.nom,
    C.prenom,
    C.email,
    C.telephone,
    C.mot_de_passe,
		O.poste_id,
		O.status as offre_status,
    P.date_postulation,
    P.status AS status
FROM
    Postulations P
JOIN
    Candidats C ON P.candidat_id = C.id
JOIN
    Offre_emploi O ON P.Offre_emploi_id = O.id;

CREATE OR REPLACE VIEW candidats_elligibles AS 
SELECT c.*
FROM v_candidat_postulation c
JOIN (
    SELECT nc.id_postulation
    FROM noteCandidat nc 
    GROUP BY nc.id_postulation
    HAVING COUNT(DISTINCT nc.idTypeNote) = (SELECT COUNT(*) FROM typeNote) and avg(nc.note) >= 5
) AS subquery ON c.id = subquery.id_postulation where c.offre_status is true;

create or replace view candidats_postules as select * from v_candidat_postulation where status  <> 'Employe' and offre_status is true;
