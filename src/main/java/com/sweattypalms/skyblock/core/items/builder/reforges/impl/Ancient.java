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

public class Ancient implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.STRENGTH, 4.0,
            Stats.CRIT_CHANCE, 3.0,
            Stats.HEALTH, 7.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 6.0
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.STRENGTH, 8.0,
            Stats.CRIT_CHANCE, 5.0,
            Stats.HEALTH, 7.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 9.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.STRENGTH, 12.0,
            Stats.CRIT_CHANCE, 7.0,
            Stats.HEALTH, 7.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 12.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.STRENGTH, 18.0,
            Stats.CRIT_CHANCE, 9.0,
            Stats.HEALTH, 7.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 16.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.STRENGTH, 25.0,
            Stats.CRIT_CHANCE, 12.0,
            Stats.HEALTH, 7.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 20.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.STRENGTH, 35.0,
            Stats.CRIT_CHANCE, 15.0,
            Stats.HEALTH, 7.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 25.0
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
        return "Ancient";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7Grants $a+1 " + Stats.CRIT_DAMAGE.getSymbol() + " Crit Damage",
                "$7per $cCatacombs $7level."
        ));
    }
}
