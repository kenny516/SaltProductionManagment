-- Duree experience d'un candidat
SELECT 
    candidat_id, 
    SUM(EXTRACT(YEAR FROM AGE(COALESCE(date_fin, CURRENT_DATE), date_debut)) * 12 +
        EXTRACT(MONTH FROM AGE(COALESCE(date_fin, CURRENT_DATE), date_debut))) AS duree_totale_mois
FROM 
    experience
GROUP BY 
    candidat_id;

CREATE OR REPLACE VIEW V_Details_Candidat AS 
SELECT c.*,     
       SUM(EXTRACT(YEAR FROM AGE(COALESCE(e.date_fin, CURRENT_DATE), e.date_debut)) * 12 +
           EXTRACT(MONTH FROM AGE(COALESCE(e.date_fin, CURRENT_DATE), e.date_debut))) AS DureeExperience
FROM Candidats c
JOIN experience e ON e.candidat_id = c.id
GROUP BY c.id;


