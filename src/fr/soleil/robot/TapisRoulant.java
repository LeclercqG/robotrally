package fr.soleil.robot;

public class TapisRoulant extends OrientedThing {
	
	private boolean isAngle;
	
	public TapisRoulant ( Plateau plateau, int x , int y, Orientation ori, boolean isAngle) {
		Cell cell=plateau.getCell(x,y);
		if (cell.getDrapeau() == null && cell.getTapisRoulant()== null && cell.getTrou()== null) {
			this.x = x ;
			this.y = y ;
			this.setAngle(isAngle) ;
			this.plateau = plateau ;
			this.ori = ori ;
			getItCell().setTapisRoulant(this);
		}
	}

	public boolean isAngle() {
		return isAngle;
	}

	public void setAngle(boolean isAngle) {
		this.isAngle = isAngle;
	}
	
}

