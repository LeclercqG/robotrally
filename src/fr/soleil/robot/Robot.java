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

	public void stepForward() {
		
		ArrayList<Thing> devant = senseForward();
		int i = 0;
		Thing t = null;
		while(i<devant.size()) {
			t = devant.get(i);
			if(t instanceof Obstacle) return;
			i++;
		}
		
		switch (this.ori) {
		case NORTH:
	
			this.pos.getContenu().remove(this);
			this.pos=plateau.getPosition(this.pos.x, this.pos.y-1);
			this.pos.getContenu().add(this);
			break;
		case SOUTH:
			
			this.pos.getContenu().remove(this);
			this.pos=plateau.getPosition(this.pos.x, this.pos.y+1);
			this.pos.getContenu().add(this);
			break;
		case WEST:
			
			this.pos.getContenu().remove(this);
			this.pos=plateau.getPosition(this.pos.x-1, this.pos.y);
			this.pos.getContenu().add(this);
			break;
		case EAST:
			
			this.pos.getContenu().remove(this);
			this.pos=plateau.getPosition(this.pos.x+1, this.pos.y);
			this.pos.getContenu().add(this);
			break;
		
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
	
	/*public Robot getRobotInArray(ArrayList<Thing> list) {
		for(Thing t : list) {
			if(t instanceof Robot) {
				return (Robot) t;
			}
		}
		return null;
	}
	
	public Robot hsudq(Robot r) {
		if(stepForward()) {
			return getRobotInArray(senseForward());
		}
	}*/
	
	
}