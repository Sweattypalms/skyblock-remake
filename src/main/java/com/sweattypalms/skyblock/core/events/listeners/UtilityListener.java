package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.events.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.events.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.armor.interfaces.IHeadHelmet;
import com.sweattypalms.skyblock.core.mobs.ISkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class UtilityListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) != null) return;
        new SkyblockPlayer(event.getPlayer());
    }

    @EventHandler
    public void equipHelmetThroughEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        SkyblockItem skyblockItem = SkyblockItem.fromItemStack(itemInHand);

        // Check if the item in the player's hand is an IHeadHelmet
        if (skyblockItem instanceof IHeadHelmet) {
            // Check that the player's helmet slot is empty (or replace current helmet)
            if (player.getInventory().getHelmet() == null || player.getInventory().getHelmet().getType() == Material.AIR) {
                // Move the item from the player's hand to their helmet slot
                player.getInventory().setHelmet(itemInHand);
                // Remove the item from the player's hand
                player.getInventory().setItemInMainHand(null);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void interactEventForwarder(PlayerInteractEvent event){
        Player player = event.getPlayer();
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        TriggerType triggerType = TriggerType.getTriggerType(event.getAction());
        if (triggerType == null || triggerType == TriggerType.NONE) return;

        SkyblockInteractEvent skyblockInteractEvent = new SkyblockInteractEvent(skyblockPlayer, triggerType);
        SkyBlock.getInstance().getServer().getPluginManager().callEvent(skyblockInteractEvent);
    }

    /**
     * For handling the player clicking on the helmet slot in their inventory.
     * EQUIP/UNEQUIP HEAD HELMETS
     * @author ChatGPT 💀💀
     * @param event InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        // Check if the clicked slot is the helmet slot (39 is the helmet slot index)
        if (event.getSlot() == 39) {
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(clickedItem);

            // If the clicked item is a head helmet, unequip it
            if (skyblockItem instanceof IHeadHelmet) {
                // Move the item from the player's helmet slot to their hand
                player.getInventory().setItemInMainHand(clickedItem);
                // Set the player's helmet slot to null (remove the helmet)
                player.getInventory().setHelmet(null);
            }
            // Else, if the clicked item is not a head helmet, try to equip it if it is in hand
            else if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack itemInHand = player.getInventory().getItemInMainHand();
                SkyblockItem handSkyblockItem = SkyblockItem.fromItemStack(itemInHand);

                if (handSkyblockItem instanceof IHeadHelmet) {
                    // Move the item from the player's hand to their helmet slot
                    player.getInventory().setHelmet(itemInHand);
                    // Remove the item from the player's hand
                    player.getInventory().setItemInMainHand(null);
                }
            }
        }
    }


    /**
     * For forwarding the EntityDeathEvent to the SkyblockDeathEvent
     * @param event EntityDeathEvent
     */
    @EventHandler
    public void onDeathBukkit(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        EntityLiving entityLiving = ((CraftLivingEntity) event.getEntity()).getHandle();
        if(!(entityLiving instanceof ISkyblockMob skyblockMob)) return;

        SkyblockDeathEvent.DeathCause reason;
        EntityDamageEvent lastDamageCause = livingEntity.getLastDamageCause();
        if(lastDamageCause == null) {
            reason = SkyblockDeathEvent.DeathCause.OTHER;
        }else{
            reason = SkyblockDeathEvent.DeathCause.getCause(lastDamageCause.getCause());
        }
//        System.out.printf("entity is: %s\nlast damage cause: %s\n", skyblockMob.getSkyblockMob().getCustomName(), reason.toString());
        SkyblockDeathEvent _event;
        if(reason == SkyblockDeathEvent.DeathCause.ENTITY){
            _event = new SkyblockDeathEvent(livingEntity.getKiller(), livingEntity);
        }else{
            _event = new SkyblockDeathEvent(livingEntity, reason);
        }

        SkyBlock.getInstance().getServer().getPluginManager().callEvent(_event);
    }

    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        if(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)
            event.setCancelled(true);
    }
}

