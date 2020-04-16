package fr.soleil.robot;

import java.util.ArrayList;
import java.util.Optional;

public class Robot extends Thing{
	private Orientation ori;
	
	public Robot(Position initialPosition, Orientation initialOrientation) {
		super(initialPosition);
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
		switch (this.ori) {
			case NORTH:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x,this.pos.y-1);
				this.pos.getContenu().add(this);
				break;
			case SOUTH:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x,this.pos.y+1);
				this.pos.getContenu().add(this);
				break;
			case WEST:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x-1,this.pos.y);
				this.pos.getContenu().add(this);
				break;
			case EAST:
				this.pos.getContenu().remove(this);
				this.pos=new Position(this.pos.x+1,this.pos.y);
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

	public Optional<ArrayList<Thing>> senseForward() {
		Position temppos;
				switch (this.ori) {
				case NORTH:
					temppos=new Position(this.pos.x,this.pos.y-1);
					return Optional.of(temppos.getContenu());
				case SOUTH:
					temppos=new Position(this.pos.x,this.pos.y+1);
					return Optional.of(temppos.getContenu());
				case WEST:
					temppos=new Position(this.pos.x-1,this.pos.y);
					return Optional.of(temppos.getContenu());
				case EAST:
					temppos=new Position(this.pos.x+1,this.pos.y);
					return Optional.of(temppos.getContenu());			
				}
				return Optional.empty();
	}
}

