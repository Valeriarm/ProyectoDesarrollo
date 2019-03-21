/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Clase padre para todos los empleados de la empresa XYZ
 * @author crist
 */
public class Usuario {
    
    //-------------------------------------------------------------------------
    // CONSTANTES
    //-------------------------------------------------------------------------
    
    public final static int MASCULINO = 0;
    
    public final static int FEMENINO = 1;
    
    public final static String GERENTE = "Gerente";
    
    public final static String JEFE_DE_TALLER = "Jefe de taller";
    
    public final static String VENDEDOR = "Vendedor";
    
    //-------------------------------------------------------------------------
    // CONSTANTES
    //-------------------------------------------------------------------------
    
    private String id;
    
    private String nombre;
    
    private String nombreUsuario;
    
    private String contrasena;
    
    private String cedula;
    
    private String cargo;
    
    private String correo;
    
    private int genero;
    
    private String direccion;
    
    private String telefono;
    
    private float salario;
    
    private String fechaNacimiento;
    
    private boolean habilitado;
    
    private String cuentaBancaria;
    
    private String fechaRegistro;
    
    private String fechaDespido;
    
    private String managerId;
    
    private int sede;
    
    //-------------------------------------------------------------------------
    // CONSTRUCTORES
    //-------------------------------------------------------------------------
    
    /**
     * Constructor de la clase Usuario, cada usuario se diferencia por su cargo
     * @param id
     * @param nombre
     * @param nombreUsuario
     * @param cedula
     * @param cargo
     * @param correo
     * @param genero
     * @param direccion
     * @param telefono
     * @param salario
     * @param fechaNacimiento
     * @param cuentaBancaria
     * @param fechaRegistro 
     * @param contrasena
     * @param habilitado
     */
    public Usuario(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasena, boolean habilitado, String fechaDespido, int sede) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.cedula = cedula;
        this.cargo = cargo;
        this.correo = correo;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentaBancaria = cuentaBancaria;
        this.fechaRegistro = fechaRegistro;
        this.contrasena = contrasena;
        this.habilitado = habilitado;
        this.fechaDespido = fechaDespido;
        this.sede = sede;
    }
    
    /**
     * Sin nombre de usuario
     * @param id
     * @param nombre
     * @param cedula
     * @param cargo
     * @param correo
     * @param genero
     * @param direccion
     * @param telefono
     * @param salario
     * @param fechaNacimiento
     * @param cuentaBancaria
     * @param fechaRegistro 
     */
    public Usuario(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, float salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.cedula = cedula;
        this.cargo = cargo;
        this.correo = correo;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentaBancaria = cuentaBancaria;
        this.fechaRegistro = fechaRegistro;
    }
 
    /**
     * Segundo constructor para la clase de super usuario
     * @param id
     * @param nombreUsuario
     * @param cedula
     * @param correo
     * @param fechaNacimiento 
     */
    public Usuario(String id, String nombreUsuario, String cedula, String correo, 
            String fechaNacimiento, String username, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.cedula = cedula;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.nombreUsuario = username;
        this.contrasena = password;
    }
    
    /**
     * Tercer constructor para la clase de super usuario
     * @param id
     * @param contrasenia
     * @param nombreUsuario
     * @param nombre
     * @param cedula
     * @param cargo
     * @param telefono
     * @param direccion
     * @param genero
     * @param correo
     * @param fechaNacimiento 
     * @param salario
     * @param cuentaBancaria
     * @param fechaRegistro
     * @param managerId
     * @param fechaDespido 
     */    
    
    public Usuario (String id, String contrasenia, String nombreUsuario, String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero,
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String managerId, String fechaDespido, int sede){
        
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasenia;
        this.cedula = cedula;
        this.cargo = cargo;
        this.correo = correo;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.cuentaBancaria = cuentaBancaria;
        this.fechaRegistro = fechaRegistro;
        this.managerId = managerId;
        this.sede = sede;
    }
    
    //-------------------------------------------------------------------------
    // GETS & SETS
    //-------------------------------------------------------------------------

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return nombreUsuario;
    }

    public void setUsuario(String usuario) {
        this.nombreUsuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDespido() {
        return fechaDespido;
    }

    public void setFechaDespido(String fechaDespido) {
        this.fechaDespido = fechaDespido;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }
    
    
}
