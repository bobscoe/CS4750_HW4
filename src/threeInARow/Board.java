package threeInARow;

public class Board {

	public char[][] board = null;

	public Board() {
		initializeBoard();
	}

	public void initializeBoard() {
		this.board = new char[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				this.board[i][j] = '-';
	}

	public boolean playerHasTwoInARow(Player player) {
		char mark = player.getMark();
		String[] successful_arrangements = { 
				new String(new char[] { '-', mark, mark }),
				new String(new char[] { mark, '-', mark }), 
				new String(new char[] { mark, mark, '-' }) 
				};
		
		if(hasImmediateWinningArrangementInRows(successful_arrangements))
			return true;
		if(hasImmediateWinningArrangementInColumns(successful_arrangements))
			return true;
		if(hasImmediateWinningArrangementInDiagonals(successful_arrangements))
			return true;
		return false;
	}
	
	private boolean hasImmediateWinningArrangementInRows(String [] successful_arrangements){
		for (int i = 0; i < getHeight(); i++) {
			char[] rowState = new char[getWidth()];
			for (int j = 0; j < getWidth(); j++) {
				rowState[j] = board[i][j];
			}
			String row = new String(rowState);
			if(hasAnySuccessfulArrangement(row, successful_arrangements))
				return true;
		}
		return false;
	}
	
	private boolean hasImmediateWinningArrangementInColumns(String [] successful_arrangements){
		for (int i = 0; i < getWidth(); i++) {
			char[] rowState = new char[getHeight()];
			for (int j = 0; j < getHeight(); j++) {
				rowState[j] = board[j][i];
			}
			String row = new String(rowState);
			if(hasAnySuccessfulArrangement(row, successful_arrangements))
				return true;
		}
		return false;
	}
	private boolean hasImmediateWinningArrangementInDiagonals(String [] successful_arrangements){
		char[] rowState = new char[getHeight()];
		for (int i = 0, j = 0; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[i] = board[i][j];
		}
		String row = new String(rowState);
		if(hasAnySuccessfulArrangement(row, successful_arrangements))
			return true;

		for (int i = getHeight() - 1, j = 0; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if(hasAnySuccessfulArrangement(row, successful_arrangements))
			return true;
		rowState = new char[getHeight()-1];
		for (int i = 1, j = 0; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if(hasAnySuccessfulArrangement(row, successful_arrangements))
			return true;
		
		for (int i = 0, j = 1; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[i] = board[i][j];
		}
		row = new String(rowState);
		if(hasAnySuccessfulArrangement(row, successful_arrangements))
			return true;
		
		for (int i = getHeight() - 2, j = 0; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if(hasAnySuccessfulArrangement(row, successful_arrangements))
			return true;
		
		for (int i = getHeight() - 1, j = 1; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j-1] = board[i][j];
		}
		row = new String(rowState);
		return hasAnySuccessfulArrangement(row, successful_arrangements);
	}
	
	private boolean hasAnySuccessfulArrangement(String boardArrangement,String [] successfulArrangements){
		for (int i = 0; i < successfulArrangements.length; i++) {
			if (boardArrangement.contains(successfulArrangements[i]))
				return true;
		}
		return false;
	}

	public int getWidth() {
		return 4;
	}

	public int getHeight() {
		return 4;
	}

}
