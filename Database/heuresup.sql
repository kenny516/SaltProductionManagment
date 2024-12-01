CREATE TABLE HeuresSup (
    id SERIAL,
    id_employe INT NOT NULL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    total_heures_sup DOUBLE PRECISION,
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


CREATE VIEW total_heures_sup AS
SELECT
    e.id AS employe_id,
    EXTRACT(week FROM hs.date_debut) AS semaine,
    SUM(hs.total_heures_sup) AS total_heures,
    SUM(hs.montant) AS total_montant
FROM
    heures_supplementaires hs
JOIN
    employes e ON hs.id_employe = e.id
GROUP BY
    e.id, EXTRACT(week FROM hs.date_debut)
HAVING
    SUM(hs.total_heures_sup) <= 20;
