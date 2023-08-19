package com.sweattypalms.skyblock.core.items.types.end.items;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.player.Stats;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public class AspectOfTheEnd extends SkyblockItem implements IHasAbility {
    public static final String ID = "aspect_of_the_end";
    private static final Map<Stats, Double> stats = Map.of(
            Stats.DAMAGE, 100d,
            Stats.STRENGTH, 100d
    );
    public AspectOfTheEnd() {
        super(
                "aspect_of_the_end",
                "Aspect of the End",
                Material.DIAMOND_SWORD,
                null,
                stats,
                Rarity.RARE,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                AbilityManager.ASPECT_OF_THE_END_TELEPORT_ABILITY
        );
    }
}
