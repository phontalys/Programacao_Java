class Animal {
    void emitirSom() {
        System.out.println("grrrr");
    }
}

class Cachorro extends Animal {
    @Override
    void emitirSom() {
        System.out.println("Au! au!");
    }
}

class Gato extends Animal {
    @Override
    void emitirSom() {
        System.out.println("Miaaaau!");
    }
}

public class TesteAnimal {
    public static void main(String[] args) {
        Animal[] animais = new Animal[2];
        animais[0] = new Cachorro();
        animais[1] = new Gato();

        for (Animal a : animais) {
            a.emitirSom();
        }
    }
}
