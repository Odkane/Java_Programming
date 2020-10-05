package Java.Masterclass.OOP.Constructor;

class Main {

     public static void main(String[] args){
        bankAccount first = new bankAccount(123456,1000,"David","david@david.com","(0049) 23122323");

        first.addAmount(3000);
        first.withDrawal(5000);
        first.addAmount(2000);
        first.withDrawal(3000);

        System.out.println("---------------------------------------------------------------\n");

        VIPCustomer second = new VIPCustomer();
        VIPCustomer third = new VIPCustomer("Roland", 1000);
        VIPCustomer fourth = new VIPCustomer("Ezio",10000,"ezio@auditore.com");

        System.out.println("Second email: " + second.getEmail());
        System.out.println("Third's credit limit: " + third.getCreditLimit());
        System.out.println("Fourth's name: " + fourth.getName());

    }
}
