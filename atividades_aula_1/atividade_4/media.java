import java.util.Scanner;

public class media {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        int numero;
        double soma = 0;

        while (true) {
            System.out.print("Digite um número (negativo para sair): ");
            numero = sc.nextInt();

            if (numero < 0) {
                break; // encerra o laço se o número for negativo
            }

            soma += numero;
            contador++;
        }

        if (contador > 0) {
            double media = soma / contador;
            System.out.println("\nQuantidade de números digitados: " + contador);
            System.out.println("Média dos números: " + media);
        }

        sc.close();
    }
}
