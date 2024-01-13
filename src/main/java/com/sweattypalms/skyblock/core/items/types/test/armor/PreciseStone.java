package com.sweattypalms.skyblock.core.items.types.test.armor;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.List;

public class PreciseStone extends SkyblockItem implements IHeadHelmet {

    public static final String ID = "test_precisestone";

    public PreciseStone() {
        super(
                ID,
                "Precise Stone",
                Material.SKELETON_SKULL,
                List.of(
                        "$7h!"
                ),
                new HashMap<>(),
                Rarity.RARE,
                SkyblockItemType.NONE
        );
    }

    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg4ZDQwNGY1ZjFmYjdhYjJlODA4NzFkM2YzODU4N2Y1ZjNjNjhhYjYxZGFjNGFiNGFhZTVhMDBhM2RiMWI4MCJ9fX0=";
    }
}
