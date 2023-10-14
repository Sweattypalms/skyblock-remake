package com.sweattypalms.skyblock.api;

import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;

/**
 * TODO: Complete holograms + show timer on slayer bosses.
 * complete slayer stuff.
 */
public class Hologram extends EntityArmorStand {
    SkyblockMob superEntity;
    Location staticLocation;
    final double yOffset;
    String textToDisplay;
    HologramType type;

    public Hologram(String textToDisplay, Location location) {
        super(EntityTypes.c, ((CraftWorld) location.getWorld()).getHandle());
        this.staticLocation = location;
        this.yOffset = 0;
        this.type = HologramType.STATIC;
        this.textToDisplay = textToDisplay;
    }

    public Hologram(SkyblockMob superEntity, double yOffset) {
        super(EntityTypes.c, ((CraftWorld) superEntity.getEntityInstance().getWorld()).getHandle());
        this.superEntity = superEntity;
        this.yOffset = yOffset;
        this.type = HologramType.REPLACEMENT;
        this.textToDisplay = superEntity.getEntityInstance().getCustomName(); // funny way?
    }

    public Hologram(String textToDisplay, SkyblockMob superEntity, double yOffset) {
        super(EntityTypes.c, ((CraftWorld) superEntity.getEntityInstance().getWorld()).getHandle());
        this.superEntity = superEntity;
        this.yOffset = yOffset;
        this.type = HologramType.FOLLOW;
        this.textToDisplay = textToDisplay;
    }

    public void start() {
        Location location = this.staticLocation == null ? this.superEntity.getEntityInstance().getLocation() : this.staticLocation;
        this.start(location);
    }

    public void start(Location location) {
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();

        ((CraftWorld) entity.getWorld()).getHandle().addEntity(this);

        this.setPosition(calculateLocation(location));

        init(entity);
    }

    private void init(LivingEntity entity) {
        entity.setCustomNameVisible(true);
        entity.setAI(false);
        entity.setCustomName(this.textToDisplay);
        entity.setCollidable(false);
        entity.setInvisible(true);
        entity.setInvulnerable(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (superEntity == null) return;

        if (superEntity.getEntityInstance() == null) {
            this.killEntity();
            return;
        }
        if (superEntity.getEntityInstance().isDead()) {
            this.killEntity();
            return;
        }
        if (this.type == HologramType.STATIC) return;

        Location location = superEntity.getEntityInstance().getLocation();
        this.setPosition(calculateLocation(location));


        if (this.type == HologramType.REPLACEMENT) {
            this.updateName(superEntity.getEntityInstance().getCustomName());
        }
    }

    public void updateName(String textToDisplay) {
        this.getBukkitEntity().setCustomName(textToDisplay);
    }

    public void setPosition(Location location) {
        this.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public Location calculateLocation(Location location) {
        return location.clone().add(0, this.yOffset, 0);
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
