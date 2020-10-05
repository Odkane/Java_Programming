package Java.Masterclass.Basics;

public class MegaBytesConverter {
	
	
	public static void printMBKB(int kiloBytes) {
	   
		if (kiloBytes< 0 ) {
			System.out.println ( "Invalid Value");
			return;
		}

		if (kiloBytes < 1024) {
			System.out.println ( kiloBytes +" KB = 0 MB and "+ kiloBytes+ " KB");
		} else {
			System.out.println ( kiloBytes +" KB = " +(int)(kiloBytes / 1024)+" MB and "+ (kiloBytes%1024)+ " KB");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		printMBKB(8192);

	}

}
