package fr.soleil.robot;

public class Mur extends OrientedThing {

	public Mur(Plateau p, int x, int y, Orientation ori) {
		if (!p.getCell(x, y).hasMurOn(ori) && p.getCell(x, y).getTrou() == null && p.getCell(x, y).getMurs().size()<3) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.ori = ori;
			this.getItCell().addMur(this);
			if(!this.getNextCell(ori.next().next()).cellIsNotInGame()) {
				switch (this.ori) {
					case NORTH:
						new Mur(p, x, y - 1, ori.next().next());
						break;
					case SOUTH:
						new Mur(p, x, y + 1, ori.next().next());
						break;
					case WEST:
						new Mur(p, x - 1, y, ori.next().next());
						break;
					case EAST:
						new Mur(p, x + 1, y, ori.next().next());
						break;
				}
			}
		}
	}

	@Override
	public Orientation getOrientation() {
		return ori;
	}
}
