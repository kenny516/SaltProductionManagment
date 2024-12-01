-- Employe A : id 1, salary 3000
-- Employe A : id 3, salary 4500

CREATE TABLE avances (
    id SERIAL,
    id_employe INT                      NOT NULL,
    montant DECIMAL(15,2)               NOT NULL,
    pourcentage_debitable DECIMAL(4,2)  NOT NULL,
    date_avance DATE                    NOT NULL,
    raison VARCHAR(255),

    PRIMARY KEY(id),
    FOREIGN KEY(id_employe) REFERENCES Employes(id),
    CHECK (montant > 0),
    CHECK (pourcentage_debitable > 0),
    CHECK (date_avance <= CURRENT_DATE)
);

CREATE TABLE AvanceRemboursement (
    id SERIAL,
    id_avance INT NOT NULL,
    montant DECIMAL(15,2) NOT NULL, -- should be pourcentage_debitable% of the employe's salary
    date_remboursement DATE NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id_avance) REFERENCES Avances(id),
    CHECK (montant > 0),
    CHECK (date_remboursement <= CURRENT_DATE)
);

-- Insert an advance for Employee A (id=1), not paid off yet
INSERT INTO avances (id_employe, montant, pourcentage_debitable, date_avance, raison)
VALUES 
(1, 2000.00, 50.00, '2024-01-01', 'Advance for Employee A');

-- Insert an advance for Employee B (id=3), fully paid off
INSERT INTO avances (id_employe, montant, pourcentage_debitable, date_avance, raison)
VALUES 
(3, 4000.00, 50.00, '2024-01-01', 'Advance for Employee B');

-- Insert payments for Employee B to fully pay off the advance
-- Employee B has salary 4500 and advance of 4000, so they will pay off the full amount

INSERT INTO AvanceRemboursement (id_avance, montant, date_remboursement)
VALUES 
(5, 2000.00, '2024-12-01'),  -- First payment of 2000
(5, 2000.00, '2024-12-01');  -- Second payment of 2000 (full repayment)

INSERT INTO AvanceRemboursement (id_avance, montant, date_remboursement)
VALUES 
(1, 100.00, '2024-11-15'),  -- First payment of 2000
(1, 150.00, '2024-02-01');  -- Second payment of 2000 (full repayment)
