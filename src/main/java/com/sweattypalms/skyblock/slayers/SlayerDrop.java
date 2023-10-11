package com.sweattypalms.skyblock.slayers;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import lombok.Getter;

@Getter
public class SlayerDrop {

    private final SkyblockItem item;
    private final double chance;

    public SlayerDrop(SkyblockItem item, double chance) {
        this.item = item;
        this.chance = chance;
    }
}
