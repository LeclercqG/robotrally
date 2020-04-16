package fr.soleil.tests;

import org.junit.jupiter.api.*;

import fr.soleil.robot.Orientation;
import fr.soleil.robot.Position;
import fr.soleil.robot.Robot;
import fr.soleil.robot.Thing;
import fr.soleil.robot.Plateau;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

public class RobotTests {

    @Test
    void robotsAreCreatedWithAPositionAndOrientation() {
        Orientation initialOrientation = Orientation.NORTH;
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, initialOrientation);

        assertEquals(initialOrientation, robot.getOrientation());
        assertEquals(initialPosition, robot.getPosition());
    }

    @Test
    void changesTheDirectionFromNorthToEast() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.NORTH);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.EAST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());

    }

    @Test
    void changesTheDirectionFromEastToSouth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.EAST);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.SOUTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromSouthToWest() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.SOUTH);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.WEST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromWestToNorth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.WEST);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.NORTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromNorthToWest() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.NORTH);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.WEST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromWestToSouth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.WEST);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.SOUTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromSouthToEast() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.SOUTH);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.EAST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromEastToNorth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.EAST);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.NORTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void decreaseTheYCoordinateByOneWhenFacingNorth() {
        Orientation initialOrientation = Orientation.NORTH;
        Robot robot = new Robot(new Position(20, 20), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(20, 19);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void increaseTheYCoordinateByOneWhenFacingSouth() {
        Orientation initialOrientation = Orientation.SOUTH;
        Robot robot = new Robot(new Position(20, 20), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(20, 21);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void increasesTheXCoordinateByOneWhenFacingEast() {
        Orientation initialOrientation = Orientation.EAST;
        Robot robot = new Robot(new Position(0, 0), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(1, 0);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void decreasesTheXCoordinateByOneWhenFacingWest() {
        Orientation initialOrientation = Orientation.WEST;
        Robot robot = new Robot(new Position(0, 0), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(-1, 0);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void movingOneRobotHasNoEffectOnAnotherRobot() {
        Position initialPosition = new Position(42, 51);
        Robot r2d2 = new Robot(initialPosition, Orientation.NORTH);
        Robot c3po = new Robot(initialPosition, Orientation.NORTH);

        c3po.stepForward();

        assertNotEquals(initialPosition, c3po.getPosition());
        assertEquals(initialPosition, r2d2.getPosition());
        assertEquals(new Position(42, 51), initialPosition);
    }

    @Test
    void instructionsStartingNorthAndMoveEastAndNorth() {
        Robot robot = new Robot(new Position(20, 20), Orientation.NORTH);

        robot.simulate("RFFLFL");

        Position expectedPosition = new Position(22, 19);
        Orientation expectedOrientation = Orientation.WEST;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void instructionsToMoveWestAndNorth() {
        Robot robot = new Robot(new Position(20, 20), Orientation.NORTH);

        robot.simulate("LFFFRFLF");

        Position expectedPosition = new Position(16, 19);
        Orientation expectedOrientation = Orientation.WEST;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void instructionsToMoveWestAndSouth() {
        Robot robot = new Robot(new Position(20, 20), Orientation.EAST);

        robot.simulate("RRFFFFFLF");

        Position expectedPosition = new Position(15, 21);
        Orientation expectedOrientation = Orientation.SOUTH;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void instructionsStartingSouthAndMoveEastAndNorth() {
        Robot robot = new Robot(new Position(20, 20), Orientation.SOUTH);

        robot.simulate("LFFFRRRFLLLL");

        Position expectedPosition = new Position(23, 19);
        Orientation expectedOrientation = Orientation.NORTH;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void creatingThingRequiresPosition() {
        Position position = new Position(42, 51);
        Thing thing = new Thing(position);

        assertEquals(position, thing.position());
    }

    @Test
    void creatingEmptyPlateauAndLookingAtSomePosition() {
        Plateau plateau = new Plateau(10,15);
        Optional<ArrayList<Thing>> nothing = plateau.at(new Position(42, 51));

        assertEquals(Optional.empty(), nothing);
    }

    @Test
    void addThingToWorldAndRetrieveIt() {
        Position position = new Position(42, 51);
        Thing thing = new Thing(position);

        Plateau plateau = new Plateau(50, 60);
        plateau.ajout(thing);
        Optional<ArrayList<Thing>> something = plateau.at(position);
        Thing thing2= something.get().get(0);
        
        assertEquals(thing,thing2);
        //assertSame(thing, something.orElseThrow());
    }

    @Test
    void robotsAreAKindOfThing() {
        Position position = new Position(0, 0);
        Thing thing = new Robot(position, Orientation.NORTH);

        Plateau plateau = new Plateau(50,60);
        plateau.ajout(thing);

        assertSame(thing, plateau.at(position).get().get(0));
    }

    @Test
    void afterMovingRobotFindItAtNewPositionInPlateau() {
        Position position = new Position(42, 51);
        Robot robot = new Robot(position, Orientation.NORTH);

        Plateau plateau = new Plateau(50,60);
        plateau.ajout(robot);
        robot.stepForward();

        assertSame(robot, plateau.at(new Position(42, 50)).get().get(0));
    }

    @Test
    void robotSensesThingJustInFrontOfItself() {
        Robot r2d2 = new Robot(new Position(20, 20), Orientation.NORTH);
        Robot c3po = new Robot(new Position(2, 0), Orientation.WEST);
        Thing something = new Thing(new Position(20, 19));
        Plateau plateau = new Plateau(50,60);
        ArrayList<Thing> list2019 = new ArrayList<>();
        list2019.add(something);
        plateau.ajout(r2d2);
        plateau.ajout(c3po);
        plateau.ajout(something);

        
       Optional<ArrayList<Thing>> sensedByR2D2 = r2d2.senseForward();
       Thing temp = sensedByR2D2.get().get(0);
       Optional<ArrayList<Thing>> sensedByC3PO = c3po.senseForward();

        assertSame(something, temp);
        //assertEquals(Optional.empty(), sensedByC3PO);
    }

    @Test
    void obstaclesAreThings() {
//        Position position = new Position(0, 0);
//        Thing thing = new Obstacle(position);
//
//        World world = new World();
//        thing.addTo(world);
//
//        assertSame(thing, world.at(position).orElseThrow());
    }

    @Test
    void obstaclePreventsRobotFromMovingToThatPosition() {
//        Position wallPosition = new Position(2, 0);
//        Thing wall = new Obstacle(wallPosition);
//        Robot r2d2 = new Robot(new Position(0, 0), Orientation.EAST);
//
//        World world = new World();
//        wall.addTo(world);
//        r2d2.addTo(world);
//
//        r2d2.stepForward();
//        assertEquals(new Position(1, 0), r2d2.position());
//
//        r2d2.stepForward();
//        assertEquals(new Position(1, 0), r2d2.position());
//
//        r2d2.turnLeft();
//        r2d2.stepForward();
//        assertEquals(new Position(1, 1), r2d2.position());
//
//        assertEquals(wallPosition, wall.position());
    }
    
    @Test
    void movingIntoAnotherRobotPushesIt() {
//        Robot r2d2 = new Robot(new Position(0, 0), Orientation.EAST);
//        Robot c3po = new Robot(new Position(1, 0), Orientation.NORTH);
//        World world = new World();
//        r2d2.addTo(world);
//        c3po.addTo(world);
//
//        r2d2.stepForward();
//
//        assertEquals(new Position(1, 0), r2d2.position());
//        assertEquals(new Position(2, 0), c3po.position());
    }

    @Test
    void pushingIsTransitive() {
//        Robot r2d2 = new Robot(new Position(0, 0), Orientation.EAST);
//        Robot c3po = new Robot(new Position(1, 0), Orientation.NORTH);
//        Robot bb8 = new Robot(new Position(2, 0), Orientation.WEST);
//
//        World world = new World();
//        r2d2.addTo(world);
//        c3po.addTo(world);
//        bb8.addTo(world);
//
//        r2d2.stepForward();
//
//        assertEquals(new Position(1, 0), r2d2.position());
//        assertEquals(new Position(2, 0), c3po.position());
//        assertEquals(new Position(3, 0), bb8.position());
    }

    @Test
    void pushingAgainstRobotAgainstWall() {
//        Position r2d2Position = new Position(0, 0);
//        Position c3poPosition = new Position(1, 0);
//        Position wallPosition = new Position(2, 0);
//        Robot r2d2 = new Robot(r2d2Position, Orientation.EAST);
//        Robot c3po = new Robot(c3poPosition, Orientation.NORTH);
//        Thing wall = new Obstacle(wallPosition);
//
//        World world = new World();
//        r2d2.addTo(world);
//        c3po.addTo(world);
//        wall.addTo(world);
//
//        r2d2.stepForward();
//
//        assertEquals(r2d2Position, r2d2.position());
//        assertEquals(c3poPosition, c3po.position());
//        assertEquals(wallPosition, wall.position());
    }
}
