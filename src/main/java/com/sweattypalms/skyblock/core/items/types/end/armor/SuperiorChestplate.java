package com.sweattypalms.skyblock.core.items.types.end.armor;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.PassiveAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IDyedArmor;
import com.sweattypalms.skyblock.core.items.types.end.items.AspectOfTheDragons;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import com.sweattypalms.skyblock.core.player.sub.stats.StatsManager;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class SuperiorChestplate extends SkyblockItem implements IHasAbility, IDyedArmor {
    public static final String ID = "superior_dragon_chestplate";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.HEALTH, 150d,
            Stats.DEFENSE, 190d,
            Stats.SPEED, 3d,
            Stats.STRENGTH, 10d,
            Stats.INTELLIGENCE, 25d,
            Stats.CRIT_CHANCE, 2d,
            Stats.CRIT_DAMAGE, 10d
    ));

    public SuperiorChestplate() {
        super(
                ID,
                "Superior Dragon Chestplate",
                Material.LEATHER_CHESTPLATE,
                null,
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.CHESTPLATE
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new SuperiorBlood(),
                new SuperiorBloodAOTD()
        );
    }

    @Override
    public String getHexColor() {
        return "f2df11";
    }

    public static class SuperiorBlood implements FullSetBonus, PassiveAbility {

        @Override
        public String getName() {
            return "Superior Blood";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Most of your stats are increased",
                    "$7by $a5%$7 and $6Aspect of the",
                    "$6Dragons $7ability deals $a50%",
                    "$7more damage."
            );
        }

        @Override
        public void onTick(SkyblockPlayer player) {
            StatsManager statsManager = player.getStatsManager();
            Map<Stats, Double> maxStats = statsManager.getMaxStats();
            Arrays.stream(Stats.values()).toList().forEach(stat -> {
                double curr = maxStats.get(stat);
                maxStats.put(stat, curr * 1.05);
            });
        }
    }

    public static class SuperiorBloodAOTD implements FullSetBonus {

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public String getName() {
            return "aspect of the dragons bonus +50% damage";
        }

        @Override
        public List<String> getDescription() {
            return List.of();
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            ItemStack activator = skyblockPlayerDamageEntityEvent.getAbilityItem();
            if (activator == null) return;
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(activator);
            if (!Objects.equals(skyblockItem.getId(), AspectOfTheDragons.ID)) return;
            skyblockPlayerDamageEntityEvent.addMultiplicativeMultiplierPercent(50);
        }
    }
}