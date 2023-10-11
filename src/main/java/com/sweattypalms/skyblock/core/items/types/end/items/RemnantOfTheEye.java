package com.sweattypalms.skyblock.core.items.types.end.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;

public class RemnantOfTheEye extends SkyblockItem implements IHeadHelmet {

    public static final String ID = "remnant_of_the_eye";

    public RemnantOfTheEye() {
        super(
                ID,
                "Remnant of the Eye",
                Material.SKELETON_SKULL,
                List.of(
                        "$7Keeps you alive when you are on",
                        "$7death's door, granting a short",
                        "$7period of invincibility.",
                        "$7Consumed on use.",
                        "$f",
                        "$c$lONLY WORKS ON THE END ISLAND."

                ),
                new HashMap<>(),
                Rarity.EPIC,
                SkyblockItemType.NONE
        );
    }

    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2QzODljNTVlY2Y3ZGI1NzJkNjk2MWNlM2Q1N2I1NzJlNzYxMzk3YjY3YTJkNmQ5NGM3MmZjOTFkZGRkNzQifX19";
    }
}

