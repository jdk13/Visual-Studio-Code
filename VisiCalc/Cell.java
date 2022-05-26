public class Cell {
	double cellNum;
	int cellNum2;
	String cellText;
	String DateText;
	String finalresult = "";
	String formulaText;
	String form[];


	public Cell(double cellNum) {
		this.cellNum = cellNum;
		finalresult = cellNum + "";

	}

	public Cell(int cellNum) {
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
		for(int i = 0; i < form.length; i+=2) {
			if(VisiCalc.checkCell(form[i])){
				other.wholearray[i] = VisiCalc.getCell(form[i]);
			}
		}
		this.finalresult = other.convert(form) + "";
		
	}

}
