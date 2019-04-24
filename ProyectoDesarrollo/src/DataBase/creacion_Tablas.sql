DROP TABLE IF EXISTS Gerente CASCADE;
DROP TABLE IF EXISTS Vendedor CASCADE;
DROP TABLE IF EXISTS Jefe_Taller CASCADE;
DROP TABLE IF EXISTS Inventario CASCADE;
DROP TABLE IF EXISTS Cotizacion CASCADE;
DROP TABLE IF EXISTS Venta CASCADE;
DROP TABLE IF EXISTS Orden_Trabajo CASCADE;
DROP TABLE IF EXISTS Sede CASCADE;
DROP TABLE IF EXISTS SuperUsuario CASCADE;
DROP TABLE IF EXISTS Actualiza CASCADE;
DROP TABLE IF EXISTS Modifica CASCADE;
DROP TABLE IF EXISTS Consulta CASCADE;



--
-- TABLE: SuperUsuario
-- 
--  
CREATE TABLE SuperUsuario (
  id_SuperUsuario VARCHAR(100) NOT NULL,
  nombre_SuperUsuario VARCHAR (100) NOT NULL,
  cedula VARCHAR (100) NOT NULL,
  correo  VARCHAR(100) NOT NULL,
  fecha_Nacimiento VARCHAR(100) NOT NULL ,
  nombre_Usuario VARCHAR(100) NOT NULL,
  contrasenia VARCHAR(100) NOT NULL,  


  PRIMARY KEY (id_SuperUsuario)
);


--
-- TABLE: Sede
-- 
--  
CREATE TABLE Sede (
  id_Sede SERIAL,
  nombre_Sede VARCHAR(100) NOT NULL ,
  direccion VARCHAR(100) NOT NULL ,
  fecha_Creacion date NOT NULL ,
  fecha_Finalizacion date,
  habilitada boolean NOT NULL,

  PRIMARY KEY (id_Sede)
);


--
-- TABLE: Gerente
-- 
--  
CREATE TABLE Gerente (
  id_Gerente VARCHAR(100) NOT NULL,
  nombre_Gerente VARCHAR(100) NOT NULL ,
  cedula VARCHAR(100) NOT NULL,
  cargo VARCHAR(100) NOT NULL ,
  e_mail VARCHAR(100) NOT NULL ,
  genero int NOT NULL ,
  direccion VARCHAR(100) NOT NULL ,
  telefono VARCHAR(100) NOT NULL ,
  salario float NOT NULL ,
  fecha_Nacimiento date NOT NULL,
  cuenta_Bancaria VARCHAR(100) NOT NULL ,
  fecha_Registro date NOT NULL,
  nombre_Usuario VARCHAR(100) NOT NULL,
  contrasenia VARCHAR(100) NOT NULL,  
  habilitado boolean NOT NULL,
  fecha_Despido date,
  id_Sede SERIAL,

  PRIMARY KEY (id_Gerente),
  FOREIGN KEY (id_Sede) REFERENCES Sede(id_Sede)
);


--
-- TABLE: Vendedor
-- 
--  
CREATE TABLE Vendedor (
  id_Vendedor  VARCHAR(100) NOT NULL,
  nombre_Vendedor VARCHAR(100) NOT NULL ,
  cedula VARCHAR(100) NOT NULL,
  cargo VARCHAR(100) NOT NULL ,
  telefono VARCHAR(100) NOT NULL ,
  direccion VARCHAR(100) NOT NULL ,
  genero int NOT NULL ,
  fecha_Nacimiento date NOT NULL ,
  e_mail VARCHAR(100) NOT NULL ,
  salario float NOT NULL ,
  cuenta_Bancaria VARCHAR(100) NOT NULL ,
  fecha_Registro date NOT NULL,
  nombre_Usuario VARCHAR(100) NOT NULL,
  contrasenia VARCHAR(100) NOT NULL,
  habilitado boolean NOT NULL,
  fecha_Despido date,
  id_Sede SERIAL, 

  PRIMARY KEY (id_Vendedor),
  FOREIGN KEY (id_Sede) REFERENCES Sede(id_Sede)
);


--
-- TABLE: Jefe_Taller
-- 
--  
CREATE TABLE Jefe_Taller (
  id_Jefe  VARCHAR(100) NOT NULL,
  contrasenia VARCHAR(100) NOT NULL,
  nombre_Usuario VARCHAR(100) NOT NULL,
  nombre_Jefe VARCHAR(100) NOT NULL ,
  cedula  VARCHAR(100) NOT NULL,
  cargo VARCHAR(100) NOT NULL ,
  telefono VARCHAR(100) NOT NULL ,
  direccion VARCHAR(100) NOT NULL ,
  genero int NOT NULL ,
  fecha_Nacimiento date NOT NULL ,
  e_mail VARCHAR(100) NOT NULL ,
  salario float NOT NULL ,
  cuenta_Bancaria VARCHAR(100) NOT NULL ,
  fecha_Registro date NOT NULL,
  habilitado boolean NOT NULL,
  fecha_Despido date,
  id_Sede SERIAL,

  PRIMARY KEY (id_Jefe),
  FOREIGN KEY (id_Sede) REFERENCES Sede(id_Sede)
);


