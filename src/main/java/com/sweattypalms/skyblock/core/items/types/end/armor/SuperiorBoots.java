package com.sweattypalms.skyblock.core.items.types.end.armor;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IDyedArmor;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public class SuperiorBoots extends SkyblockItem implements IHasAbility, IDyedArmor {
    public static final String ID = "superior_dragon_boots";
    private static final Map<Stats, Double> stats = Map.of(
            Stats.STRENGTH, 10d,
            Stats.CRIT_CHANCE, 2d,
            Stats.CRIT_DAMAGE, 10d,
            Stats.HEALTH, 80d,
            Stats.DEFENSE, 110d,
            Stats.SPEED, 3d,
            Stats.INTELLIGENCE, 25d
    );
    public SuperiorBoots() {
        super(
                ID,
                "Superior Dragon Boots",
                Material.LEATHER_BOOTS,
                null,
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.BOOTS
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new SuperiorChestplate.SuperiorBlood(),
                new SuperiorChestplate.SuperiorBloodAOTD()
        );
    }

    @Override
    public String getHexColor() {
        return "f25d18";
    }
}
