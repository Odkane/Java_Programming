package Java.Masterclass.OOP.Inheritance;

public class Audi extends Car {
    String model;
    int doorCount;

    public Audi(String brand, int wheelNumber, int topSpeed, String size) {
        super("Audi", 4, topSpeed, size);
    }
}
