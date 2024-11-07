import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseDatos base = new BaseDatos();
        CRUD crud = new CRUD();

        // Conectamos la base de datos
        base.conectar();
        // le pasamos el statement de la base al crud para que pueda realizar las consultas SQL
        crud.setStatement(base.getStatement());

        // Algunas consultas

        //crud.listarTitulares();
        //crud.agregarTitular();
        //crud.listarTitulares();
        //crud.eliminarTitular();
        //crud.listarTitulares();
    }
}