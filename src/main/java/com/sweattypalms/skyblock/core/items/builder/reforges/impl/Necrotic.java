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

public class Necrotic implements Reforge, IAdvancedReforge {
    Map<Stats, Double> COMMON = Map.of(
            Stats.INTELLIGENCE, 30.0
    );
    Map<Stats, Double> UNCOMMON = Map.of(
            Stats.INTELLIGENCE, 60.0
    );
    Map<Stats, Double> RARE = Map.of(
            Stats.INTELLIGENCE, 90.0
    );
    Map<Stats, Double> EPIC = Map.of(
            Stats.INTELLIGENCE, 120.0
    );
    Map<Stats, Double> LEGENDARY = Map.of(
            Stats.INTELLIGENCE, 150.0
    );
    Map<Stats, Double> MYTHIC = Map.of(
            Stats.INTELLIGENCE, 200.0
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
        return "Necrotic";
    }

    @Override
    public List<String> getLore() {
        return PlaceholderFormatter.format(List.of(
                "$7yeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeehaw!"
        ));
    }
}
