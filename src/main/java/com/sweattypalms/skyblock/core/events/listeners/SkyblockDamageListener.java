package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.events.def.SkyblockMobDamagePlayerEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.helpers.DamageCalculator;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.DamageAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyblockDamageListener implements Listener {
    /**
     * This event should only be used for damage calculation.
     * CALLED FASTEST
     *
     * @param event SkyblockPlayerDamageEntityEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onSkyblockPlayerDamageEntityDamageCalculation(SkyblockPlayerDamageEntityEvent event) {
        if (event.isCancelled()) return;
        if (event.getSkyblockMob() == null) return;

        EnchantManager.run(event.getPlayer(), event);

        // Item Abilities (Item in hand)
        SkyblockItem item = event.getSkyblockPlayer().getInventoryManager().getSkyblockItemInHand();
        if (item instanceof IHasAbility iHasAbility) {
            iHasAbility.getAbilities().forEach(ability -> {
                if (!(ability instanceof ITriggerableAbility triggerable)) return;
                boolean isDamageAbility = ability instanceof DamageAbility;
                isDamageAbility = isDamageAbility && ((DamageAbility) ability).preCalc();

                boolean shouldTrigger = triggerable.trigger(event) && isDamageAbility;
                if (shouldTrigger) {
                    ability.apply(event);
                }
            });
        }
        // Full Set Bonus
        FullSetBonus fullSetBonus = event.getSkyblockPlayer().getInventoryManager().getEquippedFullSetBonus();
        if (fullSetBonus != null) {
            // Not checking for trigger because it is already checked in getEquippedFullSetBonus() through isFullSetEquipped()
            fullSetBonus.apply(event);
        }

        double damage;
        if (event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ABILITY) {
            damage = DamageCalculator.calculateAbilityDamage(event);
        } else {
            damage = DamageCalculator.calculateNormalDamage(event);
        }
        event.setDamage(damage);
//        System.out.printf(
//                "Player %s damaged %s for %s damage%n",
//                event.getPlayer().getName(),
//                event.getEntity().getType(),
//                event.getDamage()
//                );
    }


    @EventHandler
    public void onSkyblockPlayerDamageEntity(SkyblockPlayerDamageEntityEvent event) {
        if (event.isCancelled()) return;
        if (event.getSkyblockMob() == null) return;

        SkyblockMob skyblockMob = event.getSkyblockMob();
        double damage = event.getDamage();
//        System.out.printf(
//                "Player %s damaged %s for %s damage%n",
//                event.getPlayer().getName(),
//                event.getEntity().getType(),
//                event.getDamage()
//        );

        event.setPreCalc(false);

        EnchantManager.run(event.getPlayer(), event);

        // Item Abilities
        SkyblockItem item = event.getSkyblockPlayer().getInventoryManager().getSkyblockItemInHand();
        if (item instanceof IHasAbility iHasAbility) {
            iHasAbility.getAbilities().forEach(ability -> {
                if (!(ability instanceof ITriggerableAbility triggerable)) return;
                boolean isDamageAbility = ability instanceof DamageAbility;
                isDamageAbility = isDamageAbility && !((DamageAbility) ability).preCalc();

                boolean shouldTrigger = triggerable.trigger(event) && isDamageAbility;
                if (shouldTrigger) {
                    ability.apply(event);
                }
            });
        }

        skyblockMob.damageEntityWithCause(event);

        if(event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ABILITY &&
        !event.isApplyFerocityOnAbility()) return;

        /* ------- FEROCITY ------- */

        int ferocity = event.getSkyblockPlayer().getStatsManager().getMaxStats().get(Stats.FEROCITY).intValue();

        int guaranteedHits = ferocity / 100;
        boolean extraHit = event.getSkyblockPlayer().getRandom().nextInt(100) < (ferocity % 100);

        Location soundLocation = event.getSkyblockPlayer().getPlayer().getLocation();
        assert soundLocation.getWorld() != null;

        for (int i = 0; i < guaranteedHits + (extraHit ? 1 : 0); i++) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (skyblockMob.getEntityInstance() == null) return;
                    if (skyblockMob.getEntityInstance().isDead() || skyblockMob.getEntityInstance().getHealth() <= 0)
                        return;
                    skyblockMob.damageEntityWithCause(event);

                    /* ------- SOUND ------- */

                    soundLocation.getWorld().playSound(soundLocation, Sound.ITEM_FLINTANDSTEEL_USE, 1.5f, 0);
                    soundLocation.getWorld().playSound(soundLocation, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.2f, 2f);

                    /* ------- SOUND ------- */
                }
            }.runTaskLater(SkyBlock.getInstance(), 5L * i + 5);
        }

        /* ------- FEROCITY ------- */


    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSkyblockMobDamageEntity(SkyblockMobDamagePlayerEvent event) {
        if (event.getSkyblockMob() == null) return;
        /* Cancel event if needed */

        SkyblockMob skyblockMob = event.getSkyblockMob();
        double damage = skyblockMob.getAttribute(MobAttributes.DAMAGE);
        event.setDamage(damage);
    }

    @EventHandler
    public void onSkyblockMobDamageEntityDamageCalculation(SkyblockMobDamagePlayerEvent event) {
        double damage = event.getDamage();
        double defense = event.getSkyblockPlayer().getStatsManager().getMaxStats().get(Stats.DEFENSE);

        damage = DamageCalculator.calculateDamageReduction(defense, damage);

        event.getSkyblockPlayer().damage(damage);
    }
}
