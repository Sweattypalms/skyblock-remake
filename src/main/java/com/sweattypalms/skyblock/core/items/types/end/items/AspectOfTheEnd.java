package com.sweattypalms.skyblock.core.items.types.end.items;

import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.bonus.Bonus;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;
import org.bukkit.event.Event;

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
                new AspectOfTheEndTeleportAbility()
        );
    }
    public static class AspectOfTheEndTeleportAbility extends AbilityManager.TELEPORT_ABILITY {
        public AspectOfTheEndTeleportAbility() {
            super(8);
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of(
                    Stats.INTELLIGENCE, 50.0
            );
        }

        @Override
        public String getName() {
            return "Instant Transmission";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Teleport $a8 $7blocks ahead of",
                    "$7you and gain $a+50 " + Stats.SPEED.getSymbol() + "Speed",
                    "$7for $a3 seconds$7."
            );
        }

        @Override
        public void apply(Event event) {
            super.apply(event);
            SkyblockPlayer skyblockPlayer = ((SkyblockInteractEvent) event).getSkyblockPlayer();
            Bonus speedBonus = new Bonus(Stats.SPEED, 50, 3000);
            skyblockPlayer.getBonusManager().setBonus("aote.speed", speedBonus);
        }
    }

}
