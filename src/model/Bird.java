package model;

public class Bird {
	
	private final int width = 34;
	private final int height = 24;
	private final int x_start = 5;
	private final int y_start = 100;
	
	private int x, y;
	
	public Bird() {
		
	}
	
	public void getStart( int x_start, int y_start ) {
		
		x = x_start;
		y = y_start;
		
	}

}
