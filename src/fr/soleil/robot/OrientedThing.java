package fr.soleil.robot;

public class OrientedThing extends Thing {

	protected Orientation ori;

	public Cell getNextCell(Orientation o) {
		Cell cell = new Cell(true);
		switch (o) {
			case NORTH:
				if (this.y == 0) {
					break;
				}
				cell = plateau.getCell(x, y - 1);
				break;
			case SOUTH:
				if (this.y == (plateau.getColumn(x).size() - 1)) {
					break;
				}
				cell = plateau.getCell(x, y + 1);
				break;
			case WEST:
				if (this.x == 0) {
					break;
				}
				cell = plateau.getCell(x - 1, y);
				break;
			case EAST:
				if (this.y == (plateau.getLine(y).size() - 1)) {
					break;
				}
				cell = plateau.getCell(x + 1, y);
				break;
		}
		return cell;
	}

	public Orientation getOrientation() {
		return this.ori;
	}
}
