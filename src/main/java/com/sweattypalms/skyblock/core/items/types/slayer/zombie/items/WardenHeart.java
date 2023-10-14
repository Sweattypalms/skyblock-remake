package com.sweattypalms.skyblock.core.items.types.slayer.zombie.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WardenHeart extends SkyblockItem implements IHeadHelmet {
    public static final String ID = "warden_heart";
    public WardenHeart() {
        super(
                ID,
                "Warden Heart",
                Material.PLAYER_HEAD,
                List.of(
                        "$7The heart of a powerful",
                        "$7creature, dropped by the atoned",
                        "$7Horror."
                ),
                new HashMap<>(),
                Rarity.LEGENDARY,
                SkyblockItemType.NONE
        );
    }
    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODgzYjg2MjQyZGMxNDk2Yzg0NmJlNjAxYjM5MmIzYmFlMGM5ZDhmZDhlMTdiYjk1Zjg2NTkyZmFmOGRjNWQxMSJ9fX0=";
    }
}
