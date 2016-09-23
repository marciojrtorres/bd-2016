CREATE DATABASE cinema;

CREATE TABLE filme (
  id               SERIAL      NOT NULL PRIMARY KEY,
  nome             VARCHAR(20)     NULL,
  duracao_min      INTEGER NULL,  
  ano              INTEGER NULL,
  genero           ENUM ('drama','comedia','acao','terror'),
  data_lancamento  DATE NULL, 
);

CREATE TABLE ator (
  id_filme   INTEGER NOT NULL
		REFERENCES filmes(id)
  nome	     VARCHAR(30)    NULL,
  sobrenome  VARCHAR(20)    NULL,
  idade      INTEGER        NULL,
);