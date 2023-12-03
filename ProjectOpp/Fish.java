package ProjectOpp;

public class Fish extends Animal implements Pet{
    String name;

    Fish() {
        super(0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public void play(){
        System.out.println("Fish cant play");
    }
    public void walk(){
        System.out.println("Fish cant walk");
    }
    public void eat(){
        System.out.println("Fish can eat");
    }
}
