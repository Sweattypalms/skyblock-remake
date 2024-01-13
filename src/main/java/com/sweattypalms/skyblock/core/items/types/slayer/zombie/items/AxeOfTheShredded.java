package com.sweattypalms.skyblock.core.items.types.slayer.zombie.items;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.DamageAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ICooldown;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import net.minecraft.util.Tuple;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

public class AxeOfTheShredded extends SkyblockItem implements IHasAbility {

    public static final String ID = "axe_of_the_shredded";

    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.DAMAGE, 140d,
            Stats.STRENGTH, 115d
    ));


    public AxeOfTheShredded() {
        super(
                ID,
                "Axe of the Shredded",
                Material.DIAMOND_AXE,
                List.of(
                        "$7Heal $c50" + Stats.HEALTH.getSymbol() + "$7per hit.",
                        "$7Deal $a+250% $7damage to Zombies.",
                        "$7Receive $a+25% $7less damage",
                        "$7from Zombies when held."
                ),
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new ThrowAbility()
        );
    }

    public static class ThrowAbility implements ITriggerableAbility, IUsageCost, ICooldown {

        /**
         * Format: <stacks>:<last used>
         * The AOTS combo expires after 2 seconds of not using it
         * The stacks are used to calculate the damage and mana cost
         */
        private static final HashMap<UUID, String> combo = new HashMap<>();
        private static final int OVER_COOLDOWN = 2000;

        /**
         * Format: <stacks>:<last used>
         *
         * @param toFormat the string to format
         * @return the formatted string; Tuple<stacks, last used>
         */
        private static Tuple<Integer, Long> fmt(String toFormat) {
            String[] split = toFormat.split(":");
            return new Tuple<>(Integer.parseInt(split[0]), Long.parseLong(split[1]));
        }

        private static void updateCombo(SkyblockPlayer skyblockPlayer) {
            UUID uuid = skyblockPlayer.getPlayer().getUniqueId();
            String comboData = combo.get(uuid);
            Tuple<Integer, Long> fmt = fmt(comboData);

            int stacks = fmt.a();
            long lastUsed = fmt.b();

            if (System.currentTimeMillis() - lastUsed > OVER_COOLDOWN) {
                combo.put(uuid, "1:" + System.currentTimeMillis());
                return;
            }

            combo.put(uuid, Math.min(stacks + 1, 16) + ":" + System.currentTimeMillis());
        }

        @Override
        public String getName() {
            return "Throw";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Throw your axe damaging all",
                    "$7enemies in its path dealing",
                    "$c10% $7melee damage.",
                    "$7Consecutive throws stack $c2x",
                    "$7damage but cost $92x $7mana up",
                    "$7to 16x"
            );
        }

        @Override
        public long getCooldown(SkyblockPlayer skyblockPlayer) {
            return 300;
        }

        @Override
        public void apply(Event _e) {
            if (!(_e instanceof SkyblockInteractEvent event)) return;
            ICooldown.super.apply(_e);

            SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();

            combo.putIfAbsent(skyblockPlayer.getPlayer().getUniqueId(), "0:" + System.currentTimeMillis());

            updateCombo(skyblockPlayer);

            Tuple<Integer, Long> fmt = fmt(combo.get(skyblockPlayer.getPlayer().getUniqueId()));

            int stacks = fmt.a();

            Player player = skyblockPlayer.getPlayer();

            ArmorStand armorstand = player.getWorld().spawn(player.getLocation().add(0, 0.5, 0), ArmorStand.class, lambdaArmorstand -> {
                lambdaArmorstand.setArms(true);
                lambdaArmorstand.setGravity(false);
                lambdaArmorstand.setVisible(false);
                lambdaArmorstand.setInvulnerable(true);
                lambdaArmorstand.setSmall(true);
                lambdaArmorstand.setMarker(true);

                EulerAngle eulerAngle = new EulerAngle(Math.toRadians(90), 0, 0);
                lambdaArmorstand.setRightArmPose(eulerAngle);

                ItemStack itemStack = new ItemStack(Material.DIAMOND_AXE);
                assert lambdaArmorstand.getEquipment() != null;
                lambdaArmorstand.getEquipment().setItemInMainHand(itemStack);
            });

            Location eyeLocation = player.getLocation();
            int shouldGo = 50; // The distance the axe should go
            Location destination = eyeLocation.clone().add(eyeLocation.getDirection().multiply(shouldGo));

            Vector vector = destination.subtract(eyeLocation).toVector(); // Vector from eyeLocation to destination

            Vector normalized = vector.clone().normalize();

            ArrayList<LivingEntity> hitEntities = new ArrayList<>();

            new BukkitRunnable() {
                @Override
                public void run() {
                    EulerAngle rotation = armorstand.getRightArmPose();
                    EulerAngle newRotation = rotation.add(20, 0, 0);
                    armorstand.setRightArmPose(newRotation);

                    armorstand.teleport(armorstand.getLocation().add(normalized));


                    Block targetBlock = armorstand.getTargetBlockExact(1);

                    if (targetBlock != null && !targetBlock.isPassable()) {
                        armorstand.remove();
                        cancel();
                    }

                    Location currentLocation = armorstand.getLocation();
                    Vector currentVector = currentLocation.subtract(eyeLocation).toVector(); // Vector from eyeLocation to currentLocation

                    if (currentVector.length() > vector.length()) {
                        armorstand.remove();
                        cancel();
                    }

                    // TODO: Implement hit detection and damage
                    double range = 1.2;
                    List<LivingEntity> nearbyEntities = armorstand.getNearbyEntities(range, range, range)
                            .stream().filter(entity -> entity instanceof LivingEntity && !(entity instanceof ArmorStand) && !(entity instanceof Player))
                            .map(entity -> (LivingEntity) entity).toList();

                    nearbyEntities.forEach(entity -> {
                        if (hitEntities.contains(entity)) return;
                        hitEntities.add(entity);

                        SkyblockPlayerDamageEntityEvent damageEntityEvent = new SkyblockPlayerDamageEntityEvent(entity, player, SkyblockPlayerDamageEntityEvent.DamageType.ABILITY);
                        damageEntityEvent.addMultiplicativeMultiplierPercent(stacks * 100);
                        damageEntityEvent.setAbility(ThrowAbility.this);
                        damageEntityEvent.setAbilityItem(skyblockPlayer.getInventoryManager().getItemInHand());
                        damageEntityEvent.setPreCalc(true);
                        damageEntityEvent.setApplyFerocityOnAbility(true);

                        Bukkit.getPluginManager().callEvent(damageEntityEvent);
                    });
                }
            }.runTaskTimer(SkyBlock.getInstance(), 0, 2);


        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockInteractEvent skyblockInteractEvent &&
                    skyblockInteractEvent.getInteractType() == this.getTriggerType() &&
                    !isOnCooldown(skyblockInteractEvent.getSkyblockPlayer());
        }

        @Override
        public Map<Stats, Double> getCost(SkyblockPlayer skyblockPlayer) {
            double stacks;
            try {
                stacks = fmt(combo.get(skyblockPlayer.getPlayer().getUniqueId())).a();
            } catch (NullPointerException e) {
                return getCost();
            }

            double manaCost = 20d * stacks;

            return Map.of(
                    Stats.INTELLIGENCE, manaCost
            );
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of(
                    Stats.INTELLIGENCE, 20d
            );

        }
    }
    public static class AxeOfTheShreddedHealAndExtraDamage implements DamageAbility {
        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public String getName() {
            return "AxeOfTheShreddedHealAndExtraDamage";
        }

        @Override
        public List<String> getDescription() {
            return List.of("AxeOfTheShreddedHealAndExtraDamage");
        }

        @Override
        public void apply(Event event) {

        }

        @Override
        public boolean preCalc() {
            return false;
        }

        @Override
        public boolean trigger(Event event) {
            return false;
        }
    }
}
