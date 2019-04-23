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
    private String idCotizacion;
    
    private float valor;
    
    private String nombreCliente;
    
    private String telefonoCliente;
  
    private String email;
    
    private String fecha;
    
    private String idVendedor;
    
            
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    public Cotizacion(String idCotizacion, float valor, String nombreCliente, String telefonoCliente, String email, String fecha, String idVendedor) {
        
        this.idCotizacion = idCotizacion;
        this.valor = valor;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.email = email;
        this.fecha = fecha;
        this.idVendedor = idVendedor;
    }

    public Cotizacion(String idCotizacion, float valorUnitario, int cantidad, String descripcion, String nombreEmpresa, String telefonoEmpresa, String direccionEmpresa, String idVendedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //-------------------------------------------------------------------------
    // GETS AND SETS
    //-------------------------------------------------------------------------

    public String getIdCotizacion() {
        return idCotizacion;
    }
    
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
    
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
