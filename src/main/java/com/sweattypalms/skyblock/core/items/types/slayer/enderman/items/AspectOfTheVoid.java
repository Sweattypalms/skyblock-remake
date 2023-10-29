package com.sweattypalms.skyblock.core.items.types.slayer.enderman.items;

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

public class AspectOfTheVoid  extends  SkyblockItem implements IHasAbility {
    public static final String ID = "Aspect_of_the_void";
    private static final Map<Stats, Double> stats = Map.of(
            Stats.DAMAGE, 120d,
            Stats.STRENGTH, 120d
    );
    public AspectOfTheVoid() {
        super(
                "aspect_of_the_void",
                "Aspect of the Void",
                Material.DIAMOND_SHOVEL,
                null,
                stats,
                Rarity.EPIC,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new AspectOfTheVoid.AspectOfTheVoidTeleportAbility()
        );
    }
    public static class AspectOfTheVoidTeleportAbility extends AbilityManager.TELEPORT_ABILITY {
        public AspectOfTheVoidTeleportAbility() {
            super(8);
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of(
                    Stats.INTELLIGENCE, 45.0
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
            skyblockPlayer.getBonusManager().setBonus("aotv.speed", speedBonus);
        }
    }

}


