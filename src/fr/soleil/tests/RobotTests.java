package fr.soleil.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.soleil.robot.Cell;
import fr.soleil.robot.Drapeau;
import fr.soleil.robot.Mur;
import fr.soleil.robot.Orientation;
import fr.soleil.robot.Plateau;
import fr.soleil.robot.Robot;
import fr.soleil.robot.Thing;
import fr.soleil.robot.Trou;

public class RobotTests {
	private static final int plateauX = 60;
	private static final int plateauY = 60;
	private Plateau p;

	@BeforeEach
	void init() {
		p = new Plateau(plateauX, plateauY);
	}

	@Test
	void robotsAreCreatedWithAPositionAndOrientation() {
		Orientation initialOrientation = Orientation.NORTH;
		Cell initialCell = p.getCell(1, 1);
		Robot robot = new Robot(p, 1, 1, initialOrientation);

		assertEquals(initialOrientation, robot.getOrientation());
		assertEquals(initialCell, robot.getItCell());
	}

	@Test
	void changesTheDirectionFromNorthToEast() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.NORTH);

		robot.turnRight();

		Orientation expectedOrientation = Orientation.EAST;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());

	}

	@Test
	void changesTheDirectionFromEastToSouth() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.EAST);

		robot.turnRight();

		Orientation expectedOrientation = Orientation.SOUTH;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void changesTheDirectionFromSouthToWest() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.SOUTH);

		robot.turnRight();

		Orientation expectedOrientation = Orientation.WEST;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void changesTheDirectionFromWestToNorth() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.WEST);

		robot.turnRight();

		Orientation expectedOrientation = Orientation.NORTH;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void changesTheDirectionFromNorthToWest() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.NORTH);

		robot.turnLeft();

		Orientation expectedOrientation = Orientation.WEST;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void changesTheDirectionFromWestToSouth() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.WEST);

		robot.turnLeft();

		Orientation expectedOrientation = Orientation.SOUTH;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void changesTheDirectionFromSouthToEast() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.SOUTH);

		robot.turnLeft();

		Orientation expectedOrientation = Orientation.EAST;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void changesTheDirectionFromEastToNorth() {
		Cell initialPosition = p.getCell(0, 0);
		Robot robot = new Robot(p, 0, 0, Orientation.EAST);

		robot.turnLeft();

		Orientation expectedOrientation = Orientation.NORTH;
		assertEquals(initialPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void decreaseTheYCoordinateByOneWhenFacingNorth() {
		Orientation initialOrientation = Orientation.NORTH;
		Robot robot = new Robot(p, 20, 20, initialOrientation);

		robot.stepForward();

		Cell expectedPosition = p.getCell(20, 19);
		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(initialOrientation, robot.getOrientation());
	}

	@Test
	void increaseTheYCoordinateByOneWhenFacingSouth() {
		Orientation initialOrientation = Orientation.SOUTH;
		Robot robot = new Robot(p, 20, 20, initialOrientation);

		robot.stepForward();

		Cell expectedPosition = p.getCell(20, 21);
		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(initialOrientation, robot.getOrientation());
	}

	@Test
	void increasesTheXCoordinateByOneWhenFacingEast() {
		Orientation initialOrientation = Orientation.EAST;
		Robot robot = new Robot(p, 0, 0, initialOrientation);

		robot.stepForward();

		Cell expectedPosition = p.getCell(1, 0);
		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(initialOrientation, robot.getOrientation());
	}

	@Test
	void decreasesTheXCoordinateByOneWhenFacingWest() {
		Orientation initialOrientation = Orientation.WEST;
		Robot robot = new Robot(p, 20, 20, initialOrientation);

		robot.stepForward();

		Cell expectedPosition = p.getCell(19, 20);
		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(initialOrientation, robot.getOrientation());
	}

	@Test
	void movingOneRobotHasNoEffectOnAnotherRobot() {
		Cell initialPosition = p.getCell(42, 51);
		Robot r2d2 = new Robot(p, 42, 51, Orientation.NORTH);
		Robot c3po = new Robot(p, 40, 40, Orientation.NORTH);

		c3po.stepForward();

		assertNotEquals(initialPosition, c3po.getItCell());
		assertEquals(initialPosition, r2d2.getItCell());
		assertEquals(p.getCell(42, 51), initialPosition);
	}

	@Test
	void instructionsStartingNorthAndMoveEastAndNorth() {
		Robot robot = new Robot(p, 20, 20, Orientation.NORTH);

		robot.simulate("RFFLFL");

		Cell expectedPosition = p.getCell(22, 19);
		Orientation expectedOrientation = Orientation.WEST;

		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void instructionsToMoveWestAndNorth() {
		Robot robot = new Robot(p, 20, 20, Orientation.NORTH);

		robot.simulate("LFFFRFLF");

		Cell expectedPosition = p.getCell(16, 19);
		Orientation expectedOrientation = Orientation.WEST;

		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void instructionsToMoveWestAndSouth() {
		Robot robot = new Robot(p, 20, 20, Orientation.EAST);

		robot.simulate("RRFFFFFLF");

		Cell expectedPosition = p.getCell(15, 21);
		Orientation expectedOrientation = Orientation.SOUTH;

		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void instructionsStartingSouthAndMoveEastAndNorth() {
		Robot robot = new Robot(p, 20, 20, Orientation.SOUTH);

		robot.simulate("LFFFRRRFLLLL");

		Cell expectedPosition = p.getCell(23, 19);
		Orientation expectedOrientation = Orientation.NORTH;

		assertEquals(expectedPosition, robot.getItCell());
		assertEquals(expectedOrientation, robot.getOrientation());
	}

	@Test
	void robotsAreThings() {
		Thing thing = new Robot(p, 0, 0, Orientation.NORTH);

		assertSame(thing, p.getCell(0, 0).getRobot());
	}

	@Test
	void afterMovingRobotFindItAtNewPositionInPlateau() {
		Robot robot = new Robot(p, 42, 51, Orientation.NORTH);

		robot.stepForward();

		assertSame(robot, p.getCell(42, 50).getRobot());
	}

	@Test
	void robotSensesThingJustInFrontOfItself() {
		Orientation oriR2d2 = Orientation.NORTH;
		Orientation oriC3po = Orientation.WEST;
		Robot r2d2 = new Robot(p, 20, 20, oriR2d2);
		Robot c3po = new Robot(p, 2, 0, oriC3po);
		new Mur(p, 20, 19, Orientation.SOUTH);
		List<Mur> sensedByR2D2 = r2d2.getNextCell(oriR2d2).getMurs();
		List<Mur> sensedByC3PO = c3po.getNextCell(oriC3po).getMurs();
		assertFalse(sensedByR2D2.isEmpty());
		assertTrue(sensedByC3PO.isEmpty());
	}

	@Test
	void mursAreThings() {
		Thing thing = new Mur(p, 10, 10, Orientation.NORTH);
		ArrayList<Thing> list = new ArrayList<>();
		list.add(thing);
		assertEquals(list, p.getCell(10, 10).getMurs());
	}

	@Test
	void mursAreCreatedInTheirNeighborWithOppositeOrientation() {
		int murX = 10;
		int murY = 10;
		Orientation oriMur = Orientation.NORTH;
		Mur mur = new Mur(p, murX, murY, oriMur);
		assertSame(true, mur.getNextCell(oriMur).hasMurOn(Orientation.SOUTH));
		assertSame(murX, mur.getNextCell(oriMur).getMurs().get(0).getX());
		assertSame(murY - 1, mur.getNextCell(oriMur).getMurs().get(0).getY());
	}

	@Test
	void murPreventsRobotFromMovingToThatPosition() {
		int wallX = 10;
		int wallY = 10;
		Thing wall = new Mur(p, wallX, wallY, Orientation.WEST);

		Robot r2d2 = new Robot(p, 8, 10, Orientation.EAST);

		r2d2.stepForward();
		assertEquals(p.getCell(9, 10), r2d2.getItCell());

		r2d2.stepForward();
		assertEquals(p.getCell(9, 10), r2d2.getItCell());

		r2d2.turnLeft();
		r2d2.stepForward();

		assertEquals(wallX, wall.getX());
		assertEquals(wallY, wall.getY());
	}

	@Test
	void movingIntoAnotherRobotPushesIt() {
		Robot r2d2 = new Robot(p, 10, 10, Orientation.EAST);
		Robot c3po = new Robot(p, 11, 10, Orientation.NORTH);

		r2d2.stepForward();

		assertEquals(p.getCell(11, 10), r2d2.getItCell());
		assertEquals(p.getCell(12, 10), c3po.getItCell());
	}

	@Test
	void pushingIsTransitive() {
		Robot r2d2 = new Robot(p, 0, 0, Orientation.EAST);
		Robot c3po = new Robot(p, 1, 0, Orientation.NORTH);
		Robot bb8 = new Robot(p, 2, 0, Orientation.WEST);

		r2d2.stepForward();

		assertEquals(p.getCell(1, 0), r2d2.getItCell());
		assertEquals(p.getCell(2, 0), c3po.getItCell());
		assertEquals(p.getCell(3, 0), bb8.getItCell());
	}

	@Test
	void pushingAgainstRobotAgainstMur() {
		int r2d2X = 10;
		int r2d2Y = 10;
		int c3poX = 11;
		int c3poY = 10;
		int wallX = 11;
		int wallY = 10;
		Robot r2d2 = new Robot(p, r2d2X, r2d2Y, Orientation.EAST);
		Robot c3po = new Robot(p, c3poX, c3poY, Orientation.NORTH);
		Mur wall = new Mur(p, wallX, wallY, Orientation.EAST);

		r2d2.stepForward();
		assertTrue(wall.getNextCell(Orientation.EAST).hasMurOn(Orientation.WEST));

		assertEquals(r2d2X, r2d2.getX());
		assertEquals(r2d2Y, r2d2.getY());
		assertEquals(c3poX, c3po.getX());
		assertEquals(c3poY, c3po.getY());
		assertEquals(wallX, wall.getX());
		assertEquals(wallY, wall.getY());
	}

	@Test
	void trousAreThings() {
		Thing thing = new Trou(p, 10, 10);

		assertSame(thing, p.getCell(10, 10).getTrou());
	}

	@Test
	void thingsCannotBeCreatedOnTrou() {
		int x = 0;
		int y = 0;
		Orientation oriWall = Orientation.EAST;
		new Trou(p, x, y);
		new Robot(p, x, y, Orientation.NORTH);
		new Mur(p, x, y, oriWall);

		assertSame(null, p.getCell(x, y).getRobot());
		assertSame(false, p.getCell(x, y).hasMurOn(oriWall));
	}

	/*@Test
	void drapeauAreThings() {
		Thing thing = new Drapeau(p, 10, 10);
		
		assertSame(thing, p.getCell(10, 10).getDrapeau());
	}*/
	
	@Test
	void drapeauHaveRank() {
		Drapeau drapeau1 = new Drapeau(p, 10, 10);
		Drapeau drapeau2= new Drapeau(p, 11, 11);
		
		assertEquals(1, drapeau1.getRang());
		assertEquals(2, drapeau2.getRang());
	}
}
