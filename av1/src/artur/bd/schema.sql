CREATE TABLE IF NOT EXISTS professores (
	id			SERIAL			NOT NULL	PRIMARY KEY,
	nome		VARCHAR(200)	NOT NULL,
	nascimento	DATE,
	sexo		VARCHAR(20),
	endereco	VARCHAR(1000),
	telefone	VARCHAR(20),
	email		VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS alunos (
	id			SERIAL			NOT NULL	PRIMARY KEY,
	nome		VARCHAR(200)	NOT NULL,
	nascimento	DATE,
	sexo		VARCHAR(20),
	endereco	VARCHAR(1000),
	telefone	VARCHAR(20),
	email		VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS disciplinas (
	id			SERIAL			NOT NULL	PRIMARY KEY,
	nome		VARCHAR(200)	NOT NULL,
	ramo		VARCHAR(200),
	id_professor	INTEGER		NOT NULL	REFERENCES professores(id)
);

CREATE TABLE IF NOT EXISTS alunos_disciplinas (
	id		SERIAL		NOT NULL	PRIMARY KEY,
	id_aluno	INTEGER		NOT NULL,
	id_disciplina	INTEGER		NOT NULL
);