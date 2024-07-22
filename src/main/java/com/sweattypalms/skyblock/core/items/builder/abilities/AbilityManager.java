package com.sweattypalms.skyblock.core.items.builder.abilities;

import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.*;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AbilityManager {
    public static final Ability LAPIS_ARMOR_ABILITY = new LAPIS_ARMOR_ABILITY();
    public static Ability UNDEAD_SWORD_ABILITY = new DamageAbility() {
        @Override
        public String getName() {
            return "Undead Sword";
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>();
        }

        @Override
        public boolean preCalc() {
            return true;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return false;
            List<EntityType> whitelistedEntities = List.of(
                    EntityType.SKELETON,
                    EntityType.ZOMBIE,
                    EntityType.ZOMBIE_VILLAGER,
                    EntityType.WITHER,
                    EntityType.PIGLIN
            );

            return whitelistedEntities.contains(skyblockPlayerDamageEntityEvent.getEntity().getType());
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            /* Multiplicative, Makes the weapon deal 2x damage */
            skyblockPlayerDamageEntityEvent.addMultiplicativeMultiplierPercent(100);
        }
    };
    public static Ability LIGHTNING_ARMOR_ABILITY = new FullSetBonus() {
        @Override
        public String getName() {
            return "Lightning";
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>(List.of(
                    "$7Striking an enemy has a $a25% $7chance",
                    "$7when full set is equipped."
            ));
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            if (skyblockPlayerDamageEntityEvent.getSkyblockPlayer().getRandom().nextInt(100) > 25) return;

            skyblockPlayerDamageEntityEvent.getEntity().getWorld().strikeLightningEffect(skyblockPlayerDamageEntityEvent.getEntity().getLocation());
        }
    };

    public static class LAPIS_ARMOR_ABILITY implements FullSetBonus, PassiveAbility {
        @Override
        public String getName() {
            return "Health";
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>(List.of(
                    "$7Increases the wearer's maximum",
                    Stats.HEALTH.getSymbol() + "Health $7by $a60"
            ));
        }


        @Override
        public void onTick(SkyblockPlayer player) {
            double currentMaxHealth = player.getStatsManager().getMaxStats().get(Stats.HEALTH);
            player.getStatsManager().setMaxStat(Stats.HEALTH, currentMaxHealth + 60);
        }
    }

    public static abstract class TELEPORT_ABILITY implements ITriggerableAbility, IUsageCost {
        private final int range;

        public TELEPORT_ABILITY(int range) {
            this.range = range;
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            SkyblockPlayer skyblockPlayer = skyblockInteractEvent.getSkyblockPlayer();
            Player player = skyblockPlayer.getPlayer();

            // check if player is looking down, if so, don't teleport.

            List<Material> whitelistedMaterials = List.of(
                    Material.AIR,
                    Material.WATER,
                    Material.LAVA
            );

            Location start = player.getEyeLocation();
            Vector direction = start.getDirection();


            for (int i = 0; i < range; i++) {
                start.add(direction);

                Block block = start.getBlock();
                if (!whitelistedMaterials.contains(block.getType())) {
                    start.subtract(direction);
                    player.sendMessage(ChatColor.RED + "There are blocks in the way!");
                    break;
                }
            }

            start = start.getBlock().getLocation();
            start = start.add(0.5, 0, 0.5);
            start.setYaw(player.getLocation().getYaw());
            start.setPitch(player.getLocation().getPitch());


            player.teleport(start);
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return false;
            return skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK;
        }
    }

    public static abstract class SHORT_BOW implements ITriggerableAbility {
        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public String getName() {
            return "Shortbow";
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>(List.of(
                    "Shoot instantly. Lore not visible."
            ));
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.NONE;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return false;
            SkyblockItem skyblockItem = skyblockInteractEvent.getSkyblockPlayer().getInventoryManager().getSkyblockItemInHand();
            TriggerType triggerType = skyblockInteractEvent.getInteractType();
            boolean shortBow = skyblockItem instanceof IShortBow;
            boolean correctTrigger = triggerType == TriggerType.LEFT_CLICK || triggerType == TriggerType.RIGHT_CLICK;
            return shortBow && correctTrigger;
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            SkyblockPlayer skyblockPlayer = skyblockInteractEvent.getSkyblockPlayer();
            Player player = skyblockPlayer.getPlayer();

            player.getWorld().spawn(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(1.5)), ArmorStand.class, as -> {
                as.setInvisible(true);
                as.setInvulnerable(true);
                as.setMarker(true);
                as.setSmall(true);
                as.setCollidable(false);

                Arrow arrow = as.launchProjectile(Arrow.class);
                arrow.setShooter(player);

                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
                as.remove();
            });
        }
    }
}

