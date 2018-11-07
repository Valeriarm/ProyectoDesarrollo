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
    private int idProducto;
    private String nombreProducto;
    private int valorUnitario;
    private String descripcion;
    private int lote;
    private int cantidadLote;

    public Inventory(int idProducto, String nombreProducto, int valorUnitario, String descripcion, int lote, int cantidadLote) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.valorUnitario = valorUnitario;
        this.descripcion = descripcion;
        this.lote = lote;
        this.cantidadLote = cantidadLote;
    }

    
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getValorUnitario() {
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

   
}
