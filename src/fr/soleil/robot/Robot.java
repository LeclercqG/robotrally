package fr.soleil.robot;

import java.util.Objects;

public class Robot extends Thing{
	
	private Orientation ori;
	private Plateau plateau;

	public Robot(Plateau plateau, int x, int y, Orientation initialOrientation) {
		this.x = x;
		this.y = y;
		this.ori=initialOrientation;
		this.plateau = plateau;
	}

	public Orientation getOrientation() {
		return this.ori;
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
	
	public void moveDirection() {
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
		Cell cell = plateau.getCell(x, y);
		if (cell.getMurs().isEmpty()) {
			return true;
		}
		return !cell.hasMurOn(ori);
	}
		
	

	public void stepForward() {
		if(!canMove(this.ori)) {
			return;
		}
		
		else if(Objects.isNull(getNextCell().getRobot())) {
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
	
	public Cell getNextCell() {
		Cell cell = new Cell(true);
		switch (this.ori) {
		case NORTH:
			if(this.y == 0) {
				break;
			}
			cell = plateau.getCell(x,y-1);
			break;
		case SOUTH:
			if(this.y == (plateau.getColumn(x).size()-1)) {
				break;
			}
			cell = plateau.getCell(x, y+1);
			break;
		case WEST:
			if(this.x == 0) {
				break;
			}
			cell = plateau.getCell(x-1,y);
			break;
		case EAST:
			if(this.y == (plateau.getLine(y).size()-1)) {
				break;
			}
			cell = plateau.getCell(x+1,y);
			break;
		}
		
		return cell;
	}

	public boolean shiftRobot() {
		switch (this.ori) {
		case NORTH:
			if (plateau.getCell(x,y-1).hasMurOn(ori)) {
				break;
			}
			Li
			for (int i = 2; i < plateau.getColumn(x).size(); i++) {
				Cell cell = plateau.getCell(x, y+i);
				if (cell.hasMurOn(ori) || Objects.isNull(cell.getRobot())) {
					
				}
			}
			break;
		case SOUTH:
			if(this.y == (plateau.getColumn(x).size()-1)) {
				break;
			}
			cell = plateau.getCell(x, y+1);
			break;
		case WEST:
			if(this.x == 0) {
				break;
			}
			cell = plateau.getCell(x-1,y);
			break;
		case EAST:
			if(this.y == (plateau.getLine(y).size()-1)) {
				break;
			}
			cell = plateau.getCell(x+1,y);
			break;
		}
		
//		Robot robotDevant = checkRobotInList(r.senseDirection(o));
//		if(robotDevant != null) {
//			if(shiftRobot(robotDevant, o)) { //On avance
//				r.moveDirection(o);
//				return true;
//			}
//			else return false;//On avance pas
//		}
//		else //On est au bout de la chaine de robot
//			if(!r.canMove(o)) return false;//Il y a un obstacle
//		r.moveDirection(o);
//		return true;
	}
}