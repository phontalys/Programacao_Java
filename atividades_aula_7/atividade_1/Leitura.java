import java.io.*;

public class Leitura {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            System.out.print("Digite o nome do arquivo de texto: ");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            String nomeArquivo = entrada.readLine();

            FileInputStream fis = new FileInputStream(nomeArquivo);
            InputStreamReader isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);

            System.out.println("\n--- Conteúdo do arquivo ---");
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo.");
            }
        }
    }
}
