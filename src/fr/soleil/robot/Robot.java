package fr.soleil.robot;

public class Robot extends Thing{
	private Orientation ori;
	
	public Robot(Position initialPosition, Orientation initialOrientation) {
		super(initialPosition);
		this.ori=initialOrientation;
	}
	
	public Orientation getOrientation() {
		return this.ori;
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	public void turnRight() {
		this.ori=this.ori.next();
	}
	
	public void turnLeft() {
		this.ori=this.ori.previous();
	}
	
	public void stepForward() {
		switch (this.ori) {
			case NORTH:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x,this.pos.y-1);
				this.pos.getContenu().add(this);
				break;
			case SOUTH:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x,this.pos.y+1);
				this.pos.getContenu().add(this);
				break;
			case WEST:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x-1,this.pos.y);
				this.pos.getContenu().add(this);
				break;
			case EAST:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x+1,this.pos.y);
				this.pos.getContenu().add(this);
				break;
		}
	}
	
	public void simulate(String string) {
		for(int i=0;i<string.length();i++)
			switch(string.charAt(i)) {
			case 'R':
				turnRight();
				break;
			case 'L':
				turnLeft();
				break;
			case 'F':
				stepForward();
				break;
			}
	}
	
}

