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
public class Vendedor extends Usuario{    
    
    public Vendedor(String id, String nombre,  String cedula, 
            String cargo, String telefono, String direccion, int genero,
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, boolean habilitado, String fechaDespido, int idSede) {
        super(id, contrasenia, nombreUsuario, nombre, cedula, cargo, telefono, direccion, genero,
             fechaNacimiento,  correo,  salario, cuentaBancaria,  fechaRegistro,fechaDespido,idSede);

    }
    
}
