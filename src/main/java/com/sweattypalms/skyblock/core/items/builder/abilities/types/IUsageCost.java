package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.player.sub.Stats;

import java.util.Map;

public interface IUsageCost {
    Map<Stats, Double> getCost();
}
