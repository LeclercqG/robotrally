package fr.soleil.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Robot extends OrientedThing {
	private int respawnX;
	private int respawnY;
	private int dernierDrapeau;
	private int nbrRespawn;
	private int vie;
	
	public Robot(Plateau p, int x, int y, Orientation initialOrientation) {
		if (p.getCell(x, y).getRobot() == null && p.getCell(x, y).getTrou() == null) {
			this.x = x;
			this.y = y;
			this.ori = initialOrientation;
			this.plateau = p;
			this.getItCell().setRobot(this);
			this.respawnX = x;
			this.respawnY = y;
			this.dernierDrapeau = 0;
			this.vie = 5;
			this.nbrRespawn=3;
		}
	}
	
	public int getRespawnX() {
		return respawnX;
	}

	public int getRespawnY() {
		return respawnY;
	}

	public int getDernierDrapeau() {
		return dernierDrapeau;
	}

	public int getNbrRespawn() {
		return nbrRespawn;
	}

	public int getVie() {
		return vie;
	}

	public void turnRight() {
		this.ori = this.ori.next();
	}

	public void turnLeft() {
		this.ori = this.ori.previous();
	}

	public void transferRobotToCell(int x, int y, Orientation ori) {
		this.getItCell().setRobot(null);
		this.x = x;
		this.y = y;
		plateau.getCell(x, y).setRobot(this);
		this.ori=ori;
	}

	public void moveDirection(Orientation o) {
		switch (o) {
			case NORTH:
				transferRobotToCell(this.x, this.y - 1, o);
				break;
			case SOUTH:
				transferRobotToCell(this.x, this.y + 1, o);
				break;
			case WEST:
				transferRobotToCell(this.x - 1, this.y, o);
				break;
			case EAST:
				transferRobotToCell(this.x + 1, this.y, o);
				break;
		}
	}

	public boolean canMove(Orientation o) { // verifie si le robot peut bouger
		Cell cell = this.getItCell();
		if (cell.getMurs().isEmpty()) {
			return true;
		}
		return !cell.hasMurOn(o);
	}

	public void stepForward() {
		if (canMove(this.ori)) {
			if (!Objects.isNull(getNextCell(ori).getRobot())) {
				shiftRobot(this, ori);
			} else {
				moveDirection(ori);
			}
			triggerTrou();
		}
	}
	
	private void triggerTrou() {
		
		if( getItCell().getTrou() != null ) {
			transferRobotToCell(respawnX,respawnY, ori);
			this.vie-=2;
			this.nbrRespawn-=1;
		}
	}
	
	private void triggerDrapeau() {
		Drapeau d;
		if((d = getItCell().getDrapeau()) != null && d.getRang() == dernierDrapeau+1) {
			respawnX = d.getX();
			respawnY= d.getY();
			dernierDrapeau++;
		}
	}

	public void simulate(String string) {
		for (int i = 0; i < string.length(); i++) {
			switch (string.charAt(i)) {
			case 'R':
				turnRight();
				break;
			case 'L':
				turnLeft();
				break;
			case 'F':
				stepForward();
				break;
			default:
				break;
			}
		}
	}

	private boolean shiftRobot(Robot r, Orientation o) {
		Robot robotDevant = r.getNextCell(o).getRobot();
		if (robotDevant != null) {
			if (shiftRobot(robotDevant, o)) { // On avance
				r.moveDirection(o);
				return true;
			} 
			else { // On avance pas
				return false;
			}
		} else if (!r.canMove(o)) { // On est au bout de la chaine de robot
			return false;// Il y a un obstacle
		}
		r.moveDirection(o);
		return true;
	}
	
	/*private List<Cell> getCellulesDevant() {
		List<Cell> c = new ArrayList<>();
		switch(ori) {
		case NORTH:
			for(int i=y;i>=0;i--) {
				if(plateau.getCell(x, i).hasMurOn(ori)) {
					break;
				}else {
					c.add(plateau.getCell(x, i));
				}
			}
			break;
		case SOUTH:
			for(int i=y;i<plateau.getColumn(x).size();i++) {
				if(plateau.getCell(x, i).hasMurOn(ori)) {
					break;
				}else {
					c.add(plateau.getCell(x, i));
				}
			}
			break;
		case EAST:
			for(int i=x;i<plateau.getLine(y).size();i++) {
				if(plateau.getCell(i, y).hasMurOn(ori)) {
					break;
				}else {
					c.add(plateau.getCell(i, y));
				}
			}
			break;
		case WEST:
			for(int i=y;i>=0;i--) {
				if(plateau.getCell(i, y).hasMurOn(ori)) {
					break;
				}else {
					c.add(plateau.getCell(i, y));
				}
			}
			break;
		}
		return c;
	}
	
	private void triggerLaser() {
		List<Cell> cells = getCellulesDevant();
		for(Cell c:cells) {
			Robot r;
			if((r=c.getRobot()) != null) {
				
			}
			
		}
	}*/
	
	

}

