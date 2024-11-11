import java.util.Scanner;
import java.util.regex.Pattern;

public class Validacion {
    private static final Scanner scanner = new Scanner(System.in);

    // Expresiones regulares
    private static final String CUIL_REGEX = "^\\d{11}$";
    private static final String CBU_REGEX = "^\\d{22}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Métodos de validación
    public static String validarCUIL() {
        return validarEntrada("Ingrese CUIL/CUIT: ", CUIL_REGEX,
                "Error: Debe contener solo 10 dígitos numéricos.");
    }

    public static String validarAlias(String mensaje) {
        return validarLongitud("Alias", mensaje, 1, 22);
    }

    public static String validarCBU(String mensaje) {
        return validarEntrada(mensaje, CBU_REGEX,
                "Error: Debe contener exactamente 22 dígitos numéricos.");
    }

    public static double validarImporte() {
        while (true) {
            System.out.print("Ingrese IMPORTE: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
                continue;
            }

            try {
                // Transformamos a double
                double importe = Double.parseDouble(input);
                if (importe > 0) {
                    return importe;
                } else {
                    System.out.println("Error: el IMPORTE debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }

    public static String validarConcepto() {
        return validarLongitud("Concepto", "Ingrese el CONCEPTO: ", 1, 50);
    }

    public static String validarMotivo() {
        final String[] MOTIVOS = {
                "ALQ (Alquileres)", "CUO (Cuotas)", "EXP (Expensas)", "FAC (Facturas)",
                "PRE (Préstamos)", "SEG (Seguros)", "HON (Honorarios)", "VAR (Varios)"
        };

        while (true) {
            System.out.println("\nSeleccione el número del MOTIVO (o 'salir' para terminar): ");
            for (int i = 0; i < MOTIVOS.length; i++) {
                System.out.println((i + 1) + ") " + MOTIVOS[i]);
            }

            String input = scanner.nextLine();

            try {
                int opcion = Integer.parseInt(input);
                if (opcion >= 1 && opcion <= MOTIVOS.length) {
                    System.out.println("Has seleccionado " + MOTIVOS[opcion - 1]);
                    return MOTIVOS[opcion - 1].substring(0, 3); // Retorna las primeras 3 letras
                } else {
                    System.out.println("Número no válido, inténtelo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }

    public static String validarReferencia() {
        return validarLongitud("Referencia", "Ingrese la REFERENCIA: ", 1, 12);
    }

    public static String validarEmail() {
        return validarEntrada("Ingrese el EMAIL: ", EMAIL_REGEX,
                "Error: Formato de correo electrónico no válido.");
    }

    public static String validarTitular() {
        return validarLongitud("Titular", "Ingrese el TITULAR: ", 1, 1);
    }

    public static int validarOpcionMenu(int min, int max) {
        while (true) {
            System.out.print("Seleccione una opción del menú: ");
            String input = scanner.nextLine();

            try {
                int opcion = Integer.parseInt(input);
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    System.out.println("Error: La opción debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }

    // Métodos privados para validación modular
    private static String validarEntrada(String mensaje, String regex, String error) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine();

            if (Pattern.matches(regex, input)) {
                return input;
            } else {
                System.out.println(error);
            }
        }
    }

    private static String validarLongitud(String nombreCampo, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine();

            if (!input.isEmpty() && input.length() >= min && input.length() <= max) {
                return input;
            } else if (input.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.printf("Error: La longitud del %s debe estar entre %d y %d caracteres.%n", nombreCampo, min, max);
            }
        }
    }
}
