package model;

import java.awt.Image;

public class Pipe {
	
	private static int y_min, y_max, gap_height;
	
	private static int pipe_step;
	
	private static int width, height;
	
	private int x, y;
	
	private boolean scored;
	
	private static Image imageTop;
	private static Image imageBot;

	public static void setYProps( int y_min, int y_max, int gap_height ) {
		
		Pipe.y_min = y_min - height;
		Pipe.y_max = y_max - height - gap_height;
		Pipe.gap_height = gap_height;
		
	}
	
	public static void setWH( int pipe_width, int pipe_height ) {
		
		Pipe.width = pipe_width;
		Pipe.height = pipe_height;
		
	}
	
	public static void setProps( int pipe_step ) {
		
		Pipe.pipe_step = pipe_step;
		
	}
	
	public Pipe() {
		
	}
	
	
	public void initRand( int x ) {
		
		this.x = x;
		y = (int)(Math.random() * (y_max - y_min) + y_min);
		scored = false;
		
	}
	
	public void move() {
		
		x -= pipe_step;
		
	}
	
	public static Image getImageTop() {
		return imageTop;
	}
	
	public static Image getImageBot() {
		return imageBot;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getY2() {
		return y + height + gap_height;
	}

	public static void setImageTop(Image imageTop) {
		Pipe.imageTop = imageTop;
	}

	public static void setImageBot(Image imageBot) {
		Pipe.imageBot = imageBot;
	}

	public static int getGap_height() {
		return gap_height;
	}

	public boolean isScored() {
		return scored;
	}

	public void setScored(boolean scored) {
		this.scored = scored;
	}
	
}
