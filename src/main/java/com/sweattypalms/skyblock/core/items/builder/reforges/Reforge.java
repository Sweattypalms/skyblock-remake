package com.sweattypalms.skyblock.core.items.builder.reforges;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;

import java.util.Map;
import java.util.Set;

public interface Reforge {

    Map<Stats, Double> getReforgeStats(Rarity rarity);

    Set<SkyblockItemType> getReforgeableItemTypes();

    String getName();
}
