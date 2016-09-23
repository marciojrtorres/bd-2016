CREATE TABLE clientes (
	id 		SERIAL 	     NOT NULL PRIMARY KEY,
	nome    VARCHAR(20) NOT NULL,
	sobrenome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NULL,
	data_nasc DATE NOT NULL,
	pag_emdia SMALLINT
);

CREATE TABLE filmes(
	id_filme SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	genero VARCHAR(30) NOT NULL,
	quantidade INTEGER,
	id_cliente INTEGER REFERENCES clientes (id)
);

INSERT INTO clientes VALUES(
	DEFAULT,'José','Silva','jose.silva@gmail.com','20-10-1990',1);

INSERT INTO filmes VALUES(
	DEFAULT,'Harry Potter','Fantasia','20',1);
	
SELECT * FROM clientes
SELECT * FROM filmes
SELECT sobrenome FROM clientes LIMIT 1 OFFSET 1;

UPDATE clientes
SET nome = 'Rafael'
WHERE id = 1;

UPDATE filmes
SET nome = 'Percy Jackson'
WHERE id_filme = 1;

INSERT INTO clientes(nome)   
VALUES('temporario');

DELETE FROM contatos
WHERE nome = 'temporario';

SELECT c.nome,f.nome
FROM clientes AS c INNER JOIN filmes AS f ON
			(c.id = f.id_cliente);