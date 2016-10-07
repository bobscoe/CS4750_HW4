package threeInARow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

	protected HumanPlayer(Player opponent, char mark) {
		super(opponent, mark);
		opponent.setOpponent(this);
	}

	public Move getMove(Board board) {
		System.out.println("Here is the current board status");
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				System.out.print(board.board[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("Enter move in row,column format");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String position = "";
		try {
			position = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] rowColumn = position.split(",");
		return new Move(Integer.parseInt(rowColumn[0]), Integer.parseInt(rowColumn[1]));
	}

	@Override
	public int getNumberOfNodesGenerated() {
		return 0;
	}

	@Override
	public void resetNumberOfNodesGenerated() {
		
	}

}
