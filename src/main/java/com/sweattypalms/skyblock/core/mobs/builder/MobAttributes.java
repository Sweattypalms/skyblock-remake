package com.sweattypalms.skyblock.core.mobs.builder;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum MobAttributes {
    LEVEL(new IAttribute.IntAttribute()),
    DAMAGE(new IAttribute.DoubleAttribute()),
    MAX_HEALTH(new IAttribute.DoubleAttribute()),
    DEFENSE(new IAttribute.DoubleAttribute()),
    SPEED(new IAttribute.IntAttribute()),
    AI_ENABLED(new IAttribute.BooleanAttribute()),
    FROZEN(new IAttribute.BooleanAttribute()),
    KNOCKBACK_RESISTANT(new IAttribute.BooleanAttribute()),

    COMBAT_XP(new IAttribute.DoubleAttribute()),
    COINS(new IAttribute.DoubleAttribute()),
    EXPERIENCE_ORBS(new IAttribute.DoubleAttribute()),
    ;


    private final IAttribute<?> attribute;

    MobAttributes(IAttribute<?> attribute) {
        this.attribute = attribute;
    }

    public static HashMap<MobAttributes, Object> getDefault() {
        HashMap<MobAttributes, Object> map = new HashMap<>();
        map.put(LEVEL, 1);
        map.put(DAMAGE, 0.0d);
        map.put(MAX_HEALTH, 100d);
        map.put(DEFENSE, 0.0d);
        map.put(SPEED, 100);
        map.put(AI_ENABLED, true);
        map.put(FROZEN, false);
        map.put(KNOCKBACK_RESISTANT, false);
        map.put(COMBAT_XP, 0.0d);
        map.put(COINS, 0.0d);
        map.put(EXPERIENCE_ORBS, 0.0d);

        return map;
    }
}
