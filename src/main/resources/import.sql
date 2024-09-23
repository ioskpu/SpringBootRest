# Script para crear data para las entidades fabricante
INSERT INTO fabricante (id, nombre) VALUES (1, 'Sony');
INSERT INTO fabricante (id, nombre) VALUES (2, 'Creative Labs');
INSERT INTO fabricante (id, nombre) VALUES (3, 'Hewlett-Packard');
INSERT INTO fabricante (id, nombre) VALUES (4, 'Iomega');
INSERT INTO fabricante (id, nombre) VALUES (5, 'Fujitsu');
INSERT INTO fabricante (id, nombre) VALUES (6, 'Winchester');
INSERT INTO fabricante (id, nombre) VALUES (7, 'Samsung');
INSERT INTO fabricante (id, nombre) VALUES (8, 'Seagate');
INSERT INTO fabricante (id, nombre) VALUES (9, 'Motorola');
INSERT INTO fabricante (id, nombre) VALUES (10, 'Asus');
INSERT INTO fabricante (id, nombre) VALUES (11, 'Apple');
INSERT INTO fabricante (id, nombre) VALUES (12, 'Xiaomi');

# Script para crear data para las entidades producto
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (1, 'Hard drive', 240, 5);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (2, 'Memory', 120, 6);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (3, 'ZIP drive', 150, 4);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (4, 'CD-ROM', 90, 2);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (5, 'Printer', 270, 3);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (6, 'Monitor', 240, 1);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (7, 'DVD drive', 180, 9);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (8, 'CD drive', 150, 2);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (9, 'Web camera', 80, 5);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (10, 'Laser Printer', 270, 3);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (11, 'Printer', 300, 3);
INSERT INTO producto (id, nombre, precio, id_fabricante) VALUES (12, 'Monitor', 240, 1);