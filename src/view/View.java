package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import model.Bird;
import model.Pipe;
import model.World;
import control.Controller;


public class View extends Frame implements KeyListener{

	private static final long serialVersionUID = 1L;

	private Controller controller;

	private BufferStrategy buffer;

	public View( int width, int height, Controller controller ) {

		super();
		setSize(width, height);
		setResizable(false);
		this.controller = controller;
		addKeyListener(this);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowIconified(WindowEvent e) {
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

	}

	public void init() {
		// Grafikpuffer gegen Bildflimmern
		// Es wird im Hintergrund gezeichnet und
		// erst später das Bild angezeigt
		createBufferStrategy(2);
		buffer = getBufferStrategy();
	}

	public void drawWorld( World world ) {

		Graphics gfx = buffer.getDrawGraphics();
//		Graphics gfx = getGraphics();

		gfx.clearRect(0, 0, getWidth(), getHeight());

		gfx.drawImage(world.getImageBackground(),
				0, 0,
				getWidth(), getHeight(),
				null);
		//		gfx.drawRect(0, 0, getWidth(), getHeight());
		//		gfx.setColor(new Color(255, 255, 255));
		//		gfx.fillRect(0, 0, getWidth(), getHeight());

		for(int i = 0; i < world.getPipe_count(); i++ ) {

			Pipe pipe = world.getPipe(i);

			gfx.drawImage(Pipe.getImageTop(),
					pipe.getX(), pipe.getY(),
					Pipe.getWidth(), Pipe.getHeight(),
					null);

			gfx.drawImage(Pipe.getImageBot(),
					pipe.getX(), pipe.getY2(),
					Pipe.getWidth(), Pipe.getHeight(),
					null);

			//			gfx.setColor(new Color(255, 0, 0));
			//			gfx.fillRect(world.getPipe(i).getX(),
			//					world.getPipe(i).getY(),
			//					Pipe.getWidth(), Pipe.getHeight());
			//			gfx.fillRect(world.getPipe(i).getX(),
			//					world.getPipe(i).getY2(),
			//					Pipe.getWidth(), Pipe.getHeight());

		}


		Bird bird = world.getBird();
		gfx.drawImage(bird.getImage(),
				bird.getX(), bird.getY(),
				bird.getWidth(), bird.getHeight(),
				null);
		//		gfx.setColor(new Color(0, 255, 0));
		//		gfx.fillRect(world.getBird().getX(),
		//				world.getBird().getY(),
		//				world.getBird().getWidth(),
		//				world.getBird().getHeight());

		gfx.drawImage(world.getImageGround(),
				0, world.getGround_y(),
				getWidth(), getHeight() - world.getGround_y(),
				null);
		//		gfx.setColor(new Color(255, 255, 0));
		//		gfx.fillRect(0, world.getGround_y(), getWidth(), getHeight()-world.getGround_y());

		gfx.setFont(new Font("Courier", 0, 30));
		gfx.setColor(new Color(255, 255, 255));
		gfx.drawString(String.valueOf(world.getScore()), 10, world.getHeight() - 30);

		if( world.isGameover() ) {
			gfx.drawImage(world.getImageGameOver(),
					world.getWidth()/2-world.getImageGameOver().getWidth(null)/2,
					world.getHeight()/2-world.getImageGameOver().getHeight(null)/2,
					null);
		}
		if( world.isRestartable() ) {
			gfx.setColor(new Color(0, 0, 0));
			gfx.setFont(new Font("Courier", 0, 20));
			gfx.drawString("Press B to restart",
					world.getWidth()/2-world.getImageGameOver().getWidth(null)/2,
					(int)(world.getHeight()/2+world.getImageGameOver().getHeight(null)*1.5));
		}

		buffer.show();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		controller.keyPressed(e);
	}

}
