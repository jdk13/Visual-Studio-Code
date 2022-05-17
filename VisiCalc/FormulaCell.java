
public class FormulaCell extends Cell {
	String input;

	public FormulaCell(String[] input) {
		super(0);
		formulaText = convert(input);
		finalresult = formulaText + "";
		
		// TODO Auto-generated constructor stub
	}

	private String convert(String[] formula) {
		int math = 0;
		for (int i = 0; i < formula.length; i+=2){
			if(VisiCalc.checkCell(formula[i])) {
				if(isItAnum(VisiCalc.getCell(formula[i]))) {
					math++;
				}				
			}
			else {
				if(isItAnum(formula[i])) {
					math++;
				}
			}
		}
		if((formula.length + 1)/2 == math) {
			mathEquation(formula);
		}
		else {
			
		}
		return "";
	}
	
	public int leftAndRight(String test, int direction) {
		return 0;
	}
	
	public boolean isItAnum(String p) {
		double parsed = 0.0;
		try {
			parsed = Double.parseDouble(p);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	public String mathEquation(String[] r) {
		return "";
	}

}
