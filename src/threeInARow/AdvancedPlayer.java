package threeInARow;

public class AdvancedPlayer extends Player {

	private static final int  MOVES_LOOK_AHEAD = 2;
	
	protected AdvancedPlayer(Player opponent,char mark) {
		super(opponent,mark);
		opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		Node root = createMinMaxTree(board);
		for(int i=0;i<root.getChildren().size();i++){
			Node child = root.getChildren().get(i);
			if(root.getHeuristicValue() == child.getHeuristicValue())
				return child.getMove();
		}
		return null;
	}

	private Node createMinMaxTree(Board board) {
		Node root = new Node(null,(char[][])board.board.clone(),null,this);
		generateMaxNodes(root,this,0);
		return root;
	}
	
	private void generateMaxNodes(Node node,Player player,int depth){
		if(depth ==  MOVES_LOOK_AHEAD){
			node.calculateHeuristicValue();
			return;
		}
			
		char [][]state = node.getState();
		int maxHeuristicValue =-100;
		for(int i =0;i<4;i++)
			for(int j =0;j<4;j++)
				if(state[i][j] == '-'){
					char [][] newState = deepCopy(state);
					newState[i][j] = player.mark;
					Node n = new Node(node,newState,new Move(i,j),player);
					node.addChild(n);
					generateMinNodes(n,player.opponent,depth+1);
					if(n.getHeuristicValue() > maxHeuristicValue){
						maxHeuristicValue = n.getHeuristicValue();
						n.getParent().setHeuristicValue(maxHeuristicValue);
					}
				}
	}
	
	private void generateMinNodes(Node node,Player player,int depth){
		if(depth ==  MOVES_LOOK_AHEAD){
			node.calculateHeuristicValue();
			return;
		}
		int minHeuristicValue= 1000;
		char [][]state = node.getState();
		for(int i =0;i<4;i++)
			for(int j =0;j<4;j++)
				if(state[i][j] == '-'){
					char [][] newState = deepCopy(state);
					newState[i][j] = player.mark;
					Node n = new Node(node,newState,new Move(i,j),player);
					node.addChild(n);
					generateMaxNodes(n,player.opponent,depth+1);
					if(n.getHeuristicValue() < minHeuristicValue){
						minHeuristicValue = n.getHeuristicValue();
						n.getParent().setHeuristicValue(minHeuristicValue);
					}
				}
	}
	
	private char[][] deepCopy(char[][] state){
		char[][] copy = new char[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				copy[i][j] = state[i][j];
		return copy;
	}
	
}
