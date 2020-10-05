package Java.Masterclass.Basics;
import java.util.Scanner;

public class InputCalculator {
	
	public static void inputThenPrintSumAndAverage() {
		
		int sum=0 , avg = 0, count = 0;
		
		boolean check = true;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a Number : ");
		
		while (check) {

			if (!scan.hasNextInt()) {
				check = false;
			    System.out.println();
			} else {
				count++;
				sum +=scan.nextInt();

			}
		}
	
		scan.close();
		
		avg = (count==0) ? 0 : sum/count;
		
		System.out.println("SUM = " + sum + " and AVG = "+ avg);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputThenPrintSumAndAverage();
	}

}
