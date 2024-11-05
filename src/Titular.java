
public class Titular {
    private String alias;
    private String cbu;
    private String email;
    private String nombre;

    // Constructor
    public Titular(String alias, String cbu, String email, String nombre) {
        this.alias = alias;
        this.cbu = cbu;
        this.email = email;
        this.nombre = nombre;
    }

    // Getters and setters
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos
}
