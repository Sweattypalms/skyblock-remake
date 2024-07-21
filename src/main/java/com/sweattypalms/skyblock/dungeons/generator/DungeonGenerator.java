package com.sweattypalms.skyblock.dungeons.generator;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DungeonGenerator {
    private static final int GRID_SIZE = 6;
    private final Room[][] grid;
    @Getter
    private List<Room> mainPath;
    @Getter
    private final LinkedHashMap<Room, RoomShapeHandler> shapeHandlers = new LinkedHashMap<>();
    private Random random;

    private final Map<RoomType, Integer> specialRoomRequirements = new HashMap<>(
            Map.of(
                    RoomType.YELLOW, 1,
                    RoomType.ORANGE, 1,
                    RoomType.PURPLE, 3
            )
    );

    public DungeonGenerator() {
        grid = new Room[GRID_SIZE][GRID_SIZE];
    }

    public void generate(long seed) {
        long startTime = System.nanoTime();
        random = new Random(seed);
        this.mainPath = new ArrayList<>();
        this.shapeHandlers.clear();

        initializeGrid();
        placeStartAndEndRooms();
        generateMainPath();
        placeFairyRoom();
        generateAndPlaceShapes();
        removeIntraShapeConnections();
        connectAllRooms();
        placeSpecialRooms();

        long endTime = System.nanoTime();
        String time = String.format("%.2f", (endTime - startTime) / 1_000_000.0);

        System.out.println("Generated dungeon in " + time + "ms");
    }

    private void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Point point = new Point(i, j);
                grid[i][j] = new Room(point, RoomType.BROWN);
            }
        }
    }

    private void placeStartAndEndRooms() {
        Point startCoords = getRandomWallPosition(random);
        Point endCoords;

        do {
            endCoords = getRandomWallPosition(random);
        } while (isSameWall(startCoords, endCoords) || distance(startCoords, endCoords) < 4);

        Room startRoom = getRoomAt(startCoords);
        Room endRoom = getRoomAt(endCoords);

        startRoom.setType(RoomType.GREEN);
        endRoom.setType(RoomType.RED);
    }

    private Point getRandomWallPosition(Random random) {
        int x, y;
        int side = random.nextInt(4);
        y = switch (side) {
            case 0 -> {
                x = random.nextInt(GRID_SIZE);
                yield 0; // Top wall
            }
            case 1 -> {
                x = random.nextInt(GRID_SIZE);
                yield GRID_SIZE - 1; // Bottom wall
            }
            case 2 -> {
                x = 0;
                yield random.nextInt(GRID_SIZE); // Left wall
            }
            default -> {
                x = GRID_SIZE - 1;
                yield random.nextInt(GRID_SIZE); // Right wall
            }
        };
        return new Point(x, y);
    }

    private boolean isSameWall(Point a, Point b) {
        int xa = a.x(), ya = a.y();
        int xb = b.x(), yb = b.y();

        // Check if any point is on the top or bottom wall
        return (xa == xb) || (ya == yb);
    }

    private double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2));
    }

    private void generateMainPath() {
        Room startRoom = getRoomOfType(RoomType.GREEN);
        Room endRoom = getRoomOfType(RoomType.RED);

        int MIN_PATH_LENGTH = 8, MAX_PATH_LENGTH = 14;

        List<Room> mainPath = DungeonUtils.generateMainPath(random, grid, startRoom, endRoom, MIN_PATH_LENGTH, MAX_PATH_LENGTH);

        for (int i = 0; i < mainPath.size(); i++) {
            if (i == mainPath.size() - 1) break;

            Room current = mainPath.get(i);
            Room next = mainPath.get(i + 1);
            current.addConnection(next);
            next.addConnection(current);
        }

        this.mainPath = mainPath;
    }

    private void placeFairyRoom() {
        List<Room> candidates = new ArrayList<>();
        for (int i = 3; i < mainPath.size() - 3; i++) {
            candidates.add(mainPath.get(i));
        }
        if (candidates.isEmpty()) {
            System.out.println("No candidates found for fairy room");
            throw new RuntimeException("No candidates found for fairy room");
        }

        Room pinkRoom = candidates.get(random.nextInt(candidates.size()));
        pinkRoom.setType(RoomType.PINK);
    }

    private void generateAndPlaceShapes() {
        RoomShape[] shapes = RoomShape.values();
        shapes = Arrays.stream(shapes).filter(shape -> !shape.isAvoid()).toArray(RoomShape[]::new);

        int specialRoomCount = specialRoomRequirements.values().stream().mapToInt(a -> 1).sum();

        int MAX_SHAPES = 100;
        for (int i = 0; i < MAX_SHAPES; i++) {
            Set<Room> occupiedRooms = this.shapeHandlers.keySet();
            Set<Room> singleRooms = new HashSet<>();
            for (Room[] row : grid) {
                for (Room room : row) {
                    if (room.getType() == RoomType.BROWN && !occupiedRooms.contains(room)) {
                        singleRooms.add(room);
                    }
                }
            }

            // Just don't place any more shapes if we have enough special rooms
            if (singleRooms.size() <= specialRoomCount) {
                break;
            }

            RoomShape shape = shapes[random.nextInt(shapes.length)];
            int rot = random.nextInt(4);
            placeShape(shape, rot);
        }

        // Consider remaining brown rooms as single rooms.
        Set<Room> singleRooms = new HashSet<>();
        Set<Room> occupied = this.shapeHandlers.keySet();
        for (Room[] row : grid) {
            for (Room room : row) {
                if (room.getType() == RoomType.BROWN && !occupied.contains(room)) {
                    singleRooms.add(room);
                }
            }
        }
        singleRooms.forEach(room -> {
            RoomShapeHandler shapeHandler = new RoomShapeHandler(RoomShape.SINGLE, 0);
            shapeHandler.addRoom(room);
            this.shapeHandlers.put(room, shapeHandler);
        });
    }

    private void placeShape(RoomShape shape, int rot) {
        int x = random.nextInt(GRID_SIZE);
        int y = random.nextInt(GRID_SIZE);

        Point coords = new Point(x, y);
        if (canPlaceShape(shape, coords, rot)) {
            placeShapeAt(shape, coords, rot);
        }
    }

    private boolean canPlaceShape(RoomShape shape, Point coords, int rot) {
        Point[] points = shape.rotateWithRotationCount(rot);
        List<Room> rooms = new ArrayList<>();
        for (Point point : points) {
            int dx = coords.x() + point.x();
            int dy = coords.y() + point.y();

            // Check if the point is within the grid
            if (dx < 0 || dx >= GRID_SIZE || dy < 0 || dy >= GRID_SIZE) {
                return false;
            }

            if (grid[dx][dy].getType() != RoomType.BROWN) {
                return false;
            }

            // Check if the point is already occupied by a shape
            if (shapeHandlers.containsKey(getRoomAt(dx, dy))) {
                return false;
            }

            rooms.add(getRoomAt(dx, dy));
        }

        // check if it doesn't loop back on itself
        AtomicInteger inOutConnections = new AtomicInteger();
        rooms.forEach(room -> {
            if (room.getConnectedRooms().isEmpty()) {
                return;
            }

            Set<Room> connectedRooms = room.getConnectedRooms();
            connectedRooms.forEach(connectedRoom -> {
                if (!rooms.contains(connectedRoom)) {
                    inOutConnections.getAndIncrement();
                }
            });
        });

        // check if it doesn't have more than 2 connections
        // It's fine if it passes through a room but shouldn't loop back on itself
        return inOutConnections.get() <= 2;
    }

    private void placeShapeAt(RoomShape shape, Point coords, int rot) {
        RoomShapeHandler shapeHandler = new RoomShapeHandler(shape, rot);
        for (Point point : shape.rotateWithRotationCount(rot)) {
            int dx = coords.x() + point.x();
            int dy = coords.y() + point.y();

            Room room = getRoomAt(new Point(dx, dy));
            room.setShape(shape);
            shapeHandler.addRoom(room);
        }

        shapeHandler.getRooms().forEach(room -> shapeHandlers.put(room, shapeHandler));
    }

    private void removeIntraShapeConnections() {
        Set<RoomShapeHandler> shapeHandlers = new HashSet<>(this.getShapeHandlers().values());

        for (RoomShapeHandler shapeHandler : shapeHandlers) {
            for (Room room : shapeHandler.getRooms()) {
                Iterator<Room> iterator = room.getConnectedRooms().iterator();
                while (iterator.hasNext()) {
                    Room connectedRoom = iterator.next();
                    if (!shapeHandler.getRooms().contains(connectedRoom)) continue;

                    iterator.remove();
                    connectedRoom.removeConnection(room);
                }
            }
        }
    }

    private void connectAllRooms() {
        List<Room> stems = new ArrayList<>(mainPath);
        // Remove the start and end rooms from the stems (Red, Green)
        stems.remove(0);
        stems.remove(stems.size() - 1);

        Set<Room> visited = new HashSet<>();
        int mainPathIndex = 0;
        int MAX_TRIES = 100;

        // add all the shapes in the main path to the visited set
        for (Room room : mainPath) {
            RoomShapeHandler shapeHandler = shapeHandlers.get(room);
            if (shapeHandler != null) {
                visited.addAll(shapeHandler.getRooms());
            }
        }

//        Set<Room> allRooms = new HashSet<>(Arrays.stream(this.grid).flatMap(Arrays::stream).toList());

        while (isThereUnconnectedRooms()) {
            Room currentRoom = stems.get(mainPathIndex % stems.size());

            if (!visited.contains(currentRoom) || mainPath.contains(currentRoom)) {
                RoomShapeHandler shapeHandler = shapeHandlers.get(currentRoom);
                if (shapeHandler != null) {
                    addShapeConnections(shapeHandler, stems, visited);
                } else {
                    addRoomConnections(currentRoom, stems, visited);
                }
                visited.add(currentRoom);
            }

            mainPathIndex++;

            if (mainPathIndex > MAX_TRIES) {
                break;
            }
        }
    }

    private boolean isThereUnconnectedRooms() {
        Set<Room> allRooms = new HashSet<>(Arrays.stream(this.grid).flatMap(Arrays::stream).toList());
        return allRooms.stream().anyMatch(room -> {
            RoomShapeHandler roomShapeHandler = shapeHandlers.get(room);
            if (roomShapeHandler != null) {
                return roomShapeHandler.getRooms().stream().allMatch(r -> r.getConnectedRooms().isEmpty());
            } else {
                return room.getConnectedRooms().isEmpty();
            }
        });
    }

    private void addShapeConnections(RoomShapeHandler shapeHandler, List<Room> stems, Set<Room> visited) {
        for (Room room : shapeHandler.getRooms()) {
            addAdjacentRooms(room, stems, visited, shapeHandler);
        }
    }

    private void addRoomConnections(Room room, List<Room> stems, Set<Room> visited) {
        if (!visited.contains(room) && !mainPath.contains(room)) {
            visited.add(room);
            addAdjacentRooms(room, stems, visited, null);
        }
    }

    private void addAdjacentRooms(Room room, List<Room> stems, Set<Room> visited, RoomShapeHandler shapeHandler) {
        int x = room.getCoordinates().x();
        int y = room.getCoordinates().y();

        if (x > 0) {
            Room top = grid[x - 1][y];
            addNeighbor(room, stems, visited, shapeHandler, top);
        }
        if (x < GRID_SIZE - 1) {
            Room bottom = grid[x + 1][y];
            addNeighbor(room, stems, visited, shapeHandler, bottom);
        }
        if (y > 0) {
            Room left = grid[x][y - 1];
            addNeighbor(room, stems, visited, shapeHandler, left);
        }
        if (y < GRID_SIZE - 1) {
            Room right = grid[x][y + 1];
            addNeighbor(room, stems, visited, shapeHandler, right);
        }
    }

    private void addNeighbor(Room room, List<Room> stems, Set<Room> visited, RoomShapeHandler shapeHandler, Room neighbor) {
        RoomShapeHandler neighborShapeHandler = shapeHandlers.get(neighbor);
        boolean isMainPath = mainPath.contains(neighbor) || (neighborShapeHandler != null && neighborShapeHandler.getRooms().stream().anyMatch(mainPath::contains));
        boolean isVisited = visited.contains(neighbor) || (neighborShapeHandler != null && neighborShapeHandler.getRooms().stream().anyMatch(visited::contains));
        boolean isStem = stems.contains(neighbor) || (neighborShapeHandler != null && neighborShapeHandler.getRooms().stream().anyMatch(stems::contains));
        boolean isShape = shapeHandler != null && shapeHandler.getRooms().contains(neighbor);
        if (!isVisited && !isStem && !isMainPath && (shapeHandler == null || !isShape)) {
            room.addConnection(neighbor);
            neighbor.addConnection(room);
            stems.add(neighbor);
        }
    }

    private void placeSpecialRooms() {
        List<Room> singleRooms = new ArrayList<>();
        for (Room[] row : grid) {
            for (Room room : row) {
                RoomShapeHandler shapeHandler = shapeHandlers.get(room);
                if (shapeHandler == null || shapeHandler.getShape() != RoomShape.SINGLE) {
                    continue;
                }

                if (room.getConnectedRooms().size() > 1) {
                    continue;
                }

                singleRooms.add(room);
            }
        }

        int requiredRooms = specialRoomRequirements.values().stream().mapToInt(Integer::intValue).sum();
        int prevSize = singleRooms.size();
        if (singleRooms.size() < requiredRooms) {
            List<Room> newlyCreatedSingleRooms = this.makeSingleRoom(requiredRooms - singleRooms.size());
            singleRooms.addAll(newlyCreatedSingleRooms);
            System.out.println("Newly created single rooms: " + newlyCreatedSingleRooms.size());
        }

        if (singleRooms.size() < requiredRooms) {
            System.out.println("Not enough single rooms to place special rooms (prev size: " + prevSize + "), required: " + requiredRooms + ", current: " + singleRooms.size());
            throw new RuntimeException("Not enough single rooms to place special rooms");
        }

        // shuffle the single rooms and assign the special rooms
        Collections.shuffle(singleRooms, random);
        for (RoomType type : specialRoomRequirements.keySet()) {
            int amount = specialRoomRequirements.get(type);

            for (int i = 0; i < amount; i++) {
                Room room = singleRooms.get(i);
                room.setType(type);
            }
            singleRooms.subList(0, amount).clear();
        }
    }

    private List<Room> makeSingleRoom(int requiredAmount) {
        List<Room> newlyCreatedRooms = new ArrayList<>();

        List<RoomShapeHandler> validShapes = new ArrayList<>();
        for (RoomShapeHandler shapeHandler : shapeHandlers.values()) {
            if (shapeHandler.getShape() == RoomShape.SINGLE) continue;
            validShapes.add(shapeHandler);
        }

        // Turn 2x2 into L; 4x1 into 3x1; 3x1 into 2x1; 2x1 into single
        // Make sure that specific 'single' room has no connections to other rooms

        Collections.shuffle(validShapes, random);

        int remaining = requiredAmount;

        Set<Room> processedRooms = new HashSet<>();

        int tries = 0;
        for (int i = 0; remaining != 0 && tries < validShapes.size(); tries++) {
            RoomShapeHandler shapeHandler = validShapes.get(i);
            i++;
            List<Room> possibleRooms = new ArrayList<>(shapeHandler.getRooms().stream().filter(r -> r.getConnectedRooms().isEmpty()).toList());
            possibleRooms.removeAll(processedRooms);

            if (possibleRooms.isEmpty()) {
                continue;
            }

            // make sure they're the end part of the shape
            Room edge = null;

            // check if the shape is a 2x2 or L, get the alone one because not possible that the 1x1 is in the center.
            if (List.of(RoomShape.SQUARE, RoomShape.L_SHAPE_1).contains(shapeHandler.getShape())) {
                if (shapeHandler.getShape() == RoomShape.SQUARE) {
                    edge = possibleRooms.get(this.random.nextInt(possibleRooms.size()));
                } else {
                    int subTries = 0;
                    do {
                        Room possibleEdge = possibleRooms.get(subTries);
                        List<Room> others = new ArrayList<>(shapeHandler.getRooms());
                        others.remove(possibleEdge);

                        Room other1 = others.get(0);
                        Room other2 = others.get(1);

                        if (other1.getCoordinates().x() == other2.getCoordinates().x() || other1.getCoordinates().y() == other2.getCoordinates().y()) {
                            edge = possibleEdge;
                            break;
                        }
                        subTries++;
                    } while (subTries < possibleRooms.size());

                    if (edge == null) {
                        edge = possibleRooms.get(this.random.nextInt(possibleRooms.size()));
                        decoupleShape(shapeHandler, edge);
                    }
                }

            } else {
                for (Room possRoom : possibleRooms) {
                    Point coords = possRoom.getCoordinates();
                    int x = coords.x();
                    int y = coords.y();

                    int nonSameNeighbors = 0;
                    if (x > 0 && !shapeHandler.getRooms().contains(grid[x - 1][y])) nonSameNeighbors++;
                    if (x < GRID_SIZE - 1 && !shapeHandler.getRooms().contains(grid[x + 1][y])) nonSameNeighbors++;
                    if (y > 0 && !shapeHandler.getRooms().contains(grid[x][y - 1])) nonSameNeighbors++;
                    if (y < GRID_SIZE - 1 && !shapeHandler.getRooms().contains(grid[x][y + 1])) nonSameNeighbors++;

                    if (x == 0 || x == GRID_SIZE - 1) nonSameNeighbors++;
                    if (y == 0 || y == GRID_SIZE - 1) nonSameNeighbors++;

                    if (nonSameNeighbors >= 3) {
                        edge = possRoom;
                        break;
                    }
                }
            }

            if (edge == null) {
                continue;
            }

//            edge.setType(RoomType.TEST);
            processedRooms.addAll(shapeHandler.getRooms());
            decoupleShape(shapeHandler, edge);
            newlyCreatedRooms.add(edge);
            tries++;
            remaining--;
        }

        if (remaining > 0) {
            System.err.println("Failed to place all required single rooms");
        }

        return newlyCreatedRooms;
    }

    private void decoupleShape(RoomShapeHandler shapeHandler, Room roomToDecouple) {
        shapeHandler.getRooms().forEach(shapeHandlers::remove);

        List<Room> rooms = shapeHandler.getRooms();
        rooms.remove(roomToDecouple);

        Point[] points = rooms.stream().map(Room::getCoordinates).toArray(Point[]::new);

        boolean makeMultipleSingles = false;

        if (points.length == 2) {
            Point p1 = points[0];
            Point p2 = points[1];
            if (p1.x() != p2.x() && p1.y() != p2.y()) {
                makeMultipleSingles = true;
            }
        }

        if (!makeMultipleSingles) {
            RoomShape.ShapeResult newShape;

            if (points.length == 1) {
                newShape = new RoomShape.ShapeResult(RoomShape.SINGLE, 0);
            } else {
                newShape = RoomShape.identifyShape(points);
            }

            RoomShapeHandler newShapeHandler = new RoomShapeHandler(newShape.shape(), newShape.rotationCount());

            for (Room room : rooms) {
                room.setShape(newShape.shape());
                newShapeHandler.addRoom(room);
                shapeHandlers.put(room, newShapeHandler);
            }

            RoomShapeHandler edgeHandler = new RoomShapeHandler(RoomShape.SINGLE, 0);
            edgeHandler.addRoom(roomToDecouple);
            shapeHandlers.put(roomToDecouple, edgeHandler);

            Room closest = getClosestRoom(roomToDecouple, rooms);

            roomToDecouple.addConnection(closest);
            closest.addConnection(roomToDecouple);
        } else {
            RoomShapeHandler edgeHandler = new RoomShapeHandler(RoomShape.SINGLE, 0);
            edgeHandler.addRoom(roomToDecouple);
            shapeHandlers.put(roomToDecouple, edgeHandler);

            Room closest = getClosestRoom(roomToDecouple, rooms);
            roomToDecouple.addConnection(closest);
            closest.addConnection(roomToDecouple);

            for (Room room : rooms) {
                RoomShapeHandler newShapeHandler = new RoomShapeHandler(RoomShape.SINGLE, 0);
                room.setShape(RoomShape.SINGLE);
                newShapeHandler.addRoom(room);
                shapeHandlers.put(room, newShapeHandler);

                /*if (room == closest && room.getConnectedRooms().size() == 1) {
                    for (Room neighbor : getNeighbors(room)) {
                        if (neighbor.getType() != RoomType.BROWN) continue;

                        room.addConnection(neighbor);
                        neighbor.addConnection(room);
                        break;
                    }
                }*/

                Stack<Room> stack = new Stack<>();
                stack.push(room);

                Set<Room> visited = new HashSet<>();
                visited.add(room);

                Set<Room> path = new HashSet<>();
                path.add(room);

                while (!stack.isEmpty()) {
                    Room current = stack.pop();
                    path.add(current);
                    if (current == getRoomOfType(RoomType.GREEN)) {
                        break;
                    }

                    if (shapeHandlers.get(current) == null) {
                        System.out.println("Shape handler is null for room " + current);
                        break;
                    }

                    // add all the connected rooms
                    Set<Room> traversableNeighbors = new HashSet<>();
                    RoomShapeHandler currentShapeHandler = shapeHandlers.get(current);
                    currentShapeHandler.getRooms().forEach(r -> traversableNeighbors.addAll(r.getConnectedRooms()));

                    traversableNeighbors.removeIf(visited::contains);
                    traversableNeighbors.removeIf(r -> r.getType() != RoomType.BROWN);

                    if (traversableNeighbors.isEmpty()) {
                        // try exploring the other rooms
                        System.out.println("No traversable neighbors for room " + current + ", exploring other rooms");
                        traversableNeighbors.addAll(getNeighbors(currentShapeHandler));
                    }

                    traversableNeighbors.removeIf(visited::contains);

                    for (Room neighbor : traversableNeighbors) {
                        visited.add(neighbor);
                        stack.push(neighbor);
                    }
                }

                Set<RoomShapeHandler> pathShapeHandlers = new HashSet<>();
                for (Room pathRoom : path) {
                    RoomShapeHandler pathShapeHandler = shapeHandlers.get(pathRoom);
                    if (pathShapeHandler != null) {
                        pathShapeHandlers.add(pathShapeHandler);
                    }
                }

                /*pathShapeHandlers.forEach(pathShapeHandler -> {
                    for (Room pathRoom : pathShapeHandler.getRooms()) {
                        if (pathRoom == room) continue;

                        List<Room> neighbors = getNeighbors(pathRoom);
                        neighbors.removeIf(neighbor -> neighbor.getType() != RoomType.BROWN);
                        neighbors.removeIf(neighbor -> !path.contains(neighbor));

                        for (Room neighbor : neighbors) {
                            pathRoom.addConnection(neighbor);
                            neighbor.addConnection(pathRoom);
                        }

                    }
                });

                if (!path.contains(getRoomOfType(RoomType.GREEN))) {
                    Room closestInPath = getClosestRoomToPath(room, path);
                    room.addConnection(closestInPath);
                    closestInPath.addConnection(room);
                }*/
            }
        }
    }

    private Room getClosestRoomToPath(Room room, Set<Room> path) {
        Room closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Room pathRoom : path) {
            Point roomCoords = room.getCoordinates();
            Point pathRoomCoords = pathRoom.getCoordinates();
            double distance = roomCoords.distance(pathRoomCoords);
            if (distance < minDistance && (roomCoords.x() == pathRoomCoords.x() || roomCoords.y() == pathRoomCoords.y())) {
                minDistance = distance;
                closest = pathRoom;
            }
        }

        if (closest == null) {
            throw new RuntimeException("Failed to find closest room to path");
        }
        return closest;
    }

    private static Room getClosestRoom(Room roomToDecouple, List<Room> rooms) {
        Room closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Room room : rooms) {
            Point roomCoords = room.getCoordinates();
            Point decoupleCoords = roomToDecouple.getCoordinates();
            double distance = roomCoords.distance(decoupleCoords);
            if (distance < minDistance && (roomCoords.x() == decoupleCoords.x() || roomCoords.y() == decoupleCoords.y())) {
                minDistance = distance;
                closest = room;
            }
        }

        if (closest == null) {
            throw new RuntimeException("Failed to find closest room");
        }
        return closest;
    }

    private List<Room> getNeighbors(Room room) {
        List<Room> neighbors = new ArrayList<>();
        int x = room.getCoordinates().x();
        int y = room.getCoordinates().y();

        if (x > 0) {
            neighbors.add(grid[x - 1][y]);
        }
        if (x < GRID_SIZE - 1) {
            neighbors.add(grid[x + 1][y]);
        }
        if (y > 0) {
            neighbors.add(grid[x][y - 1]);
        }
        if (y < GRID_SIZE - 1) {
            neighbors.add(grid[x][y + 1]);
        }

        return neighbors;
    }

    private List<Room> getNeighbors(RoomShapeHandler shapeHandler) {
        List<Room> neighbors = new ArrayList<>();

        for (Room room : shapeHandler.getRooms()) {
            neighbors.addAll(getNeighbors(room));
        }
        neighbors.removeAll(shapeHandler.getRooms());
        neighbors.removeIf(n -> !shapeHandlers.containsKey(n));
        return neighbors;
    }

    public Room[][] getDungeonGrid() {
        return grid;
    }

    private void setRoomAt(Point point, Room room) {
        grid[point.x()][point.y()] = room;
    }

    private Room getRoomAt(Point point) {
        return grid[point.x()][point.y()];
    }

    private Room getRoomAt(int x, int y) {
        return grid[x][y];
    }

    private Room getRoomOfType(RoomType type) {
        for (Room[] row : grid) {
            for (Room room : row) {
                if (room.getType() == type) {
                    return room;
                }
            }
        }
        return null;
    }

    static class Test {
        public static void main(String[] args) {
            List<Double> times = new ArrayList<>();
            for (int n = 0; n < 100_000; n++) {
                DungeonGenerator generator = new DungeonGenerator();
                AtomicInteger counter = new AtomicInteger(0);
                long start = System.nanoTime();
                generator.generate(new Random().nextLong());
                long end = System.nanoTime();
                times.add((end - start) / 1_000_000.);
            }

            double sum = times.stream().mapToDouble(Double::doubleValue).sum();
            String time = String.format("%.2f", sum / times.size());
            System.out.println("Average time: " + time + "ms");
        }
    }
}
