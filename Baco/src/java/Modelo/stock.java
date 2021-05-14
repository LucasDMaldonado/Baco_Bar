
package Modelo;

import java.sql.Date;


public class stock {
    
    private int id;
    private double cantidad;
    private double usado;
    private String proveedor;
    private int id_producto;
    private Date fecha;

    public stock(int id, double cantidad, double usado, String proveedor, int id_producto, Date fecha) {
        this.id = id;
        this.cantidad = cantidad;
        this.usado = usado;
        this.proveedor = proveedor;
        this.id_producto = id_producto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getUsado() {
        return usado;
    }

    public void setUsado(double usado) {
        this.usado = usado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "stock{" + "id=" + id + ", cantidad=" + cantidad + ", usado=" + usado + ", proveedor=" + proveedor + ", id_producto=" + id_producto + ", fecha=" + fecha + '}';
    }
    
}
