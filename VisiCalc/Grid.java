
public class Grid {
	static boolean spec = false;
	static Cell[][] spreadsheet;
	static int let = -1;

	public Grid(int width, int length) {
		spreadsheet = new Cell[width][length];

	}

	public void printGrid() {
		// printing the grid
		System.out.print("      |    A    |    B    |    C    |    D    |    E    |    F    |    G    |");
		for (int i = 0; i < 10; i++) {
			System.out.println("");
			// Alternating between dashes and cells

			printDash(77); // I tried recursion and it looks cool
			/*
			 * for (int j = 0; j < 72; j++) {
			 * System.out.print("-");
			 * }
			 */
			System.out.println("");

			// who came up with the idea
			// that the number 10 takes up two characters
			// Printing the labels followed by the cells, may have to change
			// depending on information in the cell in future checkpoints
			if (i == 9) {
				System.out.print("   " + (i + 1) + " |");

			} else {
				System.out.print("   " + (i + 1) + "  |");
			}
			// for some reason code crashes at index error when using the variable in the
			// loop
			// so i had to use another variable

			for (int k = 0; k < 7; k++) {
				if (spreadsheet[i][k] == null) {
					System.out.print("         |");
				} else {

					if (spreadsheet[i][k].finalresult.length() < 10) {
						if (spreadsheet[i][k] instanceof FormulaCell) {
							spreadsheet[i][k].updateCell(spreadsheet[i][k]);
						}

						System.out.print(spreadsheet[i][k].finalresult);

						if (spreadsheet[i][k].finalresult.length() < 9) {
							int f = 9 - spreadsheet[i][k].finalresult.length();

							for (int l = 0; l < f; l++) {
								System.out.print(" ");
							}

						}
					}

					else {
						System.out.print(spreadsheet[i][k].finalresult.substring(0, 9));
					}

					System.out.print("|");

				}

			}

		}
		System.out.println("");
		printDash(77);
		System.out.println("");

	}

	public void printDash(int n) {
		if (n > 0) {
			System.out.print("-");
			printDash(n - 1);
		}

	}

	public static void assignIntCell(int cellLoc1, int cellLoc2, double givenvalue) {
		String thing = Cell.convertToInt(givenvalue);
		if (thing.contains(".")) {
			spreadsheet[cellLoc1 - 1][cellLoc2] = new Cell(givenvalue);
		} else {
			spreadsheet[cellLoc1 - 1][cellLoc2] = new Cell(Integer.parseInt(thing));
		}
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1 );

	}

	public static void assignStringCell(int cellLoc1, int cellLoc2, String input) {

		spreadsheet[cellLoc1 - 1][cellLoc2] = new TextCell(input);
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1 );
	}

	public static void assignDateCell(int cellLoc1, int cellLoc2, String input) {
		spreadsheet[cellLoc1 - 1][cellLoc2] = new DateCell(input);
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1 );
	}

	public static String getCell(int l, int c) {
		if ((spreadsheet[l - 1][c] instanceof FormulaCell)) {
			spreadsheet[l - 1][c].updateCell(spreadsheet[l - 1][c]);
		}
		spreadsheet[l-1][c].assignCellName(c, l);

		return spreadsheet[l - 1][c].finalresult;
	}

	public static void assignFormulaCell(int cellLoc1, int cellLoc2, String[] formulaInput) {
		
		spreadsheet[cellLoc1 - 1][cellLoc2] = new FormulaCell(formulaInput);
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1);
		if (VisiCalc.avg) {
			spreadsheet[cellLoc1 - 1][cellLoc2].aver = true;
		}
	}

	/*
	 * public static void createNewCell(int column, int row) {
	 * spreadsheet[row-1][column-1] = new Cell();
	 * }
	 * 
	 * 
	 */

}

/*
 * | A | B | C | D | E | F | G |
 * --------------------------------------------------
 * 1 | | | | | | | |
 * --------------------------------------------------
 * 2 | | | | | | | |
 * --------------------------------------------------
 * 3 | | | | | | | |
 * --------------------------------------------------
 * 4 | | | | | | | |
 * --------------------------------------------------
 * 5 | | | | | | | |
 * --------------------------------------------------
 * 6 | | | | | | | |
 * --------------------------------------------------
 * 7 | | | | | | | |
 * --------------------------------------------------
 * 8 | | | | | | | |
 * --------------------------------------------------
 * 9 | | | | | | | |
 * --------------------------------------------------
 * 10 | | | | | | | |
 */