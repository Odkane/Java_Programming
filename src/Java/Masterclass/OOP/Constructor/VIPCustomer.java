package Java.Masterclass.OOP.Constructor;


public class VIPCustomer {
    String name;
    double creditLimit;
    String email;

    // Overloading
    public VIPCustomer(){
        this.name = "";
        this.creditLimit = 0;
        this.email = "aaa@bbb.com";
    }

    public VIPCustomer(String name, double creditLimit){
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = "aaa@bbb.com";
    }

    public VIPCustomer(String name, double creditLimit, String email){
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
    }

    //getters

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmail() {
        return email;
    }
}
