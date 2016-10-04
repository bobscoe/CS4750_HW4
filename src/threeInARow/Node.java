package threeInARow;

public class Node {
	private char[][] state ;
	private Move move;
	private Player player;
	private Node parent;
//	private boolean isTerminalNode = false;
	public Node(Node parent,char[][] state, Move move, Player player){
		this.parent = parent;
		this.state = state;
		this.move = move;
		this.player = player;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public Move getNextMove(){
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(state[i][j] == '-')
					return new Move(i,j);
		return null;
	}
	
	public char[][] getState(){
		return this.state;
	}
	
//	public int getHeuristicValue(){
//		return 0;
//	}
}
