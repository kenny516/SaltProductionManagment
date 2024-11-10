
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
