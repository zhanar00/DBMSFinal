package ProjectOpp;

public class AnimalTest {
    public static void main(String[] args) {
        Fish f = new Fish();
        Cat c = new Cat("Fluffy");
        Animal a = new Fish();
        Animal e =new Spider();
        Pet p = new Cat();
        f.eat();
        f.walk();
        f.play();
        System.out.println(f.legs);

        c.walk();
        c.eat();
        System.out.println(c.name);
        System.out.println(c.legs);

        System.out.println(a.legs);
        a.eat();
        a.walk();

        e.eat();
        e.walk();
        System.out.println(e.legs);


    }
}
