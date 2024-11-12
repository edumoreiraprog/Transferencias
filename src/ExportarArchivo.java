/*
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportarArchivo {
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void exportar() {
        String consulta = "SELECT * FROM Transferencias WHERE Estado = 'pendiente'";
        StringBuilder datos = new StringBuilder();
        double totalImporte = 0.0;
        int fila = 1;

        // Definimos la ruta y el nombre del archivo
        String rutaArchivo = "data/Pagina/transferencias_export.txt";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String cbuDebito = rs.getString("CbuDebito");
                String cbuCredito = rs.getString("CbuCredito");
                double importe = rs.getDouble("Importe");
                String concepto = rs.getString("Concepto");
                String motivo = rs.getString("Motivo");
                String referencia = rs.getString("Referencia");
                String email = rs.getString("Email");

                String filaDatos = String.format("%22s%22s%44s%012d%-50s%-3s%-12s%-50s%-1s\r\n",
                        cbuDebito, cbuCredito, " ", (int) (importe * 100),
                        concepto, motivo, referencia, email, "1");

                datos.append(filaDatos);
                totalImporte += importe;
                fila++;
            }

            // Crear el archivo en la ruta especificada
            try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
                fos.write(datos.toString().getBytes());
                fos.write(String.format("%05d%017d%194s\r\n", fila, (int) (totalImporte * 100), "").getBytes());
                System.out.println("Archivo exportado en: " + rutaArchivo);
            } catch (IOException e) {
                System.out.println("Error al guardar archivo: " + e.getMessage());
            }

            String update = "UPDATE Transferencias SET Estado = 'completado'";
            PreparedStatement pstm = conn.prepareStatement(update);
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL: " + e.getMessage());
        }
    }
}


 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportarArchivo {
    private Connection conn;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void exportar() {
        String consulta = "SELECT * FROM Transferencias WHERE Estado = 'pendiente'";
        StringBuilder datos = new StringBuilder();
        BigDecimal totalImporte = BigDecimal.ZERO;  // Usamos BigDecimal para mayor precisión
        int fila = 1;

        // Definimos la ruta y el nombre del archivo
        String rutaArchivo = "data/Pagina/transferencias_export.txt";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String cbuDebito = rs.getString("CbuDebito");
                String cbuCredito = rs.getString("CbuCredito");
                BigDecimal importe = rs.getBigDecimal("Importe"); // Obtener importe como BigDecimal
                String concepto = rs.getString("Concepto");
                String motivo = rs.getString("Motivo");
                String referencia = rs.getString("Referencia");
                String email = rs.getString("Email");

                // Convertir el importe a centavos para el archivo
                int importeCentavos = importe.multiply(BigDecimal.valueOf(100)).intValue();

                String filaDatos = String.format("%22s%22s%44s%012d%-50s%-3s%-12s%-50s%-1s\r\n",
                        cbuDebito, cbuCredito, " ", importeCentavos,
                        concepto, motivo, referencia, email, "1");

                datos.append(filaDatos);
                totalImporte = totalImporte.add(importe); // Sumar el importe a totalImporte
                fila++;
            }

            // Crear el archivo en la ruta especificada
            try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
                fos.write(datos.toString().getBytes());

                // Convertir totalImporte a centavos para la última línea
                int totalCentavos = totalImporte.multiply(BigDecimal.valueOf(100)).intValue();
                fos.write(String.format("%05d%017d%194s\r\n", fila, totalCentavos, "").getBytes());
                System.out.println("Archivo exportado en: " + rutaArchivo);
            } catch (IOException e) {
                System.out.println("Error al guardar archivo: " + e.getMessage());
            }

            // Actualizar el estado de las transferencias en la base de datos
            String update = "UPDATE Transferencias SET Estado = 'completado'";
            PreparedStatement pstm = conn.prepareStatement(update);
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en consulta SQL: " + e.getMessage());
        }
    }
}
