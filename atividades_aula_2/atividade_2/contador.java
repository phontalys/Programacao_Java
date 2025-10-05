public class contador {
    static int totalObjetos = 0;

    public contador() {
        totalObjetos++;
    }

    static void mostrarTotal() {
        System.out.println("Total de objetos criados: " + totalObjetos);
    }

    public static void main(String[] args) {
        contador c1 = new contador();
        contador c2 = new contador();
        contador c3 = new contador();

        contador.mostrarTotal();
    }
}
