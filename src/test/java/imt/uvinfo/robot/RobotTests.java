package imt.uvinfo.robot;

import imt.support.exam.Scoring;
import imt.support.exam.Scoring.Points;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(Scoring.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RobotTests {

    @Test
    @Order(1)
    @Points(2)
    void robotsAreCreatedWithAPositionAndOrientation() {
        Orientation initialOrientation = Orientation.NORTH;
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, initialOrientation);

        assertEquals(initialOrientation, robot.orientation());
        assertEquals(initialPosition, robot.position());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(2)
    @Points(1)
    void negativePositionsAreAllowed() {
        Position initialPosition = new Position(-1, -1);
        Orientation initialOrientation = Orientation.SOUTH;
        Robot robot = new Robot(initialPosition, initialOrientation);

        assertEquals(initialOrientation, robot.orientation());
        assertEquals(initialPosition, robot.position());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(3)
    void quickReminderAboutEnums() {
        /* Pas de points ici, juste un petit rappel sur les enum :

         En Java, un type énuméré (par exemple Orientation) est en fait une classe qui a autant d'instances que de
         constantes (Orientation.NORTH etc). Il est possible d'ajouter vos propres méthodes à un enum (mais ce n'est
         qu'une possibilité parmi d'autres). */

        /* La méthode statique values() renvoie un tableau des valeurs de l'enum, dans l'ordre de déclaration: */
        Orientation[] cardinalPoints = Orientation.values();
        assertEquals(4, cardinalPoints.length);

        /* La méthode ordinal() renvoie l'indice correspondant: */
        assertEquals(0, Orientation.NORTH.ordinal());
        assertEquals(Orientation.NORTH, cardinalPoints[0]);
        assertEquals(1, Orientation.EAST.ordinal());
        assertEquals(Orientation.EAST, cardinalPoints[1]);
        assertEquals(2, Orientation.SOUTH.ordinal());
        assertEquals(Orientation.SOUTH, cardinalPoints[2]);
        assertEquals(3, Orientation.WEST.ordinal());
        assertEquals(Orientation.WEST, cardinalPoints[3]);

        //fail("Supprimez cette ligne après lecture de ce test.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(4)
    @Points(2)
    void changesTheDirectionFromNorthToEast() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.NORTH);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.EAST;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(5)
    @Points(1)
    void changesTheDirectionFromEastToSouth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.EAST);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.SOUTH;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(6)
    @Points(1)
    void changesTheDirectionFromSouthToWest() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.SOUTH);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.WEST;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(7)
    @Points(1)
    void changesTheDirectionFromWestToNorth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.WEST);

        robot.turnRight();

        Orientation expectedOrientation = Orientation.NORTH;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(8)
    @Points(2)
    void changesTheDirectionFromNorthToWest() {
//        Position initialPosition = new Position(0, 0);
//        Robot robot = new Robot(initialPosition, Orientation.NORTH);
//
//        robot.turnLeft();
//
//        Orientation expectedOrientation = Orientation.WEST;
//        assertEquals(initialPosition, robot.position());
//        assertEquals(expectedOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(8)
    @Points(1)
    void changesTheDirectionFromWestToSouth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.WEST);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.SOUTH;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(9)
    @Points(1)
    void changesTheDirectionFromSouthToEast() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.SOUTH);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.EAST;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    //@Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(10)
    @Points(1)
    void changesTheDirectionFromEastToNorth() {
        Position initialPosition = new Position(0, 0);
        Robot robot = new Robot(initialPosition, Orientation.EAST);

        robot.turnLeft();

        Orientation expectedOrientation = Orientation.NORTH;
        assertEquals(initialPosition, robot.position());
        assertEquals(expectedOrientation, robot.orientation());
        //fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(11)
    @Points(2)
    void increasesTheYCoordinateByOneWhenFacingNorth() {
//        Orientation initialOrientation = Orientation.NORTH;
//        Robot robot = new Robot(new Position(0, 0), initialOrientation);
//
//        robot.stepForward();
//
//        Position expectedPosition = new Position(0, 1);
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(initialOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(12)
    @Points(1)
    void decreasesTheYCoordinateByOneWhenFacingSouth() {
//        Orientation initialOrientation = Orientation.SOUTH;
//        Robot robot = new Robot(new Position(0, 0), initialOrientation);
//
//        robot.stepForward();
//
//        Position expectedPosition = new Position(0, -1);
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(initialOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(13)
    @Points(1)
    void increasesTheXCoordinateByOneWhenFacingEast() {
//        Orientation initialOrientation = Orientation.EAST;
//        Robot robot = new Robot(new Position(0, 0), initialOrientation);
//
//        robot.stepForward();
//
//        Position expectedPosition = new Position(1, 0);
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(initialOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(14)
    @Points(1)
    void decreasesTheXCoordinateByOneWhenFacingWest() {
//        Orientation initialOrientation = Orientation.WEST;
//        Robot robot = new Robot(new Position(0, 0), initialOrientation);
//
//        robot.stepForward();
//
//        Position expectedPosition = new Position(-1, 0);
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(initialOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(15)
    @Points(1)
    void movingOneRobotHasNoEffectOnAnotherRobot() {
//        Position initialPosition = new Position(42, 51);
//        Robot r2d2 = new Robot(initialPosition, Orientation.NORTH);
//        Robot c3po = new Robot(initialPosition, Orientation.NORTH);
//
//        c3po.stepForward();
//
//        assertNotEquals(initialPosition, c3po.position());
//        assertEquals(initialPosition, r2d2.position());
//        assertEquals(new Position(42, 51), initialPosition);
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(16)
    @Points(2)
    void instructionsStartingNorthAndMoveEastAndNorth() {
//        Robot robot = new Robot(new Position(7, 3), Orientation.NORTH);
//
//        robot.simulate("RAALAL");
//
//        Position expectedPosition = new Position(9, 4);
//        Orientation expectedOrientation = Orientation.WEST;
//
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(expectedOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(17)
    @Points(1)
    void instructionsToMoveWestAndNorth() {
//        Robot robot = new Robot(new Position(0, 0), Orientation.NORTH);
//
//        robot.simulate("LAAARALA");
//
//        Position expectedPosition = new Position(-4, 1);
//        Orientation expectedOrientation = Orientation.WEST;
//
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(expectedOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(18)
    @Points(1)
    void instructionsToMoveWestAndSouth() {
//        Robot robot = new Robot(new Position(2, -7), Orientation.EAST);
//
//        robot.simulate("RRAAAAALA");
//
//        Position expectedPosition = new Position(-3, -8);
//        Orientation expectedOrientation = Orientation.SOUTH;
//
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(expectedOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(18)
    @Points(1)
    void instructionsStartingSouthAndMoveEastAndNorth() {
//        Robot robot = new Robot(new Position(8, 4), Orientation.SOUTH);
//
//        robot.simulate("LAAARRRALLLL");
//
//        Position expectedPosition = new Position(11, 5);
//        Orientation expectedOrientation = Orientation.NORTH;
//
//        assertEquals(expectedPosition, robot.position());
//        assertEquals(expectedOrientation, robot.orientation());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(19)
    @Points(1)
    void creatingThingRequiresPosition() {
//        Position position = new Position(42, 51);
//        Thing thing = new Thing(position);
//
//        assertEquals(position, thing.position());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(20)
    @Points(1)
    void creatingEmptyWorldAndLookingAtSomePosition() {
//        World world = new World();
//        Optional<Thing> nothing = world.at(new Position(42, 51));
//
//        assertEquals(Optional.empty(), nothing);
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(21)
    @Points(2)
    void addThingToWorldAndRetrieveIt() {
//        Position position = new Position(42, 51);
//        Thing thing = new Thing(position);
//
//        World world = new World();
//        thing.addTo(world);
//        Optional<Thing> something = world.at(position);
//
//        assertEquals(Optional.of(thing), something);
//        assertSame(thing, something.orElseThrow());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(22)
    @Points(1)
    void robotsAreAKindOfThing() {
//        Position position = new Position(0, 0);
//        Thing thing = new Robot(position, Orientation.NORTH);
//
//        World world = new World();
//        thing.addTo(world);
//
//        assertSame(thing, world.at(position).orElseThrow());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(23)
    @Points(2)
    void afterMovingRobotFindItAtNewPositionInWorld() {
//        Position position = new Position(42, 51);
//        Robot robot = new Robot(position, Orientation.NORTH);
//
//        World world = new World();
//        robot.addTo(world);
//        robot.stepForward();
//
//        assertSame(robot, world.at(new Position(42, 52)).orElseThrow());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(24)
    @Points(2)
    void robotSensesThingJustInFrontOfItself() {
//        Robot r2d2 = new Robot(new Position(0, 0), Orientation.NORTH);
//        Robot c3po = new Robot(new Position(2, 0), Orientation.WEST);
//        Thing something = new Thing(new Position(0, 1));
//        World world = new World();
//        r2d2.addTo(world);
//        c3po.addTo(world);
//        something.addTo(world);
//
//        Optional<Thing> sensedByR2D2 = r2d2.senseForward();
//        Optional<Thing> sensedByC3PO = c3po.senseForward();
//
//        assertEquals(Optional.of(something), sensedByR2D2);
//        assertEquals(Optional.empty(), sensedByC3PO);
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(24)
    @Points(1)
    void obstaclesAreThings() {
//        Position position = new Position(0, 0);
//        Thing thing = new Obstacle(position);
//
//        World world = new World();
//        thing.addTo(world);
//
//        assertSame(thing, world.at(position).orElseThrow());
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(25)
    @Points(2)
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
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(26)
    @Points(2)
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
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(27)
    @Points(2)
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
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }

    @Disabled("Supprimez cette annotation une fois les tests précédents validés.")
    @Test
    @Order(28)
    @Points(1)
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
        fail("Décommentez le corps du test et supprimez cette ligne.");
    }
}
