package com.sweattypalms.skyblock.dungeons.generator;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum RoomShape {
    /**
     * |X|
     */
    SINGLE(true, new Point(0, 0)),
    /**
     * |X X|
     */
    TWO_X_ONE(new Point(0, 0), new Point(1, 0)),
    /**
     * |X X X|
     */
    THREE_X_ONE(new Point(0, 0), new Point(1, 0), new Point(2, 0)),
    /**
     * |X X X X|
     */
    FOUR_X_ONE(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)),
    /**
     * |X X|
     * |X|
     */
    L_SHAPE_1(new Point(0, 0), new Point(1, 0), new Point(0, 1)),
    /**
     * |X X|
     * |X X|
     */
    SQUARE(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)),
    ;

    private final Point[] points;
    private final boolean avoid;

    RoomShape(Point... points) {
        this.points = points;
        this.avoid = false;
    }

    RoomShape(boolean avoid, Point... points) {
        this.points = points;
        this.avoid = avoid;
    }

    public static Point[] rotatePoints90Degrees(Point[] points) {
        Point[] rotatedPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            int x = point.x(), y = point.y();
            rotatedPoints[i] = new Point(-y, x);
        }
        return rotatedPoints;
    }

    public Point[] rotateWithRotationCount(int rotations) {
        Point[] rotatedPoints = this.points;
        for (int i = 0; i < rotations; i++) {
            rotatedPoints = rotatePoints90Degrees(rotatedPoints);
        }
        return shiftPointsInsideBounds(rotatedPoints);
    }

    private static Point[] shiftPointsInsideBounds(Point[] points) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point point : points) {
            if (point.x() < minX) {
                minX = point.x();
            }
            if (point.y() < minY) {
                minY = point.y();
            }
        }

        Point[] shiftedPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            shiftedPoints[i] = new Point(points[i].x() - minX, points[i].y() - minY);
        }

        return shiftedPoints;
    }

    @Override
    public String toString() {
        return pointsToString(this.points);
    }

    public static String pointsToString(Point[] points) {
        StringBuilder sb = new StringBuilder();
        // Find the width and height of the shape
        int width = 0, height = 0;
        for (Point point : points) {
            width = Math.max(width, point.x());
            height = Math.max(height, point.y());
        }

        // make a 2D array to represent the shape
        char[][] shape = new char[height + 1][width + 1];
        for (char[] row : shape) {
            Arrays.fill(row, ' ');
        }

        // Fill in the shape
        for (Point point : points) {
            shape[point.y()][point.x()] = 'X';
        }

        // Convert the 2D array to a string
        for (char[] row : shape) {
            for (char aChar : row) {
                sb.append(aChar);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static ShapeResult identifyShape(Point[] inputPoints) {
        Point[] normalizedInput = shiftPointsInsideBounds(inputPoints);

        for (RoomShape shape : RoomShape.values()) {
            for (int i = 0; i < 4; i++) {
                Point[] rotatedShape = shape.rotateWithRotationCount(i);
                Point[] normalizedShape = shiftPointsInsideBounds(rotatedShape);
                if (arePointsEqual(normalizedInput, normalizedShape)) {
                    return new ShapeResult(shape, i);
                }
            }
        }
//        return null; // or throw an exception if preferred
        throw new IllegalArgumentException("Shape not identified");
    }

    private static boolean arePointsEqual(Point[] points1, Point[] points2) {
        if (points1.length != points2.length) {
            return false;
        }
        Set<Point> set1 = new HashSet<>(Arrays.asList(points1));
        Set<Point> set2 = new HashSet<>(Arrays.asList(points2));
        return set1.equals(set2);
    }

    public static void main(String[] args) {
/*        for (RoomShape shape : RoomShape.values()) {
            System.out.println("shape = " + shape.name());
            for (int i = 0; i < 4; i++) {
                System.out.println("Rotation " + i + ":");
                System.out.println(pointsToString(shape.rotateWithRotationCount(i)));
            }
            System.out.println("\n\n");
        }*/

        // Test identifyShape
        Point[] testPoints = {new Point(5, 5), new Point(6, 5), new Point(6, 6)};
        ShapeResult result = identifyShape(testPoints);
        System.out.println("Identified shape: " + result.shape().name());
        System.out.println("Rotation count: " + result.rotationCount());
    }

    public record ShapeResult(RoomShape shape, int rotationCount) {}
}
