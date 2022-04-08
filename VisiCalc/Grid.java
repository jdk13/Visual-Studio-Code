
public class Grid {
	
	Cell[][] spreadsheet;
	
	public Grid(int width, int length) {
		spreadsheet = new Cell[width][length];
		for (int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				// spreadsheet[j][i] = new Cell();
			}
		}
		
	}
	public void printGrid() {
		// printing the grid
		System.out.print("      |    A    |    B    |    C    |    D    |    E    |    F    |    G    |");
		for (int i = 0; i < 10; i++) {
			System.out.println("");
			// Alternating between dashes and cells

			printDash(77); // I tried recursion and it looks cool
			/*for (int j = 0; j < 72; j++) {
				System.out.print("-");
			}*/
			System.out.println("");

			// who came up with the idea
			// that the number 10 takes up two characters
			// Printing the labels followed by the cells, may have to change
			// depending on information in the cell in future checkpoints
			if (i == 9) {
				System.out.print("   " + (i+1) + " |");

			} else {
				System.out.print("   " + (i+1) + "  |");
			}
			//for some reason code crashes at index error when using the variable in the loop
			//so i had to use another variable

			for (int k = 0; k < 7; k++) {
				if (spreadsheet[i][k] == null) {
					System.out.print("         |");
				} else {				    
					System.out.print(spreadsheet[i][k].input);
					
					
				}

			}

		}
		System.out.println("");
		printDash(77);
		System.out.println("");
		

	}
	public static void printDash(int n){
	    if(n > 0){
	        System.out.print("-");
	        printDash(n-1);
	    }
	    
	}
	
	/*public static void createNewCell(int column, int row) {
		spreadsheet[row-1][column-1] = new Cell();
	}
	
	
	*/

}


/*
      |  A  |  B  |  C  |  D  |  E  |  F  |  G  |
--------------------------------------------------
   1  |     |     |     |     |     |     |     |
--------------------------------------------------
   2  |     |     |     |     |     |     |     |
--------------------------------------------------
   3  |     |     |     |     |     |     |     |
--------------------------------------------------
   4  |     |     |     |     |     |     |     |
--------------------------------------------------
   5  |     |     |     |     |     |     |     |
--------------------------------------------------
   6  |     |     |     |     |     |     |     |
--------------------------------------------------
   7  |     |     |     |     |     |     |     |
--------------------------------------------------
   8  |     |     |     |     |     |     |     |
--------------------------------------------------
   9  |     |     |     |     |     |     |     |
--------------------------------------------------
   10 |     |     |     |     |     |     |     | */