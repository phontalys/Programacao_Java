import java.util.ArrayList;
import java.util.Scanner;

public class Array_int {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            numeros.add(sc.nextInt());
        }

        System.out.println("\nNúmeros inseridos: " + numeros);

        int soma = 0;
        for (int n : numeros) soma += n;
        double media = (double) soma / numeros.size();
        System.out.println("Soma total: " + soma);
        System.out.println("Média: " + media);

        numeros.removeIf(n -> n % 2 == 0);
        System.out.println("Lista após remover números pares: " + numeros);

        sc.close();
    }
}
