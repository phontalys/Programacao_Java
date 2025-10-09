import java.util.Scanner;

public class CalculoRaizComThrows {
    
    public static double calcularRaiz(int numero) throws IllegalArgumentException {
        if (numero < 0) {
            throw new IllegalArgumentException("Não é possível calcular raiz quadrada de número negativo!");
        }
        return Math.sqrt(numero);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Digite um número para calcular a raiz quadrada: ");
            int numero = scanner.nextInt();
            
            double raiz = calcularRaiz(numero);
            
            System.out.println("\n A raiz quadrada de " + numero + " é: " + String.format("%.2f", raiz));
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n Erro: " + e.getMessage());
            System.out.println("Por favor, informe um número não-negativo.");
        } finally {
            scanner.close();
        }
    }
}
