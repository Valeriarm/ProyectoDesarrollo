/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
        callSU();
        System.out.println(deleteManagerById("12345"));
    }
    
    /**
     * Este método hace la conexión a la base de datos
     * Lo hago aquí porque me resulta mas cómodo hacer el llamado solo una vez, que estarlo haciendo todo el tiempo
     * en cada método.
     */
    public void Connect(){
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
    public void callSU(){
        //Llamamos el metodo que cree arriba para poder conectarnos a la base de datos
        Connect();
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
    
    public String addManager(String id, String nombre, String nombreUsuario, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro){
        Connect();
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
    
    public String updateManager(String id, String nombre, String nombreUsuario, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro){
        Connect();
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
    
    public String deleteManagerById(String id){
        Connect();
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
    
    public static void main(String args[]) {    
        DBConnection prueba = new DBConnection();
    }
    
}
