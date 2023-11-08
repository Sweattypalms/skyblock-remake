package com.sweattypalms.skyblock.core.enchants.impl;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.ITriggerableEnchant;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import org.bukkit.event.Event;

import java.util.List;

public class Sharpness extends Enchantment implements ITriggerableEnchant {
    static int[] levels = {
            5,
            10,
            15,
            20,
            30,
            45,
            65
    };

    public Sharpness() {
        super("sharpness", 7);
    }

    @Override
    public String getName() {
        return "Sharpness";
    }

    @Override
    public List<String> getDescription(int level) {
        String main = "Increases melee damage dealt by";


        String levelStr = String.valueOf(getMultiplier(level));

        String concat = "$a" + levelStr + "%$7.";

        return List.of(
                main,
                concat
        );
    }

    @Override
    public List<SkyblockItemType> getApplicableItems() {
        return List.of(SkyblockItemType.SWORD);
    }

    @Override
    public boolean should(Event event) {
        return event instanceof SkyblockPlayerDamageEntityEvent ev && ev.isPreCalc();
    }

    @Override
    public void execute(int level, Event _e) {
        if (!(_e instanceof SkyblockPlayerDamageEntityEvent event)) return;

        int multiplierPercent = getMultiplier(level);

        event.addAdditiveMultiplierPercent(multiplierPercent);
    }

    private static int getMultiplier(int level){
        int multiplierPercent;

        level = Math.max(1, level);

        if (level <= levels.length) {
            multiplierPercent = levels[level - 1];
        }else {
            multiplierPercent = level * 10;
        }

        return multiplierPercent;
    }
}
