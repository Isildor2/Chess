package moving_library;

public enum Piece_Codes {
	WHITE_PAWN(1),
	BLACK_PAWN(-1),
	WHITE_KNIGHT(2),
	BLACK_KNIGHT(-2),
	WHITE_BISHOP(3),
	BLACK_BISHOP(-3),
	WHITE_ROOK(5),
	BLACK_ROOK(-5),
	WHITE_QUEEN(9),
	BLACK_QUEEN(-9),
	WHITE_KING(25),
	BLACK_KING(-25),
	EMPTY(0);
	
	final int piececode;
	Piece_Codes(int piececode) {
		this.piececode=piececode;
	}
	public int get_piece_code() {
		return this.piececode;
	}
}