package fr.soleil.robot;

import java.util.ArrayList;

import fr.soleil.robot.Thing;

public class Position {
    final int x, y;
    private ArrayList<Thing> contenu;

    public Position(int x, int y) {
    	if (x>=0)
    		this.x = x;
    	else
    		this.x=0;
    	if (y>=0)
    		this.y=y;
    	else
    		this.y=0;
    	this.contenu=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "("+x+";"+y+")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        else return x == ((Position) obj).x && y == ((Position) obj).y;
    }

	public ArrayList<Thing> getContenu() {
		return this.contenu;
	}
}
