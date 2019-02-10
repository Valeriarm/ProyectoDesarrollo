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
    private String dBUser = "postgres";
    private String dBPassword = "1144211502";
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
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
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
            idMayor = idMayor;
            ////////////////////////////////////////////////////////////            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (Exception e) {
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
            idMayor = idMayor;
            ////////////////////////////////////////////////////////////            
            id = String.valueOf(idMayor+1);
            
            rs.close();
            st.close();
            connection.close();
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return id;
    }
    
    //Comprueba que la cedula no este registrada en la base de datos
    private boolean validarCedula(String cedula){
        connect();
        boolean cedulaValida = true;
        
        try {
            //////////////////SUPER///////////////////////////////////
            sql = "SELECT cedula FROM Superusuario WHERE cedula = '"+cedula+"'";
            rs = st.executeQuery(sql);            
            if(cedulaValida && rs.next()) cedulaValida = false;
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
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
    public String crearGerente(String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario){
        
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
            sql = "INSERT INTO Gerente VALUES ('"+id+"','"+nombre+"','"+cedula+"','"+cargo+"','"+correo+"','"+genero+
                    "','"+direccion+"','"+telefono+"','"+salario+"','"+fechaNacimiento+"','"+cuentaBancaria+"','"
                    +fechaRegistro+"','" +nombreUsuario+"','"+contrasenia+"','"+true+"')";
                
            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            
            respuesta = "Gerente agregado con éxito\n\nId: "+id+"\nNombre Usuario: "+nombreUsuario+"\nContraseña: "+contrasenia;                        
        } catch (Exception e) {
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
                String cuentaBanc = rs.getString("cuenta_bancaria");
                String fechaReg = rs.getString("fecha_registro");
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                boolean habilitado = rs.getBoolean("habilitado");
                String fechaDespido = rs.getString("fecha_Despido");
                
                
                Gerente gerente = new Gerente(id, nombre, cedula, cargo, correo, 
                genero, direccion, telefono, salario, fechaNa, 
                cuentaBanc, fechaReg, nombreUsuario, contrasenia, habilitado, fechaDespido);
                
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
    
    /**
     * Lista a todos los gerentes presentes en la base de datos
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
                
                empleados = empleados+id+","+nombre+","+cedula+"$";;
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (Exception e) {
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
                sql = "UPDATE Gerente SET nombre_Gerente = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', e_mail = '"+correo+"',"
                        +" genero = '"+genero+"', direccion = '"+direccion+"', telefono = '"+telefono+"', salario = "+salario+", fecha_Nacimiento = '"+fechaNacimiento+
                        "', cuenta_Bancaria = '"+cuentaBancaria+"', nombre_Usuario = '"+nombreUsuario+"', contrasenia = '"+contrasenia+
                        "', fecha_Registro = '"+fechaRegistro+"', fecha_Despido = '"+fechaDespido+"', habilitado = '"+habilitado+
                        "' WHERE id_gerente = '"+id+"'";
                st.executeUpdate(sql);
                rs.close();
                st.close();
                connection.close();
            }else{        
                return "El gerente no esta registrado en la base de datos";
            }
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
    public String crearJefeTaller(String nombreUsuario, String nombreJefe, String cedula, 
            String cargo, String telefono, String direccion, int genero, String fechaNacimiento, String correo, float salario,
            String cuentaBancaria, String fechaRegistro, String idGerente){
        
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
            sql = "INSERT INTO Jefe_Taller VALUES ('"+id+"','"+contrasenia+"','"+nombreUsuario+"','"+nombreJefe+"','"+cedula+"','"+cargo+
                    "','"+telefono+"','"+direccion+"','"+genero+"','"+fechaNacimiento+"','"+correo+"','"
                    +salario+"','" +cuentaBancaria+"','"+fechaRegistro+"','"+idGerente+"','"+true+"')";
                
            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            
            respuesta = "Jefe de Taller agregado con éxito\n\nId: "+id+"\nNombre Usuario: "+nombreUsuario+"\nContraseña: "+contrasenia;                        
        } catch (Exception e) {
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
                String cuentaBanc = rs.getString("cuenta_bancario");
                String fechaReg = rs.getString("fecha_registro");
                String managerId = rs.getString("id_gerente");
                boolean habilitado = rs.getBoolean("habilitado");
                String fechaDespido = rs.getString("fecha_Despido");
                
                JefeTaller jefe = new JefeTaller(id, contrasenia, nombreUsuario, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, 
                cuentaBanc, fechaReg, managerId, habilitado, fechaDespido);
                
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

    
    public String actualizarJefe(String id, String contrasenia, String nombreUsuario, String nombreJefe, String cedula, 
            String cargo, String telefono, String direccion, int genero, String fechaNacimiento, String correo, float salario,
            String cuentaBancaria, String fechaRegistro, String idGerente, boolean habilitado, String fechaDespido){
        connect();
        sql = "SELECT id_Jefe FROM Jefe_Taller WHERE id_jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Jefe_Taller SET nombre_Jefe = '"+nombreJefe+"', cedula = '"+cedula+"', cargo = '"+cargo+"', "
                        + "telefono = '"+telefono+"'," +" genero = '"+genero+"', fecha_Nacimiento = '"+fechaNacimiento+"', "
                        + "e_mail = '"+correo+"', salario = "+salario+", cuenta_Bancaria = '"+cuentaBancaria+
                        "', direccion = '"+direccion+"', id_Gerente = '"+idGerente+"', contarasenia = '"+contrasenia+
                        "', nombre_Usuario = '"+nombreUsuario+"', fecha_Registro = '"+fechaRegistro+ "', habilitado = '"+habilitado+
                        "', fecha_Despido = '"+fechaDespido+"' WHERE id_jefe = '"+id+"'";
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
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";
    }
    
    
     /**
     * Lista a todos los Jefes de taller presentes en la base de datos
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
                
                empleados = empleados+id+","+nombre+","+cedula+"$";;
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return respuesta;
    }
    
     public String crearVendedor(String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero, 
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String managerId){
         
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
            sql = "INSERT INTO Vendedor VALUES ('"+id+"','"+nombre+"','"+cedula+"','"+cargo+"','"+telefono+"','"+direccion+
                    "','"+genero+"','"+fechaNacimiento+"','"+correo+"','"+salario+"','"+cuentaBancaria+"','"
                    +fechaRegistro+"','" +nombreUsuario+"','"+contrasenia+"','"+managerId+"','"+true+"')";
                
            st.executeUpdate(sql);
            rs.close();
            st.close();
            connection.close();
            
            respuesta = "Vendedor agregado con éxito\n\nId: "+id+"\nNombre Usuario: "+nombreUsuario+"\nContraseña: "+contrasenia;                        
        } catch (Exception e) {
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
                String managerId = rs.getString("id_Gerente");
                String nombreUsuario = rs.getString("nombre_Usuario");
                String contrasenia = rs.getString("contrasenia");
                boolean habilitado = rs.getBoolean("habilitado");
                String fechaDespido = rs.getString("fecha_Despido");
                
                Vendedor vendedor = new Vendedor(id, nombre, cedula, cargo, telefono, 
                direccion, genero, fechaNa, email, salario, cuentaBanc, fechaReg, 
                nombreUsuario, contrasenia, managerId, habilitado, fechaDespido);
                
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
     
    public String actualizarVendedor(String id, String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero, 
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, String managerId, boolean habilitado, String fechaDespido){
        connect();
        sql = "SELECT id_Vendedor FROM Vendedor WHERE id_Vendedor = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Vendedor SET nombre_Vendedor = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', telefono = '"+telefono+"',genero = '"+genero+
                        "', fecha_Nacimiento = '"+fechaNacimiento+"', e_mail = '"+correo+"', salario = '"+salario+"', cuenta_Bancaria = '"+cuentaBancaria+
                        "', direccion = '"+direccion+"', id_Gerente = '"+managerId+"', fecha_Registro = '"+fechaRegistro+"', nombre_Usuario = '"+nombreUsuario+
                        "', contrasenia = '"+contrasenia+"', habilitado = '"+habilitado+"', fecha_Despido = '"+fechaDespido+"' WHERE id_vendedor = '"+id+"'";
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
            
        } catch (Exception e) {
            System.out.println("ERROR DE SQL " + e.getMessage());
        }
        return "";        
    }
    
      /**
     * Lista a todos los Jefes de taller presentes en la base de datos
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
                
                empleados = empleados+id+","+nombre+","+cedula+"$";;
            }
            //POR ULTIMO E IMPORTANTE: hay que cerrar siempre las conexiones
            rs.close();
            st.close();
            connection.close();
            
            return empleados;
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
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
        } catch (Exception e) {
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
    
    
    
    public String crearOrden(String nombreCliente, String idCliente, float costo, int esCliente, 
           String descripcionOrden, String telefonoCliente, String estado, String fechaEntrega, String idJefe){
        
        connect();
        String id = idSiguienteOrden();
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "La orden de trabajo con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO Orden_Trabajo VALUES ('"+id+"','','"+nombreCliente+"', id_Cliente = '"+idCliente+"','"+costo+"','"+esCliente+"','"+descripcionOrden+"','"
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
    
    public OrdenTrabajo leerOrdenPorId(String id){
        connect();
        sql = "SELECT * FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String idOrden = rs.getString("id_Orden");
                String nombreCliente = rs.getString("nombre_Cliente");
                String idCliente = rs.getString("id_Cliente");
                String telefonoCliente = rs.getString("telefono_Cliente");
                float valorOrden = Integer.parseInt(rs.getString("valor_Orden"));
                int esCliente = Integer.parseInt(rs.getString("es_Cliente"));                
                String descripcionOrden = rs.getString("descripcion_Orden");
                String estadoOrden = rs.getString("estado_Orden");
                String fechaEntrega = rs.getString("fecha_Entrega");
                String idJefe = rs.getString("id_Jefe");
                
                OrdenTrabajo orden = new OrdenTrabajo(idOrden, nombreCliente, idCliente, telefonoCliente,
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
    
    public String actualizarOrden(String id, String nombreCliente,String idCliente, float costo, int esCliente, 
           String descripcionOrden, String telefonoCliente, String estado, String fechaEntrega, String idJefe){
        
        connect();
        sql = "SELECT id_Orden FROM Orden_Trabajo WHERE id_Orden = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE Orden_Trabajo SET nombre_Cliente = '"+nombreCliente+"', id_Cliente = '"+idCliente+"', valor_Orden = '"+costo+"', es_Cliente = '"+esCliente+
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
    
    public String eliminarOrden(String id){
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
    
    
    
    
    public String crearInventario(String nombreProducto, float valorUnitario, 
            String descripcion, int lote, int cantidadLote, String idJefe){
        connect();
        String id = idSiguienteInventario();
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
                int lote = rs.getInt("lote");
                int cantidadLote = rs.getInt("cantidad_Lote");
                String idJefe = rs.getString("id_Jefe");
                
                Inventario inventario = new Inventario(idProducto, nombreProducto, valorUnitario, 
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

    public String actualizarInventario(String id, String nombreProducto, float valorUnitario, 
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
            
        } catch (Exception e) {
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
    
    
        
    public String crearSede(String id, String nombreSede, String direccion, String fechaCreacion,
                             String fechaFinalizacion){
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
                    sql = "INSERT INTO Sede VALUES ('"+id+"','"+nombreSede+"','"+direccion+"','"+fechaCreacion+"','"+fechaFinalizacion+"')";                
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
            
        } catch (Exception e) {
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
