package control;

import model.*;

public class Controller {
	
	private final int WORLD_HEIGHT = 512;
	private final int WORLD_WIDTH = 288;
	
	private final int GROUND_Y = 500;
	
	private final int BIRD_START_X = 5;
	private final int BIRD_START_Y = 100;
	
	private final int PIPE_COUNT = 2;
	
	private final int PIPE_GAP_HEIGHT = 100;
	private final int PIPE_DISTANCE = 100;
	
	private final int PIPE_WIDTH = 52;
	private final int PIPE_HEIGHT = 320;
	
	private final int BIRD_WIDTH = 34;
	private final int BIRD_HEIGHT = 24;
	
	private World world;
	
	public Controller() {
		
		world = new World();
		world.setWH(WORLD_WIDTH, WORLD_HEIGHT);
		world.setProps(GROUND_Y, PIPE_GAP_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT);
		world.setPipeCount(PIPE_COUNT);
		world.init();
		world.getBird().setWH(BIRD_WIDTH, BIRD_HEIGHT);
		world.getBird().init(BIRD_START_X, BIRD_START_Y);
		
		
	}

}
