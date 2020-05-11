package fr.soleil.robot;

public class OrientedThing extends Thing {

	protected Orientation ori;

	public Cell getNextCell(Orientation o, int nbrCell) {
		Cell cell = new Cell(true);
		switch (o) {
			case NORTH:
				if (this.y == 0) {
					break;
				}
				cell = plateau.getCell(x, y - nbrCell);
				break;
			case SOUTH:
				if (this.y == (plateau.getColumn(x).size() - nbrCell)) {
					break;
				}
				cell = plateau.getCell(x, y + nbrCell);
				break;
			case WEST:
				if (this.x == 0) {
					break;
				}
				cell = plateau.getCell(x - nbrCell, y);
				break;
			case EAST:
				if (this.y == (plateau.getLine(y).size() - nbrCell)) {
					break;
				}
				cell = plateau.getCell(x + nbrCell, y);
				break;
		}
		return cell;
	}

	public Orientation getOrientation() {
		return this.ori;
	}
}
