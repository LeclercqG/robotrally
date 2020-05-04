package fr.soleil.robot;

public class Mur extends OrientedThing {
	
	
	public Mur(Plateau p, int x, int y, Orientation ori) {
		if(!p.getCell(x,y).hasMurOn(ori) && p.getCell(x,y).getTrou()==null) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.ori=ori;
			this.getItCell().addMur(this);
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
	
	private Mur(Plateau p, int x, int y, Orientation ori, int a) {
		this.x = x;
		this.y = y;
		this.plateau = p;
		this.ori=ori;
		this.getItCell().addMur(this);
	}
	
	@Override
	public Orientation getOrientation() {
		return ori;
	}		
}
