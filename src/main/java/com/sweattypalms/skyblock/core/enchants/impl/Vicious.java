package com.sweattypalms.skyblock.core.enchants.impl;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;

import java.util.List;

public class Vicious extends Enchantment {
    public Vicious() {
        super("vicious", 7);
    }

    @Override
    public String getName() {
        return "Vicious";
    }

    @Override
    public List<String> getDescription(int level) {
        return List.of(
                "Grants $c" + level + Stats.FEROCITY.getSymbol() + "Ferocity"
        );
    }

    @Override
    public List<SkyblockItemType> getApplicableItems() {
        return SkyblockItemType.getHandheld();
    }
}
