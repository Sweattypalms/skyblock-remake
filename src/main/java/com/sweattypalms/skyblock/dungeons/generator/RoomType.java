package com.sweattypalms.skyblock.dungeons.generator;

import lombok.Getter;
import org.bukkit.Color;

@Getter
public enum RoomType {
    BROWN(Color.fromRGB(139, 69, 19)),    // Saddle Brown
    GREEN(Color.GREEN),
    RED(Color.RED),
    PINK(Color.fromRGB(255, 105, 180)),   // Hot Pink
    YELLOW(Color.fromRGB(255, 255, 100)), // Bright Yellow
    ORANGE(Color.fromRGB(255, 140, 0)),   // Dark Orange
    PURPLE(Color.PURPLE),
    TEST(Color. AQUA);

    private final Color color;

    RoomType(Color color) {
        this.color = color;
    }
}