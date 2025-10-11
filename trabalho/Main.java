import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GerenciadorItens gerenciador = new GerenciadorItens();
        
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE ITENS ===\n");
        
        try {
            gerenciador.adicionarItem(new Livro(
                "1984",
                "Romance distópico sobre um regime totalitário",
                LocalDate.of(2024, 1, 15),
                "George Orwell",
                328
            ));
            
            gerenciador.adicionarItem(new Livro(
                "O Senhor dos Anéis",
                "Épico de fantasia sobre a Terra Média",
                LocalDate.of(2024, 2, 10),
                "J.R.R. Tolkien",
                1178
            ));
            
            gerenciador.adicionarItem(new Filme(
                "Matrix",
                "Ficção científica sobre realidade virtual",
                LocalDate.of(2024, 4, 12),
                "Lana e Lilly Wachowski",
                136
            ));
            
            gerenciador.adicionarItem(new Filme(
                "Inception",
                "Thriller sobre invasão de sonhos",
                LocalDate.of(2024, 5, 8),
                "Christopher Nolan",
                148
            ));
            
            gerenciador.exibirEstatisticas();
            
            // TESTE DE EXPORTAÇÃO
            System.out.println("\n=== TESTANDO EXPORTAÇÃO ===");
            String arquivoExportacao = "acervo_exportado.txt";
            gerenciador.exportarParaArquivo(arquivoExportacao);
            
            // TESTE DE IMPORTAÇÃO
            System.out.println("\n=== TESTANDO IMPORTAÇÃO ===");
            GerenciadorItens gerenciador2 = new GerenciadorItens();
            gerenciador2.importarDeArquivo(arquivoExportacao);
            
            System.out.println("\n=== VERIFICANDO ITENS IMPORTADOS ===");
            gerenciador2.exibirEstatisticas();
            
            List<Item> itensImportados = gerenciador2.listarTodos();
            for (Item item : itensImportados) {
                System.out.println(item.exibirDetalhes());
            }
            
            // TESTE DE ERRO: arquivo inexistente
            System.out.println("\n=== TESTANDO ERRO: ARQUIVO INEXISTENTE ===");
            try {
                gerenciador2.importarDeArquivo("arquivo_inexistente.txt");
            } catch (ArquivoException e) {
                System.err.println("✓ Exceção capturada: " + e.getMessage());
            }
            
        } catch (TituloVazioException | ArquivoException e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}