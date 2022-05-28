public class Cell{
	String cellName;
	double cellNum;
	int cellNum2;
	String cellText;
	String DateText;
	String finalresult = "";
	String formulaText;
	String form[];
	boolean formula;
	boolean aver;

	public Cell(double cellNum) {
		formula = false;
		this.cellNum = cellNum;
		finalresult = cellNum + "";

	}

	public Cell(int cellNum) {
		formula = false;
		this.cellNum2 = cellNum;
		finalresult = cellNum2 + "";
	}

	public static String convertToInt(double d) {
		String num = Double.toString(d);
		if (num.substring(num.length() - 1).equals("0")) {
			num = num.substring(0, num.length() - 2);
		}
		return num;
	}

	public String toString() {
		return cellNum + "";
	}

	public void updateCell(Cell cell) {
		FormulaCell other = (FormulaCell) cell;
		for (int i = 0; i < form.length; i += 2) {
			if (VisiCalc.checkCell(form[i])) {
				other.wholearray[i] = VisiCalc.getCell(form[i]);
			}
		}
		this.finalresult = other.convert(form) + "";

	}

	public void assignCellName(int column, int row) {
		if (column == 0) {
			cellName = "A";
		}
		if (column == 1) {
			cellName = "B";
		}
		if (column == 2) {
			cellName = "C";
		}
		if (column == 3) {
			cellName = "D";
		}
		if (column == 4) {
			cellName = "E";
		}
		if (column == 5) {
			cellName = "F";
		}
		if (column == 6) {
			cellName = "G";
		}
		if (column == 7) {
			cellName = "H";
		}
		cellName += row + "";

	}

	
}