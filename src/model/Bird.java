package model;

public class Bird {
	
	private int x, y;
	private int width, height;
	
	public Bird() {
		
	}
	
	public void setWH( int width, int height ) {
		
		this.width = width;
		this.height = height;
		
	}
	
	public void init( int x, int y ) {
		
		this.x = x;
		this.y = y;
		
	}

}
