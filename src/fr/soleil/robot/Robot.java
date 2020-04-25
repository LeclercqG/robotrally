package fr.soleil.robot;

import java.util.ArrayList;

public class Robot extends Thing{
	private Orientation ori;

	public Robot(Plateau plateau, Position initialPosition, Orientation initialOrientation) {
		super(plateau, initialPosition);
		this.ori=initialOrientation;
	}

	public Orientation getOrientation() {
		return this.ori;
	}

	public Position getPosition() {
		return this.pos;
	}

	public void turnRight() {
		this.ori=this.ori.next();
	}

	public void turnLeft() {
		this.ori=this.ori.previous();
	}

	public void moveContenu(int x, int y) {
		this.pos.getContenu().remove(this);
		this.pos=plateau.getPosition(x, y);
		this.pos.getContenu().add(this);
	}
	
	public void moveDirection(Orientation o) {
		switch (o) {
		case NORTH:
			moveContenu(this.pos.x, this.pos.y-1);
			break;
		case SOUTH:
			moveContenu(this.pos.x, this.pos.y+1);
			break;	
		case WEST:
			moveContenu(this.pos.x-1, this.pos.y);
			break;
		case EAST:
			moveContenu(this.pos.x+1, this.pos.y);
			break;
		}
	}
	
	public boolean canMove(Orientation o) { //verifie si le robot peut bouger
		
		Mur oriDevant=checkMurInList(senseDirection(o));
		Mur oriIci=checkMurInList(getPosition().getContenu());
		
		
		if((oriDevant != null && oriDevant.getOrientation()==o.next().next()) ||  (oriIci != null && oriIci.getOrientation()==o))
			return false;
		return true;
	}

	public void stepForward() {
		if(!canMove(this.ori)) {
			return;
		}
		else if(checkRobotInList(senseDirection(this.ori)) != null) {
				shiftRobot(this, this.ori);
			}
		else moveDirection(this.ori);	
	}


	public void simulate(String string) {
		for(int i=0;i<string.length();i++)
			switch(string.charAt(i)) {
			case 'R':
				turnRight();
				break;
			case 'L':
				turnLeft();
				break;
			case 'F':
				stepForward();
				break;
			}
	}
	
	public ArrayList<Thing> senseDirection(Orientation o) {
		switch (o) {
		case NORTH:
			return plateau.at(plateau.getPosition(this.pos.x,this.pos.y-1));
		case SOUTH:
			return plateau.at(plateau.getPosition(this.pos.x,this.pos.y+1));
		case WEST:
			return plateau.at(plateau.getPosition(this.pos.x-1,this.pos.y));
		case EAST:
			return plateau.at(plateau.getPosition(this.pos.x+1,this.pos.y));	
		}
		return new ArrayList<Thing>();
	}

	public Robot checkRobotInList(ArrayList<Thing> list) {
		for(Thing t : list) {
			if(t instanceof Robot) {
				return (Robot) t;
			}
		}
		return null;
	}

	public Mur checkMurInList(ArrayList<Thing> list) {
		for(Thing t : list) {
			if(t instanceof Mur) {
				return (Mur) t;
			}
		}
		return null;
	}

	public boolean shiftRobot(Robot r, Orientation o) {
		Robot robotDevant = checkRobotInList(r.senseDirection(o));
		if(robotDevant != null) {
			if(shiftRobot(robotDevant, o)) { //On avance
				r.moveDirection(o);
				return true;
			}
			else return false;//On avance pas
		}
		else //On est au bout de la chaine de robot
			if(!r.canMove(o)) return false;//Il y a un obstacle
		r.moveDirection(o);
		return true;
	}
}