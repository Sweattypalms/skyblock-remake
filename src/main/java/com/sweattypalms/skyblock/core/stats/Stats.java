package com.sweattypalms.skyblock.core.stats;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Stats {
    HEALTH(ChatColor.RED + "❤", "Health", 100, false, ChatColor.GREEN),
    DEFENCE(ChatColor.GREEN + "❈", "Defence", 0, false, ChatColor.GREEN),
    INTELLIGENCE(ChatColor.AQUA + "✎", "Intelligence", 100, false, ChatColor.GREEN),
    DAMAGE(ChatColor.DARK_RED + "❁", "Damage", 0, false, ChatColor.RED),
    ;
    @Getter
    private final String symbol;
    @Getter
    private final String name;
    @Getter
    private final double baseValue;
    @Getter
    private final boolean isPercentage;
    @Getter
    private final String itemBuilderColor;

    <T> Stats(String symbol, String name, double baseValue, boolean isPercentage, T itemBuilderColor) {
        this.symbol = symbol;
        this.name = name;
        this.baseValue = baseValue;
        this.isPercentage = isPercentage;
        if (itemBuilderColor instanceof ChatColor)
            this.itemBuilderColor = ((ChatColor) itemBuilderColor).toString();
        else
            this.itemBuilderColor = (String) itemBuilderColor;
    }

    static double get(SkyblockPlayer skyblockPlayer, Stats stat) {
        return skyblockPlayer.getMaxStats().get(stat);
    }
}
