// Jacob Kim
// AP Computer Science
// VisiCalc
// Checkpoint 2

import java.util.Scanner;

public class VisiCalc {
	
	
	static Grid gr = new Grid(10, 7);

	boolean CheckNum = false;
	static int cellLoc1;
	static int cellLoc2;
	static double givenvalue;
	static boolean intString;
	static String[] testcmd;
	static boolean validCell = false;
	static boolean stringformula = false;
	

	
	static boolean returnedAssignment = false;

	public static void main(String args[]) {
		boolean quit = false;
		
		Scanner input = new Scanner(System.in);
		
		// returning same input
		while (!quit) {
			String[] miniArray;
			System.out.print("Enter:  ");
			String answer = input.nextLine();
			if (answer.equalsIgnoreCase("print")) {
				gr.printGrid();
				
			} else if(answer.equalsIgnoreCase("clear")) {
				for (int i = 0; i < 10; i++){
					for(int k = 0; k < 7; k++){
						Grid.spreadsheet[i][k] = null;
					}
				}
				System.out.println("Cleared!");
			} else if( answer.equalsIgnoreCase("Quit")) {
				quit = true;
				System.out.println("You have exited");
			}
			else  {
				validCell = false;
				returnedAssignment = false;
				cellAssignment(answer);
				//a better name for the method should have been "AnswerChecker"
				if(!returnedAssignment) {
					System.out.println("");
					System.out.println(answer);
					System.out.println("");
				}
				
				
			} 
				
			}
		

		input.close();

	}
	
	public static boolean cellAssignment(String testCase) {
		//ok so here we are taking whatever the input is and checking if we can assign it

			testcmd = testCase.split(" ");

			if (testcmd.length > 3){
				for (int k = 3; k < testcmd.length; k++){
					String temp = testcmd[k];
					testcmd[2] = testcmd[2] + " " +  temp;
				}
			}
			if(testcmd.length == 1) {
				checkCell(testcmd[0]);
				if(validCell) {
					try {
						System.out.println("");
						System.out.println(Grid.getCell(cellLoc1, cellLoc2));
						System.out.println("");
						return returnedAssignment = true;
					} catch (NullPointerException e) {
						System.out.println("");
						System.out.println("There's nothing here!");
						System.out.println("");
						return returnedAssignment = true;
					}					
				}
			}
			//System.out.println(testcmd[1]);
			//System.out.println(testcmd[2]);
			
			//System.out.println(testcmd.length);
			/*for (int i = 0; i < testcmd.length; i++) {
				System.out.print(testcmd[i]);
				
				
			}*/
			
			//if the 2nd element is an =, then we can run these tests
			if(testcmd.length > 1) {
				if(testcmd[1].equals("=")) {
					
					checkCell(testcmd[0]);
					//this will check if the requested cell location and its contents are valid
					if(validCell){
						String[] formulaInput = testcmd[3].split(" ");

						//check if formula cell first
						String[] miniarray = isItAFormula(formulaInput);
						if(miniarray[0] == "false") {
							checkCell(testcmd[0]);
							Grid.assignFormulaCell(cellLoc1, cellLoc2, formulaInput);
						}
						else if (intorString(testcmd[2])){
							Grid.assignIntCell(cellLoc1, cellLoc2, givenvalue);
							return returnedAssignment = true;
						}
						else if(checkifDate(testcmd[2])){
							Grid.assignDateCell(cellLoc1, cellLoc2, testcmd[2]);
							return returnedAssignment = true;
						}
						else if(testcmd[2].indexOf("\"") == 0 && testcmd[2].endsWith("\"")){
							Grid.assignStringCell(cellLoc1, cellLoc2, testcmd[2].substring(1, testcmd[2].length()-1));
							return returnedAssignment = true;
						}
						else{
							return returnedAssignment = false;

						}
						
					}
					else{
						returnedAssignment = false;
						return returnedAssignment;
					}
				}
				else if(testcmd.length == 2){
					checkCell(testcmd[1]);
					if(validCell && testcmd[0].equalsIgnoreCase("clear")){
						Grid.spreadsheet[cellLoc1-1][cellLoc2] = null;
						System.out.println("Cleared!");
						return returnedAssignment = true;
					}

				}
				else{
					returnedAssignment = false;
					return returnedAssignment;
				}
			}
			return returnedAssignment = false;
		}
		
		
		//this effectively splits everything by spaces into arrays
		//and this for loop effectively mashes every element past the third element 
		//into the third element
		
		
		
		
		
	
	
