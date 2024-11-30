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

--- INSERTION DateFin dans contrat

CREATE OR REPLACE FUNCTION calculate_date_fin()
RETURNS TRIGGER AS $$
DECLARE
    contract_duration INT;  -- Variable temporaire pour stocker dureeMois
BEGIN
    -- Récupérer la durée en mois à partir du type de contrat
    SELECT dureeMois
    INTO STRICT contract_duration
    FROM TypeContrat
    WHERE id = NEW.idContrat;

    -- Calculer la date de fin en ajoutant la durée en mois à la date de début
    NEW.dateFin := NEW.dateDebut + INTERVAL '1 month' * contract_duration;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Appliquer le trigger à la table ContratEmploye avant insertion
CREATE TRIGGER trg_calculate_date_fin
BEFORE INSERT ON ContratEmploye
FOR EACH ROW
EXECUTE FUNCTION calculate_date_fin();



-- Creation ContratEmploye
CREATE OR REPLACE FUNCTION insert_contrat_employe()
RETURNS TRIGGER AS $$
DECLARE
    essai_contrat_id INT;
BEGIN
    SELECT id INTO essai_contrat_id
    FROM TypeContrat
    WHERE nomType = 'ESSAI';

    INSERT INTO ContratEmploye (idEmploye, idContrat, dateDebut)
    VALUES (NEW.id, essai_contrat_id, NEW.date_embauche);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_insert_contrat_employe
AFTER INSERT ON Employes
FOR EACH ROW
EXECUTE FUNCTION insert_contrat_employe();



