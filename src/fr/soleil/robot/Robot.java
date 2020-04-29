package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Robot extends OrientedThing{
	
	public Robot(Plateau plateau, int x, int y, Orientation initialOrientation) {
		this.x = x;
		this.y = y;
		this.ori=initialOrientation;
		this.plateau = plateau;
		this.plateau.getCell(x, y).setRobot(this);
	}

	public Orientation getOrientation() {
		return this.ori;
	}
	
	public Cell getPosition() {
		return plateau.getCell(x,y);
	}

	public void turnRight() {
		this.ori=this.ori.next();
	}

	public void turnLeft() {
		this.ori=this.ori.previous();
	}

	public void moveContenu(int x, int y) {

		plateau.getCell(this.x, this.y).setRobot(null);
		this.x = x;
		this.y = y;
		plateau.getCell(x,y).setRobot(this);
	}
	
	public void moveDirection() {
		switch (ori) {
		case NORTH:
			moveContenu(this.x, this.y-1);
			break;
		case SOUTH:
			moveContenu(this.x, this.y+1);
			break;	
		case WEST:
			moveContenu(this.x-1, this.y);
			break;
		case EAST:
			moveContenu(this.x+1, this.y);
			break;
		}
	}
	
	public boolean canMove(Orientation o) { //verifie si le robot peut bouger
		Cell cell = plateau.getCell(x, y);
		if (Objects.isNull(cell.getMurs())) {
			return true;
		}
		return !cell.hasMurOn(o);
	}
		
	

	public void stepForward() {
		if(canMove(this.ori)) {	
			if(!Objects.isNull(getNextCell().getRobot())) {
				shiftRobot();
			}
		else moveDirection();
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
	
	public void stepForwardRobotList(List <Robot> robotList) {
		for (Robot r : robotList) { //Pour chaque robot de la liste, on avance
			r.stepForward();
		}
	}
	
	public void northShift (){
		List <Robot> robotList = new ArrayList<>();
		boolean murDetected = false;
		for (int i = y; i > 0; i--) {
			Cell cell = plateau.getCell(x, y-i);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot à la liste
				robotList.add(cell.getRobot());
			}
		}
		if(!murDetected) {
			stepForwardRobotList(robotList);
		} 
	}
	
	public void southShift (){
		List <Robot> robotList = new ArrayList<>();
		boolean murDetected = false;
		for (int i = y; i < plateau.getColumn(x).size()-1; i++) {
			Cell cell = plateau.getCell(x, y+i);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot à la liste
				robotList.add(cell.getRobot());
			}
		}
		if(!murDetected) {
			stepForwardRobotList(robotList);
		}
	}
	
	public void westShift (){
		List <Robot> robotList = new ArrayList<>();
		boolean murDetected = false;
		for (int i = x; i > 0; i--) {
			Cell cell = plateau.getCell(x-i, y);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot à la liste
				robotList.add(cell.getRobot());
			}
		}
		if(!murDetected) {
			stepForwardRobotList(robotList);
		}
	}
	
	public void eastShift (){
		List <Robot> robotList = new ArrayList<>();
		boolean murDetected = false;
		for (int i = x; i < plateau.getColumn(y).size(); i++) {
			Cell cell = plateau.getCell(x+i, y);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot à la liste
				robotList.add(cell.getRobot());
			}
		}
		if(!murDetected) {
			stepForwardRobotList(robotList);
		}
	}
	

	public void shiftRobot() {
		switch (ori) {
		case NORTH:
			northShift();
			//plateau.getColumn(x).addAll(y-robotList.size(), robotList);
			break;
		case SOUTH:
			southShift();
			break;
		case WEST:
			westShift();
			break;
		case EAST:
			eastShift();
			break;	
			}	
		}

}

