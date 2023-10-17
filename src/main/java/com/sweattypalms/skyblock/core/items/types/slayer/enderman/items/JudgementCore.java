package com.sweattypalms.skyblock.core.items.types.slayer.enderman.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;

import java.util.HashMap;

public class JudgementCore extends SkyblockItem implements IHeadHelmet {
    public static final String ID = "judgement_core";

    public JudgementCore() {
        super(
                ID,
                "Judgement Core",
                Material.PLAYER_HEAD,
                null,
                new HashMap<>(),
                Rarity.LEGENDARY,
                SkyblockItemType.NONE
        );
    }
    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZhNjM0MjMyY2EwZTRjZmZkYTJhZDM2MzI0MGYwYmI0ODA2MzgyOWIxYTkwMmU3YzU0MmY3YmM2ZDE2NWRkNiJ9fX0=";
    }
}



