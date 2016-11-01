DROP TABLE IF EXISTS idhm_temp;

CREATE TABLE idhm_temp (
	ano       INTEGER,
	uf        INTEGER,
	codigo    INTEGER,
	municipio VARCHAR(100),
  idhm      DECIMAL,
  idhm_e    DECIMAL,
  idhm_l    DECIMAL,
  idhm_r    DECIMAL
);

COPY idhm_temp 
FROM '/home/torres/github/bd-2016/dados/municipios4.csv' 
WITH DELIMITER ';';

CREATE TABLE idhm (
	ano INTEGER,
	codigo INTEGER,
	idhm      DECIMAL,
  idhm_e    DECIMAL,
  idhm_l    DECIMAL,
  idhm_r    DECIMAL
);

INSERT INTO idhm (ano, codigo, idhm, idhm_e, idhm_l, idhm_r) 
	SELECT ano, codigo, idhm, idhm_e, idhm_l, idhm_r 
	FROM idhm_temp;

cat ibge-rs.txt | sed -r 's/\.//g' | sed -r 's/,/./g' | cut -f 2,4-7 | sed -r 's/( \t)/;/g' > ibge-tratado.csv

CREATE TABLE ibge (
	codigo INTEGER,
  populacao INTEGER,
  area      DECIMAL,
  densidade DECIMAL,
  serie     INTEGER
);

COPY ibge 
FROM '/home/torres/github/bd-2016/dados/ibge-tratado.csv' 
WITH DELIMITER ';'
NULL AS '';












