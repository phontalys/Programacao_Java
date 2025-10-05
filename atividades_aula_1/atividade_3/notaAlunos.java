import java.util.Scanner;

public class notaAlunos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] notas = new double[10];
        double soma = 0;

        // Entrada das notas
        for (int i = 0; i < notas.length; i++) {
            System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
            notas[i] = sc.nextDouble();
            soma += notas[i];
        }

        double media = soma / notas.length;
        int acima = 0, abaixo = 0;

        // Contagem de notas acima e abaixo da média
        for (double nota : notas) {
            if (nota > media) {
                acima++;
            } else if (nota < media) {
                abaixo++;
            }
        }

        System.out.println("\nMédia das notas: " + media);
        System.out.println("Notas acima da média: " + acima);
        System.out.println("Notas abaixo da média: " + abaixo);

        sc.close();
    }
}
