import java.util.Scanner;

public class Validacion {
    Scanner scanner = new Scanner(System.in);

    // Métodos
    public String validarCUIL() {
        String cuil = "";

        while (true) {
            System.out.print("Ingrese CUIL/CUIT: ");
            cuil = scanner.nextLine();

            if (cuil.length() == 10) {
                if (cuil.matches("\\d+")) {
                    return cuil;
                } else {
                    System.out.println("Error: La entrada debe contener solo números.");
                }
            } else {
                System.out.println("Error: La longitud debe ser igual a 10 caracteres.");
            }
        }
    }

    public String validarAlias(String mensaje) {
        String alias = "";
        while (true) {
            System.out.print(mensaje);
            alias = scanner.nextLine();

            if (alias.length() <= 22 && !alias.isEmpty()) {
                return alias;
            } else if (alias.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: La longitud no puede ser más de 22 caracteres.");
            }
        }
    }

    public String validarCBU(String mensaje) {
        String cbu = "";
        while (true) {
            System.out.print(mensaje);
            cbu = scanner.nextLine();

            if (cbu.length() == 22) {
                if (cbu.matches("\\d+")) {
                    return cbu;
                } else {
                    System.out.println("Error: La entrada debe contener solo números.");
                }
            } else {
                System.out.println("Error: La longitud debe ser igual a 22 caracteres.");
            }
        }
    }

    private double validarIMPORTE(){
        double importeActual = 0;

        while (true) {
            System.out.print("Ingrese IMPORTE: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
                continue; // Volver al inicio del bucle
            }

            try {
                importeActual = Double.parseDouble(input);

                if (importeActual <= 0) {
                    System.out.println("Error: el IMPORTE no puede ser igual o menor a 0.");
                } else {
                    System.out.println("El IMPORTE ingresado es: " + importeActual);
                    return importeActual;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }

    public String validarCONCEPTO(){
        String conceptoValido = "";

        while (true) {
            System.out.print("Ingrese el CONCEPTO: ");
            conceptoValido = scanner.nextLine();

            if (conceptoValido.length() < 50 && conceptoValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + conceptoValido.length());
                System.out.println("El dato ingresado es: "+conceptoValido);
                return conceptoValido;
            } else if (conceptoValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del CONCEPTO.");
            }
        }
    }

    // Revisar validarMOTIVO
    public String validarMOTIVO() {
        final String[] MOTIVOS = {
                "ALQ (Alquileres)",
                "CUO (Cuotas)",
                "EXP (Expensas)",
                "FAC (Facturas)",
                "PRE (Préstamos)",
                "SEG (Seguros)",
                "HON (Honorarios)",
                "VAR (Varios)"
        };

        String motivoSeleccionado = null;

        while (true) {
            System.out.println("\nSeleccione el número del MOTIVO (o 'salir' para terminar): ");
            for (int i = 0; i < MOTIVOS.length; i++) {
                System.out.println((i + 1) + ") " + MOTIVOS[i]);
            }

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                return null;
            }

            try {
                int opcion = Integer.parseInt(input);
                if (opcion >= 1 && opcion <= MOTIVOS.length) {
                    motivoSeleccionado = MOTIVOS[opcion - 1].substring(0, 3); // Toma las primeras 3 letras
                    System.out.println("Has seleccionado " + MOTIVOS[opcion - 1]);
                    return motivoSeleccionado;
                } else {
                    System.out.println("Número no válido, inténtelo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }

    public String validarREFERENCIA(){
        String referenciaValido = "";

        while (true) {
            System.out.print("Ingrese la REFERENCIA: ");
            referenciaValido = scanner.nextLine();

            if (referenciaValido.length() < 30 && referenciaValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + referenciaValido.length());
                return referenciaValido;
            } else if (referenciaValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de la REFERENCIA.");
            }
        }
    }

    public String validarEMAIL(){
        String email = "";

        while (true) {
            System.out.print("Ingrese el EMAIL: ");
            email = scanner.nextLine();

            if (email.length() < 50 && email.length() != 0) {
                return email;
            } else if (email.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del EMAIL.");
            }
        }
    }

    public String validarTITULAR(){
        String titular = "";

        while (true) {
            System.out.print("Ingrese el TITULAR: ");
            titular = scanner.nextLine();

            if (titular.length() < 50 && titular.length() != 0) {
                return titular;
            } else if (titular.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de TITULARES.");
            }
        }
    }
}
