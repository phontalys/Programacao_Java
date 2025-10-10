import java.util.HashMap;
import java.util.Scanner;

public class CadastroPessoas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> pessoas = new HashMap<>();
        
        System.out.println("=== CADASTRO DE PESSOAS ===\n");
        
        // Cadastrar 5 pessoas
        for (int i = 1; i <= 5; i++) {
            System.out.println("--- Pessoa " + i + " ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); 
            
            pessoas.put(nome, idade);
            System.out.println("Pessoa cadastrada com sucesso!\n");
        }
        
        System.out.println("\n=== PESSOAS CADASTRADAS ===");
        exibirMapa(pessoas);
        
        System.out.print("\nDigite o nome para buscar a idade: ");
        String nomeBusca = scanner.nextLine();
        
        if (pessoas.containsKey(nomeBusca)) {
            int idade = pessoas.get(nomeBusca);
            System.out.println("A idade de " + nomeBusca + " é: " + idade + " anos");
        } else {
            System.out.println("Pessoa não encontrada no cadastro!");
        }
        
        System.out.print("\nDigite o nome da pessoa a ser removida: ");
        String nomeRemover = scanner.nextLine();
        
        if (pessoas.containsKey(nomeRemover)) {
            pessoas.remove(nomeRemover);
            System.out.println("Pessoa removida com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada no cadastro!");
        }
        
        System.out.println("\n=== CADASTRO ATUALIZADO ===");
        exibirMapa(pessoas);
        
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println("Total de pessoas cadastradas: " + pessoas.size());
        
        if (!pessoas.isEmpty()) {
            int somaIdades = 0;
            for (int idade : pessoas.values()) {
                somaIdades += idade;
            }
            double media = (double) somaIdades / pessoas.size();
            System.out.printf("Idade média: %.1f anos\n", media);
        }
        
        scanner.close();
    }
    
    // função para exibir o conteúdo do mapa
    private static void exibirMapa(HashMap<String, Integer> mapa) {
        if (mapa.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("Nome\t\t\tIdade");
            System.out.println("--------------------------------");
            for (String nome : mapa.keySet()) {
                System.out.printf("%-20s\t%d anos\n", nome, mapa.get(nome));
            }
        }
    }
}