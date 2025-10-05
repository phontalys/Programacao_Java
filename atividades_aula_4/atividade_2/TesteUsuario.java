enum NivelAcesso {
    BASICO,
    INTERMEDIARIO,
    ADMIN
}

class Usuario {
    private String nome;
    private NivelAcesso nivel;

    public Usuario(String nome, NivelAcesso nivel) {
        this.nome = nome;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public NivelAcesso getNivel() {
        return nivel;
    }

    public void exibirMensagem() {
        System.out.print("Usuário " + nome + ": ");
        switch (nivel) {
            case BASICO:
                System.out.println("Acesso limitado.");
                break;
            case INTERMEDIARIO:
                System.out.println("Acesso intermediário.");
                break;
            case ADMIN:
                System.out.println("Acesso total (administrador).");
                break;
        }
    }
}

public class TesteUsuario {
    public static void main(String[] args) {
        Usuario u1 = new Usuario("Ana", NivelAcesso.BASICO);
        Usuario u2 = new Usuario("Bruno", NivelAcesso.INTERMEDIARIO);
        Usuario u3 = new Usuario("Carla", NivelAcesso.ADMIN);

        u1.exibirMensagem();
        u2.exibirMensagem();
        u3.exibirMensagem();
    }
}
