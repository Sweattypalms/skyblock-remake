package com.sweattypalms.skyblock.core.events.listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class PlListener implements Listener {

    @EventHandler
    public void onTntPlace(VehicleCreateEvent event) {
        if (event.getVehicle().getType() != EntityType.MINECART_TNT) return;

        Location loc = event.getVehicle().getLocation();
        assert loc.getWorld() != null;

        double radius = 0.5;
        if (loc.getWorld().getNearbyEntities(loc, radius, radius, radius).stream().anyMatch(
                entity -> entity.getType() == EntityType.ARROW && entity.getFireTicks() > 0
        )) {
            event.getVehicle().remove();
            loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        }
    }
}
