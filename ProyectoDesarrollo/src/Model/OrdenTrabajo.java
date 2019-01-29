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
    private String nombreCliente;
    private String idCliente;
    private String telefonoCliente;
    private float valorOrden;
    private int esCliente;
    private String descripcionOrden;
    private String estadoOrden;
    private String fechaEntrega;
    private String idJefe;

    public OrdenTrabajo(String idOrden, String nombreCliente, String idCliente,String telefonoCliente,
            int valorOrden, int esCliente, String descripcionOrden, String estadoOrden, String fechaEntrega, String idJefe) {
        this.idOrden = idOrden;
        this.nombreCliente = nombreCliente;
        this.idCliente = idCliente;
        this.telefonoCliente = telefonoCliente;
        this.valorOrden = valorOrden;
        this.esCliente = esCliente;
        this.descripcionOrden = descripcionOrden;
        this.telefonoCliente = telefonoCliente;
        this.estadoOrden = estadoOrden;
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

    public float getValorOrden() {
        return valorOrden;
    }

    public void setValorOrden(float valorOrden) {
        this.valorOrden = valorOrden;
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

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
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
