-- Le taloha

create DATABASE gestion_analytique;

use gestion_analytique;

-- Table RubriqueCateg
create table RubriqueCateg (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(70)
)engine=innodb;

-- Table NatureCentre
create table NatureCentre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
)engine=innodb;

-- Table Centre
create table Centre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    idNature INT,
    FOREIGN KEY (idNature) REFERENCES NatureCentre(id)
)engine=innodb;

-- Table UniteOeuvre
create table UniteOeuvre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(20) NOT NULL
)engine=innodb;

-- Table Produit
create table Produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    idUniteOeuvre INT,
    FOREIGN KEY (idUniteOeuvre) REFERENCES UniteOeuvre(id)
)engine=innodb;

-- Table Production
create table Production (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idProduit INT,
    date DATE NOT NULL,
    quantite DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idProduit) REFERENCES Produit(id)
)engine=innodb;

-- Table Rubrique
create table Rubrique (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    estVariable BOOLEAN NOT NULL,
    idUniteOeuvre INT,
    idCateg INT,
    dateInsertion DATE NOT NULL,
    FOREIGN KEY (idUniteOeuvre) REFERENCES UniteOeuvre(id),
    FOREIGN KEY (idCateg) REFERENCES RubriqueCateg(id)
)engine=innodb;

-- Table PartsParCentre
create table PartsParCentre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idRubrique INT,
    idCentre INT,
    valeur DECIMAL(10,2) NOT NULL,
    dateInsertion DATE NOT NULL,
    FOREIGN KEY (idRubrique) REFERENCES Rubrique(id),
    FOREIGN KEY (idCentre) REFERENCES Centre(id)
)engine=innodb;

-- Table Depenses
create table Depenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dateDepense DATE NOT NULL,
    prixU DECIMAL(10,2),
    quantite DOUBLE PRECISION,
    idRubrique INT,
    FOREIGN KEY (idRubrique) REFERENCES Rubrique(id)
)engine=innodb;

-- Table AssoDepensesParts
create table AssoDepensesParts (
    idDepense INT,
    idPart INT,
    PRIMARY KEY (idDepense, idPart),
    FOREIGN KEY (idDepense) REFERENCES Depenses(id),
    FOREIGN KEY (idPart) REFERENCES PartsParCentre(id)
)engine=innodb;


-- Le vaovao
-- Table CommandeInterne
create table CommandeInterne (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idDepartement INT, 
    dateCommande DATE,
    status INT, -- Exemples de mappage : 1 = "En attente", 2 = "Validée", 3 = "Refusée"
    quantite DOUBLE PRECISION,
    produitCommande VARCHAR(100)
)engine=innodb;

-- Table BonDeCommande
create table BonDeCommande (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idFournisseur INT, 
    dateCommande DATE,
    montant DECIMAL(10,2),
    status INT, -- Exemples de mappage : 1 = "En attente", 2 = "Reçu", 3 = "Annulé"
    produitCommande VARCHAR(100)
)engine=innodb;

-- Table BonDeReception
create table BonDeReception (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idCommande INT, 
    dateReception DATE,
    quantiteReceptionnee DOUBLE PRECISION,
    status INT, -- Exemples de mappage : 1 = "Reçu", 2 = "Partiellement reçu", 3 = "Rejeté"
    FOREIGN KEY (idCommande) REFERENCES BonDeCommande(id)
)engine=innodb;

-- Table Proforma
create table Proforma (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idFournisseur INT, 
    produitPropose VARCHAR(100),
    montantPropose DECIMAL(10,2),
    status INT -- Exemples de mappage : 1 = "Envoyé", 2 = "Approuvé", 3 = "Rejeté"
)engine=innodb;

-- Table Paiement
create table Paiement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idCommande INT,
    montant DECIMAL(10,2),
    datePaiement DATE,
    status INT, -- Exemples de mappage : 1 = "En attente", 2 = "Réalisé", 3 = "Échoué"
    FOREIGN KEY (idCommande) REFERENCES BonDeCommande(id)
)engine=innodb;