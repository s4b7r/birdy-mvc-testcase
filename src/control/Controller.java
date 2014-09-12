package control;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.stream.events.StartDocument;

import view.View;
import model.*;

public class Controller implements Runnable {

	// Weltbreite und -höhe
	// gleichzeitig Fensterbreite und -höhe
	private final int WORLD_HEIGHT = 600;
	private final int WORLD_WIDTH = 400;

	// Höhe des Bodens
	private final int GROUND_Y = 500;

	private final int BIRD_START_X = 20;
	private final int BIRD_START_Y = 100;

	private final int PIPE_COUNT = 2;

	private final int PIPE_GAP_HEIGHT = 125;
	private final int PIPE_DISTANCE = WORLD_WIDTH / PIPE_COUNT;

	// Schrittweite pro Zeitschritt für...
	// ... horizontale Bewegung der Röhren
	private final int PIPE_STEP = 2;
	// ... vertikale Bewegung des Vogels
	private final int BIRD_STEP = 3;

	private final int PIPE_WIDTH = 52;
	private final int PIPE_HEIGHT = 320;

	private final int BIRD_WIDTH = 34;
	private final int BIRD_HEIGHT = 24;

	// Höhendifferenz nachdem der Sprung beendet ist
	// und der Fall einsetzt
	private final int BIRD_JUMP_HEIGHT_MAX = 60;

	// Länge eines Zeitschritts
	// Zeitangabe für Thread.sleep(xx)
	private final int TIME_STEP = 10;

	
	private World world;
	private View view;

	// Indikator der Leertaste für Sprünge
	private boolean jump_pressed = false;

	// Bedingung für Durchlaufen des Loops 
	private boolean stop = false;

	// Indikatoren für Kollisionen mit Rohr oder Boden
	private boolean collisionPipe = false;
	private boolean collisionGround = false;


	public Controller() {

		init();

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
				// Ist der Boden berührt,
				// muss nichts mehr animiert werden ...
				stop = true;
				// ...und das Spiel kann neu gestartet werden
				world.setRestartable(true);
			}
			
			handleScore();

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

		// KeyCode 32: Leertaste
		// KeyCode 66: B
		
		if( e.getKeyCode() == 32 ) {
			jump_pressed = true;
		}
		if( world.isRestartable() && e.getKeyCode() == 66 ) {
			// Bei Neustart, Controller neu initialisieren
			// Initialisiert ebenso das Modell
			init();
		}

	}
	
	private void init() {
		
		jump_pressed = false;
		stop = false;
		collisionPipe = false;
		collisionGround = false;
		
		initWorld();

		initImages();

		initBird();

		initPipes();

		initView();

		new Thread(this).start();
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
			world.setImageGameOver(ImageIO.read(new File("res/FlappyBird_Gameover.png")));
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
		// Mit Fall-Bewegung beginnen
		world.getBird().setBird_step(BIRD_STEP * -1);
	}

	public void initPipes() {
		// Rohre entsprechend der angegebenen Distanz
		// außerhalb des anfangs dargestellten Bereichs platzieren
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
		// Ist ein Rohr komplett durch,
		// wieder an den Anfang setzen
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
					world.setGameover(true);
				}
			}
		}

	}

	public void handleCollisionGround() {

		if( world.getBird().getY() + world.getBird().getHeight() > world.getGround_y() ) {
			collisionGround = true;
			world.setGameover(true);
		}

	}
	
	public void handleScore() {
		
		for( int i = 0; i < world.getPipe_count(); i++ ) {
			if( world.getBird().getX() > world.getPipe(i).getX() + Pipe.getWidth() &&
					!world.getPipe(i).isScored() ) {
				world.getPipe(i).setScored(true);
				world.setScore(world.getScore()+1);
				System.out.println(world.getScore());
			}
		}
		
	}

}
