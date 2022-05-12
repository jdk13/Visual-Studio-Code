
public class FormulaCell extends Cell {
	String input;

	public FormulaCell(String[] input) {
		super(0);
		formulaText = convert(input);
		finalresult = formulaText + "";
		
		// TODO Auto-generated constructor stub
	}

	private String convert(String[] formula) {
		int formulaInt = 0;
		
		for (String u : formula) {
			if(VisiCalc.checkCell(u)){
				String k = VisiCalc.getCell(u);
				
			
			}
		}
		
		return null;
	}

}
