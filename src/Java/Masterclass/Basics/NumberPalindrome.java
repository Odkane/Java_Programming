package Java.Masterclass.Basics;

public class NumberPalindrome {
	
	public static boolean IsPalindrome(int number) {
		int reverse = 0;
		int num = number;
		
		if (number > -11 && number<11)
			return false;
		
		while (num != 0) {
			reverse *= 10;
			reverse+= num%10;
			num = num/10;
		}
	    
		if (reverse == number)
	        return true;
		else return false;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int x= -70;
       
       if (IsPalindrome(x)== true)
    	   System.out.println("The Number " + x + " is a palindrome ");
       else 
    	   System.out.println("The Number " + x + " is not a palindorme ");
		
	}

}
