package com.sweattypalms.skyblock.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SkyUtils {

    public static List<Block> getNearbyBlocks(Location location, int radius, Material type) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; ++x) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; ++y) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; ++z) {
                    Block block = Objects.requireNonNull(location.getWorld()).getBlockAt(x, y, z);
                    if (block.getType() != type && type != null) continue;
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public static <T> T instance(Class<T> clazz, Object... params) {
        Class<?>[] paramClasses = new Class[params.length];
        for (int i = 0; i < paramClasses.length; i++)
            paramClasses[i] = params[i].getClass();
        try {
            Constructor<T> constructor = clazz.getConstructor(paramClasses);
            constructor.setAccessible(true);
            return constructor.newInstance(params);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException ex) {
            return null;
        }
    }
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static List<LivingEntity> getNearestEntities(Entity entity, double distance) {
        List<Entity> entities = entity.getNearbyEntities(distance, distance, distance);
        List<LivingEntity> living = new ArrayList<>();
        for (Entity e : entities)
            if (e instanceof LivingEntity && !(e instanceof Player))
                living.add((LivingEntity) e);
        return living;
    }

    public static LivingEntity getNearestEntity(Entity entity, double distance) {
        List<Entity> entities = entity.getNearbyEntities(distance, distance, distance);
        List<LivingEntity> livings = new ArrayList<>();
        LivingEntity lv = null;
        if (entities.isEmpty()) return null;
        for (Entity e : entities)
            if (e instanceof LivingEntity && !(e instanceof Player))
                livings.add((LivingEntity) e);
        if (livings.isEmpty()) return null;
        lv = livings.get(0);
        return lv;
    }

    public static List<Player> getNearestPlayers(Entity entity, int distance) {
        List<Entity> entities = entity.getNearbyEntities(distance, 2, distance);
        List<Player> living = new ArrayList<>();
        for (Entity e : entities)
            if (e instanceof Player)
                living.add((Player) e);
        return living;
    }
    public static int getRandomInteger(int max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }
    public static Location getRandomLocation(Location origin, int radius) {
        int which = getRandomInteger(3);
        Location newLoc = origin;

        switch (which) {
            case 0:
                int x = getRandomInteger(radius);
                newLoc = origin.add(x, 0 ,0);
                break;
            case 1:
                int z = getRandomInteger(radius);
                newLoc = origin.add(0, 0, z);
                break;
            case 2:
                int minusX = getRandomInteger(radius);
                newLoc = origin.add(-minusX, 0, 0);
                break;
            case 3:
                int minusZ = getRandomInteger(radius);
                newLoc = origin.add(0, 0, -minusZ);
                break;
        }

        return newLoc;
    }
}