	public static boolean checkifDate(String string) {
		if(string.contains("/")){
			if(string.indexOf("/") == 2 && string.indexOf("/", 3) == 5){
				int monthTest = Integer.parseInt(string.substring(0, 2));
				if( monthTest <= 12 && monthTest > 0){
					int dayTest = Integer.parseInt(string.substring(3, 5));
					if (dayTest <= 31 && dayTest > 0){
						int yearTest = Integer.parseInt(string.substring(6, 10));
						if(yearTest >= 1000 && yearTest <= 9999){
							return true;
						}
						else{
							return false;
						}
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;

			}
		}
		else{
			return false;
		}
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
			
			//if any of the conditions are met, then it will return true
			//and cellloc will be the index
			//System.out.println(case1);			
			if (!(checkCol(case1) == -1)) {
				//if the checkColumn was returned true
				// it will then check the row
				
				if(!(checkRow(case2) == -1)) {
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
			givenvalue = Double.parseDouble(intorStringtest);
			intString = true;
			return intString;
		}
		catch(final NumberFormatException e){
			
			intString = false;
			return intString;
		}
		
	}

	public static int checkCol(String case2) {
		
		if (case2.equalsIgnoreCase("A")){
			cellLoc2 = 0;
			return 0;

		} else if (case2.equalsIgnoreCase("B")){
			cellLoc2 = 1;
			return 1;
		} else if(case2.equalsIgnoreCase("C")){
			cellLoc2 = 2;
			
			return 2;
		} else if(case2.equalsIgnoreCase("D")){
			cellLoc2 = 3;
			
			return 3;
		} else if(case2.equalsIgnoreCase("E")){
			cellLoc2 = 4;
			
			return 4;
		} else if(case2.equalsIgnoreCase("F")){
			cellLoc2 = 5;
			
			return 5;
		} else if(case2.equalsIgnoreCase("G")){
			cellLoc2 = 6;
			
			return 6;
		} else{
			validCell = false;
			return -1;
		}
	}
	public static int checkRow(String rowTest) {
		try {
			int checkingRow = Integer.parseInt(rowTest);
			cellLoc1 = Integer.parseInt(rowTest);
			if(checkingRow == 0 || checkingRow > 10){
				return checkingRow;
			}
			else{
				return -1;
			}
		}
		catch(final NumberFormatException catcherror){
			validCell = false;
			return -1;
		}
		
		
	}
	
	public static String[] isItAFormula(String[] formulaInput) {
		int lengthofinpu = formulaInput.length;
		int adjustedsize = 0;
		String[] adjust;
		for(int i = 0; i < formulaInput.length; i++){
			if(formulaInput[i].startsWith("\"") && !formulaInput[i].endsWith("\"")){
				boolean finalquote = true;
				int j = i;
				
				while(finalquote){
					if(formulaInput[j].endsWith("\"")){
						finalquote = false;
					}
					else{
						j++;
						adjustedsize++;
					}

				}

			}

		}
		adjust = new String[formulaInput.length - adjustedsize];
		int p = 0;
		for(int k = 0; k < adjust.length; k++, p++){
			adjust[k] = formulaInput[p];
			if(formulaInput[p].startsWith("\"")){
				while(!formulaInput[p].endsWith("\"")){
					adjust[k]+= " " + formulaInput[p];
					p++;
				}
			}

		}

	}

	public static boolean checkOperation(String potentialOp) {
		if(potentialOp.equalsIgnoreCase("+")) {
			return true;
		}
		else if(potentialOp.equalsIgnoreCase("-")) {
			return true;
		}
		else if(potentialOp.equalsIgnoreCase("*")) {
			return true;
		}
		else if(potentialOp.equalsIgnoreCase("/")) {
			return true;
		}
		else {
		return false;
		}
	}
	public static String getCell(String cell){
		String case1 = cell.substring(0,1);
		String case2 = cell.substring(1);
		

		return Grid.getCell(checkRow(case1), checkCol(case2));
	}

}
/*C4 + " asd we" + "we re er " 

0 = C4
1 = +
2 = "
3 = asd
4 = we"
5 = +
6 = "we
7 = re
8 = er
9 = "
String[] strings = new String[formulaInput.length];
int minimizearray = 0;
for(int j = 0; j < formulaInput.length; j++) {
	strings[j] = formulaInput[j];
	if(formulaInput[j].substring(0, 1).equalsIgnoreCase("\"") && !formulaInput[j].endsWith("\"")){
		stringformula = true;
		int o = j + 1;
		while(!formulaInput[o].endsWith("\"")){
			strings[j] += " " + formulaInput[o];
			o++;
			minimizearray++;
		}
		strings[j] += " " + formulaInput[o];
		j = o;
		
	}
}
String[] miniArray = new String[formulaInput.length - minimizearray];
for(int k = 0; k < strings.length - minimizearray; k++){
	miniArray[k] = strings[k];
}

boolean hasOp = false;
boolean hasFormula = false;
boolean hasLeftover = false;
for(int u = 1; u < miniArray.length; u+=2) {
	if((miniArray[u].equals("*") || miniArray[u].equals("/") || miniArray[u].equals("-")) && stringformula) {
		return false;
	}
	else if(checkOperation(miniArray[u])) {
		hasOp = true;
	}
}
int mathEquation = 0;
if(!stringformula){
	for(int p = 0; p < miniArray.length; p+=2) {
		if(checkCell(miniArray[p])) {
			hasFormula = true;
			try {
				double d = Double.parseDouble(getCell(miniArray[p]));
				mathEquation++;

			} catch (NumberFormatException e) {
				mathEquation--;
			}
		}
		else {
			try {
				double d = Double.parseDouble(getCell(miniArray[p]));
				mathEquation++;

			} catch (NumberFormatException e) {
				mathEquation--;
			}
			hasLeftover = true;
		}
	}
}
if(mathEquation ==( miniArray.length  + 1) / 2){
	
}
if( hasFormula && hasLeftover && hasOp) {
	return true;
}



return false;
*/