CREATE TYPE status_ped AS ENUM ('Aguardando_pagamento', 'Em_transporte', 'Entregue');

CREATE TABLE vendedores(	
	codigo 			SERIAL 				 PRIMARY KEY,
	nome 			VARCHAR(20),
	sobrenome		VARCHAR(30),
	data_nasc 		DATE,
	regiao			VARCHAR(20)
);
CREATE TABLE clientes(
	codigo			SERIAL				PRIMARY KEY,
	nome 			VARCHAR(20),
	sobrenome 		VARCHAR(30),
	cidade			VARCHAR(30),
	uf				VARCHAR(2),
	cep				VARCHAR(10)
);
CREATE TABLE produtos(
	codigo			SERIAL				 PRIMARY KEY,
	descricao		VARCHAR(30)	NOT NULL,
	categoria		VARCHAR(20)		NULL,
	preco			DOUBLE PRECISION 	NOT NULL,
	qtd_estoque		INTEGER
);
CREATE TABLE pedidos(
	codigo 			SERIAL 				 PRIMARY KEY,
	cod_vend		INTEGER		NOT NULL REFERENCES vendedores(codigo),
	cod_cli			INTEGER		NOT NULL REFERENCES clientes(codigo),
	data_pedido		DATE		NOT NULL,
	data_entrega		DATE		    NULL,
	frete			DOUBLE PRECISION    NULL,
	status			STATUS_PED	NOT NULL
);
CREATE TABLE detalhes_pedidos(
	cod_ped			INTEGER		NOT NULL REFERENCES pedidos(codigo),
	cod_prod		INTEGER		NOT NULL REFERENCES produtos(codigo),
	quantidade		INTEGER		NOT NULL
);

INSERT INTO vendedores VALUES(DEFAULT, 'Felipe', 'Mendes', '1985-01-10',  'Sul');
INSERT INTO vendedores VALUES(DEFAULT, 'Carlos', 'Goulart', '1960-05-21',  'Centro-Oeste');
INSERT INTO vendedores VALUES(DEFAULT, 'Gabriel', 'Kivitz', '1972-07-30', 'Sudeste');
INSERT INTO vendedores VALUES(DEFAULT, 'Marcos', 'Marques', '1980-12-07', 'Sul');
INSERT INTO vendedores VALUES(DEFAULT, 'Lucas', 'Oliveira', '1989-03-11', 'Sudeste');

INSERT INTO filhos VALUES(DEFAULT, 1, 'Henrique', 'Mendes', 10);
INSERT INTO filhos VALUES(DEFAULT, 1, 'Paula', 'Mendes', 5);

INSERT INTO filhos VALUES(DEFAULT, 2, 'João', 'Goulart', 18);
INSERT INTO filhos VALUES(DEFAULT, 2, 'Nicolas', 'Goulart', 15);
INSERT INTO filhos VALUES(DEFAULT, 2, 'José', 'Goulart', 12);

INSERT INTO filhos VALUES(DEFAULT, 3, 'Paula', 'Mendes', 9);

INSERT INTO filhos VALUES(DEFAULT, 5, 'Erick', 'Oliveira', 2);
INSERT INTO filhos VALUES(DEFAULT, 5, 'Joana', 'Oliveira', 7);

INSERT INTO clientes VALUES(DEFAULT, 'Milton', 'Medeiros', 'Porto Alegre', 'RS', '90450000');
INSERT INTO clientes VALUES(DEFAULT, 'Hilton', 'Sigallis', 'Porto Alegre', 'RS', '90002013');
INSERT INTO clientes VALUES(DEFAULT, 'Marcio', 'Torres', 'Pelotas', 	 'RS', '96010000');
INSERT INTO clientes VALUES(DEFAULT, 'Rafael', 'Betito', 'Florianópolis','SC', '88010020');
INSERT INTO clientes VALUES(DEFAULT, 'Igor', 'Pereira', 'Rio Grande',	 'RS', '96212000');
INSERT INTO clientes VALUES(DEFAULT, 'Marcos', 'Silva', 'Taubaté', 	 'SP', '01310909');
INSERT INTO clientes VALUES(DEFAULT, 'Nilcio', 'Lemos', 'Rio Grande', 'RS', '96212450');
INSERT INTO clientes VALUES(DEFAULT, 'Amauri', 'Pereira', 'Curitiba', 'PR', '85430250');
INSERT INTO clientes VALUES(DEFAULT, 'Cláudia', 'Poester', 'Balneario Camburiu', 'SC', '88480650');
INSERT INTO clientes VALUES(DEFAULT, 'Rosele', 'Falcke', 'Rio de Janeiro', 'RJ', '28687259');
INSERT INTO clientes VALUES(DEFAULT, 'Francisco', 'Rios', 'Uberlândia', 'MG', '28687259');

