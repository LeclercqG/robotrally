package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Icell {

	private List<Mur> murs;
	private Robot robot;
	private Trou trou;
	private Drapeau drapeau;
	private List<Robot> laserActive;
	private boolean cellNotInGame;
	private Clef clef;
	private TapisRoulant tapisRoulant;
	
	public Cell(boolean notInGame) {
		cellNotInGame = notInGame;
	}

	public Cell() {
		murs = new ArrayList<>();
		laserActive = new ArrayList<>();
	}
	 
	public TapisRoulant getTapisRoulant() {
		return tapisRoulant;
	}

	public void setTapisRoulant(TapisRoulant tapisRoulant) {
		this.tapisRoulant = tapisRoulant;
	}

	public boolean cellNotInGame() {
		return cellNotInGame;
	}
	
	public Clef getClef() {
		return clef;
	}

	public void setClef(Clef clef) {
		this.clef = clef;
	}

	public Drapeau getDrapeau() {
		return drapeau;
	}

	public void setDrapeau(Drapeau drapeau) {
		this.drapeau = drapeau;
	}

	public Trou getTrou() {
		return trou;
	}

	public void setTrou(Trou trou) {
		this.trou = trou;
	}

	public List<Mur> getMurs() {
		return murs;
	}

	public void setMurs(List<Mur> murs) {
		this.murs = murs;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public boolean hasMurOn(Orientation ori) {
		for (Mur mur : murs) {
			if (mur.getOrientation().equals(ori)) {
				return true;
			}
		}
		return false;
	}

	public void addMur(Mur mur) {
		this.murs.add(mur);
	}
	public void deleteActiveLaser(Robot r) {
		this.laserActive.remove(r);
	}
	public void addActiveLaser(Robot r) {
		this.laserActive.add(r);
	}
	
}
