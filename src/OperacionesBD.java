import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OperacionesBD {
    private final Connection conexion;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor para recibir la conexión
    public OperacionesBD(Connection conexion) {
        this.conexion = conexion;
    }

    // Métodos

    // Listar todos los titulares de la base de datos
    public void listarTitulares() {
        String consulta = "SELECT * FROM Titulares";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta);
             ResultSet resultado = stmt.executeQuery()) {

            System.out.println("\n===== Lista de Titulares =====");
            while (resultado.next()) {
                String id = resultado.getString("TitularesID");
                String cuil = resultado.getString("Cuil");
                String nombre = resultado.getString("Nombre");
                String email = resultado.getString("Email");
                String alias = resultado.getString("Alias");
                String cbu = resultado.getString("Cbu");
                String referencia = resultado.getString("Referencia");

                System.out.println("CUIL: " + cuil + ", Nombre: " + nombre + ", Email: " + email + ", REFERENCIA: " + referencia);
            }
            System.out.println("==============================");
        } catch (SQLException e) {
            System.out.println("\nError al listar titulares: " + e.getMessage());
        }
    }

    // Agregar un titular a la base de datos
    public void agregarTitular() {
        Titular titular = new Titular();

        System.out.println();
        titular.setCuil(Validacion.validarCUIL());
        titular.setNombre(Validacion.validarTitular());
        titular.setEmail(Validacion.validarEmail());
        titular.setAlias(Validacion.validarAlias("Ingrese ALIAS CBU: "));
        titular.setCbu(Validacion.validarCBU("Ingrese CBU: "));
        titular.setReferencia(Validacion.validarReferencia());

        String consulta = "INSERT INTO titulares (Cuil, Nombre, Email, Alias, Cbu, Referencia) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, titular.getCuil());
            stmt.setString(2, titular.getNombre());
            stmt.setString(3, titular.getEmail());
            stmt.setString(4, titular.getAlias());
            stmt.setString(5, titular.getCbu());
            stmt.setString(6, titular.getReferencia());

            // Ejecuta la consulta
            stmt.executeUpdate();
            System.out.println("Titular agregado exitosamente.\n\n");
        } catch (SQLException e) {
            System.out.println("Error al agregar titular: " + e.getMessage());
        }
    }

    // Eliminar un titular por CUIL de la base
    public void eliminarTitular() {
        String referencia = Validacion.validarReferencia();
        buscarTitular(referencia);
        System.out.println("\n¿Esta seguro que desea eliminar?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.println("===============================");
        int respuesta = Validacion.validarOpcionMenu(1, 2);

        if (respuesta == 1) {
            String consulta = "DELETE FROM Titulares WHERE Referencia = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
                stmt.setString(1, referencia);

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("\nTitular eliminado exitosamente.");
                } else {
                    System.out.println("\nNo se encontró un titular con esa Referencia.");
                }
            } catch (SQLException e) {
                System.out.println("\nError al eliminar titular: " + e.getMessage());
            }
        } else {
            System.out.println("\nNo se eliminó el titular.");
        }
    }

    public void buscarTitular(String referencia){
        String consulta = "SELECT * FROM Titulares WHERE Referencia = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)){
            stmt.setString(1, referencia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String cbu = rs.getString("Cbu");
                    String alias = rs.getString("Alias");
                    String email = rs.getString("Email");

                    System.out.println("\nTitular encontrado: ");
                    System.out.println("Nombre: " + nombre + ", Cbu: " + cbu + ", Alias: " + alias + ", Email: " + email);
                } else {
                    System.out.println("\nNo se encontró un titular con la Referencia " + referencia);
                }
            }
        } catch (SQLException e) {
            System.out.println("\nError al buscar el titular: " + e.getMessage());
        }
    }

    public void actualizarTitular(){
        System.out.println();
        String modificarCuil = Validacion.validarCUIL();
        String nuevoNombre = Validacion.validarTitular();
        String nuevoEmail = Validacion.validarEmail();
        String nuevoAlias = Validacion.validarAlias("Ingrese ALIAS CBU: ");
        String nuevoCbu = Validacion.validarCBU("Ingrese CBU: ");
        String nuevaReferencia = Validacion.validarReferencia();

        String consulta = "UPDATE Titulares SET nombre = ?, email = ?, alias = ?, cbu = ?, referencia = ? WHERE cuil = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevoEmail);
            stmt.setString(3, nuevoAlias);
            stmt.setString(4, nuevoCbu);
            stmt.setString(5, nuevaReferencia);
            stmt.setString(6, modificarCuil);

            // Ejecutamos la consulta de actualización
            int filasAfectadas = stmt.executeUpdate();

            // Verificamos si la actualización fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("\nTitular actualizado con éxito.");
            } else {
                System.out.println("\n No se encontró un titular con el Cuil " + modificarCuil);
            }

        } catch (Exception e) {
            System.out.println("Error al modificar el titular: " + e.getMessage());
        }
    }

    public Transferencia crearTransferencia() {
        Transferencia transferencia = null;

        // Consulta SQL para obtener los datos del titular que envía el dinero
        String sqlDebito = "SELECT Referencia, cbu FROM Titulares WHERE Referencia = ?";
        String sqlCredito = "SELECT Referencia, cbu, email, nombre FROM Titulares WHERE Referencia = ?";

        try (
                PreparedStatement pstmtDebito = conexion.prepareStatement(sqlDebito);
                PreparedStatement pstmtCredito = conexion.prepareStatement(sqlCredito);
        ) {
            System.out.println("Ingrese Referencia Debito: ");
            String refDebito = scanner.nextLine();

            // Buscar datos del titular de débito
            pstmtDebito.setString(1, refDebito);
            ResultSet rsDebito = pstmtDebito.executeQuery();

            if (rsDebito.next()) {
                String aliasDEBITO = rsDebito.getString("Referencia");
                String cbuDEBITO = rsDebito.getString("Cbu");

                System.out.println("Ingrese Referencia Crédito: ");
                String refCredito = scanner.nextLine();

                // Buscar datos del titular de crédito
                pstmtCredito.setString(1, refCredito);
                ResultSet rsCredito = pstmtCredito.executeQuery();

                if (rsCredito.next()) {
                    String aliasCREDITO = rsCredito.getString("Referencia");
                    String cbuCREDITO = rsCredito.getString("Cbu");
                    String email = rsCredito.getString("Email");
                    String titular = rsCredito.getString("Nombre");

                    // Pedir datos restantes al usuario
                    Scanner scanner = new Scanner(System.in);

                    double importe = Validacion.validarImporte();
                    scanner.nextLine();  // Consumir la nueva línea

                    String concepto = Validacion.validarConcepto();

                    System.out.print("Ingrese el motivo: ");
                    String motivo = Validacion.validarMotivo();

                    System.out.print("Ingrese la referencia: ");
                    String referencia = Validacion.validarReferencia();

                    // Crear la instancia de Transferencia
                    transferencia = new Transferencia(aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO,
                            importe, concepto, motivo, referencia, email, titular, "pendiente");
                } else {
                    System.out.println("No se encontró el titular de crédito con la referencia proporcionada.");
                }
            } else {
                System.out.println("No se encontró el titular de débito la referencia proporcionada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos de la base: " + e.getMessage());
        }

        return transferencia;
    }

    // Método para insertar la transferencia en la base de datos
    public void insertarTransferencia(Transferencia transferencia) {
        String sql = "INSERT INTO Transferencias (AliasDebito, AliasCredito, CbuDebito, CbuCredito, " +
                "Importe, Concepto, Motivo, Referencia, Email, Titular, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
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
            pstmt.setString(11, transferencia.getEstado());

            pstmt.executeUpdate();
            System.out.println("Transferencia insertada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar la transferencia: " + e.getMessage());
        }
    }

    // Listar todas las transferencias de la base de datos
    public void listarTransferencias() {
        String consulta = "SELECT * FROM Transferencias";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta);
             ResultSet resultado = stmt.executeQuery()) {

            System.out.println("\n===== Lista de Transferencias =====\n");
            while (resultado.next()) {
                String aliasDEBITO = resultado.getString("aliasDEBITO");
                String aliasCREDITO = resultado.getString("aliasCREDITO");
                String cbuDEBITO = resultado.getString("cbuDEBITO");
                String cbuCREDITO = resultado.getString("cbuCREDITO");
                double importe = resultado.getDouble("importe");
                String concepto = resultado.getString("concepto");
                String motivo = resultado.getString("motivo");
                String referencia = resultado.getString("referencia");
                String email = resultado.getString("email");
                String titular = resultado.getString("titular");

                System.out.println("Alias Débito: " + aliasDEBITO + ", \nAlias Crédito: " + aliasCREDITO);
                System.out.println("CBU Débito: " + cbuDEBITO + ", \nCBU Crédito: " + cbuCREDITO);
                System.out.println("Importe: " + importe + ", \nConcepto: " + concepto + ", \nMotivo: " + motivo);
                System.out.println("Referencia: " + referencia + ", \nEmail: " + email + ", \nTitular: " + titular);
                System.out.println("----------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar transferencias: " + e.getMessage());
        }
    }
}
