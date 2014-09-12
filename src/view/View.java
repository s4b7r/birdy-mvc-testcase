package view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import model.Pipe;
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
		createBufferStrategy(2);
		buffer = getBufferStrategy();
	}
	
	@Override
	public void repaint() {
		super.repaint();
		
		Graphics gfx = buffer.getDrawGraphics();
		
		gfx.clearRect(0, 0, getWidth(), getHeight());
		
		/*gfx.drawImage(controller.getWorld().getImageBackground(),
				0, 0,
				getWidth(), getHeight(),
				null);*/
		//gfx.drawRect(0, 0, getWidth(), getHeight());
		gfx.setColor(new Color(255, 255, 255));
		gfx.fillRect(0, 0, getWidth(), getHeight());
		/*gfx.drawImage(controller.getWorld().getImageGround(),
				0, controller.getWorld().getGround_y(),
				getWidth(), getHeight(),
				null);*/
		gfx.setColor(new Color(255, 255, 0));
		gfx.fillRect(0, controller.getWorld().getGround_y(), getWidth(), getHeight()-controller.getWorld().getGround_y());
		
		for(int i = 0; i < controller.getWorld().getPipe_count(); i++ ) {
			
			/*gfx.drawImage(Pipe.getImageTop(),
					controller.getWorld().getPipe(i).getX(), controller.getWorld().getPipe(i).getY(),
					Pipe.getWidth(), Pipe.getHeight(),
					null);
			
			gfx.drawImage(Pipe.getImageBot(),
					controller.getWorld().getPipe(i).getX(), controller.getWorld().getPipe(i).getY2(),
					Pipe.getWidth(), Pipe.getHeight(),
					null);*/
			
			gfx.setColor(new Color(255, 0, 0));
			gfx.fillRect(controller.getWorld().getPipe(i).getX(),
					controller.getWorld().getPipe(i).getY(),
					Pipe.getWidth(), Pipe.getHeight());
			gfx.fillRect(controller.getWorld().getPipe(i).getX(),
					controller.getWorld().getPipe(i).getY2(),
					Pipe.getWidth(), Pipe.getHeight());
			
		}
		
		/*gfx.drawImage(controller.getWorld().getBird().getImage(),
				controller.getWorld().getBird().getX(), controller.getWorld().getBird().getY(),
				controller.getWorld().getBird().getWidth(), controller.getWorld().getBird().getHeight(),
				null);*/
		gfx.setColor(new Color(0, 255, 0));
		gfx.fillRect(controller.getWorld().getBird().getX(),
				controller.getWorld().getBird().getY(),
				controller.getWorld().getBird().getWidth(),
				controller.getWorld().getBird().getHeight());
		
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
