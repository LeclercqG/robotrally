package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Icell {

	private List<Mur> murs;
	private Robot robot;
	boolean cellNotInGame;
	
	public Cell(boolean notInGame) {
		cellNotInGame = notInGame;
	}
	
	public Cell() {
		murs = new ArrayList<Mur>();
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
