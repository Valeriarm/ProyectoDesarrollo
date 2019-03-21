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
    
    private float valor;
    
    private String nombreCliente;
    
    private String telefonoCliente;
  
    private String email;
    
    private String fecha;
    
    private String producto;
            
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    public Cotizacion(float valor, String nombreCliente, String telefonoCliente, String email, String fecha, String producto) {

        this.valor = valor;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.email = email;
        this.fecha = fecha;
        this.producto = producto;
    }
    
    
    //-------------------------------------------------------------------------
    // GETS AND SETS
    //-------------------------------------------------------------------------

        public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public float getValorUnitario() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getEmail() {
        return email;
    }

    public void setNombreEmpresa(String email) {
        this.email = email;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoEmpresa(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
