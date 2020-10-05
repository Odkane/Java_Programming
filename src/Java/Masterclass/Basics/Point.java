package Java.Masterclass.Basics;

public class Point {
	private int x;
	private int y;
	
	
	public Point() {
	   this(0 , 0);
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	public int getX() {
		return x;
	}
    
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public double distance() {
		return Math.sqrt(x*x + y*y);
		
	}
	
	public double distance (Point p1) {
		return Math.sqrt(Math.pow((p1.x -this.x),2) + Math.pow(p1.y -this.y, 2));
	}
	
	public double distance (int x , int y) {
		return Math.sqrt(Math.pow((x - this.x),2) + Math.pow(y - this.y, 2));
	}
}
