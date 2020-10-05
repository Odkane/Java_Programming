package Java.Masterclass.OOP.Composition;

public class Wall {
    int height;
    int width;
    String color;
    String name;

    public Wall(int height, int width, String color,String name) {
        this.height = height;
        this.width = width;
        this.color = color;
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        System.out.println(this.name +"'s color changed to "+ color);
    }
}
