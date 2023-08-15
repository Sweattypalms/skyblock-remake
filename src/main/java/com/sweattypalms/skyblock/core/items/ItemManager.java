package com.sweattypalms.skyblock.core.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.SimpleSkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.stats.Stats;
import org.bukkit.Material;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    public static Map<String, SkyblockItem> ITEMS_LIST = new HashMap<>(

    );

    public static void initSimpleItems(){
        SkyblockItem diamondSword = SimpleSkyblockItem.builder()
                .id("diamond_sword")
                .displayName("Diamond Sword")
                .material(Material.DIAMOND_SWORD)
                .stats(Map.of(Stats.DAMAGE, 35d))
                .baseRarity(Rarity.UNCOMMON)
                .itemType(SkyblockItemType.SWORD)
                .build();

        SkyblockItem undeadSword = SimpleSkyblockItem.builder()
                .id("undead_sword")
                .displayName("Undead Sword")
                .material(Material.IRON_SWORD)
                .staticLore(List.of("$7Deals $a+100% $7damage to", "$7Skeletons, Zombies,", "$7Withers, and Zombie", "$7Pigwomen."))
                .stats(Map.of(Stats.DAMAGE, 30d))
                .baseRarity(Rarity.COMMON)
                .itemType(SkyblockItemType.SWORD)
                .abilities(List.of(AbilityManager.UNDEAD_SWORD_ABILITY))
                .build();


    }

}
