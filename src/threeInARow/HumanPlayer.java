package threeInARow;

public class HumanPlayer extends Player{

	protected HumanPlayer(Player opponent,char mark) {
		super(opponent,mark);
		opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
