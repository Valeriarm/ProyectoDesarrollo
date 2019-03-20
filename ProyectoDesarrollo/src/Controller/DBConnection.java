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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 * Clase tentativa para el manejo y conexión a la base de datos
 * @author Cristian Perafan
 */
public class DBConnection {
    
    //----------------------------------------------------------------------
    // Atributos necesarios para conectar la base de datos
    //----------------------------------------------------------------------
    
    //Usuario de la base de datos en postgresql
    private final String dBUser = "desarrollo";
    private final String dBPassword = "desarrollo";
  

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
            idMayor = idsMayor[0];
            if(idMayor<idsMayor[1]){
                idMayor = idsMayor[1];
            }else{
                if(idMayor<idsMayor[2]){
                    idMayor = idsMayor[2];
                }
            }
            
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
                System.out.println(nombreUsuario);
                String contrasenia = rs.getString("contrasenia");
                System.out.println(contrasenia);
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
                
                JefeTaller jefe = new JefeTaller(id, contrasenia, nombreUsuario, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, cuentaBanc, fechaReg, habilitado, fechaDespido, sede);
                
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
                            +"  telefono = '"+telefono+"', direccion = '"+direccion+"', genero = "+genero+", fecha_Nacimiento = '"+fechaNacimiento+
                            "', cuenta_Bancaria = '"+cuentaBancaria+"', e_mail = '"+correo+"', salario = '"+salario+
                            "', fecha_Registro = '"+fechaRegistro+"', fecha_Despido = '"+fechaDespido+"', habilitado = '"+habilitado+
                            "' WHERE id_jefe = '"+id+"'";
                }else{ //En caso de que no se esté despidiendo no se modf la fecha en la base de datos
                    sql = "UPDATE Jefe_taller SET contrasenia = '"+contrasenia+"', nombre_usuario = '"+nombreUsuario+"', nombre_Jefe = '"+nombreJefe+"',"
                            +" telefono = '"+telefono+"', direccion = '"+direccion+"', genero = "+genero+", fecha_Nacimiento = '"+fechaNacimiento+
                            "', cuenta_Bancaria = '"+cuentaBancaria+"', e_mail = '"+correo+"', salario = '"+salario+
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
                int sedeV = rs.getInt("id_Sede");
                
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
                            +" telefono = '"+telefono+"', direccion = '"+direccion+"', genero = '"+genero+"', fecha_nacimiento = "+fechaNacimiento+", e_mail = '"+correo+
                            "', salario = '"+salario+"', cuenta_Bancaria = '"+cuentaBancaria+"', fecha_registro = '"+fechaRegistro+
                            "', nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+"', habilitado = '"+habilitado+
                            "' WHERE id_vendedor = '"+id+"'";
                }else{ //En caso de que no se esté despidiendo no se modf la fecha en la base de datos
                    sql = "UPDATE vendedor SET nombre_vendedor = '"+nombre+"',"
                            +" telefono = '"+telefono+"', direccion = '"+direccion+"', genero = '"+genero+"', fecha_nacimiento = "+fechaNacimiento+", e_mail = '"+correo+
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
    
        //Deshabilita al Jefe de taller en la base de datos
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
    
    
     
    public String crearVenta(String id, String nombreCliente, String telefonoCliente, 
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Venta agregada con éxito";
        
        
    }
    
    public Venta leerVentaPorId(String id){
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
                
                Venta venta = new Venta(id, nombreCliente, telefonoCliente, cedulaCliente, valorVenta, 
                descripcionVenta, idVendedor);
                
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
    
    public String actualizarVenta(String id, String nombreCliente, String telefonoCliente, 
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
                    sql += "UPDATE Inventario SET cantidad="+cantidades[i]+" WHERE id_Producto = '"+referencias[i]+"' ;";
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
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        connect();
        //Creo la sentencia sql de lo que quiero hacer, en este caso, quiero todas las columnas de la tabla
        sql = "SELECT actualiza.id_Producto, nombre_Producto, actualiza.cantidad FROM "+
                "Actualiza INNER JOIN inventario on actualiza.id_producto=inventario.id_producto "+
                "WHERE actualiza.id_orden= '"+id_orden+"';";                
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {            
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            String id, nombre;
            int cantidad;
            String ordenes = "";
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                id = rs.getString("id_Producto");
                nombre = rs.getString("nombre_Producto");
                cantidad = rs.getInt("cantidad");
                ordenes = ordenes+id+","+nombre+","+cantidad+"$";
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
    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////CRUD INVENTARIO///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    public String crearInventario(String nombreProducto, float valorUnitario, 
            String descripcion, String idJefe){        
        System.out.println(idJefe);
        String id = idSiguienteInventario();
        System.out.println(id);
        connect();
        sql = "SELECT id_Producto FROM Inventario WHERE id_Producto = '"+id+"'";
        try {
            System.out.println(sql);
            rs = st.executeQuery(sql);
            System.out.println("jaime dice");
            if(rs.next()){
                return "El Producto con el id "+id+" ya existe";
                
            }else{
                
                
                System.out.println(idJefe);
                System.out.println(id);
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
    
    
    
    
    public String crearCotizacion(String id, String nombre_Producto, float valor_Unitario, int cantidad,
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
    
    public Cotizacion leerCotizacionPorId(String id){
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
                
                Cotizacion cotizacion = new Cotizacion(idCotizacion, nombreProducto, valorUnitario, 
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

    public String actualizarCotizacion(String id, String nombre_Producto, float valor_Unitario, int cantidad,
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
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                 sql = "INSERT INTO Sede VALUES (nombre_Sede, direccion, fecha_creacion, habilitada)"
                      + "('"+nombreSede+"','"+direccion+"','"+fechaCreacion+"','"+true+"')";                
                    st.executeUpdate(sql);
                    rs.close();
                    st.close();
                    connection.close();
                }
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Sede agregada con éxito";
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
    
    public String actualizarSede(String id, String nombreSede, String direccion, String fechaCreacion,
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
            
        } catch (SQLException e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
       return "Sede actualizada con éxito";
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
    
    
    

        /*listar Vendedores y Jefes*/
    
    public String listarVendedoresYJefes(int sedeGerente){
        connect();
        //hacemos una union entre todos lo vendedores y todos los jefes y retornamos los id, nombre, cedula
        sql = "SELECT id_vendedor, nombre_Vendedor, cedula from Vendedor  "
                + "WHERE Habilitado = '"+true+"' and id_Sede = '"+sedeGerente+"' UNION SELECT id_jefe, nombre_jefe , cedula from jefe_taller  WHERE Habilitado = '"+true+"' and id_Sede = '"+sedeGerente+"'";
        try {
            rs = st.executeQuery(sql);
            
            String id,nombre,cedula;
            String empleados = "";
            
            while(rs.next()){
                id = rs.getString("id_Vendedor");
                nombre = rs.getString("nombre_Vendedor");
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
    
    public static void main(String args[]) {    
        DBConnection prueba = new DBConnection();
        
        prueba.readSU();
    }
    
}
