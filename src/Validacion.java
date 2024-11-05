import java.util.Scanner;

public class Validacion {
    Scanner scanner = new Scanner(System.in);

    /*
    public void validarGeneral() {
        String aliasDEBITO = validarAlias();
        String aliasCREDITO = validarAlias("Ingrese ALIAS CBU CREDITO: ");
        String cbuDEBITO = validarCBU("Ingrese NUMERO CBU DEBITO: ");
        String cbuCREDITO = validarCBU("Ingrese NUMERO CBU CREDITO: ");
        double importe = validarIMPORTE();
        String concepto = validarCONCEPTO("Ingrese el CONCEPTO: ");
        String motivo = validarMOTIVO();
        String referencia = validarREFERENCIA("Ingrese la REFERENCIA: ");
        String email = validarEMAIL("Ingrese el EMAIL: ");
        String titular = validarTITULAR("Ingrese el TITULAR: ");

        Titular cliente = new Titular(aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, importe, concepto, motivo, referencia, email, titular);
        BDCliente bdCliente = new BDCliente();
        bdCliente.insertAliasCliente(cliente);
    }

     */

    private String validarAlias() {
        String alias = "";
        while (true) {
            System.out.print("Ingrese su alias o (salir) para salir: ");
            alias = scanner.nextLine();

            if (alias.equalsIgnoreCase("salir")) return alias;

            // Verifica que la longitud sea menor o igual a 22 caracteres
            if (alias.length() <= 22 && alias.matches("[A-Za-z0-9.@_-]+")) {
                System.out.println("Alias válido.");
                return alias;
            } else if (alias.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else if (alias.length() > 22) {
                System.out.println("Error: La longitud no puede ser mayor a 22 caracteres.");
            } else {
                System.out.println("Error: El alias contiene caracteres no permitidos. Solo se permiten letras, números y los símbolos . @ _ -");
            }
        }
    }
}
