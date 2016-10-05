package threeInARow;

public class MasterPlayer extends Player {
	
	private static final int  MOVES_LOOK_AHEAD = 4;

	protected MasterPlayer(Player opponent,char mark) {
		super(opponent,mark);
		if(opponent!=null)
			opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
