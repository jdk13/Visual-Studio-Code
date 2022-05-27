
public class FormulaCell extends Cell {
	String input;
	String wholearray[];

	public FormulaCell(String[] input) {
		super(0);
		formula = true;
		int o = 0;
		form = new String[input.length];
		for (String u : input) {
			form[o] = u;
			o++;

		}
		wholearray = input;
		for (int i = 0; i < input.length; i += 2) {
			if (VisiCalc.checkCell(form[i])) {
				wholearray[i] = VisiCalc.getCell(form[i]);
			}
		}
		if (form.length == 1 && VisiCalc.checkCell(form[0])) {
			formulaText = VisiCalc.getCell(form[0]);
		} else {
			formulaText = convert(wholearray);
		}
		

		finalresult = formulaText + "";
	}

	
	public String convert(String[] formula) {

		if (VisiCalc.mathForm) {
			double totalval = MathIt(wholearray.length - 1);
			if (this.aver) {
				int divide = 0;
				for (int y = 0; y < wholearray.length - 1; y += 2) {
					if (VisiCalc.checkCell(wholearray[y])) {
						divide++;
					}
				}
				if (divide != 0) {
					totalval = totalval / divide;
				}
			}
			String totalval0 = totalval + "";
			int possibleParse = 0;
			if (totalval0.endsWith(".0")) {
				possibleParse = (int) Double.parseDouble(totalval0);
				return possibleParse + "";
			}

			return totalval0 + "";
		} // else if (formula[0].equalsIgnoreCase("sum")) {

	//}
		else {
			String cat = "";
			for (int e = 0; e < wholearray.length; e += 2) {
				String possibleCell = "";
				if (VisiCalc.checkCell(wholearray[e])) {
					possibleCell = VisiCalc.getCell(wholearray[e]);
					cat += possibleCell;
				} else {
					cat += wholearray[e];
				}
			}
			return cat;
		}
	}

	public double MathIt(int index) {
		double element = 0.0;

		if (index >= 0) {
			element = Double.parseDouble(wholearray[index]);
		}
		if (index == 0) {
			return element;
		}
		if (wholearray[index - 1].equals("/")) {
			return MathIt(index - 2) / element;
		} else if (wholearray[index - 1].equals("-")) {
			return MathIt(index - 2) - element;
		} else if (wholearray[index - 1].equals("*")) {
			return MathIt(index - 2) * element;
		} else if (wholearray[index - 1].equals("+")) {
			return MathIt(index - 2) + element;
		}
		return -1;
	}


}
