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
    private ArrayList<Receta> Rece;

    public DTOTragoxReceta(Trago trag, ArrayList<Receta> Rece) {
        this.trag = trag;
        this.Rece = Rece;
    }

    public Trago getTrag() {
        return trag;
    }

    public void setTrag(Trago trag) {
        this.trag = trag;
    }

    public ArrayList<Receta> getRece() {
        return Rece;
    }

    public void setRece(ArrayList<Receta> Rece) {
        this.Rece = Rece;
    }
    
}
