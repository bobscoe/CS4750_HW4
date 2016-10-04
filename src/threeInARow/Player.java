package threeInARow;

public abstract class Player {
	protected Player opponent;
	protected char mark;
	protected Player(Player opponent,char mark){
		this.opponent = opponent;
		this.mark = mark;
	}
	
	public void setOpponent(Player player){
		this.opponent = player;
	}
	
	public abstract Move getMove(Board board);
	public char getMark(){
		return mark;
	}
}
