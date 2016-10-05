package threeInARow;

public class MasterPlayer extends Player {
	
	private static final int  MOVES_LOOK_AHEAD = 4;
	private MinMaxSearch search;
	protected MasterPlayer(Player opponent,char mark) {
		super(opponent,mark);
		if(opponent!=null)
			opponent.setOpponent(this);
		search = new MinMaxSearch(this, MOVES_LOOK_AHEAD);
	}

	public Move getMove(Board board) {
		return search.getOptimalMove(board);
	}

}
