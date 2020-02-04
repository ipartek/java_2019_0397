SELECT c.nombre, f.numero_factura, f.fecha, cat.nombre, p.nombre, fd.cantidad, p.precio, fd.cantidad * p.precio total, s.nombre, e.nombre, j.nombre
FROM clientes c
JOIN facturas f on c.id = f.cliente_id
JOIN facturas_detalles fd on f.id = fd.facturas_id
JOIN productos p on fd.productos_id = p.id
JOIN categorias cat on p.categoria_id = cat.id
JOIN empleados e on f.empleado_id = e.id
JOIN secciones s on e.seccion_id = s.id
JOIN empleados j on j.id = e.jefe_id

