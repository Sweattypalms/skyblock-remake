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

public class LightningHelmet extends SkyblockItem implements IHasAbility, IDyedArmor {
    public static final String ID = "lightning_helmet";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.HEALTH, 20d
    ));
    public LightningHelmet() {
        super(
                ID,
                "Lightning Armor Helmet",
                Material.LEATHER_HELMET,
                null,
                stats,
                Rarity.SPECIAL,
                SkyblockItemType.HELMET
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
