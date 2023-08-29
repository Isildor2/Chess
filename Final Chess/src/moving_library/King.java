package moving_library;

import java.util.ArrayList;

public class King extends Piece{
	private ArrayList<Move> king_moves = new ArrayList<Move>();
	private final int[] king_offsets= {-7,9,1,8,-8,-1,7,-9};

	public ArrayList<Move> generate_moves(Board board, int square) {
		king_moves.clear();
		for (int i=0;i<8;i++) {
			if ((square+king_offsets[i])>-1&(square+king_offsets[i])<64) {
				if (is_adjacent_square(square, square+king_offsets[i])==true&piece_of_color(board,square,square+king_offsets[i])==false&king_on_adjacent_square(square+king_offsets[i],board)==false) {
					king_moves.add(new Move(square,square+king_offsets[i],board.getSquare(square),-1,-1,is_capture(square+king_offsets[i],board)));
				}
			}
		}
		if (board.isWhite_turn()==true) {
			if (board.canWhite_castle_kingside()==true&board.getSquare(5)==0&board.getSquare(6)==0&board.getSquare(7)==5) {
				king_moves.add(new Move(4,6,Piece_Codes.WHITE_KING.get_piece_code(),-1,-1,false));
			}
			if (board.canWhite_castle_queenside()==true&board.getSquare(3)==0&board.getSquare(2)==0&board.getSquare(1)==0&board.getSquare(0)==5) {
				king_moves.add(new Move(4,2,Piece_Codes.WHITE_KING.get_piece_code(),-1,-1,false));
			}
		} else {
			if (board.canBlack_castle_kingside()==true&board.getSquare(61)==0&board.getSquare(62)==0&board.getSquare(63)==-5) {
				king_moves.add(new Move(60,62,Piece_Codes.BLACK_KING.get_piece_code(),-1,-1,false));
			}
			if (board.canBlack_castle_queenside()==true&board.getSquare(59)==0&board.getSquare(58)==0&board.getSquare(57)==0&board.getSquare(56)==-5) {
				king_moves.add(new Move(60,58,Piece_Codes.BLACK_KING.get_piece_code(),-1,-1,false));
			}
		}
		return king_moves;
	}
	private boolean is_adjacent_square(int square, int othersquare) {
		if (Edges.onrightedge(square)) {
			if (othersquare==square+9||othersquare==square+1||othersquare==square-7) {
				return false;
			}
		} else if (Edges.onleftedge(square)) {
			if (othersquare==square-1||othersquare==square-9||othersquare==square+7) {
				return false;
			}
		}
 		return true;
	}
	private boolean king_on_adjacent_square(int move_square, Board board) {
		for (int j=0;j<8;j++) {
			if (board.isWhite_turn()) {
				if (is_adjacent_square(move_square,move_square+king_offsets[j])==true&board.getSquare(move_square+king_offsets[j])==-25) {
					return true;
				}
			} else {
				if (is_adjacent_square(move_square,move_square+king_offsets[j])==true&board.getSquare(move_square+king_offsets[j])==25) {
					return true;
				}
			}	
		}
		return false;
	}
}