package com.sweattypalms.skyblock.core.mobs.builder;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;

import java.util.ArrayList;
import java.util.List;

public class MobLoot {
    private final List<SkyblockItem> getConfirmedDrops = new ArrayList<>();

    private final List<MobLootItem> getPotentialDrops = new ArrayList<>();

    public MobLoot() {}

    public MobLoot addConfirmedDrop(SkyblockItem skyblockItem) {
        getConfirmedDrops.add(skyblockItem);
        return this;
    }

    public MobLoot addPotentialDrop(SkyblockItem skyblockItem, double chance, int baseAmt) {
        getPotentialDrops.add(new MobLootItem(skyblockItem, chance, baseAmt));
        return this;
    }

    public record MobLootItem(SkyblockItem skyblockItem, double chance, int baseAmt){}
}
