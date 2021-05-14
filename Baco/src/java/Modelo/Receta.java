
package Modelo;


public class Receta {
    private int id;
    private int id_trago;
    private int id_prod;
    private double cantidad;

    public Receta(int id, int id_trago, int id_prod, double cantidad) {
        this.id = id;
        this.id_trago = id_trago;
        this.id_prod = id_prod;
        this.cantidad = cantidad;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_trago() {
        return id_trago;
    }

    public void setId_trago(int id_trago) {
        this.id_trago = id_trago;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }



    @Override
    public String toString() {
        return "Receta{" + "id=" + id + ", id_trago=" + id_trago + ", id_prod=" + id_prod + ", cantidad=" + cantidad + '}';
    }
    
}
