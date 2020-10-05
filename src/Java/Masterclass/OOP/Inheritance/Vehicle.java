package Java.Masterclass.OOP.Inheritance;

public class Vehicle {
    String Brand;
    int wheelNumber;
    int actualSpeed;
    int topSpeed;
    String size;

    public Vehicle(String brand, int wheelNumber, int topSpeed, String size) {
        Brand = brand;
        this.wheelNumber = wheelNumber;
        this.actualSpeed = 0;
        this.topSpeed = topSpeed;
        this.size = size;
    }

    public String getBrand() {
        return Brand;
    }

    public int getWheelNumber() {
        return wheelNumber;
    }

    public int getActualSpeed() {
        return actualSpeed;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public String getSize() {
        return size;
    }

    public void move(int speed){
        this.actualSpeed +=speed;
    }
}
