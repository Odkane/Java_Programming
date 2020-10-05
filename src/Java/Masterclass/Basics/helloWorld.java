package Java.Masterclass.Basics;

import java.math.*;
import java.text.*;
import java.util.Scanner;

public class helloWorld {
	
	public static int mult (int a , int b) {
		
		return a*b;	
	}
	
	public static int falc(int a) {
		
		if (a==0)
			return 1;
		return a * falc(a-1);
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("This is just the beginning!");
		 
		int a= 5;
		double b;
		
		DecimalFormatSymbols usa = new DecimalFormatSymbols( java.util.Locale.US );
		
		 DecimalFormat df= new DecimalFormat("0.##", usa);
		b =  Math.sqrt(a);
		
		System.out.println("Value of a :" + a + 
				" and Value of b :" + BigDecimal.valueOf(b));
		
		System.out.println("Value of a :" + a + 
				" and Value of b :" + df.format(b));
		
		String s = "Naaaaaaaa";
		char c= '\u00A9';
		
		System.out.println(s);
		System.out.println(c);
		
		String num = "50";
		int k=10;
		
		num = Integer.toString((Integer.parseInt(num) + k));
		
		System.out.println(num);
		
		 Scanner scan = new Scanner(System.in);
	     
		 System.out.print("Enter any number: ");
	   
	     int n = scan.nextInt();
	 /* 
	     System.out.print("Enter a Word: ");
		   
	     String ScanString = scan.next();
	   */  
	        // Closing Scanner after the use
	        scan.close();
	        
	        System.out.print("Les diviseurs de "+n +" sont: ");
	        for (int i=1; i<n;i++) {
	        	if ((n%i)==0) 
	        	 System.out.print(i +" ");	
	        }
	        System.out.println();
	        // Displaying the number 
	        System.out.println("The number entered by user: "+n);
	        System.out.println("The Word entered by user: "+n);
	  
	        
        int x = mult(3 , 2);
    	int y = Math.round(a);
    	System.out.println(x);
    	System.out.println(falc(x));
    	
 
	}
	
	

}
