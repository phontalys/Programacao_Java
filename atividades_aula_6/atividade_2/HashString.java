import java.util.HashSet;
import java.util.Scanner;

public class HashString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> palavras = new HashSet<>();

        String entrada;
        System.out.println("Digite palavras (ou 'fim' para encerrar):");
        while (true) {
            entrada = sc.nextLine();
            if (entrada.equalsIgnoreCase("fim")) break;
            palavras.add(entrada);
        }

        System.out.println("\nPalavras digitadas: " + palavras);

        if (palavras.contains("Java"))
            System.out.println("A palavra 'Java' foi digitada!");
        else
            System.out.println("A palavra 'Java' não foi digitada.");

        sc.close();
    }
}
