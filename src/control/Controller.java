package control;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.View;
import model.*;

public class Controller implements Runnable {
	
	private final int WORLD_HEIGHT = 512;
	private final int WORLD_WIDTH = 288;
	
	private final int GROUND_Y = 500;
	
	private final int BIRD_START_X = 5;
	private final int BIRD_START_Y = 100;
	
	private final int PIPE_COUNT = 2;
	
	private final int PIPE_GAP_HEIGHT = 100;
	private final int PIPE_DISTANCE = 100;
	
	private final int PIPE_STEP = 1;
	private final int BIRD_STEP = 1;
	
	private final int PIPE_WIDTH = 52;
	private final int PIPE_HEIGHT = 320;
	
	private final int BIRD_WIDTH = 34;
	private final int BIRD_HEIGHT = 24;
	
	private final int BIRD_JUMP_HEIGHT_MAX = 50;
	
	private final int VIEW_WIDTH = 288;
	private final int VIEW_HEIGHT = 512;
	
	private final int TIME_STEP = 1000;
	
	private World world;
	private View view;
	
	private boolean jump_pressed = false;
	
	
	public Controller() {
		
		world = new World();
		world.setWH(WORLD_WIDTH, WORLD_HEIGHT);
		world.setProps(GROUND_Y, PIPE_GAP_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT, PIPE_STEP);
		world.setPipeCount(PIPE_COUNT);
		world.init();
		
		try {
			world.setImageBackground(ImageIO.read(new File("FlappyBird_Hintergrund.png")));
			world.setImageGround(ImageIO.read(new File("FlappyBird_Boden.png")));
			world.getBird().setImage(ImageIO.read(new File("FlappyBird_Flappy.png")));
			Pipe.setImageBot(ImageIO.read(new File("FlappyBird_Rohr2_unten.png")));
			Pipe.setImageTop(ImageIO.read(new File("FlappyBird_Rohr1_oben.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		world.getBird().setWH(BIRD_WIDTH, BIRD_HEIGHT);
		world.getBird().setJump_height_max(BIRD_JUMP_HEIGHT_MAX);
		world.getBird().init(BIRD_START_X, BIRD_START_Y);
		
		world.newPipe(0, world.getWidth());
		world.newPipe(1, world.getWidth() + PIPE_DISTANCE);
		
		view = new View(VIEW_WIDTH, VIEW_HEIGHT, this);
		view.setVisible(true);
		
		new Thread(this).start();
		
	}

	@Override
	public void run() {
		
		while( true ) {
			
			if( world.getBird().isJumpMax() ) {
				world.getBird().setBird_step(BIRD_STEP * -1);
				world.getBird().resetJumpHeight();
			}
			if( jump_pressed ) {
				// TODO Distance between jumps
				world.getBird().setBird_step(BIRD_STEP);
				world.getBird().resetJumpHeight();
			}
			
			world.movePipes();
			world.getBird().move();
			
			view.repaint();
			
			try {
				Thread.sleep(TIME_STEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public World getWorld() {
		return world;
	}
	
	public void keyPressed(KeyEvent e) {
		
		
		
	}

}
