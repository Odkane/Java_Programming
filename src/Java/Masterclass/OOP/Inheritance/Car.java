package Java.Masterclass.OOP.Inheritance;

public class Car extends Vehicle{
    String GearModel;
    String carName;
    String gear;


    public Car(String brand, int wheelNumber, int topSpeed, String size, String carName) {
        super(brand, 4, topSpeed, size);
        this.GearModel = "";
        this.carName = carName;
        this.gear = gear;
    }

    public Car(String audi, int i, int topSpeed, String size) {
        super(audi, i, topSpeed, size);
    }

    @Override
    public void move(int speed) {
        super.move(speed);
        System.out.println(carName +"'s actual speed changed to :" + this.actualSpeed);
    }
}
