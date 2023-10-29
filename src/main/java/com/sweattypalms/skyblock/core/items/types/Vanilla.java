package com.sweattypalms.skyblock.core.items.types;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SimpleSkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;

import java.util.Map;

public class Vanilla {
    public static void init() {
        minecraft_golden_pickaxe();
        minecraft_flint_arrow();
        minecraft_iron_pickaxe();
        minecraft_golden_sword();
        minecraft_iron_shovel();
        minecraft_golden_axe();
        minecraft_wooden_pickaxe();
        minecraft_stone_shovel();
        minecraft_iron_sword();
        minecraft_diamond_sword();
        minecraft_fishing_rod();
        minecraft_diamond_pickaxe();
        minecraft_golden_shovel();
        minecraft_iron_axe();
        minecraft_stone_axe();
        minecraft_stone_sword();
        minecraft_stone_pickaxe();
        minecraft_diamond_shovel();
        minecraft_wooden_sword();
        minecraft_wooden_axe();
        minecraft_diamond_axe();
    }

    public static void minecraft_golden_pickaxe() {
        SimpleSkyblockItem.builder()
                .id("golden_pickaxe")
                .displayName("Golden Pickaxe")
                .material(Material.GOLDEN_PICKAXE)
                .stats(Map.of(Stats.DAMAGE, 15d))
                .itemType(SkyblockItemType.PICKAXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }





    public static void minecraft_flint_arrow() {
        SimpleSkyblockItem.builder()
                .id("arrow")
                .displayName("Flint Arrow")
                .material(Material.ARROW)
                .stats(Map.of(Stats.DAMAGE, 1d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

















    public static void minecraft_iron_pickaxe() {
        SimpleSkyblockItem.builder()
                .id("iron_pickaxe")
                .displayName("Iron Pickaxe")
                .material(Material.IRON_PICKAXE)
                .stats(Map.of(Stats.DAMAGE, 25d))
                .itemType(SkyblockItemType.PICKAXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }



























































//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_stal")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC_11)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
















    public static void minecraft_golden_sword() {
        SimpleSkyblockItem.builder()
                .id("golden_sword")
                .displayName("Golden Sword")
                .material(Material.GOLDEN_SWORD)
                .stats(Map.of(Stats.DAMAGE, 20d))
                .itemType(SkyblockItemType.SWORD)
                .baseRarity(Rarity.COMMON)
                .build();
    }









































































    public static void minecraft_iron_shovel() {
        SimpleSkyblockItem.builder()
                .id("iron_shovel")
                .displayName("Iron Shovel")
                .material(Material.IRON_SHOVEL)
                .stats(Map.of(Stats.DAMAGE, 25d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_golden_axe() {
        SimpleSkyblockItem.builder()
                .id("golden_axe")
                .displayName("Golden Axe")
                .material(Material.GOLDEN_AXE)
                .stats(Map.of(Stats.DAMAGE, 20d))
                .itemType(SkyblockItemType.AXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }







//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_wait")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC_11)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();














//    public static void minecraft_nether_brick() {
//        SimpleSkyblockItem.builder()
//                .id("nether_brick")
//                .displayName("Nether Brick")
//                .material(Material.NETHER_BRICK)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






    public static void minecraft_wooden_pickaxe() {
        SimpleSkyblockItem.builder()
                .id("wooden_pickaxe")
                .displayName("Wooden Pickaxe")
                .material(Material.WOODEN_PICKAXE)
                .stats(Map.of(Stats.DAMAGE, 15d))
                .itemType(SkyblockItemType.PICKAXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }























    public static void minecraft_stone_shovel() {
        SimpleSkyblockItem.builder()
                .id("stone_shovel")
                .displayName("Stone Shovel")
                .material(Material.STONE_SHOVEL)
                .stats(Map.of(Stats.DAMAGE, 20d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }





    public static void minecraft_iron_sword() {
        SimpleSkyblockItem.builder()
                .id("iron_sword")
                .displayName("Iron Sword")
                .material(Material.IRON_SWORD)
                .stats(Map.of(Stats.DAMAGE, 30d))
                .itemType(SkyblockItemType.SWORD)
                .baseRarity(Rarity.COMMON)
                .build();
    }

















//    public static void minecraft_empty_map() {
//        SimpleSkyblockItem.builder()
//                .id("map")
//                .displayName("Empty Map")
//                .material(Material.MAP)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();





//
//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_far")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
















//    public static void minecraft_yellow_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Yellow Stained Clay")
//                .material(Material.LEGACY_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();




































    public static void minecraft_diamond_sword() {
        SimpleSkyblockItem.builder()
                .id("diamond_sword")
                .displayName("Diamond Sword")
                .material(Material.DIAMOND_SWORD)
                .stats(Map.of(Stats.DAMAGE, 35d))
                .itemType(SkyblockItemType.SWORD)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }








//
//    public static void minecraft_light_blue_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Light Blue Stained Clay")
//                .material(Material.STAIN)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();




















//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_cat")
//                .displayName("Music Disc")
//                .material(Material.MUSIC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();




//    public static void minecraft_clay() {
//        SimpleSkyblockItem.builder()
//                .id("clay_ball")
//                .displayName("Clay")
//                .material(Material.CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






















//    public static void minecraft_gray_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Gray Stained Clay")
//                .material(Material.GRAY_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();




















    public static void minecraft_fishing_rod() {
        SimpleSkyblockItem.builder()
                .id("fishing_rod")
                .displayName("Fishing Rod")
                .material(Material.FISHING_ROD)
                .stats(Map.of(Stats.DAMAGE, 15d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }









    public static void minecraft_diamond_pickaxe() {
        SimpleSkyblockItem.builder()
                .id("diamond_pickaxe")
                .displayName("Diamond Pickaxe")
                .material(Material.DIAMOND_PICKAXE)
                .stats(Map.of(Stats.DAMAGE, 30d))
                .itemType(SkyblockItemType.PICKAXE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }











//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_mellohi")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();


//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_ward")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();


























//    public static void minecraft_purple_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Purple Stained Clay")
//                .material(Material.PURPLE_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();












































//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_mall")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_chirp")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();


//    public static void minecraft_green_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Green Stained Clay")
//                .material(Material.GREEN_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
























    public static void minecraft_golden_shovel() {
        SimpleSkyblockItem.builder()
                .id("golden_shovel")
                .displayName("Golden Shovel")
                .material(Material.GOLDEN_SHOVEL)
                .stats(Map.of(Stats.DAMAGE, 15d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }











//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_blocks")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();


//    public static void minecraft_lime_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Lime Stained Clay")
//                .material(Material.LIME_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();








    public static void minecraft_iron_axe() {
        SimpleSkyblockItem.builder()
                .id("iron_axe")
                .displayName("Iron Axe")
                .material(Material.IRON_AXE)
                .stats(Map.of(Stats.DAMAGE, 25d))
                .itemType(SkyblockItemType.AXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_hardened_clay() {
//        SimpleSkyblockItem.builder()
//                .id("hardened_clay")
//                .displayName("Hardened Clay")
//                .material(Material.HARDENED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();










//    public static void minecraft_brown_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Brown Stained Clay")
//                .material(Material.BROWN_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






























//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_11")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






















































//    public static void minecraft_black_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Black Stained Clay")
//                .material(Material.BLACK_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();








//    public static void minecraft_pink_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Pink Stained Clay")
//                .material(Material.PINK_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






































//    public static void minecraft_cyan_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Cyan Stained Clay")
//                .material(Material.CYAN_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();










//    public static void minecraft_blue_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Blue Stained Clay")
//                .material(Material.BLUE_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();


































//    public static void minecraft_light_gray_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Light Gray Stained Clay")
//                .material(Material.LIGHT_GRAY_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();


    public static void minecraft_stone_axe() {
        SimpleSkyblockItem.builder()
                .id("stone_axe")
                .displayName("Stone Axe")
                .material(Material.STONE_AXE)
                .stats(Map.of(Stats.DAMAGE, 15d))
                .itemType(SkyblockItemType.AXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }











    public static void minecraft_stone_sword() {
        SimpleSkyblockItem.builder()
                .id("stone_sword")
                .displayName("Stone Sword")
                .material(Material.STONE_SWORD)
                .stats(Map.of(Stats.DAMAGE, 25d))
                .itemType(SkyblockItemType.SWORD)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_red_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Red Stained Clay")
//                .material(Material.RED_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






    public static void minecraft_stone_pickaxe() {
        SimpleSkyblockItem.builder()
                .id("stone_pickaxe")
                .displayName("Stone Pickaxe")
                .material(Material.STONE_PICKAXE)
                .stats(Map.of(Stats.DAMAGE, 20d))
                .itemType(SkyblockItemType.PICKAXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }



    public static void minecraft_diamond_shovel() {
        SimpleSkyblockItem.builder()
                .id("diamond_shovel")
                .displayName("Diamond Shovel")
                .material(Material.DIAMOND_SHOVEL)
                .stats(Map.of(Stats.DAMAGE, 30d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }























































//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_13")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();




































































    public static void minecraft_wooden_sword() {
        SimpleSkyblockItem.builder()
                .id("wooden_sword")
                .displayName("Wooden Sword")
                .material(Material.WOODEN_SWORD)
                .stats(Map.of(Stats.DAMAGE, 20d))
                .itemType(SkyblockItemType.SWORD)
                .baseRarity(Rarity.COMMON)
                .build();
    }















    public static void minecraft_wooden_axe() {
        SimpleSkyblockItem.builder()
                .id("wooden_axe")
                .displayName("Wooden Axe")
                .material(Material.WOODEN_AXE)
                .stats(Map.of(Stats.DAMAGE, 10d))
                .itemType(SkyblockItemType.AXE)
                .baseRarity(Rarity.COMMON)
                .build();
    }









//    public static void minecraft_magenta_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Magenta Stained Clay")
//                .material(Material.MAGENTA_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();




//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_strad")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();






    public static void minecraft_diamond_axe() {
        SimpleSkyblockItem.builder()
                .id("diamond_axe")
                .displayName("Diamond Axe")
                .material(Material.DIAMOND_AXE)
                .stats(Map.of(Stats.DAMAGE, 30d))
                .itemType(SkyblockItemType.AXE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }
























}
