
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



