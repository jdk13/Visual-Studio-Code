
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
	static boolean isAFormula = false;
	static boolean mathForm = false;
	static boolean returnedAssignment = false;
	static boolean avg = false;
	static boolean isSort = false;

	public static void main(String args[]) {
		boolean quit = false;

		Scanner input = new Scanner(System.in);

		// returning same input
		while (!quit) {
			
			isAFormula = false;
			cellLoc1 = 0;
			cellLoc2 = 0;
			System.out.print("Enter:  ");
			String answer = input.nextLine();
			System.out.print("");
			if (answer.equalsIgnoreCase("print")) {
				gr.printGrid();

			} else if (answer.equalsIgnoreCase("clear")) {
				for (int i = 0; i < 10; i++) {
					for (int k = 0; k < 7; k++) {
						Grid.spreadsheet[i][k] = null;
					}
				}
				System.out.println("Cleared!");
			} else if (answer.equalsIgnoreCase("Quit")) {
				quit = true;
				System.out.println("You have exited");
			} else {
				validCell = false;
				returnedAssignment = false;
				// cellAssignment(answer);
				returnedAssignment = cellAssignment(answer);
				// a better name for the method should have been "AnswerChecker"
				if (!returnedAssignment) {
					System.out.println("");
					System.out.println(answer);
					System.out.println("");
				}

			}

		}

		input.close();

	}

	public static boolean cellAssignment(String testCase) {
		// ok so here we are taking whatever the input is and checking if we can assign
		// it

		testcmd = testCase.split(" ");

		if (testcmd.length > 3 && testcmd[1].equals("=")) {
			for (int k = 3; k < testcmd.length; k++) {
				String temp = testcmd[k];
				testcmd[2] += " " + temp;
			}
		}
		if (testcmd.length == 1) {

			// this whole if is for retrieving cells
			if (checkCell(testcmd[0])) {
				validCell = true;
				try {
					System.out.println("");
					System.out.println(Grid.getCell(cellLoc1, cellLoc2));
					System.out.println("");
					return true;
				} catch (NullPointerException e) {
					System.out.println("");
					System.out.println("There's nothing here!");
					System.out.println("");
					return true;
				}
			} else {
				return false;
			}
		}
		// if the 2nd element is an =, then we can run these tests
		else if (testcmd.length > 1) {
			if(testcmd[0].equalsIgnoreCase("Sorta") || testcmd[0].equalsIgnoreCase("Sortd")) {
				String[] testSort = checkSort(testcmd);
				if(isSort) {
					Grid.sort(testSort);
					return true;
				}
			}
			if (testcmd[1].equals("=")) {

				// this will check if the requested cell location and its contents are valid
				if (checkCell(testcmd[0])) {
					String[] formulaInput = testcmd[2].split(" ");

					// check if formula cell first
					String[] compress = isItAFormula(formulaInput);
					if (formulaInput.length == 1 && checkCell(formulaInput[0])) {
						int cellLoc3 = checkCol(testcmd[0].substring(0, 1));
						int cellRow3 = checkRow(testcmd[0].substring(1));
						Grid.assignFormulaCell(cellRow3, cellLoc3, compress);
						return true;
					}

					// else

					if (intorString(testcmd[2])) { // cell as an int
						int cellLoc3 = checkCol(testcmd[0].substring(0, 1));
						int cellRow3 = checkRow(testcmd[0].substring(1));
						Grid.assignIntCell(cellRow3, cellLoc3, givenvalue);
						return true;
					} else if (checkifDate(testcmd[2])) { // cell as a date
						Grid.assignDateCell(cellLoc1, cellLoc2, testcmd[2]);
						return true;
					} else if ((testcmd[2].startsWith("\"")) && testcmd[2].endsWith("\"")) { // cell as a string
						Grid.assignStringCell(cellLoc1, cellLoc2, testcmd[2].substring(1, testcmd[2].length() - 1));
						return true;
					} else if (isAFormula) {
						checkCell(testcmd[0]);
						Grid.assignFormulaCell(cellLoc1, cellLoc2, compress);
						return true;
					}

					else {
						return false;

					}

				} else {
					returnedAssignment = false;
					return false;
				}
			} else if (testcmd.length == 2) {
				checkCell(testcmd[1]);
				if (validCell && testcmd[0].equalsIgnoreCase("clear")) {
					Grid.spreadsheet[cellLoc1 - 1][cellLoc2] = null;
					System.out.println("Cleared!");
					return true;
				}

			} else {
				returnedAssignment = false;
				return false;
			}
		}
		return false;
	}

	private static String[] checkSort(String[] formulaInput) {
		String[] special;
		if (formulaInput[2].equalsIgnoreCase("-")) {
			if (checkCell(formulaInput[1]) && checkCell(formulaInput[3])) {
				//s and p are the values of different number
				// if the bounds were A1 - A3
				// s would be 1 and p would be 3
				//l is the difference that helps determine the array
				//r is the other one used for indexing the spreadsheet
				int s = 0;
				int l = 0;
				int p = 0;
				int r = 0;
				boolean row = false;
				boolean col = false;
				if (formulaInput[1].substring(1).equals(formulaInput[3].substring(1))) {
					s = checkCol(formulaInput[1].substring(0, 1));
					p = checkCol(formulaInput[3].substring(0, 1));
					r = checkRow(formulaInput[1].substring(1)) - 1;
					col = true;
				}
				String a = formulaInput[1].substring(0, 1);
				if (a.equals(formulaInput[3].substring(0, 1))) {
					s = checkRow(formulaInput[1].substring( 1)) - 1;
					p = checkRow(formulaInput[3].substring( 1)) - 1;
					r = checkCol(formulaInput[3].substring(0, 1));
					row = true;
				}
				if (row && col) {
					isSort = false;
					return formulaInput;
				}
				l = s - p;
				if (l < 0) {
					l = l * -1;
				}
				special = new String[l + 1];
				if (s < p) {
					int index = 0;
					for (int w = s; w <= p; w++) {
						if (row) {
							if (Grid.spreadsheet[w][r].equals(null)) {
								isSort = false;
								return special;
							} else {
								special[index] = Grid.spreadsheet[w][r].cellName;
							}
						}
						if (col) {
							if (Grid.spreadsheet[r][w].equals(null)) {
								isSort = false;
								return special;
							} else {
								special[index] = Grid.spreadsheet[r][w].cellName;
							}
						}
						//B2 - B7
						// B2 + B3 + b4 + b5 + b6 + b7

						
						index ++;
					}
				}
				else {
					int index = 0;
					for (int w = p; w <= s; w++) {
						if (row) {
							if (Grid.spreadsheet[w][r] == null) {
								isAFormula = false;
								return special;
							} else {
								special[index] = Grid.spreadsheet[w][r].cellName;
							}
						}
						if (col) {
							if (Grid.spreadsheet[r][w] == null) {
								isSort = false;
								return special;
							} else {
								special[index] = Grid.spreadsheet[w][r].cellName;
							}
						}
						// B2 - B7
						// B2 + B3 + b4 + b5 + b6 + b7

						if (index != special.length - 1) {
							special[index + 1] = "+";
						}
						index += 2;
					}
				}
				
			} else {
				isSort = false;
				return formulaInput;
			}
			isSort = true;
			return special;
		}
		return formulaInput;
	}

	// this effectively splits everything by spaces into arrays
	// and this for loop effectively mashes every element past the third element
	// into the third element

	public static boolean checkifDate(String string) {
		if (string.contains("/")) {
			if (string.indexOf("/") == 2 && string.indexOf("/", 3) == 5) {
				int monthTest = Integer.parseInt(string.substring(0, 2));
				if (monthTest <= 12 && monthTest > 0) {
					int dayTest = Integer.parseInt(string.substring(3, 5));
					if (dayTest <= 31 && dayTest > 0) {
						int yearTest = Integer.parseInt(string.substring(6, 10));
						if (yearTest >= 1000 && yearTest <= 9999) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;

			}
		} else {
			return false;
		}
	}

	public static boolean checkCell(String celltest) {
		// celltest is loc eg A3 or C4
		// cell test 2 is whatever comes after
		if (celltest.length() < 4) {
			if (celltest == "") {
				return false;
			}
			String case1 = celltest.substring(0, 1);
			// splits the letter
			String case2 = celltest.substring(1);
			// splits the number just in case its 10
			/*
			 * A4 = 3454
			 * A4 = celltest
			 * case1 = A
			 * case2 = 4
			 */

			// if any of the conditions are met, then it will return true
			// and cellloc will be the index
			// System.out.println(case1);
			if (checkCol(case1) == -1) {
				return false;
			} else if (checkRow(case2) == -1) {
				return false;
			} else {
				validCell = true;
				return true;
			}

		}

		return false;

	}

	public static boolean intorString(String intorStringtest) {

		// this will check if the given string can qualify as an int
		try {
			double doubletest = Double.parseDouble(intorStringtest);
			givenvalue = doubletest;

		} catch (java.lang.NumberFormatException e) {

			intString = false;
			return intString;
		}

		intString = true;
		return intString;

	}

	public static int checkCol(String case2) {

		if (case2.equalsIgnoreCase("A")) {
			cellLoc2 = 0;
			validCell = true;
			return 0;

		} else if (case2.equalsIgnoreCase("B")) {
			cellLoc2 = 1;
			validCell = true;
			return 1;
		} else if (case2.equalsIgnoreCase("C")) {
			cellLoc2 = 2;
			validCell = true;

			return 2;
		} else if (case2.equalsIgnoreCase("D")) {
			cellLoc2 = 3;
			validCell = true;

			return 3;
		} else if (case2.equalsIgnoreCase("E")) {
			cellLoc2 = 4;
			validCell = true;

			return 4;
		} else if (case2.equalsIgnoreCase("F")) {
			cellLoc2 = 5;
			validCell = true;

			return 5;
		} else if (case2.equalsIgnoreCase("G")) {
			cellLoc2 = 6;
			validCell = true;

			return 6;
		} else {
			validCell = false;
			return -1;
		}
	}

	public static int checkRow(String rowTest) {
		try {
			int checkingRow = Integer.parseInt(rowTest);

			if (checkingRow > 0 && checkingRow < 11) {
				cellLoc1 = checkingRow;
				return cellLoc1;
			} else {
				return -1;
			}
		} catch (final NumberFormatException catcherror) {
			validCell = false;
			return -1;
		}

	}

	public static String[] isItAFormula(String[] formulaInput) {
		// Formula Req
		// needs operation
		// needs cell
		// needs at least one other value
		// needs to mash all quotes into one in case of spaces in the quote

		String[] mini;
		mathForm = true;
		String[] special;
		// if has sum or avg, we check which one to traverse
		// and then check if every cell has an int
		//this whole part covers sum and avg
		if (formulaInput[0].equalsIgnoreCase("Sum") || formulaInput[0].equalsIgnoreCase("Avg")) {
			if (formulaInput[2].equalsIgnoreCase("-")) {
				if (checkCell(formulaInput[1]) && checkCell(formulaInput[3])) {
					//s and p are the values of different number
					// if the bounds were A1 - A3
					// s would be 1 and p would be 3
					//l is the difference that helps determine the array
					//r is the other one used for indexing the spreadsheet
					int s = 0;
					int l = 0;
					int p = 0;
					int r = 0;
					boolean row = false;
					boolean col = false;
					if (formulaInput[1].substring(1).equals(formulaInput[3].substring(1))) {
						s = checkCol(formulaInput[1].substring(0, 1));
						p = checkCol(formulaInput[3].substring(0, 1));
						r = checkRow(formulaInput[1].substring(1)) - 1;
						col = true;
					}
					String a = formulaInput[1].substring(0, 1);
					if (a.equals(formulaInput[3].substring(0, 1))) {
						s = checkRow(formulaInput[1].substring( 1)) - 1;
						p = checkRow(formulaInput[3].substring( 1)) - 1;
						r = checkCol(formulaInput[3].substring(0, 1));
						row = true;
					}
					if (row && col) {
						isAFormula = false;
						return formulaInput;
					}
					l = s - p;
					if (l < 0) {
						l = l * -1;
					}
					special = new String[l * 2 + 1];
					if (s < p) {
						int index = 0;
						for (int w = s; w <= p; w++) {
							if (row) {
								if (Grid.spreadsheet[w][r].equals(null)) {
									isAFormula = false;
									return formulaInput;
								} else {
									special[index] = Grid.spreadsheet[w][r].cellName;
								}
							}
							if (col) {
								if (Grid.spreadsheet[r][w].equals(null)) {
									isAFormula = false;
									return formulaInput;
								} else {
									special[index] = Grid.spreadsheet[r][w].cellName;
								}
							}
							//B2 - B7
							// B2 + B3 + b4 + b5 + b6 + b7

							if (index != special.length - 1) {
								special[index + 1] = "+";
							}
							index += 2;
						}
					}
					else {
						int index = 0;
						for (int w = p; w <= s; w++) {
							if (row) {
								if (Grid.spreadsheet[w][r] == null) {
									isAFormula = false;
									return formulaInput;
								} else {
									special[index] = Grid.spreadsheet[w][r].cellName;
								}
							}
							if (col) {
								if (Grid.spreadsheet[r][w] == null) {
									isAFormula = false;
									return formulaInput;
								} else {
									special[index] = Grid.spreadsheet[w][r].cellName;
								}
							}
							// B2 - B7
							// B2 + B3 + b4 + b5 + b6 + b7

							if (index != special.length - 1) {
								special[index + 1] = "+";
							}
							index += 2;
						}
					}
					
				} else {
					isAFormula = false;
					return formulaInput;
				}
			} else {
				isAFormula = false;
				return formulaInput;
			}
			if (formulaInput[0].equalsIgnoreCase("Avg")) {
				avg = true;
			}
			isAFormula = true;
			return special;

		}

		for (String a : formulaInput) { // If has " then it cannot be a math
			if (a.startsWith("\"")) {
				mathForm = false;
			}
		} // addressing the quotes
		int reduce = 0;

		if (!mathForm) {
			for (String a : formulaInput) {
				if (a.equals("-") || a.equals("*") || a.equals("/")) {
					mini = new String[1];
					isAFormula = false;
					mini[0] = "Invalid";
					return mini;
				}
			}
			boolean during = false; // during means if we are still within quote

			for (int i = 0; i < formulaInput.length; i++) {
				if (during) {
					reduce++;
				}
				if (formulaInput[i].startsWith("\"") && !during) {
					during = true;
				}
				if (formulaInput[i].endsWith("\"") && during) {
					during = false;

				}
			}

		} // if it isn't a math formula, there must only be +
		//and this whole for loop is a way to mash strings with 
		//spaces in them into a single element
		mini = new String[formulaInput.length - reduce];
		int k = 0;
		for (int j = 0; j < mini.length; j++, k++) {
			mini[j] = formulaInput[k];
			if (formulaInput[k].startsWith("\"") && !formulaInput[k].endsWith("\"")) {
				k++;
				try {
					while (!formulaInput[k].endsWith("\"")) {
						mini[j] += " " + formulaInput[k];
						k++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					isAFormula = false;
					mini[0] = "Invalid";
					return mini;
				}
				mini[j] += " " + formulaInput[k];
				j += k;
				// C4 + "f e g " + " asd"
				// "f
				// e
				// g
				// "
			}

		}
		for (int h = 0; h < mini.length; h++) {
			if (mini[h] == null) {
				mini[h] = "";
			}
		}

		// at this point, mini has been reformed so that an element has an entire quote
		// and if Mini isn't a math form but has */- it needs to return false

		if (mini.length > 1) {
			for (int m = 1; m < mini.length; m += 2) { // check op
				if (!checkOperation(mini[m])) {
					isAFormula = false;
					return mini;
				}

			}
		} else {
			isAFormula = false;
			return mini;
		} // check cell or text
			// If Cell, get the value (done)

		// Check if is an int /check if it starts and end with quotes
		// If none, then we false
		// But if any are dates, automatic false (done)
		int allMath = 0;
		boolean hasCell = false;
		/*
		 * if Cells is Int
		 * 
		 * Non Cell can be String or Int
		 * 
		 * if Cells is String
		 * 
		 * Non Cell can only be string
		 */
		for (int index = 0; index < mini.length; index += 2) {
			String u = "";
			if (checkCell(mini[index])) {
				hasCell = true;
				u = mini[index];
			} else {
				u = mini[index];
			}

			if (checkifDate(u)) {
				isAFormula = false;
				mini[0] = "Invalid";
				return mini;
			}
			try {
				double d = Double.parseDouble(u);

				allMath++;
			} catch (NumberFormatException e) {

				if (u.startsWith("\"") && u.endsWith("\"") && mathForm) {
					isAFormula = false;
					mini[0] = "Invalid";
					return mini;
				} else {
					isAFormula = true;
				}

			}

		}
		if (((mini.length + 1) / 2 == allMath) && mathForm && hasCell) {
			isAFormula = true;
			return mini;
		}
		if (!mathForm) {
			int allPlus = 0;
			for (int o = 1; o < mini.length; o += 2) {
				if (mini[o].equals("+")) {
					allPlus++;
				}
			}
			if (allPlus == mini.length / 2) {
				isAFormula = true;
				return mini;
			} else if (mini.length == 1) {
				isAFormula = false;
				return mini;
			} else {
				isAFormula = false;
				mini[0] = "Invalid";
				return mini;
			}

		}
		// mini[0] = "Invalid";
		// new array is mini and has been modified
		return mini;

	}

	private static boolean checkOperation(String potentialOp) {
		if (potentialOp.equalsIgnoreCase("+")) {
			return true;
		} else if (potentialOp.equalsIgnoreCase("-")) {
			return true;
		} else if (potentialOp.equalsIgnoreCase("*")) {
			return true;
		} else if (potentialOp.equalsIgnoreCase("/")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getCell(String cell) {
		String case1 = cell.substring(0, 1);
		String case2 = cell.substring(1);
		return Grid.getCell(checkRow(case2), checkCol(case1));
	}

}
