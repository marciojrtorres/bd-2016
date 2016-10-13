-- CREATE DATABASE ocorrencias;

CREATE TABLE ocorrencias_temp (
  municipio VARCHAR(100),
  ano       INTEGER,
  mes       INTEGER,
  fato      VARCHAR(100),
  qtde      INTEGER
);

COPY ocorrencias_temp
FROM '/home/torres/github/bd-2016/dados/ocorrencias-limpo.csv'
WITH DELIMITER ';' CSV HEADER

-- investigar o tamanho da coluna
SELECT distinct length(municipio) tam
FROM ocorrencias_temp
ORDER BY tam DESC;

-- criar uma tabela
CREATE TABLE municipios (
  id        SERIAL PRIMARY KEY,
  municipio VARCHAR(30)
);

CREATE TABLE fatos (
  id         SERIAL PRIMARY KEY,
  fato       VARCHAR(50)
);

-- copiar os fatos da tabela temporária para a tabela final
INSERT INTO fatos (fato) SELECT DISTINCT fato
FROM ocorrencias_temp
ORDER BY fato;
-- municipios
INSERT INTO municipios (municipio)
SELECT DISTINCT municipio
FROM ocorrencias_temp
ORDER BY municipio;

-- incluir colunas para receber os id's
ALTER TABLE ocorrencias_temp
ADD COLUMN id_municipio INTEGER,
ADD COLUMN id_fato      INTEGER;

-- atualizando os ids segundo os fatos
UPDATE ocorrencias_temp
SET id_fato = (
  SELECT id FROM fatos
  WHERE fatos.fato = ocorrencias_temp.fato
);
-- atualizando ids dos municipios
UPDATE ocorrencias_temp
SET id_municipio = (
  SELECT id FROM municipios
  WHERE municipios.municipio = ocorrencias_temp.municipio
);

-- tabela final
CREATE TABLE ocorrencias (
  id_municipio INTEGER REFERENCES municipios(id),
  id_fato      INTEGER REFERENCES fatos(id),
  ano          INTEGER,
  mes          INTEGER,
  qtde         INTEGER DEFAULT 0,
  PRIMARY KEY (id_municipio, id_fato, ano, mes)
);
-- copiando os dados da temporária para a tabela final
INSERT INTO ocorrencias (id_municipio, id_fato, ano, mes, qtde)
SELECT id_municipio, id_fato, ano, mes, qtde
FROM ocorrencias_temp;
