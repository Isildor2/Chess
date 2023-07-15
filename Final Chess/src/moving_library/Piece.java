package moving_library;

public class Piece {
	boolean piece_of_color(Board board, int square, int square_to_compare_to) {
		if (board.getSquare(square)>0&board.getSquare(square_to_compare_to)>0) {
			return true;
		} else if (board.getSquare(square)<0&board.getSquare(square_to_compare_to)<0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean is_capture(int square, Board board) {
		if (board.getSquare(square)!=0) {
			return true;
		}
		return false;
	}
	protected boolean knightl(int square, int targetsquare) {
		if (Math.abs((square/8)-targetsquare/8)==2&Math.abs((square%8)-targetsquare%8)==1) {
			return true;
		} else if (Math.abs((square/8)-targetsquare/8)==1&Math.abs((square%8)-targetsquare%8)==2) {
			return true;
		}
		return false;
	}
	protected boolean sameline(int first_square, int second_square) {
		if ((first_square/8==second_square/8)||(first_square%8==second_square%8)) {
			return true;
		} else 
		return false;
	}
	protected boolean samediagonal(int first_square, int second_square) {
		if (Math.abs((first_square/8)-(second_square/8))==Math.abs((first_square%8)-(second_square%8))) {
			return true;
		}
		return false;
	}
}