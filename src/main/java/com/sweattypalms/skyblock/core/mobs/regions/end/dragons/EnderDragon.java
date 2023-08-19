package com.sweattypalms.skyblock.core.mobs.regions.end.dragons;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.mobs.IRegionEntity;
import com.sweattypalms.skyblock.core.mobs.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.Regions;
import com.sweattypalms.skyblock.core.mobs.SkyblockMob;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class EnderDragon extends EntityEnderDragon implements ISkyblockMob, IRegionEntity {
    public static final String ID = "ender_dragon";
    private final SkyblockMob skyblockMob;

    private double t = 0.0; // 0 ~ 1
    private final List<DragonStage> stages;
    private int currentStage = 0;

    boolean moving = true;

    public EnderDragon(Location location, SkyblockMob skyblockMob){
        super(EntityTypes.v, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;
        this.skyblockMob
                .setMaxHealth(100_000)
                .setDamage(5_000)
                .setCustomName("$cEnder Dragon")
                .setLevel(7695);

        stages = new ArrayList<>();
        stages.add(new DragonStage(
                List.of(
                        new Vector(0, 100, 0),      // Start at middle
                        new Vector(-40, 130, 50),   // Move up and to the left
                        new Vector(-60, 90, 100),   // Move down and further left
                        new Vector(-20, 110, 70)),  // Move up and to the right
                0.005,
                () -> {}                       // Empty function
        ));
        stages.add(new DragonStage(
                List.of(
                        new Vector(-20, 110, 70),
                        new Vector(50, 90, 50),
                        new Vector(80, 130, 0),
                        new Vector(60, 90, -40)),
                0.005,
                this::roar                     // Roar action
        ));
        stages.add(new DragonStage(
                List.of(
                        new Vector(60, 90, -40),
                        new Vector(0, 70, -80),
                        new Vector(-50, 85, -60),
                        new Vector(-40, 110, -20)),
                0.005,
                () -> {}                       // Empty function
        ));
        stages.add(new DragonStage(
                List.of(
                        new Vector(-40, 110, -20),
                        new Vector(0, 140, 0),
                        new Vector(40, 110, 20),
                        new Vector(0, 100, 0)),
                0.005,
                () -> {}                       // Empty function
        ));
        stages.add(new DragonStage(
                List.of(
                        new Vector(0, 100, 0),
                        new Vector(-80, 100, 50),
                        new Vector(80, 100, 50),
                        new Vector(0, 100, 0)),
                0.005,
                this::shootFireball            // Shoot fireball action
        ));
    }

    @Override
    public void tick() {
        super.tick();
        moveAlongPath();
    }

    public void moveAlongPath() {
        if (!this.moving || stages.isEmpty()) {
            return;
        }

        DragonStage stage = stages.get(currentStage);
        Vector position = stage.getPointOnBezierCurve(t);
        Location loc = new Location(this.getBukkitEntity().getWorld(), position.getX(), position.getY(), position.getZ());

        // Look in the direction of motion
        if (t + stage.speed < 1.0) {
            Vector nextPosition = stage.getPointOnBezierCurve(t + stage.speed);
            loc.setDirection(nextPosition.subtract(position));
        }

        this.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() + 180, loc.getPitch());

        t += stage.speed;
        if (t > 1.0) {
            t = 0.0;
            currentStage = (currentStage + 1) % stages.size();
            stage = stages.get(currentStage);
            stage.onEnter(this);
        }
    }

    public void shootFireball() {
        moving = false;
        Bukkit.broadcastMessage(ChatColor.RED +"Fireball");
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!getEntityInstance().isAlive()) {
                    cancel();
                    return;
                }
                Bukkit.broadcastMessage(ChatColor.RED +"Fireball!!!");
                moving = true;
            }
        }.runTaskLater(SkyBlock.getInstance(), 40L);
    }
    public void roar() {
        moving = false;
        Bukkit.broadcastMessage(ChatColor.RED +"Roar");

        new BukkitRunnable(){
            @Override
            public void run() {
                if(!getEntityInstance().isAlive()) {
                    cancel();
                    return;
                }
                Bukkit.broadcastMessage(ChatColor.RED +"Roar!!!");
                moving = true;
            }
        }.runTaskLater(SkyBlock.getInstance(), 40L);

        // Your code to make the dragon roar
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
        return Regions.END;
    }
}

