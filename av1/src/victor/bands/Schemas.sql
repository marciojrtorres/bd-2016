--TABLES

CREATE TABLE bands (id_band 	SERIAL 	    NOT NULL PRIMARY KEY,
				    name    	VARCHAR(40) NOT NULL,
				    beggin 		INTEGER     NOT NULL,
				    end_band	INTEGER	        NULL,
				    nacionality VARCHAR(25) NOT NULL);
		    
CREATE TABLE albums (id_album	SERIAL	     NOT NULL PRIMARY KEY,
				    name_album	VARCHAR(30)  NOT NULL,
				    duration	INTEGER,
				    release		DATE	     NOT NULL,
				    id_band		INTEGER 	 NOT NULL
								REFERENCES   bands(id_band));
								
CREATE TYPE genres AS ENUM ('ROCK_PSICODELICO', 'ROCK_ALTERNATIVO', 'GRUNGE',
							'ROCK', 'ROCK_PROGRESSIVO', 'BLUES');
						   
CREATE TABLE bands_genres (id_band 	INTEGER NOT NULL
									REFERENCES bands(id_band),
						   genre	genres  NOT NULL);
						   
--INSERTS BANDS
						   
INSERT INTO bands VALUES (DEFAULT, 'Dire Straits', 1977, 1995, 'United Kingdom');
INSERT INTO bands VALUES (DEFAULT, 'Nirvana', 1987, 1994, 'USA');


INSERT INTO bands (name, beggin, nacionality) 
		    VALUES ('Os Paralamas do Sucesso', 1977, 'Brasil');

SELECT * FROM bands;

SELECT name FROM bands
		WHERE nacionality = 'Brasil';

--INSERTS GENRES
		
INSERT INTO bands_genres VALUES (1, 'rock');
INSERT INTO bands_genres VALUES (1, 'rock progressivo');

INSERT INTO bands_genres VALUES (2, 'grunge');


SELECT * FROM bands_genres;

--INSERTS ALBUMS

INSERT INTO albums VALUES(DEFAULT, 'Brothers in Arms', 54, '1985-05-13', 1);

INSERT INTO albums (name_album, duration, release, id_band) VALUES
		   			('Nevermind', 54, '1991-09-24', 2);

SELECT * FROM albums;

SELECT ban.name, alb.name_album FROM bands ban JOIN albums alb ON
				     				(ban.id_band = alb.id_band)
				     				
--SELECT GENRE

SELECT ban.name, gen.genre FROM bands ban JOIN bands_genres gen ON
										  (ban.id_band = gen.id_band);
				     				
--LIMIT AND OFFSET
				     				
SELECT name FROM bands
LIMIT 2;

SELECT * FROM bands
LIMIT 2 OFFSET 2;

--UPDATE

UPDATE bands SET name = 'British Band'
WHERE id_band = 1;

UPDATE albums SET name_album = 'Nevermind Deluxe',
		  		  release = '2011-09-24'
where id_band = 2;

--DELETE

DELETE FROM albums 
WHERE id_album = 2;

DELETE FROM bands
WHERE id_band = 3;

DELETE FROM bands_genres
WHERE id_band = 1 AND genre = 'rock progressivo'