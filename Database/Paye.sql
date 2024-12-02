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