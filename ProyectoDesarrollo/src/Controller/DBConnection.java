/*
 * To changetFhis license header, choose License Headers in Project Properties.
 * To changetFhis template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;
import static com.alee.utils.MathUtils.max;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Clase tentativa para el manejo y conexión a la base de datos
 * @author Cristian Perafan
 */
public class DBConnection {
    
    //----------------------------------------------------------------------
    // Atributos necesarios para conectar la base de datos
    //----------------------------------------------------------------------
    
    //Usuario de la base de datos en postgresql
    private final String dBUser = "postgres";
    private final String dBPassword = "Marthox2299";
  

    //puerto
    private final String port = "5432";
    //Nombre de la base de datos
    private final String dBName = "muebles_XYZ";
    //Dirección del host de la base de datos
    private final String host = "localhost";
    //URL para la conexion en postgresql
    private final String url = "jdbc:postgresql://"+host+":"+port+"/"+dBName;
    
    //Se usa para lograr la conexión
    private Connection connection;
    private Statement st;
    private ResultSet rs;
    private String sql; 
    
    /**
     * Constructor
     */
    public DBConnection(){
        
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
        }catch(ClassNotFoundException | SQLException e){
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
    }
    
    //Valida si existe un super usuario con usuario user y contraseña contra
    public String validarSuper(String user, String contra){
        //Llamamos el metodo para poder conectarnos a la base de datos
        connect();
        String respuesta = null;
        sql = "SELECT * FROM Superusuario WHERE nombre_usuario = '"+user+"' AND contrasenia = '"+contra+"'";
        //try catch porque se puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);            
                       
            if(rs.next()){
                respuesta = rs.getString("id_superusuario");
            }
            
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return respuesta;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
    private String idSiguienteCotizacion(){
    
        connect();
        int idMayor = 0;
        String id = "0";
        
        try {
            //////////////////Orden de trabajo/////////////////////////////////
            sql = "SELECT id_cotizacion FROM cotizacion";
            rs = st.executeQuery(sql);

            while(rs.next()){//En caso de que hayan ordenes de trabajo
                id = rs.getString("id_cotizacion");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id); 
            }
            //idMayor = idMayor;
            ////////////////////////////////////////////////////////////            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
      
        return id;
    }
    
    private String idSiguienteVenta(){
    connect();
        int idMayor = 0;
        String id = "0";
        
        try {
            //////////////////Orden de trabajo/////////////////////////////////
            sql = "SELECT id_factura FROM venta";
            rs = st.executeQuery(sql);

            while(rs.next()){//En caso de que hayan ordenes de trabajo
                id = rs.getString("id_factura");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id); 
            }
            //idMayor = idMayor;
            ////////////////////////////////////////////////////////////            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
      
        return id;
    }
	
    //Busca cual es el siguiente id a ser asignado
    private String idSiguiente(){
        connect();
        int[] idsMayor = new int[3];
        int idMayor = 0;
        String id = "0";
        
        try {
            //////////////////GERENTE/////////////////////////////////
            sql = "SELECT id_Gerente FROM Gerente";
            rs = st.executeQuery(sql);

            while(rs.next()){//En caso de que hayan gerentes
                id = rs.getString("id_Gerente");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id); 
            }            
            idsMayor[0] = idMayor;            
            idMayor = 0;
            
            //////////////////Jefe Taller/////////////////////////////////
            sql = "SELECT id_jefe FROM Jefe_Taller";
            rs = st.executeQuery(sql);
            while(rs.next()){//En caso de que hayan jefes de taller
                id = rs.getString("id_jefe");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id);
            }            
            idsMayor[1] = idMayor;
            
            idMayor = 0;
            
            //////////////////Vendedor/////////////////////////////////
            sql = "SELECT id_vendedor FROM Vendedor";
            rs = st.executeQuery(sql);            
            while(rs.next()){//En caso de que hayan vendedores
                id = rs.getString("id_vendedor");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id);
            }            
            idsMayor[2] = idMayor;
                    
