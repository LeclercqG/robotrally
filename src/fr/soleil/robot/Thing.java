package fr.soleil.robot;

public abstract class Thing {
	protected int x;
	protected int y;
	protected Plateau plateau;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Cell getItCell() {
		return plateau.getCell(x, y);
	}

}
