package threeInARow;

public class AdvancedPlayer extends Player {

	private static final int  MOVES_LOOK_AHEAD = 2;
	
	protected AdvancedPlayer(Player opponent,char mark) {
		super(opponent,mark);
		opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		Node root = createMinMaxTree(board);
		return null;
	}

	private Node createMinMaxTree(Board board) {
		Node root = new Node(null,(char[][])board.board.clone(),null,this);
		return root;
	}
	
	private void generateMaxNodes(Node node,Player player,int depth){
		if(depth ==  MOVES_LOOK_AHEAD)
			return;
		char [][]state = node.getState();
		for(int i =0;i<4;i++)
			for(int j =0;j<4;j++)
				if(state[i][j] == '-'){
					char [][] newState = (char[][]) state.clone();
					newState[i][j] = player.mark;
					Node n = new Node(node,newState,new Move(i,j),player);
					generateMinNodes(n,player.opponent,depth++);
				}
	}
	
	private void generateMinNodes(Node node,Player player,int depth){
		if(depth == MOVES_LOOK_AHEAD)
			return;
		char [][]state = node.getState();
		for(int i =0;i<4;i++)
			for(int j =0;j<4;j++)
				if(state[i][j] == '-'){
					char [][] newState = (char[][]) state.clone();
					newState[i][j] = player.mark;
					Node n = new Node(node,newState,new Move(i,j),player);
					generateMaxNodes(n,player.opponent,depth++);
				}
	}
	
	

}
