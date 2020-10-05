package Java.Masterclass.OOP.Polymorphism;

class Car {
    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders;
        this.name = name;
        this.wheels = 4;
        this.engine = true;
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getName() {
        return name;
    }

    public String startEngine() {
        return "Car -> StartEngine() ";
    }

    public String accelarate(){
        return "Car -> accelerate()";
    }

    public String brake(){
         return "Car -> brake()";
    }
}

class Audi extends Car {

    public Audi(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return "Audi -> StartEngine() ";
    }

    @Override
    public String accelarate(){
        return "Audi -> accelerate()";
    }

    @Override
    public String brake() {
        return "Audi -> brake()";
    }

}

class Main {

    public static void main(String[] args){
        Car car = new Car(8, "Base Car");
        System.out.println(car.startEngine());
        System.out.println(car.accelarate());
        System.out.println(car.brake());

        Audi audi = new Audi(8,"A4");
        System.out.println(audi.startEngine());
        System.out.println(audi.accelarate());
        System.out.println(audi.brake());



    }

}

