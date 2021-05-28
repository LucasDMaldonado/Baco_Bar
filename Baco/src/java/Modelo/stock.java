
package Modelo;

import java.sql.Date;


public class Stock {
    
    private int id;
    private int cantidad;
    private int usado;
    private String proveedor;
    private String nomProd;
    private int id_producto;
    private boolean ingrediente;
    private Date fecha;

    public Stock(int id, int cantidad, int usado, String proveedor, String nomProd, int id_producto, boolean ingrediente, Date fecha) {
        this.id = id;
        this.cantidad = cantidad;
        this.usado = usado;
        this.proveedor = proveedor;
        this.nomProd = nomProd;
        this.id_producto = id_producto;
        this.ingrediente = ingrediente;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getUsado() {
        return usado;
    }

    public void setUsado(int usado) {
        this.usado = usado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public boolean isIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(boolean ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
}