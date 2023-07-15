package moving_library;

import java.util.ArrayList;

public class Rook extends Piece{
	private ArrayList<Move> rook_moves = new ArrayList<Move>();
	private final int[] rook_offsets = {1,-1,8,-8};

	public ArrayList<Move> generate_moves(Board board, int square) {
		rook_moves.clear();
		for (int i=0;i<rook_offsets.length;i++) {
			for (int j=1;(square+rook_offsets[i]*j)<64&(square+rook_offsets[i]*j)>-1&j<8;j++) {
				if (piece_of_color(board,square,square+rook_offsets[i]*j)==false&sameline(square,square+rook_offsets[i]*j)==true) {
					if (board.getSquare(square+rook_offsets[i]*j)!=0) {
						rook_moves.add(new Move(square,square+rook_offsets[i]*j,board.getSquare(square),-1,-1,is_capture(square+rook_offsets[i],board)));
						break;
					} else {
						rook_moves.add(new Move(square,square+rook_offsets[i]*j,board.getSquare(square),-1,-1,is_capture(square+rook_offsets[i],board)));
					}
				} else if (piece_of_color(board,square,square+rook_offsets[i]*j)==true) {
					break;
				}
			}
		}
		return rook_moves;
	}
}