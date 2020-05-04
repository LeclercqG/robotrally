package fr.soleil.robot;

import java.util.Objects;

public class Trou extends Thing {

	public Trou(Plateau p, int x, int y) {
		if(!Objects.isNull(p.getCell(x,y))) {
			this.x = x;
			this.y = y;
			this.plateau = p;
			this.getItCell().setTrou(this);
		}
	}
}
