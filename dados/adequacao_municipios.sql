ALTER TABLE municipios 
ADD COLUMN codigo INTEGER;

UPDATE municipios
SET codigo = (
	SELECT DISTINCT codigo 
	FROM idhm_temp
	WHERE idhm_temp.municipio = municipios.municipio
);
