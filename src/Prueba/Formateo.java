package Prueba;

public class Formateo {

    public static String strpadLeft ( String cadena, String caracter, int largo ) {
        if ( cadena.length() < largo ) {
            largo -= cadena.length();
            while ( largo-- > 0) {
                cadena = caracter + cadena;
            }
        }
        return cadena;
    }

// Uso de la función
// String cadena = Fn.strpadLeft ("HOLA", "!", 10);

// System.out.println( cadena );

// Imprime por consola !!!!!!HOLA
}
