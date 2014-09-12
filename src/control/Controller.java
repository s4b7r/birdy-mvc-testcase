package control;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import view.View;
import model.*;

public class Controller implements Runnable {

	private final int WORLD_HEIGHT = 600;
	private final int WORLD_WIDTH = 400;

	private final int GROUND_Y = 500;

	private final int BIRD_START_X = 20;
	private final int BIRD_START_Y = 100;

	private final int PIPE_COUNT = 2;

	private final int PIPE_GAP_HEIGHT = 125;
	private final int PIPE_DISTANCE = WORLD_WIDTH / PIPE_COUNT;

	private final int PIPE_STEP = 2;
	private final int BIRD_STEP = 3;

	private final int PIPE_WIDTH = 52;
	private final int PIPE_HEIGHT = 320;

	private final int BIRD_WIDTH = 34;
	private final int BIRD_HEIGHT = 24;

	private final int BIRD_JUMP_HEIGHT_MAX = 60;

	private final int TIME_STEP = 10;

	private World world;
	private View view;

	private boolean jump_pressed = false;
	private boolean stop = false;

	private boolean collisionPipe = false;
	private boolean collisionGround = false;


	public Controller() {

		initWorld();

		initImages();

		initBird();

		initPipes();

		initView();

		new Thread(this).start();

	}

	@Override
	public void run() {

		while( !stop ) {

			handleBird();

			if( !collisionPipe ) {
				world.movePipes();
			}

			handlePipes();

			if( !collisionGround ) {
				world.getBird().move();
			} else {
				stop = true;
			}

			view.drawWorld(world);

			handleCollisionPipe();
			handleCollisionGround();

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

		if( e.getKeyCode() == 32 ) {
			jump_pressed = true;
		}

	}

	public void initWorld() {
		world = new World();
		world.setWH(WORLD_WIDTH, WORLD_HEIGHT);
		world.setProps(GROUND_Y, PIPE_GAP_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT, PIPE_STEP);
		world.setPipeCount(PIPE_COUNT);
		world.init();
	}

	public void initImages() {
		try {
			world.setImageBackground(ImageIO.read(new File("res/FlappyBird_Hintergrund.png")));
			world.setImageGround(ImageIO.read(new File("res/FlappyBird_Boden.png")));
			world.getBird().setImage(ImageIO.read(new File("res/FlappyBird_Flappy.png")));
			Pipe.setImageBot(ImageIO.read(new File("res/FlappyBird_Rohr2_unten.png")));
			Pipe.setImageTop(ImageIO.read(new File("res/FlappyBird_Rohr1_oben.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initBird() {
		world.getBird().setWH(BIRD_WIDTH, BIRD_HEIGHT);
		world.getBird().setJump_height_max(BIRD_JUMP_HEIGHT_MAX);
		world.getBird().init(BIRD_START_X, BIRD_START_Y);
		world.getBird().setBird_step(BIRD_STEP * -1);
	}

	public void initPipes() {
		for( int i = 0; i < world.getPipe_count(); i++ ) {
			world.newPipe(i, world.getWidth() + PIPE_DISTANCE * i);
		}
	}

	public void initView() {
		view = new View(WORLD_WIDTH, WORLD_HEIGHT, this);
		view.setVisible(true);
		view.init();
	}

	public void handleBird() {
		if( jump_pressed && !collisionPipe ) {
			jump_pressed = false;
			world.getBird().setBird_step(BIRD_STEP);
			world.getBird().resetJumpHeight();
		}
		if( world.getBird().isJumpMax() || world.getBird().getY() <= 0 || collisionPipe ) {
			world.getBird().setBird_step(BIRD_STEP * -1);
			world.getBird().resetJumpHeight();
		}
	}

	public void handlePipes() {
		for( int i = 0; i < world.getPipe_count(); i++ ) {
			if( world.getPipe(i).getX() < Pipe.getWidth() * -1 ) {
				world.newPipe(i);
			}
		}
	}

	public void handleCollisionPipe() {

		for( int i = 0; i < world.getPipe_count(); i++ ) {

			if( world.getBird().getX() + world.getBird().getWidth()
					> world.getPipe(i).getX() &&
					world.getBird().getX()
					< world.getPipe(i).getX() + Pipe.getWidth() ) {

				if( world.getBird().getY() < world.getPipe(i).getY() + Pipe.getHeight() ||
						world.getBird().getY() + world.getBird().getHeight()
						> world.getPipe(i).getY() + Pipe.getHeight() + Pipe.getGap_height() ) {

					collisionPipe = true;
				}
			}
		}

	}

	public void handleCollisionGround() {

		if( world.getBird().getY() + world.getBird().getHeight() > world.getGround_y() ) {
			collisionGround = true;
		}

	}

}
