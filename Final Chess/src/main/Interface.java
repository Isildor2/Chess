package main;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import moving_library.Board;

public class Interface {
	JFrame window=new JFrame("New Chess Game");
	private final int[] blacksquares = {1,0,1,0,1,0,1,0, 0,1,0,1,0,1,0,1, 1,0,1,0,1,0,1,0, 
			0,1,0,1,0,1,0,1, 1,0,1,0,1,0,1,0, 0,1,0,1,0,1,0,1, 1,0,1,0,1,0,1,0, 0,1,0,1,0,1,0,1};
	public Interface() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1200,800);
		window.setResizable(false);
		window.setLocation(0,0);
		window.setVisible(true);
	}
	//redo
	void add_buttons(JButton[] squares, JComboBox promotion_options,JButton promote) {
		JPanel boardpanel=new JPanel();
		JPanel optionpanel=new JPanel();
		JPanel windowpanel=new JPanel();
		GridLayout boardlayout = new GridLayout(8,8);
		boardpanel.setLayout(boardlayout);
		for (int j=7;j>-1;j--) {
			for (int i=0;i<8;i++) {
				boardpanel.add(squares[(j*8)+i]);
			}
		}
		GridLayout optionlayout = new GridLayout(2,1);
		optionpanel.setLayout(optionlayout);
		optionpanel.add(promotion_options);
		optionpanel.add(promote);
		
		GridLayout windowlayout = new GridLayout(1,2);
		windowpanel.setLayout(windowlayout);
		windowpanel.add(boardpanel);
		windowpanel.add(optionpanel);
		window.add(windowpanel);
	}
	
	void render(Board board, JButton[] square) {
		String image_bg = "";
		for (int i=0;i<64;i++) {
			if (blacksquares[i]==1) {
				image_bg = "bS";
			} else {
				image_bg = "wS";
			}
			switch (board.getSquare(i)) {
			case 0:
				if (blacksquares[i]==1) {
					Icon blacksquare = new ImageIcon("/Users/damian/Desktop/Chesspieces/bS.png");
					square[i].setIcon(blacksquare);
					continue;
				} else {
					Icon blacksquare = new ImageIcon("/Users/damian/Desktop/Chesspieces/wS.png");
					square[i].setIcon(blacksquare);
					continue;
				}
			case 1:
				Icon whitepawn = new ImageIcon("/Users/damian/Desktop/Chesspieces/wP"+image_bg+".png");
				square[i].setIcon(whitepawn);
				continue;
			case -1:
				Icon blackpawn = new ImageIcon("/Users/damian/Desktop/Chesspieces/bP"+image_bg+".png");
				square[i].setIcon(blackpawn);
				continue;
			case 2:
				Icon whiteknight = new ImageIcon("/Users/damian/Desktop/Chesspieces/wK"+image_bg+".png");
				square[i].setIcon(whiteknight);
				continue;
			case -2:
				Icon blackknight = new ImageIcon("/Users/damian/Desktop/Chesspieces/bK"+image_bg+".png");
				square[i].setIcon(blackknight);
				continue;
			case 3:
				Icon whitebishop = new ImageIcon("/Users/damian/Desktop/Chesspieces/wB"+image_bg+".png");
				square[i].setIcon(whitebishop);
				continue;
			case -3:
				Icon blackbishop = new ImageIcon("/Users/damian/Desktop/Chesspieces/bB"+image_bg+".png");
				square[i].setIcon(blackbishop);
				continue;
			case 5:
				Icon whiterook = new ImageIcon("/Users/damian/Desktop/Chesspieces/wR"+image_bg+".png");
				square[i].setIcon(whiterook);
				continue;
			case -5:
				Icon blackrook = new ImageIcon("/Users/damian/Desktop/Chesspieces/bR"+image_bg+".png");
				square[i].setIcon(blackrook);
				continue;
			case 9:
				Icon whitequeen = new ImageIcon("/Users/damian/Desktop/Chesspieces/wQ"+image_bg+".png");
				square[i].setIcon(whitequeen);
				continue;
			case -9:
				Icon blackqueen = new ImageIcon("/Users/damian/Desktop/Chesspieces/bQ"+image_bg+".png");
				square[i].setIcon(blackqueen);
				continue;
			case 25:
				Icon whiteking = new ImageIcon("/Users/damian/Desktop/Chesspieces/wKi"+image_bg+".png");
				square[i].setIcon(whiteking);
				continue;
			case -25:
				Icon blackking = new ImageIcon("/Users/damian/Desktop/Chesspieces/bKi"+image_bg+".png");
				square[i].setIcon(blackking);
			}
		}
	}
	//maybe function to highlight possible squares you could move to
}