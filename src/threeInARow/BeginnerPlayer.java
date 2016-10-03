package threeInARow;

public class BeginnerPlayer extends Player {

	public BeginnerPlayer(Player opponent,char mark){
		super(opponent,mark);
		if(opponent!=null)
			opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		if(youHave2InARow(board)){
			
		}
		if(!opponentHas2InARow())
		{
			for(int i=0;i<board.getHeight();i++){
				for(int j=0;j<board.getWidth();j++){
					if(board.board[i][j] == '\u0000')
						return new Move(i,j);
				}
			}
		}
		return null;
	}
	
	private boolean youHave2InARow(Board board){
		for(int i=0;i<board.getHeight();i++){
			for(int j=0;j<board.getWidth();j++){
				
				if( board.board[i][j] == mark) {
					
					//check square above
					if( board.board[i-1][j] == mark ) {
						return true;
					}
					
					
				}
			}
		}
		
		return false;
	}
	
	private boolean opponentHas2InARow(){
		return false;
	}
}
