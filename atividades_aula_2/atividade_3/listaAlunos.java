import java.util.ArrayList;
import java.util.Iterator;

public class listaAlunos {
    public static void main(String[] args) {
        ArrayList<String> alunos = new ArrayList<>();

        alunos.add("Arthur");
        alunos.add("Aurelio");
        alunos.add("Lui");
        alunos.add("Taina");
        alunos.add("Cayo");

        System.out.println("Lista de alunos:");
        Iterator<String> it = alunos.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        alunos.remove("Taina");

        System.out.println("\nLista atualizada:");
        for (String nome : alunos) {
            System.out.println(nome);
        }
    }
}
