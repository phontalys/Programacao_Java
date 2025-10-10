import java.io.File;
import java.util.Scanner;

public class Info {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o caminho do arquivo ou diretório: ");
        String caminho = sc.nextLine();

        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            System.out.println("O caminho informado não existe!");
        } else {
            System.out.println("\nExiste: SIM");
            System.out.println("É arquivo? " + arquivo.isFile());
            System.out.println("É diretório? " + arquivo.isDirectory());
            System.out.println("Tamanho: " + arquivo.length() + " bytes");
            System.out.println("Caminho absoluto: " + arquivo.getAbsolutePath());

            if (arquivo.isDirectory()) {
                System.out.println("\nArquivos no diretório:");
                File[] arquivos = arquivo.listFiles();
                if (arquivos != null) {
                    for (File f : arquivos) {
                        System.out.println(" - " + f.getName());
                    }
                }
            }
        }

        sc.close();
    }
}
