package moving_library;

import java.util.ArrayList;

public class Board {
	public Board() {}
	public Board(Board board) {
		
	}
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
	private int en_passant_position=-1;
	private boolean unfinished_promotion=false;
	private int open_promotion_square=-1;
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
	public void setEn_passant_position(int new_en_passant_position) {
		this.en_passant_position = new_en_passant_position;
	}
	public int getEn_passant_position() {
		return this.en_passant_position;
	}
	public boolean isUnfinished_promotion() {
		return this.unfinished_promotion;
	}
	public void setUnfinished_promotion(boolean unfinished_promotion) {
		this.unfinished_promotion = unfinished_promotion;
	}
	public int getOpen_promotion_square() {
		return this.open_promotion_square;
	}
	public void setOpen_promotion_square(int open_promotion_square) {
		this.open_promotion_square = open_promotion_square;
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
	public int[] get_Board() {
		return this.chess_board;
	}
	//resetting
	public void clone_other_board(Board other_board) {
		for (int i=0;i<64;i++) {
			chess_board[i]=other_board.getSquare(i);
		}
		white_castle_kingside=other_board.canWhite_castle_kingside();
		white_castle_queenside=other_board.canWhite_castle_queenside();
		black_castle_kingside=other_board.canBlack_castle_kingside();
		black_castle_queenside=other_board.canBlack_castle_queenside();
		en_passant_position=other_board.getEn_passant_position();
		white_turn=other_board.isWhite_turn();
		for (int i=0;i<other_board.amount_of_legal_moves();i++) {
			this.moves.add(other_board.getMove(i));
		}
	}
	public void reset_board_to_chess_default() {
		for (int i=0;i<64;i++) {
			chess_board[i]=Default_Board.getChessDefaultSquare(i);
		}
		white_castle_kingside=Default_Board.canWhite_castle_kingside();
		white_castle_queenside=Default_Board.canWhite_castle_queenside();
		black_castle_kingside=Default_Board.canBlack_castle_kingside();
		black_castle_queenside=Default_Board.canBlack_castle_queenside();
		en_passant_position=Default_Board.getEn_passant_position();
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