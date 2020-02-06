-- Queremos poder consultar las direcciones y números de las viviendas propiedad de una persona según su DNI.
SELECT CONCAT(p.dni, IFNULL(p.dni_discriminante, '')) AS DNI, p.nombre, p.apellidos, v.calle, v.numero
FROM viviendas v
JOIN personas_propiedad_viviendas ppv ON v.id = ppv.viviendas_id
JOIN personas p ON p.id = ppv.personas_id;

-- Necesitamos los nombres y apellidos de los propietarios de un municipio determinado.
SELECT m.nombre, p.nombre, p.apellidos
FROM personas p
JOIN personas_propiedad_viviendas ppv ON p.id = ppv.personas_id
JOIN viviendas v ON v.id = ppv.viviendas_id
JOIN municipios m ON m.id = v.municipios_id
WHERE m.codigo = 'BAR';

-- Queremos poder preguntar quién está empadronado en una vivienda según su dirección y número.
SELECT p.nombre, p.apellidos, p.dni
FROM personas p
JOIN viviendas v ON v.id = p.empadronada_vivienda_id
JOIN municipios m ON m.id = v.municipios_id
WHERE m.codigo = 'BIL' AND v.calle = 'Gran Vía' AND v.numero = 1;

-- Queremos saber los propietarios de una vivienda según su dirección y número.
SELECT p.nombre, p.apellidos
FROM personas p
JOIN personas_propiedad_viviendas ppv ON p.id = ppv.personas_id
JOIN viviendas v ON v.id = ppv.viviendas_id
JOIN municipios m ON m.id = v.municipios_id
WHERE m.codigo = 'BAR' AND v.calle = 'Plaza Bizkaia' AND v.numero = 5