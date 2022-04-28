public class Cell {
	double cellNum;
	int cellNum2;
	String cellText;
	String DateText;
	String finalresult = "";

	public Cell(double cellNum){
		this.cellNum = cellNum;
		finalresult = cellNum + "";
		
		
	}
	public Cell(int cellNum){
		this.cellNum2 = cellNum;
		finalresult = cellNum2 + "";
	}
	public static String convertToInt(double d){
		String num = Double.toString(d);
		if(num.substring(num.length()-1).equals("0")){
			num = num.substring(0, num.length()-2);
		}
		return num;
	}
	
	public String toString(){
		return cellNum + "";
	}
	
}
