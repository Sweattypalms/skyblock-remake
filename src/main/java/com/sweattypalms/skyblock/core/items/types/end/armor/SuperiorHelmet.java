package com.sweattypalms.skyblock.core.items.types.end.armor;

import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public class SuperiorHelmet extends SkyblockItem implements IHasAbility, IHeadHelmet {
    public static final String ID = "superior_dragon_helmet";
    private static final Map<Stats, Double> stats = Map.of(
            Stats.STRENGTH, 10d,
            Stats.CRIT_CHANCE, 2d,
            Stats.CRIT_DAMAGE, 10d,
            Stats.HEALTH, 90d,
            Stats.DEFENSE, 130d,
            Stats.SPEED, 3d,
            Stats.INTELLIGENCE, 25d
    );
    public SuperiorHelmet() {
        super(
                ID,
                "Superior Dragon Helmet",
                Material.PLAYER_HEAD,
                null,
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.HELMET
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
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=";
        // from minecraft-heads.com
    }
}
