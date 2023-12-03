package ProjectOpp;

public class Cat extends Animal implements Pet{
    String name;

    public Cat(String name){
        super(4);
        this.name = name;
    }
    Cat(){
        super(4);

    }
    public String getName(){
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void walk() {
        System.out.println("Cat can walk");
    }

    @Override
    void eat() {
        System.out.println("Cat can eat");
    }
}
