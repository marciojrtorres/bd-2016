CREATE DATABASE controlador;
CREATE TYPE genero AS ENUM('F','M');

CREATE TABLE alunos (
	id 				SERIAL		NOT NULL PRIMARY KEY,
	nome			VARCHAR(20) NOT NULL,
	genero_user	genero			NOT NULL,
	aniversario 	DATE		NOT NULL
);

CREATE TABLE series (
	id_aluno 	INTEGER NOT NULL
				REFERENCES alunos(id)
				ON DELETE SET NULL,
	nome		VARCHAR(30) NOT NULL,
	temporada 	INTEGER NOT NULL,
	episodio	INTEGER NOT NULL
);

INSERT INTO alunos (
  nome, genero_user, aniversario
  ) VALUES (
  'Jennifer', 'F', '1999-04-18'
);

INSERT INTO alunos (
  nome, genero_user, aniversario
  ) VALUES (
  'Ana','F','1999-07-02'
);

SELECT * FROM alunos;

SELECT genero_user 
FROM alunos LIMIT 1 OFFSET 1;

UPDATE alunos
SET nome = 'Lyssa'
WHERE id = 1;

UPDATE alunos
SET nome = 'Jennifer'
where nome='Lyssa';

DELETE FROM alunos
WHERE id = 1;

INSERT INTO alunos (
  nome, genero_user, aniversario
  ) VALUES (
  'Jennifer', 'F', '1999-04-18'
);

INSERT INTO series VALUES (
  2, 'Teen Wolf', 1, 10
), (
  2, 'Game of Thrones', 3, 7
);

INSERT INTO series VALUES (
  3, 'Sherlock', 1, 2
), (
  3, 'Friends', 2, 1
);

SELECT alu.nome, ser.nome, ser.temporada, ser.episodio
FROM alunos AS alu JOIN series AS ser
ON alu.id = ser.id_aluno;
