package Java.Masterclass.OOP.Composition;

public class Room {
    Wall wall1;
    Wall wall2;
    Wall wall3;
    Wall wall4;
    Bed bed;
    Table table;
    String name;

    public Room(Wall wall1, Wall wall2, Wall wall3, Wall wall4, Bed bed, Table table, String name) {

        this.wall1 = wall1;
        this.wall2 = wall2;
        this.wall3 = wall3;
        this.wall4 = wall4;
        this.bed = bed;
        this.table = table;
        this.name = name;
        System.out.println(this.name +" created");
    }
}
