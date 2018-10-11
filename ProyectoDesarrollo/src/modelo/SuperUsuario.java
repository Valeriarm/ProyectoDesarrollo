/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase del super usuario encargado de gestionar los gerentes en la empresa XYZ
 * @author crist
 */
public class SuperUsuario extends Usuario{
    
    /**
     * Constructor del super usuario
     * @param id
     * @param nombreUsuario
     * @param cedula
     * @param correo
     * @param fechaNacimiento 
     */
    public SuperUsuario(String id, String nombreUsuario, String cedula, String correo, String fechaNacimiento) {
        super(id, nombreUsuario, cedula, correo, fechaNacimiento);
        Usuario usuario = new SuperUsuario(id, nombreUsuario, cedula, correo, fechaNacimiento);
    }    
}
