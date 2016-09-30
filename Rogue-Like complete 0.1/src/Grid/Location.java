package Grid;

public class Location {
	
	public int CordX;
	public int CordY;
	private static int DefaultSquareSize= 64;
	
	public Location(int X, int Y,boolean IsCord){
		if(IsCord){
			CordX = X;
			CordY = Y;
		}else{
			CordX = X*DefaultSquareSize;
			CordY = Y*DefaultSquareSize;
		}
	}
	
	public Location(Location location) {
		CordX = location.CordX;
		CordY = location.CordY;
	}

	public int getSquareX(){
		return CordX/DefaultSquareSize;
	}
	
	public int getSquareY(){
		return CordY/DefaultSquareSize;
	}
	
	public void setSquareX(int X){
		CordX = X/DefaultSquareSize;
	}
	
	public void setSquareY(int Y){
		CordY= Y/DefaultSquareSize;
	}
}
