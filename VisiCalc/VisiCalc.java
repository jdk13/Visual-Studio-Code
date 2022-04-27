// Jacob Kim
// AP Computer Science
// VisiCalc
// Checkpoint 2

import java.util.Scanner;

public class VisiCalc {
	
	
	static Grid gr = new Grid(10, 7);
	static boolean checkColumn = false;
	boolean CheckNum = false;
	static boolean checkR = false;
	static int cellLoc1;
	static int cellLoc2;
	static int givenvalue;
	static boolean intString;
	static String[] testcmd;
	static boolean validCell = false;

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
				checkColumn = false;
				if(!returnedAssignment) {
					System.out.println(answer);
				}
				System.out.println("");
				
			} 
				
			}
		

		input.close();

	}
	
	public static boolean cellAssignment(String testCase) {
		//ok so here we are taking whatever the input is and checking if we can assign it
		if (testCase.contains(" ")){
			testcmd = testCase.split(" ");

				if (testcmd.length > 3){
					for (int k = 3; k < testcmd.length; k++){
						String temp = testcmd[k];
						testcmd[2] = testcmd[2] + " " +  temp;
					}
				}
			
			//System.out.println(testcmd[1]);
			//System.out.println(testcmd[2]);
			
			//System.out.println(testcmd.length);
			/*for (int i = 0; i < testcmd.length; i++) {
				System.out.print(testcmd[i]);
				
				
			}*/
			if(testcmd.length == 1) {
				checkCell(testcmd[0]);
				if(validCell) {
					System.out.println(Grid.getCell(cellLoc1, cellLoc2));					
				}
			}
			//if the 2nd element is an =, then we can run these tests
			if(testcmd.length > 1) {
				if(testcmd[1].equals("=")) {
					checkCell(testcmd[0]);
					//this will check if the requested cell location and its contents are valid
					if(validCell){
						intorString(testcmd[2]);
						if (intString){
							Grid.assignIntCell(cellLoc1, cellLoc2, givenvalue);
							returnedAssignment = true;
						}
						else{
							if(testcmd[2].indexOf("\"") == 0 && testcmd[2].endsWith("\"")){
								Grid.assignStringCell(cellLoc1, cellLoc2, testcmd[2].substring(1, testcmd[2].length()-1));
								returnedAssignment = true;
							}

							returnedAssignment = true;

						}
						
					}
					else{
						returnedAssignment = false;
						return returnedAssignment;
					}
				}
				else{
					returnedAssignment = false;
					return returnedAssignment;
				}
			}
			
		}
		else{
			checkCell(testCase);
				if(validCell) {
					System.out.println(Grid.getCell(cellLoc1, cellLoc2));					
				}
		}
		//this effectively splits everything by spaces into arrays
		//and this for loop effectively mashes every element past the third element 
		//into the third element
		
		
		
		return returnedAssignment;
		
	}
	
	public static boolean checkCell(String celltest){ //celltest is loc eg A3 or C4
		//cell test 2 is whatever comes after
		if(celltest.length() < 4) {
			String case1 = celltest.substring(0,1);
			//splits the letter
			String case2 = celltest.substring(1);
			//splits the number just in case its 10
			/*
			A4 = 3454
			A4 = celltest
			case1 = A
			case2 = 4
			*/
			checkCol(case1);
			//if any of the conditions are met, then it will return true
			//and cellloc will be the index
			//System.out.println(case1);
			if (checkColumn) {
				//if the checkColumn was returned true
				// it will then check the row
				checkRow(case2);
				if(checkColumn && checkR) {
					validCell = true;
					return validCell;
				}
				else{
					validCell = false;
				}
				
				
			}
			else{
				validCell = false;
			}
			
				
			}
		
			return validCell;

		}
	

	public static boolean intorString(String intorStringtest) {
		//this will check if the given string can qualify as an int
		try {
			givenvalue = Integer.parseInt(intorStringtest);
			intString = true;
			return intString;
		}
		catch(final NumberFormatException e){
			
			intString = false;
			return intString;
		}
		
	}

	public static boolean checkCol(String case2) {
		if (case2.equalsIgnoreCase("A")){
			cellLoc2 = 0;
			checkColumn = true;

		} else if (case2.equalsIgnoreCase("B")){
			cellLoc2 = 1;
			checkColumn = true;
		} else if(case2.equalsIgnoreCase("C")){
			cellLoc2 = 2;
			checkColumn = true;
		} else if(case2.equalsIgnoreCase("D")){
			cellLoc2 = 3;
			checkColumn = true;
		} else if(case2.equalsIgnoreCase("E")){
			cellLoc2 = 4;
			checkColumn = true;
		} else if(case2.equalsIgnoreCase("F")){
			cellLoc2 = 5;
			checkColumn = true;
		} else if(case2.equalsIgnoreCase("G")){
			cellLoc2 = 6;
			checkColumn = true;
		} else{
			checkColumn = false;
			validCell = false;
			return checkColumn;
		}
		return checkColumn;
	}
	public static boolean checkRow(String rowTest) {
		try {
			cellLoc1 = Integer.parseInt(rowTest);
			if(cellLoc1 == 0 || cellLoc1 > 10){
				checkR = false;
				return checkR;
			}
			else{
				checkR = true;
			}
			
			return checkR;
		}
		catch(final NumberFormatException catcherror){
			checkR = false;
			validCell = false;
			return checkR;
		}
		
		
	}

}
