CREATE DATABASE trabalho{
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;
}
 CREATE TABLE funcionario{
 	idFuncionario integer NOT NULL,
 	idEmpresa integer NOT NULL,
 	nomeFuncionario character varying(90),
 	cpf VARCHAR(11),
 	rg VARCHAR(14),
 	endereco character varying(90),
 	dataEntrada date,
 	cargo character varying(90),
 	genero  char(1) NOT NULL,
    CHECK (genero IN ('M', 'F'))
 	CONSTRAINT funcionario_pkey PRIMARY KEY (idFuncionario),
 	CONSTRAINT funcionario_empresa_fk FOREIGN KEY (idEmpresa)
      REFERENCES idEmpresa (idEmpresa) MATCH SIMPLE
 	
 }
 
 CREATE TABLE empresa{
    idEmpresa integer NOT NULL,
    cnpj VARCHAR(14),
    nomeEmpresa character varying(90),
    CONSTRAINT empresa_pkey PRIMARY KEY (idEmpresa),
 	CONSTRAINT empresa_funcionario_fk FOREIGN KEY (idEmpresa)
      REFERENCES idEmpresa (idEmpresa) MATCH SIMPLE
    
 }
 INSERT INTO empresa (cnpj, nomeEmpresa)VALUES('?', '?')
 
 INSERT INTO funcionario (nomeFuncionario, cpf, rg, endereco, dataEntrada, cargo, genero)
 VALUES('?', '?', '?', '?', '?', '?','?')
 
 
 SELECT idFuncionario. idEmpresa, nomeFuncionario, cpf, rg, endereco, dataEntrada, cargo, genero  FROM funcionario
 funcionario JOIN empresa ON funcionario.idEmpresa=empresa.idEmpresa
 WHERE idFuncionario = '?'
 
 SELECT nomeEmpresa FROM empresa 
 WHERE idEmpresa = '?'
 
 UPDATE funcionario
 SET endereco = '?', cargo = '?'
 WHERE idFuncionario = '?'
 
 DELETE funcionario
 WHERE idFuncionario = '?'
 
 DELETE empresa
 WHERE idEmpresa = '?'
 
 SELECT idEmpresa FROM empresa
 ORDER BY id DESC
 
 
 