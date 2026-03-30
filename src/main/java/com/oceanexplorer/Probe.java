package com.oceanexplorer;

/**
 * Represents the submersible probe.
 * Responsible for maintaining position, direction,
 * movement behavior, obstacle avoidance,
 * and tracking visited coordinates.
 */
public class Probe {

    private int x;
    private int y;
    private Direction direction;
    private final OceanGrid grid;

    // Tracks visited coordinates
    private final StringBuilder visitedPath = new StringBuilder();

    /**
     * Initializes the probe with position, direction, and grid constraints.
     */
    public Probe(int x, int y, Direction direction, OceanGrid grid) {

        // STEP 16b: Prevent null direction
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        // STEP 17: Prevent null grid
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null");
        }

        // STEP 18: Validate starting position within grid
        if (!grid.isWithinBounds(x, y)) {
            throw new IllegalArgumentException("Starting position is outside the grid");
        }

        // STEP 20: Prevent starting on obstacle
        if (grid.hasObstacle(x, y)) {
            throw new IllegalArgumentException("Starting position contains an obstacle");
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;

        // Record starting position
        visitedPath.append("(").append(x).append(",").append(y).append(")");
    }

    /**
     * Turns the probe left (anti-clockwise).
     */
    public void turnLeft() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.NORTH;
        }
    }

    /**
     * Turns the probe right (clockwise).
     */
    public void turnRight() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }

    /**
     * Moves the probe by the given delta if the target position is valid.
     */
    private void move(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        if (grid.isWithinBounds(newX, newY) && !grid.hasObstacle(newX, newY)) {
            x = newX;
            y = newY;
            visitedPath.append("->(").append(x).append(",").append(y).append(")");
        }
    }

    /**
     * Moves the probe forward based on current direction.
     */
    public void moveForward() {
        switch (direction) {
            case NORTH -> move(0, 1);
            case EAST -> move(1, 0);
            case SOUTH -> move(0, -1);
            case WEST -> move(-1, 0);
        }
    }

    /**
     * Moves the probe backward based on current direction.
     */
    public void moveBackward() {
        switch (direction) {
            case NORTH -> move(0, -1);
            case EAST -> move(-1, 0);
            case SOUTH -> move(0, 1);
            case WEST -> move(1, 0);
        }
    }

    /**
     * Executes a sequence of commands.
     * F = forward, B = backward, L = turn left, R = turn right
     * Whitespace is ignored and lowercase is supported.
     */
    public void execute(String commands) {

        // STEP 11: Guard against null input
        if (commands == null) {
            throw new IllegalArgumentException("Commands cannot be null");
        }

        // STEP 19: Support lowercase commands
        for (char command : commands.toUpperCase().toCharArray()) {

            // STEP 15: Ignore all whitespace
            if (Character.isWhitespace(command)) {
                continue;
            }

            processCommand(command);
        }
    }

    /**
     * Processes a single command.
     */
    private void processCommand(char command) {
        switch (command) {
            case 'F' -> moveForward();
            case 'B' -> moveBackward();
            case 'L' -> turnLeft();
            case 'R' -> turnRight();
            default -> throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    /**
     * Returns all visited coordinates.
     */
    public String getVisitedCoordinates() {
        return visitedPath.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}