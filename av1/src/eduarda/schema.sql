CREATE TYPE genero AS ENUM('feminino', 'masculino');

CREATE TABLE alunos(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(30),
	matricula INTEGER,
	t_genero genero NOT NULL
);

CREATE TABLE alunos_por_curso(
	id_aluno INTEGER REFERENCES alunos(id) ON DELETE SET NULL,
	id_curso INTEGER REFERENCES cursos(id) ON DELETE SET NULL,
	data_matricula Date NOT NULL
);


CREATE TABLE professores(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(20)
);

CREATE TABLE cursos(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(20)
);


CREATE TABLE professores_por_curso(
	id_professor  INTEGER REFERENCES professores(id),
	id_curso INTEGER REFERENCES cursos(id)
);

ALTER TABLE alunos ADD data_nasc Date NULL;

ALTER TABLE cursos ADD duracao INTEGER NOT NULL;

INSERT INTO alunos(nome, matricula, t_genero, data_nasc) VALUES ('Joaquim', 1193849, 'masculino', '1996-12-13');

INSERT INTO alunos(nome, matricula, t_genero, data_nasc) VALUES ('Joana', 11922389, 'feminino', '1998-05-08');

SELECT * FROM alunos;

DELETE FROM alunos WHERE id = 2;

SELECT nome FROM cursos WHERE duracao = 3;

INSERT INTO cursos(nome, duracao) VALUES ('Informática', 4);

INSERT INTO cursos(nome, duracao) VALUES ('Geoprocessamento', 2);

UPDATE cursos SET nome = 'Eletrotécnica' WHERE nome = 'Geoprocessamento';

INSERT INTO alunos_por_curso(id_aluno, id_curso, data_matricula) VALUES (1, 2, '2014-03-01');

