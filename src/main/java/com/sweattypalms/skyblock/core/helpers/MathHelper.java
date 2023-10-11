package com.sweattypalms.skyblock.core.helpers;

import com.sweattypalms.skyblock.SkyBlock;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MathHelper {

    /**
     * Lerp between two locations
     *
     * @param start Start location
     * @param end   End location
     * @param t     % between start and end
     * @return Vector of the lerped location
     */
    public static Vector linearInterpolation(Vector start, Vector end, double t) {
        double x = start.getX() + t * (end.getX() - start.getX());
        double y = start.getY() + t * (end.getY() - start.getY());
        double z = start.getZ() + t * (end.getZ() - start.getZ());
        return new Vector(x, y, z);
    }

    public static void spiralParticles(LivingEntity entity, double f, double delta, Particle particle) {
        Location eyeLocation = entity.getEyeLocation();

        new BukkitRunnable() {
            final int stepsPerTick = 5;
            int step = 0;

            @Override
            public void run() {
                for (int i = 0; i < stepsPerTick; i++) {
                    if (step >= 60) {
                        this.cancel();
                        return;
                    }

                    double angle = step * 6;
                    double radius = f + (delta / 60) * step;
                    double forward = (4 / 60f) * step;

                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);

                    Vector v = new Vector(x, 0, z);
                    rotateAroundAxisX(v, eyeLocation.getPitch() - 90);
                    Vector v2 = new Vector(v.getX(), v.getY(), v.getZ());
                    rotateAroundAxisY(v2, eyeLocation.getYaw());

                    Location loc = eyeLocation.clone().add(v2.getX(), v2.getY(), v2.getZ()).add(eyeLocation.clone().getDirection().multiply(0.5));
                    loc = loc.add(loc.getDirection().multiply(forward));
                    entity.getWorld().spawnParticle(particle, loc, 0);

                    step++;
                }
            }
        }.runTaskTimerAsynchronously(SkyBlock.getInstance(), 0L, 1L);
    }

    private static Vector rotateAroundAxisX(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    private static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }
}
