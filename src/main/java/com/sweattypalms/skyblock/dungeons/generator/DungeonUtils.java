package com.sweattypalms.skyblock.dungeons.generator;

import java.util.*;

public class DungeonUtils {

    private static final int MAX_RECUR_TRIES = 1000;

    public static List<Room> generateMainPath(Random random, Room[][] dungeonGrid, Room start, Room end, int minLength, int maxLength) {
        int totalTries = 1;

        do {
            List<Room> path = new ArrayList<>();

            if (dfs(random, dungeonGrid, start, end, minLength, maxLength, path, new boolean[dungeonGrid.length][dungeonGrid[0].length])) {
                return path;
            }
        } while (totalTries++ < MAX_RECUR_TRIES);

        System.out.println("Path not found after " + MAX_RECUR_TRIES + " tries\tQuitting...");

        return new ArrayList<>(); // Return empty list if no path found
    }

    private static boolean dfs(Random random, Room[][] dungeonGrid, Room current, Room end, int minLength, int maxLength, List<Room> path, boolean[][] visited) {
        if (current.equals(end) && path.size() >= minLength && path.size() <= maxLength) {
            path.add(current);
            return true;
        }

        if (path.size() > maxLength) {
            return false;
        }

        visited[current.getCoordinates().x()][current.getCoordinates().y()] = true;
        path.add(current);

        List<Room> neighbors = Arrays.asList(getNeighbors(dungeonGrid, current));
        Collections.shuffle(neighbors, random);

        for (Room neighbor : neighbors) {
            if (neighbor == null || visited[neighbor.getCoordinates().x()][neighbor.getCoordinates().y()]) {
                continue;
            }

            if (dfs(random, dungeonGrid, neighbor, end, minLength, maxLength, path, visited)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        visited[current.getCoordinates().x()][current.getCoordinates().y()] = false;
        return false;
    }

    private static Room[] getNeighbors(Room[][] dungeonGrid, Room room) {
        int x = room.getCoordinates().x();
        int y = room.getCoordinates().y();
        Room[] neighbors = new Room[4];

        if (x > 0) neighbors[0] = dungeonGrid[x - 1][y];       // Left
        if (x < dungeonGrid.length - 1) neighbors[1] = dungeonGrid[x + 1][y]; // Right
        if (y > 0) neighbors[2] = dungeonGrid[x][y - 1];       // Up
        if (y < dungeonGrid[0].length - 1) neighbors[3] = dungeonGrid[x][y + 1]; // Down

        return neighbors;
    }
}
