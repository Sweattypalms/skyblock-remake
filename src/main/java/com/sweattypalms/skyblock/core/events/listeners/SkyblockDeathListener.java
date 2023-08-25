package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyblockDeathListener implements Listener {

    /**
     * Forwarded event from EntityDeathEvent
     * @param event SkyblockDeathEvent
     */
    @EventHandler
    public void skyblockDeathListener(SkyblockDeathEvent event){
        if(event.getDeadEntity() instanceof Player player){
            player.sendMessage("You died!");
            return;
        }
        SkyblockMob skyblockMob = SkyblockMob.getSkyblockMob(event.getDeadEntity());

        if(skyblockMob == null) return;

        if(event.getDamager() != null){
            // Play ting sound
            Location location = event.getDeadEntity().getLocation();
            assert location.getWorld() != null;
            location.getWorld().playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }

//        if(skyblockMob != null){
//            System.out.println("Skyblock mob died " + skyblockMob.getCustomName());
//        }else{
//            System.out.println("random mob died");
//        }
//        System.out.println("Cause: " + event.getCause());
    }
}
