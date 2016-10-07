package threeInARow;

public class BeginnerPlayer extends Player {

	public BeginnerPlayer(Player opponent,char mark){
		super(opponent,mark);
		if(opponent!=null)
			opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		if(youHave2InARow(board))
			return getWinningMove(board);
		
		if(opponentHas2InARow(board))
			return getBlockingMove(board);
		
		for(int i=0;i<board.getHeight();i++)
			for(int j=0;j<board.getWidth();j++)
				if(board.board[i][j] == '-')
					return new Move(i,j);
		return null;
	}
	
	private Move getBlockingMove(Board board) {
		return board.getWinningMove(this.opponent);
	}

	private Move getWinningMove(Board board) {
		return board.getWinningMove(this);
	}

	private boolean youHave2InARow(Board board){
		return board.playerHasTwoInARow(this);
	}
	
	private boolean opponentHas2InARow(Board board){
		return board.playerHasTwoInARow(this.opponent);
	}

	@Override
	public int getNumberOfNodesGenerated() {
		return 0;
	}

	@Override
	public void resetNumberOfNodesGenerated() {
		// TODO Auto-generated method stub
		
	}
}
