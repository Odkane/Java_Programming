package Java.Masterclass.Basics;

public class SpeedyConverter {
	  
    public static long toMilesPerHour(double kilometersPerHour) {
        
        if (kilometersPerHour < 0)
           return -1;
         
        else {
            return Math.round(kilometersPerHour / 1.60934);
        }
        
    }
    // write your code here
    public static void main (String[] args) {

        long x = toMilesPerHour(1.25);

        System.out.println(x);
        
    }

}
