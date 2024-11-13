-- Tabla Usuario
CREATE TABLE Usuario (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         nombre TEXT NOT NULL,
                         password TEXT NOT NULL
);

-- Tabla Bodega
CREATE TABLE Bodega (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT NOT NULL,
                        descripcion TEXT,
                        historias TEXT,
                        periodoActualizacion INTEGER,
                        ultimaActualizacion DATE,
                        imgLogoBodega TEXT,
                        coordenadas TEXT,
                        sitioWeb TEXT
);

-- Tabla Enofilo
CREATE TABLE Enofilo (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         apellido TEXT NOT NULL,
                         nombre TEXT NOT NULL,
                         imagenPerfil TEXT
);

-- Tabla Maridaje
CREATE TABLE Maridaje (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          descripcion TEXT,
                          nombre TEXT NOT NULL
);

-- Tabla Siguiendo
CREATE TABLE Siguiendo (
                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                           fechaInicio DATE NOT NULL,
                           fechaFin DATE,
                           bodega_id INTEGER,
                           enofilo_id INTEGER,
                           FOREIGN KEY (bodega_id) REFERENCES Bodega(id),
                           FOREIGN KEY (enofilo_id) REFERENCES Enofilo(id)
);

-- Tabla TipoUva
CREATE TABLE TipoUva (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         descripcion TEXT,
                         nombre TEXT NOT NULL
);

-- Tabla Varietal
CREATE TABLE Varietal (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          descripcion TEXT,
                          porcentajeComposicion TEXT,
                          tipoUva_id INTEGER,
                          FOREIGN KEY (tipoUva_id) REFERENCES TipoUva(id)
);

-- Tabla Vino
CREATE TABLE Vino (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      aniada INTEGER NOT NULL,
                      imgEtiqueta TEXT,
                      nombre TEXT NOT NULL,
                      notaDeCata TEXT,
                      precio REAL NOT NULL,
                      varietal_id INTEGER,
                      bodega_id INTEGER,
                      FOREIGN KEY (varietal_id) REFERENCES Varietal(id),
                      FOREIGN KEY (bodega_id) REFERENCES Bodega(id)
);

-- Tabla intermedia para la relación muchos-a-muchos entre Vino y Maridaje
CREATE TABLE vino_maridaje (
                               vino_id INTEGER NOT NULL,
                               maridaje_id INTEGER NOT NULL,
                               PRIMARY KEY (vino_id, maridaje_id),
                               FOREIGN KEY (vino_id) REFERENCES Vino(id),
                               FOREIGN KEY (maridaje_id) REFERENCES Maridaje(id)
);
