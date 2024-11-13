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

<<<<<<< HEAD
    public void exportar() {
        String consulta = "SELECT * FROM Transferencias WHERE Estado = 'pendiente'";
=======
    public void exportar(String filename) {
        String consulta = "SELECT * FROM Transferencias";
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
        StringBuilder datos = new StringBuilder();
        double totalImporte = 0.0;
        int fila = 1;

<<<<<<< HEAD
        // Definimos la ruta y el nombre del archivo
        String rutaArchivo = "data/Pagina/transferencias_export.txt";

=======
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
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
<<<<<<< HEAD

                String filaDatos = String.format("%22s%22s%44s%012d%-50s%-3s%-12s%-50s%-1s\n",
                        cbuDebito, cbuCredito, " ", (int) (importe * 100),
                        concepto, motivo, referencia, email, "1");

=======
                String titular = rs.getString("Titular");

                String filaDatos = String.format("%22s%22s%44s%012d%-50s%-3s%-12s%-50s%-1s\n",
                        cbuDebito, cbuCredito, " ", (int) (importe * 100),
                        concepto, motivo, referencia, email, titular);
                
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
                datos.append(filaDatos);
                totalImporte += importe;
                fila++;
            }

<<<<<<< HEAD
            // Crear el archivo en la ruta especificada
            try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
                fos.write(datos.toString().getBytes());
                fos.write(String.format("%05d%017d%194s\n", fila, (int) (totalImporte * 100), "").getBytes());
                System.out.println("Archivo exportado en: " + rutaArchivo);
=======
            try (FileOutputStream fos = new FileOutputStream(filename)) {
                fos.write(datos.toString().getBytes());
                fos.write(String.format("%05d%017d%194s\n", fila, (int) (totalImporte * 100), "").getBytes());
                System.out.println("Archivo exportado: " + filename);
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
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
