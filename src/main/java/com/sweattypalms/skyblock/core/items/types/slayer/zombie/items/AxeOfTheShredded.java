package com.sweattypalms.skyblock.core.items.types.slayer.zombie.items;

import com.sweattypalms.skyblock.core.events.def.SkyblockAbilityUseEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import net.minecraft.util.Tuple;
import org.bukkit.Material;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AxeOfTheShredded extends SkyblockItem implements IHasAbility {

    public static final String ID = "axe_of_the_shredded";

    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.DAMAGE, 140d,
            Stats.STRENGTH, 115d
    ));


    public AxeOfTheShredded() {
        super(
                ID,
                "Axe of the Shredded",
                Material.DIAMOND_AXE,
                List.of(
                        "$7Heal $c50" + Stats.HEALTH.getSymbol() + "$7per hit.",
                        "$7Deal $a+250% $7damage to Zombies.",
                        "$7Receive $a+25% $7less damage",
                        "$7from Zombies when held."
                ),
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return null;
    }

    public class ThrowAbility implements ITriggerableAbility, IUsageCost {


        private static final HashMap<UUID, String> combo = new HashMap<>();
        private static final int OVER_COOLDOWN = 2000;

        @Override
        public String getName() {
            return "Throw";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Throw your axe damaging all",
                    "$7enemies in its path dealing",
                    "$c10% $7melee damage.",
                    "$7Consecutive throws stack $c2x",
                    "$7damage but cost $92x mana up",
                    "$7to $916x"
            );
        }

        @Override
        public void apply(Event _e) {
            if (!(_e instanceof SkyblockInteractEvent event)) return;

            SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();

            if(!combo.containsKey(skyblockPlayer.getPlayer().getUniqueId())) {
                combo.put(skyblockPlayer.getPlayer().getUniqueId(), "0:0");
            }



        }

        /**
         * Format: <stacks>:<last used>
         * @param toFormat the string to format
         * @return the formatted string; Tuple<stacks, last used>
         */
        private static Tuple<Integer, Long> fmt(String toFormat) {
            String[] split = toFormat.split(":");
            return new Tuple<>(Integer.parseInt(split[0]), Long.parseLong(split[1]));
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockInteractEvent;
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of(
                    Stats.INTELLIGENCE, 20d
            );
        }
    }

}
