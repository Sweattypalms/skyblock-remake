package com.sweattypalms.skyblock.dungeons.physical;

import com.sweattypalms.skyblock.dungeons.generator.*;
import com.sweattypalms.skyblock.dungeons.generator.Point;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class DungeonMapRenderer extends MapRenderer {
    public static final int GRID_SIZE = 6;
    public static final int CELL_SIZE = 100;
    public static final int CONNECTION_THICKNESS = 50;
    public static final int PADDING = 10;
    public static final int GAP = 20;

    private final DungeonGenerator generator;
    private boolean rendered = false;

    public DungeonMapRenderer(DungeonGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        if (rendered) return;

        int imgSize = GRID_SIZE * (CELL_SIZE + GAP) + GAP + PADDING;
        BufferedImage image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setComposite(AlphaComposite.Clear);
        graphics.fillRect(0, 0, imgSize, imgSize);
        graphics.setComposite(AlphaComposite.SrcOver);

        graphics.translate(PADDING, PADDING);

        drawRooms(graphics);
        drawShapes(graphics);
        drawConnections(graphics);

        // Resize and apply the image to the map canvas
        image = MapPalette.resizeImage(image);
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                Color pixelColor = new Color(image.getRGB(x, y), true);

                if (pixelColor.getAlpha() == 0) {
                    canvas.setPixel(x, y, MapPalette.TRANSPARENT);
                } else {
                    canvas.setPixel(x, y, MapPalette.matchColor(pixelColor));
                }

//                canvas.setPixel(x, y, MapPalette.matchColor(new Color(image.getRGB(x, y), true)));
            }
        }

        rendered = true;
    }

    private void drawRooms(Graphics2D graphics) {
        Room[][] dungeonGrid = generator.getDungeonGrid();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Room room = dungeonGrid[i][j];
                com.sweattypalms.skyblock.dungeons.generator.Point coords = getPlaceLocation(room.getCoordinates());
                drawRoom(graphics, room, coords.x(), coords.y());
            }
        }
    }

    private void drawRoom(Graphics2D graphics, Room room, int x, int y) {
        graphics.setColor(getRoomColor(room.getType()));
        graphics.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    private void drawShapes(Graphics2D graphics) {
        for (RoomShapeHandler shapeHandler : generator.getShapeHandlers().values()) {
            if (shapeHandler.getShape().isAvoid()) continue;

            if (List.of(RoomShape.SINGLE, RoomShape.TWO_X_ONE, RoomShape.THREE_X_ONE, RoomShape.FOUR_X_ONE, RoomShape.SQUARE).contains(shapeHandler.getShape())) {
                drawRectangularShapes(graphics, shapeHandler);
            } else {
                drawIrregularShapes(graphics, shapeHandler);
            }
        }
    }

    private void drawRectangularShapes(Graphics2D graphics, RoomShapeHandler shapeHandler) {
        List<Room> rooms = shapeHandler.getRooms();

        // Find the bounding box for the shape
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (Room room : rooms) {
            com.sweattypalms.skyblock.dungeons.generator.Point coordinates = room.getCoordinates();
            if (coordinates.x() < minX) minX = coordinates.x();
            if (coordinates.y() < minY) minY = coordinates.y();
            if (coordinates.x() > maxX) maxX = coordinates.x();
            if (coordinates.y() > maxY) maxY = coordinates.y();
        }

        int finalMinX = minX;
        int finalMinY = minY;
        Room startRoom = shapeHandler.getRooms().stream().filter(r -> {
            com.sweattypalms.skyblock.dungeons.generator.Point coordinates = r.getCoordinates();
            return (coordinates.x() == finalMinX && coordinates.y() == finalMinY);
        }).findFirst().orElseThrow();

        int finalMaxX = maxX;
        int finalMaxY = maxY;
        Room endRoom = shapeHandler.getRooms().stream().filter(r -> {
            com.sweattypalms.skyblock.dungeons.generator.Point coordinates = r.getCoordinates();
            return (coordinates.x() == finalMaxX && coordinates.y() == finalMaxY);
        }).findFirst().orElseThrow();

        com.sweattypalms.skyblock.dungeons.generator.Point startPlaceCoords = getPlaceLocation(startRoom.getCoordinates());
        com.sweattypalms.skyblock.dungeons.generator.Point endPlaceCoords = getPlaceLocation(endRoom.getCoordinates());

        // Calculate the width and height of the bounding box
        int width = endPlaceCoords.x() - startPlaceCoords.x() + CELL_SIZE;
        int height = endPlaceCoords.y() - startPlaceCoords.y() + CELL_SIZE;

        // Draw a single rectangle that covers the entire shape
        graphics.setColor(getRoomColor(startRoom.getType()));
        graphics.fillRect(startPlaceCoords.x(), startPlaceCoords.y(), width, height);
    }

    private void drawIrregularShapes(Graphics2D graphics, RoomShapeHandler shapeHandler) {
        List<Room> rooms = shapeHandler.getRooms();

        for (int i = 0; i < rooms.size(); i++) {
            Room roomA = rooms.get(i);
            Room roomB = rooms.get((i + 1) % rooms.size());

            com.sweattypalms.skyblock.dungeons.generator.Point a = roomA.getCoordinates(), b = roomB.getCoordinates();

            if (a.x() == b.x() || a.y() == b.y()) {
                // draw a single rectangle with min max
                com.sweattypalms.skyblock.dungeons.generator.Point startPlaceCoords = new com.sweattypalms.skyblock.dungeons.generator.Point(Math.min(a.x(), b.x()), Math.min(a.y(), b.y()));
                com.sweattypalms.skyblock.dungeons.generator.Point endPlaceCoords = new com.sweattypalms.skyblock.dungeons.generator.Point(Math.max(a.x(), b.x()), Math.max(a.y(), b.y()));

                startPlaceCoords = getPlaceLocation(startPlaceCoords);
                endPlaceCoords = getPlaceLocation(endPlaceCoords);

                int width = Math.abs(endPlaceCoords.x() - startPlaceCoords.x()) + CELL_SIZE;
                int height = Math.abs(endPlaceCoords.y() - startPlaceCoords.y()) + CELL_SIZE;

                graphics.setColor(getRoomColor(roomA.getType()));
                graphics.fillRect(startPlaceCoords.x(), startPlaceCoords.y(), width, height);
            }
        }
    }

    private void drawConnections(Graphics2D graphics) {
        Room[][] dungeonGrid = generator.getDungeonGrid();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Room room = dungeonGrid[i][j];
                for (Room connectedRoom : room.getConnectedRooms()) {
                    boolean isMainPath = generator.getMainPath().contains(room) && generator.getMainPath().contains(connectedRoom);
                    drawConnection(graphics, room, connectedRoom, isMainPath);
                }
            }
        }
    }

    private void drawConnection(Graphics2D graphics, Room room1, Room room2, boolean isMainPath) {
        com.sweattypalms.skyblock.dungeons.generator.Point coords1 = room1.getCoordinates();
        com.sweattypalms.skyblock.dungeons.generator.Point coords2 = room2.getCoordinates();

        // Use CELL_SIZE and GAP instead of width and spacing for consistency
//        int CELL_SIZE = width;
//        int GAP = spacing;
//        int PADDING = spacing * 2; // Equivalent to the JavaFX version's PADDING

        double d = (CELL_SIZE + GAP); // Distance between cells (cell size + gap)

        double xA = coords1.y() * d + PADDING;
        double yA = coords1.x() * d + PADDING;
        double xB = coords2.y() * d + PADDING;
        double yB = coords2.x() * d + PADDING;

        // Check for diagonal connections and ignore them
        if (xA != xB && yA != yB) {
            return;
        }

        double xS, yS;
        int connectionThickness = CONNECTION_THICKNESS; // Use a constant thickness
        double connectionWidth = connectionThickness;
        double connectionHeight = connectionThickness;

        if (xA == xB) {
            // Vertical connection
            xS = xA + CELL_SIZE / 2.0 - connectionWidth / 2.0;
            yS = Math.min(yA, yB) + CELL_SIZE;
            connectionHeight = GAP;
        } else {
            // Horizontal connection
            xS = Math.min(xA, xB) + CELL_SIZE;
            yS = yA + CELL_SIZE / 2.0 - connectionHeight / 2.0;
            connectionWidth = GAP;
        }

        graphics.setColor(isMainPath ? Color.BLACK : getRoomColor(room1.getType()));
        graphics.fillRect((int) xS, (int) yS, (int) connectionWidth, (int) connectionHeight);
    }

    private Color getRoomColor(RoomType type) {
        return new Color(
                type.getColor().getRed(),
                type.getColor().getGreen(),
                type.getColor().getBlue()
        );
    }

    private com.sweattypalms.skyblock.dungeons.generator.Point getPlaceLocation(com.sweattypalms.skyblock.dungeons.generator.Point gridCoordinates) {
        return new Point(gridCoordinates.y() * (CELL_SIZE + GAP) + PADDING,
                gridCoordinates.x() * (CELL_SIZE + GAP) + PADDING);
    }
}