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
    private int idOrden;
    private String nombreCliente;
    private int costo;
    private int esCliente;
    private String descripcionOrden;
    private String telefonoCliente;
    private String estado;
    private String fechaEntrega;
    private int idJefe;

    public WorkOrder(int idOrden, String nombreCliente, int costo, int esCliente, String descripcionOrden, String telefonoCliente, String estado, String fechaEntrega, int idJefe) {
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
    
    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
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

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }
    
}
