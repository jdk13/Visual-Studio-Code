
public class FormulaCell extends Cell {
	String input;
	String wholearray[];
	public FormulaCell(String[] input) {
		super(0);
		wholearray = input;
		formulaText = convert(input);
		finalresult = formulaText + "";
	}

	private String convert(String[] formula) {
		if(VisiCalc.mathForm){
			double totalval = MathIt(wholearray.length - 1);
			String totalval0 = totalval + "";
			int possibleParse = 0;
			if(totalval0.endsWith(".0")){
				possibleParse = (int) Double.parseDouble(totalval0);
				return possibleParse + "";
			}
			
			return totalval0 + "";
		}
		else{
			String cat = "";
			for (int e = 0; e < wholearray.length; e += 2) {
				String possibleCell = "";
				if (VisiCalc.checkCell(wholearray[e])) {
					possibleCell = VisiCalc.getCell(wholearray[e]);
					cat += possibleCell;
				}
				else{
					cat += wholearray[e];
				}
			}
			return cat;
		}
	}
	public double MathIt(int index) {
		double element = 0;
		if(index >= 0 ){
			element = Double.parseDouble(wholearray[index]);
		}
		if(index == 0){
			return element;
		}
		if(wholearray[index-1].equals("/")){
			return MathIt(index - 2) / element;
		}
		else if(wholearray[index-1].equals("-")){
			return MathIt(index - 2) - element;
		}
		else if(wholearray[index-1].equals("*")){
			return MathIt(index - 2) * element;
		}
		else if(wholearray[index-1].equals("+")){
			return MathIt(index - 2) + element;
		}
		return -1;
	}

}
