DROP TABLE IF EXISTS Gerente CASCADE;
DROP TABLE IF EXISTS Vendedor CASCADE;
DROP TABLE IF EXISTS Jefe_Taller CASCADE;
DROP TABLE IF EXISTS Inventario CASCADE;
DROP TABLE IF EXISTS Cotizacion CASCADE;
DROP TABLE IF EXISTS Venta CASCADE;
DROP TABLE IF EXISTS Orden_Trabajo CASCADE;
DROP TABLE IF EXISTS Sede CASCADE;
DROP TABLE IF EXISTS SuperUsuario CASCADE;


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
  lote int NOT NULL ,
  catidad_Lote int NOT NULL,
  id_Jefe VARCHAR(100) NOT NULL, 

  PRIMARY KEY (id_Producto),
  FOREIGN KEY (id_Jefe) REFERENCES Jefe_Taller(id_Jefe)
);


--
-- TABLE: Venta
-- 
--  

CREATE TABLE Venta (
  id_Factura VARCHAR(100) NOT NULL,
  nombre_Cliente VARCHAR(100) NOT NULL ,
  telefono_Cliente VARCHAR(100) NOT NULL ,
  cedula_Cliente int NOT NULL ,
  valor_Venta float NOT NULL ,
  descripcion_Venta VARCHAR(100) NOT NULL,
  id_Vendedor VARCHAR(100) NOT NULL,

  PRIMARY KEY (id_Factura),
  FOREIGN KEY (id_Vendedor) REFERENCES Vendedor(id_Vendedor) 
);



--
-- TABLE: Orden_Trabajo
-- 
--  

CREATE TABLE Orden_Trabajo (
  id_Orden VARCHAR(100) NOT NULL,
  nombre_Cliente VARCHAR(100) NOT NULL ,
  id_Cliente VARCHAR(100) NOT NULL,
  telefono_Cliente VARCHAR(100) NOT NULL ,
  valor_Orden float NOT NULL ,
  descripcion_Orden VARCHAR(100) NOT NULL ,
  estado_Orden VARCHAR(100) NOT NULL ,
  fecha_Entrega VARCHAR(100) NOT NULL,
  id_Jefe VARCHAR(100) NOT NULL,

  PRIMARY KEY (id_Orden),
  FOREIGN KEY (id_Jefe) REFERENCES Jefe_Taller(id_Jefe) 
);


--
-- TABLE: Cotizacion
-- 
--  

CREATE TABLE Cotizacion (
  id_Cotizacion VARCHAR(100) NOT NULL,
  nombre_Producto VARCHAR(100) NOT NULL ,
  valor_Unitario float NOT NULL ,
  cantidad int NOT NULL ,
  descripcion_Producto VARCHAR(100) NOT NULL ,
  nombre_Empresa VARCHAR(100) NOT NULL ,
  telefono_Empresa VARCHAR(100) NOT NULL ,
  direccion_Empresa VARCHAR(100) NOT NULL,
  id_Vendedor VARCHAR(100) NOT NULL,

  PRIMARY KEY (id_Cotizacion),
  FOREIGN KEY (id_Vendedor) REFERENCES Vendedor(id_Vendedor) 
);