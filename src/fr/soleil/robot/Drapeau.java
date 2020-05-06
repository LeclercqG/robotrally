package fr.soleil.robot;

import java.util.concurrent.atomic.AtomicInteger;

public class Drapeau extends Thing {
	
	private static final AtomicInteger nbDrapeau = new AtomicInteger(0);

	private int rang;
	
	public Drapeau(Plateau p, int x, int y) {
		if (p.getCell(x, y).getDrapeau() == null) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.getItCell().setDrapeau(this);
			this.rang=nbDrapeau.incrementAndGet();
		}
	}
	
	public int getRang() {
		return rang;
	}
	
	public static void resetNbDrapeau() {
		nbDrapeau.set(0);
	}
}	