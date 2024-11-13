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
<<<<<<< HEAD

=======
        
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
        int opcion;
        boolean salir = false; // Variable de control para el ciclo principal

        do {
            menuPrincipal();
            opcion = Validacion.validarOpcionMenu(1, 5);
            System.out.println(opcion);

            switch (opcion) {
                case 1:
                    int opTitular;
                    do {
                        menuTitulares();
<<<<<<< HEAD
                        opTitular = scanner.nextInt(); // Leer opcion del submenu
=======
                        opTitular = scanner.nextInt(); // Leer opción del submenú
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opTitular) {
                            case 1:
                                // Agregar Titular
<<<<<<< HEAD
                                operaciones.agregarTitular();  // Llamar funcion para agregar titular
                                break;
                            case 2:
                                // Buscar Titular
                                operaciones.buscarTitular(Validacion.validarReferencia()); // Llamar funcion para buscar titular
                                break;
                            case 3:
                                // Actualizar Titular
                                operaciones.actualizarTitular(); // Llamar funcion para actualizar titular
                                break;
                            case 4:
                                // Eliminar Titular
                                operaciones.eliminarTitular(); // Llamar funcion para eliminar titular
                                break;
                            case 5:
                                // Listar todos los Titulares
                                operaciones.listarTitulares(); // Llamar funcion para listar titulares
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
=======
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
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
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
<<<<<<< HEAD
                                System.out.println("\nVolviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("\nOpción no válida.\n");
                                break;
                        }
                    } while (opTrasferencia != 3); // Sale si opTrasferencia es 3 (Volver al menú principal)
=======
                                System.out.println("Volviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("Opción no válida.\n");
                                break;
                        }
                    } while (opTrasferencia != 3);
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
                    break;

                case 3:
                    int opArchivo;
                    do {
                        menuArchivo();
                        opArchivo = scanner.nextInt(); // Leer opción del submenú
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opArchivo) {
                            case 1:
<<<<<<< HEAD
                                exportarArchivo.exportar();
                                abrirPaginaDescargas();
                                break;
                            case 2:
                                abrirPaginaDescargas();
                                break;
                            case 3:
                                System.out.println("\nVolviendo al menú principal...\n");
                                break;
                            default:
                                System.out.println("\nOpción no válida.\n");
                                break;
                        }
                    } while (opArchivo != 3); // Sale si opArchivo es 3 (Volver al menú principal)
                    break;

                case 4:
                    // Salir del programa
                    System.out.println("\nSaliendo del programa...\n");
                    salir = true;
                    break;
                default:
                    System.out.println("\n¡¡¡Opción ingresada incorrecta!!!\n");
                    break;
            }
        } while (!salir); // Termina el ciclo cuando la variable "salir" es true
=======
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
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
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
<<<<<<< HEAD
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
=======
>>>>>>> ca4b7c625ca6c0105627f05afc5216650361d373
    }

    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}