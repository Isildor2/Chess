package moving_library;

import java.util.ArrayList;

public class Board {
	private int[] chess_board = {5,2,3,9,25,3,2,5,
								1,1,1,1,1,1,1,1,
								0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,
								0,0,0,0,0,0,0,0,
								-1,-1,-1,-1,-1,-1,-1,-1,
								-5,-2,-3,-9,-25,-3,-2,-5};
	private boolean white_castle_kingside=true;
	private boolean white_castle_queenside=true;
	private boolean black_castle_kingside=true;
	private boolean black_castle_queenside=true;
	private boolean white_turn=true;
	private boolean promotion_to_complete=false;
	private int promotion_square=-1;
	private int en_passant_position=-1;
	private ArrayList<Move> moves = new ArrayList<Move>();

	public int getSquare(int square) {
		if (square<64&square>-1) {
			return this.chess_board[square];
		} else {
			return 99;
		}
	}
	public void setSquare(int square, int piece) {
		if (square<64&square>-1) {
			this.chess_board[square]=piece;
		}
	}
	public void white_moves_king() {
		this.white_castle_kingside=false;
		this.white_castle_queenside=false;
	}
	public void black_moves_king() {
		this.black_castle_kingside=false;
		this.black_castle_queenside=false;
	}
	public boolean canWhite_castle_kingside() {
		return this.white_castle_kingside;
	}
	public void setWhite_castle_kingside(boolean white_castle_kingside) {
		this.white_castle_kingside = white_castle_kingside;
	}
	public boolean canWhite_castle_queenside() {
		return this.white_castle_queenside;
	}
	public void setWhite_castle_queenside(boolean white_castle_queenside) {
		this.white_castle_queenside = white_castle_queenside;
	}
	public boolean canBlack_castle_kingside() {
		return this.black_castle_kingside;
	}
	public void setBlack_castle_kingside(boolean black_castle_kingside) {
		this.black_castle_kingside = black_castle_kingside;
	}
	public boolean canBlack_castle_queenside() {
		return this.black_castle_queenside;
	}
	public void setBlack_castle_queenside(boolean black_castle_queenside) {
		this.black_castle_queenside = black_castle_queenside;
	}
	public boolean isWhite_turn() {
		return this.white_turn;
	}
	public void setWhite_turn(boolean white_turn) {
		this.white_turn = white_turn;
	}
	public boolean isPromotion_possible() {
		return promotion_to_complete;
	}
	public void setPromotion_possible(boolean promotion_possible) {
		this.promotion_to_complete = promotion_possible;
	}
	public void setPromotion_Square(int square) {
		if (square!=-1) {
			this.promotion_square=square;
		}
	}
	public int getPromotion_Square() {
		return this.promotion_square;
	}
	public void setEn_passant_position(int new_en_passant_position) {
		if (new_en_passant_position!=-1) {
			this.en_passant_position = new_en_passant_position;
		}
	}
	public int getEn_passant_position() {
		return en_passant_position;
	}
	public void addLegalMoves(ArrayList<Move> new_moves) {
		this.moves.addAll(new_moves);
	}
	public Move getMove(int index) {
		return this.moves.get(index);
	}
	public int amount_of_legal_moves() {
		return this.moves.size();
	}
	public void clear_moves() {
		moves.clear();
	}
	//resetting
	public void reset_board_to_chess_default() {
		for (int i=0;i<64;i++) {
			chess_board[i]=Default_Board.getChessDefaultSquare(i);
		}
		white_castle_kingside=Default_Board.canWhite_castle_kingside();
		white_castle_queenside=Default_Board.canWhite_castle_queenside();
		black_castle_kingside=Default_Board.canBlack_castle_kingside();
		black_castle_queenside=Default_Board.canBlack_castle_queenside();
		en_passant_position=Default_Board.getEn_passant_position();
		promotion_square=Default_Board.getPromotion_square();
		promotion_to_complete=Default_Board.isPromotion_to_complete();
		moves.clear();
		white_turn=Default_Board.getWhite_turn();
	}
	public void clear_board() {
		for (int i=0;i<64;i++) {
			chess_board[i]=0;
		}
		moves.clear();
	}
}