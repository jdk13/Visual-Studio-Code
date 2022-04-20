public class Cell {
	int cellNum;
	String cellText;
	String finalresult = "";

	public Cell(int cellNum){
		this.cellNum = cellNum;
		finalresult = cellNum + "";
		
		
	}
	public Cell(String cellText){
		this.cellText = cellText;
		finalresult = cellText;
		
	}
	String input;
	public String toString(){
		return cellNum + "";
	}
	
}
