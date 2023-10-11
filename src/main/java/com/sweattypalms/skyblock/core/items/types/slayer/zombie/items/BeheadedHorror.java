package com.sweattypalms.skyblock.core.items.types.slayer.zombie.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;

import java.util.HashMap;

public class BeheadedHorror extends SkyblockItem implements IHeadHelmet {
    public static final String ID = "beheaded_horror";
    public BeheadedHorror() {
        super(
                ID,
                "Beheaded Horror",
                Material.PLAYER_HEAD,
                null,
                new HashMap<>(),
                Rarity.EPIC,
                SkyblockItemType.NONE
        );
    }
    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJhZDk5ZWQzYzgyMGI3OTc4MTkwYWQwOGE5MzRhNjhkZmE5MGQ5OTg2ODI1ZGExYzk3ZjZmMjFmNDlhZDYyNiJ9fX0=";
    }
}
