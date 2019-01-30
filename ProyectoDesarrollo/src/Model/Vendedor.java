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
    
    private String managerId;
    
    public Vendedor(String id, String nombre,  String cedula, 
            String cargo, String telefono, String direccion, int genero,
            String fechaNacimiento, String correo, float salario, 
            String cuentaBancaria, String fechaRegistro, String nombreUsuario,
            String contrasenia, String managerId, boolean habilitado, String fechaDespido) {
        super(id, nombre, cedula, cargo, correo, genero, 
                direccion, telefono, salario, fechaNacimiento, cuentaBancaria, 
                fechaRegistro);
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
    

    
    
}
