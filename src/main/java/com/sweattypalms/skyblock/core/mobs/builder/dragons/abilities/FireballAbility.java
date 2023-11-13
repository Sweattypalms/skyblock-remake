package com.sweattypalms.skyblock.core.mobs.builder.dragons.abilities;

import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.EnderDragon;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FireballAbility implements IDragonAbility {
    private final EnderDragon dragon;
    private int tickCount = 0;

    public FireballAbility(EnderDragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public void start() {
        dragon.setMoving(false);
    }

    @Override
    public void stop() {
        dragon.setMoving(true);
        dragon.setAbility(null);
        tickCount = 0;
    }

    @Override
    public void tick() {
        tickCount++;

        // get the mouth part of the dragon
        Vec3D headPos = dragon.e.getPositionVector();
        Location headLoc = new Location(dragon.getBukkitEntity().getWorld(), headPos.b, headPos.c, headPos.d);
        if (tickCount % 2 == 0) { // To save on performance, only run every other tick
            Bukkit.getOnlinePlayers().forEach(player -> {
                for (int x = -1; x < 1; x++) {
                    for (int y = -1; y < 1; y++) {
                        for (int z = -1; z < 1; z++) {
                            Location loc = headLoc.clone().add(x, y, z);
                            player.spawnParticle(Particle.FLAME, loc, 0);
                        }
                    }
                }
            });
        }

        if (tickCount <= 40) { // 2 seconds (20 ticks/sec * 2)
            return;
        }

        int maxFireballs = 15;
        if (tickCount > maxFireballs * 20) { // 15 seconds after start (20 ticks/sec * {maxFireballs})
            stop();
            return;
        }

        if (tickCount % 20 != 0)
            return;

        if (!dragon.getEntityInstance().isAlive()) {
            stop();
            return;
        }

        Player closestPlayer = EntityHelper.getClosestPlayer((LivingEntity) dragon.getBukkitEntity());

        Vector direction;

        if (closestPlayer != null) {
            direction = closestPlayer.getLocation().toVector().subtract(headLoc.toVector()).normalize();
        } else {
            direction = headLoc.getDirection();
        }

        assert headLoc.getWorld() != null;
        Fireball fireball = headLoc.getWorld().spawn(headLoc, Fireball.class);
        fireball.setDirection(direction);
        fireball.setVelocity(direction.multiply(0.5));
    }

    @Override
    public boolean shouldActivate() {
        return dragon.getRandom().nextInt(600) == 0; // 1 in 600 chance or 30 seconds
    }
}
