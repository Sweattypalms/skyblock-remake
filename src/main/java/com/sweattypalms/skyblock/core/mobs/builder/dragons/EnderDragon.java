package com.sweattypalms.skyblock.core.mobs.builder.dragons;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.helpers.MathHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.mobs.builder.IRegionEntity;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.Regions;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EnderDragon extends EntityEnderDragon implements ISkyblockMob, IRegionEntity, IEndDragon {
    private final SkyblockMob skyblockMob;
    private final List<DragonStage> stages;
    boolean moving = true;
    double visualizePathTick = 0;
    private double t = 0.0; // 0 ~ 1
    private int currentStageIndex = 0;
    private DragonStage currentStage;

    private Random random = new Random();
    private Player target = null;
    private boolean isRushing = false;
    private Vector startRushPosition;
    private double rushProgress = 0.0; // 0 ~ 1 to control the linear motion

    private boolean isDoingAbility = false;

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

        if (this.isRushing) {
            rushTowardsPlayer();
            return;
        }

        if (random.nextInt(1500) == 0) { // 1 in 1000 chance or per 50 seconds
            executeRush();
            return;
        }
        if (random.nextInt(500) == 0) { // 1 in 500 chance or per 25 seconds
            fireball();
            return;
        }

        moveAlongPath();
//        visualizePath();
    }

    public void moveAlongPath() {
        if (!this.moving || stages.isEmpty()) {
            return;
        }

//        DragonStage stage = stages.get(currentStageIndex);
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

    /********************** ABILITIES **********************/
    private void executeRush() {
        if (isDoingAbility) return;

        isDoingAbility = true;
        moving = false;
        this.target = EntityHelper.getClosestPlayer((LivingEntity) this.getBukkitEntity());

        if (target == null) {
            throw new IllegalStateException("No target found??");
        }

        this.isRushing = true;
        startRushPosition = this.getBukkitEntity().getLocation().toVector();
        rushProgress = 0.0d;
    }

    private void rushTowardsPlayer() {
        // Calculate the next position using LERP
        Vector nextPosition = MathHelper.lerp(startRushPosition, target.getLocation().toVector(), rushProgress);
        Location nextLoc = nextPosition.toLocation(this.getBukkitEntity().getWorld());

        // Set the position and look direction towards the player
        this.setPositionRotation(nextLoc.getX(), nextLoc.getY(), nextLoc.getZ(), target.getLocation().getYaw(), target.getLocation().getPitch());

        rushProgress += 0.025;  // Adjust this value to control the speed

        if (rushProgress >= 1 || this.getBukkitEntity().getLocation().distance(target.getLocation()) < 3) {
            String message = "$5☬ $c" + getDragonName() + " $dused $eRush $don you for $c" + getDragonDamage() + " damage!";
            message = PlaceholderFormatter.format(message);
            target.sendMessage(message);
            SkyblockPlayer.getSkyblockPlayer(target).damageWithReduction(getDragonDamage());
            computeReturnPath();
        }
    }

    private void computeReturnPath() {
        Vector endPosition = currentStage.getPoint(1); // Get  the end position from the current path
        Vector currentDragonPosition = this.getBukkitEntity().getLocation().toVector();

        List<Vector> path = List.of(currentDragonPosition, endPosition);
        DragonStage returnPath = new DragonStage(path, 0.02);

        // Reset it so it starts lerping from the start of y = mx + n
        t = 0.0;
        currentStage = returnPath;


        // To return to the main path
        isRushing = false;
        moving = true;
    }

    private void fireball() {
        if (isDoingAbility) return;

        isDoingAbility = true;
        moving = false;

        this.target = EntityHelper.getClosestPlayer((LivingEntity) this.getBukkitEntity());

        if (target == null) {
            throw new IllegalStateException("No target found??");
        }
        // 2 seconds warmup
        // 5 seconds ability time


        // get the mouth part of the dragon
        Vec3D headPos = this.e.getPositionVector();
        Location headLoc = new Location(this.getBukkitEntity().getWorld(), headPos.b, headPos.c, headPos.d);

        AtomicInteger tick = new AtomicInteger(0);
        new BukkitRunnable() {
            @Override
            public void run() {
                tick.set(tick.get() + 10);

                Bukkit.getOnlinePlayers().forEach(player -> {
//                    spawn in 3d
                    for (int x = -1; x < 1; x++){
                        for (int y = -1; y < 1; y++){
                            for (int z = -1; z < 1; z++){
                                Location loc = headLoc.clone().add(x, y, z);
                                player.spawnParticle(Particle.FLAME, loc, 0);
                            }
                        }
                    }
                });

                if (tick.get() <= 40) {
                    return;
                }

                if (tick.get() > 140) // 5 seconds after warmup
                {
                    cancel();
                    isDoingAbility = false;
                    moving = true;
                    return;
                }

                if (!getEntityInstance().isAlive()) {
                    cancel();
                    isDoingAbility = false;
                    moving = true;
                    return;
                }

                Player closestPlayer = EntityHelper.getClosestPlayer((LivingEntity) getBukkitEntity());

                Vector direction;

                if (closestPlayer != null) {
                    direction = closestPlayer.getLocation().toVector().subtract(headLoc.toVector()).normalize();
                } else {
                    direction = headLoc.getDirection();
                }

                assert headLoc.getWorld() != null;
                Fireball fireball = headLoc.getWorld().spawn(headLoc, Fireball.class);
                fireball.setDirection(direction);
                fireball.setVelocity(direction.multiply(0.5));  // Adjust the multiplier to set the speed. 0.5 is just an example value.
            }
        }.runTaskTimer(SkyBlock.getInstance(), 0, 10);

        Bukkit.getScheduler().runTaskLater(SkyBlock.getInstance(), () -> {
            isDoingAbility = false;
            moving = true;
        }, 140);
    }

    /********************** ABILITIES **********************/
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

    @Override
    public void setHealth(float f) {
        super.setHealth(f);
        if (f <= 0) {
            Bukkit.broadcastMessage(ChatColor.RED + "Dragon died");
            DragonManager.getInstance().onEnderDragonDeath();
        }
    }
}

