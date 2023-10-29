package com.sweattypalms.skyblock.core.items.types.none.armor.lapis;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LapisHelmet extends SkyblockItem implements IHasAbility, IHeadHelmet {
    public static final String ID = "lapis_helmet";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.DEFENSE, 25d
    ));
    public LapisHelmet() {
        super(
                ID,
                "Lapis Helmet",
                Material.SEA_LANTERN,
                List.of(
                        "$7Each piece of this armor grants",
                        "$a+50% $7bonus experience when",
                        "$7mining ores."
                ),
                stats,
                Rarity.UNCOMMON,
                SkyblockItemType.HELMET
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(AbilityManager.LAPIS_ARMOR_ABILITY);
    }

    @Override
    public String getTexture() {
        return null;
    }
}
