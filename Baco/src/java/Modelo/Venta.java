
package Modelo;

import java.sql.Date;

public class Venta {
    
   private int id;
   private int id_Mesa;
   private double total;
   private Date fecha;
   private int id_pago;
   private boolean pagado;

    public Venta(int id, int id_Mesa, double total, Date fecha, int id_pago, boolean pagado) {
        this.id = id;
        this.id_Mesa = id_Mesa;
        this.total = total;
        this.fecha = fecha;
        this.id_pago = id_pago;
        this.pagado = pagado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Mesa() {
        return id_Mesa;
    }

    public void setId_Mesa(int id_Mesa) {
        this.id_Mesa = id_Mesa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", id_Mesa=" + id_Mesa + ", total=" + total + ", fecha=" + fecha + ", id_pago=" + id_pago + ", pagado=" + pagado + '}';
    }
   
}
