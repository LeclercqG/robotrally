package fr.soleil.robot;

import java.util.Objects;

public class Mur extends OrientedThing {
	
	
	public Mur(Plateau p, int x, int y, Orientation ori) {
		if(!p.getCell(x,y).hasMurOn(ori) && p.getCell(x,y).getTrou()==null) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.ori=ori;
			this.plateau.getCell(x, y).addMur(this);
			switch (this.ori) {
				case NORTH:
					new Mur(p, x, y-1, ori.next().next(), 0);
					break;
				case SOUTH:
					new Mur(p, x, y+1, ori.next().next(), 0);
					break;	
				case WEST:
					new Mur(p, x-1, y, ori.next().next(), 0);
					break;
					
				case EAST:
					new Mur(p, x+1, y, ori.next().next(), 0);
					break;
			}
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
