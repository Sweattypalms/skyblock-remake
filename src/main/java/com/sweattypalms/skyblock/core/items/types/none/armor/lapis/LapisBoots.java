package com.sweattypalms.skyblock.core.items.types.none.armor.lapis;

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

public class LapisBoots extends SkyblockItem implements IHasAbility, IDyedArmor {
    public static final String ID = "lapis_boots";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.HEALTH, 20d
    ));
    public LapisBoots() {
        super(
                ID,
                "Lapis Boots",
                Material.LEATHER_BOOTS,
                List.of(
                        "$7Each piece of this armor grants",
                        "$a+50% $7bonus experience when",
                        "$7mining ores."
                ),
                stats,
                Rarity.UNCOMMON,
                SkyblockItemType.BOOTS
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(AbilityManager.LAPIS_ARMOR_ABILITY);
    }

    @Override
    public String getHexColor() {
        return "0000ff";
    }
}

