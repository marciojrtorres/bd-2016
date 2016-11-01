SELECT m.municipio, sum(o.qtde)
FROM municipios m 
JOIN ocorrencias o ON m.id = o.id_municipio
JOIN fatos f ON f.id = o.id_fato
GROUP BY m.municipio;

SELECT m.municipio, sum(o.qtde), g.densidade
FROM municipios m 
JOIN ocorrencias o ON m.id = o.id_municipio
JOIN fatos f ON f.id = o.id_fato
JOIN ibge g ON m.codigo = g.codigo
GROUP BY m.municipio, g.densidade
ORDER BY g.densidade DESC
-- m.municipio LIKE '%GODOI%' AND
