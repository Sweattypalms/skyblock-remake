package com.sweattypalms.skyblock.core.helpers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class MozangStuff {
    public static ItemStack getHeadItemStack(String texture) {
        UUID uuid = UUID.randomUUID();
        GameProfile gameProfile = new GameProfile(uuid, null);
        gameProfile.getProperties().put("textures", new Property("textures", texture));
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, gameProfile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        skullMeta.setUnbreakable(true);
        Arrays.stream(ItemFlag.values()).toList().forEach(skullMeta::addItemFlags);
        item.setItemMeta(skullMeta);
        return item;
    }
}
