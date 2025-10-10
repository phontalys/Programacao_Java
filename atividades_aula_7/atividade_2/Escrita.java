import java.io.*;
import java.util.Scanner;

public class Escrita {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (PrintStream ps = new PrintStream("saida.txt")) {
            System.out.println("Digite várias linhas (digite 'FIM' para encerrar):");
            while (true) {
                String linha = sc.nextLine();
                if (linha.equalsIgnoreCase("FIM")) break;
                ps.println(linha);
            }
            System.out.println("As linhas foram gravadas em 'saida.txt'.");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }

        sc.close();
    }
}
