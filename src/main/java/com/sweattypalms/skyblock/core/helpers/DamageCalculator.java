package com.sweattypalms.skyblock.core.helpers;

import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.mobs.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.Stats;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DamageCalculator {


    public static double calculateNormalDamage(SkyblockPlayerDamageEntityEvent event){
        SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();
        SkyblockMob skyblockMob = event.getSkyblockMob();

        Player player = skyblockPlayer.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        double entityDefense = skyblockMob.getDefense();

        double baseDamage = PDCHelper.getOrDefault(item, Stats.DAMAGE.name().toLowerCase(), 0d);
        double strength = skyblockPlayer.getMaxStats().get(Stats.STRENGTH);
        double critDamage = skyblockPlayer.getMaxStats().get(Stats.CRIT_DAMAGE);

        double additiveMultiplier = event.getAdditiveMultiplier();
        double multiplicitiveMultiplier = event.getMultiplicativeMultiplier();

        double finalDamage = (5 + baseDamage)
                * (1 + (strength / 100))
                * (1 + (critDamage / 100))
                * (1 + (additiveMultiplier / 100))
                * multiplicitiveMultiplier;

        return finalDamage * (1 - calculateDamageReduction(entityDefense));
    }

    public static double calculateAbilityDamage(SkyblockPlayer skyblockPlayer, SkyblockMob skyblockMob){
        // TODO: Implement ability damage
        return 0d;
    }

    public static double calculateDamageReduction(double defense) {
        return defense / (defense + 100);
    }
}
