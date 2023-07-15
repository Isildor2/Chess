package moving_library;

public class Edges {
	//h file
	public static boolean onrightedge(int square) {
		if (square==7||square==15||square==23||square==31||square==39||square==47||square==55||square==63) {
			return true;
		}
		return false;
	}
	//a file
	public static boolean onleftedge(int square) {
		if (square==0||square==8||square==16||square==24||square==32||square==40||square==48||square==56) {
			return true;
		}
		return false;
	}
	//8 rank
	public static boolean on_upper_edge(int square) {
		if (square>55) {
			return true;
		}
		return false;
	}
	//1 rank
	public static boolean on_lower_edge(int square) {
		if (square<8) {
			return true;
		}
		return false;
	}
}