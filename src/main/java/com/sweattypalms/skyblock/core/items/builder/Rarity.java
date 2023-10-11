package com.sweattypalms.skyblock.core.items.builder;

import lombok.Getter;

@Getter
public enum Rarity {
    COMMON("§f"),
    UNCOMMON("§a"),
    RARE("§9"),
    EPIC("§5"),
    LEGENDARY("§6"),
    MYTHIC("§d"),
    DIVINE("§b"),
    SPECIAL("§c"),
    VERY_SPECIAL("§c");

    private final String color;

    Rarity(String color) {
        this.color = color;
    }

    public Rarity getUpgraded(){
        int current = this.ordinal();
        if (current == Rarity.values().length - 1)
            return Rarity.values()[current];

        return Rarity.values()[current + 1];
    }
}
