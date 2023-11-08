package com.sweattypalms.skyblock.core.enchants.impl;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.ITriggerableEnchant;
import com.sweattypalms.skyblock.core.events.def.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public class Telekinesis extends Enchantment implements ITriggerableEnchant {
    public Telekinesis() {
        super("telekinesis");
    }
    @Override
    public String getName() {
        return "Telekinesis";
    }

    @Override
    public List<String> getDescription(int level) {
        return List.of(
                "Block and mob drops go directly",
                "into your inventory."
        );
    }

    @Override
    public List<SkyblockItemType> getApplicableItems() {
        return SkyblockItemType.getHandheld();
    }

    @Override
    public boolean should(Event event) {
        return event instanceof BlockBreakEvent || event instanceof SkyblockDeathEvent;
    }

    @Override
    public void execute(int level, Event event) {
        if (event instanceof BlockBreakEvent blockBreakEvent) {
            blockBreakEvent.setDropItems(false);
            blockBreakEvent.setExpToDrop(0);
        } else if (event instanceof SkyblockDeathEvent deathEvent) {
            // ok
        }
    }
}