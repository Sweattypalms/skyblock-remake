package com.sweattypalms.skyblock.slayers;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import lombok.Getter;

public class SlayerDrop {

    @Getter
    private final SkyblockItem item;
    @Getter
    private final double chance;

    public SlayerDrop(SkyblockItem item, double chance) {
        this.item = item;
        this.chance = chance;
    }
}
