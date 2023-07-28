package com.sweattypalms.skyblock.utils;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.enums.Rarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ItemUtil {

    public static void setStringInItem(ItemStack item, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
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
        if (meta == null) return;
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
        item.setItemMeta(meta);
    }
    public static void setDoubleInItem(ItemStack item, String key, double value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.DOUBLE, value);
        item.setItemMeta(meta);
    }

    public static double getDoubleFromItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);

        if (item != null && Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().has(namespacedKey, PersistentDataType.DOUBLE))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.DOUBLE);
        return 0;
    }


    public static void setDamage(ItemStack itemStack, double value){
        if (value == 0) return;
        setDoubleInItem(itemStack , "damage" , value);
    }
    public static double getDamage(ItemStack itemStack){
        return getDoubleFromItem(itemStack, "damage");
    }
    public static void setStrength(ItemStack item , double value){
        if (value == 0) return;
        setDoubleInItem(item, "strength", value);
    }
    public static double getStrength(ItemStack stack){
        return getDoubleFromItem(stack , "strength");
    }
    public static void setCritDamage(ItemStack stack , double value){
        if (value == 0) return;
        setDoubleInItem(stack, "critDamage", value);
    }
    public static double getCritDamage(ItemStack stack){
        return getDoubleFromItem(stack, "critDamage");
    }

    public static void setCritChance(ItemStack stack, double value){
        if (value == 0) return;
        setDoubleInItem(stack, "critChance", value);
    }
    public static double getCritChance(ItemStack stack){
        return getDoubleFromItem(stack, "critChance");
    }

    public static void setMana(ItemStack stack , double value){
        if (value == 0) return;
        setDoubleInItem(stack, "mana", value);
    }

    public static double getMana(ItemStack stack){
        return getDoubleFromItem(stack, "mana");
    }

    public static void setSpeed(ItemStack stack, double value){
        if (value == 0) return;
        setDoubleInItem(stack, "speed", value);
    }

    public static double getSpeed(ItemStack stack){
        return getDoubleFromItem(stack , "speed");
    }

    public static void setHealth(ItemStack stack , double value){
        if (value == 0) return;
        setDoubleInItem(stack , "health" , value);
    }

    public static double getHealth(ItemStack stack){
        return getDoubleFromItem(stack, "health");
    }

    public static void setDefense(ItemStack stack , double value){
        if (value == 0) return;
        setDoubleInItem(stack, "defense", value);
    }


    public static double getDefense(ItemStack stack){
        return getDoubleFromItem(stack, "defense");
    }

    public static void setRarity(ItemStack stack , Rarity rarity){
        if (rarity == null){
            setStringInItem(stack , "rarity" , Rarity.COMMON.toString());
            return;
        }
        setStringInItem(stack, "rarity", rarity.toString());
    }
    public static Rarity getRarity(ItemStack stack) {
        return Rarity.valueOf(getStringFromItem(stack, "rarity"));
    }

    public static int getIntFromItem(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);

        if (item != null && Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().has(namespacedKey, PersistentDataType.INTEGER))
            return item.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER);
        return 0;
    }



    public static void setArrayInItem(ItemStack item, String key, long[] value) {
        NamespacedKey namespacedKey = new NamespacedKey(SkyBlock.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
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
