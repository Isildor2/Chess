package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import engine.*;
import moving_library.Board;
import moving_library.Move_Handler;

public class Controller {
	public JButton[] squares = new JButton[64];
	Board board=new Board();
	Interface ui=new Interface();
	Move_Handler handler=new Move_Handler();
	boolean one_click=false;
	boolean unfinished_promotion=false;
	int open_promotion_square=-1;
	private int startsquare;
	private int targetsquare;
	
	void setup_board() {
		for (int i=0;i<64;i++) {
			squares[i]= new JButton(""+i);
			squares[i].addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				if (unfinished_promotion==false) { 
	    				Object source = e.getSource();
	    				for (int j=0;j<squares.length;j++) {
	    					if (squares[j]==source) {
	    						if (one_click==false) {
	    							startsquare=j;
	    							one_click=true;
	    						} else {
	    							targetsquare=j;
	    							one_click=false;
	    							move();
	    						}
	    					}
	    				}
    				}
    			}
    		});
		}
		setup_options();
	}
	void setup_options() {
		JComboBox<String> promotion_options = new JComboBox<>();
		promotion_options.addItem("Queen");
		promotion_options.addItem("Rook");
		promotion_options.addItem("Bishop");
		promotion_options.addItem("Knight");
		
		JButton promote = new JButton("Promote");  
		promote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int promoted_piece=0;
				if (promotion_options.getSelectedItem()=="Queen") {
					promoted_piece=9;
				} else if (promotion_options.getSelectedItem()=="Rook") {
					promoted_piece=5;
				} else if (promotion_options.getSelectedItem()=="Bishop") {
					promoted_piece=3;
				} else {
					promoted_piece=2;
				}
				if (board.isWhite_turn()==true) {
					promoted_piece=promoted_piece*-1;
				}
				board.setSquare(open_promotion_square,promoted_piece);
				unfinished_promotion=false;
				open_promotion_square=-1;
				ui.render(board, squares);
			}
		});
		ui.add_buttons(squares,promotion_options, promote);
		ui.render(board, squares);
	}

	void move() {
		board.clear_moves();
		handler.generate_all_legal_moves(board);
System.out.println("");
System.out.println(board.amount_of_legal_moves());
		for (int i=0;i<board.amount_of_legal_moves();i++) {
			if (board.getMove(i).start_square()==startsquare&&board.getMove(i).target_square()==targetsquare) {
				handler.execute_move(board, board.getMove(i));
				startsquare=-1;
				targetsquare=-1;
				if (board.getMove(i).promotion_type()!=-1) {
					unfinished_promotion=true;
					open_promotion_square=board.getMove(i).target_square();
				}
				board.clear_moves();
				ui.render(board, squares);
				break;
			}
		}
	}
}