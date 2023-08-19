package com.sweattypalms.skyblock.core.helpers;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import static com.sweattypalms.skyblock.SkyBlock.getInstance;

public class PDCHelper {
    public static boolean hasInt(ItemStack item, String key){
        return item != null && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(getInstance(), key), PersistentDataType.INTEGER);
    }
    public static boolean hasString(ItemStack item, String key){
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(getInstance(), key), PersistentDataType.STRING);
    }
    public static boolean hasDouble(ItemStack item, String key){
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(getInstance(), key), PersistentDataType.DOUBLE);
    }
    public static Integer getInt(ItemStack item, String key) {
        return hasInt(item, key) ? item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(getInstance(), key), PersistentDataType.INTEGER)
                : 0;
    }

    public static String getString(ItemStack item, String key) {
        return hasString(item, key) ? item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(getInstance(), key), PersistentDataType.STRING) : "";
    }
    public static Double getDouble(ItemStack item, String key) {
        return hasDouble(item, key) ?
                item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(getInstance(), key), PersistentDataType.DOUBLE)
                : 0.0;
    }
    public static <T> void set(ItemStack item, String key, T value){
        ItemMeta meta = item.getItemMeta();
        if(value instanceof Integer)
            meta.getPersistentDataContainer().set(new NamespacedKey(getInstance(), key), PersistentDataType.INTEGER, (int) value);
        else if(value instanceof String)
            meta.getPersistentDataContainer().set(new NamespacedKey(getInstance(), key), PersistentDataType.STRING, (String) value);
        else if(value instanceof Double)
            meta.getPersistentDataContainer().set(new NamespacedKey(getInstance(), key), PersistentDataType.DOUBLE, (double) value);
        item.setItemMeta(meta);
    }

    public static void setInt(ItemStack item, String key, Integer value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(getInstance(), key), PersistentDataType.INTEGER, value);
        item.setItemMeta(meta);
    }

    public static void setString(ItemStack item, String key, String value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(getInstance(), key), PersistentDataType.STRING, value);
        item.setItemMeta(meta);
    }

    public static void setDouble(ItemStack item, String key, Double value){
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(getInstance(), key), PersistentDataType.DOUBLE, value);
        item.setItemMeta(meta);
    }
    public static <T> T getOrDefault(ItemStack item, String key, T def){
        if(def instanceof Double && hasDouble(item, key))
            return (T) getDouble(item, key);
        if(def instanceof String && hasString(item, key))
            return (T) getString(item, key);
        if(def instanceof Integer && hasInt(item, key))
            return (T) getInt(item, key);

        return def;
    }

}