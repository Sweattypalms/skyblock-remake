package com.sweattypalms.skyblock.core.items.types.test.armor;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.events.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerable;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class LightningWand extends SkyblockItem implements IHasAbility {
    public static String ID = "lightning_wand";


    public LightningWand() {
        super(
                ID,
                "Lightning Wand",
                Material.SNOWBALL,
                List.of(
                        "$7ZEUS'S THUNDER BALLS"
                ),
                new HashMap<>(),
                Rarity.SPECIAL,
                SkyblockItemType.WAND
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return null;
    }

    public class LightningWandAbility implements ITriggerable {

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockInteractEvent skyblockInteractEvent && skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK;
        }

        @Override
        public String getName() {
            return "Thunder Balls";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Shoots a snow ball that ",
                    "$7strikes any enemy on impact"
            );
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            Snowball baller = skyblockInteractEvent.getSkyblockPlayer().getPlayer().launchProjectile(
                    Snowball.class
            );

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (baller.isDead()) {
                        cancel();
                        return;
                    }

                    List<LivingEntity> entities = baller.getNearbyEntities(1, 1, 1).stream().filter(
                                    entity -> entity instanceof LivingEntity && !(entity instanceof Player))
                            .map(entity -> (LivingEntity) entity)
                            .toList();

                    entities.forEach(entity -> {
                        SkyblockMob skyblockMob = SkyblockMob.getSkyblockMob(entity);
                        if (skyblockMob == null) return;

                        SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent = new SkyblockPlayerDamageEntityEvent(
                                entity,
                                ((SkyblockInteractEvent) event).getSkyblockPlayer().getPlayer(),
                                SkyblockPlayerDamageEntityEvent.DamageType.ABILITY
                        );
                        skyblockPlayerDamageEntityEvent.setForcedCrit(false);
                        skyblockPlayerDamageEntityEvent.addMultiplicativeMultiplierPercent(-50);
                    });

                    if(entities.size() > 0) {
                        baller.remove();
                        cancel();
                    }
                }
            }.runTaskTimer(SkyBlock.getInstance(), 0, 1);


        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }
    }
}
