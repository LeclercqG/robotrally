package fr.soleil.robot;

import java.util.Objects;

public class Robot extends OrientedThing{

	public Robot(Plateau p, int x, int y, Orientation initialOrientation) {
		if(p.getCell(x,y).getRobot()==null && p.getCell(x,y).getTrou()==null) {
			this.x = x;
			this.y = y;
			this.ori=initialOrientation;
			this.plateau = p;
			this.getItCell().setRobot(this);
		}
	}

	public void turnRight() {
		this.ori=this.ori.next();
	}

	public void turnLeft() {
		this.ori=this.ori.previous();
	}

	public void transferRobottToCell(int x, int y) {
		this.getItCell().setRobot(null);
		this.x = x;
		this.y = y;
		plateau.getCell(x,y).setRobot(this);
	}

	public void moveDirection(Orientation o) {
		switch (o) {
		case NORTH:
			transferRobottToCell(this.x, this.y-1);
			break;
		case SOUTH:
			transferRobottToCell(this.x, this.y+1);
			break;	
		case WEST:
			transferRobottToCell(this.x-1, this.y);
			break;
		case EAST:
			transferRobottToCell(this.x+1, this.y);
			break;
		}
	}

	public boolean canMove(Orientation o) { //verifie si le robot peut bouger
		Cell cell = this.getItCell();
		if (cell.getMurs().isEmpty()) {
			return true;
		}
		return !cell.hasMurOn(o);
	}



	public void stepForward() {
		if(canMove(this.ori)) {	
			if(!Objects.isNull(getNextCell(ori).getRobot())) {
				shiftRobot(this, ori);
			}
			else moveDirection(ori);
		}
	}

	public void simulate(String string) {
		for(int i=0;i<string.length();i++) {
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
			default :
				break;
			}
		}
	}

	public boolean shiftRobot(Robot r, Orientation o) {
		Robot robotDevant = r.getNextCell(o).getRobot();
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

