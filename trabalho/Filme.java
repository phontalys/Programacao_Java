import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Filme extends Item {
    private String diretor;
    private int duracaoMinutos;
    
    // construtor
    public Filme(String titulo, String descricao, LocalDate dataCadastro,
                 String diretor, int duracaoMinutos) {
        super(titulo, descricao, dataCadastro);
        this.diretor = diretor;
        this.duracaoMinutos = duracaoMinutos;
    }
    
    public String getDiretor() {
        return diretor;
    }
    
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
    
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    
    public void setDuracaoMinutos(int duracaoMinutos) {
        if (duracaoMinutos > 0) {
            this.duracaoMinutos = duracaoMinutos;
        } else {
            throw new IllegalArgumentException("Duração deve ser positiva");
        }
    }
    
    // metodo abstrato
    @Override
    public String exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int horas = duracaoMinutos / 60;
        int minutos = duracaoMinutos % 60;
        
        return String.format("""
            === FILME ===
            Título: %s
            Descrição: %s
            Data de Cadastro: %s
            Diretor: %s
            Duração: %dh %dmin (%d minutos)
            """, 
            getTitulo(), 
            getDescricao(), 
            getDataCadastro().format(formatter),
            diretor, 
            horas,
            minutos,
            duracaoMinutos);
    }
    
    // interface exportavel
    @Override
    public String paraFormatoExportacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("FILME|%s|%s|%s|%s|%d",
            getTitulo(),
            getDescricao(),
            getDataCadastro().format(formatter),
            diretor,
            duracaoMinutos);
    }
}
