package com.sweattypalms.skyblock.core.listeners;

import com.sweattypalms.skyblock.core.PDCHelper;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageEntityListener implements Listener {
    @EventHandler
    public void onPlayerAttackEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        Player player = (Player) event.getDamager();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        // Get your SkyblockItem from itemInHand, here's a placeholder
        SkyblockItem skyblockItem = ItemManager.ITEMS_LIST.getOrDefault(PDCHelper.getOrDefault(itemInHand, "id", "null"), null);

        if (skyblockItem != null && skyblockItem.getAbilities() != null) {
            for (Ability ability : skyblockItem.getAbilities()) {
                if (ability.getTriggerType() == TriggerType.ATTACK && ability.trigger(event)) {
                    // convert Player to SkyblockPlayer, here's a placeholder
                    SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
                    ability.apply(skyblockPlayer, event);
                }
            }
        }
    }

}
