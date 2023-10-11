package com.sweattypalms.skyblock.core.regions;

import com.sweattypalms.skyblock.api.Point;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.Arrays;

public enum Regions {
    VILLAGE(ChatColor.AQUA, "skyblock_hub", new Point(207, -239), new Point(-323, 211)),
    GRAVEYARD(ChatColor.RED, "skyblock_hub", new Point(-91, -75), new Point(-233, -224)),
    SOME_HOUSE(ChatColor.RED, "skyblock_hub", new Point(-125, -105), new Point(-139, -93)),
    THE_END(ChatColor.DARK_PURPLE, "skyblock_end", new Point(222, 140), new Point(-137, -138)),
    DRAGONS_NEST(ChatColor.DARK_PURPLE, "skyblock_end", new Point(69, 74), new Point(-71, -77)),
    ;

    @Getter
    private final String color;
    /**
     * The minimum and maximum points of the region.
     * Must be diagonal.
     */
    @Getter
    private Point min;
    @Getter
    private Point max;
    @Getter
    private final String world;

    int getArea(){
        return (max.getX() - min.getX()) * (max.getZ() - min.getZ());
    }

    /**
     * Creates a new region with the given color, world, and points.
     * @param color The color of the region. Can be a string or a ChatColor.
     * @param world The world the region is in.
     * @param point1 The first point of the region. <b>(Must be diagonal)</b>
     * @param point2 The second point of the region. <b>(Must be diagonal)</b>
     * @param <T> The type of the color. ChatColor or String.
     */
    <T> Regions(T color, String world, Point point1, Point point2) {
        if (color instanceof String)
            this.color = (String) color;
        else if (color instanceof ChatColor)
            this.color = ((ChatColor) color).toString();
        else
            this.color = "";

        this.world = world;
        setMinMax(point1, point2);
    }
    private void setMinMax(Point point1, Point point2) {
        int minX = Math.min(point1.getX(), point2.getX());
        int maxX = Math.max(point1.getX(), point2.getX());

        int minZ = Math.min(point1.getZ(), point2.getZ());
        int maxZ = Math.max(point1.getZ(), point2.getZ());

        this.min = new Point(minX, minZ);
        this.max = new Point(maxX, maxZ);
    }

    public static Regions getRegion(String region) {
        final Regions[] _region = {null};
        Arrays.stream(Regions.values()).toList().forEach(reg -> {
            if (reg.name().equalsIgnoreCase(region)) {
                _region[0] = reg;
            }
        });
        return _region[0];
    }

    public String getDisplayName() {
//        split _ and capitalize each word
        String[] parts = this.name().split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            sb.append(PlaceholderFormatter.capitalize(parts[i]));
            if (i != parts.length - 1) {
                sb.append(" ");
            }
        }
        return getColor() + sb;
    }


    public boolean contains(int x, int z) {
        return x >= min.getX() && x <= max.getX() && z >= min.getZ() && z <= max.getZ();
    }
}
