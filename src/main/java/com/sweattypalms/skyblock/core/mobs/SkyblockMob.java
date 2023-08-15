package com.sweattypalms.skyblock.core.mobs;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkyblockMob {

    // maybe not needed because the base entityclass extends ISkyblockMob and has a reference to the SkyblockMob instance
    public static Map<UUID, SkyblockMob> SPAWNED_MOBS = new HashMap<>();

    @Getter
    public final String id;
    @Getter
    private EntityType entityType;

    @Getter
    private String customName;
    public SkyblockMob setCustomName(String customName) {
        this.customName = PlaceholderFormatter.format(customName);
        if (entityInstance != null) {
            entityInstance.setCustomName(this.customName);
        }
        return this;
    }

    @Getter
    protected double damage;
    public SkyblockMob setDamage(double damage) {
        this.damage = damage;
        return this;
    }
    @Getter
    protected double maxHealth;
    @Getter
    protected double defense;
    public SkyblockMob setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    @Getter
    protected double speed;
    public SkyblockMob setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    @Getter
    protected int level;
    public SkyblockMob setLevel(int level) {
        this.level = level;
        return this;
    }

    @Getter
    @Setter
    private LivingEntity entityInstance;
    private Class<? extends ISkyblockMob> nmsClass;

    public SkyblockMob(String id, Class<? extends ISkyblockMob> nmsClass) {
        this.id = id;
        this.nmsClass = nmsClass;
    }


    public SkyblockMob setMaxHealth(double health) {
        this.maxHealth = health;
        if (entityInstance != null)
            setEntityInstanceMaxHealth(health);
        return this;
    }
    private void setEntityInstanceMaxHealth(double health){
        AttributeInstance attributeInstance = entityInstance.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attributeInstance != null;
        attributeInstance.setBaseValue(health);
        entityInstance.setHealth(health);
    }

    public SkyblockMob setDefense(double defense) {
        this.defense = defense;
        return this;
    }

    public SkyblockMob setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public void spawn(Location location) {
        if (entityInstance != null) return;

        if (nmsClass == null) return;
        try{
            Constructor<? extends ISkyblockMob> constructor = nmsClass.getConstructor(Location.class, SkyblockMob.class);
            ISkyblockMob _skyblockMob = constructor.newInstance(location, this);
            EntityLiving _entityLiving = _skyblockMob.getEntityInstance();

            ((CraftWorld) location.getWorld()).getHandle().addEntity(_entityLiving);
            _entityLiving.setPosition(location.getX(), location.getY(), location.getZ());

            this.entityInstance = (LivingEntity) _entityLiving.getBukkitEntity();
        }catch (Exception e) {
            e.printStackTrace();
        }

        initMob();
        heartbeat();
    }

    private void initMob(){
        setEntityInstanceMaxHealth(maxHealth);
    }

    private void heartbeat() {

        new BukkitRunnable() {
            SkyblockMob capture = SkyblockMob.this;
            @Override
            public void run() {
                if (entityInstance == null || entityInstance.isDead() || entityInstance.getHealth() < 1) {
                    capture.despawn();
                    cancel();
                    return;
                }

                AttributeInstance attributeInstance = entityInstance.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                assert attributeInstance != null;
                attributeInstance.setBaseValue(maxHealth);

                String levelComponent = "$8[$7Lv" + level + "$8]";

                String healthColour =
                        entityInstance.getHealth() > maxHealth * 0.66 ? "$a" :
                                entityInstance.getHealth() > maxHealth * 0.33 ? "$e" :
                                        "$c";

                String healthComponent =
                        healthColour + String.format("%.0f", Math.floor(entityInstance.getHealth())) + "§f/§a" + String.format("%.0f", getMaxHealth()) + "$c❤";

                String customName = levelComponent + " " + getCustomName() + " " + healthComponent;

                customName = PlaceholderFormatter.format(customName);

                entityInstance.setCustomName(customName);

            }
        }.runTaskTimer(SkyBlock.getInstance(), 0, 10);
    }


    public void despawn() {
        if (entityInstance == null) return;
        if(!entityInstance.isDead()) {
            entityInstance.setHealth(0);
        }else {
            entityInstance.remove();
        }
        entityInstance = null;
    }
}


//    /**
//     * Non NMS entity
//     *
//     * @param id         id of the mob, ex. "village_zombie"
//     * @param entityType the bukkit entity type, ex. EntityType.ZOMBIE
//     */
//    public SkyblockMob(String id, EntityType entityType, String name) {
//        this.id = id;
//        this.entityType = entityType;
//        this.customName = PlaceholderFormatter.format(name);
//    }
//
//    public SkyblockMob(String id, EntityType entityType, String name, EntityInsentient nmsClass) {
//        this(id, entityType, name);
//        this.nmsClass = nmsClass;
//    }
