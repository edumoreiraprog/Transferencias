
public class Main {
    public static void main(String[] args) {

        BaseDatos base = new BaseDatos();
        base.conectar();

        // Instanciamos la clase CRUD utilizaremos sus métodos.
        CRUD crud = new CRUD();

        // le pasamos el statement de la base para que pueda realizar las consultas SQL
        crud.setStatement(base.getStatement());


        //crud.insertarTitular("2045279879", "edumoreira.mp", "edu@gmail.com", "Eduardo Moreira");
        crud.listarTitulares(base.getStatement());

    }
}