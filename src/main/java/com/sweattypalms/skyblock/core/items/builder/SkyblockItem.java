package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.PDCHelper;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.stats.Stats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    private final List<Ability> abilities;


    /**
     *
     * @param id "diamond_sword"
     * @param displayName "Diamond Sword"
     * @param material Material.DIAMOND_SWORD
     * @param staticLore List.of(" $7This is a diamond sword"), Use $ as a placeholder
     * @param stats Map.of(Stats.DAMAGE, 35d)
     * @param baseRarity Rarity.RARE
     * @param itemType SkyblockItemType.SWORD
     */
    public SkyblockItem(String id, String displayName, Material material, @Nullable List<String> staticLore, Map<Stats, Double> stats, Rarity baseRarity, SkyblockItemType itemType, @Nullable List<Ability> abilities) {
        this.id = id;
        System.out.println("id: " + id);
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
        this.abilities = abilities;
    }


    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(this.material);
        ItemMeta meta = item.getItemMeta();


        // ------- DISPLAY NAME -------
        meta.setDisplayName(rarity.getColor() + this.displayName);
        // ------- DISPLAY NAME -------

        // ------- LORE -------
        StringBuilder singleLineLore = new StringBuilder();
        this.staticLore.forEach(line -> {
            singleLineLore.append(line.replace("$", "§"));
            singleLineLore.append("\n");
        });

        List<String> lore = buildLore();
        meta.setLore(lore);
        // ------- LORE -------

        item.setItemMeta(meta);

        // ------- STATS -------
        buildStatsPDC(item);
        // ------- STATS -------

        // ------- PERSISTENT DATA CONTAINER -------
        PDCHelper.setString(item, "id", this.id);
        PDCHelper.setString(item, "lore", singleLineLore.toString());
        PDCHelper.setString(item, "rarity", this.rarity.name());
        // ------- PERSISTENT DATA CONTAINER -------

        return item;
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

        if(this.staticLore != null) {
            this.staticLore.forEach(line -> {
                lore.add(line.replace("$", "§"));
            });
            lore.add("§7");
        }


        // TODO: Add abilities

        String canBeReforged = "§8This item can be reforged!";
        lore.add(canBeReforged);
        String type = this.itemType == SkyblockItemType.NONE ? "" : String.valueOf(this.itemType);
        String rarityLine=  rarity.getColor() + "§l"  + rarity + " " + type;
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

    public List<Ability> getAbilities(){
        return this.abilities;
    }

    public SkyblockItem addAbility(Ability ability){
        this.abilities.add(ability);
        System.out.println("Added ability  to " + this.id);
        return this;
    }

}
