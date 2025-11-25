-- Limpiar la tabla antes de insertar
DELETE FROM registro;

-- Insertar registros de ejemplo
INSERT INTO registro (fecha_lectura, ubicacion, temperatura, unidad, precipitacion) VALUES
('2025-01-01', 'Madrid', 12.5, 'CELSIUS', 0.0),
('2025-01-02', 'Barcelona', 15.2, 'CELSIUS', 1.5),
('2025-01-03', 'Valencia', 18.0, 'CELSIUS', 0.2),
('2025-01-04', 'Sevilla', 20.3, 'CELSIUS', 0.0),
('2025-01-05', 'Bilbao', 10.8, 'CELSIUS', 2.0),
('2025-01-06', 'Madrid', 13.1, 'FAHRENHEIT', 0.1),
('2025-01-07', 'Barcelona', 16.0, 'FAHRENHEIT', 0.0),
('2025-01-08', 'Valencia', 19.5, 'CELSIUS', 0.3),
('2025-01-09', 'Sevilla', 21.0, 'CELSIUS', 0.0),
('2025-01-10', 'Bilbao', 11.2, 'FAHRENHEIT', 1.8);
