package com.sweattypalms.skyblock.core.helpers;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class BukkitWrapper {

    /**
     * Get the NMS handle of an entity
     * <b>This is a dirty hack, using Generic Erasure, but it works</b>
     * <br>
     * Usage:
     * <pre>
     * {@code
     *     Entity entity = Bukkit.getEntity("...");
     *     EntityLiving entityLiving = BukkitWrapper.getHandle(entity);
     *     // Do stuff with entityLiving
     * }
     * </pre>
     *
     * @param entity The entity
     * @param <T>    Make sure to cast properly.
     * @return The NMS handle
     */
    @SuppressWarnings("unchecked")
    public static <T> T getHandle(Entity entity) {
        return (T) ((CraftEntity) entity).getHandle();
    }

}
