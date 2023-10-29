package com.sweattypalms.skyblock.core.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SimpleSkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.items.types.Vanilla;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItemManager {
    public static Map<String, SkyblockItem> ITEMS_LIST = new HashMap<>(Map.of(
    ));

    public static ItemStack getItemStack(String id) {
        try {
            return ITEMS_LIST.get(id).toItemStack();
        } catch (Exception e) {
            return null;
        }
    }

    public static void init() {
        initReflection();
        initSimpleItems();
        Vanilla.init();
    }

    private static void initReflection() {
        Reflections reflections = new Reflections("com.sweattypalms.skyblock.core.items.types");
        Set<Class<? extends SkyblockItem>> itemClasses = reflections.getSubTypesOf(SkyblockItem.class);

        for (Class<? extends SkyblockItem> clazz : itemClasses) {
            try {
                if (clazz == SimpleSkyblockItem.class) continue;
                SkyblockItem item = clazz.getDeclaredConstructor().newInstance();
                ITEMS_LIST.put(item.getId(), item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void initSimpleItems() {
        diamondSword();
        undeadSword();
    }
    private static void diamondSword(){
        SkyblockItem diamondSword = SimpleSkyblockItem.builder()
                .id("diamond_sword")
                .displayName("Diamond Sword")
                .material(Material.DIAMOND_SWORD)
                .stats(Map.of(Stats.DAMAGE, 35d))
                .baseRarity(Rarity.UNCOMMON)
                .itemType(SkyblockItemType.SWORD)
                .build();

    }
    private static void undeadSword(){
        SkyblockItem undeadSword = SimpleSkyblockItem.builder()
                .id("undead_sword")
                .displayName("Undead Sword")
                .material(Material.IRON_SWORD)
                .staticLore(List.of("$7Deals $a+100% $7damage to", "$7Skeletons, Zombies,", "$7Withers, and Zombie", "$7Pigwomen."))
                .stats(Map.of(Stats.DAMAGE, 30d, Stats.HEALTH, 50d))
                .baseRarity(Rarity.COMMON)
                .itemType(SkyblockItemType.SWORD)
                .abilities(List.of(AbilityManager.UNDEAD_SWORD_ABILITY))
                .build();
    }

    public SkyblockItem getFromVanillaItem(Material material) {
        SkyblockItemType itemType;

        for (SkyblockItemType value : SkyblockItemType.values()) {
            if(material.toString().contains(value.toString())) {
                itemType = value;
                break;
            }
        }

        return null;
//
//        SkyblockItem skyblockItem = SimpleSkyblockItem
//                .builder()
//                .material(material)
//                .itemType(itemType)
//                .
    }
}
