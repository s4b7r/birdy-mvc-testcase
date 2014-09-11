package model;

public class Pipe {
	
	private final int width = 52;
	private final int height = 320;
	private final int gapHeight = 100;
	
	private int x, y; 

	public Pipe() {
		
	}
	
	public void getRandStart( int x_start, int y_max, int y_min ) {
		
		x = x_start;
		y = (int)(Math.random() * (y_max - y_min) + y_min);
		
	}
	
}
