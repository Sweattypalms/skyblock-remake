package com.sweattypalms.skyblock.core.items.types;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SimpleSkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import org.bukkit.Material;

import java.util.Map;

public class Vanilla {
    public static void init() {
        minecraft_golden_pickaxe();
        minecraft_enchanted_book();
        minecraft_acacia_wood_stairs();
        minecraft_flint_arrow();
        minecraft_nether_quartz_ore();
        minecraft_brewing_stand();
        minecraft_double_tallgrass();
        minecraft_gray_stained_glass_pane();
        minecraft_redstone_torch();
        minecraft_pumpkin_seeds();
        minecraft_lapis_lazuli();
        minecraft_slime_block();
        minecraft_iron_pickaxe();
        minecraft_red_sand();
        minecraft_light_blue_stained_glass();
        minecraft_cracked_stone_bricks();
        minecraft_allium();
        minecraft_redstone_comparator();
        minecraft_item_frame();
        minecraft_white_stained_clay();
        minecraft_birch_wood();
        minecraft_potato();
        minecraft_orange_stained_clay();
        minecraft_piston();
        minecraft_cactus_green();
        minecraft_bottle_o_enchanting();
        minecraft_button();
        minecraft_raw_rabbit();
        minecraft_sugar_cane();
        minecraft_blaze_rod();
        minecraft_mossy_cobblestone_wall();
        minecraft_green_banner();
        minecraft_web();
        minecraft_black_carpet();
        minecraft_sticky_piston();
        minecraft_lime_carpet();
        minecraft_chest();
        minecraft_iron_chestplate();
        minecraft_magma_cream();
        minecraft_orange_dye();
        minecraft_polished_granite();
        minecraft_diorite();
        minecraft_yellow_stained_glass_pane();
        minecraft_dark_oak_wood_slab();
        minecraft_command_block();
        minecraft_jungle_wood_stairs();
        minecraft_weighted_pressure_plate_heavy();
        minecraft_raw_chicken();
        minecraft_gold_nugget();
        minecraft_birch_wood_planks();
        minecraft_golden_sword();
        minecraft_golden_apple();
        minecraft_charcoal();
        minecraft_diamond_helmet();
        minecraft_cyan_stained_glass_pane();
        minecraft_rose_red();
        minecraft_powered_rail();
        minecraft_bed();
        minecraft_dark_oak_wood_planks();
        minecraft_pumpkin_pie();
        minecraft_brown_carpet();
        minecraft_acacia_leaves();
        minecraft_acacia_wood_slab();
        minecraft_red_mushroom();
        minecraft_golden_hoe();
        minecraft_painting();
        minecraft_magenta_banner();
        minecraft_red_wool();
        minecraft_bookshelf();
        minecraft_baked_potato();
        minecraft_bread();
        minecraft_lead();
        minecraft_prismarine_shard();
        minecraft_fire_charge();
        minecraft_spruce_wood_slab();
        minecraft_stick();
        minecraft_pink_wool();
        minecraft_blue_wool();
        minecraft_oak_leaves();
        minecraft_stone_pressure_plate();
        minecraft_coarse_dirt();
        minecraft_hay_bale();
        minecraft_andesite();
        minecraft_chiseled_sandstone();
        minecraft_block_of_diamond();
        minecraft_carrot();
        minecraft_sandstone_stairs();
        minecraft_iron_shovel();
        minecraft_golden_axe();
        minecraft_glowstone();
        minecraft_orange_stained_glass_pane();
        minecraft_cooked_rabbit();
        minecraft_ghast_tear();
        minecraft_glowstone_dust();
        minecraft_redstone();
        minecraft_dark_oak_sapling();
        minecraft_block_of_quartz();
        minecraft_acacia_fence_gate();
        minecraft_acacia_door();
        minecraft_nether_brick();
        minecraft_spruce_sapling();
        minecraft_golden_carrot();
        minecraft_block_of_redstone();
        minecraft_wooden_pickaxe();
        minecraft_acacia_fence();
        minecraft_chiseled_red_sandstone();
        minecraft_diamond();
        minecraft_birch_fence();
        minecraft_gold_ore();
        minecraft_water_bottle();
        minecraft_green_stained_glass_pane();
        minecraft_purple_banner();
        minecraft_rabbit_stew();
        minecraft_brown_wool();
        minecraft_pumpkin();
        minecraft_stone_shovel();
        minecraft_emerald();
        minecraft_dandelion_yellow();
        minecraft_iron_sword();
        minecraft_quartz_slab();
        minecraft_black_stained_glass_pane();
        minecraft_pink_dye();
        minecraft_dragon_egg();
        minecraft_dark_oak_fence_gate();
        minecraft_cactus();
        minecraft_wooden_hoe();
        minecraft_light_blue_carpet();
        minecraft_empty_map();
        minecraft_oxeye_daisy();
        minecraft_birch_wood_slab();
        minecraft_nether_star();
        minecraft_polished_diorite();
        minecraft_enchanted_golden_apple();
        minecraft_mushroom_stew();
        minecraft_chainmail_leggings();
        minecraft_iron_boots();
        minecraft_cauldron();
        minecraft_mutton();
        minecraft_iron_horse_armor();
        minecraft_jungle_door();
        minecraft_spruce_wood();
        minecraft_gray_banner();
        minecraft_note_block();
        minecraft_lime_wool();
        minecraft_cooked_salmon();
        minecraft_iron_door();
        minecraft_tnt();
        minecraft_bone_meal();
        minecraft_minecart_with_chest();
        minecraft_azure_bluet();
        minecraft_minecart();
        minecraft_slimeball();
        minecraft_lime_stained_glass();
        minecraft_monster_spawner();
        minecraft_diamond_ore();
        minecraft_water_bucket();
        minecraft_ink_sac();
        minecraft_diamond_sword();
        minecraft_oak_fence_gate();
        minecraft_enchantment_table();
        minecraft_detector_rail();
        minecraft_chiseled_quartz_block();
        minecraft_flint();
        minecraft_milk();
        minecraft_book();
        minecraft_iron_bars();
        minecraft_boat();
        minecraft_glass_pane();
        minecraft_orange_stained_glass();
        minecraft_sand();
        minecraft_weighted_pressure_plate_light();
        minecraft_string();
        minecraft_stone_brick_stairs();
        minecraft_lapis_lazuli_ore();
        minecraft_clay();
        minecraft_nether_brick_slab();
        minecraft_smooth_sandstone();
        minecraft_lily_pad();
        minecraft_stone_slab();
        minecraft_lava_bucket();
        minecraft_yellow_carpet();
        minecraft_cocoa_beans();
        minecraft_dirt();
        minecraft_stone_bricks_slab();
        minecraft_orange_banner();
        minecraft_purple_stained_glass_pane();
        minecraft_orange_carpet();
        minecraft_red_sandstone();
        minecraft_cyan_stained_glass();
        minecraft_cyan_banner();
        minecraft_written_book();
        minecraft_purple_stained_glass();
        minecraft_cooked_mutton();
        minecraft_leather_chestplate();
        minecraft_diamond_chestplate();
        minecraft_blue_stained_glass();
        minecraft_fishing_rod();
        minecraft_lime_stained_glass_pane();
        minecraft_spruce_wood_planks();
        minecraft_oak_wood_slab();
        minecraft_birch_wood_stairs();
        minecraft_diamond_pickaxe();
        minecraft_white_stained_glass_pane();
        minecraft_diamond_boots();
        minecraft_gray_dye();
        minecraft_stone_bricks();
        minecraft_spawn();
        minecraft_glass_bottle();
        minecraft_anvil();
        minecraft_gravel();
        minecraft_block_of_coal();
        minecraft_long_grass();
        minecraft_block_of_gold();
        minecraft_oak_sapling();
        minecraft_redstone_lamp();
        minecraft_red_tulip();
        minecraft_clay();
        minecraft_cyan_wool();
        minecraft_spruce_fence_gate();
        minecraft_fermented_spider_eye();
        minecraft_minecart_with_tnt();
        minecraft_cobblestone_stairs();
        minecraft_lever();
        minecraft_carrot_on_a_stick();
        minecraft_furnace();
        minecraft_snowball();
        minecraft_stone_hoe();
        minecraft_brown_stained_glass_pane();
        minecraft_netherrack();
        minecraft_prismarine_crystals();
        minecraft_golden_chestplate();
        minecraft_glass();
        minecraft_wooden_pressure_plate();
        minecraft_orange_wool();
        minecraft_name_tag();
        minecraft_golden_leggings();
        minecraft_shrub();
        minecraft_torch();
        minecraft_oak_wood_planks();
        minecraft_melon();
        minecraft_egg();
        minecraft_diamond_hoe();
        minecraft_cobblestone_wall();
        minecraft_red_sandstone_slab();
        minecraft_oak_fence();
        minecraft_red_stained_glass();
        minecraft_light_gray_carpet();
        minecraft_flint_and_steel();
        minecraft_polished_andesite();
        minecraft_soul_sand();
        minecraft_lime_banner();
        minecraft_ender_chest();
        minecraft_hopper();
        minecraft_magenta_carpet();
        minecraft_prismarine();
        minecraft_chainmail_chestplate();
        minecraft_quartz_stairs();
        minecraft_gray_wool();
        minecraft_poisonous_potato();
        minecraft_golden_shovel();
        minecraft_rail();
        minecraft_end_portal_frame();
        minecraft_nether_quartz();
        minecraft_light_blue_dye();
        minecraft_black_wool();
        minecraft_bricks();
        minecraft_redstone_repeater();
        minecraft_green_wool();
        minecraft_jungle_wood_planks();
        minecraft_cooked_fish();
        minecraft_iron_axe();
        minecraft_light_gray_stained_glass();
        minecraft_brick();
        minecraft_diamond_horse_armor();
        minecraft_black_banner();
        minecraft_brown_stained_glass();
        minecraft_sandstone();
        minecraft_raw_beef();
        minecraft_flower_pot();
        minecraft_light_blue_stained_glass_pane();
        minecraft_light_gray_stained_glass_pane();
        minecraft_eye_of_ender();
        minecraft_golden_boots();
        minecraft_acacia_sapling();
        minecraft_grass_block();
        minecraft_purple_dye();
        minecraft_oak_wood_stairs();
        minecraft_poppy();
        minecraft_gold_horse_armor();
        minecraft_trapped_chest();
        minecraft_spruce_wood_stairs();
        minecraft_steak();
        minecraft_packed_ice();
        minecraft_sign();
        minecraft_block_of_iron();
        minecraft_block_of_emerald();
        minecraft_sandstone_slab();
        minecraft_iron_helmet();
        minecraft_white_tulip();
        minecraft_nether_brick();
        minecraft_brown_mushroom();
        minecraft_dead_bush();
        minecraft_lime_dye();
        minecraft_wooden_trapdoor();
        minecraft_blue_carpet();
        minecraft_dark_oak_door();
        minecraft_pink_stained_glass_pane();
        minecraft_cyan_carpet();
        minecraft_minecart_with_hopper();
        minecraft_lilac();
        minecraft_oak_wood();
        minecraft_iron_ore();
        minecraft_rose_bush();
        minecraft_dandelion();
        minecraft_red_stained_glass_pane();
        minecraft_melon_block();
        minecraft_moss_stone();
        minecraft_minecart_with_furnace();
        minecraft_jungle_sapling();
        minecraft_snow();
        minecraft_iron_leggings();
        minecraft_pink_tulip();
        minecraft_dark_oak_fence();
        minecraft_redstone_ore();
        minecraft_birch_sapling();
        minecraft_dropper();
        minecraft_leather_helmet();
        minecraft_bowl();
        minecraft_magenta_wool();
        minecraft_tripwire_hook();
        minecraft_jack_o_lantern();
        minecraft_lapis_lazuli_block();
        minecraft_brick_stairs();
        minecraft_leather_boots();
        minecraft_blue_orchid();
        minecraft_melon_seeds();
        minecraft_seeds();
        minecraft_pink_stained_glass();
        minecraft_peony();
        minecraft_activator_rail();
        minecraft_feather();
        minecraft_gray_carpet();
        minecraft_firework_star();
        minecraft_coal_ore();
        minecraft_chiseled_stone_bricks();
        minecraft_iron_hoe();
        minecraft_glistering_melon();
        minecraft_blue_stained_glass_pane();
        minecraft_white_banner();
        minecraft_crafting_table();
        minecraft_button();
        minecraft_fern();
        minecraft_dispenser();
        minecraft_blaze_powder();
        minecraft_cake();
        minecraft_light_gray_banner();
        minecraft_jungle_fence();
        minecraft_saddle();
        minecraft_white_stained_glass();
        minecraft_green_carpet();
        minecraft_diamond_leggings();
        minecraft_cookie();
        minecraft_chainmail_helmet();
        minecraft_compass();
        minecraft_stone_axe();
        minecraft_purple_wool();
        minecraft_sugar();
        minecraft_snow_block();
        minecraft_black_stained_glass();
        minecraft_red_sandstone_stairs();
        minecraft_stone_sword();
        minecraft_cooked_chicken();
        minecraft_end_stone();
        minecraft_light_blue_banner();
        minecraft_stone_pickaxe();
        minecraft_cyan_dye();
        minecraft_diamond_shovel();
        minecraft_pillar_quartz_block();
        minecraft_yellow_wool();
        minecraft_stone();
        minecraft_light_gray_wool();
        minecraft_yellow_stained_glass();
        minecraft_acacia_wood_planks();
        minecraft_ladder();
        minecraft_acacia_wood();
        minecraft_magenta_stained_glass();
        minecraft_pink_carpet();
        minecraft_magenta_stained_glass_pane();
        minecraft_iron_ingot();
        minecraft_chainmail_boots();
        minecraft_light_blue_wool();
        minecraft_emerald_ore();
        minecraft_ice();
        minecraft_blue_banner();
        minecraft_green_stained_glass();
        minecraft_bricks_slab();
        minecraft_mossy_stone_bricks();
        minecraft_daylight_sensor();
        minecraft_rabbits_foot();
        minecraft_leather_leggings();
        minecraft_sunflower();
        minecraft_iron_trapdoor();
        minecraft_light_gray_dye();
        minecraft_cobblestone_slab();
        minecraft_cobblestone();
        minecraft_jungle_wood();
        minecraft_bow();
        minecraft_ender_pearl();
        minecraft_rotten_flesh();
        minecraft_apple();
        minecraft_jungle_fence_gate();
        minecraft_cooked_porkchop();
        minecraft_birch_fence_gate();
        minecraft_empty_map();
        minecraft_vines();
        minecraft_sponge();
        minecraft_dark_oak_wood_stairs();
        minecraft_nether_brick_fence();
        minecraft_barrier();
        minecraft_shears();
        minecraft_book_and_quill();
        minecraft_gray_stained_glass();
        minecraft_nether_wart();
        minecraft_granite();
        minecraft_dark_prismarine();
        minecraft_white_wool();
        minecraft_paper();
        minecraft_rabbit_hide();
        minecraft_red_banner();
        minecraft_jungle_wood_slab();
        minecraft_bucket();
        minecraft_birch_door();
        minecraft_spider_eye();
        minecraft_jukebox();
        minecraft_purple_carpet();
        minecraft_stone_monster_egg();
        minecraft_orange_tulip();
        minecraft_prismarine_bricks();
        minecraft_wooden_sword();
        minecraft_brown_banner();
        minecraft_carpet();
        minecraft_magenta_dye();
        minecraft_spruce_fence();
        minecraft_raw_porkchop();
        minecraft_firework_rocket();
        minecraft_raw_fish();
        minecraft_wooden_axe();
        minecraft_bone();
        minecraft_dark_oak_wood();
        minecraft_spruce_door();
        minecraft_oak_door();
        minecraft_pink_banner();
        minecraft_coal();
        minecraft_gold_ingot();
        minecraft_leather();
        minecraft_yellow_banner();
        minecraft_diamond_axe();
        minecraft_clock();
        minecraft_sea_lantern();
        minecraft_gunpowder();
        minecraft_minecart_with_command_block();
        minecraft_obsidian();
        minecraft_golden_helmet();
        minecraft_wheat();
        minecraft_red_carpet();
        minecraft_nether_brick_stairs();
        minecraft_armor_stand();
        minecraft_skeleton_skull();
        minecraft_smooth_red_sandstone();
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

    public static void minecraft_enchanted_book() {
        SimpleSkyblockItem.builder()
                .id("enchanted_book")
                .displayName("Enchanted Book")
                .material(Material.ENCHANTED_BOOK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_wood_stairs() {
        SimpleSkyblockItem.builder()
                .id("acacia_stairs")
                .displayName("Acacia Wood Stairs")
                .material(Material.ACACIA_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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

    public static void minecraft_nether_quartz_ore() {
        SimpleSkyblockItem.builder()
                .id("quartz_ore")
                .displayName("Nether Quartz Ore")
                .material(Material.NETHER_QUARTZ_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brewing_stand() {
        SimpleSkyblockItem.builder()
                .id("brewing_stand")
                .displayName("Brewing Stand")
                .material(Material.BREWING_STAND)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_double_tallgrass() {
        SimpleSkyblockItem.builder()
                .id("double_plant")
                .displayName("Double Tallgrass")
                .material(Material.TALL_GRASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gray_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Gray Stained Glass Pane")
                .material(Material.GRAY_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_redstone_torch() {
        SimpleSkyblockItem.builder()
                .id("redstone_torch")
                .displayName("Redstone Torch")
                .material(Material.REDSTONE_TORCH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pumpkin_seeds() {
        SimpleSkyblockItem.builder()
                .id("pumpkin_seeds")
                .displayName("Pumpkin Seeds")
                .material(Material.PUMPKIN_SEEDS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lapis_lazuli() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Lapis Lazuli")
                .material(Material.LAPIS_LAZULI)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_slime_block() {
        SimpleSkyblockItem.builder()
                .id("slime")
                .displayName("Slime Block")
                .material(Material.SLIME_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
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

    public static void minecraft_red_sand() {
        SimpleSkyblockItem.builder()
                .id("sand")
                .displayName("Red Sand")
                .material(Material.RED_SAND)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_blue_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Light Blue Stained Glass")
                .material(Material.LIGHT_BLUE_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cracked_stone_bricks() {
        SimpleSkyblockItem.builder()
                .id("stonebrick")
                .displayName("Cracked Stone Bricks")
                .material(Material.CRACKED_STONE_BRICKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_allium() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Allium")
                .material(Material.ALLIUM)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_redstone_comparator() {
        SimpleSkyblockItem.builder()
                .id("comparator")
                .displayName("Redstone Comparator")
                .material(Material.LEGACY_REDSTONE_COMPARATOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_item_frame() {
        SimpleSkyblockItem.builder()
                .id("item_frame")
                .displayName("Item Frame")
                .material(Material.ITEM_FRAME)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_white_stained_clay() {
        SimpleSkyblockItem.builder()
                .id("stained_hardened_clay")
                .displayName("White Stained Clay")
                .material(Material.LEGACY_STAINED_CLAY)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_wood() {
        SimpleSkyblockItem.builder()
                .id("log")
                .displayName("Birch Wood")
                .material(Material.BIRCH_WOOD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_potato() {
        SimpleSkyblockItem.builder()
                .id("potato")
                .displayName("Potato")
                .material(Material.POTATO)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_stained_clay() {
        SimpleSkyblockItem.builder()
                .id("stained_hardened_clay")
                .displayName("Orange Stained Clay")
                .material(Material.LEGACY_STAINED_CLAY)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_piston() {
        SimpleSkyblockItem.builder()
                .id("piston")
                .displayName("Piston")
                .material(Material.PISTON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cactus_green() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Cactus Green")
                .material(Material.CACTUS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bottle_o_enchanting() {
        SimpleSkyblockItem.builder()
                .id("experience_bottle")
                .displayName("Bottle o' Enchanting")
                .material(Material.EXPERIENCE_BOTTLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_button() {
        SimpleSkyblockItem.builder()
                .id("stone_button")
                .displayName("Button")
                .material(Material.STONE_BUTTON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_raw_rabbit() {
        SimpleSkyblockItem.builder()
                .id("rabbit")
                .displayName("Raw Rabbit")
                .material(Material.RABBIT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sugar_cane() {
        SimpleSkyblockItem.builder()
                .id("reeds")
                .displayName("Sugar Cane")
                .material(Material.SUGAR_CANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blaze_rod() {
        SimpleSkyblockItem.builder()
                .id("blaze_rod")
                .displayName("Blaze Rod")
                .material(Material.BLAZE_ROD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_mossy_cobblestone_wall() {
        SimpleSkyblockItem.builder()
                .id("cobblestone_wall")
                .displayName("Mossy Cobblestone Wall")
                .material(Material.MOSSY_COBBLESTONE_WALL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_green_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Green Banner")
                .material(Material.GREEN_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_web() {
        SimpleSkyblockItem.builder()
                .id("web")
                .displayName("Web")
                .material(Material.COBWEB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_black_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Black Carpet")
                .material(Material.BLACK_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sticky_piston() {
        SimpleSkyblockItem.builder()
                .id("sticky_piston")
                .displayName("Sticky Piston")
                .material(Material.STICKY_PISTON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lime_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Lime Carpet")
                .material(Material.LIME_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chest() {
        SimpleSkyblockItem.builder()
                .id("chest")
                .displayName("Chest")
                .material(Material.CHEST)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_chestplate() {
        SimpleSkyblockItem.builder()
                .id("iron_chestplate")
                .displayName("Iron Chestplate")
                .material(Material.IRON_CHESTPLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.CHESTPLATE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magma_cream() {
        SimpleSkyblockItem.builder()
                .id("magma_cream")
                .displayName("Magma Cream")
                .material(Material.MAGMA_CREAM)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Orange Dye")
                .material(Material.ORANGE_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_polished_granite() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Polished Granite")
                .material(Material.POLISHED_GRANITE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diorite() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Diorite")
                .material(Material.DIORITE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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
//    }

    public static void minecraft_yellow_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Yellow Stained Glass Pane")
                .material(Material.YELLOW_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_wood_slab() {
        SimpleSkyblockItem.builder()
                .id("wooden_slab")
                .displayName("Dark Oak Wood Slab")
                .material(Material.DARK_OAK_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_command_block() {
        SimpleSkyblockItem.builder()
                .id("command_block")
                .displayName("Command Block")
                .material(Material.COMMAND_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jungle_wood_stairs() {
        SimpleSkyblockItem.builder()
                .id("jungle_stairs")
                .displayName("Jungle Wood Stairs")
                .material(Material.JUNGLE_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_weighted_pressure_plate_heavy() {
        SimpleSkyblockItem.builder()
                .id("heavy_weighted_pressure_plate")
                .displayName("Weighted Pressure Plate (Heavy)")
                .material(Material.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_raw_chicken() {
        SimpleSkyblockItem.builder()
                .id("chicken")
                .displayName("Raw Chicken")
                .material(Material.CHICKEN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gold_nugget() {
        SimpleSkyblockItem.builder()
                .id("gold_nugget")
                .displayName("Gold Nugget")
                .material(Material.GOLD_NUGGET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_wood_planks() {
        SimpleSkyblockItem.builder()
                .id("planks")
                .displayName("Birch Wood Planks")
                .material(Material.BIRCH_PLANKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_golden_apple() {
        SimpleSkyblockItem.builder()
                .id("golden_apple")
                .displayName("Golden Apple")
                .material(Material.GOLDEN_APPLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_charcoal() {
        SimpleSkyblockItem.builder()
                .id("coal")
                .displayName("Charcoal")
                .material(Material.CHARCOAL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_helmet() {
        SimpleSkyblockItem.builder()
                .id("diamond_helmet")
                .displayName("Diamond Helmet")
                .material(Material.DIAMOND_HELMET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.HELMET)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_cyan_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Cyan Stained Glass Pane")
                .material(Material.CYAN_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_rose_red() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Rose Red")
                .material(Material.LEGACY_RED_ROSE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_powered_rail() {
        SimpleSkyblockItem.builder()
                .id("golden_rail")
                .displayName("Powered Rail")
                .material(Material.POWERED_RAIL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bed() {
        SimpleSkyblockItem.builder()
                .id("bed")
                .displayName("Bed")
                .material(Material.RED_BED)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_wood_planks() {
        SimpleSkyblockItem.builder()
                .id("planks")
                .displayName("Dark Oak Wood Planks")
                .material(Material.DARK_OAK_PLANKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pumpkin_pie() {
        SimpleSkyblockItem.builder()
                .id("pumpkin_pie")
                .displayName("Pumpkin Pie")
                .material(Material.PUMPKIN_PIE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brown_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Brown Carpet")
                .material(Material.BROWN_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_leaves() {
        SimpleSkyblockItem.builder()
                .id("leaves2")
                .displayName("Acacia Leaves")
                .material(Material.ACACIA_LEAVES)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_wood_slab() {
        SimpleSkyblockItem.builder()
                .id("wooden_slab")
                .displayName("Acacia Wood Slab")
                .material(Material.ACACIA_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_mushroom() {
        SimpleSkyblockItem.builder()
                .id("red_mushroom")
                .displayName("Red Mushroom")
                .material(Material.RED_MUSHROOM)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_golden_hoe() {
        SimpleSkyblockItem.builder()
                .id("golden_hoe")
                .displayName("Golden Hoe")
                .material(Material.GOLDEN_HOE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_painting() {
        SimpleSkyblockItem.builder()
                .id("painting")
                .displayName("Painting")
                .material(Material.PAINTING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magenta_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Magenta Banner")
                .material(Material.MAGENTA_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Red Wool")
                .material(Material.RED_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bookshelf() {
        SimpleSkyblockItem.builder()
                .id("bookshelf")
                .displayName("Bookshelf")
                .material(Material.BOOKSHELF)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_baked_potato() {
        SimpleSkyblockItem.builder()
                .id("baked_potato")
                .displayName("Baked Potato")
                .material(Material.BAKED_POTATO)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bread() {
        SimpleSkyblockItem.builder()
                .id("bread")
                .displayName("Bread")
                .material(Material.BREAD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lead() {
        SimpleSkyblockItem.builder()
                .id("lead")
                .displayName("Lead")
                .material(Material.LEAD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_prismarine_shard() {
        SimpleSkyblockItem.builder()
                .id("prismarine_shard")
                .displayName("Prismarine Shard")
                .material(Material.PRISMARINE_SHARD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_fire_charge() {
        SimpleSkyblockItem.builder()
                .id("fire_charge")
                .displayName("Fire Charge")
                .material(Material.FIRE_CHARGE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_wood_slab() {
        SimpleSkyblockItem.builder()
                .id("wooden_slab")
                .displayName("Spruce Wood Slab")
                .material(Material.SPRUCE_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stick() {
        SimpleSkyblockItem.builder()
                .id("stick")
                .displayName("Stick")
                .material(Material.STICK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pink_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Pink Wool")
                .material(Material.PINK_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blue_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Blue Wool")
                .material(Material.BLUE_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_leaves() {
        SimpleSkyblockItem.builder()
                .id("leaves")
                .displayName("Oak Leaves")
                .material(Material.OAK_LEAVES)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_pressure_plate() {
        SimpleSkyblockItem.builder()
                .id("stone_pressure_plate")
                .displayName("Stone Pressure Plate")
                .material(Material.STONE_PRESSURE_PLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_coarse_dirt() {
        SimpleSkyblockItem.builder()
                .id("dirt")
                .displayName("Coarse Dirt")
                .material(Material.COARSE_DIRT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_hay_bale() {
        SimpleSkyblockItem.builder()
                .id("hay_block")
                .displayName("Hay Bale")
                .material(Material.HAY_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_andesite() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Andesite")
                .material(Material.ANDESITE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chiseled_sandstone() {
        SimpleSkyblockItem.builder()
                .id("sandstone")
                .displayName("Chiseled Sandstone")
                .material(Material.CHISELED_SANDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_diamond() {
        SimpleSkyblockItem.builder()
                .id("diamond_block")
                .displayName("Block of Diamond")
                .material(Material.DIAMOND_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_carrot() {
        SimpleSkyblockItem.builder()
                .id("carrot")
                .displayName("Carrot")
                .material(Material.CARROT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sandstone_stairs() {
        SimpleSkyblockItem.builder()
                .id("sandstone_stairs")
                .displayName("Sandstone Stairs")
                .material(Material.SANDSTONE_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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

    public static void minecraft_glowstone() {
        SimpleSkyblockItem.builder()
                .id("glowstone")
                .displayName("Glowstone")
                .material(Material.GLOWSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Orange Stained Glass Pane")
                .material(Material.ORANGE_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cooked_rabbit() {
        SimpleSkyblockItem.builder()
                .id("cooked_rabbit")
                .displayName("Cooked Rabbit")
                .material(Material.COOKED_RABBIT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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
//    }

    public static void minecraft_ghast_tear() {
        SimpleSkyblockItem.builder()
                .id("ghast_tear")
                .displayName("Ghast Tear")
                .material(Material.GHAST_TEAR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_glowstone_dust() {
        SimpleSkyblockItem.builder()
                .id("glowstone_dust")
                .displayName("Glowstone Dust")
                .material(Material.GLOWSTONE_DUST)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_redstone() {
        SimpleSkyblockItem.builder()
                .id("redstone")
                .displayName("Redstone")
                .material(Material.REDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_sapling() {
        SimpleSkyblockItem.builder()
                .id("sapling")
                .displayName("Dark Oak Sapling")
                .material(Material.DARK_OAK_SAPLING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_quartz() {
        SimpleSkyblockItem.builder()
                .id("quartz_block")
                .displayName("Block of Quartz")
                .material(Material.QUARTZ_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_fence_gate() {
        SimpleSkyblockItem.builder()
                .id("acacia_fence_gate")
                .displayName("Acacia Fence Gate")
                .material(Material.ACACIA_FENCE_GATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_door() {
        SimpleSkyblockItem.builder()
                .id("acacia_door")
                .displayName("Acacia Door")
                .material(Material.ACACIA_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_nether_brick() {
//        SimpleSkyblockItem.builder()
//                .id("nether_brick")
//                .displayName("Nether Brick")
//                .material(Material.NETHER_BRICK)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_spruce_sapling() {
        SimpleSkyblockItem.builder()
                .id("sapling")
                .displayName("Spruce Sapling")
                .material(Material.SPRUCE_SAPLING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_golden_carrot() {
        SimpleSkyblockItem.builder()
                .id("golden_carrot")
                .displayName("Golden Carrot")
                .material(Material.GOLDEN_CARROT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_redstone() {
        SimpleSkyblockItem.builder()
                .id("redstone_block")
                .displayName("Block of Redstone")
                .material(Material.REDSTONE_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_acacia_fence() {
        SimpleSkyblockItem.builder()
                .id("acacia_fence")
                .displayName("Acacia Fence")
                .material(Material.ACACIA_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chiseled_red_sandstone() {
        SimpleSkyblockItem.builder()
                .id("red_sandstone")
                .displayName("Chiseled Red Sandstone")
                .material(Material.CHISELED_RED_SANDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond() {
        SimpleSkyblockItem.builder()
                .id("diamond")
                .displayName("Diamond")
                .material(Material.DIAMOND)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_fence() {
        SimpleSkyblockItem.builder()
                .id("birch_fence")
                .displayName("Birch Fence")
                .material(Material.BIRCH_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gold_ore() {
        SimpleSkyblockItem.builder()
                .id("gold_ore")
                .displayName("Gold Ore")
                .material(Material.GOLD_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_water_bottle() {
        SimpleSkyblockItem.builder()
                .id("potion")
                .displayName("Water Bottle")
                .material(Material.GLASS_BOTTLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_green_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Green Stained Glass Pane")
                .material(Material.GREEN_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_purple_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Purple Banner")
                .material(Material.PURPLE_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_rabbit_stew() {
        SimpleSkyblockItem.builder()
                .id("rabbit_stew")
                .displayName("Rabbit Stew")
                .material(Material.RABBIT_STEW)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brown_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Brown Wool")
                .material(Material.BROWN_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pumpkin() {
        SimpleSkyblockItem.builder()
                .id("pumpkin")
                .displayName("Pumpkin")
                .material(Material.PUMPKIN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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

    public static void minecraft_emerald() {
        SimpleSkyblockItem.builder()
                .id("emerald")
                .displayName("Emerald")
                .material(Material.EMERALD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dandelion_yellow() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Dandelion Yellow")
                .material(Material.DANDELION)
                .stats(Map.of(Stats.DAMAGE, 0d))
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

    public static void minecraft_quartz_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Quartz Slab")
                .material(Material.QUARTZ_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_black_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Black Stained Glass Pane")
                .material(Material.BLACK_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pink_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Pink Dye")
                .material(Material.PINK_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dragon_egg() {
        SimpleSkyblockItem.builder()
                .id("dragon_egg")
                .displayName("Dragon Egg")
                .material(Material.DRAGON_EGG)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_fence_gate() {
        SimpleSkyblockItem.builder()
                .id("dark_oak_fence_gate")
                .displayName("Dark Oak Fence Gate")
                .material(Material.DARK_OAK_FENCE_GATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cactus() {
        SimpleSkyblockItem.builder()
                .id("cactus")
                .displayName("Cactus")
                .material(Material.CACTUS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_wooden_hoe() {
        SimpleSkyblockItem.builder()
                .id("wooden_hoe")
                .displayName("Wooden Hoe")
                .material(Material.WOODEN_HOE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_blue_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Light Blue Carpet")
                .material(Material.LIGHT_BLUE_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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
//    }

    public static void minecraft_oxeye_daisy() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Oxeye Daisy")
                .material(Material.OXEYE_DAISY)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_wood_slab() {
        SimpleSkyblockItem.builder()
                .id("wooden_slab")
                .displayName("Birch Wood Slab")
                .material(Material.BIRCH_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_nether_star() {
        SimpleSkyblockItem.builder()
                .id("nether_star")
                .displayName("Nether Star")
                .material(Material.NETHER_STAR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }
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
//    }

    public static void minecraft_polished_diorite() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Polished Diorite")
                .material(Material.POLISHED_DIORITE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_enchanted_golden_apple() {
        SimpleSkyblockItem.builder()
                .id("golden_apple")
                .displayName("Enchanted Golden Apple")
                .material(Material.ENCHANTED_GOLDEN_APPLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_mushroom_stew() {
        SimpleSkyblockItem.builder()
                .id("mushroom_stew")
                .displayName("Mushroom Stew")
                .material(Material.MUSHROOM_STEW)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chainmail_leggings() {
        SimpleSkyblockItem.builder()
                .id("chainmail_leggings")
                .displayName("Chainmail Leggings")
                .material(Material.CHAINMAIL_LEGGINGS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.LEGGINGS)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_iron_boots() {
        SimpleSkyblockItem.builder()
                .id("iron_boots")
                .displayName("Iron Boots")
                .material(Material.IRON_BOOTS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOOTS)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cauldron() {
        SimpleSkyblockItem.builder()
                .id("cauldron")
                .displayName("Cauldron")
                .material(Material.CAULDRON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_mutton() {
        SimpleSkyblockItem.builder()
                .id("mutton")
                .displayName("Mutton")
                .material(Material.MUTTON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_horse_armor() {
        SimpleSkyblockItem.builder()
                .id("iron_horse_armor")
                .displayName("Iron Horse Armor")
                .material(Material.IRON_HORSE_ARMOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.RARE)
                .build();
    }

//    public static void minecraft_yellow_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Yellow Stained Clay")
//                .material(Material.LEGACY_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_jungle_door() {
        SimpleSkyblockItem.builder()
                .id("jungle_door")
                .displayName("Jungle Door")
                .material(Material.JUNGLE_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_wood() {
        SimpleSkyblockItem.builder()
                .id("log")
                .displayName("Spruce Wood")
                .material(Material.SPRUCE_WOOD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gray_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Gray Banner")
                .material(Material.GRAY_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_note_block() {
        SimpleSkyblockItem.builder()
                .id("noteblock")
                .displayName("Note Block")
                .material(Material.NOTE_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lime_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Lime Wool")
                .material(Material.LIME_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cooked_salmon() {
        SimpleSkyblockItem.builder()
                .id("cooked_fish")
                .displayName("Cooked Salmon")
                .material(Material.COOKED_SALMON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_door() {
        SimpleSkyblockItem.builder()
                .id("iron_door")
                .displayName("Iron Door")
                .material(Material.IRON_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_tnt() {
        SimpleSkyblockItem.builder()
                .id("tnt")
                .displayName("TNT")
                .material(Material.TNT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bone_meal() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Bone Meal")
                .material(Material.BONE_MEAL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_minecart_with_chest() {
        SimpleSkyblockItem.builder()
                .id("chest_minecart")
                .displayName("Minecart with Chest")
                .material(Material.CHEST_MINECART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_azure_bluet() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Azure Bluet")
                .material(Material.AZURE_BLUET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_minecart() {
        SimpleSkyblockItem.builder()
                .id("minecart")
                .displayName("Minecart")
                .material(Material.MINECART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_slimeball() {
        SimpleSkyblockItem.builder()
                .id("slime_ball")
                .displayName("Slimeball")
                .material(Material.SLIME_BALL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lime_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Lime Stained Glass")
                .material(Material.LIME_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_monster_spawner() {
        SimpleSkyblockItem.builder()
                .id("mob_spawner")
                .displayName("Monster Spawner")
                .material(Material.SPAWNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_ore() {
        SimpleSkyblockItem.builder()
                .id("diamond_ore")
                .displayName("Diamond Ore")
                .material(Material.DIAMOND_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_water_bucket() {
        SimpleSkyblockItem.builder()
                .id("water_bucket")
                .displayName("Water Bucket")
                .material(Material.WATER_BUCKET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_ink_sac() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Ink Sac")
                .material(Material.INK_SAC)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_oak_fence_gate() {
        SimpleSkyblockItem.builder()
                .id("fence_gate")
                .displayName("Oak Fence Gate")
                .material(Material.OAK_FENCE_GATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_enchantment_table() {
        SimpleSkyblockItem.builder()
                .id("enchanting_table")
                .displayName("Enchantment Table")
                .material(Material.ENCHANTING_TABLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_detector_rail() {
        SimpleSkyblockItem.builder()
                .id("detector_rail")
                .displayName("Detector Rail")
                .material(Material.DETECTOR_RAIL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chiseled_quartz_block() {
        SimpleSkyblockItem.builder()
                .id("quartz_block")
                .displayName("Chiseled Quartz Block")
                .material(Material.CHISELED_QUARTZ_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
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
//    }

    public static void minecraft_flint() {
        SimpleSkyblockItem.builder()
                .id("flint")
                .displayName("Flint")
                .material(Material.FLINT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_milk() {
        SimpleSkyblockItem.builder()
                .id("milk_bucket")
                .displayName("Milk")
                .material(Material.MILK_BUCKET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_book() {
        SimpleSkyblockItem.builder()
                .id("book")
                .displayName("Book")
                .material(Material.BOOK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_bars() {
        SimpleSkyblockItem.builder()
                .id("iron_bars")
                .displayName("Iron Bars")
                .material(Material.IRON_BARS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_boat() {
        SimpleSkyblockItem.builder()
                .id("boat")
                .displayName("Boat")
                .material(Material.OAK_BOAT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("glass_pane")
                .displayName("Glass Pane")
                .material(Material.GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Orange Stained Glass")
                .material(Material.ORANGE_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sand() {
        SimpleSkyblockItem.builder()
                .id("sand")
                .displayName("Sand")
                .material(Material.SAND)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_weighted_pressure_plate_light() {
        SimpleSkyblockItem.builder()
                .id("light_weighted_pressure_plate")
                .displayName("Weighted Pressure Plate (Light)")
                .material(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_string() {
        SimpleSkyblockItem.builder()
                .id("string")
                .displayName("String")
                .material(Material.STRING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_cat")
//                .displayName("Music Disc")
//                .material(Material.MUSIC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_stone_brick_stairs() {
        SimpleSkyblockItem.builder()
                .id("stone_brick_stairs")
                .displayName("Stone Brick Stairs")
                .material(Material.STONE_BRICK_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lapis_lazuli_ore() {
        SimpleSkyblockItem.builder()
                .id("lapis_ore")
                .displayName("Lapis Lazuli Ore")
                .material(Material.LAPIS_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_clay() {
//        SimpleSkyblockItem.builder()
//                .id("clay_ball")
//                .displayName("Clay")
//                .material(Material.CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_nether_brick_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Nether Brick Slab")
                .material(Material.NETHER_BRICK_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_smooth_sandstone() {
        SimpleSkyblockItem.builder()
                .id("sandstone")
                .displayName("Smooth Sandstone")
                .material(Material.SMOOTH_SANDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lily_pad() {
        SimpleSkyblockItem.builder()
                .id("waterlily")
                .displayName("Lily Pad")
                .material(Material.LILY_PAD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Stone Slab")
                .material(Material.STONE_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lava_bucket() {
        SimpleSkyblockItem.builder()
                .id("lava_bucket")
                .displayName("Lava Bucket")
                .material(Material.LAVA_BUCKET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_yellow_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Yellow Carpet")
                .material(Material.YELLOW_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cocoa_beans() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Cocoa Beans")
                .material(Material.COCOA_BEANS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dirt() {
        SimpleSkyblockItem.builder()
                .id("dirt")
                .displayName("Dirt")
                .material(Material.DIRT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_bricks_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Stone Bricks Slab")
                .material(Material.STONE_BRICK_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Orange Banner")
                .material(Material.ORANGE_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_purple_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Purple Stained Glass Pane")
                .material(Material.PURPLE_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_gray_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Gray Stained Clay")
//                .material(Material.GRAY_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_orange_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Orange Carpet")
                .material(Material.ORANGE_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_sandstone() {
        SimpleSkyblockItem.builder()
                .id("red_sandstone")
                .displayName("Red Sandstone")
                .material(Material.RED_SANDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cyan_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Cyan Stained Glass")
                .material(Material.CYAN_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cyan_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Cyan Banner")
                .material(Material.CYAN_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_written_book() {
        SimpleSkyblockItem.builder()
                .id("written_book")
                .displayName("Written Book")
                .material(Material.WRITTEN_BOOK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_purple_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Purple Stained Glass")
                .material(Material.PURPLE_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cooked_mutton() {
        SimpleSkyblockItem.builder()
                .id("cooked_mutton")
                .displayName("Cooked Mutton")
                .material(Material.COOKED_MUTTON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_leather_chestplate() {
        SimpleSkyblockItem.builder()
                .id("leather_chestplate")
                .displayName("Leather Chestplate")
                .material(Material.LEATHER_CHESTPLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.CHESTPLATE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_chestplate() {
        SimpleSkyblockItem.builder()
                .id("diamond_chestplate")
                .displayName("Diamond Chestplate")
                .material(Material.DIAMOND_CHESTPLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.CHESTPLATE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_blue_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Blue Stained Glass")
                .material(Material.BLUE_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_lime_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Lime Stained Glass Pane")
                .material(Material.LIME_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_wood_planks() {
        SimpleSkyblockItem.builder()
                .id("planks")
                .displayName("Spruce Wood Planks")
                .material(Material.SPRUCE_PLANKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_wood_slab() {
        SimpleSkyblockItem.builder()
                .id("wooden_slab")
                .displayName("Oak Wood Slab")
                .material(Material.OAK_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_wood_stairs() {
        SimpleSkyblockItem.builder()
                .id("birch_stairs")
                .displayName("Birch Wood Stairs")
                .material(Material.BIRCH_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
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

    public static void minecraft_white_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("White Stained Glass Pane")
                .material(Material.WHITE_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_boots() {
        SimpleSkyblockItem.builder()
                .id("diamond_boots")
                .displayName("Diamond Boots")
                .material(Material.DIAMOND_BOOTS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOOTS)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_gray_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Gray Dye")
                .material(Material.GRAY_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_bricks() {
        SimpleSkyblockItem.builder()
                .id("stonebrick")
                .displayName("Stone Bricks")
                .material(Material.STONE_BRICKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spawn() {
        SimpleSkyblockItem.builder()
                .id("spawn_egg")
                .displayName("Spawn")
                .material(Material.ZOMBIE_SPAWN_EGG)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
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
//    }

    public static void minecraft_glass_bottle() {
        SimpleSkyblockItem.builder()
                .id("glass_bottle")
                .displayName("Glass Bottle")
                .material(Material.GLASS_BOTTLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_ward")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_anvil() {
        SimpleSkyblockItem.builder()
                .id("anvil")
                .displayName("Anvil")
                .material(Material.ANVIL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gravel() {
        SimpleSkyblockItem.builder()
                .id("gravel")
                .displayName("Gravel")
                .material(Material.GRAVEL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_coal() {
        SimpleSkyblockItem.builder()
                .id("coal_block")
                .displayName("Block of Coal")
                .material(Material.COAL_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_long_grass() {
        SimpleSkyblockItem.builder()
                .id("tallgrass")
                .displayName("Long Grass")
                .material(Material.TALL_GRASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_gold() {
        SimpleSkyblockItem.builder()
                .id("gold_block")
                .displayName("Block of Gold")
                .material(Material.GOLD_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_sapling() {
        SimpleSkyblockItem.builder()
                .id("sapling")
                .displayName("Oak Sapling")
                .material(Material.OAK_SAPLING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_redstone_lamp() {
        SimpleSkyblockItem.builder()
                .id("redstone_lamp")
                .displayName("Redstone Lamp")
                .material(Material.REDSTONE_LAMP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_tulip() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Red Tulip")
                .material(Material.RED_TULIP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_clay() {
        SimpleSkyblockItem.builder()
                .id("clay")
                .displayName("Clay")
                .material(Material.CLAY)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cyan_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Cyan Wool")
                .material(Material.CYAN_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_fence_gate() {
        SimpleSkyblockItem.builder()
                .id("spruce_fence_gate")
                .displayName("Spruce Fence Gate")
                .material(Material.SPRUCE_FENCE_GATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_fermented_spider_eye() {
        SimpleSkyblockItem.builder()
                .id("fermented_spider_eye")
                .displayName("Fermented Spider Eye")
                .material(Material.FERMENTED_SPIDER_EYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_minecart_with_tnt() {
        SimpleSkyblockItem.builder()
                .id("tnt_minecart")
                .displayName("Minecart with TNT")
                .material(Material.TNT_MINECART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_purple_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Purple Stained Clay")
//                .material(Material.PURPLE_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_cobblestone_stairs() {
        SimpleSkyblockItem.builder()
                .id("stone_stairs")
                .displayName("Cobblestone Stairs")
                .material(Material.COBBLESTONE_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lever() {
        SimpleSkyblockItem.builder()
                .id("lever")
                .displayName("Lever")
                .material(Material.LEVER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_carrot_on_a_stick() {
        SimpleSkyblockItem.builder()
                .id("carrot_on_a_stick")
                .displayName("Carrot on a Stick")
                .material(Material.CARROT_ON_A_STICK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_furnace() {
        SimpleSkyblockItem.builder()
                .id("furnace")
                .displayName("Furnace")
                .material(Material.FURNACE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_snowball() {
        SimpleSkyblockItem.builder()
                .id("snowball")
                .displayName("Snowball")
                .material(Material.SNOWBALL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_hoe() {
        SimpleSkyblockItem.builder()
                .id("stone_hoe")
                .displayName("Stone Hoe")
                .material(Material.STONE_HOE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brown_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Brown Stained Glass Pane")
                .material(Material.BROWN_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_netherrack() {
        SimpleSkyblockItem.builder()
                .id("netherrack")
                .displayName("Netherrack")
                .material(Material.NETHERRACK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_prismarine_crystals() {
        SimpleSkyblockItem.builder()
                .id("prismarine_crystals")
                .displayName("Prismarine Crystals")
                .material(Material.PRISMARINE_CRYSTALS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_golden_chestplate() {
        SimpleSkyblockItem.builder()
                .id("golden_chestplate")
                .displayName("Golden Chestplate")
                .material(Material.GOLDEN_CHESTPLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.CHESTPLATE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_glass() {
        SimpleSkyblockItem.builder()
                .id("glass")
                .displayName("Glass")
                .material(Material.GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_wooden_pressure_plate() {
        SimpleSkyblockItem.builder()
                .id("wooden_pressure_plate")
                .displayName("Wooden Pressure Plate")
                .material(Material.OAK_PRESSURE_PLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Orange Wool")
                .material(Material.ORANGE_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_name_tag() {
        SimpleSkyblockItem.builder()
                .id("name_tag")
                .displayName("Name Tag")
                .material(Material.NAME_TAG)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_golden_leggings() {
        SimpleSkyblockItem.builder()
                .id("golden_leggings")
                .displayName("Golden Leggings")
                .material(Material.GOLDEN_LEGGINGS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.LEGGINGS)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_shrub() {
        SimpleSkyblockItem.builder()
                .id("tallgrass")
                .displayName("Shrub")
                .material(Material.TALL_SEAGRASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_torch() {
        SimpleSkyblockItem.builder()
                .id("torch")
                .displayName("Torch")
                .material(Material.TORCH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_wood_planks() {
        SimpleSkyblockItem.builder()
                .id("planks")
                .displayName("Oak Wood Planks")
                .material(Material.OAK_PLANKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_melon() {
        SimpleSkyblockItem.builder()
                .id("melon")
                .displayName("Melon")
                .material(Material.MELON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_egg() {
        SimpleSkyblockItem.builder()
                .id("egg")
                .displayName("Egg")
                .material(Material.EGG)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_hoe() {
        SimpleSkyblockItem.builder()
                .id("diamond_hoe")
                .displayName("Diamond Hoe")
                .material(Material.DIAMOND_HOE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_cobblestone_wall() {
        SimpleSkyblockItem.builder()
                .id("cobblestone_wall")
                .displayName("Cobblestone Wall")
                .material(Material.COBBLESTONE_WALL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_mall")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_red_sandstone_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab2")
                .displayName("Red Sandstone Slab")
                .material(Material.RED_SANDSTONE_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_fence() {
        SimpleSkyblockItem.builder()
                .id("fence")
                .displayName("Oak Fence")
                .material(Material.OAK_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Red Stained Glass")
                .material(Material.RED_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_chirp")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_light_gray_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Light Gray Carpet")
                .material(Material.LIGHT_GRAY_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_green_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Green Stained Clay")
//                .material(Material.GREEN_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_flint_and_steel() {
        SimpleSkyblockItem.builder()
                .id("flint_and_steel")
                .displayName("Flint and Steel")
                .material(Material.FLINT_AND_STEEL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_polished_andesite() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Polished Andesite")
                .material(Material.POLISHED_ANDESITE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_soul_sand() {
        SimpleSkyblockItem.builder()
                .id("soul_sand")
                .displayName("Soul Sand")
                .material(Material.SOUL_SAND)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lime_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Lime Banner")
                .material(Material.LIME_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_ender_chest() {
        SimpleSkyblockItem.builder()
                .id("ender_chest")
                .displayName("Ender Chest")
                .material(Material.ENDER_CHEST)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_hopper() {
        SimpleSkyblockItem.builder()
                .id("hopper")
                .displayName("Hopper")
                .material(Material.HOPPER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magenta_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Magenta Carpet")
                .material(Material.MAGENTA_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_prismarine() {
        SimpleSkyblockItem.builder()
                .id("prismarine")
                .displayName("Prismarine")
                .material(Material.PRISMARINE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chainmail_chestplate() {
        SimpleSkyblockItem.builder()
                .id("chainmail_chestplate")
                .displayName("Chainmail Chestplate")
                .material(Material.CHAINMAIL_CHESTPLATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.CHESTPLATE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_quartz_stairs() {
        SimpleSkyblockItem.builder()
                .id("quartz_stairs")
                .displayName("Quartz Stairs")
                .material(Material.QUARTZ_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gray_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Gray Wool")
                .material(Material.GRAY_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_poisonous_potato() {
        SimpleSkyblockItem.builder()
                .id("poisonous_potato")
                .displayName("Poisonous Potato")
                .material(Material.POISONOUS_POTATO)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_rail() {
        SimpleSkyblockItem.builder()
                .id("rail")
                .displayName("Rail")
                .material(Material.RAIL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_end_portal_frame() {
        SimpleSkyblockItem.builder()
                .id("end_portal_frame")
                .displayName("End Portal Frame")
                .material(Material.END_PORTAL_FRAME)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_nether_quartz() {
        SimpleSkyblockItem.builder()
                .id("quartz")
                .displayName("Nether Quartz")
                .material(Material.QUARTZ)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_blue_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Light Blue Dye")
                .material(Material.LIGHT_BLUE_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_black_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Black Wool")
                .material(Material.BLACK_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
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
//    }

    public static void minecraft_bricks() {
        SimpleSkyblockItem.builder()
                .id("brick_block")
                .displayName("Bricks")
                .material(Material.BRICKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_lime_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Lime Stained Clay")
//                .material(Material.LIME_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_redstone_repeater() {
        SimpleSkyblockItem.builder()
                .id("repeater")
                .displayName("Redstone Repeater")
                .material(Material.REPEATER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_green_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Green Wool")
                .material(Material.GREEN_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jungle_wood_planks() {
        SimpleSkyblockItem.builder()
                .id("planks")
                .displayName("Jungle Wood Planks")
                .material(Material.JUNGLE_PLANKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cooked_fish() {
        SimpleSkyblockItem.builder()
                .id("cooked_fish")
                .displayName("Cooked Fish")
                .material(Material.LEGACY_COOKED_FISH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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
//    }

    public static void minecraft_light_gray_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Light Gray Stained Glass")
                .material(Material.LIGHT_GRAY_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brick() {
        SimpleSkyblockItem.builder()
                .id("brick")
                .displayName("Brick")
                .material(Material.BRICK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_horse_armor() {
        SimpleSkyblockItem.builder()
                .id("diamond_horse_armor")
                .displayName("Diamond Horse Armor")
                .material(Material.DIAMOND_HORSE_ARMOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.EPIC)
                .build();
    }

    public static void minecraft_black_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Black Banner")
                .material(Material.BLACK_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brown_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Brown Stained Glass")
                .material(Material.BROWN_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_brown_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Brown Stained Clay")
//                .material(Material.BROWN_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_sandstone() {
        SimpleSkyblockItem.builder()
                .id("sandstone")
                .displayName("Sandstone")
                .material(Material.SANDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_raw_beef() {
        SimpleSkyblockItem.builder()
                .id("beef")
                .displayName("Raw Beef")
                .material(Material.BEEF)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_flower_pot() {
        SimpleSkyblockItem.builder()
                .id("flower_pot")
                .displayName("Flower Pot")
                .material(Material.FLOWER_POT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_blue_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Light Blue Stained Glass Pane")
                .material(Material.LIGHT_BLUE_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_gray_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Light Gray Stained Glass Pane")
                .material(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_eye_of_ender() {
        SimpleSkyblockItem.builder()
                .id("ender_eye")
                .displayName("Eye of Ender")
                .material(Material.ENDER_EYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_golden_boots() {
        SimpleSkyblockItem.builder()
                .id("golden_boots")
                .displayName("Golden Boots")
                .material(Material.GOLDEN_BOOTS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOOTS)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_sapling() {
        SimpleSkyblockItem.builder()
                .id("sapling")
                .displayName("Acacia Sapling")
                .material(Material.ACACIA_SAPLING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_grass_block() {
        SimpleSkyblockItem.builder()
                .id("grass")
                .displayName("Grass Block")
                .material(Material.GRASS_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_purple_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Purple Dye")
                .material(Material.PURPLE_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_wood_stairs() {
        SimpleSkyblockItem.builder()
                .id("oak_stairs")
                .displayName("Oak Wood Stairs")
                .material(Material.OAK_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_poppy() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Poppy")
                .material(Material.POPPY)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gold_horse_armor() {
        SimpleSkyblockItem.builder()
                .id("golden_horse_armor")
                .displayName("Gold Horse Armor")
                .material(Material.GOLDEN_HORSE_ARMOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.EPIC)
                .build();
    }

    public static void minecraft_trapped_chest() {
        SimpleSkyblockItem.builder()
                .id("trapped_chest")
                .displayName("Trapped Chest")
                .material(Material.TRAPPED_CHEST)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_wood_stairs() {
        SimpleSkyblockItem.builder()
                .id("spruce_stairs")
                .displayName("Spruce Wood Stairs")
                .material(Material.SPRUCE_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_11")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_steak() {
        SimpleSkyblockItem.builder()
                .id("cooked_beef")
                .displayName("Steak")
                .material(Material.COOKED_BEEF)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_packed_ice() {
        SimpleSkyblockItem.builder()
                .id("packed_ice")
                .displayName("Packed Ice")
                .material(Material.PACKED_ICE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sign() {
        SimpleSkyblockItem.builder()
                .id("sign")
                .displayName("Sign")
                .material(Material.OAK_SIGN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_iron() {
        SimpleSkyblockItem.builder()
                .id("iron_block")
                .displayName("Block of Iron")
                .material(Material.IRON_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_block_of_emerald() {
        SimpleSkyblockItem.builder()
                .id("emerald_block")
                .displayName("Block of Emerald")
                .material(Material.EMERALD_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sandstone_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Sandstone Slab")
                .material(Material.SANDSTONE_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_helmet() {
        SimpleSkyblockItem.builder()
                .id("iron_helmet")
                .displayName("Iron Helmet")
                .material(Material.IRON_HELMET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.HELMET)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_white_tulip() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("White Tulip")
                .material(Material.WHITE_TULIP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_nether_brick() {
        SimpleSkyblockItem.builder()
                .id("nether_brick")
                .displayName("Nether Brick")
                .material(Material.NETHER_BRICK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brown_mushroom() {
        SimpleSkyblockItem.builder()
                .id("brown_mushroom")
                .displayName("Brown Mushroom")
                .material(Material.BROWN_MUSHROOM)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dead_bush() {
        SimpleSkyblockItem.builder()
                .id("deadbush")
                .displayName("Dead Bush")
                .material(Material.DEAD_BUSH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lime_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Lime Dye")
                .material(Material.LIME_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_wooden_trapdoor() {
        SimpleSkyblockItem.builder()
                .id("trapdoor")
                .displayName("Wooden Trapdoor")
                .material(Material.OAK_TRAPDOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blue_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Blue Carpet")
                .material(Material.BLUE_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_door() {
        SimpleSkyblockItem.builder()
                .id("dark_oak_door")
                .displayName("Dark Oak Door")
                .material(Material.DARK_OAK_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pink_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Pink Stained Glass Pane")
                .material(Material.PINK_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cyan_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Cyan Carpet")
                .material(Material.CYAN_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_minecart_with_hopper() {
        SimpleSkyblockItem.builder()
                .id("hopper_minecart")
                .displayName("Minecart with Hopper")
                .material(Material.HOPPER_MINECART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lilac() {
        SimpleSkyblockItem.builder()
                .id("double_plant")
                .displayName("Lilac")
                .material(Material.LILAC)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_wood() {
        SimpleSkyblockItem.builder()
                .id("log")
                .displayName("Oak Wood")
                .material(Material.OAK_WOOD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_ore() {
        SimpleSkyblockItem.builder()
                .id("iron_ore")
                .displayName("Iron Ore")
                .material(Material.IRON_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_rose_bush() {
        SimpleSkyblockItem.builder()
                .id("double_plant")
                .displayName("Rose Bush")
                .material(Material.ROSE_BUSH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dandelion() {
        SimpleSkyblockItem.builder()
                .id("yellow_flower")
                .displayName("Dandelion")
                .material(Material.DANDELION)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Red Stained Glass Pane")
                .material(Material.RED_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_melon_block() {
        SimpleSkyblockItem.builder()
                .id("melon_block")
                .displayName("Melon Block")
                .material(Material.LEGACY_MELON_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_moss_stone() {
        SimpleSkyblockItem.builder()
                .id("mossy_cobblestone")
                .displayName("Moss Stone")
                .material(Material.MOSS_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_minecart_with_furnace() {
        SimpleSkyblockItem.builder()
                .id("furnace_minecart")
                .displayName("Minecart with Furnace")
                .material(Material.FURNACE_MINECART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_black_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Black Stained Clay")
//                .material(Material.BLACK_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_jungle_sapling() {
        SimpleSkyblockItem.builder()
                .id("sapling")
                .displayName("Jungle Sapling")
                .material(Material.JUNGLE_SAPLING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_snow() {
        SimpleSkyblockItem.builder()
                .id("snow_layer")
                .displayName("Snow")
                .material(Material.SNOW)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_leggings() {
        SimpleSkyblockItem.builder()
                .id("iron_leggings")
                .displayName("Iron Leggings")
                .material(Material.IRON_LEGGINGS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.LEGGINGS)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pink_tulip() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Pink Tulip")
                .material(Material.PINK_TULIP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_pink_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Pink Stained Clay")
//                .material(Material.PINK_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_dark_oak_fence() {
        SimpleSkyblockItem.builder()
                .id("dark_oak_fence")
                .displayName("Dark Oak Fence")
                .material(Material.DARK_OAK_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_redstone_ore() {
        SimpleSkyblockItem.builder()
                .id("redstone_ore")
                .displayName("Redstone Ore")
                .material(Material.REDSTONE_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_sapling() {
        SimpleSkyblockItem.builder()
                .id("sapling")
                .displayName("Birch Sapling")
                .material(Material.BIRCH_SAPLING)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dropper() {
        SimpleSkyblockItem.builder()
                .id("dropper")
                .displayName("Dropper")
                .material(Material.DROPPER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_leather_helmet() {
        SimpleSkyblockItem.builder()
                .id("leather_helmet")
                .displayName("Leather Helmet")
                .material(Material.LEATHER_HELMET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.HELMET)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bowl() {
        SimpleSkyblockItem.builder()
                .id("bowl")
                .displayName("Bowl")
                .material(Material.BOWL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOW)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magenta_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Magenta Wool")
                .material(Material.MAGENTA_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_tripwire_hook() {
        SimpleSkyblockItem.builder()
                .id("tripwire_hook")
                .displayName("Tripwire Hook")
                .material(Material.TRIPWIRE_HOOK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jack_o_lantern() {
        SimpleSkyblockItem.builder()
                .id("lit_pumpkin")
                .displayName("Jack o' Lantern")
                .material(Material.JACK_O_LANTERN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_lapis_lazuli_block() {
        SimpleSkyblockItem.builder()
                .id("lapis_block")
                .displayName("Lapis Lazuli Block")
                .material(Material.LAPIS_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_brick_stairs() {
        SimpleSkyblockItem.builder()
                .id("brick_stairs")
                .displayName("Brick Stairs")
                .material(Material.BRICK_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_leather_boots() {
        SimpleSkyblockItem.builder()
                .id("leather_boots")
                .displayName("Leather Boots")
                .material(Material.LEATHER_BOOTS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOOTS)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blue_orchid() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Blue Orchid")
                .material(Material.BLUE_ORCHID)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_melon_seeds() {
        SimpleSkyblockItem.builder()
                .id("melon_seeds")
                .displayName("Melon Seeds")
                .material(Material.MELON_SEEDS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_seeds() {
        SimpleSkyblockItem.builder()
                .id("wheat_seeds")
                .displayName("Seeds")
                .material(Material.WHEAT_SEEDS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pink_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Pink Stained Glass")
                .material(Material.PINK_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_peony() {
        SimpleSkyblockItem.builder()
                .id("double_plant")
                .displayName("Peony")
                .material(Material.PEONY)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_activator_rail() {
        SimpleSkyblockItem.builder()
                .id("activator_rail")
                .displayName("Activator Rail")
                .material(Material.ACTIVATOR_RAIL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_feather() {
        SimpleSkyblockItem.builder()
                .id("feather")
                .displayName("Feather")
                .material(Material.FEATHER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_cyan_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Cyan Stained Clay")
//                .material(Material.CYAN_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_gray_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Gray Carpet")
                .material(Material.GRAY_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_firework_star() {
        SimpleSkyblockItem.builder()
                .id("firework_charge")
                .displayName("Firework Star")
                .material(Material.FIREWORK_STAR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_coal_ore() {
        SimpleSkyblockItem.builder()
                .id("coal_ore")
                .displayName("Coal Ore")
                .material(Material.COAL_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chiseled_stone_bricks() {
        SimpleSkyblockItem.builder()
                .id("stonebrick")
                .displayName("Chiseled Stone Bricks")
                .material(Material.CHISELED_STONE_BRICKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_hoe() {
        SimpleSkyblockItem.builder()
                .id("iron_hoe")
                .displayName("Iron Hoe")
                .material(Material.IRON_HOE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_blue_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Blue Stained Clay")
//                .material(Material.BLUE_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_glistering_melon() {
        SimpleSkyblockItem.builder()
                .id("speckled_melon")
                .displayName("Glistering Melon")
                .material(Material.GLISTERING_MELON_SLICE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blue_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Blue Stained Glass Pane")
                .material(Material.BLUE_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_white_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("White Banner")
                .material(Material.WHITE_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_crafting_table() {
        SimpleSkyblockItem.builder()
                .id("crafting_table")
                .displayName("Crafting Table")
                .material(Material.CRAFTING_TABLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_button() {
        SimpleSkyblockItem.builder()
                .id("wooden_button")
                .displayName("Button")
                .material(Material.OAK_BUTTON)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_fern() {
        SimpleSkyblockItem.builder()
                .id("tallgrass")
                .displayName("Fern")
                .material(Material.FERN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dispenser() {
        SimpleSkyblockItem.builder()
                .id("dispenser")
                .displayName("Dispenser")
                .material(Material.DISPENSER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blaze_powder() {
        SimpleSkyblockItem.builder()
                .id("blaze_powder")
                .displayName("Blaze Powder")
                .material(Material.BLAZE_POWDER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cake() {
        SimpleSkyblockItem.builder()
                .id("cake")
                .displayName("Cake")
                .material(Material.CAKE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_gray_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Light Gray Banner")
                .material(Material.LIGHT_GRAY_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jungle_fence() {
        SimpleSkyblockItem.builder()
                .id("jungle_fence")
                .displayName("Jungle Fence")
                .material(Material.JUNGLE_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_saddle() {
        SimpleSkyblockItem.builder()
                .id("saddle")
                .displayName("Saddle")
                .material(Material.SADDLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_white_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("White Stained Glass")
                .material(Material.WHITE_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_green_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Green Carpet")
                .material(Material.GREEN_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_diamond_leggings() {
        SimpleSkyblockItem.builder()
                .id("diamond_leggings")
                .displayName("Diamond Leggings")
                .material(Material.DIAMOND_LEGGINGS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.LEGGINGS)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_cookie() {
        SimpleSkyblockItem.builder()
                .id("cookie")
                .displayName("Cookie")
                .material(Material.COOKIE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chainmail_helmet() {
        SimpleSkyblockItem.builder()
                .id("chainmail_helmet")
                .displayName("Chainmail Helmet")
                .material(Material.CHAINMAIL_HELMET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.HELMET)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

//    public static void minecraft_light_gray_stained_clay() {
//        SimpleSkyblockItem.builder()
//                .id("stained_hardened_clay")
//                .displayName("Light Gray Stained Clay")
//                .material(Material.LIGHT_GRAY_STAINED_CLAY)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_compass() {
        SimpleSkyblockItem.builder()
                .id("compass")
                .displayName("Compass")
                .material(Material.COMPASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_purple_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Purple Wool")
                .material(Material.PURPLE_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sugar() {
        SimpleSkyblockItem.builder()
                .id("sugar")
                .displayName("Sugar")
                .material(Material.SUGAR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_snow_block() {
        SimpleSkyblockItem.builder()
                .id("snow")
                .displayName("Snow Block")
                .material(Material.SNOW_BLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_black_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Black Stained Glass")
                .material(Material.BLACK_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_sandstone_stairs() {
        SimpleSkyblockItem.builder()
                .id("red_sandstone_stairs")
                .displayName("Red Sandstone Stairs")
                .material(Material.RED_SANDSTONE_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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
//    }

    public static void minecraft_cooked_chicken() {
        SimpleSkyblockItem.builder()
                .id("cooked_chicken")
                .displayName("Cooked Chicken")
                .material(Material.COOKED_CHICKEN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_end_stone() {
        SimpleSkyblockItem.builder()
                .id("end_stone")
                .displayName("End Stone")
                .material(Material.END_STONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_blue_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Light Blue Banner")
                .material(Material.LIGHT_BLUE_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_cyan_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Cyan Dye")
                .material(Material.CYAN_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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

    public static void minecraft_pillar_quartz_block() {
        SimpleSkyblockItem.builder()
                .id("quartz_block")
                .displayName("Pillar Quartz Block")
                .material(Material.QUARTZ_PILLAR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_yellow_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Yellow Wool")
                .material(Material.YELLOW_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Stone")
                .material(Material.STONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_gray_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Light Gray Wool")
                .material(Material.LIGHT_GRAY_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_yellow_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Yellow Stained Glass")
                .material(Material.YELLOW_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_wood_planks() {
        SimpleSkyblockItem.builder()
                .id("planks")
                .displayName("Acacia Wood Planks")
                .material(Material.ACACIA_PLANKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_ladder() {
        SimpleSkyblockItem.builder()
                .id("ladder")
                .displayName("Ladder")
                .material(Material.LADDER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_acacia_wood() {
        SimpleSkyblockItem.builder()
                .id("log2")
                .displayName("Acacia Wood")
                .material(Material.ACACIA_WOOD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magenta_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Magenta Stained Glass")
                .material(Material.MAGENTA_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_pink_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Pink Carpet")
                .material(Material.PINK_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magenta_stained_glass_pane() {
        SimpleSkyblockItem.builder()
                .id("stained_glass_pane")
                .displayName("Magenta Stained Glass Pane")
                .material(Material.MAGENTA_STAINED_GLASS_PANE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_ingot() {
        SimpleSkyblockItem.builder()
                .id("iron_ingot")
                .displayName("Iron Ingot")
                .material(Material.IRON_INGOT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_chainmail_boots() {
        SimpleSkyblockItem.builder()
                .id("chainmail_boots")
                .displayName("Chainmail Boots")
                .material(Material.CHAINMAIL_BOOTS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOOTS)
                .baseRarity(Rarity.UNCOMMON)
                .build();
    }

    public static void minecraft_light_blue_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("Light Blue Wool")
                .material(Material.LIGHT_BLUE_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_emerald_ore() {
        SimpleSkyblockItem.builder()
                .id("emerald_ore")
                .displayName("Emerald Ore")
                .material(Material.EMERALD_ORE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_ice() {
        SimpleSkyblockItem.builder()
                .id("ice")
                .displayName("Ice")
                .material(Material.ICE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_blue_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Blue Banner")
                .material(Material.BLUE_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_green_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Green Stained Glass")
                .material(Material.GREEN_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bricks_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Bricks Slab")
                .material(Material.BRICK_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_mossy_stone_bricks() {
        SimpleSkyblockItem.builder()
                .id("stonebrick")
                .displayName("Mossy Stone Bricks")
                .material(Material.MOSSY_STONE_BRICKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_daylight_sensor() {
        SimpleSkyblockItem.builder()
                .id("daylight_detector")
                .displayName("Daylight Sensor")
                .material(Material.DAYLIGHT_DETECTOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_rabbits_foot() {
        SimpleSkyblockItem.builder()
                .id("rabbit_foot")
                .displayName("Rabbit's Foot")
                .material(Material.RABBIT_FOOT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_leather_leggings() {
        SimpleSkyblockItem.builder()
                .id("leather_leggings")
                .displayName("Leather Leggings")
                .material(Material.LEATHER_LEGGINGS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.LEGGINGS)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sunflower() {
        SimpleSkyblockItem.builder()
                .id("double_plant")
                .displayName("Sunflower")
                .material(Material.SUNFLOWER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_iron_trapdoor() {
        SimpleSkyblockItem.builder()
                .id("iron_trapdoor")
                .displayName("Iron Trapdoor")
                .material(Material.IRON_TRAPDOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_light_gray_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Light Gray Dye")
                .material(Material.LIGHT_GRAY_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cobblestone_slab() {
        SimpleSkyblockItem.builder()
                .id("stone_slab")
                .displayName("Cobblestone Slab")
                .material(Material.COBBLESTONE_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
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
//    }

    public static void minecraft_cobblestone() {
        SimpleSkyblockItem.builder()
                .id("cobblestone")
                .displayName("Cobblestone")
                .material(Material.COBBLESTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jungle_wood() {
        SimpleSkyblockItem.builder()
                .id("log")
                .displayName("Jungle Wood")
                .material(Material.JUNGLE_WOOD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bow() {
        SimpleSkyblockItem.builder()
                .id("bow")
                .displayName("Bow")
                .material(Material.BOW)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.BOW)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_ender_pearl() {
        SimpleSkyblockItem.builder()
                .id("ender_pearl")
                .displayName("Ender Pearl")
                .material(Material.ENDER_PEARL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_rotten_flesh() {
        SimpleSkyblockItem.builder()
                .id("rotten_flesh")
                .displayName("Rotten Flesh")
                .material(Material.ROTTEN_FLESH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_apple() {
        SimpleSkyblockItem.builder()
                .id("apple")
                .displayName("Apple")
                .material(Material.APPLE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jungle_fence_gate() {
        SimpleSkyblockItem.builder()
                .id("jungle_fence_gate")
                .displayName("Jungle Fence Gate")
                .material(Material.JUNGLE_FENCE_GATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_cooked_porkchop() {
        SimpleSkyblockItem.builder()
                .id("cooked_porkchop")
                .displayName("Cooked Porkchop")
                .material(Material.COOKED_PORKCHOP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_fence_gate() {
        SimpleSkyblockItem.builder()
                .id("birch_fence_gate")
                .displayName("Birch Fence Gate")
                .material(Material.BIRCH_FENCE_GATE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_empty_map() {
        SimpleSkyblockItem.builder()
                .id("map")
                .displayName("Empty Map")
                .material(Material.MAP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_vines() {
        SimpleSkyblockItem.builder()
                .id("vine")
                .displayName("Vines")
                .material(Material.VINE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sponge() {
        SimpleSkyblockItem.builder()
                .id("sponge")
                .displayName("Sponge")
                .material(Material.SPONGE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_wood_stairs() {
        SimpleSkyblockItem.builder()
                .id("dark_oak_stairs")
                .displayName("Dark Oak Wood Stairs")
                .material(Material.DARK_OAK_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_nether_brick_fence() {
        SimpleSkyblockItem.builder()
                .id("nether_brick_fence")
                .displayName("Nether Brick Fence")
                .material(Material.NETHER_BRICK_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_barrier() {
        SimpleSkyblockItem.builder()
                .id("barrier")
                .displayName("Barrier")
                .material(Material.BARRIER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_shears() {
        SimpleSkyblockItem.builder()
                .id("shears")
                .displayName("Shears")
                .material(Material.SHEARS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_book_and_quill() {
        SimpleSkyblockItem.builder()
                .id("writable_book")
                .displayName("Book and Quill")
                .material(Material.LEGACY_BOOK_AND_QUILL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gray_stained_glass() {
        SimpleSkyblockItem.builder()
                .id("stained_glass")
                .displayName("Gray Stained Glass")
                .material(Material.GRAY_STAINED_GLASS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_nether_wart() {
        SimpleSkyblockItem.builder()
                .id("nether_wart")
                .displayName("Nether Wart")
                .material(Material.NETHER_WART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_granite() {
        SimpleSkyblockItem.builder()
                .id("stone")
                .displayName("Granite")
                .material(Material.GRANITE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_prismarine() {
        SimpleSkyblockItem.builder()
                .id("prismarine")
                .displayName("Dark Prismarine")
                .material(Material.DARK_PRISMARINE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_white_wool() {
        SimpleSkyblockItem.builder()
                .id("wool")
                .displayName("White Wool")
                .material(Material.WHITE_WOOL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_paper() {
        SimpleSkyblockItem.builder()
                .id("paper")
                .displayName("Paper")
                .material(Material.PAPER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_rabbit_hide() {
        SimpleSkyblockItem.builder()
                .id("rabbit_hide")
                .displayName("Rabbit Hide")
                .material(Material.RABBIT_HIDE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Red Banner")
                .material(Material.RED_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jungle_wood_slab() {
        SimpleSkyblockItem.builder()
                .id("wooden_slab")
                .displayName("Jungle Wood Slab")
                .material(Material.JUNGLE_SLAB)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_bucket() {
        SimpleSkyblockItem.builder()
                .id("bucket")
                .displayName("Bucket")
                .material(Material.BUCKET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_birch_door() {
        SimpleSkyblockItem.builder()
                .id("birch_door")
                .displayName("Birch Door")
                .material(Material.BIRCH_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spider_eye() {
        SimpleSkyblockItem.builder()
                .id("spider_eye")
                .displayName("Spider Eye")
                .material(Material.SPIDER_EYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_jukebox() {
        SimpleSkyblockItem.builder()
                .id("jukebox")
                .displayName("Jukebox")
                .material(Material.JUKEBOX)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_purple_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Purple Carpet")
                .material(Material.PURPLE_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_stone_monster_egg() {
        SimpleSkyblockItem.builder()
                .id("monster_egg")
                .displayName("Stone Monster Egg")
                .material(Material.LEGACY_MONSTER_EGG)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_orange_tulip() {
        SimpleSkyblockItem.builder()
                .id("red_flower")
                .displayName("Orange Tulip")
                .material(Material.ORANGE_TULIP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_prismarine_bricks() {
        SimpleSkyblockItem.builder()
                .id("prismarine")
                .displayName("Prismarine Bricks")
                .material(Material.PRISMARINE_BRICKS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_brown_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Brown Banner")
                .material(Material.BROWN_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Carpet")
                .material(Material.RED_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_magenta_dye() {
        SimpleSkyblockItem.builder()
                .id("dye")
                .displayName("Magenta Dye")
                .material(Material.MAGENTA_DYE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_fence() {
        SimpleSkyblockItem.builder()
                .id("spruce_fence")
                .displayName("Spruce Fence")
                .material(Material.SPRUCE_FENCE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_raw_porkchop() {
        SimpleSkyblockItem.builder()
                .id("porkchop")
                .displayName("Raw Porkchop")
                .material(Material.PORKCHOP)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_firework_rocket() {
        SimpleSkyblockItem.builder()
                .id("fireworks")
                .displayName("Firework Rocket")
                .material(Material.FIREWORK_ROCKET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_raw_fish() {
        SimpleSkyblockItem.builder()
                .id("fish")
                .displayName("Raw Fish")
                .material(Material.LEGACY_RAW_FISH)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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

    public static void minecraft_bone() {
        SimpleSkyblockItem.builder()
                .id("bone")
                .displayName("Bone")
                .material(Material.BONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_dark_oak_wood() {
        SimpleSkyblockItem.builder()
                .id("log2")
                .displayName("Dark Oak Wood")
                .material(Material.DARK_OAK_WOOD)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_spruce_door() {
        SimpleSkyblockItem.builder()
                .id("spruce_door")
                .displayName("Spruce Door")
                .material(Material.SPRUCE_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_oak_door() {
        SimpleSkyblockItem.builder()
                .id("wooden_door")
                .displayName("Oak Door")
                .material(Material.OAK_DOOR)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
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
//    }

    public static void minecraft_pink_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Pink Banner")
                .material(Material.PINK_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_coal() {
        SimpleSkyblockItem.builder()
                .id("coal")
                .displayName("Coal")
                .material(Material.COAL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

//    public static void minecraft_music_disc() {
//        SimpleSkyblockItem.builder()
//                .id("record_strad")
//                .displayName("Music Disc")
//                .material(Material.MUSIC_DISC)
//                .stats(Map.of(Stats.DAMAGE, 0d))
//                .itemType(SkyblockItemType.NONE)
//                .baseRarity(Rarity.COMMON)
//                .build();
//    }

    public static void minecraft_gold_ingot() {
        SimpleSkyblockItem.builder()
                .id("gold_ingot")
                .displayName("Gold Ingot")
                .material(Material.GOLD_INGOT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_leather() {
        SimpleSkyblockItem.builder()
                .id("leather")
                .displayName("Leather")
                .material(Material.LEATHER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_yellow_banner() {
        SimpleSkyblockItem.builder()
                .id("banner")
                .displayName("Yellow Banner")
                .material(Material.YELLOW_BANNER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

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

    public static void minecraft_clock() {
        SimpleSkyblockItem.builder()
                .id("clock")
                .displayName("Clock")
                .material(Material.CLOCK)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_sea_lantern() {
        SimpleSkyblockItem.builder()
                .id("sea_lantern")
                .displayName("Sea Lantern")
                .material(Material.SEA_LANTERN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_gunpowder() {
        SimpleSkyblockItem.builder()
                .id("gunpowder")
                .displayName("Gunpowder")
                .material(Material.GUNPOWDER)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_minecart_with_command_block() {
        SimpleSkyblockItem.builder()
                .id("command_block_minecart")
                .displayName("Minecart with Command Block")
                .material(Material.COMMAND_BLOCK_MINECART)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_obsidian() {
        SimpleSkyblockItem.builder()
                .id("obsidian")
                .displayName("Obsidian")
                .material(Material.OBSIDIAN)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_golden_helmet() {
        SimpleSkyblockItem.builder()
                .id("golden_helmet")
                .displayName("Golden Helmet")
                .material(Material.GOLDEN_HELMET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.HELMET)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_wheat() {
        SimpleSkyblockItem.builder()
                .id("wheat")
                .displayName("Wheat")
                .material(Material.WHEAT)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_red_carpet() {
        SimpleSkyblockItem.builder()
                .id("carpet")
                .displayName("Red Carpet")
                .material(Material.RED_CARPET)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_nether_brick_stairs() {
        SimpleSkyblockItem.builder()
                .id("nether_brick_stairs")
                .displayName("Nether Brick Stairs")
                .material(Material.NETHER_BRICK_STAIRS)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_armor_stand() {
        SimpleSkyblockItem.builder()
                .id("armor_stand")
                .displayName("Armor Stand")
                .material(Material.ARMOR_STAND)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }

    public static void minecraft_skeleton_skull() {
        SimpleSkyblockItem.builder()
                .id("skull")
                .displayName("Skeleton Skull")
                .material(Material.SKELETON_SKULL)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.RARE)
                .build();
    }

    public static void minecraft_smooth_red_sandstone() {
        SimpleSkyblockItem.builder()
                .id("red_sandstone")
                .displayName("Smooth Red Sandstone")
                .material(Material.SMOOTH_RED_SANDSTONE)
                .stats(Map.of(Stats.DAMAGE, 0d))
                .itemType(SkyblockItemType.NONE)
                .baseRarity(Rarity.COMMON)
                .build();
    }
}
