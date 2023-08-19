package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerable;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.items.builder.armor.interfaces.IDyedArmor;
import com.sweattypalms.skyblock.core.items.builder.armor.interfaces.IHeadHelmet;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import javax.annotation.Nullable;
import java.util.*;

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
        this.stats = new HashMap<>(stats);
        Arrays.stream(Stats.values()).toList().forEach(stat -> {
            if (!this.stats.containsKey(stat)) {
                this.stats.put(stat, 0d);
            }
        });
        this.rarity = baseRarity;
        this.itemType = itemType;
    }

    public static SkyblockItem fromItemStack(ItemStack item) {
        String id = PDCHelper.getString(item, "id");
        return ItemManager.ITEMS_LIST.getOrDefault(id, null);
    }

    public ItemStack toItemStack() {
        ItemStack item;
        if (this instanceof IHeadHelmet headHelmet) {
            if (headHelmet.getTexture() != null) {
//            TODO: Add head helmet texture
                item = new ItemStack(Material.PLAYER_HEAD);
            } else {
                item = new ItemStack(this.material);
            }
        } else {
            item = new ItemStack(this.material);
        }

        ItemMeta meta = item.getItemMeta();

        assert meta != null;

        if(this instanceof IDyedArmor dyedArmor){
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

    /**
     * This is made to be overridden;
     * In dungeon item, you can display stars and things with the name by overriding.
     *
     * @return "§l§6Diamond Sword"
     */
    private String __getDisplayName() {
        return this.rarity.getColor() + this.displayName;
    }

    private List<String> buildLore() {
        Map<Stats, Double> stats = this.stats;
        List<String> lore = new ArrayList<>();

        stats.forEach((stat, value) -> {
            if (value == 0) return;

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("§7");
            stringBuilder.append(stat.getName());
            stringBuilder.append(": ");
            stringBuilder.append(stat.getItemBuilderColor());
            stringBuilder.append((value > 0) ? "+" : "-");
            String statValue = String.valueOf(Math.abs(value)).replace(".0", "");
            stringBuilder.append(statValue);
            if (stat.isPercentage()) {
                stringBuilder.append("%");
            }
            stringBuilder.append(" ");

            lore.add(stringBuilder.toString());
        });

        lore.add("§7");

        if (this.staticLore != null) {
            this.staticLore.forEach(line -> {
                lore.add(PlaceholderFormatter.format(line));
            });
            lore.add("§7");
        }

        if (this instanceof IHasAbility iHasAbility) {
            iHasAbility.getAbilities().forEach(ability -> {
                if (!ability.nameVisible()) return;
                if(!(ability instanceof ITriggerable triggerable)) return;
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
                if(ability instanceof IUsageCost cost){
                    cost.getCost().forEach((_stat, _value) -> {
                        String costLine = "$8";
                        costLine += PlaceholderFormatter.capitalize(_stat.name());
                        costLine += " Cost:";
                        costLine += " $3";
                        costLine += _value;
                        costLine = PlaceholderFormatter.format(costLine);
                        lore.add(costLine);
                    });
                }
                lore.add("§7");
            });
        }



        String canBeReforged = "§8This item can be reforged!";
        lore.add(canBeReforged);
        String type = this.itemType == SkyblockItemType.NONE ? "" : String.valueOf(this.itemType);
        String rarityLine = rarity.getColor() + "§l" + rarity + " " + type;
        lore.add(rarityLine);

        return lore;
    }

    private void buildStatsPDC(ItemStack item) {
        this.stats.forEach((stat, value) -> {
            PDCHelper.setDouble(item, stat.name().toLowerCase(), value);
        });
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Material getMaterial() {
        return this.material;
    }

    public List<String> getStaticLore() {
        return this.staticLore;
    }

    public Map<Stats, Double> getStats() {
        return this.stats;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public SkyblockItemType getItemType() {
        return this.itemType;
    }
}
