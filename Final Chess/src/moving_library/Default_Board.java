package moving_library;

public class Default_Board {
	final private static int[] chessdefaultboard = {5,2,3,9,25,3,2,5,
												   1,1,1,1,1,1,1,1,
												   0,0,0,0,0,0,0,0,
												   0,0,0,0,0,0,0,0,
												   0,0,0,0,0,0,0,0,
												   0,0,0,0,0,0,0,0,
												   -1,-1,-1,-1,-1,-1,-1,-1,
												   -5,-2,-3,-9,-25,-3,-2,-5};
	final private static boolean white_castle_kingside=true;
	final private static boolean white_castle_queenside=true;
	final private static boolean black_castle_kingside=true;
	final private static boolean black_castle_queenside=true;
	final private static int en_passant_position=-1;
	final private static boolean white_turn=true;
	final private boolean unfinished_promotion=false;
	final private int open_promotion_square=-1;
	
	public static int getChessDefaultSquare(int square) {
		return chessdefaultboard[square];
	}
	public static boolean canWhite_castle_kingside() {
		return white_castle_kingside;
	}
	public static boolean canWhite_castle_queenside() {
		return white_castle_queenside;
	}
	public static boolean canBlack_castle_kingside() {
		return black_castle_kingside;
	}
	public static boolean canBlack_castle_queenside() {
		return black_castle_queenside;
	}
	public static int getEn_passant_position() {
		return en_passant_position;
	}
	public static boolean getWhite_turn() {
		return white_turn;
	}
	public boolean isUnfinished_promotion() {
		return unfinished_promotion;
	}
	public int getOpen_promotion_square() {
		return open_promotion_square;
	}
}