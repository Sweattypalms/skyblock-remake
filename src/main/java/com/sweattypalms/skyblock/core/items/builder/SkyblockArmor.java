package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.stats.Stats;
import org.bukkit.Material;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public abstract class SkyblockArmor extends SkyblockItem implements Wearable {
    public SkyblockArmor(String id, String displayName, Material material, List<String> staticLore, Map<Stats, Double> stats, Rarity baseRarity, SkyblockItemType itemType, @Nullable List<Ability> abilities) {
        super(id, displayName, material, staticLore, stats, baseRarity, itemType, abilities);
    }

    public abstract ArmorSet getArmorSet();
}
