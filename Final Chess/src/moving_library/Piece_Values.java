package moving_library;

public enum Piece_Values {
	PAWN(1),
	KNIGHT(3),
	BISHOP(3),
	ROOK(5),
	QUEEN(9),
	KING(25);
	
	final int value;
	Piece_Values (int value) {
		this.value = value;
	}
	public int piece_value() {
		return this.value;
	}
}