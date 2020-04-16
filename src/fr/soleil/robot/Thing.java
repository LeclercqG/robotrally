package fr.soleil.robot;

import fr.soleil.robot.Position;

public class Thing {
	private Position pos;
	public Thing(Position position){
		this.pos=position;
		position.getContenu().add(this);
	}
	public Position position() {
		return this.pos;
	}

}
