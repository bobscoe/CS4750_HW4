package threeInARow;

public class AdvancedPlayer extends Player {

	protected AdvancedPlayer(Player opponent,char mark) {
		super(opponent,mark);
		opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
