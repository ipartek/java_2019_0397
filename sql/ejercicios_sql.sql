-- 48. Hallar los departamentos que tienen más de tres (3) empleados. Entregar el número de empleados de esos departamentos.

SELECT d.codDepto, d.nombreDpto, COUNT(*) as numEmp
FROM departamento d
JOIN empleado e ON e.codDepto = d.codDepto
GROUP BY d.codDepto /* ó d.nombreDpto */
HAVING numEmp > 3;

-- 49. Obtener la lista de empleados jefes, que tienen al menos un empleado a su cargo. Ordene el informe inversamente por el nombre

SELECT j.nDIEmp, j.nomEmp, COUNT(e.jefeID)
FROM empleado e
JOIN empleado j ON e.jefeID = j.nDIEmp
GROUP BY j.nDIEmp
ORDER BY j.nomEmp DESC;

-- 50. Hallar los departamentos que no tienen empleados

SELECT *
FROM departamento d
WHERE d.codDepto NOT IN (SELECT codDepto FROM empleado);

SELECT d.codDepto, d.nombreDpto, d.ciudad
FROM departamento d
LEFT JOIN empleado e ON e.codDepto = d.codDepto
WHERE e.codDepto IS NULL;

-- 51. Entregar un reporte con el numero de cargos en cada departamento y cual es el promedio de salario de cada uno. Indique el nombre del departamento en el resultado.

SELECT d.codDepto, d.nombreDpto, COUNT(e.cargoE), IFNULL(AVG(e.salEmp), 0)
FROM departamento d
LEFT JOIN empleado e ON d.codDepto = e.codDepto
GROUP BY d.codDepto;

SELECT d.codDepto, d.nombreDpto, d.ciudad, e.cargoE, AVG(e.salEmp)
FROM departamento d
INNER JOIN empleado e ON d.codDepto = e.codDepto
GROUP BY d.codDepto, d.ciudad, e.cargoE
ORDER BY codDepto;

-- 52. Entregar el nombre del departamento cuya suma de salarios sea la más alta, indicando el valor de la suma

SELECT d.nombreDpto, SUM(e.salEmp)
FROM departamento d
INNER JOIN empleado e ON d.codDepto = e.codDepto
GROUP BY d.codDepto
ORDER BY SUM(e.salEmp) DESC
LIMIT 1;

SELECT d.nombreDpto, SUM(e.salEmp)
FROM departamento d
INNER JOIN empleado e ON d.codDepto = e.codDepto
GROUP BY d.codDepto
HAVING d.codDepto = (SELECT d.codDepto
	FROM departamento d
	INNER JOIN empleado e ON d.codDepto = e.codDepto
	GROUP BY d.codDepto
	ORDER BY SUM(e.salEmp) DESC
	LIMIT 1)
;

SELECT d.nombreDpto, ss.sumS
FROM departamento d
JOIN SumSalar ss ON ss.codDepto = d.codDepto
WHERE ss.sumS = (SELECT * FROM SumSalar2);

SELECT D.nombreDpto, E.sSalD
FROM Departamento D,
 (SELECT codDepto, sSalD
 FROM SumSalar2 S2, SumSalar S1
 WHERE S2.sSalD = S1.sumS) E
WHERE D.codDepto = E.codDepto;

-- 53. Entregar un reporte con el código y nombre de cada jefe, junto al número de empleados que dirige.
-- Puede haber empleados que no tengan supervisores, para esto se indicará solamente el numero de ellos dejando los valores restantes en NULL.

SELECT j.nDIEmp, j.nomEmp, COUNT(e.nDIEmp) numeroEmpleados
FROM empleado e
LEFT JOIN empleado j ON e.jefeID = j.nDIEmp
GROUP BY j.nDIEmp
ORDER BY numeroEmpleados DESC, nDIEmp DESC; 
