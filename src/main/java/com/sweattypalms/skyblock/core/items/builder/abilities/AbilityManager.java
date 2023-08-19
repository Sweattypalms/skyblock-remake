package com.sweattypalms.skyblock.core.items.builder.abilities;

import com.sweattypalms.skyblock.core.events.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.*;
import com.sweattypalms.skyblock.core.player.sub.Bonus;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbilityManager {
    public static final Ability LAPIS_ARMOR_ABILITY = new LAPIS_ARMOR_ABILITY();
    public static Ability UNDEAD_SWORD_ABILITY = new DamageAbility() {
        @Override
        public String getName() {
            return "Undead Sword";
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>();
        }

        @Override
        public boolean preCalc() {
            return true;
        }
        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return false;
            List<EntityType> whitelistedEntities = List.of(
                    EntityType.SKELETON,
                    EntityType.ZOMBIE,
                    EntityType.ZOMBIE_VILLAGER,
                    EntityType.WITHER,
                    EntityType.PIGLIN
            );
            boolean undead = whitelistedEntities.contains(skyblockPlayerDamageEntityEvent.getEntity().getType());
            return undead;
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            /* Multiplicitive, Makes the weapon deal 2x damage */
            skyblockPlayerDamageEntityEvent.addMultiplicativeMultiplierPercent(100);
        }
    };
    public static Ability LIGHTNING_ARMOR_ABILITY = new FullSetBonus() {
        @Override
        public String getName() {
            return "Lightning";
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>(List.of(
                    "$7Striking an enemy has a $a25% $7chance",
                    "$7when full set is equipped."
            ));
        }

        @Override
        public void apply(Event event) {
            if(!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return;
            if(skyblockPlayerDamageEntityEvent.getSkyblockPlayer().getRandom().nextInt(100) > 25) return;
            skyblockPlayerDamageEntityEvent.getEntity().getWorld().strikeLightningEffect(skyblockPlayerDamageEntityEvent.getEntity().getLocation());
        }
    };
    public static Ability ASPECT_OF_THE_END_TELEPORT_ABILITY = new TELEPORT_ABILITY(8) {
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
    };
    public static class LAPIS_ARMOR_ABILITY implements FullSetBonus, PassiveAbility{

        @Override
        public String getName() {
            return "Health";
        }

        @Override
        public List<String> getDescription() {
            return new ArrayList<>(List.of(
                    "$7Increases the wearer's maximum",
                    Stats.HEALTH.getSymbol() + "Health $7by $a60"
            ));
        }

        @Override
        public void onTick(SkyblockPlayer player) {
            double currentMaxHealth = player.getStatsManager().getMaxStats().get(Stats.HEALTH);
            player.getStatsManager().setMaxStat(Stats.HEALTH, currentMaxHealth + 60);
        }
    }
    public static abstract class TELEPORT_ABILITY implements ITriggerable, IUsageCost {

        private final int range;

        public TELEPORT_ABILITY(int range) {
            this.range = range;
        }

        @Override
        public void apply(Event event) {
            if(!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            SkyblockPlayer skyblockPlayer = skyblockInteractEvent.getSkyblockPlayer();
            Player player = skyblockPlayer.getPlayer();

            List<Material> whitelistedMaterials = List.of(
                    Material.AIR,
                    Material.WATER,
                    Material.LAVA
            );

            Location start = player.getEyeLocation();
            Vector direction = start.getDirection();


            for (int i = 0; i < range; i++) {
                start.add(direction);

                Block block = start.getBlock();
                if(!whitelistedMaterials.contains(block.getType())){
                    start.subtract(direction);
                    player.sendMessage("There are blocks in the way!");
                    break;
                }
            }

            player.teleport(start);
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            if(!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return false;
            return skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK;
        }
    }
}

