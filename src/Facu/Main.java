package Facu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ValidarDatos2 validarDatos2 = new ValidarDatos2();
        //FileManager exportarTXT = new FileManager();
        // Llamar al método estático connect
        Connection conexion = DatabaseConnection.connect();
        
        if (conexion != null) {
            validarDatos2.validarGeneral();
            //exportarTXT.writeToFile("FacturaFinal.txt");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
}
