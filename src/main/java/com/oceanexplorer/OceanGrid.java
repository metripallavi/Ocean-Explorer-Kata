package com.oceanexplorer;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the ocean floor grid.
 * Responsible for enforcing boundaries and obstacle checks.
 */
public class OceanGrid {

    private final int width;
    private final int height;
    private final Set<Coordinate> obstacles = new HashSet<>();

    public OceanGrid(int width, int height) {

        // Validate grid dimensions
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than zero");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero");
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Adds an obstacle at the given coordinates.
     */
    public void addObstacle(int x, int y) {

        // Prevent adding obstacle outside grid
        if (!isWithinBounds(x, y)) {
            throw new IllegalArgumentException("Obstacle position is outside the grid");
        }

        obstacles.add(new Coordinate(x, y));
    }

    /**
     * Checks whether the given coordinates are inside grid bounds.
     */
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Checks whether the given coordinates contain an obstacle.
     */
    public boolean hasObstacle(int x, int y) {
        return obstacles.contains(new Coordinate(x, y));
    }
}