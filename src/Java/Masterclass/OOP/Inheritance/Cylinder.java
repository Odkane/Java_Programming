package Java.Masterclass.OOP.Inheritance;

import Java.Masterclass.OOP.Inheritance.Circle;

public class Cylinder extends Circle {
	
	private double height;
	
	public Cylinder (double radius, double height) {
		super(radius);
		
		if (height <0)
			height = 0;

		this.height = height;
		
	}
	
	public double getHeight() {
		
		return height;
	}
	
	public double getVolume () {
		double area = this.getArea();
		
		return area*height;
		
	}
	

}
