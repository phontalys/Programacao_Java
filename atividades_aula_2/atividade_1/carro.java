public class carro {
    String marca;
    String modelo;
    int ano;

    void exibirInfo() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("#########");
    }

    public static void main(String[] args) {
        carro carro1 = new carro();
        carro1.marca = "Toyota";
        carro1.modelo = "Corolla";
        carro1.ano = 2020;

        carro carro2 = new carro();
        carro2.marca = "Honda";
        carro2.modelo = "Civic";
        carro2.ano = 2022;

        carro1.exibirInfo();
        carro2.exibirInfo();
    }
}
