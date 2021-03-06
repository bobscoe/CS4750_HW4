package threeInARow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	private Board gameBoard;
	private Player player1;
	private Player player2;
	
	public Game(Player player1,Player player2) {
		gameBoard = new Board();
		this.player1  = player1;
		this.player2  = player2;
	}
	
	public void start(){
		
		while(!gameBoard.isDraw()){
			try{
			player1.isMax = true;
			player2.isMax = false;
			long startTime = System.currentTimeMillis();
			Move move = player1.getMove(gameBoard);
			long stopTime = System.currentTimeMillis();
			System.out.println("Execution time for move ("+move.Row+","+move.Column+") in milli seconds:"+ (stopTime - startTime));
			System.out.println("Number of nodes generated:" + player1.getNumberOfNodesGenerated());
			player1.resetNumberOfNodesGenerated();
			gameBoard.markMove(player1,move);
			gameBoard.printState();
			if(gameBoard.isComplete(player1)){
				System.out.println("Player 1 wins");
				break;
			}
			
			player1.isMax = false;
			player2.isMax = true;
			startTime = System.currentTimeMillis();
			move = player2.getMove(gameBoard);
			stopTime = System.currentTimeMillis();
			System.out.println("Execution time for move ("+move.Row+","+move.Column+") in milli seconds:"+ (stopTime - startTime));
			System.out.println("Number of nodes generated:" + player2.getNumberOfNodesGenerated());
			player2.resetNumberOfNodesGenerated();
			gameBoard.markMove(player2,move);
			gameBoard.printState();
			if(gameBoard.isComplete(player2)){
				System.out.println("Player 2 wins");
				break;
			}
			}
			catch(IllegalOperationException ex){
				System.out.println(ex.getMessage());
				break;
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.out.println("Choose Player 1 difficulty:");
		System.out.println("1 - Beginner");
		System.out.println("2 - Advanced");
		System.out.println("3 - Master");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int player1Choice = Integer.parseInt(br.readLine());
		PlayerFactory factory = new PlayerFactory();
		Player player1 = factory.getPlayer(player1Choice,null,'X');
		
		System.out.println("Choose Player 2 difficulty:");
		System.out.println("1 - Beginner");
		System.out.println("2 - Advanced");
		System.out.println("3 - Master");
		System.out.println("4 - Human");
		int player2Choice = Integer.parseInt(br.readLine());
		Player player2 = factory.getPlayer(player2Choice,player1,'O');
		
		Game game = new Game(player1,player2);
		game.start();
	}

}
