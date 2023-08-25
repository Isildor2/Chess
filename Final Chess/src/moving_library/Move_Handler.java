package moving_library;

import java.util.ArrayList;

public class Move_Handler {
	Board board=new Board();
	Pawn pawn=new Pawn();
	Knight knight=new Knight();
	Bishop bishop=new Bishop();
	Rook rook=new Rook();
	Queen queen=new Queen();
	King king=new King();
	Check_Checker check=new Check_Checker();
	Game_End_Identifier end=new Game_End_Identifier();
	ArrayList<Move> pseudo_legal_moves = new ArrayList<Move>();

	public void generate_all_legal_moves(Board board) {
		int square;
		for (square=0;square<64;square++) {
			if ((board.getSquare(square)<1&board.isWhite_turn()==true)||(board.getSquare(square)>-1&board.isWhite_turn()==false)) {
				continue;
			} else {
				if (board.isWhite_turn()==true) {
					switch(board.getSquare(square)) {
					case 1:
						pseudo_legal_moves.addAll(pawn.generate_moves(board, square));
						break;
					case 2:
						pseudo_legal_moves.addAll(knight.generate_moves(board, square));
						break;
					case 3:
						pseudo_legal_moves.addAll(bishop.generate_moves(board, square));
						break;
					case 5:
						pseudo_legal_moves.addAll(rook.generate_moves(board, square));
						break;
					case 9:
						pseudo_legal_moves.addAll(queen.generate_moves(board, rook, bishop, square));
						break;
					case 25:
						pseudo_legal_moves.addAll(king.generate_moves(board, square));
					}
				} else {
					switch(board.getSquare(square)) {
					case -1:
						pseudo_legal_moves.addAll(pawn.generate_moves(board, square));
						break;
					case -2:
						pseudo_legal_moves.addAll(knight.generate_moves(board, square));
						break;
					case -3:
						pseudo_legal_moves.addAll(bishop.generate_moves(board, square));
						break;
					case -5:
						pseudo_legal_moves.addAll(rook.generate_moves(board, square));
						break;
					case -9:
						pseudo_legal_moves.addAll(queen.generate_moves(board, rook, bishop, square));
						break;
					case -25:
						pseudo_legal_moves.addAll(king.generate_moves(board, square));
					}
				}
			}
		}
		check.checkAll(pseudo_legal_moves,board);
		board.addLegalMoves(pseudo_legal_moves);
		end.game_has_ended(board);
		pseudo_legal_moves.clear();
	}
	public void execute_move(Board board, Move move) {
		switch (move.piece_type()) {
		case 25:
			board.white_moves_king();
			break;
		case -25:
			board.black_moves_king();
			break;
		case 5:
			if (move.start_square()==0) {
				board.setWhite_castle_queenside(false);
			} else if (move.start_square()==7) {
				board.setWhite_castle_kingside(false);
			}
			break;
		case -5:
			if (move.start_square()==56) {
				board.setBlack_castle_queenside(false);
			} else if (move.start_square()==63) {
				board.setBlack_castle_kingside(false);
			}
		}
		if (move.piece_type()==25&move.start_square()==4&move.target_square()==2) {
			board.setSquare(move.target_square(),move.piece_type());
			board.setSquare(0, 0);
			board.setSquare(3, 5);
		} else if (move.piece_type()==25&move.start_square()==4&move.target_square()==6) {
			board.setSquare(move.target_square(),move.piece_type());
			board.setSquare(7, 0);
			board.setSquare(5, 5);
		} else if (move.piece_type()==-25&move.start_square()==60&move.target_square()==58) {
			board.setSquare(move.target_square(),move.piece_type());
			board.setSquare(56, 0);
			board.setSquare(59, -5);
		} else if (move.piece_type()==25&move.start_square()==60&move.target_square()==62) {
			board.setSquare(move.target_square(),move.piece_type());
			board.setSquare(63, 0);
			board.setSquare(61, -5);
		} else if (move_is_enpa(move,board)==true) {
			board.setSquare(move.target_square(),move.piece_type());
			board.setSquare(board.getEn_passant_position(), 0);
		} else {
			board.setSquare(move.target_square(), move.piece_type());
		}
		board.setSquare(move.start_square(), 0);
		board.setEn_passant_position(move.en_passant_square());
		board.setWhite_turn(!board.isWhite_turn());
	}
	private boolean move_is_enpa(Move move,Board board) {
		if (move.piece_type()!=1&move.piece_type()!=-1) {
			return false;
		}
		if (board.getEn_passant_position()+1==move.start_square()&Edges.onleftedge(move.start_square())==true) {
			return false;
		} else if (board.getEn_passant_position()-1==move.start_square()&Edges.onrightedge(move.start_square())==true) {
			return false;
		} else if (board.getEn_passant_position()-1==move.start_square()||board.getEn_passant_position()+1==move.start_square()) {
			if (board.isWhite_turn()==true) {
				if (move.target_square()==board.getEn_passant_position()+8) {
					return true;
				}
			} else {
				if (move.target_square()==board.getEn_passant_position()-8) {
					return true;
				}
			}
		}
		return false;
	}
	public void move(int startsquare, int targetsquare) {
		board.clear_moves();
		generate_all_legal_moves(board);
		for (int i=0;i<board.amount_of_legal_moves();i++) {
			if (board.getMove(i).start_square()==startsquare&&board.getMove(i).target_square()==targetsquare) {
				execute_move(board, board.getMove(i));
				startsquare=-1;
				targetsquare=-1;
				if (board.getMove(i).promotion_type()!=-1) {
					board.setUnfinished_promotion(true);
					board.setOpen_promotion_square(board.getMove(i).target_square());
				}
				board.clear_moves();
				break;
			}
		}
	}
	public void promote(int promoted_piece) {
		if (board.isWhite_turn()==true) {
			promoted_piece=promoted_piece*-1;
		}
		board.setSquare(board.getOpen_promotion_square(),promoted_piece);
		board.setUnfinished_promotion(false);
		board.setOpen_promotion_square(-1);
	}
	public boolean game_is_over(Board board) {
		return end.game_has_ended(board);
	}
	public Board get_board() {
		return this.board;
	}
}