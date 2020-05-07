package fr.soleil.robot;

public class Clef extends Thing {	
	public Clef(Plateau p, int x, int y) {
		Cell cell=p.getCell(x, y);
		if (cell.getDrapeau() == null && cell.getClef()== null && cell.getTrou()== null) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.getItCell().setClef(this);
		}
	}
}
