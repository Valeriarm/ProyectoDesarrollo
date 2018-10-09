/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author crist
 */
public class JefeTaller extends Usuario{
    
    public JefeTaller(String id, String nombre, String nombreUsuario, String cedula, String cargo, String correo, int genero, String direccion, String telefono, double salario, String fechaNacimiento, String cuentaBancaria, String fechaRegistro) {
        super(id, nombre, nombreUsuario, cedula, cargo, correo, genero, direccion, telefono, salario, fechaNacimiento, cuentaBancaria, fechaRegistro);
    }
    
}
