package moving_library;

public class Game_End_Identifier extends Check_Checker{
	int king_square=-1;
	int c;
	public boolean game_has_ended(Board board) {
		if (board.amount_of_legal_moves()==0) {
			for (int i=0;i<64;i++) {
				//finds king and whose turn it is
				if (board.isWhite_turn()==true&board.getSquare(i)==25) {
					king_square=i;
					c=-1;
				} else if (board.isWhite_turn()==false&board.getSquare(i)==-25) {
					king_square=i;
					c=1;
				}
			}
			//check attacks
			//no attacks+no moves=stalemate
			//attacks+no moves=checkmate
			if (knight_attack(king_square, c)==false
			&diagonal_attack(king_square, board, c)==false
			&straight_attack(king_square, board, c)==false) {
				System.out.println("Stalemate");
				return true;
			} else {
				System.out.println("Checkmate");
				System.out.println("Kings_position: "+king_square);
				if (board.isWhite_turn()==true) {
					System.out.println("White has lost");
				} else {
					System.out.println("Black has lost");
				}
				return true;
			}
		} else {
			return false;
		}
	}
	
}