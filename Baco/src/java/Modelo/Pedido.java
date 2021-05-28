/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author panic
 */
public class Pedido {
    private int idPedido;
    private int idMesa;
    private boolean Estado;
    private Date fechaPedido;

    public Pedido(int idPedido, int idMesa, boolean Estado, Date fechaPedido) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.Estado = Estado;
        this.fechaPedido = fechaPedido;
    }
    
    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }


}
