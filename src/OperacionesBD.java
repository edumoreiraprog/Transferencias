import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OperacionesBD {
    //private Validacion validacion = new Validacion();
    private Validacion validacion = new Validacion();
    private Connection connection;
    private Scanner scanner = new Scanner(System.in);

    // Constructor para recibir la conexión
    public OperacionesBD(Connection connection) {
        this.connection = connection;
    }

    // Métodos

    // Listar todos los titulares de la base de datos
    public void listarTitulares() {
        String consulta = "SELECT * FROM titulares";
        try (PreparedStatement stmt = connection.prepareStatement(consulta);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                String cuil = resultado.getString("cuil");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String alias = resultado.getString("alias");
                String cbu = resultado.getString("cbu");

                System.out.println("CUIL: " + cuil + ", Nombre: " + nombre + ", Email: " + email + ", Alias: " + alias + ", CBU: " + cbu);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar titulares: " + e.getMessage());
        }
    }

    // Agregar un titular a la base de datos
    public void agregarTitular() {
        Titular titular = new Titular();

        titular.setCuil(validacion.validarCUIL());
        titular.setNombre(validacion.validarTitular());
        titular.setEmail(validacion.validarEmail());
        titular.setAlias(validacion.validarAlias("Ingrese ALIAS CBU: "));
        titular.setCbu(validacion.validarCBU("Ingrese CBU: "));

        String consulta = "INSERT INTO titulares (cuil, nombre, email, alias, cbu) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, titular.getCuil());
            stmt.setString(2, titular.getNombre());
            stmt.setString(3, titular.getEmail());
            stmt.setString(4, titular.getAlias());
            stmt.setString(5, titular.getCbu());
            stmt.executeUpdate();
            System.out.println("Titular agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar titular: " + e.getMessage());
        }
    }

    // Eliminar un titular por CUIL de la base
    public void eliminarTitular() {
        System.out.print("Ingrese el CUIL del titular a eliminar: ");
        String cuil = scanner.nextLine();

        String consulta = "DELETE FROM titulares WHERE cuil = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, cuil);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Titular eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un titular con ese CUIL.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar titular: " + e.getMessage());
        }
    }
}
