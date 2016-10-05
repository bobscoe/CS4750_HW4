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
	
	public Move getWinningMove(Player player){
		char mark = player.getMark();
		Move move = null;
		String[] successful_arrangements = { new String(new char[] { '-', mark, mark }),
				new String(new char[] { mark, '-', mark }), new String(new char[] { mark, mark, '-' }) };
		if(playerHasTwoInARow(player)){
			move = getWinningMoveRows(player,successful_arrangements);
			if(move!=null)
				return move;
			move = getWinningMoveColumns(player,successful_arrangements);
			if(move!=null)
				return move;
			return getWinningMoveDiagonals(player,successful_arrangements);
		}
		return null;
	}
	
	private Move getWinningMoveDiagonals(Player player, String[] arrangements) {
		char[] rowState = new char[getHeight()];
		for (int i = 0, j = 0; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[i] = board[i][j];
		}
		String row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
			for(int i=0;i<arrangements.length;i++){
				if(row.contains(arrangements[i])){
					int index = row.indexOf(arrangements[i]);
					int offset = arrangements[i].indexOf('-');
					return new Move(index+offset,index+offset);
				}
			}

		for (int i = getHeight() - 1, j = 0; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
//			for(int i=0;i<arrangements.length;i++){
//				if(row.contains(arrangements[i])){
//					int index = row.indexOf(arrangements[i]);
//					int offset = arrangements[i].indexOf('-');
//					return new Move(getHeight() - 1 - offset,index+offset);
//				}
//			}
		rowState = new char[getHeight() - 1];
		for (int i = 1, j = 0; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
//			return true;

		for (int i = 0, j = 1; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[i] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
//			return true;

		for (int i = getHeight() - 2, j = 0; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
//			return true;

		for (int i = getHeight() - 1, j = 1; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j - 1] = board[i][j];
		}
		row = new String(rowState);
//		return hasAnySuccessfulArrangement(row, arrangements);
		return null;
	}

	private Move getWinningMoveColumns(Player player, String[] successful_arrangements) {
		for (int i = 0; i < getWidth(); i++) {
			char[] rowState = new char[getHeight()];
			for (int j = 0; j < getHeight(); j++) {
				rowState[j] = board[j][i];
			}
			String row = new String(rowState);
			for(int k =0;k<successful_arrangements.length;k++)
			{
				if(row.contains(successful_arrangements[k])){
					int index = row.indexOf(successful_arrangements[k]);
					int offset = successful_arrangements[k].indexOf('-');
					return new Move(index+offset,i);
				}
			}
		}
		return null;
	}

	public Move getWinningMoveRows(Player player,String [] successful_arrangements){
		for (int i = 0; i < getHeight(); i++) {
			char[] rowState = new char[getWidth()];
			for (int j = 0; j < getWidth(); j++) {
				rowState[j] = board[i][j];
			}
			String row = new String(rowState);
			for(int k =0;k<successful_arrangements.length;k++)
			{
				if(row.contains(successful_arrangements[k])){
					int index = row.indexOf(successful_arrangements[k]);
					int offset = successful_arrangements[k].indexOf('-');
					return new Move(i,index+offset);
				}
			}
		}
		return null;
	}

	public boolean playerHasTwoInARow(Player player) {
		char mark = player.getMark();
		String[] two_in_a_row_arrangements = { new String(new char[] { '-', mark, mark }),
				new String(new char[] { mark, '-', mark }), new String(new char[] { mark, mark, '-' }) };

		if (hasArrangementInRows(two_in_a_row_arrangements))
			return true;
		if (hasArrangementInColumns(two_in_a_row_arrangements))
			return true;
		return hasArrangementInDiagonals(two_in_a_row_arrangements);
	}

	private boolean hasArrangementInRows(String[] arrangements) {
		for (int i = 0; i < getHeight(); i++) {
			char[] rowState = new char[getWidth()];
			for (int j = 0; j < getWidth(); j++) {
				rowState[j] = board[i][j];
			}
			String row = new String(rowState);
			if (hasAnySuccessfulArrangement(row, arrangements))
				return true;
		}
		return false;
	}

	private boolean hasArrangementInColumns(String[] arrangements) {
		for (int i = 0; i < getWidth(); i++) {
			char[] rowState = new char[getHeight()];
			for (int j = 0; j < getHeight(); j++) {
				rowState[j] = board[j][i];
			}
			String row = new String(rowState);
			if (hasAnySuccessfulArrangement(row, arrangements))
				return true;
		}
		return false;
	}

	private boolean hasArrangementInDiagonals(String[] arrangements) {
		char[] rowState = new char[getHeight()];
		for (int i = 0, j = 0; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[i] = board[i][j];
		}
		String row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
			return true;

		for (int i = getHeight() - 1, j = 0; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
			return true;
		rowState = new char[getHeight() - 1];
		for (int i = 1, j = 0; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
			return true;

		for (int i = 0, j = 1; i < getHeight() && j < getWidth(); i++, j++) {
			rowState[i] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
			return true;

		for (int i = getHeight() - 2, j = 0; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j] = board[i][j];
		}
		row = new String(rowState);
		if (hasAnySuccessfulArrangement(row, arrangements))
			return true;

		for (int i = getHeight() - 1, j = 1; i >= 0 && j < getWidth(); i--, j++) {
			rowState[j - 1] = board[i][j];
		}
		row = new String(rowState);
		return hasAnySuccessfulArrangement(row, arrangements);
	}

	private boolean hasAnySuccessfulArrangement(String boardArrangement, String[] arrangements) {
		for (int i = 0; i < arrangements.length; i++) {
			if (boardArrangement.contains(arrangements[i]))
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

	public void markMove(Player player,Move move){
		board[move.Row][move.Column] = player.getMark();
	}

	public boolean isComplete(Player player) {
		char mark = player.getMark();
		String [] winning_arrangements = {
				new String(new char[]{mark,mark,mark})
		};
		if (hasArrangementInRows(winning_arrangements))
			return true;
		if (hasArrangementInColumns(winning_arrangements))
			return true;
		return hasArrangementInDiagonals(winning_arrangements);
	}
}
