package model;

public class World {

	private int pipe_count;

	private Bird bird;
	private Pipe[] pipe;
	
	private int width, height;
	private int ground_y;

	public World( ) {

	}

	public void setPipeCount( int pipe_count ) {

		this.pipe_count = pipe_count;

	}
	
	public void init() {
		
		bird = new Bird();
		pipe = new Pipe[pipe_count];
		
	}
	
	public void setWH( int width, int height ) {
		
		this.width = width;
		this.height = height;
		
	}
	
	public void setProps( int ground_y, int pipe_gap_height,
			int pipe_width, int pipe_height ) {
		
		this.ground_y = ground_y;
		Pipe.setYProps(ground_y, height, pipe_gap_height);
		
		
	}

	public Bird getBird() {
		return bird;
	}
	
	public Pipe getPipe( int index ) {
		return pipe[index];
	}

}