--
-- TABLE: Inventario
-- 
--  
CREATE TABLE Inventario (
  id_Producto VARCHAR(100) NOT NULL,
  nombre_Producto VARCHAR(100) NOT NULL ,
  valor_Unitario float NOT NULL ,
  descripcion_Producto VARCHAR(100) NOT NULL ,
  cantidad INTEGER NOT NULL,
  estado_Producto VARCHAR(100) NOT NULL,
  id_Jefe VARCHAR(100) NOT NULL,

  PRIMARY KEY (id_Producto),
  FOREIGN KEY (id_Jefe) REFERENCES Jefe_Taller(id_Jefe)
);


--
-- TABLE: Orden_Trabajo
-- 
--  
CREATE TABLE Orden_Trabajo (
    id_Orden VARCHAR(100) NOT NULL,
    especificaciones VARCHAR(100),
    estado_Orden VARCHAR(100) NOT NULL ,
    fecha_Creacion DATE NOT NULL,
    fecha_Entrega DATE,
    id_Jefe VARCHAR(100) NOT NULL,

    PRIMARY KEY (id_Orden),
    FOREIGN KEY (id_Jefe) REFERENCES Jefe_Taller(id_Jefe) 
);


--
-- TABLE: Actualiza
-- Relacion entre inventario y orden
--  
CREATE TABLE Actualiza (
    cantidad INTEGER,
    id_Orden VARCHAR (100) NOT NULL,
    id_Producto VARCHAR(100) NOT NULL,

    FOREIGN KEY (id_Producto) REFERENCES Inventario(id_Producto),
    FOREIGN KEY (id_Orden) REFERENCES Orden_Trabajo(id_Orden)
);


--
-- TABLE: Venta
-- 
--  
CREATE TABLE Venta (
  id_Factura VARCHAR(100) NOT NULL,
  nombre_Cliente VARCHAR(100) NOT NULL ,
  telefono_Cliente VARCHAR(100) NOT NULL ,
  cedula_Cliente VARCHAR(100) NOT NULL ,
  valor_Venta float NOT NULL ,
  descripcion_Venta VARCHAR(100),
  fecha_Venta date,
  id_Vendedor VARCHAR(100) NOT NULL,

  PRIMARY KEY (id_Factura),
  FOREIGN KEY (id_Vendedor) REFERENCES Vendedor(id_Vendedor) 
);


--
--TABLE: Modifica  
--Relacion entre inventario y venta
--
CREATE TABLE Modifica (
    cantidad INTEGER,
    id_Factura VARCHAR(100) NOT NULL,
    id_Producto VARCHAR(100) NOT NULL,

    FOREIGN KEY (id_Producto) REFERENCES Inventario(id_Producto),
    FOREIGN KEY (id_Factura) REFERENCES Venta(id_Factura)
);


--
-- TABLE: Cotizacion
-- 
--  
CREATE TABLE Cotizacion (
  id_Cotizacion VARCHAR(100) NOT NULL,
  nombre_Cliente VARCHAR(100) NOT NULL ,
  telefono_Cliente VARCHAR(100) NOT NULL ,
  e_mail VARCHAR(100) NOT NULL ,
  valor_Cotizacion FLOAT NOT NULL,
  fecha_Cotizacion date NOT NULL,
  id_Vendedor VARCHAR(100) NOT NULL,

  PRIMARY KEY (id_Cotizacion),
  FOREIGN KEY (id_Vendedor) REFERENCES Vendedor(id_Vendedor) 
);


--
--TABLE: Consulta
--Relacion entre cotizacion e inventario
--
CREATE TABLE Consulta (
    cantidad INTEGER,
    id_Cotizacion VARCHAR(100) NOT NULL,
    id_Producto VARCHAR(100) NOT NULL,

    FOREIGN KEY (id_Producto) REFERENCES Inventario(id_Producto),
    FOREIGN KEY (id_Cotizacion) REFERENCES Cotizacion(id_Cotizacion)
);

INSERT INTO Sede (nombre_Sede, direccion, fecha_creacion, habilitada) VALUES 
                        ('Sede1','Calle1', '2000-01-01',true);

INSERT INTO Sede (nombre_Sede, direccion, fecha_creacion, habilitada) VALUES
                        ('Sede2','Calle2', '2000-01-01',true);

INSERT INTO Sede (nombre_Sede, direccion, fecha_creacion, habilitada) VALUES 
                        ('Sede3','Calle3', '2000-01-01',true);

INSERT INTO Sede (nombre_Sede, direccion, fecha_creacion, habilitada) VALUES
                        ('Sede4','Calle4','2000-01-01',true);

INSERT INTO SuperUsuario (id_SuperUsuario, nombre_SuperUsuario, cedula, correo, fecha_nacimiento, nombre_Usuario, contrasenia ) VALUES
                                ('0','super','super','super','2000-01-01','super','super');