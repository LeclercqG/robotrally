package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Robot extends OrientedThing{

	public Robot(Plateau p, int x, int y, Orientation initialOrientation) {
		if(p.getCell(x,y).getRobot()==null && p.getCell(x,y).getTrou()==null) {
			this.x = x;
			this.y = y;
			this.ori=initialOrientation;
			this.plateau = p;
			this.plateau.getCell(x, y).setRobot(this);
		}
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

	public void moveDirection(Orientation o) {
		switch (o) {
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

	public void northShift (){
		List <Cell> robotsCellList = new ArrayList<>();
		boolean murDetected = false;
		for (int i = y; i > 0; i--) {
			Cell cell = plateau.getCell(x, y-i);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot � la liste
				robotsCellList.add(cell);
			}
		}
		if(!murDetected) {
			List <Cell> column = plateau.getColumn(x);
			column.removeAll(robotsCellList);
			column.addAll(y-robotsCellList.size(), robotsCellList);
		}
	}

	public void southShift (){
		List <Cell> robotsCellList = new ArrayList<>();
		List <Cell> column = plateau.getColumn(x);
		boolean murDetected = false;
		for (int i = y; i < column.size()-1; i++) {
			Cell cell = plateau.getCell(x, y+i);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot � la liste
				robotsCellList.add(cell);
			}
		}
		if(!murDetected) {
			column.removeAll(robotsCellList);
			column.addAll(y+1, robotsCellList);
		}
	}

	public void westShift (){
		List <Cell> robotsCellList = new ArrayList<>();
		//List <Cell> ligne=new Arraylist<>(plateau.getLine(y)));
		boolean murDetected = false;
		for (int i = x; i > 0; i--) {
			Cell cell = plateau.getCell(x-i, y);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot � la liste
				robotsCellList.add(cell);
			}
		}
		if(!murDetected) {
			List <Cell> line = plateau.getLine(y);
			line.removeAll(robotsCellList);
			line.addAll(x-robotsCellList.size(), robotsCellList);
		}
	}

	public void eastShift (){
		List <Robot> robotsList = new ArrayList<>();
		List <Cell> ligne=plateau.getLine(y);
		boolean murDetected = false;
		for (int i = x; i < ligne.size(); i++) {
			Cell cell = plateau.getCell(x+i, y);
			if (cell.hasMurOn(ori)) { //On s'arrete
				murDetected = true;
				break;
			}
			else if (!Objects.isNull(cell.getRobot())){ //on ajoute le robot � la liste
				robotsList.add(cell.getRobot());
			}
		}
		if(!murDetected) {
			for (int i = x; i < robotsList.size(); i++) {
				//robot.getPosition().setRobot(null);
				//robot.getNextCell(ori).setRobot(robot);
			}
			System.out.print("coucou");
		}
	}
	
	/*public void shiftRobot() {
		switch(ori) {
			case NORTH:
				northShift();
				break;
			case EAST:
				eastShift();
				break;
			case SOUTH:
				southShift();
				break;
			case WEST:
				westShift();
				break;
		}
	}*/
	
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

