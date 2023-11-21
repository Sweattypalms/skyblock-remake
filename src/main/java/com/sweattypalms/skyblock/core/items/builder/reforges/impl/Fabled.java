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

public class Fabled implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.STRENGTH, 30.0,
            Stats.CRIT_DAMAGE, 15.0 
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.STRENGTH, 35.0,
            Stats.CRIT_DAMAGE, 20.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.STRENGTH, 40.0,
            Stats.CRIT_DAMAGE, 25.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.STRENGTH, 50.0,
            Stats.CRIT_DAMAGE, 32.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.STRENGTH, 60.0,
            Stats.CRIT_DAMAGE, 40.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.STRENGTH, 75.0,
            Stats.CRIT_DAMAGE, 50.0
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
        return "Fabled";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7Critical hits have a chance to deal",
                "$7up to +15% extra damage."
        ));
    }
}
