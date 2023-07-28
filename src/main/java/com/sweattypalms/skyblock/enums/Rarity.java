package com.sweattypalms.skyblock.enums;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHIC(ChatColor.LIGHT_PURPLE),
    SUPREME(ChatColor.DARK_RED),
    SPECIAL(ChatColor.RED),
    VERY_SPECIAL(ChatColor.RED),
    EXCLUSIVE(ChatColor.GRAY);


    private final ChatColor color;

    Rarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }

    public Rarity upgrade() {
        return values()[Math.min(this.ordinal() + 1, values().length - 1)];
    }

    public Rarity downgrade() {
        if (this.ordinal() - 1 < 0)
            return this;
        return values()[this.ordinal() - 1];
    }

    public boolean isAtLeast(Rarity rarity) {
        return ordinal() >= rarity.ordinal();
    }

    public String getDisplay() {
        return "" + color + ChatColor.BOLD + name().replaceAll("_", " ");
    }

    public String getBoldedColor() {
        return "" + color + ChatColor.BOLD;
    }

    public static Rarity getRarity(String string) {
        try {
            return Rarity.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
