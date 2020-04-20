package fr.soleil.robot;

import java.util.ArrayList;
import java.util.Optional;

import fr.soleil.robot.Position;

public class Plateau {
	private Position plateau[][];
	
	public Plateau(int width, int length) {
		this.plateau=new Position[width][length];
		this.init();
	}
	
	public void init() {
		for(int i=0;i<this.plateau.length;i++) {
			for(int j=0;j<this.plateau[i].length;j++) {
				this.plateau[i][j]=new Position(i,j);
			}
		}
	}
	
	public ArrayList<Thing> at(Position pos) {
		
		return pos.getContenu();
	}

	
	public Position getPosition(int x, int y) {
		return this.plateau[x][y];
	}
	
	public Position[][] getAllPositions(){
		
		return plateau;
	}

}
