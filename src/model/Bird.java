package model;

import java.awt.Image;

public class Bird {
	
	private int x, y;
	private int width, height;
	
	private int jump_height_max;
	private int jump_height_last;
	
	private int bird_step;
	
	private Image image;
	
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

	public void move() {
		
		y += bird_step;
		jump_height_last += bird_step;
		
	}
	
	public boolean isJumpMax() {
		return jump_height_last >= jump_height_max;
	}

	public int getBird_step() {
		return bird_step;
	}

	public void setBird_step(int bird_step) {
		this.bird_step = bird_step;
	}

	public int getJump_height_max() {
		return jump_height_max;
	}

	public void setJump_height_max(int jump_height_max) {
		this.jump_height_max = jump_height_max;
	}

	public int getJump_height_last() {
		return jump_height_last;
	}

	public void setJump_height_last(int jump_height_last) {
		this.jump_height_last = jump_height_last;
	}
	
	public void resetJumpHeight() {
		jump_height_last = 0;
	}
	
	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
