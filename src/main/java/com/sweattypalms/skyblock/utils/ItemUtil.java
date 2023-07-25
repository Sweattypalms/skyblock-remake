package com.sweattypalms.skyblock.utils;

import com.sweattypalms.skyblock.SkyBlock;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemUtil {

    public static void setStringInItem(ItemStack item, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
    }

    public static String getStringFromItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
        return "";
    }

    public static void setIntInItem(ItemStack item, String key, int value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
        item.setItemMeta(meta);
    }

    public static int getIntFromItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (item != null && item.getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.INTEGER))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER);
        return 0;
    }

    public static void setArrayInItem(ItemStack item, String key, long[] value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.LONG_ARRAY, value);
        item.setItemMeta(meta);
    }

    public static long[] getArrayInItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        if (item != null && item.getItemMeta().getPersistentDataContainer().has(namespacedKey, PersistentDataType.LONG_ARRAY))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.LONG_ARRAY);
        return new long[]{};
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
    public static void addEnchantmentGlint(ItemStack item) {
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }
    public static ItemStack dyeArmor(ItemStack item, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;
    }

}
