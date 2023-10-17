package com.sweattypalms.skyblock.core.items.types.slayer.zombie.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShardOfTheShredded extends SkyblockItem implements IHeadHelmet {
    public static final String ID = "shard_of_the_shredded";
    public ShardOfTheShredded() {
        super(
                ID,
                "Shard Of The Shredded",
                Material.PLAYER_HEAD,
                List.of(
                        "$7The core of a powerful weapon,",
                        "$7dropped by the Atoned Horror,"
                ),
                new HashMap<>(),
                Rarity.LEGENDARY,
                SkyblockItemType.NONE
        );
    }
    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTkwMGQ5NzQyZDBhZmE2M2NkNDA0MWFlMDZlMzRlMzI0MTMzMTVlY2M0MGVhMjJkYWUwYTNhY2UyYmJlMzg3NyJ9fX0=";
    }
}
