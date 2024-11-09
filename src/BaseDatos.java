import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
    // Atributo de conexión
    private static final String URL = "jdbc:sqlite:data/transferencias.sqlite";
    private Connection conn = null;

    // Conectar a la base de datos
    public void conectar() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos establecida.");
            inicializarTablas();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Cerrar conexión
    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Método para obtener la conexión (para uso en otras clases)
    public Connection getConnection() {
        return conn;
    }

    // Inicializar tablas
    private void inicializarTablas() {
        crearTabla("CREATE TABLE IF NOT EXISTS titulares (" +
                "cuil TEXT PRIMARY KEY, " +
                "nombre TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "alias TEXT NOT NULL, " +
                "cbu TEXT NOT NULL);");

        crearTabla("CREATE TABLE IF NOT EXISTS transferencias (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aliasDEBITO TEXT NOT NULL, " +
                "aliasCREDITO TEXT NOT NULL, " +
                "cbuDEBITO TEXT NOT NULL, " +
                "cbuCREDITO TEXT NOT NULL, " +
                "IMPORTE REAL NOT NULL, " +
                "CONCEPTO TEXT, " +
                "MOTIVO TEXT, " +
                "REFERENCIA TEXT, " +
                "EMAIL TEXT, " +
                "TITULAR TEXT NOT NULL);");
    }

    // Método para crear tablas de forma modular
    private void crearTabla(String sql) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}
