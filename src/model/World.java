package model;

import java.awt.Image;

public class World {

	private int pipe_count;

	private Bird bird;
	private Pipe[] pipe;
	
	private int width, height;
	private int ground_y;
	
	private Image imageBackground;
	private Image imageGround;
	private Image imageGameOver;
	
	private int score;
	private boolean gameover;
	private boolean restartable;

	public World( ) {

	}

	public void setPipeCount( int pipe_count ) {

		this.pipe_count = pipe_count;

	}
	
	public void init() {
		
		score = 0;
		gameover = false;
		restartable = false;
		bird = new Bird();
		pipe = new Pipe[pipe_count];
		for( int i = 0; i < pipe_count; i++ ) {
			pipe[i] = new Pipe();
		}
		
	}
	
	public void setWH( int width, int height ) {
		
		this.width = width;
		this.height = height;
		
	}
	
	public void setProps( int ground_y, int pipe_gap_height,
			int pipe_width, int pipe_height,
			int pipe_step ) {
		
		this.ground_y = ground_y;
		Pipe.setWH(pipe_width, pipe_height);
		// Höhe der "Löcher" der Rohre, sowie erlaubten Bereich
		// für die Löcher übergeben
		// Der erlaubte Bereich wird so berechnet, dass
		// die Rohr-Bilder nicht zu kurz sind
		Pipe.setYProps(ground_y - pipe_gap_height - pipe_height,
				pipe_height + pipe_gap_height,
				pipe_gap_height);
		Pipe.setProps(pipe_step);
		
	}

	public Bird getBird() {
		return bird;
	}
	
	public void newPipe( int index, int x_start ) {
		Pipe p = pipe[index];
		p.initRand(x_start);
	}
	
	public void newPipe( int index ) {
		newPipe(index, width);
	}

	public int getWidth() {
		return width;
	}
	
	public void movePipes() {
		
		for( int i = 0; i < pipe_count; i++ ) {
			pipe[i].move();
		}
		
	}
	
	public Image getImageBackground() {
		return imageBackground;
	}
	
	public Image getImageGround() {
		return imageGround;
	}

	public int getGround_y() {
		return ground_y;
	}

	public int getPipe_count() {
		return pipe_count;
	}

	public Pipe getPipe( int index ) {
		return pipe[index];
	}

	public void setImageBackground(Image imageBackground) {
		this.imageBackground = imageBackground;
	}

	public void setImageGround(Image imageGround) {
		this.imageGround = imageGround;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHeight() {
		return height;
	}

	public Image getImageGameOver() {
		return imageGameOver;
	}

	public void setImageGameOver(Image imageGameOver) {
		this.imageGameOver = imageGameOver;
	}

	public boolean isGameover() {
		return gameover;
	}

	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}

	public boolean isRestartable() {
		return restartable;
	}

	public void setRestartable(boolean restartable) {
		this.restartable = restartable;
	}

}
