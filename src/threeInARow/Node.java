package threeInARow;

import java.util.ArrayList;

public class Node {
	private char[][] state ;
	private Move move;
	private Player player;
	private Node parent;
	private int heuristicValue;
	private int gridSize = 4;
	private ArrayList<Node> children = new ArrayList<Node>();
	public Node(Node parent,char[][] state, Move move, Player player){
		this.parent = parent;
		this.state = state;
		this.move = move;
		this.player = player;
	}
	
	public Move getMove(){
		return move;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public void addChild(Node node){
		children.add(node);
	}
	
	public ArrayList<Node> getChildren(){
		return children;
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
	
	public void setHeuristicValue(int value){
		heuristicValue = value;
	}
	
	
	public int getHeuristicValue(){
		return heuristicValue;
	}
	
	public int twoInARowCount(Player player) {
		char mark = player.getMark();
		String[] two_in_a_row_arrangements = { new String(new char[] { '-', mark, mark }),
				new String(new char[] { mark, '-', mark }), new String(new char[] { mark, mark, '-' }) };
		int two_in_a_row_count = 0;

		two_in_a_row_count = twoInARowInRows(two_in_a_row_arrangements);
		two_in_a_row_count += twoInARowInColumns(two_in_a_row_arrangements);
		return twoInARowInDiagonals(two_in_a_row_arrangements) + two_in_a_row_count;
	}

	private int twoInARowInRows(String[] arrangements) {
		int count = 0;
		for (int i = 0; i <gridSize; i++) {
			char[] rowState = new char[gridSize];
			for (int j = 0; j < gridSize; j++) {
				rowState[j] = state[i][j];
			}
			String row = new String(rowState);
			count += successfulArrangementCount(row, arrangements);
		}
		return count;
	}

	private int twoInARowInColumns(String[] arrangements) {
		int count = 0;
		for (int i = 0; i < gridSize; i++) {
			char[] rowState = new char[gridSize];
			for (int j = 0; j < gridSize; j++) {
				rowState[j] = state[j][i];
			}
			String row = new String(rowState);
			count += successfulArrangementCount(row, arrangements);
		}
		return count;
	}

	private int twoInARowInDiagonals(String[] arrangements) {
		int count = 0;
		char[] rowState = new char[gridSize];
		for (int i = 0, j = 0; i < gridSize && j < gridSize; i++, j++) {
			rowState[i] = state[i][j];
		}
		String row = new String(rowState);
		count += successfulArrangementCount(row, arrangements);

		for (int i = gridSize - 1, j = 0; i >= 0 && j < gridSize; i--, j++) {
			rowState[j] = state[i][j];
		}
		row = new String(rowState);
		count += successfulArrangementCount(row, arrangements);
		
		rowState = new char[gridSize - 1];
		for (int i = 1, j = 0; i < gridSize && j < gridSize; i++, j++) {
			rowState[j] = state[i][j];
		}
		row = new String(rowState);
		count += successfulArrangementCount(row, arrangements);

		for (int i = 0, j = 1; i < gridSize && j < gridSize; i++, j++) {
			rowState[i] = state[i][j];
		}
		row = new String(rowState);
		count += successfulArrangementCount(row, arrangements);

		for (int i = gridSize - 2, j = 0; i >= 0 && j < gridSize; i--, j++) {
			rowState[j] = state[i][j];
		}
		row = new String(rowState);
		count += successfulArrangementCount(row, arrangements);


		for (int i = gridSize - 1, j = 1; i >= 0 && j < gridSize; i--, j++) {
			rowState[j - 1] = state[i][j];
		}
		row = new String(rowState);
		count += successfulArrangementCount(row, arrangements);
		return count;
	}

	private int successfulArrangementCount(String boardArrangement, String[] arrangements) {
		int count = 0;
		for (int i = 0; i < arrangements.length; i++) {
			if (boardArrangement.contains(arrangements[i]))
				count++;
		}
		return count;
	}

	public void calculateHeuristicValue() {
		if(player.isMax)
			heuristicValue = twoInARowCount(player) - twoInARowCount(player.opponent);
		else
			heuristicValue = twoInARowCount(player.opponent) - twoInARowCount(player);
	}
}
