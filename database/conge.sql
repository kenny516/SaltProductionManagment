CREATE TABLE TypeConge
(
    id        SERIAL,
    nom       VARCHAR(20)           NOT NULL,
    est_paye  BOOLEAN DEFAULT false NOT NULL,
    cumulable BOOLEAN DEFAULT false NOT NULL,
    duree_max NUMERIC(4, 2), -- en annee
    PRIMARY KEY (id)
);
--
CREATE TABLE SoldeConge
(
    id            SERIAL,
    id_employe    INTEGER NOT NULL,
    id_type_conge INTEGER NOT NULL,
    mois          INTEGER NOT NULL,
    annee         INTEGER NOT NULL,
    annee_fin     INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_employe) REFERENCES Employes (id),
    FOREIGN KEY (id_type_conge) REFERENCES TypeConge (id)
);
--
CREATE TABLE Conge
(
    id            SERIAL,
    id_employe    INTEGER NOT NULL,
    id_type_conge INTEGER NOT NULL,
    date_debut    DATE    NOT NULL,
    date_fin      DATE    NOT NULL,
    duree         NUMERIC(5, 2) GENERATED ALWAYS AS (date_fin - date_debut + 1) STORED,
    status        VARCHAR(20) DEFAULT 'En attente',
    PRIMARY KEY (id),
    FOREIGN KEY (id_employe) REFERENCES Employes (id),
    FOREIGN KEY (id_type_conge) REFERENCES TypeConge (id)
);
--
