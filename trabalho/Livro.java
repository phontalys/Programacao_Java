import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro extends Item {
    private String autor;
    private int numeroPaginas;
    
    // construtor
    public Livro(String titulo, String descricao, LocalDate dataCadastro, 
                 String autor, int numeroPaginas) {
        super(titulo, descricao, dataCadastro);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }
    
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas > 0) {
            this.numeroPaginas = numeroPaginas;
        } else {
            throw new IllegalArgumentException("Número de páginas deve ser positivo");
        }
    }
    
    //  metodo abstrato
    @Override
    public String exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("""
            === LIVRO ===
            Título: %s
            Descrição: %s
            Data de Cadastro: %s
            Autor: %s
            Número de Páginas: %d
            """, 
            getTitulo(), 
            getDescricao(), 
            getDataCadastro().format(formatter),
            autor, 
            numeroPaginas);
    }
    
    //  interface exportavel
    @Override
    public String paraFormatoExportacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("LIVRO|%s|%s|%s|%s|%d",
            getTitulo(),
            getDescricao(),
            getDataCadastro().format(formatter),
            autor,
            numeroPaginas);
    }
}