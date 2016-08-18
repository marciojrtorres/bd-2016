CREATE DATABASE agenda;
-- palavras reservadas do banco sempre
-- maiúsculo
-- palavras do usuário em snake_case
-- minúsculo
-- tabelas sempre no plural
CREATE TABLE contatos (
  id        SERIAL      NOT NULL PRIMARY KEY,
  nome      VARCHAR(20) NOT NULL,
  sobrenome VARCHAR(30)     NULL
);

CREATE TABLE telefones (
  id_contato INTEGER NOT NULL 
             REFERENCES contatos(id)
             ON DELETE CASCADE,
  ddd    VARCHAR(2)      NULL,
  numero VARCHAR(9)  NOT NULL
);
-- 
INSERT INTO contatos VALUES (
  DEFAULT, 'Ogro', 'Betito'
); -- 1

INSERT INTO contatos (
  nome, sobrenome
) VALUES (
  'Igor', 'Bira'
); -- 2
-- select all
SELECT * FROM contatos;
-- select "econômico": só a coluna que precisa
-- e só uma página de registros
SELECT sobrenome 
FROM contatos LIMIT 1 OFFSET 1;
-- ver os primeiros 5 contatos
SELECT sobrenome
FROM contatos LIMIT 5;
-- próximos 5
SELECT sobrenome
FROM contatos LIMIT 5 OFFSET 5;
--
UPDATE contatos
SET nome = 'Rafael'
WHERE id = 1;
--
INSERT INTO contatos (nome) 
VALUES ('temp');
--
DELETE FROM contatos
WHERE nome = 'temp';
-- 
INSERT INTO telefones VALUES (
  18, '53', '88992211'
), (
  18, '53', '32334455'
);
--
INSERT INTO telefones
(id_contato, numero) VALUES (
  19, '32334455'
), (
  19, '99234455'
);
--
SELECT nome, numero 
FROM contatos c JOIN telefones t
ON c.id = t.id_contato;
--
SELECT nome, numero 
FROM contatos NATURAL JOIN telefones;












