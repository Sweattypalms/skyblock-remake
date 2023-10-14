package com.sweattypalms.skyblock.core.items.builder.armor;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sweattypalms.skyblock.core.helpers.MozangStuff;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public interface IHeadHelmet {
    String getTexture();

    /**
     * @return the head generated through the texture value
     */
    default ItemStack getHeadItemStack() {
        return MozangStuff.getHeadItemStack(getTexture());
    }


}
