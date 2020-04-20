package fr.soleil.robot;

import fr.soleil.robot.Position;

public class Thing {
	protected Position pos;
	protected Plateau plateau;
	
	public Thing(Plateau plateau, Position position){
		this.pos=position;
		this.plateau = plateau;
		position.getContenu().add(this);
	}
	public Position position() {
		return this.pos;
	}
}
