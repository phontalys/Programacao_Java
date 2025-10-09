import java.util.Scanner;

public class DivisaoComTryCatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Digite o primeiro número (dividendo): ");
            int num1 = scanner.nextInt();
            
            System.out.print("Digite o segundo número (divisor): ");
            int num2 = scanner.nextInt();
            
            int resultado = num1 / num2;
            
            System.out.println("\nResultado da divisão: " + num1 + " / " + num2 + " = " + resultado);
            
        } catch (ArithmeticException e) {
            System.out.println("\n Erro: Não é possível dividir por zero!");
            System.out.println("Por favor, informe um divisor diferente de zero.");
        } finally {
            scanner.close();
        }
    }
}
