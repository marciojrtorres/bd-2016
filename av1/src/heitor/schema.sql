CREATE TYPE ser AS ENUM ('A', 'B');

CREATE TABLE time (
	id SERIAL NOT FULL PRIMARY KEY,
	nome VARCHAR(30) NOT NULL,
	dat_fund DATE NOT NULL,
	qtd_titulos INTEGER  NULL
	serie ser NOT NULL
);

CREATE TABLE jogador (
id_time INTEGER NOT NULL REFERENCES time(id),
						
	id_jog SERIAL NOT FULL PRIMARY KEY,
	nome  VARCHAR(50) NOT NULL,
	dat_nasc DATE NOTT NULL,
	altura FLOAT NOT NULL
	qtd_títulos_jog INTEGER NULL
);
 
 INSERT INTO time ( nome, dat_fund, qtd_titulos, serie ser) VALUES ('Inter', '1909', '50', 'A');
  
 
