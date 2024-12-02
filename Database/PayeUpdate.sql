CREATE TABLE IF NOT EXISTS paye_details(
    id SERIAL,
	id_employe INTEGER NOT NULL,
	mois INTEGER NOT NULL,
	annee INTEGER NOT NULL,
	heure_normale NUMERIC(5, 2) NOT NULL DEFAULT 160.0,
	heure_sup NUMERIC(20, 2),
    montant_heure_sup NUMERIC(20, 2) NOT NULL,
	salaire_base NUMERIC(20, 2) NOT NULL,
	avance NUMERIC(20, 2),
    nb_heure_abs NUMERIC(20,2) NOT NULL DEFAULT 0,
    droit_conge NUMERIC(20,2) NOT NULL DEFAULT 0,
    droit_preavis NUMERIC(20,2) DEFAULT 0,
    indemnite NUMERIC(10,2) NOT NULL DEFAULT 0,
    prime_diverse NUMERIC(10,2) NOT NULL DEFAULT 0,
	irsa NUMERIC(10,2) NOT NULL DEFAULT 0,
    cnaps NUMERIC(10,2) NOT NULL,
    sanitaire NUMERIC(10,2) NOT NULL,
	total NUMERIC(20, 2) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_employe) REFERENCES Employes(id)
);
CREATE TABLE IF NOT EXISTS type_bonus_salaire(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30) NOT NULL
);
insert into type_bonus_salaire VALUES 
(default, 'INDEMNITE'), (default,'PRIME');

CREATE TABLE bonus_salaire(
    id SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employes(id),
    type_bonus INT REFERENCES type_bonus_salaire(id),
    montant NUMERIC(10,2),
    date_bonus DATE
);

CREATE OR REPLACE FUNCTION calculer_irsa(p_id_employe INT)
RETURNS NUMERIC(10, 2) AS $$
DECLARE
    sal NUMERIC(10, 2);
    irsa_total NUMERIC(10, 2) := 0;
BEGIN
    SELECT salaire INTO sal
    FROM ContratEmploye
    WHERE id_employe = p_id_employe 
    AND date_debut = (SELECT MAX(date_debut) FROM ContratEmploye WHERE id_employe = p_id_employe);

    IF sal > 600000 THEN
        irsa_total := irsa_total + (salaire - 600000) * 0.20;
        sal := 600000;
    END IF;

    IF sal > 500000 THEN
        irsa_total := irsa_total + (sal - 500000) * 0.15;
        sal := 500000;
    END IF;

    IF sal > 400000 THEN
        irsa_total := irsa_total + (sal - 400000) * 0.10;
        sal := 400000;
    END IF;

    IF sal > 350000 THEN
        irsa_total := irsa_total + (sal - 350000) * 0.05;
    END IF;

    RETURN irsa_total;
END;
$$ LANGUAGE plpgsql;
