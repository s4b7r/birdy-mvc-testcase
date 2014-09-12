package view;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.Pipe;
import control.Controller;


public class View extends Frame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;

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
	
	@Override
	public void repaint() {
		super.repaint();
		
		Graphics gfx = getGraphics();
		
		gfx.clearRect(0, 0, getWidth(), getHeight());
		
		gfx.drawImage(controller.getWorld().getImageBackground(),
				0, 0,
				getWidth(), getHeight(),
				null);
		gfx.drawImage(controller.getWorld().getImageGround(),
				0, controller.getWorld().getGround_y(),
				getWidth(), getHeight(),
				null);
		
		for(int i = 0; i < controller.getWorld().getPipe_count(); i++ ) {
			
			gfx.drawImage(Pipe.getImageTop(),
					controller.getWorld().getPipe(i).getX(), controller.getWorld().getPipe(i).getY(),
					Pipe.getWidth(), Pipe.getHeight(),
					null);
			
			gfx.drawImage(Pipe.getImageBot(),
					controller.getWorld().getPipe(i).getX(), controller.getWorld().getPipe(i).getY2(),
					Pipe.getWidth(), Pipe.getHeight(),
					null);
			
		}
		
		gfx.drawImage(controller.getWorld().getBird().getImage(),
				controller.getWorld().getBird().getX(), controller.getWorld().getBird().getY(),
				controller.getWorld().getBird().getWidth(), controller.getWorld().getBird().getHeight(),
				null);
		
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
