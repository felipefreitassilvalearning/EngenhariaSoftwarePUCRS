package Aula_11;

import java.util.Scanner;

public class TrocaVogal {
    public static void main(String[] args) {
        String palavra;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Escreva uma palavra: ");
            palavra = input.nextLine().toLowerCase();

            palavra = palavra.replaceAll("a", "x");
            palavra = palavra.replaceAll("e", "x");
            palavra = palavra.replaceAll("i", "x");
            palavra = palavra.replaceAll("o", "x");
            palavra = palavra.replaceAll("u", "x");

            System.out.println("Nova palavra: " + palavra);
        }
    }
}
