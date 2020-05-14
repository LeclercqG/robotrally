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
		int caracRestants;
		for(int i=0;i<getColumn(0).size();i++) {
			List<Cell> ligne = getLine(i);
			retour.append("---------------------------------------------------------\n");
			for(Cell c : ligne) {
				caracRestants = 6;
				retour.append("|");
				if(c.getRobot() != null) {
					switch(c.getRobot().getOrientation()) {
					case NORTH:
						retour.append("Rn");
						break;
					case SOUTH:
						retour.append("Rs");
						break;
					case EAST:
						retour.append("Re");
						break;
					case WEST:
						retour.append("Ro");
						break;
					}
					
					caracRestants-=2;
				}
				if(c.getClef() != null) {
					retour.append("Cx");
					caracRestants-=2;
				}
				if(c.getDrapeau() != null) {
					retour.append("Dx");
					caracRestants-=2;
				}
				if(c.getTapisRoulant() != null) {
					switch(c.getTapisRoulant().getOrientation()) {
					case NORTH:
						retour.append("an");
						break;
					case SOUTH:
						retour.append("As");
						break;
					case EAST:
						retour.append("Ae");
						break;
					case WEST:
						retour.append("Ao");
						break;
					}
					caracRestants-=2;
				}
				if(c.getTrou() != null) {
					retour.append("Tx");
					caracRestants-=2;
				}
				if(!c.getMurs().isEmpty()) {
					for(Mur m : c.getMurs()) {
						switch(m.getOrientation()) {
						case NORTH:
							retour.append("Mn");
							break;
						case SOUTH:
							retour.append("Ms");
							break;
						case EAST:
							retour.append("Me");
							break;
						case WEST:
							retour.append("Mo");
							break;
						}
					}
					caracRestants-=2;
				}
				if(caracRestants == 6) {
					retour.append("      ");
				}else if (caracRestants == 4) {
					retour.append("    ");
				}else if (caracRestants == 2){
					retour.append("  ");
				}

				retour.append("|");

			}
			retour.append("\n");
		}
		retour.append("---------------------------------------------------------\n");
		return retour.toString();
	}
}
