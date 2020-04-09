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
}

