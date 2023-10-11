package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerable;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.items.builder.armor.IDyedArmor;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.items.builder.item.IShortBow;
import com.sweattypalms.skyblock.core.items.builder.reforges.IAdvancedReforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.Reforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import lombok.Getter;
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

    @SuppressWarnings("ReplaceAll")
    public static ItemStack updateItemStack(ItemStack itemStack) {
        String id = PDCHelper.getString(itemStack, "id");
        SkyblockItem skyblockItem = ItemManager.ITEMS_LIST.get(id);

        Map<Stats, Double> stats = getStatsFromItemStack(itemStack);
        String reforgeStr = PDCHelper.getOrDefault(itemStack, "reforge", "None");
        Reforge reforge = ReforgeManager.getReforge(reforgeStr);
        String rarityStr = PDCHelper.getString(itemStack, "rarity");
        Rarity rarity = Rarity.valueOf(rarityStr);
        boolean upgraded = skyblockItem.getRarity().getUpgraded().name().equals(rarityStr);

        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(skyblockItem.__getDisplayName(upgraded, reforge));

        List<String> lore = new ArrayList<>(
                buildStatsLore(stats, reforge, rarity)
        );

        if(!lore.isEmpty()) lore.add("§7");

        lore.addAll(skyblockItem.__getStaticLore());

        if (skyblockItem.getStaticLore() != null) lore.add("§7");

        if(skyblockItem instanceof IShortBow shortBow){
            lore.add(rarity.getColor() + "Shortbow: Instantly shoots!");
            lore.addAll(PlaceholderFormatter.format(shortBow.getShortBowDescription()));
            lore.add("§7");
        }

        lore.addAll(skyblockItem.__getAbilityLore());

        lore.addAll(ReforgeManager.getReforgeLore(reforge));

        if (reforge instanceof IAdvancedReforge) lore.add("§7"); // Will automatically check if the reforge isn't null

        lore.addAll(skyblockItem.__getRarityLine(upgraded));

        meta.setLore(lore);

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static Map<Stats, Double> getStatsFromItemStack(ItemStack itemStack) {
        Map<Stats, Double> stats = new HashMap<>();
        Arrays.stream(Stats.values()).toList().forEach(stat -> stats.put(stat, PDCHelper.getOrDefault(itemStack, "stat." + stat.name().toLowerCase(), 0d)));
        return stats;
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
        meta.setDisplayName(__getDisplayName());
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

        lore.addAll(__getStaticLore());

        if (this.staticLore != null) lore.add("§7");

        if (this instanceof IShortBow shortBow){
            lore.add("§6Shortbow: Instantly shoots!");
            lore.addAll(PlaceholderFormatter.format(shortBow.getShortBowDescription()));
            lore.add("§7");
        }

        lore.addAll(__getAbilityLore());

        lore.addAll(__getRarityLine());

        return lore;
    }

    private List<String> buildStatsLore(Map<Stats, Double> stats) {
        return buildStatsLore(stats, null, this.rarity);
    }


    /* ------------------ GETTERS & SETTERS ------------------ */

    private void buildStatsPDC(ItemStack item) {
        buildStatsPDC(item, this.stats);
    }

    private List<String> __getAbilityLore() {
        List<String> lore = new ArrayList<>();
        if (this instanceof IHasAbility iHasAbility) {
            iHasAbility.getAbilities().forEach(ability -> {
                if (!ability.nameVisible()) return;
                if (!(ability instanceof ITriggerable triggerable)) return;
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
                        costLine += PlaceholderFormatter.formatDouble(_value);
                        costLine = PlaceholderFormatter.format(costLine);
                        lore.add(costLine);
                    });
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
    public String __getDisplayName() {
        return __getDisplayName(false , null);
    }

    private String __getDisplayName(boolean upgraded, @Nullable Reforge reforge) {
        Rarity rarity = this.rarity; // Mutable copy
        if (upgraded) rarity = rarity.getUpgraded();
        String finalName = rarity.getColor();
        if(reforge != null) finalName += reforge.getName() + " ";
        finalName += this.displayName;
        return finalName;
    }

    private List<String> __getStaticLore() {
        return this.staticLore == null ? new ArrayList<>() : PlaceholderFormatter.format(this.staticLore);
    }

    public List<String> __getRarityLine(boolean upgraded) {
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

    private List<String> __getRarityLine() {
        return __getRarityLine(false);
    }
}
