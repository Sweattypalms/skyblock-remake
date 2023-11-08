package com.sweattypalms.skyblock.core.items.types.end.items;

import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.helpers.MathHelper;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IHasAbilityDamage;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AspectOfTheDragons extends SkyblockItem implements IHasAbility {
    public static final String ID = "aspect_of_the_dragons";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.DAMAGE, 225d,
            Stats.STRENGTH, 100d
    ));

    public AspectOfTheDragons() {
        super(
                ID,
                "Aspect of the Dragons",
                Material.DIAMOND_SWORD,
                null,
                stats,
                Rarity.LEGENDARY,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new DragonRage()
        );
    }

    public static class DragonRage implements ITriggerableAbility, IUsageCost, IHasAbilityDamage {

        @Override
        public String getName() {
            return "Dragon Rage";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7All Monsters in front of you",
                    "$7take $a12,000 $7damage. Hit",
                    "$7monsters take large knockback."
            );
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;

            SkyblockPlayer skyblockPlayer = skyblockInteractEvent.getSkyblockPlayer();
            Player player = skyblockPlayer.getPlayer();
            MathHelper.spiralParticles(player, 0.1, 1.5, Particle.FLAME);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 10, 1);

            double maxDistance = 4.0;
            double maxKnockback = 4.0;
            double baseKnockback = 1;
            Location playerLoc = player.getLocation();
            Vector viewDirection = playerLoc.getDirection();

            for (LivingEntity entity : player.getNearbyEntities(maxDistance, maxDistance, maxDistance).stream().filter(entity -> entity instanceof LivingEntity).map(entity -> (LivingEntity) entity).toList()) {
                EntityLiving entityLiving = ((CraftLivingEntity) entity).getHandle();

                if (!(entityLiving instanceof ISkyblockMob skyblockMob)) continue;

                Location mobLoc = entity.getLocation();

                Vector toEntity = mobLoc.toVector().subtract(playerLoc.toVector());
                double angle = Math.toDegrees(viewDirection.angle(toEntity));

                if (angle > 60) continue;


                if (!(boolean) skyblockMob.getSkyblockMob().getAttribute(MobAttributes.KNOCKBACK_RESISTANT)) {
                    // Check if the entity is within 30 degrees of the player's view direction
                    double distance = playerLoc.distance(mobLoc);
                    double knockbackFactor = baseKnockback + maxKnockback * Math.pow((maxDistance - distance) / maxDistance, 2);

                    Vector knockback = toEntity.normalize().multiply(knockbackFactor);
                    knockback.setY(knockbackFactor * 0.2);  // Make the vertical component also dependent on distance

                    entity.setVelocity(knockback);
                }

//                damage
                SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent = new SkyblockPlayerDamageEntityEvent(entity, player, SkyblockPlayerDamageEntityEvent.DamageType.ABILITY);
                skyblockPlayerDamageEntityEvent.setAbilityItem(skyblockInteractEvent.getSkyblockPlayer().getInventoryManager().getItemInHand());
                skyblockPlayerDamageEntityEvent.setAbility(this);

                Bukkit.getPluginManager().callEvent(skyblockPlayerDamageEntityEvent);

//                skyblockPlayer.getPlayer().sendMessage(
//                        ChatColor.RED + "You dealt " + ChatColor.GOLD + skyblockPlayerDamageEntityEvent.getDamage() + ChatColor.RED + " damage to " + ChatColor.GOLD + skyblockPlayerDamageEntityEvent.getEntity().getName()
//                );
            }
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockInteractEvent skyblockInteractEvent && skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK;
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of(
                    Stats.INTELLIGENCE, 100d
            );
        }

        @Override
        public double getBaseAbilityDamage() {
            return 12_000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.1;
        }
    }

}
