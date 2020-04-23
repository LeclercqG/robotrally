package fr.soleil.robot;

import java.util.ArrayList;
import java.util.Optional;

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

	public boolean stepForward() {
		
		ArrayList<Thing> devant = senseForward();
		int i = 0;
		Thing t = null;
		while(i<devant.size()) {
			t = devant.get(i);
			if(t instanceof Obstacle) return false;
			//if(t instanceof Robot) transitiveMove( t.pos.getContenu().get(0));
			i++;
		}
		
		switch (this.ori) {
		case NORTH:
			moveContenu(this.pos.x, this.pos.y-1);
			return true;
			
		case SOUTH:
			moveContenu(this.pos.x, this.pos.y+1);
			return true;
			
		case WEST:
			moveContenu(this.pos.x-1, this.pos.y);
			return true;
			
		case EAST:
			moveContenu(this.pos.x+1, this.pos.y);
			return true;
			
		}
		return false;
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
	
	public void transitiveMove(Robot r) {
		ArrayList<Thing> devant = this.senseForward();
		
		if(checkObstacleInList(devant)==null && checkRobotInList(devant) == null) {
			stepForward();
		}else {
			if(checkObstacleInList(devant)!= null) {
				//Il y a un obstacle on fait des trucs
			}else if(checkRobotInList(devant) != null) {
				transitiveMove(checkRobotInList(devant));
			}	
			
		}
	}
	
}