package com.sweattypalms.skyblock.core.items.builder;

import lombok.Getter;

public enum Rarity {
    COMMON("§f"),
    UNCOMMON("§a"),
    RARE("§9"),
    EPIC("§5"),
    LEGENDARY("§6"),
    MYTHIC("§d"),
    SUPREME("§c"),
    SPECIAL("§b"),
    VERY_SPECIAL("§d");

    @Getter
    private final String color;

    Rarity(String color) {
        this.color = color;
    }
}
