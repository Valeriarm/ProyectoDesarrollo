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

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getEspecficaciones() {
        return especficaciones;
    }

    public void setEspecficaciones(String especficaciones) {
        this.especficaciones = especficaciones;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }
    
    
}
    
