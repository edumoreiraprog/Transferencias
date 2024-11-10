package Prueba;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String entrada = scanner.nextLine();

        System.out.println(Formateo.strpadLeft(entrada, " ", 50));
        entrada = scanner.nextLine();
        System.out.println(Formateo.strpadLeft(entrada, "0", 10));
    }
}
