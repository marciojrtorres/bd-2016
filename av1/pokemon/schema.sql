CREATE DATABASE pokemon;

CREATE TYPE Evolução AS ENUM ('Inicial', 'Secundaria', 'Final');

CREATE TABLE pokedex(
	id_usuario 	SERIAL 	 	NOT NULL PRIMARY KEY,
	nome 		VARCHAR(20) NOT NULL
);

CREATE TABLE base(	
	id_base 	INTEGER 		NOT NULL PRIMARY KEY,
	nome 		VARCHAR(20) 	NOT NULL,
	tipo 		VARCHAR(20)		NOT NULL
);

CREATE TABLE pokemons_capturados(
	id_capturados 	SERIAL 		NOT NULL,
	id_pokedex 		INTEGER		NOT NULL REFERENCES pokedex (id_usuario),
	id_pokemon 		INTEGER		NOT NULL REFERENCES base (id_base),
	registro 		DATE		 	NULL,
	estagio			evolução		NULL
);

INSERT INTO base (nome, id_base, tipo) VALUES ('Bulbasaur',1,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Ivysaur',2,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Venusaur',3,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Charmander',4,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Charmeleon',5,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Charizard', 6,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Squirtle',7,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Wartortle', 8,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Blastoise', 9,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Caterpie', 10,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Metapod', 11,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Butterfree', 12,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Weedle', 13,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Kakuna', 14,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Beedrill',15,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Pidgey', 16,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Pidgeotto', 17,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Pidgeot', 18,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Rattata', 19,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Raticate', 20,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Spearow', 21,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Fearow', 22,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Ekans', 23,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Arbok', 24,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Pikachu', 25,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Raichu',26,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Sandshrew', 27,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Sandslash', 28,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Nidoran F', 29,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Nidorina',30,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Nidoqueen', 31,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Nidoran M', 32,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Nidorino', 33,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Nidoking', 34,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Clefairy', 35 ,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Clefable', 36,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Vulpix', 37,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Ninetales', 38,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Jigglypuff', 39,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Wigglytuff', 40,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Zubat',41,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Golbat',42,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Oddish',43,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Gloom',44,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Vileplume', 45,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Paras',46,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Parasect',47,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Venonat',48,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Venomoth',49,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Diglett',50,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Dugtrio',51,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Meowth',52 ,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Persian',53 ,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Psyduck',54 ,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Golduck',55 ,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Mankey', 56 ,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Primeape',57,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Growlithe', 58,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Arcanine',59,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Poliwag',60,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Poliwhirl', 61,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Poliwrath', 62,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Abra', 63, 'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Kadabra', 64,'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Alakazam',65 ,'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Machop',66 ,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Machoke',67,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Machamp',68 ,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Bellsprout', 69,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Weepinbell', 70,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Victreebel', 71,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Tentacool', 72,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Tentacruel', 73,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Geodude',74,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Graveler',75,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Golem',76,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Ponyta',77,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Rapidash',78,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Slowpoke',79,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Slowbro',80,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Magnemite', 81,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Magneton',82,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Farfetchd', 83,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Doduo',84,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Dodrio',85,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Seel',86,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Dewgong',87,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Grimer',88,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Muk', 89,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Shellder', 90,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Cloyster', 91,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Gastly', 92,'Ghost');
INSERT INTO base (nome, id_base, tipo) VALUES ('Haunter', 93,'Ghost');
INSERT INTO base (nome, id_base, tipo) VALUES ('Gengar', 94,'Ghost');
INSERT INTO base (nome, id_base, tipo) VALUES ('Onix', 95,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Drowzee',96,'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Hypno',97,'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Krabby',98,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Kingler',99,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Voltorb',100,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Electrode', 101,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Exeggcute', 102,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Exeggutor', 103,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Cubone', 104,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Marowak', 105 ,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Hitmonlee', 106 ,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Hitmonchan', 107,'Fighting');
INSERT INTO base (nome, id_base, tipo) VALUES ('Lickitung',108,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Koffing', 109,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Weezing',110,'Poison');
INSERT INTO base (nome, id_base, tipo) VALUES ('Rhyhorn',111,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Rhydon',112,'Ground');
INSERT INTO base (nome, id_base, tipo) VALUES ('Chansey',113,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Tangela',114,'Grass');
INSERT INTO base (nome, id_base, tipo) VALUES ('Kangaskhan', 115,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Horsea',116,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Seadra',117,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Goldeen',118,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Seaking',119,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Staryu',120,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Starmie', 121,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Mr. Mime', 122,'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Scyther',123,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Jynx', 124 ,'Ice');
INSERT INTO base (nome, id_base, tipo) VALUES ('Electabuzz', 125,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Magmar',126,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Pinsir',127,'Bug');
INSERT INTO base (nome, id_base, tipo) VALUES ('Tauros',128,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Magikarp',129,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Gyarados',130,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Lapras',131,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Ditto',132,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Eevee', 133,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Vaporeon', 134,'Water');
INSERT INTO base (nome, id_base, tipo) VALUES ('Jolteon', 135,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Flareon', 136,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Porygon', 137,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Omanyte', 138 ,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Omastar', 139,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Kabuto', 140,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Kabutops', 141,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Aerodactyl', 142,'Rock');
INSERT INTO base (nome, id_base, tipo) VALUES ('Snorlax', 143,'Normal');
INSERT INTO base (nome, id_base, tipo) VALUES ('Articuno', 144 ,'Ice');
INSERT INTO base (nome, id_base, tipo) VALUES ('Zapdos', 145,'Electric');
INSERT INTO base (nome, id_base, tipo) VALUES ('Moltres',146,'Fire');
INSERT INTO base (nome, id_base, tipo) VALUES ('Dratini', 147,'Dragon');
INSERT INTO base (nome, id_base, tipo) VALUES ('Dragonair', 148,'Dragon');
INSERT INTO base (nome, id_base, tipo) VALUES ('Dragonite', 149,'Dragon');
INSERT INTO base (nome, id_base, tipo) VALUES ('Mewtwo', 150,'Psychic');
INSERT INTO base (nome, id_base, tipo) VALUES ('Mew', 151,'Psychic');

INSERT INTO pokedex VALUES(default, 'Thaylles');

INSERT INTO pokemons_capturados VALUES (default, 1, 1);

SELECT pok.id_pokemon, base.nome FROM base INNER JOIN pokemons_capturados AS pok ON (base.id_base = pok.id_pokemon) INNER JOIN pokedex AS po ON (pok.id_pokedex = po.id_usuario) WHERE id_usuario = 1;

SELECT * FROM pokedex;

UPDATE pokedex SET nome='Rosa' WHERE id_usuario = 1;

UPDATE base SET nome='Bulbasalto' WHERE id_base=1;

DELETE FROM pokemons_capturados WHERE id_pokemon = 1;