package com.sweattypalms.skyblock.core.mobs.builder.dragons;

import com.sweattypalms.skyblock.core.mobs.builder.IRegionEntity;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.regions.Regions;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.abilities.FireballAbility;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.abilities.IDragonAbility;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.abilities.RushAbility;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public abstract class EnderDragon extends EntityEnderDragon implements ISkyblockMob, IRegionEntity, IEndDragon {
    private final SkyblockMob skyblockMob;
    private final List<DragonStage> stages;
    private final boolean pathVisualized = false;


    @Getter @Setter
    private boolean moving = true;
    private double visualizePathTick = 0;
    @Setter
    private double t = 0.0; // 0 ~ 1, to control the linear motion
    @Getter @Setter
    private int currentStageIndex = 0;
    @Getter @Setter
    private DragonStage currentStage;

    @Getter
    private final Random random = new Random();

    @Setter
    private IDragonAbility ability = null;
    private final List<IDragonAbility> abilities = List.of(
            new FireballAbility(this),
            new RushAbility(this)
    );

    public EnderDragon(Location location, SkyblockMob skyblockMob) {
        super(EntityTypes.v, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;
        this.skyblockMob
                .setMaxHealth(getMaxDragonHealth())
                .setDamage(getDragonDamage())
                .setCustomName(getDragonName())
        ;

        this.skyblockMob.setCustomNameVisible(false);

        this.stages = getDragonStages();
        if (stages.isEmpty()) throw new IllegalStateException("No stages found for dragon " + getDragonName());
        currentStage = stages.get(0);
    }

    public void visualizePath() {
        visualizePathTick++;
        if (visualizePathTick % 20 != 0) return;
        visualizePathTick = 0;

        for (DragonStage stage : stages) {
            for (double t = 0; t <= 1; t += 0.005) {
                Vector position = stage.getPoint(t);
                Location loc = new Location(getBukkitEntity().getWorld(), position.getX(), position.getY(), position.getZ());

                Bukkit.getOnlinePlayers().forEach(player -> player.spawnParticle(Particle.FLAME, loc, 0));
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.ability != null) {
            try {
                this.ability.tick();
            } catch (NullPointerException ex) {
                this.ability = null;
            }
            return;
        }

        for (IDragonAbility ability : this.abilities) {
            if (ability.shouldActivate()) {
                this.ability = ability;
                this.ability.start();
                break;
            }
        }
        moveAlongPath();
//        (Will show path for the dragon with Fire particles until the dragon is dead)
        if (pathVisualized) {
            visualizePath();
        }
    }

    public void moveAlongPath() {
        if (!this.moving || stages.isEmpty()) {
            return;
        }

        DragonStage stage = currentStage;
        Vector position = stage.getPoint(t);
        Location loc = new Location(this.getBukkitEntity().getWorld(), position.getX(), position.getY(), position.getZ());

        // Look in the direction of motion
        if (t + stage.speed < 1.0) {
            Vector nextPosition = stage.getPoint(t + stage.speed);
            loc.setDirection(nextPosition.subtract(position));
        }

        this.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() + 180, loc.getPitch());

        t += stage.speed;
        if (t > 1.0) {
            t = 0.0;
            currentStageIndex = (currentStageIndex + 1) % stages.size();
            currentStage = stages.get(currentStageIndex);
        }
    }
    @Override
    public SkyblockMob getSkyblockMob() {
        return skyblockMob;
    }

    @Override
    public EntityLiving getEntityInstance() {
        return this;
    }

    @Override
    public Regions getRegion() {
        return Regions.THE_END;
    }

    @Override
    public void setHealth(float f) {
        super.setHealth(f);
        if (f <= 0) {
            Bukkit.broadcastMessage(ChatColor.RED + "Dragon died");
            DragonManager.getInstance().onEnderDragonDeath();
        }
    }
}

