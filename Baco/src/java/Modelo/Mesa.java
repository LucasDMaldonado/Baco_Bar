
package Modelo;


public class Mesa {
    private int id;
    private String Nombre;
    private String Ubicacion;
    private int idPedido;

    public Mesa(int id, String Nombre, String Ubicacion, int idPedido) {
        this.id = id;
        this.Nombre = Nombre;
        this.Ubicacion = Ubicacion;
        this.idPedido = idPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    
}
