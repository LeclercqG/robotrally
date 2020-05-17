package fr.soleil.robot;

public class OrientedThing extends Thing {

	protected Orientation ori;

	/*public Cell getNextCell(Orientation o, int nbrCell) {
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
	}*/
	
	public Cell getNextCell(Orientation o, int nbrCellInAxe, int nbrCellOppositeAxe) {
		Cell cell = new Cell(true);
			if(nbrCellInAxe>0) {
			switch (o) {
				case NORTH:
					 cell = getCellFromTranslation(x + nbrCellOppositeAxe,y - nbrCellInAxe);
					break;
				case SOUTH:
					cell = getCellFromTranslation(x + nbrCellOppositeAxe,y + nbrCellInAxe);
					break;
				case WEST:
					cell = getCellFromTranslation(x - nbrCellInAxe,y + nbrCellOppositeAxe);
					break;
				case EAST:
					cell = getCellFromTranslation(x + nbrCellInAxe,y + nbrCellOppositeAxe);
					break;
			}
		}
		return cell;
	}
	
	
	
	public Cell getCellFromTranslation(int x, int y) {
		Cell cell = new Cell(true);
		if ( x>=0 && x<plateau.getLine(y).size() && y>=0 && y<plateau.getColumn(x).size()){
				cell = plateau.getCell(x, y);
		}
		return cell;
	}

	public Orientation getOrientation() {
		return this.ori;
	}
}
