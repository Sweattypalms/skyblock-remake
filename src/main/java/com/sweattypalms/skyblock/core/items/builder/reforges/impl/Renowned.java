package com.sweattypalms.skyblock.core.items.builder.reforges.impl;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.reforges.IAdvancedReforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.Reforge;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Renowned implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.STRENGTH, 3.0,
            Stats.CRIT_CHANCE, 2.0,
            Stats.CRIT_DAMAGE, 3.0,
            Stats.BONUS_ATTACK_SPEED, 1.0,
            Stats.HEALTH, 2.0,
            Stats.DEFENSE, 2.0,
            Stats.SPEED, 1.0,
            Stats.INTELLIGENCE, 3.0
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.STRENGTH, 4.0,
            Stats.CRIT_CHANCE, 4.0,
            Stats.CRIT_DAMAGE, 4.0,
            Stats.BONUS_ATTACK_SPEED, 1.0,
            Stats.HEALTH, 3.0,
            Stats.DEFENSE, 3.0,
            Stats.SPEED, 1.0,
            Stats.INTELLIGENCE, 4.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.STRENGTH, 6.0,
            Stats.CRIT_CHANCE, 6.0,
            Stats.CRIT_DAMAGE, 6.0,
            Stats.BONUS_ATTACK_SPEED, 2.0,
            Stats.HEALTH, 4.0,
            Stats.DEFENSE, 4.0,
            Stats.SPEED, 1.0,
            Stats.INTELLIGENCE, 6.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.STRENGTH, 8.0,
            Stats.CRIT_CHANCE, 8.0,
            Stats.CRIT_DAMAGE, 8.0,
            Stats.BONUS_ATTACK_SPEED, 3.0,
            Stats.HEALTH, 6.0,
            Stats.DEFENSE, 6.0,
            Stats.SPEED, 1.0,
            Stats.INTELLIGENCE, 8.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.STRENGTH, 10.0,
            Stats.CRIT_CHANCE, 10.0,
            Stats.CRIT_DAMAGE, 10.0,
            Stats.BONUS_ATTACK_SPEED, 4.0,
            Stats.HEALTH, 8.0,
            Stats.DEFENSE, 8.0,
            Stats.SPEED, 1.0,
            Stats.INTELLIGENCE, 10.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.STRENGTH, 12.0,
            Stats.CRIT_CHANCE, 12.0,
            Stats.CRIT_DAMAGE, 12.0,
            Stats.BONUS_ATTACK_SPEED, 5.0,
            Stats.HEALTH, 10.0,
            Stats.DEFENSE, 10.0,
            Stats.SPEED, 1.0,
            Stats.INTELLIGENCE, 12.0
    );

    @Override
    public Map<Stats, Double> getReforgeStats(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> this.COMMON;
            case UNCOMMON -> this.UNCOMMON;
            case RARE -> this.RARE;
            case EPIC -> this.EPIC;
            case LEGENDARY -> this.LEGENDARY;
            default -> this.MYTHIC;
        };
    }

    @Override
    public Set<SkyblockItemType> getReforgeableItemTypes() {
        return Set.of(
                SkyblockItemType.HELMET,
                SkyblockItemType.CHESTPLATE,
                SkyblockItemType.LEGGINGS,
                SkyblockItemType.BOOTS
        );
    }

    @Override
    public String getName() {
        return "Spiked";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7Increase all stats by 1%" //again calculation isnt a thing pls be patient
        ));
    }
}
