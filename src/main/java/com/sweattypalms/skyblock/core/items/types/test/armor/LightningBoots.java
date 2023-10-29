package com.sweattypalms.skyblock.core.items.types.test.armor;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IDyedArmor;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightningBoots extends SkyblockItem implements IHasAbility, IDyedArmor {
    public static final String ID = "lightning_boots";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.HEALTH, 15d
    ));

    public LightningBoots() {
        super(
                ID,
                "Lightning Armor Boots",
                Material.LEATHER_BOOTS,
                null,
                stats,
                Rarity.SPECIAL,
                SkyblockItemType.BOOTS
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(AbilityManager.LIGHTNING_ARMOR_ABILITY);
    }

    @Override
    public String getHexColor() {
        return "FFFF00";
    }
}