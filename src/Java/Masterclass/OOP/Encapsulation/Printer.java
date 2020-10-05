package Java.Masterclass.OOP.Encapsulation;

// Encapsulation by putting attributes to private and methods to public
public class Printer {
    private String name;
    private int tonerLevel;
    private int numberPages;
    private boolean checkDuplex;

    public Printer(String name, int tonerLevel, int numberPages, boolean checkDuplex) {
        this.name = name;
        if (tonerLevel < 0) {
            this.tonerLevel = 0;
        } else if (tonerLevel > 100) {
            this.tonerLevel = 100;
        } else {
            this.tonerLevel = tonerLevel;
        }

        if (numberPages>0)
            this.numberPages = numberPages;
        else
            this.numberPages = 0;

        this.checkDuplex = checkDuplex;

        System.out.println(name +" created");

    }

    public void fillToner(int amount){
        if (amount<0){
            System.out.println("Invalid amount!");
            return;
        }
        if((this.tonerLevel+amount)>100){
            this.tonerLevel = 100;
        }
        else this.tonerLevel += amount;

        System.out.println("New Toner Level: "+ this.tonerLevel);
    }

    public void print(int number){
        this.numberPages += number;
    }

}
