package com.oceanexplorer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OceanGridTest {

    @Test
    void shouldThrowExceptionWhenWidthIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OceanGrid(0, 5);
        });
    }

    @Test
    void shouldThrowExceptionWhenHeightIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OceanGrid(5, 0);
        });
    }

    @Test
    void shouldThrowExceptionWhenWidthIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OceanGrid(-1, 5);
        });
    }

    @Test
    void shouldThrowExceptionWhenHeightIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OceanGrid(5, -1);
        });
    }

    @Test
    void shouldThrowExceptionWhenAddingObstacleOutsideGrid() {
        OceanGrid grid = new OceanGrid(5, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            grid.addObstacle(5, 5); // outside grid
        });
    }

    @Test
    void shouldAllowAddingSameObstacleOnlyOnce() {
        OceanGrid grid = new OceanGrid(5, 5);

        grid.addObstacle(2, 2);
        grid.addObstacle(2, 2);

        assertEquals(true, grid.hasObstacle(2, 2));
    }
}
