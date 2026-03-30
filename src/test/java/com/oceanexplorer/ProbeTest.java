package com.oceanexplorer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbeTest {

    @Test
    void shouldCreateProbeWithInitialPosition() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(1, 2, Direction.NORTH, grid);

        assertEquals(1, probe.getX());
        assertEquals(2, probe.getY());
    }

    @Test
    void shouldHaveInitialDirection() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.EAST, grid);

        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldTurnLeftFromNorthToWest() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.turnLeft();

        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void shouldTurnRightFromNorthToEast() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.turnRight();

        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldMoveForwardWhenFacingNorth() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(1, 2, Direction.NORTH, grid);

        probe.moveForward();

        assertEquals(1, probe.getX());
        assertEquals(3, probe.getY());
    }

    @Test
    void shouldMoveBackwardWhenFacingNorth() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(1, 2, Direction.NORTH, grid);

        probe.moveBackward();

        assertEquals(1, probe.getX());
        assertEquals(1, probe.getY());
    }

    @Test
    void shouldNotMoveOutsideGrid() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.SOUTH, grid);

        probe.moveForward();

        assertEquals(0, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void shouldNotMoveIntoObstacle() {
        OceanGrid grid = new OceanGrid(5, 5);
        grid.addObstacle(0, 1);

        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.moveForward();

        assertEquals(0, probe.getX());
        assertEquals(0, probe.getY());
    }

    @Test
    void shouldTrackVisitedCoordinates() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.moveForward();
        probe.moveForward();

        String visited = probe.getVisitedCoordinates();

        assertEquals("(0,0)->(0,1)->(0,2)", visited);
    }

    @Test
    void shouldExecuteMultipleCommands() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.execute("FFRFF");

        assertEquals(2, probe.getX());
        assertEquals(2, probe.getY());
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldThrowExceptionWhenCommandsAreNull() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        assertThrows(IllegalArgumentException.class, () -> {
            probe.execute(null);
        });
    }

    @Test
    void shouldDoNothingWhenCommandsAreEmpty() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.execute("");

        assertEquals(0, probe.getX());
        assertEquals(0, probe.getY());
        assertEquals(Direction.NORTH, probe.getDirection());
        assertEquals("(0,0)", probe.getVisitedCoordinates());
    }

    @Test
    void shouldExecuteLowercaseCommands() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.execute("ffr");

        assertEquals(0, probe.getX());
        assertEquals(2, probe.getY());
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldIgnoreSpacesInCommands() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.execute("F F R F");

        assertEquals(1, probe.getX());
        assertEquals(2, probe.getY());
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldIgnoreTabsAndNewLinesInCommands() {
        OceanGrid grid = new OceanGrid(5, 5);
        Probe probe = new Probe(0, 0, Direction.NORTH, grid);

        probe.execute("F\tF\nR F");

        assertEquals(1, probe.getX());
        assertEquals(2, probe.getY());
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void shouldThrowExceptionWhenDirectionIsNull() {
        OceanGrid grid = new OceanGrid(5, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            new Probe(0, 0, null, grid);
        });
    }

    @Test
    void shouldThrowExceptionWhenGridIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Probe(0, 0, Direction.NORTH, null);
        });
    }

    @Test
    void shouldThrowExceptionWhenStartingPositionIsOutsideGrid() {
        OceanGrid grid = new OceanGrid(5, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            new Probe(5, 5, Direction.NORTH, grid);
        });
    }

    @Test
    void shouldThrowExceptionWhenStartingPositionHasObstacle() {
        OceanGrid grid = new OceanGrid(5, 5);
        grid.addObstacle(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            new Probe(0, 0, Direction.NORTH, grid);
        });
    }
}