CREATE TABLE HeuresSup(
	id SERIAL,
	id_employe INTEGER NOT NULL,
	date_debut TIMESTAMP NOT NULL,
	date_fin TIMESTAMP NOT NULL,
	total_heures_sup NUMERIC(5, 2),
	taux_horaire NUMERIC(2, 1) NOT NULL,
	montant NUMERIC(15, 2) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_employe) REFERENCES Employe(id)
);



CREATE OR REPLACE VIEW vue_heures_sup_semaine AS
SELECT 
    id_employe,
    DATE_TRUNC('week', date_debut) AS semaine,
    SUM(total_heures_sup) AS total_heures_sup,
    SUM(montant) AS total_montant
FROM HeuresSup
GROUP BY id_employe, DATE_TRUNC('week', date_debut);


