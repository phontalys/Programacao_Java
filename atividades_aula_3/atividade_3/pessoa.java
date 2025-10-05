// Superclasse
class pessoa {
    private String nome;
    private int idade;

    public pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

class aluno extends pessoa {
    private String matricula;

    public aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Matrícula: " + getMatricula());
    }

    public static void main(String[] args) {
        aluno aluno = new aluno("Pedro Pedrino", 20, "202111722004");

        System.out.println("Informações iniciais:");
        aluno.exibirInfo();

        aluno.setNome("Antonio Claudio");
        aluno.setIdade(22);
        aluno.setMatricula("202111722007");

        System.out.println("\nInformações atualizadas:");
        aluno.exibirInfo();
    }
}
