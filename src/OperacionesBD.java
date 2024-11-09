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
        String consulta = "SELECT * FROM titulares";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta);
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

        titular.setCuil(Validacion.validarCUIL());
        titular.setNombre(Validacion.validarTitular());
        titular.setEmail(Validacion.validarEmail());
        titular.setAlias(Validacion.validarAlias("Ingrese ALIAS CBU: "));
        titular.setCbu(Validacion.validarCBU("Ingrese CBU: "));

        String consulta = "INSERT INTO titulares (cuil, nombre, email, alias, cbu) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
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
        try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
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

    public Transferencia crearTransferencia(String aliasDebito, String aliasCredito) {
        Transferencia transferencia = null;

        // Consulta SQL para obtener los datos del titular que envía el dinero
        String sqlDebito = "SELECT alias, cbu FROM titulares WHERE alias = ?";
        String sqlCredito = "SELECT alias, cbu, email, nombre FROM titulares WHERE alias = ?";

        try (
                PreparedStatement pstmtDebito = conexion.prepareStatement(sqlDebito);
                PreparedStatement pstmtCredito = conexion.prepareStatement(sqlCredito);
        ) {
            // Buscar datos del titular de débito
            pstmtDebito.setString(1, aliasDebito);
            ResultSet rsDebito = pstmtDebito.executeQuery();

            if (rsDebito.next()) {
                String aliasDEBITO = rsDebito.getString("alias");
                String cbuDEBITO = rsDebito.getString("cbu");

                // Buscar datos del titular de crédito
                pstmtCredito.setString(1, aliasCredito);
                ResultSet rsCredito = pstmtCredito.executeQuery();

                if (rsCredito.next()) {
                    String aliasCREDITO = rsCredito.getString("alias");
                    String cbuCREDITO = rsCredito.getString("cbu");
                    String email = rsCredito.getString("email");
                    String titular = rsCredito.getString("nombre");

                    // Pedir datos restantes al usuario
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Ingrese el importe: ");
                    double importe = Validacion.validarImporte();
                    scanner.nextLine();  // Consumir la nueva línea

                    System.out.print("Ingrese el concepto: ");
                    String concepto = Validacion.validarConcepto();

                    System.out.print("Ingrese el motivo: ");
                    String motivo = Validacion.validarMotivo();

                    System.out.print("Ingrese la referencia: ");
                    String referencia = Validacion.validarReferencia();

                    // Crear la instancia de Transferencia
                    transferencia = new Transferencia(aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO,
                            importe, concepto, motivo, referencia, email, titular);
                } else {
                    System.out.println("No se encontró el titular de crédito con el alias proporcionado.");
                }
            } else {
                System.out.println("No se encontró el titular de débito con el alias proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos de la base: " + e.getMessage());
        }

        return transferencia;
    }

    // Método para insertar la transferencia en la base de datos
    public void insertarTransferencia(Transferencia transferencia) {
        String sql = "INSERT INTO transferencias (aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, " +
                "IMPORTE, CONCEPTO, MOTIVO, REFERENCIA, EMAIL, TITULAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            pstmt.executeUpdate();
            System.out.println("Transferencia insertada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar la transferencia: " + e.getMessage());
        }
    }

    // Listar todas las transferencias de la base de datos
    public void listarTransferencias() {
        String consulta = "SELECT * FROM transferencias";
        try (PreparedStatement stmt = conexion.prepareStatement(consulta);
             ResultSet resultado = stmt.executeQuery()) {

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

                System.out.println("Alias Débito: " + aliasDEBITO + ", Alias Crédito: " + aliasCREDITO);
                System.out.println("CBU Débito: " + cbuDEBITO + ", CBU Crédito: " + cbuCREDITO);
                System.out.println("Importe: " + importe + ", Concepto: " + concepto + ", Motivo: " + motivo);
                System.out.println("Referencia: " + referencia + ", Email: " + email + ", Titular: " + titular);
                System.out.println("----------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar transferencias: " + e.getMessage());
        }
    }
}