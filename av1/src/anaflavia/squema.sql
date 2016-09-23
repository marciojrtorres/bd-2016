-- Numeros usados, nos selects, updates e deletes variáveis...
CREATE TABLE clientes ( id_cliente    SERIAL      PRIMARY KEY, 
						nome          VARCHAR(20) NOT NULL,
						email     VARCHAR(30) NULL,
						idade     INTEGER 

);

CREATE TABLE tipo_compra( id_tipo SERIAL PRIMARY KEY,
			tipo  VARCHAR (20) NOT NULL
);

CREATE TABLE compras ( 	id_compra     SERIAL    PRIMARY KEY,
						id_cliente    INTEGER   NOT NULL , 
						id_tipo       INTEGER NOT NULL,
						quantidade    INTEGER,
						preco        FLOAT,
						data_compra  DATE,
						FOREIGN KEY(id_cliente)REFERENCES clientes(id_cliente),
						FOREIGN KEY (id_tipo) REFERENCES tipo_compra(id_tipo)

);

INSERT INTO clientes (nome, email,idade ) VALUES ('Marcio','marcio.torres@hotmail.com',40);
INSERT INTO clientes (nome, email,idade ) VALUES ('Pedro','pedro@hotmail.com',21);
INSERT INTO clientes (nome, email,idade ) VALUES ('Vitoria','vitoria@hotmail.com',27);
INSERT INTO tipo_compra (tipo) VALUES ('Borracha');
INSERT INTO tipo_compra (tipo) VALUES ('Apontador');
INSERT INTO tipo_compra (tipo) VALUES ('Caderno');
INSERT INTO compras (id_cliente, id_tipo,quantidade, preco, data_compra ) VALUES (1,2,3,15.00,'24/08/2016');
INSERT INTO compras (id_cliente, id_tipo,quantidade, preco, data_compra ) VALUES (2,1,4,20.00,'17/08/2016');
INSERT INTO compras (id_cliente, id_tipo,quantidade, preco, data_compra ) VALUES (1,2,5,10.00,'23/08/2016');

DELETE FROM tipo_compra WHERE id_tipo = 3;
DELETE FROM clientes WHERE id_cliente = 4;
DELETE FROM compras WHERE id_compra = 4;

SELECT nome,email,idade FROM clientes WHERE id_cliente = 1;
SELECT tipo FROM tipo_compra WHERE id_tipo = 1;
SELECT cli.nome, t.tipo ,comp.quantidade, comp.preco, comp.data_compra FROM compras AS comp 
		INNER JOIN clientes AS cli ON (cli.id_cliente = comp.id_cliente) 
		INNER JOIN tipo_compra AS t ON (t.id_tipo = comp.id_tipo) 
		WHERE comp.id_compra = 3;
		
SELECT id_cliente,nome,email, idade FROM clientes ORDER BY nome,idade DESC OFFSET 2 LIMIT 2;
SELECT id_tipo,tipo FROM tipo_compra ORDER BY tipo DESC OFFSET  1 LIMIT 2;
SELECT cli.id_cliente,cli.nome, cli.email, cli.idade,t.id_tipo ,t.tipo ,comp.id_compra, comp.quantidade, comp.preco, comp.data_compra FROM compras AS comp 
	INNER JOIN clientes AS cli ON (cli.id_cliente = comp.id_cliente) 
	INNER JOIN tipo_compra AS t ON (t.id_tipo = comp.id_tipo)  
	ORDER BY cli.nome,cli.idade DESC OFFSET  2 LIMIT 2;
		
UPDATE tipo_compra SET tipo = 'classificador' WHERE id_tipo = 7;
UPDATE clientes SET nome = 'Ana',email = 'flavia@hotmail.com',idade = 17 WHERE id_cliente = 2;
UPDATE compras SET id_cliente = 2, id_tipo=6, quantidade = 5,preco = 3.50,data_compra = '24/08/2016' WHERE id_compra = 2;
