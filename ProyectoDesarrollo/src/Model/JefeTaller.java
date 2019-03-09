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
public class JefeTaller extends Usuario{
    
    private String managerId;
    
    public JefeTaller(String id, String contrasenia, String nombreUsuario, String nombre, String cedula, 
            String cargo, String telefono, String direccion, int genero,
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String managerId, boolean habilitado, String fechaDespido, int sede) {
        super(id, contrasenia, nombreUsuario, nombre, cedula, cargo, telefono, direccion, genero,
             fechaNacimiento,  correo,  salario, cuentaBancaria,  fechaRegistro,  managerId,  fechaDespido, sede);
        
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
   
    
}