            ////////////////////////////////////////////////////////////
            /*idMayor = idsMayor[0];
            if(idMayor<idsMayor[1]){
                idMayor = idsMayor[1];
            }else{
                if(idMayor<idsMayor[2]){
                    idMayor = idsMayor[2];
                }
            }*/
            idMayor= max(idsMayor[0], idsMayor[1], idsMayor[2]);
            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return id;
    }
    
    private String idSiguienteOrden(){
        connect();
        int idMayor = 0;
        String id = "0";
        
        try {
            //////////////////Orden de trabajo/////////////////////////////////
            sql = "SELECT id_Orden FROM Orden_Trabajo";
            rs = st.executeQuery(sql);

            while(rs.next()){//En caso de que hayan ordenes de trabajo
                id = rs.getString("id_Orden");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id); 
            }
            //idMayor = idMayor;
            ////////////////////////////////////////////////////////////            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return id;
    }
    
    private String idSiguienteInventario(){
        connect();
        int idMayor = 0;
        String id = "0";
        
        try {
            //////////////////Orden de trabajo/////////////////////////////////
            sql = "SELECT id_Producto FROM Inventario";
            rs = st.executeQuery(sql);

            while(rs.next()){//En caso de que hayan ordenes de trabajo
                id = rs.getString("id_Producto");
                if(idMayor<Integer.parseInt(id)) idMayor = Integer.parseInt(id); 
            }            
            //idMayor = idMayor;
            ////////////////////////////////////////////////////////////            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return id;
    }
    
    //Comprueba que la cedula no este registrada en la base de datos
    private boolean validarCedula(String cedula){
        connect();
        boolean cedulaValida = true;
        
        try {
            /*
            //////////////////SUPER///////////////////////////////////
            sql = "SELECT cedula FROM Superusuario WHERE cedula = '"+cedula+"'";
            rs = st.executeQuery(sql);            
            if(cedulaValida && rs.next()) cedulaValida = false;
            */
            //////////////////GERENTE/////////////////////////////////
            sql = "SELECT cedula FROM Gerente WHERE cedula = '"+cedula+"' AND habilitado = '"+true+"'";
            rs = st.executeQuery(sql);            
            if(cedulaValida && rs.next()) cedulaValida = false;            
            //////////////////Jefe Taller/////////////////////////////
            sql = "SELECT cedula FROM Jefe_Taller WHERE cedula = '"+cedula+"' AND habilitado = '"+true+"'";
            rs = st.executeQuery(sql);            
            if(cedulaValida && rs.next()) cedulaValida = false;            
            //////////////////Vendedor////////////////////////////////
            sql = "SELECT cedula FROM Vendedor WHERE cedula = '"+cedula+"' AND habilitado = '"+true+"'";
            rs = st.executeQuery(sql);            
            if(cedulaValida && rs.next()) cedulaValida = false;            
            ////////////////////////////////////////////////////////////
            rs.close();
            st.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        
        return cedulaValida;
    }
    
    //Comprueba que el nombre de usuario no este registrado en la base de datos
    private boolean validarNombreUsu(String nombreU){
        connect();
        boolean nombreUValido = true;
        
        try {
            //////////////////SUPER///////////////////////////////////
            sql = "SELECT nombre_usuario FROM Superusuario WHERE nombre_usuario = '"+nombreU+"'";
            rs = st.executeQuery(sql);            
            if(nombreUValido && rs.next()) nombreUValido = false;
            //////////////////GERENTE/////////////////////////////////
            sql = "SELECT nombre_usuario FROM Gerente WHERE nombre_usuario = '"+nombreU+"' AND habilitado = '"+true+"'";
            rs = st.executeQuery(sql);            
            if(nombreUValido && rs.next()) nombreUValido = false;            
            //////////////////Jefe Taller/////////////////////////////
            sql = "SELECT nombre_usuario FROM Jefe_Taller WHERE nombre_usuario = '"+nombreU+"' AND habilitado = '"+true+"'";
            rs = st.executeQuery(sql);            
            if(nombreUValido && rs.next()) nombreUValido = false;            
            //////////////////Vendedor////////////////////////////////
            sql = "SELECT nombre_usuario FROM Vendedor WHERE nombre_usuario = '"+nombreU+"' AND habilitado = '"+true+"'";
            rs = st.executeQuery(sql);            
            if(nombreUValido && rs.next()) nombreUValido = false;            
            ////////////////////////////////////////////////////////////
            rs.close();
            st.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        
        return nombreUValido;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////CRUD GERENTE/////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    //Valida si existe un gerente con usuario user y contraseña contra
    public String validarGerente(String user, String contra){
        //Llamamos el metodo para poder conectarnos a la base de datos
        connect();
        String respuesta = null;
        sql = "SELECT * FROM Gerente WHERE nombre_usuario = '"+user+"' AND contrasenia = '"+contra+"'";
        //try catch porque se puede arrojar un error de consulta (SQL)
        try {
            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);            
                       
            if(rs.next()){
                respuesta = rs.getString("id_gerente");
            }
            
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return respuesta;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
    public String crearGerente(String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario, int idSede){
        
        String respuesta = "Ocurrió un error";
        boolean cedulaValida = validarCedula(cedula);
        boolean nombreUsuValido;
        
        if(!cedulaValida){ //// La cedula ya esta registrada en el sistema
            respuesta = "La cedula "+cedula+" ya esta registrada en el sistema";
            return respuesta;
        }else{
            nombreUsuValido = validarNombreUsu(nombreUsuario);
            if(!nombreUsuValido){ //// El nombre de usuario ya esta registrado en el sistema
                respuesta = "El nombre de usuario "+nombreUsuario+" ya esta registrado en el sistema";
                return respuesta;
            }   
        }
        
        String id = idSiguiente();
        String contrasenia = id+cedula;
        connect();
        
        try {                            
            sql = "INSERT INTO Gerente (id_gerente, nombre_gerente, cedula, cargo, e_mail, genero,"
                    + " direccion, telefono, salario, fecha_nacimiento, cuenta_bancaria,"
                    + " fecha_registro, nombre_usuario, contrasenia, habilitado, id_sede)"
                    + " VALUES ('"+id+"','"+nombre+"','"+cedula+"','"+cargo+"','"+correo+"','"+genero+
                    "','"+direccion+"','"+telefono+"','"+salario+"','"+fechaNacimiento+"','"+cuentaBancaria+"','"
                    +fechaRegistro+"','" +nombreUsuario+"','"+contrasenia+"','"+true+"','"+idSede+"')";
                
            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            
            respuesta = "Gerente agregado con éxito\n\nId: "+id+"\nNombre Usuario: "+nombreUsuario+"\nContraseña: "+contrasenia;                        
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }        
       return respuesta;
    }
    
    /**
     * Lectura de la base de datos para los gerentes
     * @param id
     * @return null si no encuentra el gerente
     */
    public Gerente leerGerentePorId(String id){
        connect();
        sql = "SELECT * FROM Gerente WHERE id_Gerente = '"+id+"' AND habilitado = '"+true+"'";
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
                //System.out.println(fechaNa);
                String cuentaBanc = rs.getString("cuenta_bancaria");
                String fechaReg = rs.getString("fecha_registro");
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                boolean habilitado = rs.getBoolean("habilitado");
                String fechaDespido = rs.getString("fecha_Despido");
                int idSede = Integer.parseInt(rs.getString("id_sede"));
                
                
                Gerente gerente = new Gerente(id, nombre, cedula, cargo, correo, 
                genero, direccion, telefono, salario, fechaNa, 
                cuentaBanc, fechaReg, nombreUsuario, contrasenia, habilitado, fechaDespido,idSede);
                
                rs.close();
                st.close();
                connection.close();
                
                return gerente;
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Lista a todos los gerentes presentes en la base de datos
     * @return Lista de gerentes
     */
    public String listarGerentes(){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        sql = "SELECT * FROM Gerente WHERE habilitado = '"+true+"'";
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            
            String id,nombre,cedula;
            String empleados = "";
            
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_gerente");
                nombre = rs.getString("nombre_Gerente");
                cedula = rs.getString("cedula");
                
                empleados = empleados+id+","+nombre+","+cedula+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    //TODO
    public String actualizarGerente(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String nombreUsuario,
            String contrasenia, String fechaRegistro, boolean habilitado, String fechaDespido){
        connect();
        sql = "SELECT id_Gerente FROM Gerente WHERE id_Gerente = '"+id+"' AND habilitado = '"+true+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                if(fechaDespido!=null){
                    sql = "UPDATE Gerente SET nombre_Gerente = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', e_mail = '"+correo+"',"
                            +" genero = '"+genero+"', direccion = '"+direccion+"', telefono = '"+telefono+"', salario = "+salario+", fecha_Nacimiento = '"+fechaNacimiento+
                            "', cuenta_Bancaria = '"+cuentaBancaria+"', nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+
                            "', fecha_Registro = '"+fechaRegistro+"', fecha_Despido = '"+fechaDespido+"', habilitado = '"+habilitado+
                            "' WHERE id_gerente = '"+id+"'";
                }else{ //En caso de que no se esté despidiendo no se modf la fecha en la base de datos
                    sql = "UPDATE Gerente SET nombre_Gerente = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', e_mail = '"+correo+"',"
                            +" genero = '"+genero+"', direccion = '"+direccion+"', telefono = '"+telefono+"', salario = "+salario+", fecha_Nacimiento = '"+fechaNacimiento+
                            "', cuenta_Bancaria = '"+cuentaBancaria+"', nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+
                            "', fecha_Registro = '"+fechaRegistro+"', habilitado = '"+habilitado+
                            "' WHERE id_gerente = '"+id+"'";
                }
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El gerente no esta registrado en la base de datos";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Gerente actualizado con éxito";
    }   
    
    public String eliminarGerente(String id){
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    //Deshabilita al gerente en la base de datos
    public String despedirGerente(String id, String fechaDespido){
        connect();
        sql = "SELECT id_Gerente FROM Gerente WHERE id_gerente = '"+id+"' AND habilitado = '"+true+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Gerente SET habilitado = '"+false+"', fecha_Despido = '"+fechaDespido+"' WHERE id_Gerente = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El gerente fue despedido";
            }else{              
                return "El gerente ya había sido despedido";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
        
    /**
     * Lista a todas las sedes presentes en la base de datos     
     * @param agregar true si la función que lo llama es agregar, false para modificar
     * @return lista de sedes
     */
    public String listarSedes(boolean agregar){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        if(agregar){
            sql = "WITH sedesGerente AS (SELECT id_sede FROM gerente WHERE habilitado = '"+true+"'),"+
                        "idsSedes AS (SELECT id_sede FROM sede where habilitada = '"+true+"'),"+
                        "idsSedesNoG AS (SELECT * FROM idsSedes EXCEPT SELECT * FROM sedesGerente)"+
            "SELECT * FROM sede NATURAL JOIN IdsSedesNoG";
            //sql = "WITH sedesGerente AS (SELECT id_sede FROM gerente) "+
            //"SELECT * FROM sede INNER JOIN sedesGerente ON sede.id_sede!=sedesGerente.id_sede WHERE habilitada = '"+true+"'";
        }else{
            sql = "SELECT * FROM Sede WHERE habilitada = '"+true+"'";
        }
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            
            String id,nombre,direccion;
            String sedes = "";
            
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_sede");
                nombre = rs.getString("nombre_sede");
                direccion = rs.getString("direccion");
                
                sedes = sedes+id+","+nombre+","+direccion+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return sedes;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////CRUD JEFE DE TALLER/////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    //Valida si existe un jefe de taller con usuario user y contraseña contra
    public String validarJefeTaller(String user, String contra){
        //Llamamos el metodo para poder conectarnos a la base de datos
        connect();
        String respuesta = null;
        sql = "SELECT * FROM Jefe_Taller WHERE nombre_Usuario = '"+user+"' AND contrasenia = '"+contra+"'";
        //try catch porque se puede arrojar un error de consulta (SQL)
        try {
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);            
                       
            if(rs.next()){
                respuesta = rs.getString("id_jefe");
            }
            
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return respuesta;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
    public String crearJefeTaller(String nombreUsuario, String nombreJefe, String cedula, 
            String cargo, String telefono, String direccion, int genero, String fechaNacimiento, String correo, float salario,
            String cuentaBancaria, String fechaRegistro, String idGerente, int idSede){
        
        String respuesta = "Ocurrió un error";
        boolean cedulaValida = validarCedula(cedula);
        boolean nombreUsuValido;
        
        if(!cedulaValida){ //// La cedula ya esta registrada en el sistema
            respuesta = "La cedula "+cedula+" ya esta registrada en el sistema";
            return respuesta;
        }else{
            nombreUsuValido = validarNombreUsu(nombreUsuario);
            if(!nombreUsuValido){ //// El nombre de usuario ya esta registrado en el sistema
                respuesta = "El nombre de usuario "+nombreUsuario+" ya esta registrado en el sistema";
                return respuesta;
            }   
        }
        
        String id = idSiguiente();
        String contrasenia = id+cedula;
        connect();
        
        try {                            
            sql = "INSERT INTO Jefe_Taller (id_jefe, contrasenia, nombre_usuario, nombre_jefe, cedula, cargo,"
                    + " telefono, direccion, genero, fecha_nacimiento, e_mail,"
                    + " salario, cuenta_bancaria, fecha_registro, habilitado, id_sede)"
                    + "VALUES ('"+id+"','"+contrasenia+"','"+nombreUsuario+"','"+nombreJefe+"','"+cedula+"','"+cargo+
                    "','"+telefono+"','"+direccion+"','"+genero+"','"+fechaNacimiento+"','"+correo+"','"
                    +salario+"','" +cuentaBancaria+"','"+fechaRegistro+"','"+true+"','"+idSede+"')";
                
            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            
            respuesta = "Jefe de Taller agregado con éxito\n\nId: "+id+"\nNombre Usuario: "+nombreUsuario+"\nContraseña: "+contrasenia;                        
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }        
       return respuesta;
    }
        
    public JefeTaller leerJefeTallerPorId(String id){
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
                String cuentaBanc = rs.getString("cuenta_bancaria");
                String fechaReg = rs.getString("fecha_registro");
                boolean habilitado = rs.getBoolean("habilitado");
                String fechaDespido = rs.getString("fecha_Despido");
                int sede = rs.getInt("id_Sede");
                System.out.println(sede);
                JefeTaller jefe = new JefeTaller(id, contrasenia, nombreUsuario, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, cuentaBanc, fechaReg, habilitado, fechaDespido, sede);
                System.out.println(jefe.getIdSede());
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

    
    public String actualizarJefe(String id, String contrasenia, String nombreUsuario, String nombreJefe, 
            String telefono, String direccion, int genero, String fechaNacimiento, String correo, float salario,
            String cuentaBancaria, String fechaRegistro, boolean habilitado, String fechaDespido){
        connect();
        sql = "SELECT id_Jefe FROM Jefe_Taller WHERE id_jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                if(fechaDespido!=null){
                    sql = "UPDATE Jefe_taller SET contrasenia = '"+contrasenia+"', nombre_usuario = '"+nombreUsuario+"', nombre_Jefe = '"+nombreJefe+"',"
                            +"  telefono = '"+telefono+"', direccion = '"+direccion+"', genero = "+genero+", fecha_Nacimiento = '"+fechaNacimiento+"', "
                            +" cuenta_Bancaria = '"+cuentaBancaria+"', e_mail = '"+correo+"', salario = '"+salario+
                            "', fecha_Registro = '"+fechaRegistro+"', fecha_Despido = '"+fechaDespido+"', habilitado = '"+habilitado+
                            "' WHERE id_jefe = '"+id+"'";
                }else{ //En caso de que no se esté despidiendo no se modf la fecha en la base de datos
                    sql = "UPDATE Jefe_taller SET contrasenia = '"+contrasenia+"', nombre_usuario = '"+nombreUsuario+"', nombre_Jefe = '"+nombreJefe+"',"
                            +" telefono = '"+telefono+"', direccion = '"+direccion+"', genero = "+genero+", fecha_Nacimiento = '"+fechaNacimiento+"',"
                            +" cuenta_Bancaria = '"+cuentaBancaria+"', e_mail = '"+correo+"', salario = '"+salario+
                            "', fecha_Registro = '"+fechaRegistro+"', habilitado = '"+habilitado+
                            "' WHERE id_jefe = '"+id+"'";
                }
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El jefe no esta registrado en la base de datos";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Jefe de taller actualizado con éxito";
    }
    
    public String eliminarJefeTaller(String id){
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
        
     /**
     * Lista a todos los Jefes de taller presentes en la base de datos
     * @return Lista de los jefes de taller
     */
    public String listarJefesTaller(){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        sql = "SELECT * FROM Jefe_Taller WHERE habilitado = '"+true+"'";
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            
            String id,nombre,cedula;
            String empleados = "";
            
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Jefe");
                nombre = rs.getString("nombre_Jefe");
                cedula = rs.getString("cedula");
                
                empleados = empleados+id+","+nombre+","+cedula+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
        //Deshabilita al Jefe de taller en la base de datos
    public String despedirJefeTaller(String id, String fechaDespido){
        connect();
        sql = "SELECT id_Jefe FROM Jefe_Taller WHERE id_Jefe = '"+id+"' AND habilitado = '"+true+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Jefe_Taller SET habilitado = '"+false+"', fecha_Despido = '"+fechaDespido+"' WHERE id_Jefe = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El Jefe de taller fue despedido";
            }else{              
                return "El Jefe de taller ya había sido despedido";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD VENDEDOR/////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    //Valida si existe un vendedor con usuario user y contraseña contra
    public String validarVendedor(String user, String contra){
        //Llamamos el metodo para poder conectarnos a la base de datos
        connect();
        String respuesta = null;
        sql = "SELECT * FROM Vendedor WHERE nombre_usuario = '"+user+"' AND contrasenia = '"+contra+"'";
        //try catch porque se puede arrojar un error de consulta (SQL)
        try {
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);            
                       
            if(rs.next()){
                respuesta = rs.getString("id_vendedor");
            }
            
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return respuesta;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
    public String crearVendedor(String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero, 
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            int sede){
         
        String respuesta = "Ocurrió un error";
        boolean cedulaValida = validarCedula(cedula);
        boolean nombreUsuValido;
        
        if(!cedulaValida){ //// La cedula ya esta registrada en el sistema
            respuesta = "La cedula "+cedula+" ya esta registrada en el sistema";
            return respuesta;
        }else{
            nombreUsuValido = validarNombreUsu(nombreUsuario);
            if(!nombreUsuValido){ //// El nombre de usuario ya esta registrado en el sistema
                respuesta = "El nombre de usuario "+nombreUsuario+" ya esta registrado en el sistema";
                return respuesta;
            }   
        }
        
        String id = idSiguiente();
        String contrasenia = id+cedula;
        connect();
        
        try {                            
            sql = "INSERT INTO vendedor (id_vendedor, nombre_vendedor, cedula, cargo, telefono, direccion,"
                    + " genero, fecha_Nacimiento, e_mail, salario, cuenta_Bancaria,"
                    + " fecha_Registro, nombre_Usuario, contrasenia, habilitado, id_sede)"
                    + "VALUES ('"+id+"','"+nombre+"','"+cedula+"','"+cargo+"','"+telefono+"','"+direccion+
                    "','"+genero+"','"+fechaNacimiento+"','"+correo+"','"+salario+"','"+cuentaBancaria+"','"
                    +fechaRegistro+"','" +nombreUsuario+"','"+contrasenia+"','"+true+"','"+sede+"')";
                
            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            
            respuesta = "Vendedor agregado con éxito\n\nId: "+id+"\nNombre Usuario: "+nombreUsuario+"\nContraseña: "+contrasenia;                        
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }        
       return respuesta;
    }
     
    public Vendedor leerVendedorPorId(String id){
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
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                boolean habilitado = rs.getBoolean("habilitado");
                String fechaDespido = rs.getString("fecha_Despido");
                int sedeV = rs.getInt("id_sede");
                Vendedor vendedor = new Vendedor(id, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, cuentaBanc, fechaReg, 
                nombreUsuario, contrasenia, habilitado, fechaDespido,sedeV);
                
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
     
    public String actualizarVendedor(String id, String nombre, 
             String telefono, String direccion, int genero, 
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, boolean habilitado, String fechaDespido){
        connect();
        sql = "SELECT id_Vendedor FROM Vendedor WHERE id_Vendedor = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                if(fechaDespido!=null){
                    sql = "UPDATE vendedor SET nombre_vendedor = '"+nombre+"', fecha_despido = '"+fechaDespido+"',"
                            +" telefono = '"+telefono+"', direccion = '"+direccion+"', genero = '"+genero+"', fecha_nacimiento = '"+fechaNacimiento+"', e_mail = '"+correo+
                            "', salario = '"+salario+"', cuenta_Bancaria = '"+cuentaBancaria+"', fecha_registro = '"+fechaRegistro+
                            "', nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+"', habilitado = '"+habilitado+
                            "' WHERE id_vendedor = '"+id+"'";
                }else{ //En caso de que no se esté despidiendo no se modf la fecha en la base de datos
                    sql = "UPDATE vendedor SET nombre_vendedor = '"+nombre+"',"
                            +" telefono = '"+telefono+"', direccion = '"+direccion+"', genero = '"+genero+"', fecha_nacimiento = '"+fechaNacimiento+"', e_mail = '"+correo+
                            "', salario = '"+salario+"', cuenta_Bancaria = '"+cuentaBancaria+"', fecha_registro = '"+fechaRegistro+
                            "', nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+"', habilitado = '"+habilitado+
                            "' WHERE id_vendedor = '"+id+"'";
                }
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El vendedor no esta registrado en la base de datos";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Vendedor actualizado con éxito";
    }
    
    public String eliminarVendedor(String id){
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";        
    }
    
      /**
     * Lista a todos los Jefes de taller presentes en la base de datos
     * @return Lista de todos los vendedores
     */
    public String listarVendedores(){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        sql = "SELECT * FROM Vendedor WHERE habilitado = '"+true+"'";
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            
            String id,nombre,cedula;
            String empleados = "";
            
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Vendedor");
                nombre = rs.getString("nombre_Vendedor");
                cedula = rs.getString("cedula");
                
                empleados = empleados+id+","+nombre+","+cedula+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
        //Deshabilita al vendedor en la base de datos
    public String despedirVendedor(String id, String fechaDespido){
        connect();
        sql = "SELECT id_Vendedor FROM Vendedor WHERE id_Vendedor = '"+id+"' AND habilitado = '"+true+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Vendedor SET habilitado = '"+false+"', fecha_Despido = '"+fechaDespido+"' WHERE id_Vendedor = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "El vendedor fue despedido";
            }else{              
                return "El vendedor ya había sido despedido";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
        
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////CRUD SALE//////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    public String crearVenta(String nombreCliente, String telefonoCliente, float valorVenta,
           String cedulaCliente, String descripcionVenta, String[] producto, int[] cantidad,String idVendedor){
        
        String id = idSiguienteVenta();
        connect();
        String mensaje = "No hay existencias del producto ";
        
        try {
            for(int i=0; i<cantidad.length; i++){
                    sql = "SELECT * FROM inventario WHERE id_producto = '"+producto[i]+"' AND cantidad < "+cantidad[i];
                    rs = st.executeQuery(sql);
                    if(rs.next()){
                        mensaje += rs.getString("nombre_producto")+" id:"+rs.getString("id_producto");
                    }
                }
            if(!mensaje.equals("No hay existencias del producto ")){
                        return mensaje;
                    }
            sql = "SELECT Venta.id_Factura, Modifica.id_Producto FROM Venta "
                + "INNER JOIN Modifica ON  Modifica.id_Factura = '"+id+"';";
            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La venta con el id "+id+" ya existe";
            }else{ 
            sql = "INSERT INTO Venta VALUES ('"+id+"','"+nombreCliente+"','"+telefonoCliente+"','"+cedulaCliente+"','"+valorVenta+"','"+descripcionVenta+
                    "','"+idVendedor+"');";
              
            for(int i=0; i<cantidad.length; i++){
                    sql += "INSERT INTO Modifica VALUES ("+cantidad[i]+",'"+id
                            +"','"+producto[i]+"'); ";
                    sql += "UPDATE inventario SET cantidad = cantidad-"+
                            cantidad[i]+" WHERE id_producto = '"+producto[i]+"'; ";
                }

            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            }
                             
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }        
       return "Venta agregada con exito";
    }
    
    public float obtenerValorVenta(String[] producto){
        connect();
        sql="";
        float valor = 0;
        try {
            for (int i=0; i< producto.length; i++){
                sql += "SELECT * FROM inventario WHERE id_producto = '"+producto[i]+"'";

                    rs = st.executeQuery(sql);
                    if(rs.next()){
                        valor += rs.getFloat("valor_unitario");
                    }
            }
            rs.close();
            st.close();
            connection.close();
        } catch (SQLException e) {
                System.out.println("ERROR DE SQL " + e.getMessage());
            }
        return valor;
    }
    
    public Venta leerVentaPorId(String id){
        connect();
        sql = "SELECT * FROM Venta WHERE id_Factura = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idVenta = rs.getString("id_Factura");
                String nombreCliente = rs.getString("nombre_cliente");
                String telefonoCliente = rs.getString("telefono_Cliente");
                String cedulaCliente = rs.getString("cedula_Cliente");
                int valorVenta = Integer.parseInt(rs.getString("valor_Venta"));
                String descripcionVenta = rs.getString("descripcion_Venta");
                String idVendedor = rs.getString("id_Vendedor");
                
                Venta venta = new Venta(idVenta, nombreCliente, telefonoCliente, cedulaCliente, valorVenta, descripcionVenta, idVendedor);
                
                rs.close();
                st.close();
                connection.close();
                
                return venta;
            }
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
    
    
    public String listarVentas(){
    //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        sql = "SELECT * FROM Venta";
        try {
        rs = st.executeQuery(sql);
        String id,nombreCliente;
        String ventas = "";
         while(rs.next()){
                    //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                    id = rs.getString("id_factura");
                    nombreCliente = rs.getString("nombre_cliente");

                    ventas = ventas+id+","+nombreCliente+"$";
                  }

        rs.close();
        st.close();
        connection.close();

        return ventas;
         }

        catch (SQLException e){
            System.out.println("ERROR DE SQL"+ e.getMessage());
        }

     return "";   
     }
    
    public String eliminarVenta(String id){
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
    public String crearOrden(String especificaciones, String estado, String fechaCreacion ,String idJefe, String[] referencias, int[] cantidades){                
        String id = idSiguienteOrden();
        connect();
        sql = "SELECT Orden_Trabajo.id_Orden, Actualiza.id_Producto FROM Orden_Trabajo "
                + "INNER JOIN Actualiza ON  Actualiza.id_Orden = '"+id+"' WHERE estado_Orden = 'En Proceso'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La orden de trabajo con el id "+id+" ya existe";
            }else{   
                sql = "INSERT INTO Orden_Trabajo VALUES ('"+id+"','"+especificaciones+"','"+estado+"','"
                                                        +fechaCreacion+"', null ,'"+idJefe+"');";
                for(int i=0; i<cantidades.length; i++){
                    sql += "INSERT INTO Actualiza VALUES ("+cantidades[i]+",'"+id+"','"+referencias[i]+"');";
                }
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
    
    public Object[] leerOrdenPorId(String id){
        connect();
        Object[] refs=new Object[2];
        sql = "SELECT * FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idOrden = rs.getString("id_Orden");
                String descripcionOrden = rs.getString("especificaciones");
                String estadoOrden = rs.getString("estado_Orden");
                String fechaCreacion = rs.getString("fecha_Creacion");
                String fechaEntrega = rs.getString("fecha_Entrega");
                String idJefe = rs.getString("id_Jefe");
                
                OrdenTrabajo orden = new OrdenTrabajo(idOrden, descripcionOrden, estadoOrden, fechaCreacion ,fechaEntrega, idJefe);
                rs.close();
                st.close();
                connection.close();
                refs[0]=orden;
                refs[1]=listarInventarioOrden(idOrden);
                return refs;
            }
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
        
    public String actualizarOrden(String id, String especificaciones, String estado,int[] cantidades, String[] referencias,String idJefe){
        connect();
        System.out.println(estado.equals("Terminada"));
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"' and estado_Orden = 'En Proceso';";
        try {
            rs = st.executeQuery(sql);
            boolean hayOrden = rs.next();
            if(hayOrden && estado.equals("Terminada")){
                System.out.println("Orden terminada");
                int anio = Calendar.getInstance().get(Calendar.YEAR);
                int mes = Calendar.getInstance().get(Calendar.MONTH);
                int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                String fecha_entrega = String.valueOf(anio)+"-"+String.valueOf(mes)
                +"-"+String.valueOf(dia);
                sql = "UPDATE Orden_Trabajo SET especificaciones='"+especificaciones+
                        "' ,estado_Orden='Terminada', fecha_Entrega='"+fecha_entrega+"' WHERE id_jefe ='"+idJefe+"' AND id_Orden = '"+id+"';";
                for(int i=0; i<cantidades.length; i++){
                    sql += "UPDATE Actualiza SET cantidad="+cantidades[i]+" WHERE id_Producto = '"+referencias[i]+"' AND id_Orden = '"+id+"';";
                    sql += "UPDATE Inventario SET cantidad=cantidad + "+cantidades[i]+" WHERE id_Producto = '"+referencias[i]+"' ;";
                    System.out.println(cantidades[i]+" - "+referencias[i]);
                }
                rs = st.executeQuery(sql);
                rs.close();
                st.close();
                connection.close();
            
            }else if(hayOrden){
                sql = "UPDATE Orden_Trabajo SET especificaciones='"+especificaciones+
                        "',estado_Orden='En Proceso'"+" WHERE id_orden='"+id+"' AND id_jefe = '"+idJefe+"';";
                for(int i=0; i<cantidades.length; i++){
                    sql += "UPDATE Actualiza SET cantidad="+cantidades[i]+" WHERE id_Producto = '"+referencias[i]+"' AND id_orden='"+id+"';";
                    System.out.println(cantidades[i]+" - "+referencias[i]);
                }
                //sql += ";";
                rs = st.executeQuery(sql);
                rs.close();
                st.close();
                connection.close();
            
            }else{        
                return "La orden con el id "+id+" no existe o ya esta terminada o anulada";
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Orden actualizada con éxito";           
    }
    
    public String eliminarOrden(String id){
        connect();
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Orden_Trabajo SET estado_Orden = 'Anulada' WHERE id_Orden = '"+id+"'";
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
    
    public String listarOrden(String idJefe, String consulta){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        if(consulta.equals("modificar")){
            sql = "SELECT * FROM Orden_Trabajo WHERE estado_Orden = 'En Proceso'"+" AND  id_Jefe ='"+idJefe+"'"; 
        }
        if(consulta.equals("consultar")){
            sql = "SELECT * FROM Orden_Trabajo WHERE estado_Orden IN('En Proceso', 'Terminada')"+" AND  id_Jefe ='"+idJefe+"'"; 
        }
        if(consulta.equals("todas")){
            sql = "SELECT * FROM Orden_Trabajo WHERE id_Jefe ='"+idJefe+"'"; 

        }
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            String id;
            String ordenes = "";
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Orden");
                ordenes = ordenes+id+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return ordenes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    public String listarInventarioOrden(String id_orden){
        connect();
        sql = "SELECT actualiza.id_Producto, nombre_Producto, actualiza.cantidad FROM "+
                "Actualiza INNER JOIN inventario on actualiza.id_producto=inventario.id_producto "+
                "WHERE actualiza.id_orden= '"+id_orden+"';";                
        try {            
            rs = st.executeQuery(sql);
            String id, nombre;
            int cantidad;
            String ordenes = "";
            while(rs.next()){
                id = rs.getString("id_Producto");
                nombre = rs.getString("nombre_Producto");
                cantidad = rs.getInt("cantidad");
                ordenes = ordenes+id+","+nombre+","+cantidad+"$";
            }
            rs.close();
            st.close();
            connection.close();
            return ordenes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    public int obtainCantidadRefOrden(String id_orden, String id_ref){
        connect();
        sql = "SELECT actualiza.id_Producto, nombre_Producto, actualiza.cantidad FROM "+
                "Actualiza INNER JOIN inventario on actualiza.id_producto=inventario.id_producto "+
                "WHERE actualiza.id_orden= '"+id_orden+"' AND actualiza.id_producto = '"
                + id_ref +"';";                
        try {            
            rs = st.executeQuery(sql);
            int cantidad=0;
            while(rs.next()){
                cantidad = rs.getInt("cantidad");
            }
            rs.close();
            st.close();
            connection.close();
            return cantidad;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return -1;
    }
    

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD INVENTARIO///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    public String crearInventario(String nombreProducto, float valorUnitario, 
            String descripcion, String idJefe){        
        String id = idSiguienteInventario();
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El Producto con el id "+id+" ya existe";
                
            }else{
               // sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
                //rs = st.executeQuery(sql);
                //if(rs.next()){
                    sql = "INSERT INTO Inventario VALUES ('"+id+"','"+nombreProducto+"',"
                            +valorUnitario+",'"+descripcion+"', 0,'disponible' ,'"+idJefe+"')";                
                    st.executeUpdate(sql);
                    rs.close();
                    st.close();
                    connection.close();
               // }else{
                //    return "El producto con el id "+id+" no existe";
               //}
                
            }
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Producto agregado con éxito";
    }
    
    public Inventario leerInventarioPorId(String id){
        connect();
        sql = "SELECT * FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idProducto = rs.getString("id_Producto");
                String nombreProducto = rs.getString("nombre_Producto");
                float valorUnitario = rs.getFloat("valor_Unitario");
                String descripcion = rs.getString("descripcion_Producto");
                int cantidad = rs.getInt("cantidad");
                String estado = rs.getString("estado_producto");
                String idJefe = rs.getString("id_Jefe");
                
                Inventario inventario = new Inventario(idProducto, 
                        nombreProducto, valorUnitario, descripcion, cantidad, estado, idJefe);
                
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

    public String actualizarInventario(String id, String nombreProducto, float valorUnitario, 
            String descripcion){
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Inventario SET nombre_Producto = '"+nombreProducto+"', valor_Unitario = "+valorUnitario+
                        ", descripcion_producto = '"+descripcion+"' WHERE id_Producto = '"+id+"';";
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
    
    public String deshabilitarInventario(String id){
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Inventario SET estado_Orden = '"+"'No disponible'";
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
       return "Referencia deshabilitada con éxito";
    }
    
    public String eliminarInventario(String id){
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
    
    public String listarInventario(String consulta){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        if(consulta.equals("disponible")){
            sql = "SELECT * FROM Inventario WHERE estado_Producto = 'disponible'";
        }
        if(consulta.equals("no disponible")){
            sql = "SELECT * FROM Inventario";
        }
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            String id, nombre;
            String ordenes = "";
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Producto");
                nombre = rs.getString("nombre_Producto");
                ordenes = ordenes+id+","+nombre+"$";
            }
            System.out.println("1552 - "+ordenes);
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return ordenes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
        ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD COTIZACION///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    public String crearCotizacion( String nombre_Cliente, String telefono, String email , float valor_Unitario,
                                    String fecha, String idVendedor,String[] producto, int[] cantidad){
        
        String id = idSiguienteCotizacion();
        connect();
        
        sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La venta con el id "+id+" ya existe";
            }else{ 
            sql = "INSERT INTO Cotizacion VALUES ('"+id+"','"+nombre_Cliente+"','"+telefono+"','"+email+"','"+valor_Unitario+"','"+fecha+"','"+idVendedor+"')";                
                
            for(int i=0; i<cantidad.length; i++){
                    sql += "INSERT INTO Consulta VALUES ("+cantidad[i]+",'"+id+"','"+producto[i]+"');";
                }

            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            }
                             
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }        
        return "Cotizacion agregada con éxito\n\nId: "+id+"\nNombre Cliente: "+nombre_Cliente+"\nEmail Cliente: "+email;
    }
    
     public String actualizarCotizacion(String id, String nombreCliente, String telefono, String email, float valor){
       connect();
        System.out.println(":v prueba2");
        sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            
            rs = st.executeQuery(sql);
            System.out.println(":v prueba1");
            if(rs.next()){
                sql = "UPDATE Cotizacion SET nombre_Cliente = '"+nombreCliente+"', valor_Cotizacion = "+valor+
                        ", email = '"+email+"', telefono_Cliente = '"+telefono+"' WHERE id_Cotizacion = '"+id+"';";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();

            }else{        
                return "La cotizacion con el id "+id+" no existe";
            }
            
        } catch (Exception e) {
            System.out.println(":v prueba");
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Cotizacion actualizado";          
    }
     
     public String listarProducto(String idVendedor, String consulta){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();

            sql = "SELECT * FROM Inventario "; 
        
        
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            rs = st.executeQuery(sql);
            String id;
            String nombre;
            String productos = "";
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Producto");
                productos = productos+id+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return productos;
        } catch (SQLException e) {
            System.out.println(":vv");
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
     
    
     public String listarVenta(String idVendedor, String consulta){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();

            sql = "SELECT * FROM Venta WHERE id_Vendedor ='"+idVendedor+"'"; 
        
        
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            rs = st.executeQuery(sql);
            String id;
            String ventas = "";
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Factura");
                ventas = ventas+id+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return ventas;
        } catch (SQLException e) {
            System.out.println(":vv");
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
     
    public String listarCotizacion(String idVendedor, String consulta){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();

            sql = "SELECT * FROM Cotizacion WHERE id_Vendedor ='"+idVendedor+"'"; 
        
        
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            rs = st.executeQuery(sql);
            String id;
            String cotizaciones = "";
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Cotizacion");
                cotizaciones = cotizaciones+id+"$";
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            return cotizaciones;
        } catch (SQLException e) {
            System.out.println(":vv");
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    public Cotizacion leerCotizacionPorId(String id){
        connect();
        sql = "SELECT * FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){

                String idCotizacion = rs.getString("id_Cotizacion");
                float valor = rs.getFloat("valor_Cotizacion");
                String nombreCliente = rs.getString("nombre_Cliente");
                String telefonoCliente = rs.getString("telefono_Cliente");
                String email = rs.getString("email");
                String fecha = rs.getString("fecha_Cotizacion");
                String idVendedor = rs.getString("id_Vendedor");
                
                Cotizacion cotizacion = new Cotizacion(idCotizacion,valor, nombreCliente, telefonoCliente,email,fecha, idVendedor);
                
                rs.close();
                st.close();
                connection.close();
                
                return cotizacion;
            }
        } catch (Exception e) {System.out.println(":/");
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }

        public String listarCotizaciones(){
    //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
    sql = "SELECT * FROM Cotizacion";
    
    try {
    
    rs = st.executeQuery(sql);
    String id,nombreCliente;
    String cotizaciones = "";
    
     while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Cotizacion");
                nombreCliente = rs.getString("nombre_cliente");
                
                cotizaciones = cotizaciones+id+","+nombreCliente+"$";
              }
    
    rs.close();
    st.close();
    connection.close();
            
    return cotizaciones;
     }
    
    catch (SQLException e){
        System.out.println("ERROR DE SQL"+ e.getMessage());
    }

 return "";   
 }
    
    public String actualizarCotizacion(String id, String nombreCliente, String telefono, String email,float valor_Unitario,
                                    String fecha, float valor,String id_Vendedor){
        connect();
        sql = "SELECT id_Cotizacion FROM Cotizacion WHERE id_Cotizacion = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Cotizacion SET nombre_Cliente = '"+nombreCliente+"', telefono_Cliente = '"+telefono+
                        "', email = '"+email+"'" +" valor_Cotizacion = '"+valor+"'"+" id_Vendedor = '"+id_Vendedor+"'";
                st.executeUpdate(sql);
                rs.close();
                
                st.close();
                connection.close();
            }else{        
                return "La Cotizacion con el id "+id+" no existe";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Cotizacion actualizada con éxito";
    }
    
    public String eliminarCotizacion(String id){
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD SEDE/////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
     public String crearSede(String nombreSede, String direccion, String fechaCreacion){
        connect();
        sql = "SELECT * FROM Sede ";
        String mensaje = "";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                 sql = "INSERT INTO Sede  (nombre_Sede, direccion, fecha_creacion, habilitada) VALUES"
                      + "('"+nombreSede+"','"+direccion+"','"+fechaCreacion+"','"+true+"')";                
                    st.executeUpdate(sql);
                    rs.close();
                    st.close();
                    connection.close();
                }
            
            mensaje = "Sede agregada con éxito";
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
            mensaje = "Hubo un Error";
        }
       return mensaje;
    }
    
    public Sede leerSedePorId(String id){
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
                
                Sede sede = new Sede(idSede, nombreSede, direccion,fechaCreacion, fechaFinalizacion);
                
                rs.close();
                st.close();
                connection.close();
                
                return sede;
            }
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return null;
    }
    
    public String actualizarSede(String id, String nombreSede, String direccion){
        connect();
        sql = "SELECT id_Sede FROM Sede WHERE id_Sede = '"+id+"'";
        String mensaje = "";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Sede SET nombre_Sede = '"+nombreSede+"', direccion = '"+direccion+"' "
                        + "WHERE id_sede = "+id;
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                mensaje = "La Sede con el id "+id+" no existe";
            }
            mensaje = "Sede actualizada con éxito";
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
            mensaje = "Hubo un error";
        }
       return mensaje;
    }
    
    public String eliminarSede(String id){
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    public String deshabilitarSede(String id, String fechaDespido){
        connect();
        sql = "SELECT id_sede FROM Sede WHERE id_sede = '"+id+"' AND habilitada = '"+true+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Sede SET habilitada = '"+false+"', fecha_finalizacion = '"+fechaDespido+"' WHERE id_sede = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
                return "la sede fue deshabilitada";
            }else{              
                return "la Sede ya habia sido deshabilitada";
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////    

    /*listar Vendedores y Jefes*/
    
    public String listarVendedoresYJefes(int sedeGerente){
        connect();
        //hacemos una union entre todos lo vendedores y todos los jefes y retornamos los id, nombre, cedula
        sql = "SELECT id_vendedor as id, nombre_Vendedor as nombre, cedula from Vendedor  "
                + "WHERE Habilitado = '"+true+"' and id_Sede = '"+sedeGerente+"' UNION SELECT id_jefe as id, nombre_jefe as nombre , cedula from jefe_taller  WHERE Habilitado = '"+true+"' and id_Sede = '"+sedeGerente+"'";
        try {
            rs = st.executeQuery(sql);
            
            String id,nombre,cedula;
            String empleados = "";
            
            while(rs.next()){
                id = rs.getString("id");
                nombre = rs.getString("nombre");
                cedula = rs.getString("cedula");
                
                empleados = empleados+id+","+nombre+","+cedula+"$";
            }

            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// REPORTES /////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public List reporteInventario(){
        connect();
        //Obtaining data from database
        sql = "SELECT * FROM inventario";
        try {
            rs = st.executeQuery(sql);
            List reportes = new ArrayList();
            String id = "";
            String nombre = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                id = rs.getString("id_producto");
                nombre = rs.getString("nombre_producto");
                cant = rs.getInt("cantidad");
                        report = new Report(nombre+"("+id+")", cant);
                        reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("Error en Reporte inventario");
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    public List reporteOrdenesTrabajoDia(String jefe, String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, EXTRACT(MONTH "
                + "FROM fecha_creacion) as mes, EXTRACT(DAY FROM fecha_creacion) "
                + "as dia, COUNT(*) AS cant FROM Orden_Trabajo WHERE id_Jefe = '"
                +jefe+"' AND fecha_creacion > '"+initDate+"' AND fecha_creacion "
                + "< '"+finishDate+"' GROUP BY dia, mes, anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String dia = "";
            String mes = "";
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                dia = rs.getString("dia");
                mes = rs.getString("mes");
                anio = rs.getString("anio");
                
                cant = rs.getInt("cant");
                report = new Report(dia+"/"+mes+"/"+anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    public List reporteOrdenesTrabajoMes(String jefe, String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, EXTRACT(MONTH "
                + "FROM fecha_creacion) as mes, COUNT(*) AS cant FROM Orden_Trabajo"
                + " WHERE id_Jefe = '"+jefe+"' AND fecha_creacion > '"+initDate+"' "
                + "AND fecha_creacion < '"+finishDate+"' GROUP BY mes, anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String mes = "";
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                mes = rs.getString("mes");
                anio = rs.getString("anio");
                
                cant = rs.getInt("cant");
                
                report = new Report(mes+"/"+anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    public List reporteOrdenesTrabajoAnio(String jefe, String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, COUNT(*) AS cant FROM Orden_Trabajo"
                + " WHERE id_Jefe = '"+jefe+"' AND fecha_creacion > '"+initDate+"' "
                + "AND fecha_creacion < '"+finishDate+"' GROUP BY anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                anio = rs.getString("anio");
                cant = rs.getInt("cant");
                report = new Report(anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    //Esta funcion lo que nos retorna los reportes de toda la empresa por dia
    public List reporteGerenteOrdenesTrabajoDia(String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, EXTRACT(MONTH "
                + "FROM fecha_creacion) as mes, EXTRACT(DAY FROM fecha_creacion) "
                + "as dia, COUNT(*) AS cant FROM Orden_Trabajo WHERE fecha_creacion > '"+initDate+"' AND fecha_creacion "
                + "< '"+finishDate+"' GROUP BY dia, mes, anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String dia = "";
            String mes = "";
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                dia = rs.getString("dia");
                mes = rs.getString("mes");
                anio = rs.getString("anio");
                
                cant = rs.getInt("cant");
                report = new Report(dia+"/"+mes+"/"+anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    //Esta funcion lo que nos retorna los reportes de toda la empresa por mes
    public List reporteGerenteOrdenesTrabajoMes(String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, EXTRACT(MONTH "
                + "FROM fecha_creacion) as mes, COUNT(*) AS cant FROM Orden_Trabajo"
                + " WHERE fecha_creacion > '"+initDate+"' "
                + "AND fecha_creacion < '"+finishDate+"' GROUP BY mes, anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String mes = "";
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                mes = rs.getString("mes");
                anio = rs.getString("anio");
                
                cant = rs.getInt("cant");
                
                report = new Report(mes+"/"+anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    //Esta funcion lo que nos retorna los reportes de toda la empresa por Anio
    public List reporteGerenteOrdenesTrabajoAnio(String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, COUNT(*) AS cant FROM Orden_Trabajo"
                + " WHERE fecha_creacion > '"+initDate+"' "
                + "AND fecha_creacion < '"+finishDate+"' GROUP BY anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                anio = rs.getString("anio");
                cant = rs.getInt("cant");
                report = new Report(anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    //Esta funcion lo que nos retorna los reportes de toda la empresa por dia
    public List reporteGerenteSedeOrdenesTrabajoDia(String sede, String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, EXTRACT(MONTH "
                + "FROM fecha_creacion) as mes, EXTRACT(DAY FROM fecha_creacion) "
                + "as dia, COUNT(*) AS cant FROM orden_trabajo NATURAL JOIN "
                + "jefe_taller  WHERE id_sede = "+String.valueOf(sede)+" "
                + "AND fecha_creacion > '"+initDate+"' AND fecha_creacion "
                + "< '"+finishDate+"' GROUP BY anio, mes, dia";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String dia = "";
            String mes = "";
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                dia = rs.getString("dia");
                mes = rs.getString("mes");
                anio = rs.getString("anio");
                
                cant = rs.getInt("cant");
                report = new Report(dia+"/"+mes+"/"+anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    //Esta funcion lo que nos retorna los reportes de toda la empresa por mes
    public List reporteGerenteSedeOrdenesTrabajoMes(String sede, String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, EXTRACT(MONTH "
                + "FROM fecha_creacion) as mes, COUNT(*) AS cant FROM orden_trabajo NATURAL JOIN "
                + "jefe_taller  WHERE id_sede = "+String.valueOf(sede)+" "
                + "AND fecha_creacion > '"+initDate+"' AND fecha_creacion "
                + "< '"+finishDate+"' GROUP BY anio, mes";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String mes = "";
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                mes = rs.getString("mes");
                anio = rs.getString("anio");
                
                cant = rs.getInt("cant");
                
                report = new Report(mes+"/"+anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    //Esta funcion lo que nos retorna los reportes de toda la empresa por Anio
    public List reporteGerenteSedeOrdenesTrabajoAnio(String sede, String initDate, String finishDate){
        connect();
        //Obtaining data from database
        sql = "SELECT EXTRACT(YEAR FROM fecha_creacion) as anio, COUNT(*) AS cant "
                + "FROM orden_trabajo NATURAL JOIN "
                + "jefe_taller  WHERE id_sede = "+String.valueOf(sede)+" "
                + "AND fecha_creacion > '"+initDate+"' AND fecha_creacion "
                + "< '"+finishDate+"' GROUP BY anio";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List reportes = new ArrayList();
            String anio = "";
            int cant = 0;
            Report report = new Report("",0);
            while(rs.next()){
                anio = rs.getString("anio");
                cant = rs.getInt("cant");
                report = new Report(anio, cant);
                reportes.add(report);
            }
            rs.close();
            st.close();
            connection.close();
            return reportes;
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        List empty = new ArrayList();
        return empty;
    }
    
    public static void main(String args[]) {    
        DBConnection prueba = new DBConnection();
        
        prueba.readSU();
    }
}
