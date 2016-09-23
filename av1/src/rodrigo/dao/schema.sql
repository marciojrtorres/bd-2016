CREATE DATABASE SpotFavela 

CREATE TABLE gravadoras(
id SERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(20) NOT NULL,
qtde_bandas INTEGER NULL,
data_fundacao DATE NULL
);

CREATE TYPE nac AS ENUM ('BR', 'EST');

CREATE TABLE bandas (
id SERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(20) NOT NULL,
genero VARCHAR(15) NULL,
qtde_albums INTEGER NULL,
nacionalidade nac  NULL,
id_gravadoras INTEGER NOT NULL REFERENCES gravadoras(id)
);

INSERT INTO gravadoras VALUES( DEFAULT, 'RCA Records',23,'1/1/1929');
INSERT INTO gravadoras VALUES (DEFAULT, 'Domino Records',33,'1/1/1993');
INSERT INTO gravadoras VALUES (DEFAULT, 'EMI Records',41,'1/3/1931'); 
INSERT INTO bandas VALUES (DEFAULT, 'The Strokes', 'Indie Rock',5,'EST', 1);
INSERT INTO bandas VALUES (DEFAULT, 'Arctic Monkeys', 'Indie Rock',5,'EST', 2);
INSERT INTO Bandas VALUES (DEFAULT, 'The Rolling Stones', 'Rock',7,'EST', 3);
UPDATE bandas
SET nome = 'Radiohead' 
WHERE id = 3;
INSERT INTO bandas VALUES (DEFAULT,'NONAME_TEST', 'whatever',7,'BR', 3);
DELETE FROM bandas WHERE nome = 'NONAME_TEST';
SELECT nome FROM gravadoras;
SELECT nome, genero FROM bandas;
SELECT * from gravadoras;