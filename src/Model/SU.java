/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Clase del super usuario encargado de gestionar los gerentes en la empresa XYZ
 * @author crist
 */
public class SU extends Usuario{
    
    /**
     * Constructor del super usuario
     * @param id
     * @param nombreUsuario
     * @param cedula
     * @param correo
     * @param fechaNacimiento 
     */
    public SU(String id, String nombreUsuario, String cedula, String correo, 
            String fechaNacimiento, String username, String password) {
        super(id, nombreUsuario, cedula, correo, 
            fechaNacimiento, username, password);
    }    
}
