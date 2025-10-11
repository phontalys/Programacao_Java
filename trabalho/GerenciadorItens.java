import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorItens {
    private List<Item> itens;
    
    // construtor
    public GerenciadorItens() {
        this.itens = new ArrayList<>();
    }
    
    // adicionar item
    public void adicionarItem(Item item) throws TituloVazioException {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new TituloVazioException("O título do item não pode estar vazio");
        }
        
        itens.add(item);
        System.out.println("✓ Item adicionado com sucesso: " + item.getTitulo());
    }
    
    // listar todos os itens
    public List<Item> listarTodos() {
        // ordenar por título
        List<Item> itensOrdenados = new ArrayList<>(itens);
        Collections.sort(itensOrdenados, Comparator.comparing(Item::getTitulo, String.CASE_INSENSITIVE_ORDER));
        return itensOrdenados;
    }
    
    // buscar por título (filtro parcial, case-insensitive)
    public List<Item> buscarPorTitulo(String titulo) throws ItemNaoEncontradoException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título de busca não pode estar vazio");
        }
        
        List<Item> resultados = itens.stream()
            .filter(item -> item.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .sorted(Comparator.comparing(Item::getTitulo, String.CASE_INSENSITIVE_ORDER))
            .collect(Collectors.toList());
        
        if (resultados.isEmpty()) {
            throw new ItemNaoEncontradoException("Nenhum item encontrado com o título: '" + titulo + "'");
        }
        
        return resultados;
    }
    
    // contar por tipo
    public Map<String, Long> contarPorTipo() {
        Map<String, Long> contagem = new HashMap<>();
        
        long livros = itens.stream().filter(item -> item instanceof Livro).count();
        long filmes = itens.stream().filter(item -> item instanceof Filme).count();
        
        contagem.put("Livros", livros);
        contagem.put("Filmes", filmes);
        contagem.put("Total", (long) itens.size());
        
        return contagem;
    }
    
    // exibir estatísticas
    public void exibirEstatisticas() {
        Map<String, Long> stats = contarPorTipo();
        System.out.println("\n=== ESTATÍSTICAS DO ACERVO ===");
        System.out.println("Total de itens: " + stats.get("Total"));
        System.out.println("Livros: " + stats.get("Livros"));
        System.out.println("Filmes: " + stats.get("Filmes"));
        System.out.println("==============================\n");
    }
    
    
    // exportar para arquivo
    public void exportarParaArquivo(String caminho) throws ArquivoException {
        if (caminho == null || caminho.trim().isEmpty()) {
            throw new ArquivoException("Caminho do arquivo não pode estar vazio");
        }
        
        if (itens.isEmpty()) {
            throw new ArquivoException("Não há itens para exportar");
        }
        
        try (FileWriter fw = new FileWriter(caminho);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            for (Item item : itens) {
                bw.write(item.paraFormatoExportacao());
                bw.newLine();
            }
            
            System.out.println("✓ Dados exportados com sucesso para: " + caminho);
            System.out.println("  Total de itens exportados: " + itens.size());
            
        } catch (IOException e) {
            throw new ArquivoException("Erro ao exportar dados para o arquivo: " + caminho, e);
        }
    }
    
    // importar de arquivo
    public void importarDeArquivo(String caminho) throws ArquivoException {
        if (caminho == null || caminho.trim().isEmpty()) {
            throw new ArquivoException("Caminho do arquivo não pode estar vazio");
        }
        
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            throw new ArquivoException("Arquivo não encontrado: " + caminho);
        }
        
        int linhasLidas = 0;
        int itensImportados = 0;
        int erros = 0;
        
        try (FileReader fr = new FileReader(caminho);
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                linhasLidas++;
                linha = linha.trim();
                
                if (linha.isEmpty()) {
                    continue; // ignora linhas vazias
                }
                
                try {
                    Item item = parseLinhaParaItem(linha);
                    if (item != null) {
                        adicionarItem(item);
                        itensImportados++;
                    }
                } catch (Exception e) {
                    erros++;
                    System.err.println("⚠ Erro na linha " + linhasLidas + ": " + e.getMessage());
                }
            }
            
            System.out.println("\n✓ Importação concluída!");
            System.out.println("  Linhas lidas: " + linhasLidas);
            System.out.println("  Itens importados: " + itensImportados);
            if (erros > 0) {
                System.out.println("  Erros encontrados: " + erros);
            }
            
        } catch (IOException e) {
            throw new ArquivoException("Erro ao importar dados do arquivo: " + caminho, e);
        }
    }
    
    // parser de linha para Item
    private Item parseLinhaParaItem(String linha) throws Exception {
        String[] partes = linha.split("\\|");
        
        if (partes.length < 5) {
            throw new IllegalArgumentException("Formato inválido: linha deve ter pelo menos 5 campos");
        }
        
        String tipo = partes[0].trim().toUpperCase();
        String titulo = partes[1].trim();
        String descricao = partes[2].trim();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataCadastro;
        try {
            dataCadastro = LocalDate.parse(partes[3].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + partes[3]);
        }
        
        switch (tipo) {
            case "LIVRO":
                if (partes.length < 6) {
                    throw new IllegalArgumentException("LIVRO requer 6 campos");
                }
                String autor = partes[4].trim();
                int numeroPaginas = Integer.parseInt(partes[5].trim());
                return new Livro(titulo, descricao, dataCadastro, autor, numeroPaginas);
                
            case "FILME":
                if (partes.length < 6) {
                    throw new IllegalArgumentException("FILME requer 6 campos");
                }
                String diretor = partes[4].trim();
                int duracaoMinutos = Integer.parseInt(partes[5].trim());
                return new Filme(titulo, descricao, dataCadastro, diretor, duracaoMinutos);
                
            default:
                throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
        }
    }
}
