package com.sweattypalms.skyblock.core.items.types.slayer.zombie.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScytheBlade extends SkyblockItem implements IHeadHelmet {
    public static final String ID = "scythe_blade";

    public ScytheBlade() {
        super(
                ID,
                "Scythe Blade",
                Material.DIAMOND,
                null,
                new HashMap<>(),
                Rarity.LEGENDARY,
                SkyblockItemType.NONE
        );
    }
    @Override
    public String getTexture() {
        return null;
    }
}


