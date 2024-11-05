package Facu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDCliente {
    public void insertAliasCliente(AliasCliente cliente) {
        String sql = "INSERT INTO Cliente (aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, IMPORTE, CONCEPTO, MOTIVO, REFERENCIA, EMAIL, TITULAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getAliasDEBITO());
            pstmt.setString(2, cliente.getAliasCREDITO());
            pstmt.setString(3, cliente.getCbuDEBITO());
            pstmt.setString(4, cliente.getCbuCREDITO());
            pstmt.setDouble(5, cliente.getImporte());
            pstmt.setString(6, cliente.getConcepto());
            pstmt.setString(7, cliente.getMotivo());
            pstmt.setString(8, cliente.getReferencia());
            pstmt.setString(9, cliente.getEmail());
            pstmt.setString(10, cliente.getTitular());
            pstmt.executeUpdate();
            System.out.println("Alias guardado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }
}
