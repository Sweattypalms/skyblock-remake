package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.UltimateEnchantment;
import com.sweattypalms.skyblock.core.helpers.DamageCalculator;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.*;
import com.sweattypalms.skyblock.core.items.builder.armor.IDyedArmor;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.items.builder.reforges.IAdvancedReforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.Reforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import lombok.Getter;
import net.minecraft.util.Tuple;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import javax.annotation.Nullable;
import java.util.*;

@Getter
public abstract class SkyblockItem {
    private final String id;
    private final String displayName;
    private final Material material;
    private final List<String> staticLore;
    private final Map<Stats, Double> stats;
    private final Rarity rarity;
    private final SkyblockItemType itemType;

    /**
     * @param id          "diamond_sword"
     * @param displayName "Diamond Sword"
     * @param material    Material.DIAMOND_SWORD
     * @param staticLore  List.of(" $7This is a diamond sword"), Use $ as a placeholder
     * @param stats       Map.of(Stats.DAMAGE, 35d)
     * @param baseRarity  Rarity.RARE
     * @param itemType    SkyblockItemType.SWORD
     */
    public SkyblockItem(String id, String displayName, Material material, @Nullable List<String> staticLore, Map<Stats, Double> stats, Rarity baseRarity, SkyblockItemType itemType) {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
        this.staticLore = staticLore;
        this.stats = new HashMap<>();
        Arrays.stream(Stats.values()).toList().forEach(stat -> this.stats.put(stat, stats.getOrDefault(stat, 0d)));
        this.rarity = baseRarity;
        this.itemType = itemType;
    }

    public static SkyblockItem fromItemStack(ItemStack item) {
        String id = PDCHelper.getString(item, "id");
        return ItemManager.ITEMS_LIST.getOrDefault(id, null);
    }

    public static SkyblockItem get(String id) {
        return ItemManager.ITEMS_LIST.getOrDefault(id, null);
    }

    public static List<String> buildStatsLore(Map<Stats, Double> stats, @Nullable Reforge reforge, Rarity rarity) {
        List<String> lore = new ArrayList<>();

        Map<Stats, Double> reforgeStats = (reforge != null) ? reforge.getReforgeStats(rarity) : Collections.emptyMap();
        Arrays.stream(Stats.values()).toList().forEach(stat -> {
            double value = stats.getOrDefault(stat, 0.0);
            StringBuilder stringBuilder = new StringBuilder();

            double reforgeValue = reforgeStats.getOrDefault(stat, 0.0);
            double combinedValue = value + reforgeValue;

            if (combinedValue == 0) return;


            stringBuilder.append("$7");
            stringBuilder.append(stat.getName());
            stringBuilder.append(": ");
            stringBuilder.append(stat.getItemBuilderColor());
            stringBuilder.append((combinedValue > 0) ? "+" : "-");
            String combinedValueStr = String.valueOf(Math.abs(combinedValue)).replace(".0", "");
            stringBuilder.append(combinedValueStr);

            if (reforgeValue != 0) {
                stringBuilder.append(" $9(");
                stringBuilder.append((reforgeValue > 0) ? "+" : "-");
                stringBuilder.append(String.valueOf(Math.abs(reforgeValue)).replace(".0", ""));
                if (stat.isPercentage()) {
                    stringBuilder.append("%");
                }
                stringBuilder.append(")");
            }

            if (stat.isPercentage() && reforgeValue == 0) {
                stringBuilder.append("%");
            }
            String statLine = PlaceholderFormatter.format(stringBuilder.toString());
            lore.add(statLine);
        });
        return lore;
    }

    private static void buildStatsPDC(ItemStack item, Map<Stats, Double> stats) {
        stats.forEach((stat, value) -> PDCHelper.setDouble(item, "stat." + stat.name().toLowerCase(), value));
    }

    public static ItemStack updateItemStack(SkyblockPlayer skyblockPlayer) {
        return updateItemStack(skyblockPlayer, null);
    }

