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
			this.y-=1;
			break;
		case SOUTH:
			this.y+=1;
			break;	
		case WEST:
			this.x-=1;
			break;
		case EAST:
			this.x+=1;
			break;
		}
		this.ori=this.ori.next().next();
		this.getNextCell().addMur(this);
	}
	
	public Orientation getOrientation() {
		return ori;
	}
	
	public Cell getPosition() {
		return plateau.getCell(x, y);
	}
	
}
