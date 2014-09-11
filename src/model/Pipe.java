package model;

public class Pipe {
	
	private static int y_min, y_max, gap_height;
	
	private int x, y; 

	public static void setYProps( int y_min, int y_max, int gap_height ) {
		
		Pipe.y_min = y_min;
		Pipe.y_max = y_max;
		Pipe.gap_height = gap_height;
		
	}
	
	public Pipe() {
		
	}
	
	public void initRand( int x_start ) {
		
		x = x_start;
		y = (int)(Math.random() * (y_max - y_min) + y_min);
		
	}
	
}
