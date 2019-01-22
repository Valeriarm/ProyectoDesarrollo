/*
 * To changetFhis license header, choose License Headers in Project Properties.
 * To changetFhis template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Clase tentativa para el manejo y conexión a la base de datos
 * @author Cristian Perafan
 */
public class DBConnection {
    
    //----------------------------------------------------------------------
    // Atributos necesarios para conectar la base de datos
    //----------------------------------------------------------------------
    
    //Usuario de la base de datos en postgresql
    private String dBUser = "desarrollo";
    private String dBPassword = "desarrollo";
    //puerto
    private String port = "5433";
    //Nombre de la base de datos
    private String dBName = "muebles_XYZ";
    //Dirección del host de la base de datos
    private String host = "localhost";
    //URL para la conexion en postgresql
    private String url = "jdbc:postgresql://"+host+":"+port+"/"+dBName;
    
    //Se usa para lograr la conexión
    private Connection connection;
    //Se usa para crear las sentencias en sql
    private Statement st;
    //Se usa para recibir los resultados de la peticion sql
    private ResultSet rs;
    //Se usa para escribir la sentencia sql en un string
    private String sql; 
    
    /**
     * Constructor
     */
    public DBConnection(){
        //readSU();        
        //System.out.println(createManager("12345","Cristian Perafan","1144070010", Manager.GERENTE,"cristian@hotmail.com", 0,"Calle 6","3117307659",455000,"02/05/1994","03142571","29/10/2018"));
        //System.out.println(updateManager("12345","Cristian Perafan","1144070010", Manager.GERENTE,"cristian@hotmail.com", 0,"Calle 66 1 bis 61","3206778112",455000,"02/05/1994","03142571"));
        //System.out.println(createForeman("23456","Valeria Rivera","114425875", User.JEFE_DE_TALLER,"valeria@hotmail.com", 1,"Carrera 66","3117307659",500000,"12/11/1998","14131444211","29/10/2018","1234"));
        //System.out.println(updateForeman("2345","Mateo Gregory","1144070011", Manager.JEFE_DE_TALLER,"matgre@hotmail.com", 0,"Same","31123455",1000,"05/10/1999","03142571","1234"));
        //System.out.println(createForeman("1234","Mateo Gregory","1144070011", Manager.GERENTE,"matgre@hotmail.com", 0,"Same","3117307659  ",1000,"05/10/1999","03142571","1234"));
        //System.out.println(createSeller("345","Melissa Fuentes","114402571", User.VENDEDOR,"melissa@hotmail.com", 1,"Av 2 45 12","30025584",80000,"02/07/1998","12321434","29/10/2018","12345"));
        //System.out.println(updateSeller("345","Juan Felipe Gil","114402571", User.VENDEDOR,"melissa@hotmail.com", 1,"Av 2 45 12","30025584",80000,"02/07/1998","12321434","1234"));
        
        
        Seller g = readSellerById("345");
        if(g == null){
            System.out.println("No existe");
        }else{
            System.out.println(g.getNombre());
            System.out.println(g.getCedula());
        }
    }
    
    /**
     * Este método hace la conexión a la base de datos
     * Lo hago aquí porque me resulta mas cómodo hacer el llamado solo una vez, que estarlo haciendo todo el tiempo
     * en cada método.
     */
    public void connect(){
        try{            
            //Esto crea una clase de tipo driver necesaria para la conexion
            Class.forName("org.postgresql.Driver");
            //Al atributo de conexion se le asigna esta linea, se le pasa la url, el usuario y la contraseña de la BD
            connection = DriverManager.getConnection(url, dBUser, dBPassword);
            //A partir de la conexion se pueden crear las sentencias
            st = connection.createStatement();
        }catch(Exception e){
            //Esto en caso de error imprime el mensaje, luego se puede pasar a través de una ventana informativa
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
    }
    
    /**
     * En este metodo hacemos el llamado de los datos de todos los super usuarios
     */
    public void readSU(){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        sql = "SELECT * FROM Superusuario";
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                String user = rs.getString("id_Superusuario");
                String contra = rs.getString("contrasenia");
                
                System.out.println(user + "," + contra);
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
    }  
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////CRUD GERENTE/////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    public String createManager(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, boolean habilitado){
        connect();
        sql = "SELECT id_Gerente FROM Gerente WHERE id_Gerente = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El gerente con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO Gerente VALUES ('"+id+"','','"+nombre+"','"+cedula+"','"+cargo+"','"+correo+"','"+genero+
                        "','"+direccion+"','"+telefono+"','"+salario+"','"+fechaNacimiento+"','"+cuentaBancaria+"','"
                        +fechaRegistro+"','" +nombreUsuario+"','"+contrasenia+"','"+true+"')";
                
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Gerente agregado con éxito";
    }
    
