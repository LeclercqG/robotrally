package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;

import fr.soleil.tests.Drapeau;

public class Cell implements Icell {

	private List<Mur> murs;
	private Robot robot;
	private Trou trou;
	private Drapeau drapeau;

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

	private boolean cellNotInGame;

	public Cell(boolean notInGame) {
		cellNotInGame = notInGame;
	}

	public Cell() {
		murs = new ArrayList<>();
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
}
