--ENUM
CREATE TYPE tipo AS ENUM (
  'Solo', 'Grupo'
  );
--TABELA DE EMPRESARIOS
CREATE TABLE empresarios (
id		SERIAL		NOT NULL PRIMARY KEY,
nome	VARCHAR		NOT NULL,
contato	VARCHAR 	NOT NULL 
);
--TABELA DE ARTISTAS
CREATE TABLE artistas(
id 		SERIAL 		NOT NULL PRIMARY KEY,
id_empresario INTEGER  
             REFERENCES empresarios(id),
nome		VARCHAR		NOT NULL,
data_criacao	DATE	NOT NULL,
tip			TIPO	NOT NULL
);
--INSERTS dos empresarios
INSERT INTO empresarios(nome,contato)
VALUES('empresario','empresario@gmail.com');

INSERT INTO empresarios(nome,contato)
VALUES('empresario2','empresario2@gmail.com');


--insert dos artistas
INSERT INTO artistas(id_empresario,nome,data_criacao,tip)
VALUES(1,'macacos articos','01-03-2008','Grupo');
INSERT INTO artistas(id_empresario,nome,data_criacao,tip)
VALUES(1,'solo guy','01-03-2000','Solo');
INSERT INTO artistas(id_empresario,nome,data_criacao,tip)
VALUES(2,'macacos imitadores','01-03-2008','Grupo');

--SELECT


