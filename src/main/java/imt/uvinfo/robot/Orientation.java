package imt.uvinfo.robot;

enum Orientation {
    NORTH, EAST, SOUTH, WEST;
    public Orientation next() {
        return values()[(ordinal() + 1) % Orientation.values().length];
    }
    public Orientation previous() {
        return values()[(ordinal() + 3) % Orientation.values().length];
    }
}
