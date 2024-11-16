-- IMPORTANTE: Las cosnultas se deben ejecutar si o si en el orden indicado:

INSERT INTO bodega (id, nombre, descripcion, historias, periodo_actualizacion, ultima_actualizacion, img_logo_bodega, sitio_web, latitud, longitud) VALUES
                (1, 'Bodega Freixenet Argentina', 'Bodega especializada en espumantes.', 'Historia de Freixenet Argentina.', 12, 1681257600000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/19_799412406012b67f5141fa74399575e0.jpg', 'http://www.freixenet.com.ar', -34.4713, -58.5555),
                (2, 'Bodega Flechas De Los Andes', 'Bodega ubicada en Mendoza con enfoque en vinos de alta gama.', 'Historia de Flechas De Los Andes.', 6, 1723075200000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/159_b05db0957d4f6eeac441c8f6358d17a2.jpg', 'http://www.flechasdelosandes.com.ar', -32.8898, -68.8458),
                (3, 'Bodega La Caroyense', 'Bodega tradicional de Córdoba.', 'Historia de La Caroyense.', 3, 1705795200000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/155_334fb1dd99c20e75fe0df768f9198cf5.jpg', 'http://www.bodegalacaroyense.com.ar', -31.0441, -64.1081),
                (4, 'Bodega Domaine Le Billoud', 'Bodega boutique en Mendoza.', 'Historia de Domaine Le Billoud.', 12, 1704499200000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/37_594412dbcbeee4e681b4c0288add8415.jpg', 'http://www.domainelebilloud.com', -32.8975, -68.835),
                (5, 'Bodega Lockton Argentina', 'Consultora de seguros y riesgos.', 'Historia de Lockton Argentina.', 12, 1704067200000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/282_519370a8481d96663b06f19bc1db05d6.jpg', NULL, -34.5625, -58.4428),
                (6, 'Bodega L&L Servicios Internacionales', 'Servicios de importación y exportación.', 'Historia de L&L Servicios Internacionales.', 1, 1710489600000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/271_5aefbb8454d0657fa18c2a84c6c760cf.png', NULL, -32.8899, -68.8456),
                (7, 'Bodega Luis Segundo Correas', 'Bodega con tradición familiar.', 'Historia de Luis Segundo Correas.', 12, 1706227200000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/29_608812743f3e95b3a9a0238bada1181c.jpg', 'http://www.bodegacorreas.com.ar', -32.8898, -68.8458),
                (8, 'Bodega Aaron Tubert', 'Pequeña bodega familiar.', 'Historia de Aaron Tubert.', 3, 1702944000000, 'https://www.bodegasdeargentina.org/wp-content/uploads/partners/193_42d2e3a3f09caa42233d10c04931c5d0.jpg', NULL, -32.8898, -68.8458),
                (9, 'Bodega Dante Robino', 'Bodega en Buenos Aires.', 'Historia de Dante Robino.', 12, 1707177600000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/193_42d2e3a3f09caa42233d10c04931c5d0.jpg', 'http://www.bodegadanterobino.com', -34.6022, -58.378),
                (10, 'Bodega Lagarde', 'Bodega con vinos premiados.', 'Historia de Lagarde.', 12, 1709251200000, 'https://www.bodegasdeargentina.org//wp-content/uploads/partners/27_772be6bee3c1b94099c4c5311fdd0b5e.jpg', 'http://www.rocawines.com', -32.9831, -68.8756);

INSERT INTO tipo_uva (nombre, descripcion) VALUES
                   ('Tempranillo', 'Variedad de uva tinta utilizada para hacer vino en España y Portugal.'),
                   ('Sauvignon Blanc', 'Variedad de uva verde utilizada para hacer vino blanco.'),
                   ('Garnacha', 'Variedad de uva tinta utilizada para hacer vino en España, Francia y otras regiones.'),
                   ('Cabernet Sauvignon', 'Variedad de uva tinta utilizada para hacer vino en todo el mundo.'),
                   ('Merlot', 'Variedad de uva tinta utilizada para hacer vino en todo el mundo.'),
                   ('Chardonnay', 'Variedad de uva verde utilizada para hacer vino blanco en todo el mundo.'),
                   ('Syrah', 'Variedad de uva tinta utilizada para hacer vino en todo el mundo.'),
                   ('Malbec', 'Variedad de uva tinta utilizada para hacer vino en Argentina y otros países.');

INSERT INTO maridaje (nombre, descripcion) VALUES
                   ('Carnes rojas y quesos curados', 'Ideal para acompañar carnes rojas y quesos curados.'),
                   ('Pescados y mariscos', 'Perfecto para acompañar pescados y mariscos frescos.'),
                   ('Pasta con salsas ricas', 'Excelente para maridar con pastas acompañadas de salsas ricas y cremosas.'),
                   ('Quesos suaves y frutos secos', 'Ideal para acompañar quesos suaves y una variedad de frutos secos.');

INSERT INTO varietal (descripcion, porcentaje_composicion, tipo_uva_id) VALUES
                    ('Tempranillo', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Tempranillo')),
                    ('Sauvignon Blanc', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Sauvignon Blanc')),
                    ('Garnacha', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Garnacha')),
                    ('Cabernet Sauvignon', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Cabernet Sauvignon')),
                    ('Merlot', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Merlot')),
                    ('Chardonnay', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Chardonnay')),
                    ('Syrah', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Syrah')),
                    ('Malbec', 100, (SELECT id FROM tipo_uva WHERE nombre = 'Malbec'));

-- Inserción de vinos en la tabla `vino`
INSERT INTO vino (aniada, img_etiqueta, bodega_id, nombre, nota_de_cata, precio, varietal_id) VALUES
                  (2019, 'https://images.vivino.com/thumbs/HvooITLjRHuzhzzI6XqnGg_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Freixenet Argentina'),
                   'Vino Tinto Reserva', 'Aromas a frutas rojas y especias, taninos suaves y final persistente.', 25.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Tempranillo')),

                  (2020, 'https://images.vivino.com/thumbs/BSrZZl-kTT-5HlQwX2Ayug_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Flechas De Los Andes'),
                   'Vino Blanco Joven', 'Notas cítricas y florales, fresco y ligero.', 12.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Sauvignon Blanc')),

                  (2018, 'https://images.vivino.com/thumbs/wKyTV_eTTleJniQ2gGebDQ_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega La Caroyense'),
                   'Vino Rosado Semidulce', 'Aromas a frutos rojos, dulzor equilibrado y refrescante.', 15.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Garnacha')),

                  (2017, 'https://images.vivino.com/thumbs/FjglrW43STyXnzcl8kqYSw_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Domaine Le Billoud'),
                   'Vino Tinto Gran Reserva', 'Intenso y estructurado, con notas a frutos secos y madera.', 35.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Cabernet Sauvignon')),

                  (2016, 'https://images.vivino.com/thumbs/HvooITLjRHuzhzzI6XqnGg_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega L&L Servicios Internacionales'),
                   'Vino Tinto Crianza', 'Equilibrado y estructurado, con aromas a frutas maduras y especias.', 18.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Merlot')),

                  (2019, 'https://images.vivino.com/thumbs/BSrZZl-kTT-5HlQwX2Ayug_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Lockton Argentina'),
                   'Vino Blanco Barrica', 'Elegante y complejo, con toques de madera y frutas blancas.', 28.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Chardonnay')),

                  (2017, 'https://images.vivino.com/thumbs/wKyTV_eTTleJniQ2gGebDQ_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Luis Segundo Correas'),
                   'Vino Rosado Seco', 'Fresco y afrutado, con notas a frutas rojas y buena acidez.', 14.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Tempranillo')),

                  (2018, 'https://images.vivino.com/thumbs/FjglrW43STyXnzcl8kqYSw_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Aaron Tubert'),
                   'Vino Tinto Joven', 'Frutal y fácil de beber, con taninos suaves y final afrutado.', 9.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Syrah')),

                  (2021, 'https://images.vivino.com/thumbs/HvooITLjRHuzhzzI6XqnGg_pb_x960.png',
                   (SELECT id FROM bodega WHERE nombre = 'Bodega Dante Robino'),
                   'Vino Tinto Roble', 'Intenso y especiado, con toques de madera y frutos negros.', 22.99,
                   (SELECT id FROM varietal WHERE descripcion = 'Malbec'));

-- Inserción de relaciones en la tabla `vino_maridaje`
INSERT INTO vino_maridaje (vino_id, maridaje_id) VALUES
                 ((SELECT id FROM vino WHERE nombre = 'Vino Tinto Reserva' AND aniada = 2019),
                  (SELECT id FROM maridaje WHERE nombre = 'Carnes rojas y quesos curados')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Blanco Joven' AND aniada = 2020),
                  (SELECT id FROM maridaje WHERE nombre = 'Pescados y mariscos')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Rosado Semidulce' AND aniada = 2018),
                  (SELECT id FROM maridaje WHERE nombre = 'Pasta con salsas ricas')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Tinto Gran Reserva' AND aniada = 2017),
                  (SELECT id FROM maridaje WHERE nombre = 'Pasta con salsas ricas')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Tinto Crianza' AND aniada = 2016),
                  (SELECT id FROM maridaje WHERE nombre = 'Carnes rojas y quesos curados')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Blanco Barrica' AND aniada = 2019),
                  (SELECT id FROM maridaje WHERE nombre = 'Pescados y mariscos')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Rosado Seco' AND aniada = 2017),
                  (SELECT id FROM maridaje WHERE nombre = 'Quesos suaves y frutos secos')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Tinto Joven' AND aniada = 2018),
                  (SELECT id FROM maridaje WHERE nombre = 'Quesos suaves y frutos secos')),

                 ((SELECT id FROM vino WHERE nombre = 'Vino Tinto Roble' AND aniada = 2021),
                  (SELECT id FROM maridaje WHERE nombre = 'Carnes rojas y quesos curados'));


-- Inserts para la tabla usuario y enofilo
INSERT INTO usuario (nombre, password) VALUES ('juang', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Gonzalez', 'Juan', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'juang'));

INSERT INTO usuario (nombre, password) VALUES ('mariap', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Perez', 'Maria', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'mariap'));

INSERT INTO usuario (nombre, password) VALUES ('pedrog', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Gomez', 'Pedro', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'pedrog'));

INSERT INTO usuario (nombre, password) VALUES ('anar', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Rodriguez', 'Ana', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'anar'));

INSERT INTO usuario (nombre, password) VALUES ('luciaf', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Fernandez', 'Lucia', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'luciaf'));

INSERT INTO usuario (nombre, password) VALUES ('carlosl', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Lopez', 'Carlos', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'carlosl'));

INSERT INTO usuario (nombre, password) VALUES ('sofiam', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Martinez', 'Sofia', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'sofiam'));

INSERT INTO usuario (nombre, password) VALUES ('jorges', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Sanchez', 'Jorge', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'jorges'));

INSERT INTO usuario (nombre, password) VALUES ('valeriar', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Romero', 'Valeria', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'valeriar'));

INSERT INTO usuario (nombre, password) VALUES ('migueld', 'password123');
INSERT INTO enofilo (apellido, nombre, imagen_perfil, usuario_id)
VALUES ('Diaz', 'Miguel', 'https://randomuser.me/api/portraits', (SELECT id FROM usuario WHERE nombre = 'migueld'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega Freixenet Argentina'), (SELECT id FROM enofilo WHERE nombre = 'Juan' AND apellido = 'Gonzalez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega La Caroyense'), (SELECT id FROM enofilo WHERE nombre = 'Maria' AND apellido = 'Perez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega Aaron Tubert'), (SELECT id FROM enofilo WHERE nombre = 'Pedro' AND apellido = 'Gomez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega Freixenet Argentina'), (SELECT id FROM enofilo WHERE nombre = 'Ana' AND apellido = 'Rodriguez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega L&L Servicios Internacionales'), (SELECT id FROM enofilo WHERE nombre = 'Lucia' AND apellido = 'Fernandez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega Aaron Tubert'), (SELECT id FROM enofilo WHERE nombre = 'Carlos' AND apellido = 'Lopez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega La Caroyense'), (SELECT id FROM enofilo WHERE nombre = 'Sofia' AND apellido = 'Martinez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega L&L Servicios Internacionales'), (SELECT id FROM enofilo WHERE nombre = 'Jorge' AND apellido = 'Sanchez'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega Freixenet Argentina'), (SELECT id FROM enofilo WHERE nombre = 'Valeria' AND apellido = 'Romero'));

INSERT INTO siguiendo (fecha_inicio, bodega_id, enofilo_id)
VALUES (1577836800000, (SELECT id FROM bodega WHERE nombre = 'Bodega La Caroyense'), (SELECT id FROM enofilo WHERE nombre = 'Miguel' AND apellido = 'Diaz'));