/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author MATEO
 */
public class OrdenTrabajo {
    private String idOrden;
    private String especficaciones;
    private String estadoOrden;
    private String fechaCreacion;
    private String fechaEntrega;
    private String idJefe;
    

    public OrdenTrabajo(String idOrden, String especificaciones, String estadoOrden, String fechaCreacion, 
                        String fechaEntrega, String idJefe){
        this.idOrden = idOrden;
        this.especficaciones = especificaciones;
        this.estadoOrden = estadoOrden;
        this.fechaEntrega = fechaEntrega;
        this.fechaCreacion = fechaCreacion;
        this.idJefe = idJefe;
    }
    
    
}
    
