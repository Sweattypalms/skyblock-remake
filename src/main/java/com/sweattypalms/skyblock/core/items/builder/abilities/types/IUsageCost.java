package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;

import java.util.Map;

public interface IUsageCost {
    /**
     * Get the cost of using the ability for the given player
     * @param skyblockPlayer the player to get the cost for
     * @return a map of stats to the cost of using the ability
     */
    default Map<Stats, Double> getCost(SkyblockPlayer skyblockPlayer) {
        return getCost();
    }

    /**
     * Default cost
     * @return a map of stats to the cost of using the ability
     */
    Map<Stats, Double> getCost();
}
