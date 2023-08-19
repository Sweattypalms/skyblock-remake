package com.sweattypalms.skyblock.core.items.builder.abilities;

import org.bukkit.event.block.Action;

public enum TriggerType {
    NONE,
    RIGHT_CLICK,
    LEFT_CLICK;

    public static TriggerType getTriggerType(Action action) {
        return switch (action) {
            case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> RIGHT_CLICK;
            case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> LEFT_CLICK;
            default -> NONE;
        };
    }
}
