CREATE DATABASE carrinho;

CREATE TABLE clientes (
	id	SERIAL      NOT NULL PRIMARY KEY,
	nome	VARCHAR(20) NOT NULL,
	sobrenome VARCHAR(20),
	nascimento DATE
		);

CREATE TYPE enumeracao AS ENUM ('Raro' , 'Comum');

CREATE TABLE produtos (
	id_produto SERIAL NOT NULL PRIMARY KEY,
	id	INTEGER ,
	nome	VARCHAR(20) NOT NULL,
	preco	INTEGER NOT NULL,
	raridade enumeracao,
	FOREIGN KEY (id) REFERENCES clientes(id) ON DELETE SET NULL
		);

INSERT INTO clientes (nome , sobrenome , nascimento) VALUES ('Erick' , 'Martins' , '10/09/1999');

INSERT INTO produtos (id , nome , preco, raridade) VALUES ( 1 , 'Impala 69' ,20000 , 'Raro');

SELECT * FROM clientes WHERE id = 1;

SELECT * FROM produtos WHERE id_produto = 1;

UPDATE clientes SET nome = 'Êrick' WHERE id = 1;

UPDATE produtos SET preco = 10000 WHERE id_produto = 1;

DELETE FROM clientes WHERE id = 1;

DELETE FROM produtos WHERE id_produto = 1;
