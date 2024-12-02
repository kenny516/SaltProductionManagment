-- Mise à jour de la fonction evaluer_statut_candidat pour utiliser la table Postulations
CREATE OR REPLACE FUNCTION evaluer_statut_candidat()
RETURNS TRIGGER AS $$
DECLARE
    moyenne_niveau FLOAT;
BEGIN
    -- Calculer la moyenne des niveaux de compétence pour le candidat et le poste postulé
    SELECT AVG(cc.niveau) INTO moyenne_niveau
    FROM CompetencesCandidats cc
    JOIN detailsPoste dp ON cc.competence_id = dp.idCompetence
    JOIN Offre_emploi oe ON dp.idPoste = oe.poste_id
    WHERE cc.id_candidat = NEW.candidat_id
    AND oe.id = NEW.Offre_emploi_id;

    -- Mettre à jour le statut de la candidature en fonction de la moyenne
    IF moyenne_niveau IS NOT NULL AND moyenne_niveau >= 3 THEN
        -- Vérifier si le statut n'est pas déjà 'Retenu' pour éviter des mises à jour inutiles
        IF NEW.status != 'Retenu' THEN
            UPDATE Postulations SET status = 'Retenu' WHERE id = NEW.id;
        END IF;
    ELSE
        -- Vérifier si le statut n'est pas déjà 'Refus' pour éviter des mises à jour inutiles
        IF NEW.status != 'Refus' THEN
            UPDATE Postulations SET status = 'Refus' WHERE id = NEW.id;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Créer un trigger pour évaluer le statut d'une candidature
CREATE OR REPLACE TRIGGER trigger_evaluer_statut_candidat
AFTER INSERT OR UPDATE ON Postulations
FOR EACH ROW
EXECUTE FUNCTION evaluer_statut_candidat();

-- Create the trigger function
CREATE OR REPLACE FUNCTION update_offre_status()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the status was updated to 'employe'
    IF NEW.status = 'employe' THEN
        -- Check if the number of applicants meets or exceeds the available seats
        IF (SELECT COUNT(*) FROM Postulations WHERE Offre_emploi_id = NEW.Offre_emploi_id AND status = 'employe') >=
           (SELECT nbr_candidat_dm FROM Offre_emploi WHERE id = NEW.Offre_emploi_id) THEN
            -- Update the status of the Offre_emploi to its opposite
            UPDATE Offre_emploi
            SET status = false
            WHERE id = NEW.Offre_emploi_id;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger on the Postulations table
CREATE TRIGGER trigger_update_offre_status
AFTER UPDATE OF status ON Postulations
FOR EACH ROW
EXECUTE FUNCTION update_offre_status();
