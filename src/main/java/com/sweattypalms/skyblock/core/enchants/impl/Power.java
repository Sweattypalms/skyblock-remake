package com.sweattypalms.skyblock.core.enchants.impl;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.ITriggerableEnchant;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import org.bukkit.event.Event;

import java.util.List;

public class Power extends Enchantment implements ITriggerableEnchant {
    public Power() {
        super("power", 7);
    }

    @Override
    public String getName() {
        return "Power";
    }

    @Override
    public List<String> getDescription(int level) {
        return List.of(
                "Increases bow damage by $a" + getMultiplier(level) + "%$7."
        );
    }

    @Override
    public List<SkyblockItemType> getApplicableItems() {
        return List.of(SkyblockItemType.BOW);
    }

    @Override
    public boolean should(Event event) {
        return false;
    }

    @Override
    public void execute(int level, Event event) {

    }

    private int getMultiplier(int level) {
        int[] levels = {
                8,
                16,
                24,
                32,
                40,
                50,
                65
        };

        level = Math.max(1, level);

        if (level > levels.length) {
            return level * 10;
        }else {
            return levels[level - 1];
        }
    }
}