INSERT INTO telefones VALUES(DEFAULT, 1, '53', '99784455');
INSERT INTO telefones VALUES(DEFAULT, 1, '53', '99989635');
INSERT INTO telefones VALUES(DEFAULT, 2, '34', '85749621');
INSERT INTO telefones VALUES(DEFAULT, 3, '51', '99865234');
INSERT INTO telefones VALUES(DEFAULT, 4, '53', '91052635');
INSERT INTO telefones VALUES(DEFAULT, 5, '51', '91020565');
INSERT INTO telefones VALUES(DEFAULT, 5, '51', '91032546');

INSERT INTO produtos VALUES(DEFAULT, 'Capacete', 'Proteçao', 10.00, 2000);
INSERT INTO produtos VALUES(DEFAULT, 'Filtro solar', 'Proteção', 7.00, 1500);
INSERT INTO produtos VALUES(DEFAULT, 'Corda', 'Proteção', 20.00, 100);
INSERT INTO produtos VALUES(DEFAULT, 'Saco plástico', 'Higienização', 2.00, 1200);
INSERT INTO produtos VALUES(DEFAULT, 'Alicate', 'Construção', 3.00, 450);
INSERT INTO produtos VALUES(DEFAULT, 'Martelo', 'Construção', 12.00, 500);
INSERT INTO produtos VALUES(DEFAULT, 'Alcool Gel', 'Higienização', 8.50, 780);
INSERT INTO produtos VALUES(DEFAULT, 'Oculos preto', 'Proteção', 3.50, 680);
INSERT INTO produtos VALUES(DEFAULT, 'Luva', 'Proteçao', 5.00 , 5000);
INSERT INTO produtos VALUES(DEFAULT, 'Fone de ouvidos', 'Proteção', 25.00, 700);
INSERT INTO produtos VALUES(DEFAULT, 'Roupa especial', 'Preoção', 50.00, 150);
INSERT INTO produtos VALUES(DEFAULT, 'Bota', 'Proteção', 30.00, 300);

INSERT INTO pedidos VALUES(DEFAULT, 1, 1, '2016-05-01', '2016-05-09', 100.00, 'Entregue');
INSERT INTO pedidos VALUES(DEFAULT, 3, 6, '2016-05-03', '2016-05-15', 250.00, 'Entregue');
INSERT INTO pedidos VALUES(DEFAULT, 3, 10, '2016-05-08', '2016-05-19', 50.00, 'Entregue');
INSERT INTO pedidos VALUES(DEFAULT, 1, 3, '2016-06-02', '2016-06-10', 25.00, 'Entregue');
INSERT INTO pedidos VALUES(DEFAULT, 2, 11, '2016-06-15', '2016-06-30', 80.00, 'Entregue');
INSERT INTO pedidos VALUES(DEFAULT, 5, 6, '2016-08-18', DEFAULT, 300.00, 'Em_transporte');
INSERT INTO pedidos VALUES(DEFAULT, 1, 5, '2016-08-19', DEFAULT, 50.00, 'Em_transporte');
INSERT INTO pedidos VALUES(DEFAULT, 4, 2, '2016-08-15', '2016-08-10', 30.00, 'Entregue');
INSERT INTO pedidos VALUES(DEFAULT, 4, 7, '2016-08-20', DEFAULT, 0.0, 'Aguardando_pagamento');
INSERT INTO pedidos VALUES(DEFAULT, 4, 3, '2016-08-20', DEFAULT, 85.00, 'Aguardando_pagamento');

INSERT INTO detalhes_pedidos VALUES(1, 1, 20);
INSERT INTO detalhes_pedidos VALUES(1, 2, 30);
INSERT INTO detalhes_pedidos VALUES(1, 5, 10);
INSERT INTO detalhes_pedidos VALUES(2, 1, 20);
INSERT INTO detalhes_pedidos VALUES(2, 4, 2);
INSERT INTO detalhes_pedidos VALUES(2, 6, 15);
INSERT INTO detalhes_pedidos VALUES(3, 1, 20);
INSERT INTO detalhes_pedidos VALUES(3, 8, 8);
INSERT INTO detalhes_pedidos VALUES(4, 9, 5);
INSERT INTO detalhes_pedidos VALUES(5, 10, 20);
INSERT INTO detalhes_pedidos VALUES(5, 1, 12);
INSERT INTO detalhes_pedidos VALUES(5, 7, 15);
INSERT INTO detalhes_pedidos VALUES(5, 6, 35);
INSERT INTO detalhes_pedidos VALUES(5, 5, 22);

DELETE FROM clientes WHERE codigo = 9;

DELETE FROM detalhes_pedidos WHERE cod_ped = 2;

SELECT nome FROM clientes WHERE UPPER(cidade) = 'PORTO ALEGRE';

SELECT codigo FROM pedidos WHERE status = 'Em_transporte';

-- Atualiza o nome do vendedor cujo seu codigo é 2
UPDATE vendedores SET nome = 'Joaquino' WHERE codigo = 2;

--Seleciona o nome dos clientes com mais de 5 vendas
SELECT clientes.nome FROM clientes
			JOIN pedidos ON(clientes.codigo = pedidos.cod_cli)
			GROUP BY clientes.codigo
			HAVING COUNT(pedidos.*) > 5;


			


			
