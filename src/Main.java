import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BaseDatos base = new BaseDatos();
        // Conectamos la base de datos
        base.conectar();

        // Instanciamos OperacionesBD y le pasamos la conexión de la base
        // Esto para que pueda realizar las consultas SQL
        OperacionesBD operaciones = new OperacionesBD(base.getConnection());

        ExportarArchivo exportarArchivo = new ExportarArchivo();
        exportarArchivo.setConn(base.getConnection());

        int opcion;
        boolean salir = false; // Variable de control para el ciclo principal

        do {
            menuPrincipal();
            opcion = Validacion.validarOpcionMenu(1, 4);

            switch (opcion) {
                case 1:
                    int opTitular;
                    do {
                        menuTitulares();
                        opTitular = Validacion.validarOpcionMenu(1, 6); // Leer opción del submenu

                        switch (opTitular) {
                            case 1:
                                // Agregar Titular
                                operaciones.agregarTitular();  // Llamar función para agregar titular
                                break;
                            case 2:
                                // Buscar Titular
                                operaciones.buscarTitular(Validacion.validarReferencia()); // Llamar función para buscar titular
                                break;
                            case 3:
                                // Actualizar Titular
                                operaciones.actualizarTitular(); // Llamar función para actualizar titular
                                break;
                            case 4:
                                // Eliminar Titular
                                operaciones.eliminarTitular(); // Llamar función para eliminar titular
                                break;
                            case 5:
                                // Listar todos los Titulares
                                operaciones.listarTitulares(); // Llamar función para listar titulares
                                break;
                            case 6:
                                // Volver al Menu Principal
                                System.out.println("\nVolviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("\nOpción no válida.\n");
                                break;
                        }
                    } while (opTitular != 6); // Sale si opTitular es 6 (Volver al menú principal)
                    break;

                case 2:
                    int opTrasferencia;
                    do {
                        menuTransferencias();
                        opTrasferencia = Validacion.validarOpcionMenu(1, 3); // Leer opción del submenú
                        switch (opTrasferencia) {
                            case 1:
                                // Agregar transferencia
                                operaciones.insertarTransferencia(operaciones.crearTransferencia());
                                break;
                            case 2:
                                // Consultar Transferencias en la base
                                operaciones.listarTransferencias();
                                break;
                            case 3:
                                System.out.println("\nVolviendo al menú principal...");
                                break;
                            default:
                                System.out.println("\nOpción no válida.\n");
                                break;
                        }
                    } while (opTrasferencia != 3); // Sale si opTrasferencia es 3 (Volver al menú principal)
                    break;

                case 3:
                    int opArchivo;
                    do {
                        menuArchivo();
                        opArchivo = Validacion.validarOpcionMenu(1, 3); // Leer opción del submenú

                        switch (opArchivo) {
                            case 1:
                                exportarArchivo.exportar();
                                abrirPaginaDescargas();
                                break;
                            case 2:
                                abrirPaginaDescargas();
                                break;
                            case 3:
                                System.out.println("\nVolviendo al menú principal...");
                                break;
                            default:
                                System.out.println("\nOpción no válida.\n");
                                break;
                        }
                    } while (opArchivo != 3); // Sale si opArchivo es 3 (Volver al menú principal)
                    break;

                case 4:
                    // Salir del programa
                    base.cerrarConexion(); // Cerramos la conexión
                    System.out.println("\nSaliendo del programa...\n");
                    salir = true;
                    break;
                default:
                    System.out.println("\n¡¡¡Opción ingresada incorrecta!!!\n");
                    break;
            }
        } while (!salir); // Termina el ciclo cuando la variable "salir" es true
        scanner.close();
    }

    public static void menuPrincipal() {
        System.out.println("\n===== Menú Principal =====");
        System.out.println("1. Gestión de Titulares");
        System.out.println("2. Gestión de Transferencias");
        System.out.println("3. Gestión de archivo .txt");
        System.out.println("4. Salir");
        System.out.println("==========================");
    }

    public static void menuTitulares() {
        System.out.println("\n==== Gestión de Titulares ====");
        System.out.println("1. Agregar Titular");
        System.out.println("2. Consultar Titular por Referencia");
        System.out.println("3. Actualizar Titular");
        System.out.println("4. Eliminar Titular");
        System.out.println("5. Listar todos los Titulares");
        System.out.println("6. Volver al Menú Principal");
        System.out.println("==============================");
    }

    public static void menuTransferencias() {
        System.out.println("\n== Gestión de Transferencias ==");
        System.out.println("1. Agregar Transferencia");
        System.out.println("2. Consultar Transferencias");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("===============================");
    }

    public static void menuArchivo() {
        System.out.println("\n===== Gestión de Archivos =====");
        System.out.println("1. Generar archivo .txt para transferencias");
        System.out.println("2. Descargar ultimo archivo generado");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("===============================");
    }

    public static void abrirPaginaDescargas() {
        // Ruta al archivo HTML dentro del proyecto (ajusta esta ruta si es necesario)
        File pagina = new File("data/pagina/index.html");

        try {
            // Verificar si la clase Desktop es compatible en el sistema actual
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Intentar abrir el archivo si es un archivo HTML local
                if (pagina.exists()) {
                    desktop.browse(pagina.toURI()); // Abre el archivo en el navegador
                } else {
                    System.out.println("La página HTML no se encontró en la ruta especificada.");
                }
            } else {
                System.out.println("La clase Desktop no está soportada en este sistema.");
            }
        } catch (IOException e) {
            System.out.println("Error al intentar abrir la página: " + e.getMessage());
        }
    }
}