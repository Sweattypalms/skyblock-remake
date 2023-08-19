package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.SkyblockMobDamagePlayerEvent;
import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class EntityDamageEntityListener implements Listener {
//    @EventHandler
//    public void onPlayerAttackEntity(EntityDamageByEntityEvent event) {
//        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity)) {
//            return;
//        }
//
//        Player player = (Player) event.getDamager();
//        ItemStack itemInHand = player.getInventory().getItemInMainHand();
//
//        // Get your SkyblockItem from itemInHand, here's a placeholder
//        SkyblockItem skyblockItem = ItemManager.ITEMS_LIST.getOrDefault(PDCHelper.getOrDefault(itemInHand, "id", "null"), null);
//
//        if (skyblockItem != null && skyblockItem.getAbilities() != null) {
//            for (Ability ability : skyblockItem.getAbilities()) {
//                if (ability.getTriggerType() == TriggerType.ATTACK && ability.trigger(event)) {
//                    // convert Player to SkyblockPlayer, here's a placeholder
//                    SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
//                    ability.apply(skyblockPlayer, event);
//                }
//            }
//        }
//    }
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent  event){
        event.setDamage(0);
        if(!(event.getEntity() instanceof LivingEntity livingEntity))
            return;

        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player)
            return;

        if(event.getDamager() instanceof Player player){
            // Add more checks like checking if the item is a bow etc.
            SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent = new SkyblockPlayerDamageEntityEvent(
                    livingEntity,
                    player,
                    SkyblockPlayerDamageEntityEvent.DamageType.MELEE
            );
            Bukkit.getPluginManager().callEvent(skyblockPlayerDamageEntityEvent);
        }else{
            SkyblockMobDamagePlayerEvent skyblockMobDamagePlayerEvent = new SkyblockMobDamagePlayerEvent(
                    (Player) event.getEntity(),
                    (LivingEntity) event.getDamager()
            );
            Bukkit.getPluginManager().callEvent(skyblockMobDamagePlayerEvent);
        }
    }
}
