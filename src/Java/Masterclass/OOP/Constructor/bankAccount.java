package Java.Masterclass.OOP.Constructor;

public class bankAccount {
    int accountNumber;
    double balance;
    String customerName;
    String customerEmail;

    public bankAccount(int accountNumber, double balance, String customerName, String customerEmail, String customerPhoneNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    //setters
    public void setAccountNumber(int accountNumber){
        this.accountNumber=accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    String customerPhoneNumber;

    //getters
    public int getAccountNumber(){
        return this.accountNumber;
    }

    public double getBalance(){
        return this.balance;
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public String getCustomerEmail(){
        return this.customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    // Methods
    public void addAmount(int amount){
        this.balance +=amount;
        System.out.println(amount + " added, New Balance: "+ this.balance);
    }

    public void withDrawal(int amount){
        if ((this.balance - amount)<0){
            System.out.println("Not enough money, Your Balance: " + this.balance);
            return;
        }
        else {
            this.balance -=amount;
            System.out.println(amount + " withdrawn, New Balance: "+ this.balance);
        }
    }
}
