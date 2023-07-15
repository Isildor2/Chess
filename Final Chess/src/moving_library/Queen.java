package moving_library;

import java.util.ArrayList;

public class Queen{
	private ArrayList<Move> queen_moves = new ArrayList<Move>();

	public ArrayList<Move> generate_moves(Board board, Rook rook, Bishop bishop, int square) {
		queen_moves.clear();
		queen_moves.addAll(rook.generate_moves(board, square));
		queen_moves.addAll(bishop.generate_moves(board, square));
		return queen_moves;
	}
}