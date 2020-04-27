package fr.soleil.robot;

public class Mur extends OrientedThing {
	
	
	public Mur(Plateau plateau, int x, int y, Orientation ori) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.ori=ori;
		this.plateau.getCell(x, y).addMur(this);
	}
	
	public Orientation getOrientation() {
		return ori;
	}
	
	public Cell getPosition() {
		return plateau.getCell(x, y);
	}
	
}
