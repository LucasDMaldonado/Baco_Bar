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
public class DTOTragoxReceta {
    
    private Trago trag;
    private ArrayList<Producto> prod;
    private int Categoria;

    public DTOTragoxReceta(Trago trag, ArrayList<Producto> prod, int Categoria) {
        this.trag = trag;
        this.prod = prod;
        this.Categoria = Categoria;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }

    public Trago getTrag() {
        return trag;
    }

    public void setTrag(Trago trag) {
        this.trag = trag;
    }

    public ArrayList<Producto> getProd() {
        return prod;
    }

    public void setProd(ArrayList<Producto> prod) {
        this.prod = prod;
    }

  

       
    
}
