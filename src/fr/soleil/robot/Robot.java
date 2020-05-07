package fr.soleil.robot;

import java.util.Objects;

public class Robot extends OrientedThing {
	private int respawnX;
	private int respawnY;
	private int dernierDrapeau;
	private int nbrRespawn;
	private int pionsDegats;
	private boolean horsTension;

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
			this.pionsDegats = 0;
			this.nbrRespawn=3;
			this.horsTension=false;
		}
	}

	public boolean isHorsTension() {
		return horsTension;
	}

	public void miseHorsTension() {
		this.pionsDegats=0;
		this.horsTension = true;
	}

	public void miseSousTension() {
		this.horsTension = false;
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

	public int getPionsDegats() {
		return pionsDegats;
	}

	public void turnRight() {
		this.ori = this.ori.next();
	}

	public void turnLeft() {
		this.ori = this.ori.previous();
	}

	private void transferRobotToCell(int x, int y, Orientation ori) {
		this.getItCell().setRobot(null);
		this.x = x;
		this.y = y;
		plateau.getCell(x, y).setRobot(this);
		this.ori=ori;
	}

	private void moveDirection(Orientation o) {
		if (!this.getNextCell(o).cellNotInGame()) {
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
		}else {
			respawn();
		}
	}

	private boolean canMove(Orientation o) { // verifie si le robot peut bouger
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

	private void respawn() {
		if (nbrRespawn!=0) {
			transferRobotToCell(respawnX,respawnY, ori);
			this.pionsDegats=2;
			nbrRespawn--;
		}
	}

	private void triggerTrou() {
		if( getItCell().getTrou() != null) {
			respawn();
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

	private void triggerClef() {
		if((getItCell().getClef()) != null && this.pionsDegats>0) {
			this.pionsDegats--;
		}
	}

	public void simulate(String string) {
		if(!this.isHorsTension()) {
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
		//TODO Deplacer a la fin du mouvement de tout les robots
		triggerDrapeau();
		triggerClef();
		triggerLaser();
		triggerTapisRoulant();
	}

	private void triggerTapisRoulant() {
		Treadmill tapisRoulant= getItCell().getTapisRoulant();
		if((tapisRoulant) != null && getItCell().getTapisRoulant().isAngle() ) {
			moveDirection(tapisRoulant.ori);
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

	private Robot getRobotEnVue() {
		Robot r = null;
		switch(ori) {
		case NORTH:
			for(int i=y;i>=0;i--) {
				if(plateau.getCell(x, i).hasMurOn(ori)) {
					break;
				}else if(plateau.getCell(x, i).getRobot() != null) {
					r = plateau.getCell(x, i).getRobot();
					break;
				}
			}
			break;
		case SOUTH:
			for(int i=y;i<plateau.getColumn(x).size();i++) {
				if(plateau.getCell(x, i).hasMurOn(ori)) {
					break;
				}else if(plateau.getCell(x, i).getRobot() != null) {
					r = plateau.getCell(x, i).getRobot();
					break;
				}
			}
			break;
		case EAST:
			for(int i=x;i<plateau.getLine(y).size();i++) {
				if(plateau.getCell(i, y).hasMurOn(ori)) {
					break;
				}else if(plateau.getCell(i, y).getRobot() != null) {
					r = plateau.getCell(i, y).getRobot();
					break;
				}
			}
			break;
		case WEST:
			for(int i=y;i>=0;i--) {
				if(plateau.getCell(i, y).hasMurOn(ori)) {
					break;
				}else if(plateau.getCell(i, y).getRobot() != null) {
					r = plateau.getCell(i, y).getRobot();
					break;
				}
			}
			break;
		}
		return r;
	}

	private void triggerLaser() {
		Robot victime = getRobotEnVue();
		if(victime != null) {
			if(victime.pionsDegats<10) {
				victime.pionsDegats++;
				System.out.println("PIOU PIOU");
			}
			else {
				victime.respawn();
			}
		}

	}
}





