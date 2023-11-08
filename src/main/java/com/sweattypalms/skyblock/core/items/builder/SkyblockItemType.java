package com.sweattypalms.skyblock.core.items.builder;

import lombok.Getter;
import org.bukkit.inventory.EquipmentSlot;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

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

    public static List<SkyblockItemType> getHandheld() {
        return Arrays.stream(SkyblockItemType.values()).filter(type -> type.getSlot() == EquipmentSlot.HAND).toList();
    }

    public static List<SkyblockItemType> getArmor() {
        return Arrays.stream(SkyblockItemType.values()).filter(type -> type.getSlot() != EquipmentSlot.HAND && type.getSlot() != null).toList();
    }
}
