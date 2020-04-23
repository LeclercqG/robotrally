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

		case SOUTH:
			moveContenu(this.pos.x, this.pos.y+1);

		case WEST:
			moveContenu(this.pos.x-1, this.pos.y);

		case EAST:
			moveContenu(this.pos.x+1, this.pos.y);

		}
	}
	

	public void stepForward() {

		ArrayList<Thing> devant = senseForward();
		if(checkObstacleInList(devant) !=null) {
			return;
		}else if(checkRobotInList(devant)!= null) {
			boolean resultShift = shiftRobot(checkRobotInList(devant), this.ori);
			if(resultShift) {
				moveDirection(this.ori);
			}
		}else {
			moveDirection(this.ori);
			
		}
		
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

	public ArrayList<Thing> senseForward() {
		switch (this.ori) {
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

	public Obstacle checkObstacleInList(ArrayList<Thing> list) {
		for(Thing t : list) {
			if(t instanceof Obstacle) {
				return (Obstacle) t;
			}
		}
		return null;
	}
	

	public boolean shiftRobot(Robot r, Orientation o) {
		Robot devant = checkRobotInList(r.senseForward());
		if(devant != null) {
			boolean statutDevant = shiftRobot(devant, o);
			if(statutDevant) { //Devant dit ok on avance
				r.moveDirection(o);
				return true;
			}else { //Devant dit stop on avance pas
				return false;
			}
		}else { //On est au bout de la chaine de robot
			if(checkObstacleInList(this.senseForward()) != null) { //Il y a un obstacle
				return false;
			}else {
				r.moveDirection(o);
				return true;
			}

		}
	}


}