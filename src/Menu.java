import java.sql.*;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);

    Statement statement = null;

    // Setters
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void mostrarMenu() {
        int op;
        do {
            menuPrincipal();
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:

                    menuTitulares();

                    //System.out.print("\nPresione cualquier tecla para continuar...");
                    //sc.nextLine();
                    break;
                case 2:
                    //Agregar producto al carrito
                    System.out.print("Ingrese el nombre del producto: ");
                    String nProducto = sc.nextLine();
                    //agregarProducto(productos, carrito, nProducto);
                    System.out.print("Presione cualquier tecla para continuar...");
                    sc.nextLine();
                    break;
                case 3:
                    //Eliminar producto del carrito
                    System.out.print("Ingrese el nombre del producto: ");
                    String eProducto = sc.nextLine();
                    //eliminarProducto(carrito, eProducto);
                    System.out.print("Presione cualquier tecla para continuar...");
                    sc.nextLine();
                    break;
                case 4:
                    //Ver carrito
                    //mostrarCarrito(carrito);
                    System.out.print("\nPresione cualquier tecla para continuar...");
                    sc.nextLine();
                    break;
                case 5:
                    //Ir a pagar
                    //mostrarTicket(cajas, carrito, sc);
                    op = 6;
                    break;
                case 6:
                    //Salir
                    System.out.println("Gracias, vuelva prontos.");
                    break;
                default:
                    System.out.println("¡¡¡Opción ingresada incorrecta!!!");
                    System.out.print("Presione cualquier tecla para continuar...");
                    sc.nextLine();
            }
        } while (op != 6);
    }

    public static void menuPrincipal() {
        System.out.println("===== Menú Principal =====");
        System.out.println("1. Gestión de Titulares");
        System.out.println("2. Gestión de Transferencias");
        System.out.println("3. Gestión de archivo .txt");
        System.out.println("4. Salir");
        System.out.println("==========================");
        System.out.println("Seleccione una opción: ");
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
        System.out.println("Seleccione una opción: ");
    }

    public static void menuTransferencias() {
        System.out.println("== Gestión de Transferencias ==");
        System.out.println("1. Agregar Transferencia");
        System.out.println("3. Listar las Transferencias actuales");
        System.out.println("2. Consultar historial de Transferencias");
        System.out.println("4. Volver al Menú Principal");
        System.out.println("===============================");
        System.out.println("Seleccione una opción: ");
    }

    public static void menuArchivo() {
        System.out.println("===== Gestión de Archivos =====");
        System.out.println("1. Generar archivo .txt para transferencias");
        System.out.println("3. Descargar ultimo archivo generado");
        System.out.println("4. Volver al Menú Principal");
        System.out.println("===============================");
        System.out.println("Seleccione una opción: ");
    }
}
