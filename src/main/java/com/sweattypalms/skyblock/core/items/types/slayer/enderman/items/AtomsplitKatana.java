package com.sweattypalms.skyblock.core.items.types.slayer.enderman.items;

import com.sweattypalms.skyblock.api.sequence.Sequence;
import com.sweattypalms.skyblock.api.sequence.SequenceAction;
import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockMobDamagePlayerEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.*;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.InventoryManager;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.sweattypalms.skyblock.SkyBlock.getInstance;

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
        return List.of(
                new BaseDamageAbility(),
                new DamageReductionAbility(),
                new SoulCryTrigger(),
                new SoulCryDamage()
        );
    }

    // should be -1 if the ability wasn't used
    static HashMap<UUID, Long> soulCryLastUsed = new HashMap<>();

    static class BaseDamageAbility implements DamageAbility {
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

    static class DamageReductionAbility implements RecvDamageAbility {

        @Override
        public boolean preCalc() {
            return true;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockMobDamagePlayerEvent skyblockMobDamagePlayerEvent)) return false;

            return skyblockMobDamagePlayerEvent.getSkyblockMob().getEntityInstance().getType() == EntityType.ENDERMAN;
        }

        @Override
        public String getName() {
            return "";
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
            if (!(event instanceof SkyblockMobDamagePlayerEvent skyblockMobDamagePlayerEvent)) return;
            // Additive, Makes the player take 12% less damage from Endermen
            skyblockMobDamagePlayerEvent.addAdditiveMultiplierPercent(-12);
        }
    }

    static class SoulCryTrigger implements ITriggerableAbility, IUsageCost, ICooldown {
        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return false;

            boolean isOnCooldown = isOnCooldown(skyblockInteractEvent.getSkyblockPlayer());
            boolean isRightClick = skyblockInteractEvent.getInteractType() == this.getTriggerType();

            return !isOnCooldown && isRightClick;
        }

        @Override
        public String getName() {
            return "Soulcry";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Gain $c+400 " + Stats.FEROCITY.getSymbol() + " Ferocity $7against",
                    "$7Endermen for $a4s"
            );
        }

        @Override
        public long getCooldown(SkyblockPlayer skyblockPlayer) {
            // 2s cooldown
            return 2_000;
        }

        @Override
        public void apply(Event event) {
            ICooldown.super.apply(event);
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            UUID playerUUID = skyblockInteractEvent.getSkyblockPlayer().getPlayer().getUniqueId();
            soulCryLastUsed.put(playerUUID, System.currentTimeMillis());

            InventoryManager inventoryManager = skyblockInteractEvent.getSkyblockPlayer().getInventoryManager();


            // make the sword golden, and turn it back to normal after 4s
            int slot = inventoryManager.getHeldItemSlot();

            ItemStack item = inventoryManager.getItemInHand();
            item.setType(Material.GOLDEN_SWORD);

            inventoryManager.updateItemInSlot(slot, item);
            inventoryManager.refreshInventory();

            // play enderman scream sound like the sound going upwards
            Player player = skyblockInteractEvent.getSkyblockPlayer().getPlayer();

            Sequence sequence = new Sequence();

            for (float i = 0.5f; i <= 0.9f; i += 0.1f) {
                final float pitch = i;
                sequence.add(new SequenceAction(() -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1f, pitch);
                    player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5f, pitch + 1.0f);
                }, 0, 1));
            }

            sequence.start();


            Bukkit.getScheduler().runTaskLater(
                    getInstance(),
                    () -> {
                        long lastUsed = soulCryLastUsed.get(playerUUID);
                        long delta = System.currentTimeMillis() - lastUsed;
                        if (delta < this.getCooldown(skyblockInteractEvent.getSkyblockPlayer())) return;
                        item.setType(Material.DIAMOND_SWORD);

                        inventoryManager.updateItemInSlot(slot, item);
                        inventoryManager.refreshInventory();
                        // play a downward sound

                        Sequence endSequence = new Sequence();

                        for (float i = 0.9f; i >= 0.5f; i -= 0.1f) {
                            final float pitch = i;
                            endSequence.add(new SequenceAction(() -> {
                                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1f, pitch);
                                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5f, pitch + 1.0f);
                            }, 0, 1));
                        }

                        endSequence.start();
                    },
                    4 * 20
            );

        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public Map<Stats, Double> getCost() {
            return Map.of(
                    Stats.INTELLIGENCE, 200d
            );
        }
    }

    static class SoulCryDamage implements DamageAbility {

        @Override
        public boolean preCalc() {
            return true;
        }

        @Override
        public boolean trigger(Event event) {
            if (!(event instanceof SkyblockPlayerDamageEntityEvent skyblockPlayerDamageEntityEvent)) return false;

            long lastUsed = soulCryLastUsed.getOrDefault(skyblockPlayerDamageEntityEvent.getSkyblockPlayer().getPlayer().getUniqueId(), -1L);

            if (lastUsed == -1) return false;
            // It's been more than 4s since the last time the ability was used
            if (lastUsed + 4_000 < System.currentTimeMillis()) return false;

            return skyblockPlayerDamageEntityEvent.getEntity().getType() == EntityType.ENDERMAN;
        }

        @Override
        public String getName() {
            return "";
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
            // Adds 400 ferocity
            skyblockPlayerDamageEntityEvent.addStatModifier(Stats.FEROCITY, 400);
        }
    }
}
