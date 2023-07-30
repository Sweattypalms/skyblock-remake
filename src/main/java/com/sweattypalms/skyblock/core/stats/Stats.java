package com.sweattypalms.skyblock.core.stats;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Stats {
    HEALTH( ChatColor.RED + "❤", "Health", 100),
    DEFENCE( ChatColor.GREEN + "❈", "Defence", 0),
    INTELLIGENCE( ChatColor.AQUA + "✎", "Intelligence", 100);

    @Getter
    private final String symbol;
    @Getter
    private final String name;
    @Getter
    private final double baseValue;

    Stats(String symbol, String name, double baseValue){
        this.symbol = symbol;
        this.name = name;
        this.baseValue = baseValue;
    }

    static double get(SkyblockPlayer skyblockPlayer, Stats stat){
        return skyblockPlayer.getMaxStats().get(stat);
    }
}
