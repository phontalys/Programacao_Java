class Calculadora {

    int somar(int a, int b) {
        return a + b;
    }

    double somar(double a, double b) {
        return a + b;
    }

    int somar(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        int somaInt2 = calc.somar(2, 3);
        double somaDouble2 = calc.somar(2.5, 3.7);
        int somaInt3 = calc.somar(1, 2, 3);

        System.out.println("Soma de dois inteiros: " + somaInt2);
        System.out.println("Soma de dois doubles: " + somaDouble2);
        System.out.println("Soma de tres inteiros: " + somaInt3);
    }
}
