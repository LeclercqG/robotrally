package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

	// Liste de lignes qui contient une liste de colonnes qui contient des cellules
	private List<List<Cell>> myPlateau;

	// add contstructor
	public Plateau(int x, int y) {

		// /!\ creation plateau vide
		myPlateau = new ArrayList<>();

		// creation de x colonnes
		for (int i = 0; i < x; i++) {
			List<Cell> tmpLine = new ArrayList<>();
			// creation de y ligne
			for (int j = 0; j < y; j++) {
				tmpLine.add(new Cell());
			}
			myPlateau.add(tmpLine);
		}
	}

	public List<Cell> getLine(int y) {
		List<Cell> myLine = new ArrayList<>();
		for (List<Cell> column : myPlateau) {
			myLine.add(column.get(y));
		}
		return myLine;
	}

	public List<Cell> getColumn(int x) {
		return myPlateau.get(x);
	}

	public Cell getCell(int x, int y) {
		return myPlateau.get(x).get(y);
	}
	
	public String afficherPlateau() {
		StringBuilder retour = new StringBuilder(200);
		for(int i=0;i<getColumn(0).size();i++) {
			List<Cell> ligne = getLine(i);
			retour.append("----------------------------------------\n");
			for(Cell c : ligne) {
				
				if(c.getRobot() != null) {
					retour.append("|R");
				}else {
					retour.append("| ");
				}
			}
			retour.append("\n");
		}
		retour.append("----------------------------------------\n");
		return retour.toString();
	}
}
