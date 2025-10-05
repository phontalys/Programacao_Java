abstract class ContaBancaria {
    protected double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public abstract boolean sacar(double valor);
    public abstract void depositar(double valor);

    public void exibirSaldo() {
        System.out.println("Saldo atual: R$ " + saldo);
    }
}

class ContaCorrente extends ContaBancaria {
    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public boolean sacar(double valor) {
        double valorComTaxa = valor + 1.0;
        if (saldo >= valorComTaxa) {
            saldo -= valorComTaxa;
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque com taxa.");
            return false;
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
}

class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
}

public class TesteConta {
    public static void main(String[] args) {
        ContaBancaria cc = new ContaCorrente(100);
        ContaBancaria cp = new ContaPoupanca(100);

        System.out.println("=== Conta Corrente ===");
        cc.exibirSaldo();
        cc.sacar(50);
        cc.exibirSaldo();
        cc.depositar(30);
        cc.exibirSaldo();

        System.out.println("\n=== Conta Poupança ===");
        cp.exibirSaldo();
        cp.sacar(50);
        cp.exibirSaldo();
        cp.depositar(30);
        cp.exibirSaldo();
    }
}
