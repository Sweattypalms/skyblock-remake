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

public class Precise implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.STRENGTH, 3.0,
            Stats.CRIT_CHANCE, 8.0,
            Stats.CRIT_DAMAGE, 5.0
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.STRENGTH, 7.0,
            Stats.CRIT_CHANCE, 9.0,
            Stats.CRIT_DAMAGE, 10.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.STRENGTH, 12.0,
            Stats.CRIT_CHANCE, 10.0,
            Stats.CRIT_DAMAGE, 18.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.STRENGTH, 18.0,
            Stats.CRIT_CHANCE, 11.0,
            Stats.CRIT_DAMAGE, 32.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.STRENGTH, 25.0,
            Stats.CRIT_CHANCE, 13.0,
            Stats.CRIT_DAMAGE, 50.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.STRENGTH, 34.0,
            Stats.CRIT_CHANCE, 15.0,
            Stats.CRIT_DAMAGE, 70.0
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
                SkyblockItemType.BOW
        );
    }

    @Override
    public String getName() {
        return "Precise";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7Deal 10% extra damage when",
                "$7arrows hit the head of a mob."
        ));
    }
}
