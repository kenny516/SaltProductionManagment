CREATE TABLE HeuresSup (
    id SERIAL,
    id_employe INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    total_heures_sup DOUBLE PRECISION,
    taux_horaire DOUBLE PRECISION,
    montant DOUBLE PRECISION,
    PRIMARY KEY(id),
    FOREIGN KEY(id_employe) REFERENCES Employes(id)
);

CREATE OR REPLACE VIEW vue_heures_sup_semaine AS
SELECT 
    id_employe,
    DATE_TRUNC('week', date_debut)::DATE AS semaine,  -- Conversion explicite à DATE
    SUM(total_heures_sup) AS total_heures_sup,
    SUM(montant) AS total_montant
FROM HeuresSup
GROUP BY id_employe, DATE_TRUNC('week', date_debut);


-- 1. Fonction pour calculer total_heures_sup
CREATE OR REPLACE FUNCTION calculate_total_heures_sup()
RETURNS TRIGGER AS $$
BEGIN
    -- Calculer la durée en heures entre date_debut et date_fin
    NEW.total_heures_sup := EXTRACT(EPOCH FROM (NEW.date_fin - NEW.date_debut)) / 3600;

    -- Calculer le montant en utilisant taux_horaire et total_heures_sup
    NEW.montant := NEW.total_heures_sup * NEW.taux_horaire;

    -- Retourner la nouvelle ligne avec les valeurs calculées
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 2. Trigger pour appeler la fonction avant insertion
CREATE TRIGGER before_insert_calculate_total_heures_sup
BEFORE INSERT ON HeuresSup
FOR EACH ROW
EXECUTE FUNCTION calculate_total_heures_sup();
