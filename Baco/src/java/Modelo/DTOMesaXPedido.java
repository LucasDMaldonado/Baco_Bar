/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author panic
 */
public class DTOMesaXPedido {
    
    private Mesa mesa;
    private DTOPedido pedido;

    public DTOMesaXPedido(Mesa mesa, DTOPedido pedido) {
        this.mesa = mesa;
        this.pedido = pedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public DTOPedido getPedido() {
        return pedido;
    }

    public void setPedido(DTOPedido pedido) {
        this.pedido = pedido;
    }
    
    
}
