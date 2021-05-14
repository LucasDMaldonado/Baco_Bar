
package Modelo;


public class Mesa {
    private int id;
    private String Nombre;
    private String Ubicacion;

    public Mesa(int id, String Nombre, String Ubicacion) {
        this.id = id;
        this.Nombre = Nombre;
        this.Ubicacion = Ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", Nombre=" + Nombre + ", Ubicacion=" + Ubicacion + '}';
    }
    
}
