public class Main {
    public static void main(String[] args) {
        BaseDatos base = new BaseDatos();
        // Conectamos la base de datos
        base.conectar();

        // Instanciamos OperacionesBD y le pasamos la conexión de la base
        // Esto para que pueda realizar las consultas SQL
        OperacionesBD operaciones = new OperacionesBD(base.getConnection());

        // Algunas consultas

        operaciones.listarTitulares();
        //operaciones.agregarTitular();
        //operaciones.listarTitulares();
        //operaciones.eliminarTitular();
        System.out.println("");
        //operaciones.insertarTransferencia(operaciones.crearTransferencia("edu.bien", "facu.facundo"));
        operaciones.listarTransferencias();
    }
}