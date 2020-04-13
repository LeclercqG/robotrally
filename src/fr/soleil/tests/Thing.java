package fr.soleil.tests;

import fr.soleil.robot.Position;

public class Thing {
	private Position pos;
	public Thing(Position position){
		this.pos=position;
	}
	public Position position() {
		return this.pos;
	}

}
