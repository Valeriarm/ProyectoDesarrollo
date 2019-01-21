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
public class Inventory {
    private String idProducto;
    private String nombreProducto;
    private float valorUnitario;
    private String descripcion;
    private int lote;
    private int cantidadLote;
    private String idJefe;

    public Inventory(String idProducto, String nombreProducto, float valorUnitario, String descripcion, int lote, int cantidadLote, String idJefe) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.valorUnitario = valorUnitario;
        this.descripcion = descripcion;
        this.lote = lote;
        this.cantidadLote = cantidadLote;
        this.idJefe = idJefe;
    }

    
    
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
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

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getCantidadLote() {
        return cantidadLote;
    }

    public void setCantidadLote(int cantidadLote) {
        this.cantidadLote = cantidadLote;
    }

    public String getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }

    
   
}
