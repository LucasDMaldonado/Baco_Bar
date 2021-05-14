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
public class Producto {
    
    private int id;
    private String nombre;
    private boolean Ingrediente;

    public Producto(int id, String nombre, boolean Ingrediente) {
        this.id = id;
        this.nombre = nombre;
        this.Ingrediente = Ingrediente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isIngrediente() {
        return Ingrediente;
    }

    public void setIngrediente(boolean Ingrediente) {
        this.Ingrediente = Ingrediente;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", Ingrediente=" + Ingrediente + '}';
    }
    
    
}
