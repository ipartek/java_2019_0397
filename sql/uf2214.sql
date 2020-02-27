-- 1. Listado de todos los proveedores ordenados alfabéticamente.
SELECT * FROM proveedores ORDER BY nombre;

-- 2. Listado de todos productos organizados por categorías.
SELECT *
FROM categorias c
JOIN productos p ON p.id_categoria = c.id_categoria
ORDER BY c.id_categoria, p.id_producto;

-- 3. Las ventas del último mes, mostrando: cliente, producto, categoría y cantidad
SELECT c.nombre, p.descripcion, c.descripcion, v.cantidad
FROM clientes c, (SELECT MAX(fecha) fecha FROM facturas) m
JOIN facturas f ON c.id_cliente = f.id_cliente
JOIN ventas v ON f.id_factura = v.id_factura
JOIN productos p ON p.id_producto = v.id_producto
WHERE MONTH(f.fecha) = MONTH(m.fecha) AND YEAR(f.fecha) = YEAR(m.fecha);

-- 4. Listado de los 5 productos más vendidos
SELECT p.descripcion, SUM(v.cantidad) total
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.id_producto
ORDER BY total DESC
LIMIT 5;

-- 5. Listado de los 5 clientes que más dinero han gastado
SELECT c.nombre, SUM(p.precio * v.cantidad) total
FROM clientes c
JOIN facturas f ON c.id_cliente = f.id_cliente
JOIN ventas v ON f.id_factura = v.id_factura
JOIN productos p ON p.id_producto = v.id_producto
GROUP BY c.id_cliente
ORDER BY total DESC
LIMIT 5;

-- Crear una vista que para mostrar datos estadísticos de productos por categoría, por ejemplo: 
-- numero productos por categoría, precio máximo, precio mínimo, media de precios y sumatorio.
CREATE VIEW estadistica AS 
SELECT c.descripcion, COUNT(p.id_producto) numero, MAX(p.precio) maximo, MIN(p.precio) minimo, AVG(p.precio) media, SUM(p.precio) sumatorio
FROM categorias c
JOIN productos p ON c.id_categoria = p.id_categoria
GROUP BY c.id_categoria;

-- Crear procedimiento almacenado para que busque facturas entre dos fechas concretas.
DELIMITER $$;
CREATE PROCEDURE facturasEntreDosFechas(IN inicio DATE, IN fin DATE)
BEGIN
	SELECT *
    FROM facturas
    WHERE fecha BETWEEN inicio AND fin;
END;
$$
DELIMITER ; $$
-- Crear disparador para controlar que no se pueda insertar un precio negativo en la tabla de productos
CREATE TRIGGER precioNegativo BEFORE INSERT ON productos FOR EACH ROW
BEGIN
	IF NEW.precio < 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='No se admiten precios negativos';
    END IF;
END;
