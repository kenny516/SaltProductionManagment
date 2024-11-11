
--- INSERTION DateFin dans contrat
CREATE OR REPLACE FUNCTION set_date_fin_contrat()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.dateDebut IS NOT NULL THEN
        SELECT dureeMois INTO NEW.dateFin
        FROM TypeContrat
        WHERE id = NEW.idContrat;
    
        IF NEW.dateFin IS NULL OR NEW.dateFin = 0 THEN
            NEW.dateFin := NULL;
        ELSE
            NEW.dateFin := NEW.dateDebut + INTERVAL '1 month' * NEW.dateFin;
        END IF;
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_set_date_fin_contrat
BEFORE INSERT ON ContratEmploye
FOR EACH ROW
EXECUTE FUNCTION set_date_fin_contrat();


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



