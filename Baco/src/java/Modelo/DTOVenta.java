/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author panic
 */
public class DTOVenta {
    
    private Venta venta;
    private ArrayList<Trago> trago;

    public DTOVenta(Venta venta, ArrayList<Trago> trago) {
        this.venta = venta;
        this.trago = trago;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ArrayList<Trago> getTrago() {
        return trago;
    }

    public void setTrago(ArrayList<Trago> trago) {
        this.trago = trago;
    }
    
}
