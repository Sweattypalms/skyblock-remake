package com.sweattypalms.skyblock.core.mobs.builder.dragons.loot;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;

public interface IDragonLoot {
    public SkyblockItem getDropItem();
    public SkyblockPlayer getDropOwner();
}
