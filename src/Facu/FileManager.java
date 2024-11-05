package Facu;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FileManager {
    public void writeToFile(String filename) {
        String query = "SELECT * FROM Cliente";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery();
             FileWriter writer = new FileWriter(filename)) {
            while (rs.next()) {
                String cbuDEBITO = rs.getString("cbuDEBITO");
                String cbuCREDITO = rs.getString("cbuDEBITO");
                String IMPORTE = rs.getString("IMPORTE");
                String CONCEPTO = rs.getString("CONCEPTO");
                String MOTIVO = rs.getString("MOTIVO");
                String REFERENCIA = rs.getString("REFERENCIA");
                String EMAIL = rs.getString("EMAIL");
                String TITULAR = rs.getString("TITULAR");
                String line = String.format("%-20s %-20s %-12s %-20s %-20s %-20s %-30s %-20s",cbuDEBITO,cbuCREDITO,IMPORTE,CONCEPTO,MOTIVO,REFERENCIA,EMAIL,TITULAR);
                writer.write(line + "\n");
            }
            System.out.println("Datos guardados en el archivo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String espacios(int cantidad) {
        return " ".repeat(cantidad); // Desde Java 11
    }
}
