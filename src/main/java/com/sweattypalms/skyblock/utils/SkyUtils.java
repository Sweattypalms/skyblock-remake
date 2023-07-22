package com.sweattypalms.skyblock.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public static ItemStack createNamedItemStack(Material material, String name) {
        ItemStack stack = new ItemStack(material);
        if (name != null) {
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(name);
            stack.setItemMeta(meta);
        }
        return stack;
    }
}
