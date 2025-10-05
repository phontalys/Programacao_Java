class pessoa {
    String nome;
    int idade;

    public pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

class aluno extends pessoa {
    String matricula;

    public aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
    }

    void exibirInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Matrícula: " + matricula);
    }

    public static void main(String[] args) {
        aluno aluno = new aluno("Pedro Pedrino", 20, "202111722004");
        aluno.exibirInfo();
    }
}
