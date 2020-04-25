package fr.soleil.robot;

public class Obstacle extends Thing {
	Orientation ori;
	public Obstacle(Plateau plateau, Position position, Orientation ori) {
		super(plateau, position);
		this.ori=ori;
	}
}
