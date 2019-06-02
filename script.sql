CREATE TABLE IF NOT EXISTS Etudiants(
    id INT(5) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName  VARCHAR(100) NOT NULL,
    filiere  VARCHAR(100) NOT NULL,
    createdAt DATE,
    updated_at DATE,
    PRIMARY KEY(id)
 );
 CREATE TABLE IF NOT EXISTS Enseignants(
    id INT(5) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName  VARCHAR(100) NOT NULL,
    departement VARCHAR(100) NOT NULL,
    createdAt DATE,
    updated_at DATE,
    PRIMARY KEY(id)
 );
  
 CREATE TABLE IF NOT EXISTS enseignant_etudiant(
    enseignant_id INT(5),
    etudiant_id  INT(5),
    FOREIGN KEY (enseignant_id) REFERENCES Enseignants(id), 
    FOREIGN KEY (etudiant_id) REFERENCES Etudiants(id)
 );
