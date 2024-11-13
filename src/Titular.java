import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Titular {
    // Atributos
    private String cuil;
    private String nombre;
    private String email;
    private String alias;
    private String cbu;
    private String referencia;

    // Setters
    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    // Método para insertar un titular con los datos actuales de la instancia en la tabla titulares
    public void insertarSQL(Connection connection) {
        String insertSQL = "INSERT INTO titulares (cuil, nombre, email, alias, cbu, referencia) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            // Establecer los valores de los parámetros
            pstmt.setString(1, this.cuil);
            pstmt.setString(2, this.nombre);
            pstmt.setString(3, this.email);
            pstmt.setString(4, this.alias);
            pstmt.setString(5, this.cbu);
            pstmt.setString(6, this.referencia);

            // Ejecutar la inserción
            pstmt.executeUpdate();
            System.out.println("\nTitular insertado exitosamente: " + nombre);
        } catch (SQLException e) {
            System.out.println("\nError al insertar el titular: " + e.getMessage());
        }
    }
}
