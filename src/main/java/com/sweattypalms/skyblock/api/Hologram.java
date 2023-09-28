package com.sweattypalms.skyblock.api;

import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

/**
 * TODO: Complete holograms + show timer on slayer bosses.
 * complete slayer stuff.
 */
public class Hologram extends EntityArmorStand {
    SkyblockMob superEntity;
    Location staticLocation;
    final double yOffset;
    HologramType type;

    boolean isCustomText = false;

    public Hologram(String textToDisplay, Location location, double yOffset) {
        super(EntityTypes.c, ((CraftWorld) location.getWorld()).getHandle());
        this.staticLocation = location;
        this.yOffset = yOffset;
    }

    public void setHologramType(HologramType type) {
        this.type = type;
    }

    public enum HologramType {
        STATIC,
        FOLLOW,
        REPLACEMENT
    }
}
