-- Criacao das tabelas
CREATE TABLE usuarios (
	id_usuario SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(20),
	sobrenome VARCHAR(30),
	data_de_nascimento DATE,
	genero SMALLINT	
);

CREATE TABLE usuarios_jogos (
	id_usuariof INTEGER NOT NULL REFERENCES usuarios (id_usuario),
	id_jogof INTEGER NOT NULL REFERENCES jogos (id_jogo)	
);

CREATE TABLE jogos (
	id_jogo SERIAL NOT NULL PRIMARY KEY,
	nome_jogo VARCHAR(50)	
);

-- Insercoes na tabela 'usuarios'
INSERT INTO usuarios VALUES (DEFAULT, 'Laura', 'Dalmolin', '11-09-1999', 1);
INSERT INTO usuarios VALUES (DEFAULT, 'Laura', 'Gomes', '24-05-2000', 1);
INSERT INTO usuarios VALUES (DEFAULT, 'Felipe', 'Santos', '02-06-1978', 0);
INSERT INTO usuarios VALUES (DEFAULT, 'Steve', 'Jobs', '24-02-1955', 0);

-- Insercoes na tabela 'jogos'
INSERT INTO jogos VALUES (DEFAULT, 'The Last Of Us'); -- #1
INSERT INTO jogos VALUES (DEFAULT, 'Life Is Strange'); -- #2
INSERT INTO jogos VALUES (DEFAULT, 'Little Big Planet 3'); -- #3
INSERT INTO jogos VALUES (DEFAULT, 'Bloodborne'); -- #4
INSERT INTO jogos VALUES (DEFAULT, 'Super Mario World'); -- #5
INSERT INTO jogos VALUES (DEFAULT, 'League of Legends'); -- #6

-- Insercoes na tabela de ligacao
INSERT INTO usuarios_jogos VALUES (1, 1);
INSERT INTO usuarios_jogos VALUES (1, 2);
INSERT INTO usuarios_jogos VALUES (1, 4);
INSERT INTO usuarios_jogos VALUES (2, 3);
INSERT INTO usuarios_jogos VALUES (2, 6);
INSERT INTO usuarios_jogos VALUES (3, 6);
INSERT INTO usuarios_jogos VALUES (4, 5);

-- Updates 
UPDATE usuarios SET nome = 'Bill' WHERE id_usuario = 4;
UPDATE jogos SET nome_jogo = 'LoL' WHERE id_jogo = 6;

-- Deletes
DELETE FROM usuarios WHERE id_usuario = 3;
DELETE FROM jogos WHERE id_jogo = 3;

-- Podemos deletar um dado da tabela 'usuarios' ou 'jogos' apenas quando nao houver nenhum correspondente do mesmo em 'usuarios_jogos'
-- Assim, devemos sempre excluir os dados da tabela 'usuarios_jogos' para depois excluirmos de fato da tabela que precisamos.

-- Drops
DROP TABLE usuarios;
DROP TABLE jogos;
DROP TABLE usuarios_jogos;

-- Selects
-- Seleciona todos os dados de todas as tabelas
SELECT u.nome, u.sobrenome, u.data_de_nascimento, u.sexo, u.id_usuario, j.nome_jogo, j.id_jogo
FROM usuarios AS u INNER JOIN usuarios_jogos AS uj ON (u.id_usuario = uj.id_usuariof) 
				   INNER JOIN jogos AS j ON (uj.id_jogof = j.id_jogo);
				   
-- Seleciona os dados da tabela 'usuarios'
SELECT id_usuario, nome, sobrenome, data_de_nascimento, sexo
FROM usuarios;
-- OU
SELECT * FROM usuarios WHERE id = 1;

-- Seleciona os dados da tabela 'jogos'
SELECT id_jogo, nome_jogo
FROM jogos;

-- Seleciona paginado
SELECT u.nome, u.sobrenome, u.data_de_nascimento, u.sexo, u.id_usuario, j.nome_jogo, j.id_jogo
FROM usuarios AS u INNER JOIN usuarios_jogos AS uj ON (u.id_usuario = uj.id_usuariof) 
				   INNER JOIN jogos AS j ON (uj.id_jogof = j.id_jogo)
LIMIT 1 OFFSET 1;

SELECT nome_jogo, id_jogo FROM jogos LIMIT 3 OFFSET 0;
