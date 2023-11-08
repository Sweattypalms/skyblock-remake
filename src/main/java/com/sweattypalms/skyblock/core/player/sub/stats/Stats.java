package com.sweattypalms.skyblock.core.player.sub.stats;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum Stats {
    DAMAGE(ChatColor.RED + "❁", "Damage", 0, false, ChatColor.RED),
    STRENGTH(ChatColor.RED + "❁", "Strength", 0, false, ChatColor.RED),
    CRIT_DAMAGE(ChatColor.BLUE + "☠", "Crit Damage", 0, true, ChatColor.RED),
    CRIT_CHANCE(ChatColor.BLUE + "☣", "Crit Chance", 0, true, ChatColor.RED),
    FEROCITY(ChatColor.RED + "⫽", "Ferocity", 0, false, ChatColor.RED),
    BONUS_ATTACK_SPEED(ChatColor.YELLOW + "⚔", "Bonus Attack Speed", 0, false, ChatColor.YELLOW),

    /* ---------------------------- */

    HEALTH(ChatColor.RED + "❤", "Health", 100, false, ChatColor.GREEN),
    DEFENSE(ChatColor.GREEN + "❈", "Defence", 0, false, ChatColor.GREEN),
    INTELLIGENCE(ChatColor.AQUA + "✎", "Intelligence", 100, false, ChatColor.GREEN),
    SPEED(ChatColor.WHITE + "✦", "Speed", 100, false, ChatColor.GREEN),

    /* ---------------------------- */

    HEALTH_REGEN(ChatColor.RED + "❣", "Health Regen", 100, false, ChatColor.RED, true),
    MANA_REGEN(ChatColor.AQUA + "❉", "Mana Regen", 100, false, ChatColor.AQUA, true),


    /* ---------------------------- */
    BOW_PULL(ChatColor.WHITE + "⇧", "Bow Pull", 0, false, ChatColor.WHITE, true), // => For bow crit calculation.
    SHORTBOW_SHOT_COOLDOWN( "?", "Shot Cooldown", 0, false, ChatColor.WHITE, true), // => For Short bow cooldown.

    CUSTOM("?", "Custom", 0, false, ChatColor.WHITE, true) // => For bonuses variable management.
    ;
    private final String symbol;
    private final String name;
    private final double baseValue;
    private final boolean isPercentage;
    private final String itemBuilderColor;
    private boolean privateField = false;

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
    <T> Stats(String symbol, String name, double baseValue, boolean isPercentage, T itemBuilderColor, boolean privateField){
        this(symbol, name, baseValue, isPercentage, itemBuilderColor);
        this.privateField = privateField;
    }

    static double get(SkyblockPlayer skyblockPlayer, Stats stat) {
        return skyblockPlayer.getStatsManager().getMaxStats().get(stat);
    }
}
