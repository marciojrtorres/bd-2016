CREATE TYPE modalidades AS ENUM('Integrado','Concomitante','Subsequente');

CREATE TABLE cursos (
	id_curso 	SERIAL		NOT NULL PRIMARY KEY,
	nome		VARCHAR(30)	NOT NULL,
	coordenador	VARCHAR(40)	NOT	NULL,
	modalidade	modalidades	
);

CREATE TABLE alunos (
	id_aluno 	SERIAL		NOT NULL PRIMARY KEY, 
	matricula	VARCHAR(8)	NOT NULL,
	nome		VARCHAR(20)	NOT NULL,
	sobrenome	VARCHAR(30)		NULL,
	id_curso	INTEGER		NOT NULL
			REFERENCES	cursos(id_curso),
	data_inicio 	DATE
);

// C-R-U-D

// CREATE

INSERT INTO cursos(
	nome, coordenador, modalidade
) VALUES (
	'Informática Para Internet', 'Raquel Barbosa', 'Integrado'
);

INSERT INTO cursos(
	nome, coordenador, modalidade
) VALUES (
	'Enfermagem', 'Eliana Cardia de Pinho', 'Subsequente'	
);

INSERT INTO cursos(
	nome, coordenador, modalidade
) VALUES (
	'Eletrotécnica', 'Rodrigo Tonin', 'Integrado'
);

INSERT INTO alunos(
	matricula, nome, sobrenome, id_curso, data_inicio
) VALUES (
	'11030230', 'Ana Carolina', 'Barbosa', 1, '21/03/2014'
);


INSERT INTO alunos(
	matricula, nome, sobrenome, id_curso, data_inicio
) VALUES (	
	'11090430', 'Catarina', 'Souza', 4, '15/03/2016'
);

INSERT INTO alunos(
	matricula, nome, sobrenome, id_curso, data_inicio
) VALUES (
	'11050210', 'Joaquim', 'Miranda', 5, '02/03/2015'	
);	

INSERT INTO alunos(
	matricula, nome, sobrenome, id_curso, data_inicio
) VALUES (
	'11030221', 'Pedro', 'Campos', 1, '21/03/2014'
);

INSERT INTO alunos(
	matricula, nome, sobrenome, id_curso, data_inicio
) VALUES (
	'11090409', 'Mario', 'Silva', 4, '02/03/2015'
);

// FIM CREATE

// UPDATES

UPDATE alunos
SET nome = 'Maria'
WHERE matricula = '11090409';

UPDATE cursos
SET modalidade = 'Subsequente'
WHERE nome = 'Eletrotécnica';

UPDATE cursos
SET modalidade = 'Integrado'
WHERE id_curso = 5;

// FIM UPDATES

// DELETES

INSERT INTO cursos(
	nome, coordenador, modalidade
) VALUES (
	'Geoprocessamento', 'Franciane de Lima Coimbra', 'Integrado'
);

DELETE FROM cursos
WHERE nome = 'Geoprocessamento';

DELETE FROM alunos
WHERE matricula = '11090409';

// FIM DELETES

// READ


SELECT c.nome AS NomeCurso, a.nome AS NomeAluno
FROM cursos as c JOIN alunos as a 
ON	c.id_curso = a.id_curso;

SELECT nome, matricula
FROM alunos
WHERE id_curso = 1;

SELECT nome
FROM alunos
WHERE EXTRACT (YEAR FROM data_inicio) = 2015;

// FIM READ
