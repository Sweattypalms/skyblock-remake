package com.sweattypalms.skyblock.core.helpers;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IHasAbilityDamage;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DamageCalculator {

    public static double calculateNormalDamage(SkyblockPlayerDamageEntityEvent event) {
        SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();
        SkyblockMob skyblockMob = event.getSkyblockMob();

        Player player = skyblockPlayer.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        boolean crit = true;

        if (event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.MELEE || event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ARROW && event.getSkyblockPlayer().getInventoryManager().getSkyblockItemInHand() instanceof IShortBow) {
            double critChance = event.getSkyblockPlayer().getStatsManager().getMaxStats().get(Stats.CRIT_CHANCE);
            double random = event.getSkyblockPlayer().getRandom().nextDouble() * 100;
            crit = random <= critChance;
        } else if (event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ARROW) {
            if (event.getSkyblockPlayer().getStatsManager().getMaxStats().get(Stats.BOW_PULL) < 1) {
                crit = false;
            }
        }

        event.setCrit(crit);

        if (event.isForcedCrit()) {
            event.setCrit(true);
            crit = true;
        }


        double entityDefense = skyblockMob.getAttribute(MobAttributes.DEFENSE);

        double baseDamage = PDCHelper.getDouble(item, "stat." + Stats.DAMAGE.name().toLowerCase());
        double strength = skyblockPlayer.getStatsManager().getMaxStats().get(Stats.STRENGTH);
        double critDamage = skyblockPlayer.getStatsManager().getMaxStats().get(Stats.CRIT_DAMAGE);

        double additiveMultiplier = event.getAdditiveMultiplier();
        double multiplicativeMultiplier = event.getMultiplicativeMultiplier();

        ItemStack itemInHand = skyblockPlayer.getInventoryManager().getItemInHand();
        if (itemInHand != null && itemInHand.getType() == Material.BOW && event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.MELEE) {
            baseDamage = -4; // => So that the damage becomes kind of melee. gets multiplied by 1
        }

        if (!crit) critDamage = 0;

        double finalDamage = (5 + baseDamage) * (1 + (strength / 100)) * (1 + (critDamage / 100)) * (1 + (additiveMultiplier / 100)) * multiplicativeMultiplier;

        return finalDamage * (1 - calculateDamageReduction(entityDefense));
    }

    public static double calculateAbilityDamage(SkyblockPlayerDamageEntityEvent event) {
        if (event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ABILITY && event.getAbilityItem() == null) {
            throw new IllegalArgumentException("Ability must have an ability activator");
        }

        if (!(event.getAbility() instanceof IHasAbilityDamage abilityActivator)) {
            return calculateNormalDamage(event);
        }

        SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();

        boolean crit = event.isForcedCrit();

        event.setCrit(crit);

        double entityDefense = event.getSkyblockMob().getAttribute(MobAttributes.DEFENSE);

        double baseAbilityDamage = abilityActivator.getBaseAbilityDamage();
        double abilityScaling = abilityActivator.getAbilityScaling();

        double critDamage = skyblockPlayer.getStatsManager().getMaxStats().get(Stats.CRIT_DAMAGE);
        double intelligence = skyblockPlayer.getStatsManager().getMaxStats().get(Stats.INTELLIGENCE);

        double additiveMultiplier = event.getAdditiveMultiplier();
        double multiplicativeMultiplier = event.getMultiplicativeMultiplier();

        if (!crit) critDamage = 0;

        double finalDamage = baseAbilityDamage * (1 + (intelligence / 100) * abilityScaling) * (1 + (critDamage / 100)) * (1 + (additiveMultiplier / 100)) * multiplicativeMultiplier;

        return finalDamage * (1 - calculateDamageReduction(entityDefense));
    }

    public static double calculateDamageReduction(double defense) {
        return defense / (float) (defense + 100);
    }

    public static double calculateDamageReduction(double defense, double damage) {
        return damage * (1 - calculateDamageReduction(defense));
    }

    public static int getShortbowCooldown(SkyblockPlayer skyblockPlayer) {
        double ats = skyblockPlayer.getStatsManager().getMaxStats().get(Stats.BONUS_ATTACK_SPEED);
        if (ats > 67) return 200;
        if (ats > 41) return 250;
        if (ats > 12) return 350;

        return 450;
    }

    public static String getShortbowCooldownFmt(SkyblockPlayer skyblockPlayer) {
        int attackSpeed = getShortbowCooldown(skyblockPlayer);
        double seconds = attackSpeed / 1000.0;

        return String.format("%.1fs", seconds);
    }
}
