package imt.uvinfo.robot;

public class Thing {
	private Position pos;
	public Thing(Position position) {
		this.pos=position;
	}

	public Object position() {
		return this.pos;
	}
}
