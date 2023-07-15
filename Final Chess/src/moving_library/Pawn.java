package moving_library;

import java.util.ArrayList;

public class Pawn extends Piece{
	private ArrayList<Move> pawn_moves = new ArrayList<Move>();

	public ArrayList<Move> generate_moves(Board board, int square) {
		pawn_moves.clear();
		if (board.isWhite_turn()==true) {
			if (board.getSquare(square+8)==0) {
				if (square+8<=55) {
					pawn_moves.add(new Move(square, square+8,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,false));
				} else {
					generate_promos(square,square+8,board);
				}
				if (square<16&board.getSquare(square+16)==0) {
					pawn_moves.add(new Move(square,square+16,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,square+16,false));
				}
			}
			if ((board.getSquare(square+9)<0)&Edges.onrightedge(square)==false) {
				if (square+9<=55) {
					pawn_moves.add(new Move(square, square+9,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,is_capture(square+9,board)));
				} else {
					generate_promos(square,square+9,board);
				}
			}
			if ((board.getSquare(square+7)<0)&Edges.onleftedge(square)==false) {
				if (square+7<=55) {
					pawn_moves.add(new Move(square, square+7,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,is_capture(square+7,board)));
				} else {
					generate_promos(square,square+7,board);
				}
			}
		} else {
			if (board.getSquare(square-8)==0) {
				if (square-8>=8) {
					pawn_moves.add(new Move(square, square-8,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,false));
				} else {
					generate_promos(square,square-8,board);
				}
				if (square>47&board.getSquare(square-16)==0) {
					pawn_moves.add(new Move(square,square-16,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,square-16,false));
				}
			}
			if ((board.getSquare(square-9)>0)&Edges.onleftedge(square)==false) {
				if (square-9>=8) {
					pawn_moves.add(new Move(square, square-9,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,is_capture(square-9,board)));
				} else {
					generate_promos(square,square-9,board);
				}
			}
			if ((board.getSquare(square-7)>0)&Edges.onrightedge(square)==false) {
				if (square-7>=8) {
					pawn_moves.add(new Move(square, square-7,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,is_capture(square-7,board)));
				} else {
					generate_promos(square,square-7,board);
				}
			}
		}
		generate_en_passeant(board,square);
		return pawn_moves;
	}
	private void generate_en_passeant(Board board, int square) {
		if (adjacent_enpa_square(board,square)==true) {
			if (Edges.onrightedge(square)==true) {
				if (board.isWhite_turn()==true) {
					pawn_moves.add(new Move(square,square+7,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,false));
				} else {
					pawn_moves.add(new Move(square,square-9,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,false));
				}
			} else if (Edges.onleftedge(square)==true) {
				if (board.isWhite_turn()==true) {
					pawn_moves.add(new Move(square,square+9,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,false));
				} else {
					pawn_moves.add(new Move(square,square-7,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,false));
				}
			} else {
				if (board.isWhite_turn()==true) {
					if (square+1==board.getEn_passant_position()) {
						pawn_moves.add(new Move(square,square+9,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,false));
					} else {
						pawn_moves.add(new Move(square,square+7,Piece_Codes.WHITE_PAWN.get_piece_code(),-1,-1,false));
					}
				} else {
					if (square-1==board.getEn_passant_position()) {
						pawn_moves.add(new Move(square,square-9,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,false));
					} else {
						pawn_moves.add(new Move(square,square-7,Piece_Codes.BLACK_PAWN.get_piece_code(),-1,-1,false));
					}
				}
			}
		}
	}
	private boolean adjacent_enpa_square(Board board, int square) {
		if (board.getEn_passant_position()+1==square&Edges.onleftedge(square)==true) {
			return false;
		} else if (board.getEn_passant_position()-1==square&Edges.onrightedge(square)==true) {
			return false;
		} else if (board.getEn_passant_position()-1==square||board.getEn_passant_position()+1==square) {
			return true;
		}
		return false;
	}
	private void generate_promos(int firstsquare, int targetsquare, Board board) {
		int color=1;
		if (board.isWhite_turn()==false) {
			color=-1;
		}
		pawn_moves.add(new Move(firstsquare, targetsquare,board.getSquare(firstsquare),Piece_Codes.WHITE_KNIGHT.get_piece_code()*color,-1,is_capture(targetsquare,board)));
		pawn_moves.add(new Move(firstsquare, targetsquare,board.getSquare(firstsquare),Piece_Codes.WHITE_BISHOP.get_piece_code()*color,-1,is_capture(targetsquare,board)));
		pawn_moves.add(new Move(firstsquare, targetsquare,board.getSquare(firstsquare),Piece_Codes.WHITE_ROOK.get_piece_code()*color,-1,is_capture(targetsquare,board)));
		pawn_moves.add(new Move(firstsquare, targetsquare,board.getSquare(firstsquare),Piece_Codes.WHITE_QUEEN.get_piece_code()*color,-1,is_capture(targetsquare,board)));
	}
}