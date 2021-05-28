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
public class TragoxCant {
    
    private int trago;
    private String nombre;
    private int cant;
    private double precio;

    public TragoxCant(int trago, String nombre, int cant, double precio) {
        this.trago = trago;
        this.nombre = nombre;
        this.cant = cant;
        this.precio = precio;
    }

    public int getTrago() {
        return trago;
    }

    public void setTrago(int trago) {
        this.trago = trago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

      
}
