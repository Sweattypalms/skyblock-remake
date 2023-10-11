package com.sweattypalms.skyblock.core.regions;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Comparator;

public class RegionManager {
    public static Regions getCurrentRegion(int x, int z, String world) {
        return Arrays.stream(Regions.values())
                .filter(region -> region.getWorld().equals(world) && region.contains(x, z))
                .min(Comparator.comparingInt(Regions::getArea) )  // Smallest area has the highest priority
                .orElse(null);
    }

    public static void updatePlayerRegion(SkyblockPlayer skyblockPlayer){
        Player player = skyblockPlayer.getPlayer();
        Location location = player.getLocation();
        assert location.getWorld() != null;
        Regions currentRegion = getCurrentRegion(location.getBlockX(), location.getBlockZ(), location.getWorld().getName());
        if (currentRegion != null && currentRegion != skyblockPlayer.getLastKnownRegion()) {
            skyblockPlayer.setLastKnownRegion(currentRegion);
//            player.sendMessage(ChatColor.GOLD + "You have entered " + currentRegion.getColor() + currentRegion.getDisplayName() + ChatColor.GOLD + "!");
        }
    }
}

