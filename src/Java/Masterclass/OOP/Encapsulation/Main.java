package Java.Masterclass.OOP.Encapsulation;

public class Main {
    public static void main (String[] args){

        Printer canon = new Printer("Canon",0,0,true);

        // Due to Encapsulation attributes are not directly accessible from here

        // canon.tonerLevel = 50;

        canon.fillToner(200);



    }
}
