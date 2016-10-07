package threeInARow;

public class AdvancedPlayer extends Player {

	private static final int  MOVES_LOOK_AHEAD = 2;
	private MinMaxSearch search;
	protected AdvancedPlayer(Player opponent,char mark) {
		super(opponent,mark);
		if(opponent!=null)
			opponent.setOpponent(this);
		search = new MinMaxSearch(this, MOVES_LOOK_AHEAD);
	}

	public Move getMove(Board board) {
		return search.getOptimalMove(board);
	}

	@Override
	public int getNumberOfNodesGenerated() {
		return search.getNumberOfNodesGenerated();
	}
	
	@Override
	public void resetNumberOfNodesGenerated(){
		search.resetNumberOfNodesGenerated();
	}

}
