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
public class DTOPedido {
    
    private Pedido ped;
    private ArrayList<TragoxCant> detalle;

    public DTOPedido(Pedido ped, ArrayList<TragoxCant> detalle) {
        this.ped = ped;
        this.detalle = detalle;
    }

    public Pedido getPed() {
        return ped;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }

    public ArrayList<TragoxCant> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<TragoxCant> detalle) {
        this.detalle = detalle;
    }

    
    
}
