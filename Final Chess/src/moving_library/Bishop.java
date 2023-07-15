package moving_library;

import java.util.ArrayList;

public class Bishop extends Piece{
	private ArrayList<Move> bishop_moves = new ArrayList<Move>();
	private final int[] bishop_offsets= {7,9,-7,-9};
	
	public ArrayList<Move> generate_moves(Board board, int square) {
		bishop_moves.clear();
		for (int i=0;i<bishop_offsets.length;i++) {
			for (int j=1;(square+bishop_offsets[i]*j)<64&(square+bishop_offsets[i]*j)>-1&j<8;j++) {
				if (piece_of_color(board,square,square+bishop_offsets[i]*j)==false&samediagonal(square,square+bishop_offsets[i]*j)==true) {
					if (board.getSquare(square+bishop_offsets[i]*j)!=0) {
						bishop_moves.add(new Move(square, square+bishop_offsets[i]*j,board.getSquare(square), -1,-1,is_capture(square+bishop_offsets[i],board)));
						break;
					} else {
						bishop_moves.add(new Move(square, square+bishop_offsets[i]*j,board.getSquare(square), -1,-1,is_capture(square+bishop_offsets[i],board)));
					}
				} else if (piece_of_color(board,square,square+bishop_offsets[i]*j)==true) {
					break;
				}
			}
		}
		return bishop_moves;
	}
}