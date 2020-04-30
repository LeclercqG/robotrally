package fr.soleil.robot;

public class Mur extends OrientedThing {
	
	
	public Mur(Plateau plateau, int x, int y, Orientation ori) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.ori=ori;
		this.plateau.getCell(x, y).addMur(this);
		switch (this.ori) {
		case NORTH:
			new Mur(plateau, x, y-1, ori.next().next(), 0);
			break;
		case SOUTH:
			new Mur(plateau, x, y+1, ori.next().next(), 0);
			break;	
		case WEST:
			new Mur(plateau, x-1, y, ori.next().next(), 0);
			break;
			
		case EAST:
			new Mur(plateau, x+1, y, ori.next().next(), 0);
			break;
		}
	}
	
	private Mur(Plateau plateau, int x, int y, Orientation ori, int a) {
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
