package fr.soleil.robot;

public class Drapeau extends Thing {
	//TODO trouver mieu qu'un static (qui pose probl�me lors des tests)
	private static int nbrDrapeau=1;
	private int rang;
	public int getRang() {
		return rang;
	}

	public Drapeau(Plateau p, int x, int y) {
		if (p.getCell(x, y).getDrapeau() == null) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.getItCell().setDrapeau(this);
			this.rang=nbrDrapeau++;
		}
	}
}
