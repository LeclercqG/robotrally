package imt.uvinfo.robot;

public class Robot {
	private Position pos;
	private Orientation ori;
	public Robot(Position initialPosition, Orientation initialOrientation) {
		this.pos=initialPosition;
		this.ori=initialOrientation;
	}
	public Object orientation() {
		return this.ori;
	}
	public Object position() {
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
				this.pos=new Position(this.pos.x,this.pos.y+1);
				break;
			case SOUTH:
				this.pos=new Position(this.pos.x,this.pos.y-1);
				break;
			case WEST:
				this.pos=new Position(this.pos.x-1,this.pos.y);
				break;
			case EAST:
				this.pos=new Position(this.pos.x+1,this.pos.y);
				break;
		}
		
	}
}

