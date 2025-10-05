package interfaces;

public class Main {
    public static void main(String[] args) {
        Triatleta atleta = new Triatleta("João");
        
        atleta.aquecer();
        atleta.nadar();
        atleta.correr();
        atleta.correrDeBicicleta();
    }
}