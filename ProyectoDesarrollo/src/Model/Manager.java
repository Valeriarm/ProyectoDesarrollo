/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author crist
 */
public class Manager extends User{
    
    public Manager(String id, String nombre, String cedula, 
            String cargo, String correo, int genero, String direccion, 
            String telefono, double salario, String fechaNacimiento, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasena, boolean habilitado) {
        super(id, nombre, cedula, cargo, correo, 
                genero, direccion, telefono, salario, fechaNacimiento, 
                cuentaBancaria, fechaRegistro, nombreUsuario, contrasena, habilitado);
    }    
}
