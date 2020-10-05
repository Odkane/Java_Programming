package Java.Masterclass.OOP.Composition;

class Main {

    public static void main(String[] args){
        Wall wall1= new Wall(100,50,"blue","East");
        Wall wall2 = new Wall(100,50,"red","North");
        Wall wall3 = new Wall(100,50,"yellow","West");
        Wall wall4 = new Wall(100,50,"violet","South");

        Bed bed = new Bed(10,5,"Water Bet");
        Table table = new Table("White","Ebony",10,10);

        Room bedroom = new Room(wall1,wall2,wall3,wall4,bed,table,"Bedroom");

        bedroom.wall1.setColor("Lila");
    }
}
