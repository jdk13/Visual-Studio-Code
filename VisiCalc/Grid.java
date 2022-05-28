import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Grid {

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
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1);

	}

	public static void assignStringCell(int cellLoc1, int cellLoc2, String input) {

		spreadsheet[cellLoc1 - 1][cellLoc2] = new TextCell(input);
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1);
	}

	public static void assignDateCell(int cellLoc1, int cellLoc2, String input) {
		spreadsheet[cellLoc1 - 1][cellLoc2] = new DateCell(input);
		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1);
	}

	public static String getCell(int l, int c) {
		if ((spreadsheet[l - 1][c] instanceof FormulaCell)) {
			spreadsheet[l - 1][c].updateCell(spreadsheet[l - 1][c]);
		}
		spreadsheet[l - 1][c].assignCellName(c, l);

		return spreadsheet[l - 1][c].finalresult;
	}

	public static void assignFormulaCell(int cellLoc1, int cellLoc2, String[] formulaInput) {

		if (VisiCalc.avg) {
			spreadsheet[cellLoc1 - 1][cellLoc2] = new FormulaCell(formulaInput, true);

		} else {
			spreadsheet[cellLoc1 - 1][cellLoc2] = new FormulaCell(formulaInput, false);
		}

		spreadsheet[cellLoc1 - 1][cellLoc2].assignCellName(cellLoc2, cellLoc1);

	}

	public static void sort(String[] testSort) {
		ArrayList<Cell> sort = new ArrayList<Cell>();
		for (int i = 0; i < testSort.length; i++) {
			int Row = VisiCalc.checkRow(testSort[i].substring(1)) - 1;
			int Col = VisiCalc.checkCol(testSort[i].substring(0, 1));
			sort.add(spreadsheet[Row][Col]);

		}

		Collections.sort(sort, new Comparator<Cell>() {

			@Override
			public int compare(Cell o1, Cell o2) {
				if (o1 instanceof DateCell && o2 instanceof DateCell) {
					int year = o1.finalresult.substring(6).compareTo(o2.finalresult.substring(6));
					if (year == 0) {
						int month = o1.finalresult.substring(0, 2).compareTo(o2.finalresult.substring(0, 2));
						if (month == 0) {
							return o1.finalresult.substring(3, 5).compareTo(o2.finalresult.substring(3, 5));
						} else {
							return month;
						}
					} else {
						return year;
					}
				}
				return o1.finalresult.compareTo(o2.finalresult);
			}

		});
		int firstRow = VisiCalc.checkRow(testSort[0].substring(1)) - 1;
		int secRow = VisiCalc.checkRow(testSort[testSort.length - 1].substring(1)) - 1;
		int firstCol = VisiCalc.checkCol(testSort[0].substring(0, 1));
		int secCol = VisiCalc.checkCol(testSort[testSort.length - 1].substring(0, 1));
		// A, 1, A, 3
		if (firstCol == secCol) {
			int p = 0;
			for (Cell c : sort) {

			}

		}

	}

	public static void sort(ArrayList<Cell> list) {

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