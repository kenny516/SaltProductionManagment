CREATE TABLE HeuresSup (
    id SERIAL,
    id_employe INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    total_heures_sup DOUBLE PRECISION,
    taux_horaire DOUBLE PRECISION NOT NULL,
    montant DOUBLE PRECISION NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_employe) REFERENCES Employes(id)
);

CREATE OR REPLACE VIEW vue_heures_sup_semaine AS
SELECT 
    id_employe,
    DATE_TRUNC('week', date_debut) AS semaine,
    SUM(total_heures_sup) AS total_heures_sup,
    SUM(montant) AS total_montant
FROM HeuresSup
GROUP BY id_employe, DATE_TRUNC('week', date_debut);


