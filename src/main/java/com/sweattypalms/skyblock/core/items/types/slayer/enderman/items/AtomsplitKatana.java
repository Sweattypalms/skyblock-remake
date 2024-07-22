package com.sweattypalms.skyblock.core.items.types.slayer.enderman.items;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.DamageAbility;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

import java.util.List;
import java.util.Map;

public class AtomsplitKatana extends SkyblockItem implements IHasAbility {
    public static final String ID = "atomsplit_katana";

    public AtomsplitKatana() {
        super(
                ID,
                "Atomsplit Katana",
                Material.DIAMOND_SWORD,
                List.of(
                        "$7Deal $a+300% $7damage to Endermen.",
                        "$7Receive $a12% $7less damage from",
                        "$7Endermen when held."
                ),
                Map.of(
                        Stats.DAMAGE, 245d,
                        Stats.STRENGTH, 100d,
                        Stats.CRIT_DAMAGE, 30d,
                        Stats.INTELLIGENCE, 300d
                ),
                Rarity.LEGENDARY,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(new AtomsplitKatanaBaseDamageAbility());
    }

    static class AtomsplitKatanaBaseDamageAbility implements DamageAbility {
        @Override
        public boolean preCalc() {
            return true;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return false;

            return skyblockPlayerDamageEntityEvent.getEntity().getType() == EntityType.ENDERMAN;
        }


        @Override
        public String getName() {
            return "Atomsplit Katana Base Damage (+300% to Endermen)";
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public List<String> getDescription() {
            return List.of();
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            // Multiplicative, Makes the weapon deal 3x damage
            skyblockPlayerDamageEntityEvent.addMultiplicativeMultiplierPercent(200);
        }
    }
}
