package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.events.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.events.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.SkyblockMobDamagePlayerEvent;
import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemStack;

public class UtilityListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome to Skyblock!");
        if (SkyblockPlayer.getSkyblockPlayer(event.getPlayer()) != null) return;
        new SkyblockPlayer(event.getPlayer());
    }



    @EventHandler(priority = EventPriority.LOW)
    public void interactEventForwarder(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        TriggerType triggerType = TriggerType.getTriggerType(event.getAction());
        if (triggerType == null || triggerType == TriggerType.NONE) return;

        SkyblockInteractEvent skyblockInteractEvent = new SkyblockInteractEvent(skyblockPlayer, triggerType);

        if (event.getClickedBlock() != null) {
            skyblockInteractEvent.setInteractedBlock(event.getClickedBlock());
        }

        SkyBlock.getInstance().getServer().getPluginManager().callEvent(skyblockInteractEvent);

        if (skyblockInteractEvent.isCancelled()) event.setCancelled(true);
    }
    @EventHandler
    public void equipHelmetThroughEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        SkyblockItem skyblockItem = SkyblockItem.fromItemStack(itemInHand);

        if (skyblockItem == null) return;

        if (skyblockItem.getItemType() != SkyblockItemType.HELMET || !(skyblockItem instanceof IHeadHelmet)) return;

        if (player.getInventory().getHelmet() == null || player.getInventory().getHelmet().getType() == Material.AIR) {
            player.getInventory().setHelmet(itemInHand);
            player.getInventory().setItemInMainHand(null);
        }
    }
    /**
     * For handling the player clicking on the helmet slot in their inventory.
     * EQUIP/UNEQUIP HEAD HELMETS
     * TODO: MAKE IT
     * @param event InventoryClickEvent
     * @author ChatGPT 💀💀
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
    }
//        if (!(event.getWhoClicked() instanceof Player)) return;
//
//        Player player = (Player) event.getWhoClicked();
//        ItemStack clickedItem = event.getCurrentItem();
//
//        int headSlot = 39;
//
//        SkyblockItem clickedItemSkyblock = SkyblockItem.fromItemStack(clickedItem);
//
//        if (!(clickedItemSkyblock instanceof IHeadHelmet)) return;
//
//        if (event.getSlot() == headSlot && clickedItemSkyblock instanceof IHeadHelmet) {
//            event.setCurrentItem(player.getInventory().getHelmet());
//            player.getInventory().setHelmet(null);
//        } else {
//            // Equip
//            if(event.getCurrentItem() == null) return;
//            if(event.getCurrentItem().getType() == Material.AIR) return;
//            if(!(clickedItemSkyblock instanceof IHeadHelmet)) return;
//            if(player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType() != Material.AIR) return;
//            player.getInventory().setHelmet(event.getCurrentItem());
//            event.setCurrentItem(null);
//        }
//
//        // Else, if the clicked item is not a head helmet, try to equip it if it is in hand
////        else if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
////            ItemStack itemInHand = player.getInventory().getItemInMainHand();
////            SkyblockItem handSkyblockItem = SkyblockItem.fromItemStack(itemInHand);
////
////            if (handSkyblockItem instanceof IHeadHelmet) {
////                // Move the item from the player's hand to their helmet slot
////                player.getInventory().setHelmet(itemInHand);
////                // Remove the item from the player's hand
////                player.getInventory().setItemInMainHand(null);
////            }
////        }
//    }
//

    /**
     * For forwarding the EntityDeathEvent to the SkyblockDeathEvent
     *
     * @param event EntityDeathEvent
     */
    @EventHandler
    public void onDeathBukkit(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        event.setDroppedExp(0);
        event.getDrops().clear();
        if (livingEntity instanceof Player player) {
            SkyblockDeathEvent _event = new SkyblockDeathEvent(player, SkyblockDeathEvent.DeathCause.OTHER);
            Bukkit.getPluginManager().callEvent(_event);
            return;
        }

        EntityLiving entityLiving = ((CraftLivingEntity) event.getEntity()).getHandle();
        if (!(entityLiving instanceof ISkyblockMob skyblockMob)) return;

        SkyblockDeathEvent.DeathCause reason;
        EntityDamageEvent lastDamageCause = livingEntity.getLastDamageCause();
        if (lastDamageCause == null) {
            reason = SkyblockDeathEvent.DeathCause.OTHER;
        } else {
            reason = SkyblockDeathEvent.DeathCause.getCause(lastDamageCause.getCause());
        }
        SkyblockDeathEvent _event;
        if (reason == SkyblockDeathEvent.DeathCause.ENTITY) {
            _event = new SkyblockDeathEvent(livingEntity.getKiller(), livingEntity);
        } else {
            _event = new SkyblockDeathEvent(livingEntity, reason);
        }

        SkyBlock.getInstance().getServer().getPluginManager().callEvent(_event);
    }

    @EventHandler
    public void playerDeathBukkit(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.setKeepInventory(true);
        event.setKeepLevel(true);
        event.setDroppedExp(0);
        event.getDrops().clear();
        event.getEntity().spigot().respawn();
        // TODO: from here
    }

    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)
            event.setCancelled(true);
    }

    @EventHandler
    public void onBowPull(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);

        double bowPull = event.getForce();
        skyblockPlayer.getStatsManager().setMaxStat(Stats.BOW_PULL, bowPull);

        if (skyblockPlayer.getInventoryManager().getSkyblockItemInHand() instanceof IShortBow) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void projectileHitEvent(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
        if(!(event.getHitEntity() instanceof LivingEntity) || !(event.getHitEntity() instanceof EnderDragonPart)) return;
        if(event.getEntity() instanceof FallingBlock || event.getHitEntity() instanceof FallingBlock) return;
        if(event.getEntity().getShooter() == null) return;

        LivingEntity shooter = (LivingEntity) event.getEntity().getShooter();

        if (shooter instanceof Player && event.getHitEntity() instanceof Player) {
            event.setCancelled(true);
            return;
        }

        EntityLiving entityLiving = ((CraftLivingEntity) shooter).getHandle();
        if (entityLiving instanceof ISkyblockMob skyblockMob && event.getHitEntity() instanceof Player player) {
            event.setCancelled(true);
            SkyblockMobDamagePlayerEvent skyblockMobDamagePlayerEvent = new SkyblockMobDamagePlayerEvent(
                    player,
                    shooter
            );
            Bukkit.getPluginManager().callEvent(skyblockMobDamagePlayerEvent);
            return;
        }

        LivingEntity hitEntity = event.getHitEntity() instanceof EnderDragonPart ? ((EnderDragonPart) event.getHitEntity()).getParent() : (LivingEntity) event.getHitEntity();
        if (shooter instanceof Player player && ((CraftLivingEntity) hitEntity).getHandle() instanceof ISkyblockMob skyblockMob) {
            SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent = new SkyblockPlayerDamageEntityEvent(
                    hitEntity,
                    player,
                    SkyblockPlayerDamageEntityEvent.DamageType.ARROW
            );
            Bukkit.getPluginManager().callEvent(skyblockPlayerDamageEntityEvent);
            return;
        }

        event.setCancelled(true);


    }

    @EventHandler
    public void pickupItemEvent(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        Item item = event.getItem();
        ItemStack itemStack = item.getItemStack();
        if (itemStack.getType() == Material.ARROW) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void arrowPickupEvent(PlayerPickupArrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void endermanTeleport(EntityTeleportEvent event) {
        if(!(event.getEntity() instanceof Enderman)) return;

        event.setCancelled(true);
    }



    /* -------------------- WORLD MANAGEMENT -------------------- */

//    @EventHandler
//    public void placeBlock(EntityPlaceEvent event){
//        if(!(event.getEntity() instanceof Player player)) return;
//        if(player.isOp()) return;
//        event.setCancelled(true);
//    }
//
//    @EventHandler
//    public void breakBlock(BlockBreakEvent event){
//        if(event.getPlayer().isOp()) return;
//        event.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onPhysicalInteract(PlayerInteractEvent event) {
//        if (event.getClickedBlock() == null) return;
//        if(event.getPlayer().isOp()) return;
//        event.setCancelled(true);
//    }

    @EventHandler
    public void onFallingBlock(EntityChangeBlockEvent event) {
        if(event.getEntity() instanceof FallingBlock) {
            event.setCancelled(true);
            event.getEntity().remove();
            event.getBlock().setType(Material.AIR);
        }
    }
}

