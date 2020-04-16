package fr.soleil.tests;

import org.junit.Before;
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
	private Plateau p;
	@BeforeEach
	void init() {
		p = new Plateau(60, 60);
	}

    @Test
    void robotsAreCreatedWithAPositionAndOrientation() {
        Orientation initialOrientation = Orientation.NORTH;
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, initialOrientation);

        assertEquals(initialOrientation, robot.getOrientation());
        assertEquals(initialPosition, robot.getPosition());
    }

    @Test
    void changesTheDirectionFromNorthToEast() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.NORTH);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.EAST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());

    }

    @Test
    void changesTheDirectionFromEastToSouth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.EAST);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.SOUTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromSouthToWest() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.SOUTH);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.WEST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromWestToNorth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.WEST);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.NORTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromNorthToWest() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.NORTH);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.WEST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromWestToSouth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.WEST);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.SOUTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromSouthToEast() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.SOUTH);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.EAST;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void changesTheDirectionFromEastToNorth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(p,initialPosition, Orientation.EAST);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.NORTH;
        assertEquals(initialPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void decreaseTheYCoordinateByOneWhenFacingNorth() {
        Orientation initialOrientation = Orientation.NORTH;
        Robot robot = new Robot(p,new Position(20, 20), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(20, 19);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void increaseTheYCoordinateByOneWhenFacingSouth() {
        Orientation initialOrientation = Orientation.SOUTH;
        Robot robot = new Robot(p,new Position(20, 20), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(20, 21);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void increasesTheXCoordinateByOneWhenFacingEast() {
        Orientation initialOrientation = Orientation.EAST;
        Robot robot = new Robot(p,new Position(0, 0), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(1, 0);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void decreasesTheXCoordinateByOneWhenFacingWest() {
        Orientation initialOrientation = Orientation.WEST;
        Robot robot = new Robot(p,new Position(20, 20), initialOrientation);

        robot.stepForward();

        Position expectedPosition = new Position(19, 20);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(initialOrientation, robot.getOrientation());
    }

    @Test
    void movingOneRobotHasNoEffectOnAnotherRobot() {
        Position initialPosition = new Position(42, 51);
        Robot r2d2 = new Robot(p,initialPosition, Orientation.NORTH);
        Robot c3po = new Robot(p,initialPosition, Orientation.NORTH);

        c3po.stepForward();

        assertNotEquals(initialPosition, c3po.getPosition());
        assertEquals(initialPosition, r2d2.getPosition());
        assertEquals(new Position(42, 51), initialPosition);
    }

    @Test
    void instructionsStartingNorthAndMoveEastAndNorth() {
        Robot robot = new Robot(p,new Position(20, 20), Orientation.NORTH);

        robot.simulate("RFFLFL");

        Position expectedPosition = new Position(22, 19);
        Orientation expectedOrientation = Orientation.WEST;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void instructionsToMoveWestAndNorth() {
        Robot robot = new Robot(p,new Position(20, 20), Orientation.NORTH);

        robot.simulate("LFFFRFLF");

        Position expectedPosition = new Position(16, 19);
        Orientation expectedOrientation = Orientation.WEST;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void instructionsToMoveWestAndSouth() {
        Robot robot = new Robot(p,new Position(20, 20), Orientation.EAST);

        robot.simulate("RRFFFFFLF");

        Position expectedPosition = new Position(15, 21);
        Orientation expectedOrientation = Orientation.SOUTH;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void instructionsStartingSouthAndMoveEastAndNorth() {
        Robot robot = new Robot(p,new Position(20, 20), Orientation.SOUTH);

        robot.simulate("LFFFRRRFLLLL");

        Position expectedPosition = new Position(23, 19);
        Orientation expectedOrientation = Orientation.NORTH;

        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(expectedOrientation, robot.getOrientation());
    }

    @Test
    void creatingThingRequiresPosition() {
        Position position = new Position(42, 51);
        Thing thing = new Thing(p,position);

        assertEquals(position, thing.position());
    }

    @Test
    void creatingEmptyPlateauAndLookingAtSomePosition() {

        Optional<ArrayList<Thing>> nothing = p.at(new Position(42, 51));

        assertEquals(Optional.empty(), nothing);
    }

    @Test
    void addThingToWorldAndRetrieveIt() {
        Position position = new Position(42, 51);
        Thing thing = new Thing(p,position);


        p.ajout(thing);
        Optional<ArrayList<Thing>> something = p.at(position);
        Thing thing2= something.get().get(0);
        
        assertEquals(thing,thing2);
        //assertSame(thing, something.orElseThrow());
    }

    @Test
    void robotsAreAKindOfThing() {
        Position position = new Position(0, 0);
        Thing thing = new Robot(p,position, Orientation.NORTH);

        p.ajout(thing);

        assertSame(thing, p.at(position).get().get(0));
    }

    @Test
    void afterMovingRobotFindItAtNewPositionInPlateau() {
        Position position = new Position(42, 51);
        Robot robot = new Robot(p,position, Orientation.NORTH);

        p.ajout(robot);
        robot.stepForward();

        assertSame(robot, p.at(new Position(42, 50)).get().get(0));
    }

    @Test
    void robotSensesThingJustInFrontOfItself() {
    	
    	Robot r2d2 = new Robot(p,p.getPosition(20, 20), Orientation.NORTH);
        Robot c3po = new Robot(p,p.getPosition(2, 0), Orientation.WEST);
        Thing something = new Thing(p,p.getPosition(20, 19));
        
        ArrayList<Thing> list2019 = new ArrayList<>();
        list2019.add(something);
        p.ajout(r2d2);
        p.ajout(c3po);
        p.ajout(something);

        
       Optional<ArrayList<Thing>> sensedByR2D2 = r2d2.senseForward();
       Thing temp = sensedByR2D2.get().get(0);
       Optional<ArrayList<Thing>> sensedByC3PO = c3po.senseForward();

        assertSame(something, temp);
        assertEquals(Optional.empty(), sensedByC3PO);
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
