package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.api.sequence.Sequence;
import com.sweattypalms.skyblock.api.sequence.SequenceAction;
import com.sweattypalms.skyblock.core.events.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.events.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.SkyblockMobDamagePlayerEvent;
import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonManager;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.IEndDragon;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.loot.IDragonLoot;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderDragonPart;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemStack;

public class UtilityListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String message = "$eWelcome to $cSkyblock$e!";
        message = PlaceholderFormatter.format(message);
        event.getPlayer().sendMessage(message);
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
        new Sequence().add(
                new SequenceAction(() -> {

                    event.getEntity().spigot().respawn();
                    event.getEntity().updateInventory();
                }, 1)
        ).start();
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
        if (!(event.getHitEntity() instanceof LivingEntity) && !(event.getHitEntity() instanceof CraftEnderDragonPart))
            return;
        if (event.getEntity() instanceof FallingBlock || event.getHitEntity() instanceof FallingBlock) return;
        if (event.getEntity().getShooter() == null) return;

        LivingEntity shooter = (LivingEntity) event.getEntity().getShooter();

        if (shooter instanceof Player && event.getHitEntity() instanceof Player) {
            event.setCancelled(true);
            return;
        }

        EntityLiving entityLiving = ((CraftLivingEntity) shooter).getHandle();
        if (entityLiving instanceof ISkyblockMob && event.getHitEntity() instanceof Player player) {
            event.setCancelled(true);
            SkyblockMobDamagePlayerEvent skyblockMobDamagePlayerEvent = new SkyblockMobDamagePlayerEvent(
                    player,
                    shooter
            );
            Bukkit.getPluginManager().callEvent(skyblockMobDamagePlayerEvent);
            return;
        }

        LivingEntity hitEntity = event.getHitEntity() instanceof EnderDragonPart ? ((EnderDragonPart) event.getHitEntity()).getParent() : (LivingEntity) event.getHitEntity();
        if (shooter instanceof Player player && ((CraftLivingEntity) hitEntity).getHandle() instanceof ISkyblockMob) {
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

        if (((CraftItem) event.getItem()).getHandle() instanceof IDragonLoot dragonLoot) {
            player.sendMessage(ChatColor.RED + "You cannot pick up this item! (owner: " + dragonLoot.getDropOwner().getPlayer().getName() + ")");
            event.setCancelled(true);
        }

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
        if (!(event.getEntity() instanceof Enderman)) return;

        event.setCancelled(true);
    }

    /* -------------------- WORLD MANAGEMENT -------------------- */

    @EventHandler
    public void placeBlock(EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.isOp()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPhysicalInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onFallingBlock(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock) {
            event.setCancelled(true);
            event.getEntity().remove();
            event.getBlock().setType(Material.AIR);
        }
    }

    @EventHandler
    public void enderDragonDamage(SkyblockPlayerDamageEntityEvent event){
        if(event.getSkyblockMob() == null) return;
        if(event.getSkyblockMob().getEntityInstance() == null) return;
        if(!(((CraftLivingEntity) event.getSkyblockMob().getEntityInstance()).getHandle() instanceof IEndDragon)) return;

        DragonManager.getInstance().addPlayerDamage(event.getPlayer().getUniqueId(), event.getDamage());
    }

}


