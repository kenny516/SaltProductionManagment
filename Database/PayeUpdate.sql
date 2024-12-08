CREATE TABLE Paye(
                     id SERIAL,
                     id_employe INTEGER NOT NULL,
                     mois INTEGER NOT NULL,
                     annee INTEGER NOT NULL,
                     heure_normale NUMERIC(5, 2) NOT NULL,
                     heure_sup NUMERIC(20, 2),
                     avance NUMERIC(20, 2),
                     salaire_base NUMERIC(20, 2) NOT NULL,
                     total NUMERIC(20, 2) NOT NULL,
                     PRIMARY KEY(id),
                     FOREIGN KEY(id_employe) REFERENCES Employes(id)
);
CREATE TABLE IF NOT EXISTS paye_details(
    id SERIAL,
	id_employe INTEGER NOT NULL,
	mois INTEGER NOT NULL,
	annee INTEGER NOT NULL,
	heure_normale NUMERIC(5, 2) NOT NULL DEFAULT 160.0,
	heure_sup NUMERIC(20, 2) DEFAULT 0,
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

CREATE OR REPLACE FUNCTION get_val_irsa(sal NUMERIC(10,2), pourcentage NUMERIC(4,2))
RETURNS NUMERIC(10, 2) AS $$
DECLARE
    irsa_total NUMERIC(10, 2) := 0;
BEGIN

    IF pourcentage < 20 THEN
        irsa_total := 100000 * pourcentage;
    END IF;
    IF pourentage >= 20 THEN
        irsa_total := sal-600000 * pourcentage;
    END IF;

    RETURN irsa_total;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculer_irsa_tranches(sal NUMERIC(10,2))
RETURNS TABLE(
    tranche_5 NUMERIC(10,2),
    tranche_10 NUMERIC(10,2),
    tranche_15 NUMERIC(10,2),
    tranche_20 NUMERIC(10,2)
) AS $$
BEGIN
    -- Initialiser les tranches
    tranche_5 := 0;
    tranche_10 := 0;
    tranche_15 := 0;
    tranche_20 := 0;

    -- Calcul tranche 5% (350,001 à 400,000)
    IF sal > 350000 THEN
        tranche_5 := LEAST(sal, 400000) - 350000;
        tranche_5 := tranche_5 * 0.05;
    END IF;

    -- Calcul tranche 10% (400,001 à 500,000)
    IF sal > 400000 THEN
        tranche_10 := LEAST(sal, 500000) - 400000;
        tranche_10 := tranche_10 * 0.10;
    END IF;

    -- Calcul tranche 15% (500,001 à 600,000)
    IF sal > 500000 THEN
        tranche_15 := LEAST(sal, 600000) - 500000;
        tranche_15 := tranche_15 * 0.15;
    END IF;

    -- Calcul tranche 20% (au-delà de 600,000)
    IF sal > 600000 THEN
        tranche_20 := (sal - 600000) * 0.20;
    END IF;

    -- Retourner les résultats
    RETURN QUERY SELECT tranche_5, tranche_10, tranche_15, tranche_20;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_total_heures_sup(
    emp INT,
    month_ INT,
    year_ INT,
    maj NUMERIC
)
RETURNS NUMERIC AS $$
BEGIN
    RETURN COALESCE((
        SELECT SUM(h.montant)
        FROM HeuresSup h
        WHERE h.id_employe = emp
          AND EXTRACT(MONTH FROM h.date_debut) = month_
          AND EXTRACT(YEAR FROM h.date_debut) = year_
          AND h.majoration = maj
    ), 0);
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE VIEW V_irsa_employe AS 
SELECT 
    emp.id,
    ce.salaire,
    (calculer_irsa_tranches(ce.salaire)).tranche_5 AS irsa_5,
    (calculer_irsa_tranches(ce.salaire)).tranche_10 AS irsa_10,
    (calculer_irsa_tranches(ce.salaire)).tranche_15 AS irsa_15,
    (calculer_irsa_tranches(ce.salaire)).tranche_20 AS irsa_20
FROM Employes emp
JOIN ContratEmploye ce 
    ON ce.id_employe = emp.id
WHERE ce.date_debut = (
    SELECT MAX(date_debut) 
    FROM ContratEmploye 
    WHERE id_employe = emp.id
);



CREATE OR REPLACE VIEW V_Paye_Employe_Details AS
SELECT
    emp.id,
    emp.nom,
    emp.prenom,
    emp.email,
    emp.telephone,
    emp.date_embauche,
    ce.date_debut AS contrat_date_debut,
    ce.date_fin AS contrat_date_fin,
    ce.salaire AS salaire,
    ce.taux_horaire AS taux_horaire,
    postes.titre AS titre,
    postes.description AS poste_description,
    postes.departement AS poste_departement,
    pd.mois AS mois,
    pd.annee AS annee,
    pd.heure_normale AS heure_normale,
    pd.heure_sup AS heure_sup,
    (SELECT get_total_heures_sup(emp.id, pd.mois, pd.annee, 30)) as heure_sup30,
    (SELECT get_total_heures_sup(emp.id, pd.mois, pd.annee, 40)) as heure_sup40,
    (SELECT get_total_heures_sup(emp.id, pd.mois, pd.annee, 50)) as heure_sup50,
    (SELECT get_total_heures_sup(emp.id, pd.mois, pd.annee, 100)) as heure_sup100,
    pd.montant_heure_sup AS montant_heure_sup,
    pd.salaire_base AS salaire_base,
    pd.avance AS avance,
    pd.nb_heure_abs AS nb_heure_abs,
    pd.droit_conge AS droit_conge,
    pd.droit_preavis AS droit_preavis,
    pd.indemnite AS indemnite,
    pd.prime_diverse AS prime_diverse,
    pd.irsa AS irsa,
    pd.cnaps AS cnaps,
    pd.sanitaire AS sanitaire,
    pd.total AS total,
    vire.irsa_5 AS irsa_5,
    vire.irsa_10 AS irsa_10,
    vire.irsa_15 AS irsa_15,
    vire.irsa_20 AS irsa_20
FROM Employes emp
JOIN ContratEmploye ce 
    ON emp.id_contrat_actuel = ce.id
JOIN Postes 
    ON ce.id_poste = postes.id
JOIN paye_details pd 
    ON pd.id_employe = emp.id
LEFT JOIN V_irsa_employe vire 
    ON vire.id = emp.id;




