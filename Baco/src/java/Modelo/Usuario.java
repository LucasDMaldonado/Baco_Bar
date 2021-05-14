
package Modelo;


public class Usuario {

 private String User;
 private String Pass;

    public Usuario(String User, String Pass) {
        this.User = User;
        this.Pass = Pass;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    @Override
    public String toString() {
        return "Usuario{" + "User=" + User + ", Pass=" + Pass + '}';
    }
 
}
