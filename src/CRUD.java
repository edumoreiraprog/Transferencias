import java.sql.*;
import java.util.Scanner;

public class CRUD {
    private Validacion validacion = new Validacion();
    private Statement statement = null;
    Scanner sc = new Scanner(System.in);

    // Getters and setters
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    // Métodos

    // Listar todos los titulares de la base de datos
    public void listarTitulares() {
        String consulta = "SELECT * FROM titulares";
        try {
            // Ejecuta la consulta y guarda lo que devuelve en resultado
            ResultSet resultado = statement.executeQuery(consulta);

            while (resultado.next()) {
                String cuil = resultado.getString("cuil");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String alias = resultado.getString("alias");
                String cbu = resultado.getString("cbu");

                System.out.println("CUIL: " + cuil + ", Nombre: " + nombre + ", Email: " + email + ", Alias: " + alias + ", CBU: " + cbu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Agregar un titular a la base de datos
    public void agregarTitular() {
        Titular titular = new Titular();

        titular.setCuil(validacion.validarCUIL());
        titular.setNombre(validacion.validarTITULAR());
        titular.setEmail(validacion.validarEMAIL());
        titular.setAlias(validacion.validarAlias("Ingrese ALIAS CBU: "));
        titular.setCbu(validacion.validarCBU("Ingrese CBU: "));
        titular.insertarSQL(this.statement);
    }

    // Eliminar un titular por CUIL de la base
    public void eliminarTitular() {
        try {
            System.out.print("Ingrese el cuil del titular a eliminar: ");
            String cuil = sc.nextLine();
            String consulta = "DELETE FROM titulares WHERE cuil = " + cuil;

            // Ejecuta la consulta y almacena la cantidad de filas afectadas
            int filasAfectadas = statement.executeUpdate(consulta);

            if (filasAfectadas > 0) {
                System.out.println("Titular eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el titular.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar por dni

}

