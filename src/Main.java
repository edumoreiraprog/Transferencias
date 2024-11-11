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

        do {
            menuPrincipal();
            opcion = Validacion.validarOpcionMenu(1, 5);
            System.out.println(opcion);

            switch (opcion) {
                case 1:
                    int opTitular;
                    do {
                        menuTitulares();
                        opTitular = scanner.nextInt(); // Leer opción del submenú
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opTitular) {
                            case 1:
                                // Agregar Titular
                                operaciones.agregarTitular();  // Llamar función para agregar titular
                                break;
                            case 2:
                                // Consultar Titular por ID
                                operaciones.buscarTitular();// Llamar función para buscar titular
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
                                // Volver al Menú Principal
                                System.out.println("Volviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("Opción no válida.\n");
                                break;
                        }
                    } while (opTitular != 6);
                    break;
                case 2:
                    int opTrasferencia;
                    do {
                        menuTransferencias();
                        opTrasferencia = scanner.nextInt(); // Leer opción del submenú
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opTrasferencia) {
                            case 1:
                                // Agregar transferencia
                                operaciones.insertarTransferencia(operaciones.crearTransferencia());
                                break;
                            case 2:
                                // Consultar historial de Transferencias
                                operaciones.listarTransferencias();
                                break;
                            case 3:
                                System.out.println("Volviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("Opción no válida.\n");
                                break;
                        }
                    } while (opTrasferencia != 3);
                    break;
                case 3:
                    int opArchivo;
                    do {
                        menuArchivo();
                        opArchivo = scanner.nextInt(); // Leer opción del submenú
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opArchivo) {
                            case 1:
                                //Generar archivo .txt para transferencias
                                exportarArchivo.exportar("Creacion01.txt");
                                break;
                            case 2:
                                System.out.println("Volviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("Opción no válida.\n");
                                break;
                        }
                    } while (opArchivo != 2);
                    break;
                default:
                    System.out.println("¡¡¡Opción ingresada incorrecta!!!\n");
            }
        } while (opcion != 3);
        scanner.close();
    }

    public static void menuPrincipal() {
        System.out.println("===== Menú Principal =====");
        System.out.println("1. Gestión de Titulares");
        System.out.println("2. Gestión de Transferencias");
        System.out.println("3. Gestión de archivo .txt");
        System.out.println("4. Salir");
        System.out.println("==========================");
        System.out.print("Seleccione una opción: ");
    }

    public static void menuTitulares() {
        System.out.println("==== Gestión de Titulares ====");
        System.out.println("1. Agregar Titular");
        System.out.println("2. Consultar Titular por ID");
        System.out.println("3. Actualizar Titular");
        System.out.println("4. Eliminar Titular");
        System.out.println("5. Listar todos los Titulares");
        System.out.println("6. Volver al Menú Principal");
        System.out.println("==============================");
        System.out.print("Seleccione una opción: ");
    }

    public static void menuTransferencias() {
        System.out.println("== Gestión de Transferencias ==");
        System.out.println("1. Agregar Transferencia");
        System.out.println("2. Consultar historial de Transferencias");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("===============================");
        System.out.print("Seleccione una opción: ");
    }

    public static void menuArchivo() {
        System.out.println("===== Gestión de Archivos =====");
        System.out.println("1. Generar archivo .txt para transferencias");
        System.out.println("2. Descargar ultimo archivo generado");
        System.out.println("3. Volver al Menú Principal");
        System.out.println("===============================");
        System.out.print("Seleccione una opción: ");
    }

    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}