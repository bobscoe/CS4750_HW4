package threeInARow;

public class PlayerFactory {
	public Player getPlayer(int difficulty,Player opponent,char mark){
		switch(difficulty){
		case 1: return new BeginnerPlayer(opponent,mark);
		case 2: return new AdvancedPlayer(opponent,mark);
		case 3: return new MasterPlayer(opponent,mark);
		case 4: return new HumanPlayer(opponent,mark);
		default: return null;
		}
	}

}
