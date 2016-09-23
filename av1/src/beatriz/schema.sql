CREATE TABLE escritores(
	id_escritor SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL, 
	sobrenome VARCHAR(50) NOT NULL,
	cep INTEGER NOT NULL
);

CREATE TYPE genero AS ENUM ('romance', 'ficcao_cientifica', 'terror');

CREATE TABLE livros(
	id_escritor INTEGER REFERENCES escritores(id_escritor),
		ON DELETE SET NULL;
	titulo VARCHAR(50) NULL,
	publicacao DATE NOT NULL,
	livro_genero genero
	
);

INSERT INTO escritores VALUES(
	DEFAULT, 'Stefanie', 'Meyer', 01977856040
);

INSERT INTO escritores VALUES(
	DEFAULT, 'J.K', 'Rowling', 01977856041
);

SELECT * FROM escritores;

UPDATE escritores
SET nome = 'Stephanie'
WHERE id_escritor = 1;

SELECT nome
FROM escritores;

INSERT INTO livros VALUES (
	(SELECT id_escritor FROM escritores WHERE cpf = 01977856040), 'Crepusculo', '19-08-2016', 'romance'
	);

INSERT INTO livros VALUES (
	(SELECT id_escritor FROM escritores WHERE cpf = 01977856041), 'Harry Potter', '20-08-2016', 'ficcao cientifica'
	);
	

SELECT nome, titulo
FROM escritores e INNER JOIN livros l 
ON e.id_escritor = l.id_escritor

SELECT nome, livros
FROM escritores NATURAL JOIN livros;

DELETE FROM livros
WHERE id_escritor = 2;

DELETE FROM escritores
WHERE id_escritor = 1;

SELECT titulo
FROM livros