package com.sweattypalms.skyblock.core.player;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Stats {
    DAMAGE(ChatColor.RED + "❁", "Damage", 0, false, ChatColor.RED),
    STRENGTH(ChatColor.RED + "❁", "Strength", 0, false, ChatColor.RED),
    CRIT_DAMAGE(ChatColor.BLUE + "☠", "Crit Damage", 0, false, ChatColor.RED),
    CRIT_CHANCE(ChatColor.BLUE + "☣", "Crit Chance", 0, true, ChatColor.RED),
    FEROCITY(ChatColor.RED + "⫽", "Ferocity", 0, false, ChatColor.RED),

    /* ---------------------------- */

    HEALTH(ChatColor.RED + "❤", "Health", 100, false, ChatColor.GREEN),
    DEFENCE(ChatColor.GREEN + "❈", "Defence", 0, false, ChatColor.GREEN),
    INTELLIGENCE(ChatColor.AQUA + "✎", "Intelligence", 100, false, ChatColor.GREEN),
    SPEED(ChatColor.WHITE + "✦", "Speed", 100, false, ChatColor.GREEN),

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
