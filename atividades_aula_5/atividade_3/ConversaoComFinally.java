import java.util.Scanner;

public class ConversaoComFinally {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Digite um número inteiro: ");
            String entrada = scanner.nextLine();
            
            int numero = Integer.parseInt(entrada);
            
            System.out.println("\nConversão bem-sucedida!");
            System.out.println("Você digitou o número: " + numero);
            System.out.println("O dobro deste número é: " + (numero * 2));
            
        } catch (NumberFormatException e) {
            System.out.println("\nErro: A entrada não é um número inteiro válido!");
            System.out.println("Por favor, digite apenas números inteiros.");
        } finally {
            System.out.println("\n-----------------------------------------");
            System.out.println("Encerrando...");
            System.out.println("-----------------------------------------");
            scanner.close();
        }
    }
}