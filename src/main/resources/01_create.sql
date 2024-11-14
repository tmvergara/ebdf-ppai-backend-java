-- Tabla Usuario
CREATE TABLE usuario (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         nombre TEXT NOT NULL,
                         password TEXT NOT NULL
);

-- Tabla Bodega
CREATE TABLE bodega (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT NOT NULL,
                        descripcion TEXT,
                        historias TEXT,
                        periodo_actualizacion INTEGER,
                        ultima_actualizacion DATE,
                        img_logo_bodega TEXT,
                        latitud DOUBLE,
                        longitud DOUBLE,
                        sitio_web TEXT
);

-- Tabla Enofilo con FK a Usuario
CREATE TABLE enofilo (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         apellido TEXT NOT NULL,
                         nombre TEXT NOT NULL,
                         imagen_perfil TEXT,
                         usuario_id INTEGER,
                         FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabla Maridaje
CREATE TABLE maridaje (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          descripcion TEXT,
                          nombre TEXT NOT NULL
);

-- Tabla Siguiendo
CREATE TABLE siguiendo (
                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                           fecha_inicio DATE NOT NULL,
                           fecha_fin DATE,
                           bodega_id INTEGER,
                           enofilo_id INTEGER,
                           FOREIGN KEY (bodega_id) REFERENCES bodega(id),
                           FOREIGN KEY (enofilo_id) REFERENCES enofilo(id)
);

-- Tabla TipoUva
CREATE TABLE tipo_uva (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          descripcion TEXT,
                          nombre TEXT NOT NULL
);

-- Tabla Varietal
CREATE TABLE varietal (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          descripcion TEXT,
                          porcentaje_composicion TEXT,
                          tipo_uva_id INTEGER,
                          FOREIGN KEY (tipo_uva_id) REFERENCES tipo_uva(id)
);

-- Tabla Vino
CREATE TABLE vino (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      aniada INTEGER NOT NULL,
                      img_etiqueta TEXT,
                      nombre TEXT NOT NULL,
                      nota_de_cata TEXT,
                      precio REAL NOT NULL,
                      varietal_id INTEGER,
                      bodega_id INTEGER,
                      FOREIGN KEY (varietal_id) REFERENCES varietal(id),
                      FOREIGN KEY (bodega_id) REFERENCES bodega(id)
);

-- Tabla intermedia para la relación muchos-a-muchos entre Vino y Maridaje
CREATE TABLE vino_maridaje (
                               vino_id INTEGER NOT NULL,
                               maridaje_id INTEGER NOT NULL,
                               PRIMARY KEY (vino_id, maridaje_id),
                               FOREIGN KEY (vino_id) REFERENCES vino(id),
                               FOREIGN KEY (maridaje_id) REFERENCES maridaje(id)
);
