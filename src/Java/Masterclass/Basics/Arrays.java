package Java.Masterclass.Basics;

public class Arrays {
	
	public static int[] getIntegers(int n) {
		
		int[] arr = new int[n];
		
		for (int i =0; i<n ;i++) {
			
			arr[i]= (int)(Math.random()*50);
		}
		
		return arr;
	}
	public static void  sortIntegers (int[] arr) {
		
		int count = 1, temp;
		
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr.length-count; j++) {
				if (arr[j] < arr[j+1]) {
					//sort(myArray[j] ,myArray[j+1]);
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
				}
		
			}
			
			count++;
		}
	}
	
	
	public static int[] reverse (int[] arr) {
		
		int[] arr1 = new int[arr.length];

		int i = arr.length - 1;
		while (i >= 0) {
			arr1[i] = arr[arr.length - 1 - i];
			i--;
		}

		return arr1;
	}
	
	public static void printArray(int[] arr) {

		for (int j : arr) {

			System.out.print(" " + j);
		}
		
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		int n=5;

		int[] myArray;

		myArray = getIntegers(n);

		System.out.print(" Array before sorting :");
		printArray(myArray);

		sortIntegers(myArray);

		System.out.print(" Array after sorting :");
		printArray(myArray);
		
		myArray = reverse(myArray);
		
		System.out.print(" Array after reversing :");
		printArray(myArray);
		
		
	}

}
