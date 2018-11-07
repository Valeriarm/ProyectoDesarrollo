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
public class WorkOrder {
    private String idOrden;
    private String nombreCliente;
    private int costo;
    private int esCliente;
    private String descripcionOrden;
    private String telefonoCliente;
    private String estado;
    private String fechaEntrega;
    private String idJefe;

    public WorkOrder(String idOrden, String nombreCliente, int costo, int esCliente, String descripcionOrden, String telefonoCliente, String estado, String fechaEntrega, String idJefe) {
        this.idOrden = idOrden;
        this.nombreCliente = nombreCliente;
        this.costo = costo;
        this.esCliente = esCliente;
        this.descripcionOrden = descripcionOrden;
        this.telefonoCliente = telefonoCliente;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.idJefe = idJefe;
    }
    
    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getEsCliente() {
        return esCliente;
    }

    public void setEsCliente(int esCliente) {
        this.esCliente = esCliente;
    }

    public String getDescripcionOrden() {
        return descripcionOrden;
    }

    public void setDescripcionOrden(String descripcionOrden) {
        this.descripcionOrden = descripcionOrden;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
