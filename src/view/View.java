package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private final int initWidth = 288;
	private final int initHeight = 512;

	public View() {
		
		super();
		init();
		
	}
	
	public void init() {
		
		setSize(initWidth, initHeight);
		setResizable(false);
		setContentPane(getPanel());
		
	}
	
	public JPanel getPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		
		
		return panel;
		
	}

}
