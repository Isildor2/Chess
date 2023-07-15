package moving_library;

import java.util.ArrayList;

public class Knight extends Piece{
	ArrayList<Move> knight_moves = new ArrayList<Move>();
	private final int[] knight_offsets= {-6,10,-15,17,15,6,-10,-17};
	
	public ArrayList<Move> generate_moves(Board board, int square) {
		knight_moves.clear();
		for (int i=0;i<8;i++) {
			if (square+knight_offsets[i]>=0&square+knight_offsets[i]<=63) {	
				if(piece_of_color(board,square,square+knight_offsets[i])==false
				&knightl(square,square+knight_offsets[i])==true) {
					knight_moves.add(new Move(square, square+knight_offsets[i],board.getSquare(square),-1,-1,is_capture(square+knight_offsets[i],board)));
				}
			}
		}
		return knight_moves;
	}
}