    /**
     * Lectura de la base de datos para los gerentes
     * @param id
     * @return null si no encuentra el gerente
     */
    public Manager readManagerById(String id){
        connect();
        sql = "SELECT * FROM Gerente WHERE id_Gerente = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String nombre = rs.getString("nombre_Gerente");
                String cedula = rs.getString("cedula");
                String cargo = rs.getString("cargo");
                String correo = rs.getString("e_mail");
                int genero = Integer.parseInt(rs.getString("genero"));
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                float salario = rs.getFloat("salario");
                String fechaNa = rs.getString("fecha_Nacimiento");
                String cuentaBanc = rs.getString("cuenta_bancaria");
                String fechaReg = rs.getString("fecha_registro");
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                boolean habilitado = rs.getBoolean("habilitado");
                
                
                Manager gerente = new Manager(id, nombre, cedula, cargo, correo, 
                genero, direccion, telefono, salario, fechaNa, 
                cuentaBanc, fechaReg, nombreUsuario, contrasenia, habilitado);
                
                rs.close();
                st.close();
                connection.close();
                
                return gerente;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
    
    //TODO
    public String updateManager(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, boolean habilitado){
        connect();
        sql = "SELECT id_Gerente FROM Gerente WHERE id_Gerente = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Gerente SET nombre_Gerente = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', e_mail = '"+correo+"',"
                        +" genero = '"+genero+"', direccion = '"+direccion+"', telefono = '"+telefono+"', salario = "+salario+", fecha_Nacimiento = '"+fechaNacimiento+
                        "', cuenta_Bancaria = '"+cuentaBancaria+"', fecha_Registro = '"+fechaRegistro+", nombre_Usuario = "+nombreUsuario+", contrasenia = "+contrasenia+
                        ", habilitado = "+habilitado+"' WHERE id_gerente = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El gerente con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Gerente actualizado con éxito";
    }   
    
    public String deleteManagerById(String id){
        connect();
        sql = "SELECT id_Gerente FROM Gerente WHERE id_gerente = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Gerente WHERE id_Gerente = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El gerente fue borrado exitosamente";
            }else{              
                return "El gerente con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CRUD JEFE DE TALLER/////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public String createForeman(String idJefe, String contrasenia, String nombreUsuario, String nombreJefe, String cedula, 
            String cargo, String telefono, String direccion, int genero, String fechaNacimiento, String correo, float salario,
            String cuentaBancaria, String fechaRegistro, String idGerente){
        connect();
        sql = "SELECT id_Jefe FROM Jefe_Taller WHERE id_Jefe = '"+idJefe+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El jefe de taller con el id "+idJefe+" ya existe";
            }else{                
                sql = "INSERT INTO Jefe_Taller VALUES ('"+idJefe+"','','"+contrasenia+"','"+nombreUsuario+"','"+nombreJefe+"','"+cedula+"','"+cargo+
                        "','"+telefono+"','"+direccion+"','"+genero+"','"+fechaNacimiento+"','"+correo+"','"
                        +salario+"','" +cuentaBancaria+"','"+fechaRegistro+"','"+idGerente+"')";
                
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Jefe de taller agregado con éxito";
    }
    
    
    public Foreman readForemanById(String id){
        connect();
        sql = "SELECT * FROM Jefe_Taller WHERE id_Jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String nombre = rs.getString("nombre_jefe");
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                String cedula = rs.getString("cedula");
                String cargo = rs.getString("cargo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                int genero = Integer.parseInt(rs.getString("genero"));
                String fechaNa = rs.getString("fecha_nacimiento");
                String email = rs.getString("e_mail");
                float salario = rs.getFloat("salario");
                String cuentaBanc = rs.getString("cuenta_bancario");
                String fechaReg = rs.getString("fecha_registro");
                String managerId = rs.getString("id_gerente");
                
                Foreman jefe = new Foreman(id, contrasenia, nombreUsuario, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, 
                cuentaBanc, fechaReg, managerId);
                
                rs.close();
                st.close();
                connection.close();
                
                return jefe;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }

    
    public String updateForeman(String id, String contrasenia, String nombreUsuario, String nombreJefe, String cedula, 
            String cargo, String telefono, String direccion, int genero, String fechaNacimiento, String correo, float salario,
            String cuentaBancaria, String fechaRegistro, String idGerente){
        connect();
        sql = "SELECT id_Jefe FROM Jefe_Taller WHERE id_jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Jefe_Taller SET nombre_Jefe = '"+nombreJefe+"', cedula = '"+cedula+"', cargo = '"+cargo+"', "
                        + "telefono = '"+telefono+"'," +" genero = '"+genero+"', fecha_Nacimiento = '"+fechaNacimiento+"', "
                        + "e_mail = '"+correo+"', salario = "+salario+", cuenta_Bancaria = '"+cuentaBancaria+
                        "', direccion = '"+direccion+"', id_Gerente = '"+idGerente+"', contarasenia = '"+contrasenia+
                        "', nombre_Usuario = '"+nombreUsuario+"', fecha_Registro = '"+fechaRegistro+"' WHERE id_jefe = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El jefe de taller con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Jefe de taller actualizado con éxito";
    }
    
    public String deleteForemanById(String id){
        connect();
        sql = "SELECT id_Jefe FROM Jefe_Taller WHERE id_Jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Jefe_Taller WHERE id_Jefe = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El jefe de taller fue borrado exitosamente";
            }else{              
                return "El jefe de taller con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD VENTA///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
     public String createSeller(String id, String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero, 
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, String managerId, boolean habilitado){
        connect();
        sql = "SELECT id_Vendedor FROM Vendedor WHERE id_Vendedor = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El vendedor con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO Vendedor VALUES ('"+id+"','','"+nombre+"','"+cedula+"','"+cargo+"','"+telefono+"','"+direccion+
                        "','"+genero+"','"+fechaNacimiento+"','"+correo+"','"+salario+"','"+cuentaBancaria+"','"
                        +fechaRegistro+"','"+nombreUsuario+"','"+contrasenia+"','"+managerId+"','"+habilitado+"')";
                
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Vendedor agregado con éxito";
    }
     
    public Seller readSellerById(String id){
        connect();
        sql = "SELECT * FROM Vendedor WHERE id_Vendedor = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String nombre = rs.getString("nombre_Vendedor");
                String cedula = rs.getString("cedula");
                String cargo = rs.getString("cargo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                int genero = Integer.parseInt(rs.getString("genero"));
                String fechaNa = rs.getString("fecha_Nacimiento");
                String email = rs.getString("e_mail");
                float salario = rs.getFloat("salario");
                String cuentaBanc = rs.getString("cuenta_Bancaria");
                String fechaReg = rs.getString("fecha_Registro");
                String managerId = rs.getString("id_Gerente");
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                boolean habilitado = rs.getBoolean("habilitado");
                
                Seller vendedor = new Seller(id, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, cuentaBanc, fechaReg, 
                nombreUsuario, contrasenia, managerId, habilitado);
                
                rs.close();
                st.close();
                connection.close();
                
                return vendedor;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
     
    public String updateSeller(String id, String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero, 
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, String managerId, boolean habilitado){
        connect();
        sql = "SELECT id_Vendedor FROM Vendedor WHERE id_Vendedor = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Vendedor SET nombre_Vendedor = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', telefono = '"+telefono+"',"
                        +" genero = '"+genero+"', fecha_Nacimiento = '"+fechaNacimiento+"', e_mail = '"+correo+"', salario = "+salario+", cuenta_Bancaria = '"+cuentaBancaria+
                        "', direccion = '"+direccion+"', id_Gerente = '"+managerId+"', fecha_Registro = '"+fechaRegistro+"'"
                        + ", nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+"', habilitado = '"+habilitado+"' WHERE id_vendedor = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El vendedor con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Vendedor actualizado con éxito";
    }
    
    public String deleteSeller(String id){
        connect();
        sql = "SELECT id_Vendedor FROM Vendedor WHERE id_Vendedor = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Vendedor WHERE id_Vendedor = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El Vendedor fue borrado exitosamente";
            }else{              
                return "El Vendedor con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";        
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////CRUD SALE//////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
     
    public String createSale(String id, String nombreCliente, String telefonoCliente, 
           String cedulaCliente, int valorVenta, String descripcionVenta, String idVendedor){
        
        connect();
        sql = "SELECT id_Factura FROM Venta WHERE id_Factura = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La venta con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO Venta VALUES ('"+id+"','','"+nombreCliente+"','"+telefonoCliente+"','"+cedulaCliente+"','"
                        +valorVenta+"','"+descripcionVenta+"','"+idVendedor+"')";
                
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Venta agregada con éxito";
        
        
    }
    
    public Sale readSaleById(String id){
        connect();
        sql = "SELECT * FROM Venta WHERE id_Factura = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String nombreCliente = rs.getString("nombre_cliente");
                String telefonoCliente = rs.getString("telefono_Cliente");
                String cedulaCliente = rs.getString("cedula_Cliente");
                int valorVenta = Integer.parseInt(rs.getString("valor_Venta"));
                String descripcionVenta = rs.getString("descripcion_Venta");
                String idVendedor = rs.getString("id_Vendedor");
                
                Sale venta = new Sale(id, nombreCliente, telefonoCliente, cedulaCliente, valorVenta, 
                descripcionVenta, idVendedor);
                
                rs.close();
                st.close();
                connection.close();
                
                return venta;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
    
    public String updateSaleById(String id, String nombreCliente, String telefonoCliente, 
           String cedulaCliente, int valorVenta, String descripcionVenta, String idVendedor){
        connect();
        sql = "SELECT id_Factura FROM venta WHERE id_Factura = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Venta SET nombre_Cliente = '"+nombreCliente+"', tefelono_Cliente = '"+telefonoCliente+"', cedula_Cliente = '"+cedulaCliente+
                        "', valor_Venta = '"+valorVenta+"', descripcion_Venta = '"+descripcionVenta+"',id_Vendedor = '"+idVendedor+"'  WHERE id_Factura = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "La venta con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Venta actualizada con éxito";
    }
    
    public String deleteSale(String id){
        connect();
        sql = "SELECT id_Factura FROM Venta WHERE id_Factura = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Venta WHERE id_Factura = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "La venta fue borrada exitosamente";
            }else{              
                return "La venta con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";        
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD ORDEN///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public String createWorkOrder(String id, String nombreCliente, int costo, int esCliente, 
           String descripcionOrden, String telefonoCliente, String estado, String fechaEntrega, String idJefe){
        
        connect();
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La orden de trabajo con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO Orden_Trabajo VALUES ('"+id+"','','"+nombreCliente+"','"+costo+"','"+esCliente+"','"+descripcionOrden+"','"
                        +telefonoCliente+"','"+estado+"','"+fechaEntrega+"','"+idJefe+"')";
                
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Orden de Trabajo agregada con éxito";
    }
    
    public WorkOrder readWorkOrderById(String id){
        connect();
        sql = "SELECT * FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idOrden = rs.getString("id_Orden");
                String nombreCliente = rs.getString("nombre_Cliente");
                String idCliente = rs.getString("id_Cliente");
                String telefonoCliente = rs.getString("telefono_Cliente");
                int valorOrden = Integer.parseInt(rs.getString("valor_Orden"));
                int esCliente = Integer.parseInt(rs.getString("es_Cliente"));                
                String descripcionOrden = rs.getString("descripcion_Orden");
                String estadoOrden = rs.getString("estado_Orden");
                String fechaEntrega = rs.getString("fecha_Entrega");
                String idJefe = rs.getString("id_Jefe");
                
                WorkOrder orden = new WorkOrder(idOrden, nombreCliente, idCliente, telefonoCliente,
                                    valorOrden, esCliente, descripcionOrden, estadoOrden, fechaEntrega, idJefe);
                
                rs.close();
                st.close();
                connection.close();
                
                return orden;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
    
    public String updateWorkOrderById(String id, String nombreCliente, int costo, int esCliente, 
           String descripcionOrden, String telefonoCliente, String estado, String fechaEntrega, String idJefe){
        
        connect();
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Orden_Trabajo SET nombre_Cliente = '"+nombreCliente+"', valor_Orden = '"+costo+"', es_Cliente = '"+esCliente+
                        "', descripcion_Orden = '"+descripcionOrden+"', telefono_Cliente = '"+telefonoCliente+"', "
                        + "estado = '"+estado+ "fecha_Entrega = '"+fechaEntrega+ "id_Jefe = '"+idJefe+"' WHERE id_Orden = '"+id+"'";
                
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "La orden con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Orden actualizada con éxito";           
    }
    
    public String deleteWorkOrder(String id){
        connect();
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "La orden fue borrada exitosamente";
            }else{              
                return "La orden con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";        
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD INVENTARIO///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    public String createInventory(String id, String nombreProducto, float valorUnitario, 
            String descripcion, int lote, int cantidadLote, String idJefe){
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El Producto con el id "+id+" ya existe";
                
            }else{ 
                sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
                rs = st.executeQuery(sql);
                if(rs.next()){
                    sql = "INSERT INTO Inventario VALUES ('"+id+"','"+nombreProducto+"','"+valorUnitario+"','"+descripcion+"','"+lote+"','"+cantidadLote+"','"+idJefe+"')";                
                    st.executeUpdate(sql);
                    rs.close();
                    st.close();
                    connection.close();
                }else{
                    return "El producto con el id "+id+" no existe";
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Producto agregado con éxito";
    }
    
    public Inventory readInventoryId(String id){
        connect();
        sql = "SELECT * FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idProducto = rs.getString("id_Producto");
                String nombreProducto = rs.getString("nombre_Producto");
                float valorUnitario = rs.getFloat("valor_Unitario");
                String descripcion = rs.getString("descripcion_Producto");
                int lote = rs.getInt("lote");
                int cantidadLote = rs.getInt("cantidad_Lote");
                String idJefe = rs.getString("id_Jefe");
                
                Inventory inventario = new Inventory(idProducto, nombreProducto, valorUnitario, 
                        descripcion, lote, cantidadLote, idJefe);
                
                rs.close();
                st.close();
                connection.close();
                
                return inventario;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }

    public String updateInventory(String id, String nombreProducto, float valorUnitario, 
            String descripcion, int lote, int cantidadLote){
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Inventario SET nombre_Producto = '"+nombreProducto+"', valor_Unitario = '"+valorUnitario+
                        "', descripcion_Producto = '"+descripcion+"', lote = '"+lote+"',"+" cantidad_Lote = '"+cantidadLote+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El producto con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Inventario actualizado con éxito";
    }
    
    public String deleteInventoryId(String id){
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Inventario WHERE id_Producto = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El producto fue borrado exitosamente";
            }else{              
                return "El producto con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
        ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD COTIZACION///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    public String createQuotation(String id, String nombre_Producto, float valor_Unitario, int cantidad,
                                    String descripcion_Producto, String nombreEmpresa, String telefono,
                                    String direccion, String id_Vendedor){
        connect();
        sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La Cotizacion con el id "+id+" ya existe";
                
            }else{ 
                sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
                rs = st.executeQuery(sql);
                if(rs.next()){
                    sql = "INSERT INTO Cotizacion VALUES ('"+id+"','"+nombre_Producto+"','"+valor_Unitario+"','"+cantidad+"','"+descripcion_Producto+"','"+nombreEmpresa+"','"+telefono+"','"+direccion+"','"+id_Vendedor+"')";                
                    st.executeUpdate(sql);
                    rs.close();
                    st.close();
                    connection.close();
                }else{
                    return "La Cotizacion con el id "+id+" no existe";
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Cotizacion agregado con éxito";
    }
    
    public Quotation readQuotationId(String id){
        connect();
        sql = "SELECT * FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idCotizacion = rs.getString("id_Cotizacion");
                String nombreProducto = rs.getString("nombre_Producto");
                float valorUnitario = rs.getFloat("valor_Unitario");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion_Producto");
                String nombreEmpresa = rs.getString("nombre_Empresa");
                String telefonoEmpresa = rs.getString("telefono_Empresa");
                String direccionEmpresa = rs.getString("direccion_Empresa");
                String idVendedor = rs.getString("id_Vendedor");
                
                Quotation cotizacion = new Quotation(idCotizacion, nombreProducto, valorUnitario, 
                        cantidad, descripcion, nombreEmpresa, telefonoEmpresa, direccionEmpresa, idVendedor);
                
                rs.close();
                st.close();
                connection.close();
                
                return cotizacion;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }

    public String updateQuotation(String id, String nombre_Producto, float valor_Unitario, int cantidad,
                                    String descripcion_Producto, String nombreEmpresa, String telefono,
                                    String direccion, String id_Vendedor){
        connect();
        sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Cotizacion SET nombre_Producto = '"+nombre_Producto+"', valor_Unitario = '"+valor_Unitario+
                        "', descripcion_Producto = '"+descripcion_Producto+"', cantidad = '"+cantidad+"',"+" nombre_Empresa = '"+nombreEmpresa+"'"
                        +" telefono_Empresa = '"+telefono+"'"+" direccion_Empresa = '"+direccion+"'"+" id_Vendedor = '"+id_Vendedor+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "La Cotizacion con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Cotizacion actualizada con éxito";
    }
    
    public String deleteQuotationId(String id){
        connect();
        sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "La Cotizacion fue borrada exitosamente";
            }else{              
                return "La Cotizacion con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD SEDE/////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
        public String createSede(String id, String nombreSede, String direccion, String fechaCreacion,
                                    String fechaFinalizacion, String idGerente){
        connect();
        sql = "SELECT id_Sede FROM Sede WHERE id_Sede = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La Sede con el id "+id+" ya existe";
                
            }else{ 
                sql = "SELECT id_Sede FROM Sede WHERE id_Sede = '"+id+"'";
                rs = st.executeQuery(sql);
                if(rs.next()){
                    sql = "INSERT INTO Sede VALUES ('"+id+"','"+nombreSede+"','"+direccion+"','"+fechaCreacion+"','"+fechaFinalizacion+"','"+idGerente+"')";                
                    st.executeUpdate(sql);
                    rs.close();
                    st.close();
                    connection.close();
                }else{
                    return "La Sede con el id "+id+" no existe";
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Sede agregada con éxito";
    }
    
    public Sede readSedenId(String id){
        connect();
        sql = "SELECT * FROM Sede WHERE id_Sede = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idSede = rs.getString("id_Sede");
                String nombreSede = rs.getString("nombre_Sede");
                String direccion = rs.getString("direccion");
                String fechaCreacion = rs.getString("fecha_Creacion");
                String fechaFinalizacion = rs.getString("fecha_Finalizacion");
                String idGerente = rs.getString("id_Gerente");
                
                Sede sede = new Sede(idSede, nombreSede, direccion, 
                         fechaCreacion, fechaFinalizacion, idGerente);
                
                rs.close();
                st.close();
                connection.close();
                
                return sede;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }

    public String updateSede(String id, String nombreSede, String direccion, String fechaCreacion,
                             String fechaFinalizacion, String idGerente){
        connect();
        sql = "SELECT id_Sede FROM Sede WHERE id_Sede = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Sede SET nombre_Sede = '"+nombreSede+"', direccion = '"+direccion+
                        "', fecha_Creacion = '"+fechaCreacion+"', fecha_Finalizacion = '"+fechaFinalizacion+"',"+" id_Gerente = '"+idGerente+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "La Sede con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Sede actualizada con éxito";
    }
    
    public String deleteSedeId(String id){
        connect();
        sql = "SELECT id_Sede FROM Sede WHERE id_Sede = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM Sede WHERE id_Sede = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "La Sede fue borrada exitosamente";
            }else{              
                return "La Sede con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    
    public static void main(String args[]) {    
        DBConnection prueba = new DBConnection();
        prueba.readSU();
    }
    
}
