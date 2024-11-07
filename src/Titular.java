import java.sql.*;

public class Titular {
    // Atributos
    private String cuil;
    private String nombre;
    private String email;
    private String alias;
    private String cbu;

    // Setters
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos

    // Inserta un titular en la tabla titulares con los datos actuales de la instancia
    public void insertarSQL(Statement statement) {
        String insertSQL = "INSERT INTO titulares (cuil, nombre, email, alias, cbu) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = statement.getConnection().prepareStatement(insertSQL)) {
            // Establecer los valores de los parámetros
            pstmt.setString(1, this.cuil);
            pstmt.setString(2, this.nombre);
            pstmt.setString(3, this.email);
            pstmt.setString(4, this.alias);
            pstmt.setString(5, this.cbu);

            // Ejecutar la inserción
            pstmt.executeUpdate();
            System.out.println("Titular insertado exitosamente: " + nombre);
        } catch (SQLException e) {
            System.out.println("Error al insertar el titular: " + e.getMessage());
        }
    }

}
