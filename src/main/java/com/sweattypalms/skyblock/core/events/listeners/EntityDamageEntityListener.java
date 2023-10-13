package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.def.SkyblockMobDamagePlayerEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class EntityDamageEntityListener implements Listener {
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent  event){
        event.setDamage(0);
        if (!(event.getEntity() instanceof LivingEntity livingEntity))
            return;

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player)
            return;

        if (event.getDamager() instanceof Player player){
            SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent = new SkyblockPlayerDamageEntityEvent(
                    livingEntity,
                    player,
                    SkyblockPlayerDamageEntityEvent.DamageType.MELEE
            );
            Bukkit.getPluginManager().callEvent(skyblockPlayerDamageEntityEvent);
        } else if (event.getDamager() instanceof LivingEntity livingEntity_ && event.getEntity() instanceof Player player){
            SkyblockMobDamagePlayerEvent skyblockMobDamagePlayerEvent = new SkyblockMobDamagePlayerEvent(
                    player,
                    livingEntity_
            );
            Bukkit.getPluginManager().callEvent(skyblockMobDamagePlayerEvent);
        }
    }
}
