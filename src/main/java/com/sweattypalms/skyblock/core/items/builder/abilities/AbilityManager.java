package com.sweattypalms.skyblock.core.items.builder.abilities;

import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

public class AbilityManager {
    public static Ability UNDEAD_SWORD_ABILITY = new Ability() {
        @Override
        public TriggerType getTriggerType() {
            return TriggerType.ATTACK;
        }

        @Override
        public boolean trigger(Event event) {
            System.out.println("Triggered");
            if(!(event instanceof EntityDamageByEntityEvent playerAttackEntityEvent)) return false;
            List<EntityType> whitelistedEntities = List.of(
                    EntityType.SKELETON,
                    EntityType.ZOMBIE,
                    EntityType.WITHER,
                    EntityType.PIGLIN
            );
            boolean undead = whitelistedEntities.contains(playerAttackEntityEvent.getEntityType());
            System.out.println("Undead: " + undead);
            return undead;
        }

        @Override
        public void apply(SkyblockPlayer player, Event event) {
            EntityDamageByEntityEvent playerAttackEntityEvent = (EntityDamageByEntityEvent) event;
            playerAttackEntityEvent.setDamage(playerAttackEntityEvent.getDamage() * 2);
        }
    };
}
