public class livro {
    String titulo;
    String autor;

    public livro() {
        this.titulo = "Título padrão";
        this.autor = "Autor desconhecido";
    }

    public livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    void exibirInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("############");
    }


    public static void main(String[] args) {
        livro livro1 = new livro();
        livro livro2 = new livro("Dom Casmurro", "Machado de Assis");

        livro1.exibirInfo();
        livro2.exibirInfo();
    }
}
