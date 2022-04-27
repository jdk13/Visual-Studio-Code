public class Cell {
	int cellNum;
	String cellText;
	String finalresult = "";

	public Cell(int cellNum){
		this.cellNum = cellNum;
		finalresult = cellNum + "";
		
		
	}
	
	public String toString(){
		return cellNum + "";
	}
	
}
