SELECT c.nombre, SUM(p.precio * fd.cantidad) total
FROM clientes c
JOIN facturas f on c.id = f.cliente_id
JOIN facturas_detalles fd on f.id = fd.facturas_id
JOIN productos p on fd.productos_id = p.id
GROUP BY c.id
ORDER BY total DESC
LIMIT 0,1