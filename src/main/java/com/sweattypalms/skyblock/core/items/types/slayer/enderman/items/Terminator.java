package com.sweattypalms.skyblock.core.items.types.slayer.enderman.items;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockProjectileLaunchEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockProjectilePrelaunchEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.DamageAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.PassiveAbility;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.bonus.Bonus;
import com.sweattypalms.skyblock.core.player.sub.bonus.BonusManager;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import com.sweattypalms.skyblock.core.player.sub.stats.StatsManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Terminator extends SkyblockItem implements IHasAbility, IShortBow {
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.DAMAGE, 310d,
            Stats.STRENGTH, 50d,
            Stats.CRIT_DAMAGE, 250d
    ));

    public Terminator() {
        super(
                "terminator",
                "Terminator",
                Material.BOW,
                null,
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.BOW
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new TerminatorShortBow(),
                new TerminatorCritChancePassive(),
                new TerminatorHitStack(),
                new TerminatorSalvation(),
                new TerminatorSalvationHitMultiplier()
        );
    }

    @Override
    public List<String> getShortBowDescription() {
        return List.of(
                "$7Shoots $b3 $7arrows at once.",
                "$7Can damage enderman.",
                "$7",
                "$cDivides your " + Stats.CRIT_CHANCE.getSymbol() + " Crit Chance $cby 4!"
        );
    }

    public static class TerminatorShortBow extends AbilityManager.SHORT_BOW {
        @Override
        public boolean nameVisible() {
            return super.nameVisible();
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            SkyblockPlayer skyblockPlayer = skyblockInteractEvent.getSkyblockPlayer();
            Player player = skyblockPlayer.getPlayer();

            // Don't execute, so the other Salvation ability can.
            List<Bonus> stack = skyblockPlayer.getBonusManager().getBonuses().getOrDefault("terminator.salvation", List.of());
            if (stack.size() >= 3 && skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK) {
                return;
            }

            SkyblockProjectilePrelaunchEvent tryLaunchProjectile = new SkyblockProjectilePrelaunchEvent(skyblockPlayer);

            Bukkit.getPluginManager().callEvent(tryLaunchProjectile);

            if (tryLaunchProjectile.isCancelled()) return;

            player.getWorld().spawn(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(1.5)), ArmorStand.class, as -> {
                as.setInvisible(true);
                as.setInvulnerable(true);
                as.setMarker(true);
                as.setSmall(true);
                as.setCollidable(false);


                Arrow arrow = as.launchProjectile(Arrow.class);
                Arrow arrowRight = as.getWorld().spawn(as.getEyeLocation(), Arrow.class);
                Arrow arrowLeft = as.getWorld().spawn(as.getEyeLocation(), Arrow.class);

                SkyblockProjectileLaunchEvent<Arrow> skyblockProjectileLaunchEvent = new SkyblockProjectileLaunchEvent<>(skyblockPlayer, List.of(arrow, arrowRight, arrowLeft));

                if (skyblockProjectileLaunchEvent.isCancelled()) {
                    as.remove();
                    arrow.remove();
                    arrowRight.remove();
                    arrowLeft.remove();
                    return;
                }

                arrow.setShooter(player);
                arrowRight.setShooter(player);
                arrowLeft.setShooter(player);

                arrow.setBounce(false);
                arrowRight.setBounce(false);
                arrowLeft.setBounce(false);

                arrowRight.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(5)));
                arrowLeft.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-5)));

                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
                as.remove();
            });
        }
    }

    public static class TerminatorCritChancePassive implements PassiveAbility {
        @Override
        public void onTick(SkyblockPlayer player) {
            StatsManager statsManager = player.getStatsManager();
            double critChance = statsManager.getMaxStats().get(Stats.CRIT_CHANCE);
            statsManager.setMaxStat(Stats.CRIT_CHANCE, critChance / 4);
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public String getName() {
            return "_";
        }

        @Override
        public List<String> getDescription() {
            return List.of();
        }
    }

    public static class TerminatorHitStack implements DamageAbility {
        @Override
        public boolean preCalc() {
            return false;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent
                    && skyblockPlayerDamageEntityEvent.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ARROW;
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public String getName() {
            return "";
        }

        @Override
        public List<String> getDescription() {
            return List.of();
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            SkyblockPlayer skyblockPlayer = skyblockPlayerDamageEntityEvent.getSkyblockPlayer();
            Bonus bonus = new Bonus(Stats.CUSTOM, 1, 100000000);
            skyblockPlayer.getBonusManager().setStackingBonus("terminator.salvation", bonus);
        }
    }

    public static class TerminatorSalvation implements ITriggerableAbility, IUsageCost {
        @Override
        public TriggerType getTriggerType() {
            return TriggerType.LEFT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return false;
            return skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK;
        }

        @Override
        public String getName() {
            return "Salvation";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Can be casted after landing $63 $7hits.",
                    "$7Shoot a beam, penetrating up",
                    "$7to $e5 $7foes and dealing $c2x",
                    "$7the damage an arrow would.",
                    "$7The beam always crits."
            );
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;

            SkyblockPlayer skyblockPlayer = skyblockInteractEvent.getSkyblockPlayer();

            ItemStack activatorItem = skyblockPlayer.getInventoryManager().getItemInHand();

            BonusManager bonusManager = skyblockPlayer.getBonusManager();
            List<Bonus> stack = bonusManager.getBonuses().getOrDefault("terminator.salvation", List.of());

            if (stack.size() < 3) return;

            bonusManager.getBonuses("terminator.salvation").forEach(bonus -> bonus.setExpiryTime(0));
            bonusManager.cleanupExpiredBonuses();

            ArmorStand armorstand = createInvisibleArmorStand(skyblockPlayer.getPlayer());

            ArrayList<LivingEntity> cleansedEntities = new ArrayList<>();

            int blocksToMove = 25;
            for (int i = 0; i < blocksToMove; i++) {
                moveArmorStandForward(armorstand);

                if (isArmorStandBlocked(armorstand)) {
                    armorstand.remove();
                    break;
                }

                damageNearbyEntities(skyblockPlayer.getPlayer(), activatorItem, armorstand, cleansedEntities);
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.getInstance(), armorstand::remove, 40L);
        }

        private ArmorStand createInvisibleArmorStand(Player p) {
            return p.getWorld().spawn(p.getEyeLocation(), ArmorStand.class, as -> {
                as.setInvisible(true);
                as.setInvulnerable(true);
                as.setMarker(true);
                as.setSmall(true);
                as.setCollidable(false);
            });
        }

        private void moveArmorStandForward(ArmorStand armorstand) {
            armorstand.teleport(armorstand.getLocation().add(armorstand.getLocation().getDirection().normalize()));
            armorstand.getWorld().spawnParticle(Particle.DRIP_LAVA, armorstand.getLocation(), 4);
            armorstand.getWorld().spawnParticle(Particle.REDSTONE, armorstand.getLocation(), 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.RED, 1));
        }

        private boolean isArmorStandBlocked(ArmorStand armorstand) {
            Block block = armorstand.getTargetBlockExact(1);
            return block != null && !block.isPassable();
        }

        private void damageNearbyEntities(Player p, ItemStack activatorItem, ArmorStand armorstand, ArrayList<LivingEntity> cleansedEntities) {
            armorstand.getNearbyEntities(2, 1, 2).forEach(ent_ -> {
                if (!(ent_ instanceof LivingEntity ent) && !(ent_ instanceof EnderDragonPart)) return;

                LivingEntity ent = ent_ instanceof EnderDragonPart ? ((EnderDragonPart) ent_).getParent() : (LivingEntity) ent_;

                if (ent == p) return;

                if (cleansedEntities.contains(ent)) return;

                cleansedEntities.add(ent);

                SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent = new SkyblockPlayerDamageEntityEvent(
                        ent,
                        p,
                        SkyblockPlayerDamageEntityEvent.DamageType.ABILITY
                );
                skyblockPlayerDamageEntityEvent.setForcedCrit(true);
                skyblockPlayerDamageEntityEvent.setAbilityItem(activatorItem);
                Bukkit.getPluginManager().callEvent(skyblockPlayerDamageEntityEvent);
            });
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of();
        }
    }

    public static class TerminatorSalvationHitMultiplier implements DamageAbility {
        @Override
        public boolean preCalc() {
            return true;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent
                    && skyblockPlayerDamageEntityEvent.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ABILITY;
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public String getName() {
            return "null";
        }

        @Override
        public List<String> getDescription() {
            return List.of();
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            skyblockPlayerDamageEntityEvent.addMultiplicativeMultiplierPercent(100);
        }
    }
}
