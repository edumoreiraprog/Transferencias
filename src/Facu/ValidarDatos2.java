package Facu;

import java.util.Scanner;

public class ValidarDatos2 {
    Scanner scanner = new Scanner(System.in);

    public void validarGeneral() { 
        String aliasDEBITO = validarAlias("Ingrese ALIAS CBU DEBITO: ");
        String aliasCREDITO = validarAlias("Ingrese ALIAS CBU CREDITO: ");
        String cbuDEBITO = validarCBU("Ingrese NUMERO CBU DEBITO: ");
        String cbuCREDITO = validarCBU("Ingrese NUMERO CBU CREDITO: ");
        double importe = validarIMPORTE();
        String concepto = validarCONCEPTO("Ingrese el CONCEPTO: ");
        String motivo = validarMOTIVO();
        String referencia = validarREFERENCIA("Ingrese la REFERENCIA: ");
        String email = validarEMAIL("Ingrese el EMAIL: ");
        String titular = validarTITULAR("Ingrese el TITULAR: ");
        
        AliasCliente cliente = new AliasCliente(aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, importe, concepto, motivo, referencia, email, titular);
        BDCliente bdCliente = new BDCliente();
        bdCliente.insertAliasCliente(cliente);
    }
    
    private String validarAlias(String mensaje) {
        String alias = "";
        while (true) {
            System.out.print(mensaje);
            alias = scanner.nextLine();
            if (alias.equalsIgnoreCase("salir")) return alias;
    
            if (alias.length() < 22 && !alias.isEmpty()) {
                System.out.println("La longitud de la entrada es: " + alias.length());
                System.out.println("Dato ingresado: " + alias);
                return alias;
            } else if (alias.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: La longitud no puede ser más de 22 caracteres.");
            }
        }
    }
    
    private String validarCBU(String mensaje) {
        String cbu = "";
        while (true) {
            System.out.print(mensaje);
            cbu = scanner.nextLine();
            if (cbu.equalsIgnoreCase("salir")) return cbu;
    
            if (cbu.length() == 22) {
                if (cbu.matches("\\d+")) {
                    System.out.println("El número ingresado es: " + cbu);
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

    private String validarCONCEPTO(String mensaje){
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
    

    private String validarREFERENCIA(String mensaje){
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

    private String validarEMAIL(String mensaje){
        String emailValido = "";

        while (true) {
            System.out.print("Ingrese el EMAIL: ");
            emailValido = scanner.nextLine();

            if (emailValido.length() < 50 && emailValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + emailValido.length());
                return emailValido;
            } else if (emailValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del EMAIL.");
            }
        }
    }

    private String validarTITULAR(String mensaje){
        String titularValido = "";

        while (true) {
            System.out.print("Ingrese el TITULAR: ");
            titularValido = scanner.nextLine();

            if (titularValido.length() < 50 && titularValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + titularValido.length());
                return titularValido;
            } else if (titularValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de TITULARES.");
            }
        }
    }
}
