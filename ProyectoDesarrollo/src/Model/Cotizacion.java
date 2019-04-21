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
public class Cotizacion {
    
    //-------------------------------------------------------------------------
    // ATRIBUTOS
    //-------------------------------------------------------------------------
    
    private String id;
    private String nombreCliente;
    private String valorCotizacion;
    private String telefonoCliente;
    private String fechaCotizacion;
    private String correoCliente;
    private String idVendedor;
            
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    public Cotizacion(String id, String nombreCliente, String valorCotizacion, String telefonoCliente, String fechaCotizacion, String correoCliente, String idVendedor) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.valorCotizacion = valorCotizacion;
        this.telefonoCliente = telefonoCliente;
        this.fechaCotizacion = fechaCotizacion;
        this.correoCliente = correoCliente;
        this.idVendedor = idVendedor;
    }
    
    
    //-------------------------------------------------------------------------
    // GETS AND SETS
    //-------------------------------------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getValorCotizacion() {
        return valorCotizacion;
    }

    public void setValorCotizacion(String valorCotizacion) {
        this.valorCotizacion = valorCotizacion;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefono) {
        this.telefonoCliente = telefono;
    }

    public String getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(String fechaC) {
        this.fechaCotizacion = fechaC;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoC) {
        this.correoCliente = correoC;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
}