    @SuppressWarnings("ReplaceAll")
    public static ItemStack updateItemStack(SkyblockPlayer skyblockPlayer, @Nullable ItemStack item) {
        ItemStack itemStack = item == null ? skyblockPlayer.getInventoryManager().getItemInHand() : item;

        String id = PDCHelper.getString(itemStack, "id");

        if (id == null) return itemStack;

        SkyblockItem skyblockItem = ItemManager.ITEMS_LIST.get(id);

        Map<Stats, Double> stats = getStatsFromItemStack(itemStack);
        String reforgeStr = PDCHelper.getOrDefault(itemStack, "reforge", "None");
        Reforge reforge = ReforgeManager.getReforge(reforgeStr);
        String rarityStr = PDCHelper.getString(itemStack, "rarity");
        Rarity rarity = Rarity.valueOf(rarityStr);
        boolean upgraded = skyblockItem.getRarity().getUpgraded().name().equals(rarityStr);

        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(skyblockItem.f_getDisplayName(upgraded, reforge));

        List<String> lore = new ArrayList<>(
                buildStatsLore(stats, reforge, rarity)
        );

        if (skyblockItem instanceof IShortBow) {
            // Shot Cooldown: 0.3s
            String builder = "$7Shot Cooldown: $a" +
                    DamageCalculator.getShortbowCooldownFmt(skyblockPlayer);

            lore.add(PlaceholderFormatter.format(builder));
        }

        if (!lore.isEmpty()) lore.add("§7");

        List<String> enchantmentsLore = buildEnchantmentsLore(itemStack);
        lore.addAll(enchantmentsLore);

        if (!enchantmentsLore.isEmpty()) lore.add("§7");

        /**
         * If the item has a personalized description, use that
         */
        if (skyblockItem instanceof IPersonalizedDescription personalizedDescription) {
            lore.addAll(PlaceholderFormatter.format(personalizedDescription.getDescription(skyblockPlayer, itemStack)));
        } else {
            lore.addAll(skyblockItem.f_getStaticLore());
        }

        if (skyblockItem.getStaticLore() != null || skyblockItem instanceof IPersonalizedDescription) lore.add("§7");

        if (skyblockItem instanceof IShortBow shortBow) {
            lore.add(rarity.getColor() + "Shortbow: Instantly shoots!");
            lore.addAll(PlaceholderFormatter.format(shortBow.getShortBowDescription()));
            lore.add("§7");
        }

        String ultimateWise = "ultimate_wise";
        int reduceBy = EnchantManager.getEnchantment(itemStack, ultimateWise).orElse(0) * 10;
        lore.addAll(skyblockItem.f_getAbilityLore(reduceBy));

        lore.addAll(ReforgeManager.getReforgeLore(reforge));

        if (reforge instanceof IAdvancedReforge) lore.add("§7"); // Will automatically check if the reforge isn't null

        lore.addAll(skyblockItem.f_getRarityLine(upgraded));

        meta.setLore(lore);

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static Map<Stats, Double> getStatsFromItemStack(ItemStack itemStack) {
        Map<Stats, Double> stats = new HashMap<>();
        Arrays.stream(Stats.values()).toList().forEach(stat -> stats.put(stat, PDCHelper.getOrDefault(itemStack, "stat." + stat.name().toLowerCase(), 0d)));
        return stats;
    }

/*    private static List<String> buildEnchantmentsLore(ItemStack item) {
//        List<String> lore = new ArrayList<>();
//
//        int amount = 0;
//        StringBuilder line = new StringBuilder();
//
//        List<Tuple<Enchantment, Integer>> enchantments = EnchantManager.getEnchantments(item);
//
//        // order by if enchantment is ultimate or not,
//        // ultimate will be first
//        enchantments.sort((o1, o2) -> {
//            if (o1.a() instanceof UltimateEnchantment) return -1;
//            if (o2.a() instanceof UltimateEnchantment) return 1;
//
//            return 0;
//        });
//
//        boolean showDescription = enchantments.size() < 3;
//
//        for (Tuple<Enchantment, Integer> enchantmentTuple : enchantments) {
//            Enchantment enchantment = enchantmentTuple.a();
//
//            String name = enchantment.getName();
//
//            if (enchantmentTuple.b() < 1) continue;
//
//            String formatted = enchantment instanceof UltimateEnchantment ? "$d$l" : "$9";
//
//            String toRomanNumeral;
//            try {
//                toRomanNumeral = PlaceholderFormatter.toRomanNumeral(enchantmentTuple.b());
//            } catch (Exception e) {
//                toRomanNumeral = ">" + PlaceholderFormatter.toRomanNumeral(3999); // 3999 is the max
//            }
//
//            formatted += name + " " + toRomanNumeral;
//
//            if (amount++ % 2 == 0) { // new line
//                line = new StringBuilder(formatted);
//
//            } else  { // add to previous line
//                line.append(", ").append(formatted);
//                lore.add(line.toString());
//                line = new StringBuilder();
//            }
//        }
//
//        if (!line.isEmpty()) {
//            lore.add(line.toString());
//        }
//
//        return PlaceholderFormatter.format(lore);
}*/

    private static List<String> buildEnchantmentsLore(ItemStack item) {
        List<String> lore = new ArrayList<>();

        List<Tuple<Enchantment, Integer>> enchantments = EnchantManager.getEnchantments(item);

        // Sort by if enchantment is ultimate or not, ultimate will be first
        enchantments.sort((o1, o2) -> {
            if (o1.a() instanceof UltimateEnchantment) return -1;
            if (o2.a() instanceof UltimateEnchantment) return 1;
            return 0;
        });

        boolean showDescription = enchantments.size() <= 3;
        StringBuilder line = new StringBuilder();

        for (Tuple<Enchantment, Integer> enchantmentTuple : enchantments) {
            Enchantment enchantment = enchantmentTuple.a();
            int level = enchantmentTuple.b();

            if (level < 1) continue;

            String formatted = (enchantment instanceof UltimateEnchantment ? "$d$l" : "$9") +
                    enchantment.getName() + " " + PlaceholderFormatter.toRomanNumeral(level);

            if (showDescription) {
                // Add enchantment name and level
                lore.add(formatted);
                // Add description below the enchantment
                List<String> description = enchantment.getDescription(level);
                description.forEach(desc -> lore.add("$7" + desc));
            } else {
                // If not showing descriptions, format enchantments in pairs
                if (line.length() == 0) {
                    line = new StringBuilder(formatted);
                } else {
                    line.append(", ").append(formatted);
                    lore.add(line.toString());
                    line = new StringBuilder(); // Reset the line after adding to lore
                }
            }
        }

        // If there's a remaining enchantment that hasn't been added to the lore
        if (line.length() > 0) {
            lore.add(line.toString());
        }

        return PlaceholderFormatter.format(lore);
    }

    public ItemStack toItemStack() {
        ItemStack item;
        if (this instanceof IHeadHelmet headHelmet && headHelmet.getTexture() != null) {
            item = headHelmet.getHeadItemStack();
        } else {
            item = new ItemStack(this.material);
        }

        ItemMeta meta = item.getItemMeta();

        assert meta != null;

        if (this instanceof IDyedArmor dyedArmor) {
            ((LeatherArmorMeta) meta).setColor(dyedArmor.getColor());
        }

        // ------- DISPLAY NAME -------
        meta.setDisplayName(f_getDisplayName());
        // ------- DISPLAY NAME -------

        // ------- LORE -------
        List<String> lore = buildLore();
        meta.setLore(lore);
        // ------- LORE -------

        Arrays.stream(ItemFlag.values()).toList().forEach(meta::addItemFlags);
        meta.setUnbreakable(true);

        item.setItemMeta(meta);

        // ------- STATS -------
        buildStatsPDC(item);
        // ------- STATS -------

        // ------- PERSISTENT DATA CONTAINER -------
        PDCHelper.set(item, "id", this.id);
//        PDCHelper.set(item, "lore", singleLineLore.toString()); // Implement later when making Dctr Space Helmet
        PDCHelper.set(item, "rarity", this.rarity.name());
        // ------- PERSISTENT DATA CONTAINER -------

        return item;
    }

    private List<String> buildLore() {
        Map<Stats, Double> stats = this.stats;
        List<String> lore = new ArrayList<>(
                buildStatsLore(stats)
        );
        if (!lore.isEmpty()) lore.add("§7");

        lore.addAll(f_getStaticLore());

        if (this.staticLore != null) lore.add("§7");

        if (this instanceof IShortBow shortBow) {
            lore.add("§6Shortbow: Instantly shoots!");
            lore.addAll(PlaceholderFormatter.format(shortBow.getShortBowDescription()));
            lore.add("§7");
        }

        lore.addAll(f_getAbilityLore());

        lore.addAll(f_getRarityLine());

        return lore;
    }

    private List<String> buildStatsLore(Map<Stats, Double> stats) {
        return buildStatsLore(stats, null, this.rarity);
    }


    /*
    *  ------------------ GETTERS & SETTERS ------------------
    *  The f stands for formatted
    */

    private void buildStatsPDC(ItemStack item) {
        buildStatsPDC(item, this.stats);
    }

    private List<String> f_getAbilityLore() {
        return f_getAbilityLore(0);
    }

    private List<String> f_getAbilityLore(int reduceBy) {
        List<String> lore = new ArrayList<>();
        if (this instanceof IHasAbility iHasAbility) {
            iHasAbility.getAbilities().forEach(ability -> {
                if (!ability.nameVisible()) return;
                if (!(ability instanceof ITriggerableAbility triggerable)) return;
                String abilityName = ability.getName();
                final String[] triggerType = {""};
                if (triggerable.getTriggerType() == TriggerType.NONE) {
                    triggerType[0] = "";
                } else {
                    triggerType[0] = triggerable.getTriggerType().name().replace("_", " ");
                }
                String abilityType = ability instanceof FullSetBonus ? "Full Set Bonus" : "Ability";
                String abilityLine = "$6" + abilityType + ": $6" + abilityName + " $e$l" + triggerType[0];
                abilityLine = PlaceholderFormatter.format(abilityLine);
                lore.add(abilityLine);
                lore.addAll(PlaceholderFormatter.format(ability.getDescription()));
                if (ability instanceof IUsageCost cost) {
                    cost.getCost().forEach((_stat, _value) -> {
                        String costLine = "$8";
                        costLine += PlaceholderFormatter.capitalize(_stat.name()).replace("Intelligence", "Mana");
                        costLine += " Cost:";
                        costLine += " $3";
                        double newValue = _value * (1 - reduceBy / 100.0);
                        newValue = Math.max(0, newValue);
                        costLine += PlaceholderFormatter.formatDouble(newValue);
                        costLine = PlaceholderFormatter.format(costLine);
                        lore.add(costLine);
                    });
                }
                if (ability instanceof ICooldown cooldown) {
                    //FIXME: getCooldown shouldn't exactly need a SkyblockPlayer??????
                    int cooldownValue = (int) (cooldown.getCooldown(null) / 1000);
                    String cooldownLine = "$8Cooldown: $a" + cooldownValue + "s";
                    cooldownLine = PlaceholderFormatter.format(cooldownLine);
                    lore.add(cooldownLine);
                }
                lore.add("§7");
            });
        }
        return lore;
    }

    /**
     * This is made to be overridden;
     * In dungeon item, you can display stars and things with the name by overriding.
     *
     * @return "§l§6Diamond Sword"
     */
    public String f_getDisplayName() {
        return f_getDisplayName(false, null);
    }

    private String f_getDisplayName(boolean upgraded, @Nullable Reforge reforge) {
        Rarity rarity = this.rarity; // Mutable copy
        if (upgraded) rarity = rarity.getUpgraded();
        String finalName = rarity.getColor();
        if (reforge != null) finalName += reforge.getName() + " ";
        finalName += this.displayName;
        return finalName;
    }

    private List<String> f_getStaticLore() {
        return this.staticLore == null ? new ArrayList<>() : PlaceholderFormatter.format(this.staticLore);
    }

    public List<String> f_getRarityLine(boolean upgraded) {
        List<String> lore = new ArrayList<>();

        Rarity rarity = this.rarity; // Mutable copy

        if (upgraded) rarity = rarity.getUpgraded();

        String canBeReforged = "§8This item can be reforged!";
        if (!upgraded && this.itemType != SkyblockItemType.NONE) lore.add(canBeReforged);

        String type = this.itemType == SkyblockItemType.NONE ? "" : String.valueOf(this.itemType);

        String upgradedObfuscationMark = "§l§kU";

        String rarityLine = "";
        if (upgraded) rarityLine += rarity.getColor() + upgradedObfuscationMark;
        rarityLine += rarity.getColor() + "§l" + (upgraded ? " " : "") + rarity + " " + type;
        if (upgraded) rarityLine += " " + upgradedObfuscationMark;

        lore.add(rarityLine);
        return lore;
    }

    private List<String> f_getRarityLine() {
        return f_getRarityLine(false);
    }
}
