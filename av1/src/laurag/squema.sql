-----------------------------------
--------- CREATE TABLE ------------
CREATE TYPE sexo AS ENUM ('M', 'F');

CREATE TABLE alunos (
	id         	SERIAL        PRIMARY KEY,
	nome       	VARCHAR(20)   NULL,
	sobrenome  	VARCHAR(20)   NULL,
	sexo		sexo
);

CREATE TABLE disciplinas (
	id	    SERIAL 		 PRIMARY KEY,
	nome    VARCHAR(15)  NULL,
	c_hora  INT			 NULL
);

CREATE TABLE matriculas (
	id_alunos		INTEGER NOT NULL
			   			REFERENCES alunos(id),
	id_disciplinas 	INTEGER NOT NULL
						REFERENCES disciplinas(id),
	inscricao		DATE	    NULL
);


---------------------------------
--------- INSERT ALUNOS ---------
INSERT INTO alunos VALUES (
	DEFAULT, 'Laura', 'Gomes', 'F'
);
INSERT INTO alunos VALUES (
	DEFAULT, 'Thaylles', 'Rosa', 'M'
);
INSERT INTO alunos VALUES (
	DEFAULT, 'Laura', 'Dalmolin', 'F'
);
INSERT INTO alunos VALUES (
	DEFAULT, 'Erick', 'Martins', 'M'
);
INSERT INTO alunos VALUES (
	DEFAULT, 'Beah', 'Costa', 'F'
);
INSERT INTO alunos VALUES (
	DEFAULT, 'Raimundo', 'Lima', 'M'
);


--------------------------------
------ INSERT DISCIPLINAS ------
INSERT INTO disciplinas VALUES (
	DEFAULT, 'Português', '40'
);
INSERT INTO disciplinas VALUES (
	DEFAULT, 'Matemática', '40'
);
INSERT INTO disciplinas VALUES (
	DEFAULT, 'Biologia', '30'
);
INSERT INTO disciplinas VALUES (
	DEFAULT, 'Filosofia', '10'
);
INSERT INTO disciplinas VALUES (
	DEFAULT, 'Sociologia', '10'
);
INSERT INTO disciplinas VALUES (
	DEFAULT, 'Espanhol', '20'
);


-------------------------------
------ INSERT MATRÍCULAS ------
INSERT INTO matriculas VALUES (
	1, 6, '2016-02-22'
);
INSERT INTO matriculas VALUES (
	2, 2, '2016-03-07'
);
INSERT INTO matriculas VALUES (
	3, 3, '2016-03-07'
);
INSERT INTO matriculas VALUES (
	4, 3, '2016-03-07'
);
INSERT INTO matriculas VALUES (
	5, 5, '2015-11-21'
);


------------
-- SELECT --
SELECT * 
FROM alunos;

SELECT a.nome, d.nome
FROM matriculas m JOIN alunos a 
ON (a.id = m.id_alunos)
				  JOIN disciplinas d 
ON (d.id = m.id_disciplinas);
							
SELECT a.nome, a.sobrenome
FROM matriculas m JOIN alunos a 
ON (a.id = m.id_alunos)
WHERE inscricao = '2016-03-07';

SELECT sobrenome
FROM alunos LIMIT 1 OFFSET 1;


-----------------
----- UPDATE ----
UPDATE matriculas
SET inscricao = '2016-05-23'
WHERE id_alunos = 2;

UPDATE alunos
SET nome = 'Beatriz'
WHERE id = 5;


------------------
----- DELETE -----
DELETE FROM alunos
WHERE nome = 'Raimundo';