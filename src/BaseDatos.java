import java.sql.*;

public class BaseDatos {
    private String url = "jdbc:sqlite:data/transferencias.sqlite";
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    // Getters and setters
    public String getUrl() {
        return url;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void conectar(){
        try {
            // Abre la conexión a la base de datos
            conn = DriverManager.getConnection(url);
            statement = conn.createStatement();
            System.out.println("Conexión establecida.");

            // Crear las tablas solo si no existen
            inicializarTablaTitulares();
            inicializarTablaTransferencias();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    private void inicializarTablaTitulares() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS titulares (" +
                    "cuil TEXT PRIMARY KEY," + // CUIL como clave primaria
                    "nombre TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "alias TEXT NOT NULL" +
                    ");";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla titulares: " + e.getMessage());
        }
    }

    private void inicializarTablaTransferencias() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS transferencias (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aliasDEBITO TEXT NOT NULL," +
                    "aliasCREDITO TEXT NOT NULL," +
                    "cbuDEBITO TEXT NOT NULL," +
                    "cbuCREDITO TEXT NOT NULL," +
                    "IMPORTE REAL NOT NULL," +
                    "CONCEPTO TEXT," +
                    "MOTIVO TEXT," +
                    "REFERENCIA TEXT," +
                    "EMAIL TEXT," +
                    "TITULAR TEXT NOT NULL" +
                    ");";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla transferencias: " + e.getMessage());
        }
    }
}








