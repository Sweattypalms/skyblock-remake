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

public class Loving implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.HEALTH, 4.0,
            Stats.DEFENSE, 4.0,
            Stats.INTELLIGENCE, 20.0
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.HEALTH, 5.0,
            Stats.DEFENSE, 5.0,
            Stats.INTELLIGENCE, 40.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.HEALTH, 6.0,
            Stats.DEFENSE, 6.0,
            Stats.INTELLIGENCE, 60.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.HEALTH, 8.0,
            Stats.DEFENSE, 7.0,
            Stats.INTELLIGENCE, 80.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.HEALTH, 10.0,
            Stats.DEFENSE, 10.0,
            Stats.INTELLIGENCE, 100.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.HEALTH, 14.0,
            Stats.DEFENSE, 14.0,
            Stats.INTELLIGENCE, 120.0
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
                SkyblockItemType.CHESTPLATE
        );
    }

    @Override
    public String getName() {
        return "Loving";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7Increased ability damage by ",
                "$a5%"
        ));
    }
}
