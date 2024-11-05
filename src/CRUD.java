import java.sql.*;

public class CRUD {
    private Statement statement = null;

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /* insertar transferencia
    public void insertarTransferencia(Transferencia transferencia) {
        String sql = "INSERT INTO transferencias (aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, IMPORTE, CONCEPTO, MOTIVO, REFERENCIA, EMAIL, TITULAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transferencia.getAliasDEBITO());
            pstmt.setString(2, transferencia.getAliasCREDITO());
            pstmt.setString(3, transferencia.getCbuDEBITO());
            pstmt.setString(4, transferencia.getCbuCREDITO());
            pstmt.setDouble(5, transferencia.getImporte());
            pstmt.setString(6, transferencia.getConcepto());
            pstmt.setString(7, transferencia.getMotivo());
            pstmt.setString(8, transferencia.getReferencia());
            pstmt.setString(9, transferencia.getEmail());
            pstmt.setString(10, transferencia.getTitular());
            pstmt.executeUpdate();
            System.out.println("Transferencia cargada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

     */

    // Listar Titulares
    public void listarTitulares(Statement statement) {
        String consulta = "SELECT * FROM titulares";
        try {
            ResultSet resultado = statement.executeQuery(consulta);
            while (resultado.next()) {
                int cuil = resultado.getInt("cuil");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String alias = resultado.getString("alias");

                System.out.println("CUIL: " + cuil + ", Nombre: " + nombre + ", Email: " + email + ", Alias: " + alias);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Insertar Titular
    public void insertarTitular(String cuil, String alias, String email, String nombre) {
        String insertSQL = "INSERT INTO titulares (cuil, alias, email, nombre) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = statement.getConnection().prepareStatement(insertSQL)) {
            // Establecer los valores de los parámetros
            pstmt.setString(1, cuil);
            pstmt.setString(2, alias);
            pstmt.setString(3, email);
            pstmt.setString(4, nombre);

            // Ejecutar la inserción
            pstmt.executeUpdate();
            System.out.println("Titular insertado exitosamente: " + nombre);
        } catch (SQLException e) {
            System.out.println("Error al insertar el titular: " + e.getMessage());
        }
    }
    // Eliminar
    // Listar por dni

}

