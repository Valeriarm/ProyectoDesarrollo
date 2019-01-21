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
public class Quotation {
    
    //-------------------------------------------------------------------------
    // ATRIBUTOS
    //-------------------------------------------------------------------------
    
    private String id;
    
    private String nombreProducto;
    
    private float valorUnitario;
    
    private int cantidad;
    
    private String descripcionProducto;
    
    private String nombreEmpresa;
    
    private String telefonoEmpresa;
    
    private String direccionEmpresa;
    
    private String idVendedor;
            
    //-------------------------------------------------------------------------
    // CONSTRUCTOR
    //-------------------------------------------------------------------------

    public Quotation(String id, String nombreProducto, float valorUnitario, int cantidad, String descripcionProducto, String nombreEmpresa, String telefonoEmpresa, String direccionEmpresa, String idVendedor) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.valorUnitario = valorUnitario;
        this.cantidad = cantidad;
        this.descripcionProducto = descripcionProducto;
        this.nombreEmpresa = nombreEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.direccionEmpresa = direccionEmpresa;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
}
