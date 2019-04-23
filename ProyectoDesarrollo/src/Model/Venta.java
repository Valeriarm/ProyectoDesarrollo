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
public class Venta {
    
    //-------------------------------------------------------------------------
    // ATRIBUTOS
    //-------------------------------------------------------------------------
    private String idVenta;
    
    private String nombreCliente;
    
    private String telefonoCliente;
    
    private String cedulaCliente;
    
    private float valorVenta;
    
    private String descripcionVenta;
    
    private String idVendedor;
    
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------


    public Venta(String idVenta,String nombreCliente, String telefonoCliente, String cedulaCliente, float valorVenta, String descripcionVenta, String idVendedor) {

        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.cedulaCliente = cedulaCliente;
        this.valorVenta = valorVenta;
        this.descripcionVenta = descripcionVenta;
        this.idVendedor = idVendedor;
    }
    
    //-------------------------------------------------------------------------
    // GETS AND SETS
    //-------------------------------------------------------------------------

    public Venta(String id, String nombreCliente, String telefonoCliente, String cedulaCliente, int valorVenta, String descripcionVenta, String fecha, String producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      public String getIdVenta() {
        return idVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public float getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(int valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getDescripcionVenta() {
        return descripcionVenta;
    }

    public void setDescripcionVenta(String descripcionVenta) {
        this.descripcionVenta = descripcionVenta;
    }
    
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
}