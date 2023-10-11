package com.sweattypalms.skyblock.core.mobs.builder;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum NameAttributes {
    CUSTOM_NAME(new IAttribute.StringAttribute()),
    FORMATTED(new IAttribute.BooleanAttribute()),
    CUSTOM_NAME_VISIBLE(new IAttribute.BooleanAttribute()),
    SHOW_HP(new IAttribute.BooleanAttribute()),
    SHOW_LEVEL(new IAttribute.BooleanAttribute()),
    ;

    private final IAttribute<?> attribute;

    NameAttributes(IAttribute<?> attribute) {
        this.attribute = attribute;
    }

    public static HashMap<NameAttributes, Object> getDefault() {
        return new HashMap<>(Map.of(
                CUSTOM_NAME, "???",
                FORMATTED, false,
                CUSTOM_NAME_VISIBLE, true,
                SHOW_HP, true,
                SHOW_LEVEL, true
        ));
    }

}
