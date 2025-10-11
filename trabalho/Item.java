import java.time.LocalDate;

public abstract class Item implements Exportavel {
    private String titulo;
    private String descricao;
    private LocalDate dataCadastro;
    
    // construtor
    public Item(String titulo, String descricao, LocalDate dataCadastro) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    // metodo abstrato
    public abstract String exibirDetalhes();
}