package fr.soleil.robot;

public class Mur extends Thing {
	
	private Orientation ori;
	
	public Mur(Plateau plateau, Position position, Orientation ori) {
		super(plateau, position);
		this.ori=ori;
	}
	
	public Orientation getOrientation() {
		return ori;
	}
}
