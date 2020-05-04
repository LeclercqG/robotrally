package fr.soleil.robot;

public enum Orientation {
    NORTH, EAST, SOUTH, WEST;
    public Orientation next() {
        return values()[(ordinal() + 1) % Orientation.values().length];
    }
    public Orientation previous() {
        return values()[(ordinal() + 3) % Orientation.values().length];
    }
    
    public static boolean northSouthAxe(Orientation ori) { //renvoie l axe sur lequel on est
    	return ori.ordinal() % 2 == 0 ? true : false;
    }
}
