package com.sweattypalms.skyblock.core.helpers;

import org.bukkit.util.Vector;

public class MathHelper {
    public static Vector lerp(Vector start, Vector end, double t) {
        double x = start.getX() + t * (end.getX() - start.getX());
        double y = start.getY() + t * (end.getY() - start.getY());
        double z = start.getZ() + t * (end.getZ() - start.getZ());
        return new Vector(x, y, z);
    }
}
