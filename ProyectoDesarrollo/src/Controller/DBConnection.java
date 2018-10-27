/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Foreman;
import Model.Manager;
import Model.User;
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
    private String dBUser = "bloodymist";
    private String dBPassword = "crilake01";
    //puerto
    private String port = "5432";
    //Nombre de la base de datos
    private String dBName = "xyz";
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
        //System.out.println(updateManager("1234","Mateo Gregory","1144070011", Manager.GERENTE,"matgre@hotmail.com", 0,"Same","3117307659  ",1000,"05/10/1999","03142571"));
        System.out.println(updateForeman("1234","Mateo Gregory","1144070011", Manager.GERENTE,"matgre@hotmail.com", 0,"Same","3117307659  ",1000,"05/10/1999","03142571","1234"));
        Manager g = readManagerById("1234");
        if(g == null){
            System.out.println("No existe");
        }else{
            System.out.println(g.getNombre());
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
        sql = "SELECT * FROM superusuario";
        //Necesito un try catch porque esto me puede arrojar un error de consulta (SQL)
        try {
            //Aquí usamos el metodo de Statment executeQuery y le pasamos la sentencia sql, esto lo guardamos en el 
            //Resultset y usamos next() para saltar entre filas, cada fila es un ingreso de la base de datos
            rs = st.executeQuery(sql);
            while(rs.next()){
                //Usando getString podemos obtener el resultado de nuestra consulta pasandole el nombre de la columna
                String user = rs.getString("id_superusuario");
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
    
    public String createManager(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro){
        connect();
        sql = "SELECT id_gerente FROM gerente WHERE id_gerente = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El gerente con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO gerente VALUES ('"+id+"','','"+nombre+"','"+cedula+"','"+cargo+"','"+telefono+"','"+direccion+
                        "','"+genero+"','"+fechaNacimiento+"','"+correo+"','"+salario+"','"+cuentaBancaria+"','"
                        +fechaRegistro+"')";
                
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
        sql = "SELECT * FROM gerente WHERE id_gerente = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String nombre = rs.getString("nombre_gerente");
                String cedula = rs.getString("cedula");
                String cargo = rs.getString("cargo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                int genero = Integer.parseInt(rs.getString("genero"));
                String fechaNa = rs.getString("fecha_nacimiento");
                String email = rs.getString("e_mail");
                double salario = rs.getDouble("salario");
                String cuentaBanc = rs.getString("cuenta_bancaria");
                String fechaReg = rs.getString("fecha_registro");
                
                Manager gerente = new Manager(id, nombre, cedula, cargo, email, 
                genero, direccion, telefono, salario, fechaNa, 
                cuentaBanc, fechaReg);
                
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
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria){
        connect();
        sql = "SELECT id_gerente FROM gerente WHERE id_gerente = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE gerente SET nombre_gerente = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', telefono = '"+telefono+"',"
                        +" genero = '"+genero+"', fecha_nacimiento = '"+fechaNacimiento+"', e_mail = '"+correo+"', salario = "+salario+", cuenta_bancaria = '"+cuentaBancaria+
                        "', direccion = '"+direccion+"' WHERE id_gerente = '"+id+"'";
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
        sql = "SELECT id_gerente FROM gerente WHERE id_gerente = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM gerente WHERE id_gerente = '"+id+"'";
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
    
    public String createForeman(String id, String nombre, String nombreUsuario, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String managerId){
        connect();
        sql = "SELECT id_jefe FROM jefe_taller WHERE id_jefe = '"+id+"'";
        try {

            rs = st.executeQuery(sql);
            if(rs.next()){
                return "El Jefe de taller con el id "+id+" ya existe";
            }else{                
                sql = "INSERT INTO jefe_taller VALUES ('"+id+"','','"+nombre+"','"+cedula+"','"+cargo+"','"+telefono+"','"+direccion+
                        "','"+genero+"','"+fechaNacimiento+"','"+correo+"','"+salario+"','"+cuentaBancaria+"','"
                        +fechaRegistro+",'"+managerId+"')";
                
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
    
    public Foreman readForemanById(String id){
        connect();
        sql = "SELECT * FROM jefe_taller WHERE id_jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                String nombre = rs.getString("nombre_gerente");
                String cedula = rs.getString("cedula");
                String cargo = rs.getString("cargo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                int genero = Integer.parseInt(rs.getString("genero"));
                String fechaNa = rs.getString("fecha_nacimiento");
                String email = rs.getString("e_mail");
                double salario = rs.getDouble("salario");
                String cuentaBanc = rs.getString("cuenta_bancaria");
                String fechaReg = rs.getString("fecha_registro");
                String managerId = rs.getString("id_gerente");
                
                Foreman jefe = new Foreman(id, nombre, cedula, cargo, email, 
                genero, direccion, telefono, salario, fechaNa, 
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

    public String updateForeman(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria, String managerId){
        connect();
        sql = "SELECT id_jefe FROM jefe_taller WHERE id_jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "UPDATE jefe SET nombre_jefe = '"+nombre+"', cedula = '"+cedula+"', cargo = '"+cargo+"', telefono = '"+telefono+"',"
                        +" genero = '"+genero+"', fecha_nacimiento = '"+fechaNacimiento+"', e_mail = '"+correo+"', salario = "+salario+", cuenta_bancaria = '"+cuentaBancaria+
                        "', direccion = '"+direccion+"', id_gerente = '"+managerId+"' WHERE id_gerente = '"+id+"'";
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
        sql = "SELECT id_jefe FROM jefe_taller WHERE id_jefe = '"+id+"'";
        try {
            rs = st.executeQuery(sql);
            if(rs.next()){
                sql = "DELETE FROM jefe_taller WHERE id_jefe = '"+id+"'";
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
    
    public static void main(String args[]) {    
        DBConnection prueba = new DBConnection();
    }
    
}
