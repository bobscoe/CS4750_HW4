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
					if(board.board[i][j] == '-')
						return new Move(i,j);
				}
			}
		}
		return null;
	}
	
	private boolean youHave2InARow(Board board){
		//check rows
		String [] arrangements = { 
				new String(new char[]{'-',mark,mark}),
				new String(new char[]{mark,'-',mark}),
				new String(new char[]{mark,mark,'-'})
				};
		
		for(int i=0;i<board.getHeight();i++){
			char [] rowState = new char[board.getWidth()];
			for(int j=0;j<board.getWidth();j++) {
				rowState[j] = board.board[i][j];
			}
			String row =  new String(rowState);
			for(int k =0;k<arrangements.length;k++){
				if(row.contains(row))
					return true;
			}
		}
		
		//check columns
		for(int i=0;i<board.getWidth();i++){
			char [] rowState = new char[board.getHeight()];
			for(int j=0;j<board.getHeight();j++) {
				rowState[j] = board.board[j][i];
			}
			String row =  new String(rowState);
			for(int k =0;k<arrangements.length;k++){
				if(row.contains(row))
					return true;
			}
		}
		
		//check diagonals
		char [] rowState = new char[board.getHeight()];
		for(int i=0,j=0;i<board.getHeight() && j<board.getWidth();i++,j++){
			rowState[i] = board.board[i][j];
		}
		String row = new String(rowState);
		for(int k =0;k<arrangements.length;k++){
			if(row.contains(row))
				return true;
		}
		
		for(int i=board.getHeight()-1,j=board.getWidth()-1;i>=0 && j>=0;i--,j--){
			rowState[i] = board.board[i][j];
		}
		row = new String(rowState);
		for(int k =0;k<arrangements.length;k++){
			if(row.contains(row))
				return true;
		}
		for(int i=1,j=0;i<board.getHeight() && j<board.getWidth();i++,j++){
			rowState[j] = board.board[i][j];
		}
		row = new String(rowState);
		for(int k =0;k<arrangements.length;k++){
			if(row.contains(row))
				return true;
		}
		for(int i=0,j=1;i<board.getHeight() && j<board.getWidth();i++,j++){
			rowState[j] = board.board[i][j];
		}
		row = new String(rowState);
		for(int k =0;k<arrangements.length;k++){
			if(row.contains(row))
				return true;
		}
		for(int i=board.getHeight(),j=board.getWidth();i>0 && j>0;i--,j--){
			rowState[i] = board.board[i][j];
		}
		row = new String(rowState);
		for(int i=board.getHeight()-2,j=0;i>=0 && j<board.getWidth();i--,j++){
			rowState[i] = board.board[i][j];
		}
		row = new String(rowState);
		for(int k =0;k<arrangements.length;k++){
			if(row.contains(row))
				return true;
		}
		for(int i=board.getHeight()-1,j=1;i>=0 && j<board.getWidth();i--,j++){
			rowState[i] = board.board[i][j];
		}
		row = new String(rowState);
		for(int k =0;k<arrangements.length;k++){
			if(row.contains(row))
				return true;
		}
		return false;
	}
	
	
	private boolean opponentHas2InARow(){
		return false;
	}
}
