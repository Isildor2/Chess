package moving_library;

import java.util.ArrayList;

public class Check_Checker extends Piece{
	private final int[] diagonal_offsets= {7,9,-7,-9};
	private final int[] knight_offsets= {-6,10,-15,17,15,6,-10,-17};
	private final int[] straight_offsets = {1,-1,8,-8};
	int[] check_testing_board=new int[64];
	int color_modifier=1;
	int kings_square=-1;
	
	public void checkAll(ArrayList<Move> pseudo_legal_moves,Board board) {
		if (board.isWhite_turn()==true) {
			//-1*-25==25, which is the white king
			//1*-25 finds the black king
			color_modifier=-1;
		} else {
			color_modifier=1;
		}
		int i=0;
		//loop over all moves
		while (i<pseudo_legal_moves.size()) {
			//set test board to current position and execute move to test
			set_testboard_to_current_position(board);
			execute_test_move(pseudo_legal_moves.get(i),board);
			//locate king of whose side it is to move
			for (int j=0;j<64;j++) {
				if (check_testing_board[j]==-25*color_modifier) {
					kings_square=j;
					break;
				}
			}
			//check all different attacks
			if (knight_attack(this.kings_square, color_modifier)==false
			&diagonal_attack(this.kings_square, color_modifier, board.isWhite_turn())==false
			&straight_attack(this.kings_square, color_modifier)==false) {
				i++;
			} else {
				pseudo_legal_moves.remove(i);
			}
		}
	}
	//rook and queen attack on testing board
	protected boolean straight_attack(int kings_square,int c) {
		for (int i=0;i<straight_offsets.length;i++) {
			for (int j=1;(kings_square+straight_offsets[i]*j)<64&(kings_square+straight_offsets[i]*j)>-1&j<8;j++) {
				if (piece_of_color(check_testing_board,kings_square,kings_square+straight_offsets[i]*j)==false&sameline(kings_square,kings_square+straight_offsets[i]*j)==true) {
					if (check_testing_board[kings_square+straight_offsets[i]*j]==5*c
					||check_testing_board[kings_square+straight_offsets[i]*j]==9*c) {
						return true;
					}
				} else if (piece_of_color(check_testing_board,kings_square,kings_square+straight_offsets[i]*j)==true) {
					break;
				}
			}
		}
		return false;
	}
	//rook and queen attack, with Board parameter
		protected boolean straight_attack(int kings_square,Board board,int c) {
			for (int i=0;i<straight_offsets.length;i++) {
				for (int j=1;(kings_square+straight_offsets[i]*j)<64&(kings_square+straight_offsets[i]*j)>-1&j<8;j++) {
					if (piece_of_color(board,kings_square,kings_square+straight_offsets[i]*j)==false&sameline(kings_square,kings_square+straight_offsets[i]*j)==true) {
						if (board.getSquare(kings_square+straight_offsets[i]*j)==5*c
						||board.getSquare(kings_square+straight_offsets[i]*j)==9*c) {
							return true;
						}
					} else if (piece_of_color(board,kings_square,kings_square+straight_offsets[i]*j)==true) {
						break;
					}
				}
			}
			return false;
		}
	//bishop, queen and pawn attack with testing board
	/*
	 * checks possible pawn attacks, aswell as queen and bishop attacks
	 */
	protected boolean diagonal_attack(int kings_square,int c, boolean iswhiteturn) {
		if (iswhiteturn==true) {
			if (kings_square+9<=63) {
				if (check_testing_board[kings_square+9]==-1&Edges.onrightedge(kings_square)==false) {
					return true;
				}
				if (check_testing_board[kings_square+7]==-1&Edges.onleftedge(kings_square)==false) {
					return true;
				}
			}
		} else {
			if (kings_square-9>=0) {
				if (check_testing_board[kings_square-9]==1&Edges.onleftedge(kings_square)==false) {
					return true;
				}
				if (check_testing_board[kings_square-7]==1&Edges.onleftedge(kings_square)==false) {
					return true;
				}
			}
		}
		for (int i=0;i<diagonal_offsets.length;i++) {
			for (int j=1;(kings_square+diagonal_offsets[i]*j)<64&(kings_square+diagonal_offsets[i]*j)>-1&j<8;j++) {
				if (piece_of_color(check_testing_board,kings_square,kings_square+diagonal_offsets[i]*j)==false&samediagonal(kings_square,kings_square+diagonal_offsets[i]*j)==true) {
					if (check_testing_board[kings_square+diagonal_offsets[i]*j]==3*c
					||check_testing_board[kings_square+diagonal_offsets[i]*j]==9*c) {
						return true;
					}
				} else if (piece_of_color(check_testing_board,kings_square,kings_square+diagonal_offsets[i]*j)==true) {
					break;
				}
			}
		}
		return false;
	}
	//bishop, queen and pawn attack with testing board
		protected boolean diagonal_attack(int kings_square,Board board, int c) {
			if (board.isWhite_turn()==true) {
				if (check_testing_board[kings_square+9]==-1&Edges.onrightedge(kings_square)==false) {
					return true;
				}
				if (board.getSquare(kings_square+7)==-1&Edges.onleftedge(kings_square)==false) {
					return true;
				}
			} else {
				if (board.getSquare(kings_square-9)==1&Edges.onleftedge(kings_square)==false) {
					return true;
				}
				if (board.getSquare(kings_square-7)==1&Edges.onleftedge(kings_square)==false) {
					return true;
				}
			}
			for (int i=0;i<diagonal_offsets.length;i++) {
				for (int j=1;(kings_square+diagonal_offsets[i]*j)<64&(kings_square+diagonal_offsets[i]*j)>-1&j<8;j++) {
					if (piece_of_color(board,kings_square,kings_square+diagonal_offsets[i]*j)==false&samediagonal(kings_square,kings_square+diagonal_offsets[i]*j)==true) {
						if (check_testing_board[kings_square+diagonal_offsets[i]*j]==3*c
						||check_testing_board[kings_square+diagonal_offsets[i]*j]==9*c) {
							return true;
						}
					} else if (piece_of_color(board,kings_square,kings_square+diagonal_offsets[i]*j)==true) {
						break;
					}
				}
			}
			return false;
		}
	//knight attack
	protected boolean knight_attack(int kings_square,int c) {
		for (int i=0;i<8;i++) {
			if (kings_square+knight_offsets[i]>=0&kings_square+knight_offsets[i]<=63) {	
				if(kings_square+knight_offsets[i]==2*c
				&knightl(kings_square,kings_square+knight_offsets[i])==true) {
					return true;
				}
			}
		}
		return false;
	}
	//execute the move for attack testing
	/*
	 * the board is the actual game board and is used to identify if the current positon
	 * (on the board) possesses all criteria for en passant, which would otherwise require
	 * a new instance of Board for this class
	 * 
	 * It is never modified in this function
	 */
	public void execute_test_move(Move move,Board board) {
		if (move.piece_type()==25&move.start_square()==4&move.target_square()==2) {
			check_testing_board[move.target_square()]=move.piece_type();
			check_testing_board[0]=0;
			check_testing_board[3]=5;
		} else if (move.piece_type()==25&move.start_square()==4&move.target_square()==6) {
			check_testing_board[move.target_square()]=move.piece_type();
			check_testing_board[7]=0;
			check_testing_board[5]=5;
		} else if (move.piece_type()==-25&move.start_square()==60&move.target_square()==58) {
			check_testing_board[move.target_square()]=move.piece_type();
			check_testing_board[56]=0;
			check_testing_board[59]=-5;
		} else if (move.piece_type()==25&move.start_square()==60&move.target_square()==62) {
			check_testing_board[move.target_square()]=move.piece_type();
			check_testing_board[63]=0;
			check_testing_board[61]=-5;
		} else if (testmove_is_enpa(move,board)==true) {
			check_testing_board[move.target_square()]=move.piece_type();
			check_testing_board[board.getEn_passant_position()]=0;
		} else {
			check_testing_board[move.target_square()]=move.piece_type();
		}
		check_testing_board[move.start_square()]=0;
	}
	//en passaent attack
	/*
	 * the board is the actual game board and is used to identify if the current positon
	 * (on the board) possesses all criteria for en passant, which would otherwise require
	 * a new instance of Board for this class
	 * 
	 * It is never modified in this function
	 */
	private boolean testmove_is_enpa(Move move,Board board) {
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
	//set testboard to the position your in
	private void set_testboard_to_current_position(Board board) {
		for (int i=0;i<64;i++) {
			check_testing_board[i]=board.getSquare(i);
		}
	}
}