package threeInARow;

public class Board {
	
	public char[][] board = null;
	
	public Board() {
		initializeBoard();
	}
	
	public void initializeBoard(){
		this.board = new char[4][4];
	}
	
	public int getWidth(){
		return 4;
	}
	
	public int getHeight(){
		return 4;
	}

}
