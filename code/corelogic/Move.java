/**
 * @file Move.java
 * @brief Defines the Move class representing a series of points in a move chain.
 */

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @class Move
 * @brief Represents a series of points in a move chain.
 *
 * The Move class is used to define and manipulate moves on a game board.
 */
public final class Move implements Serializable {

    /** @brief The move chain representing a series of points. */
    public LinkedList<Point> moveChain = new LinkedList();

    /** @brief Default constructor for the Move class. */
    public Move() {
    }

    /**
     * @brief Constructor to initialize a move with a starting point.
     * @param fromX The x-coordinate of the starting point.
     * @param fromY The y-coordinate of the starting point.
     */
    public Move(int fromX, int fromY) {
        this.moveChain.add(new Point(fromX, fromY));
    }

    /**
     * @brief Constructor to initialize a move with a starting and destination point.
     * @param fromX The x-coordinate of the starting point.
     * @param fromY The y-coordinate of the starting point.
     * @param destinationX The x-coordinate of the destination point.
     * @param destinationY The y-coordinate of the destination point.
     */
    public Move(int fromX, int fromY, int destinationX, int destinationY) {
        this.moveChain.add(new Point(fromX, fromY));
        this.moveChain.add(new Point(destinationX, destinationY));
    }

    /**
     * @brief Constructor to initialize a move with a starting point.
     * @param from The starting point as a Point object.
     */
    public Move(Point from) {
        this.moveChain.add(from);
    }

    /**
     * @brief Constructor to initialize a move with starting and destination points.
     * @param from The starting point as a Point object.
     * @param destination The destination point as a Point object.
     */
    public Move(Point from, Point destination) {
        this.moveChain.add(from);
        this.moveChain.add(destination);
    }

    /** @brief Gets the starting point of the move. */
    public Point getFrom() {
        return this.moveChain.getFirst();
    }

    /** @brief Gets the destination point of the move. */
    public Point getDestination() {
        return this.moveChain.getLast();
    }

    /**
     * @brief Adds a waypoint to the move.
     * @param x The x-coordinate of the waypoint.
     * @param y The y-coordinate of the waypoint.
     */
    public void addWayPoint(int x, int y) {
        this.addWayPoint(new Point(x, y));
    }

    /**
     * @brief Adds a waypoint to the move.
     * @param point The waypoint as a Point object.
     */
    public void addWayPoint(Point point) {
        this.moveChain.add(point);
    }

    /**
     * @brief Compares two Move objects for equality.
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Move move = (Move)o;
        if (!this.getDestination().equals(move.getDestination())) {
            return false;
        }
        return this.getFrom().equals(move.getFrom());
    }

    /**
     * @brief Computes the hash code for the Move object.
     * @return The hash code value.
     */
    public int hashCode() {
        int result = Arrays.hashCode(new int[]{this.getFrom().x, this.getFrom().y});
        result = 31 * result + Arrays.hashCode(new int[]{this.getDestination().x, this.getDestination().y});
        return result;
    }

    /**
     * @brief Generates a string representation of the Move object.
     * @return A string representation of the Move object.
     */
    public String toString() {
        return "Move{(" + this.getFrom().getX() + "," + this.getFrom().getY() +
                ") --> (" + this.getDestination().getX() + "," + this.getDestination().getY() + ")}";
    }

    /**
     * @brief Main method for testing the Move class.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Move temp = new Move(1, 2, 3, 4);
        System.out.println(temp);
    }
}
