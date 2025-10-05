class ContaBancaria {
    private int numero;

    public ContaBancaria(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "ContaBancaria [numero=" + numero + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;
        ContaBancaria other = (ContaBancaria) obj;
        return this.numero == other.numero;
    }

    public static void main(String[] args) {
        ContaBancaria c1 = new ContaBancaria(12345);
        ContaBancaria c2 = new ContaBancaria(12345);

        System.out.println("As contas são iguais? " + c1.equals(c2));

        System.out.println("Exibindo conta: " + c1);
    }
}
