package Java.Masterclass.Basics;

public class AreaCalculator {


    public static double area (double radius) {
        
        if (radius < 0)
          return -1;
          
         return Math.PI*radius*radius;
    }
    
    public static double area (double x, double y) {
        
        if (x < 0 || y < 0)
          return -1;
          
         return x*y;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.println(area(-5.0,6.0));
       
	}

}
