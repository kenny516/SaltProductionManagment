CREATE OR REPLACE VIEW v_avances_impayes AS
    SELECT a.*, a.montant - COALESCE(ar.total_remboursement, 0) AS reste_payer
    FROM avances a
    LEFT JOIN (
        SELECT id_avance, COALESCE(SUM(montant), 0) AS total_remboursement
        FROM AvanceRemboursement
        GROUP BY id_avance
    ) ar ON a.id = ar.id_avance
    WHERE COALESCE(ar.total_remboursement, 0) < a.montant;