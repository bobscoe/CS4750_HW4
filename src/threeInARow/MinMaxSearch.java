package threeInARow;

import java.util.ArrayList;

public class MinMaxSearch {
	private final int MOVES_LOOK_AHEAD;
	private Player player;
	private final int WIN_VALUE = 100;
	private final int LOSS_VALUE = -100;
	private int numberOfNodesGenerated = 0;
	
	public MinMaxSearch(Player player,int movesToLookAhead) {
		this.player = player;
		MOVES_LOOK_AHEAD = movesToLookAhead;
	}

	public Move getOptimalMove(Board board) {
		Node root = createMinMaxTree(board);
		ArrayList<Node> possibleMoves = new ArrayList<Node>();
		for(int i=0;i<root.getChildren().size();i++){
			Node child = root.getChildren().get(i);
			if(root.getHeuristicValue() == child.getHeuristicValue())
				possibleMoves.add(child);
		}
		if(possibleMoves.size()>0){
			int random = (int) (Math.random()*possibleMoves.size()-1);
			return possibleMoves.get(random).getMove();
		}
		return null;
	}
	private Node createMinMaxTree(Board board) {
		Node root = new Node(null,(char[][])board.board.clone(),null,player);
		generateMaxNodes(root,player,0);
		return root;
	}
	
	public int getNumberOfNodesGenerated(){
		return numberOfNodesGenerated;
	}
	
	public void resetNumberOfNodesGenerated(){
		 numberOfNodesGenerated = 0;
	}
	
	private void generateMaxNodes(Node node,Player player,int depth){
		if(depth ==  MOVES_LOOK_AHEAD){
			node.calculateHeuristicValue();
			return;
		}
			
		char [][]state = node.getState();
		int maxHeuristicValue =-1000;
		for(int i =0;i<4;i++)
			for(int j =0;j<4;j++)
				if(state[i][j] == '-'){
					char [][] newState = deepCopy(state);
					newState[i][j] = player.mark;
					Node n = new Node(node,newState,new Move(i,j),player);
					numberOfNodesGenerated++;
					node.addChild(n);
					if(n.isTerminalNode(player))
					{
						maxHeuristicValue = WIN_VALUE;
						n.setHeuristicValue(WIN_VALUE);
						n.getParent().setHeuristicValue(WIN_VALUE);
						continue;
					}
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
					numberOfNodesGenerated++;
					node.addChild(n);
					if(n.isTerminalNode(player))
					{
						minHeuristicValue = LOSS_VALUE;
						n.setHeuristicValue(LOSS_VALUE);
						n.getParent().setHeuristicValue(LOSS_VALUE);
						continue;
					}
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
