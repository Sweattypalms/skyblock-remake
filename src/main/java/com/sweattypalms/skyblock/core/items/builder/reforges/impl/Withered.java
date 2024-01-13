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

public class Withered implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.STRENGTH, 60.0
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.STRENGTH, 75.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.STRENGTH, 90.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.STRENGTH, 110.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.STRENGTH, 135.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.STRENGTH, 170.0
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
                SkyblockItemType.SWORD
        );
    }

    @Override
    public String getName() {
        return "Withered";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7Grants $a+1 " + Stats.STRENGTH.getSymbol() + " Strength",
                "$7per $cCatacombs $7level."
        ));
    }
}
