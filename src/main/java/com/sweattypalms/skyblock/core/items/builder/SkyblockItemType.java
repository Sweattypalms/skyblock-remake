package com.sweattypalms.skyblock.core.items.builder;

import lombok.Getter;
import org.bukkit.inventory.EquipmentSlot;

import javax.annotation.Nullable;

@Getter
public enum SkyblockItemType {
    SWORD,
    BOW,
    HELMET(EquipmentSlot.HEAD),
    CHESTPLATE(EquipmentSlot.CHEST),
    LEGGINGS(EquipmentSlot.LEGS),
    BOOTS(EquipmentSlot.FEET),
    ACCESSORY(null),
    WAND,
    PICKAXE,
    DRILL,
    AXE,
    NONE(null);

    @Nullable
    private final EquipmentSlot slot;
    SkyblockItemType(@Nullable EquipmentSlot slot) {
        this.slot = slot;
    }
    SkyblockItemType() {
        this.slot = EquipmentSlot.HAND;
    }
}
