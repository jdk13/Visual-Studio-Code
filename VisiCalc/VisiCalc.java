// Jacob Kim
// AP Computer Science
// VisiCalc
// Checkpoint 2

import java.util.Scanner;
import java.util.Arrays;

public class VisiCalc {
	
	static Grid gr = new Grid(10, 7);
	
	static boolean returnedAssignment = false;

	public static void main(String args[]) {
		boolean quit = false;
		
		Scanner input = new Scanner(System.in);
		
		// returning same input
		while (!quit) {
			String answer = input.nextLine();
			if (answer.equalsIgnoreCase("print")) {
				gr.printGrid();
				
			} else if( answer.equalsIgnoreCase("Quit")) {
				quit = true;
				System.out.println("You have exited");
			}
			else  {
				cellAssignment(answer);
				if(!returnedAssignment) {
					System.out.println(answer);
				}
				
			} 
				
			}
		

		input.close();

	}
	
	public static boolean cellAssignment(String testCase) {
		String[] testcmd = testCase.split(" ");
		System.out.println(testcmd.length);
		for (int i = 0; i < testcmd.length; i++) {
			System.out.print(testcmd[i]);
			
			
		}
		System.out.println("");
		
		if(testcmd[1] == "=") {
			checkCell(testcmd[0]);
		}
		
		
		return returnedAssignment;
		
	}
	
	public static void checkCell(String celltest){
		if(celltest.length() < 4) {
			String case1 = celltest.substring(0,0);
			if (case1.contains)) {
				
			}
			
			
		
		
	
	}

	

}
}
