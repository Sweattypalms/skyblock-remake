package com.sweattypalms.skyblock.core.mobs.builder;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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
    ;


    private IAttribute<?> attribute;

    MobAttributes(IAttribute<?> attribute) {
        this.attribute = attribute;
    }

    public static HashMap<MobAttributes, Object> getDefault(){
        return new HashMap<>(Map.of(
                LEVEL, 1,
                DAMAGE, 0.0d,
                MAX_HEALTH, 100d,
                DEFENSE, 0.0d,
                SPEED, 100,
                AI_ENABLED, true,
                FROZEN, false,
                KNOCKBACK_RESISTANT, false
        ));
    }
}
