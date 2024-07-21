package com.sweattypalms.skyblock.dungeons.generator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomShapeHandler {
    private final RoomShape shape;
    private final List<Room> rooms;
    private final int rot;

    public RoomShapeHandler(RoomShape shape, int rot) {
        this.shape = shape;
        this.rot = rot;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}