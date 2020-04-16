package fr.soleil.robot;

import java.util.ArrayList;
import java.util.Optional;

import fr.soleil.robot.Position;

public class Plateau {
	private Position plateau[][];
	private ArrayList<Thing> listThing;
	
	public Plateau(int width, int length) {
		this.plateau=new Position[width][length];
		this.listThing=new ArrayList<>();
		this.init();
	}
	
	public void init() {
		for(int i=0;i<this.plateau.length;i++) {
			for(int j=0;j<this.plateau[i].length;j++) {
				this.plateau[i][j]=new Position(i,j);
			}
		}
	}
	
	public Optional<ArrayList<Thing>> at(Position pos) {
		for(Thing t:listThing) {
			if(t.position().equals(pos))
				return Optional.of(t.position().getContenu());
		}
		return Optional.empty();
	}

	public void ajout(Thing thing) {
		this.listThing.add(thing);
	}

}
