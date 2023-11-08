package com.sweattypalms.skyblock.core.enchants.impl;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.ITriggerableEnchant;
import com.sweattypalms.skyblock.core.enchants.builder.UltimateEnchantment;
import com.sweattypalms.skyblock.core.events.def.SkyblockAbilityUseEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import org.bukkit.event.Event;

import java.util.List;

public class UltimateWise extends Enchantment implements UltimateEnchantment, ITriggerableEnchant {
    public UltimateWise() {
        super("ultimate_wise");
    }



    @Override
    public String getName() {
        return "Ultimate Wise";
    }

    @Override
    public List<String> getDescription(int level) {
        return List.of(
                "Reduces the ability mana cost of",
                "this item by $a" + level*10 + "%$7."
        );
    }

    @Override
    public List<SkyblockItemType> getApplicableItems() {
        return SkyblockItemType.getHandheld();
    }

    @Override
    public boolean should(Event event) {
        return event instanceof SkyblockAbilityUseEvent;
    }

    @Override
    public void execute(int level, Event event) {
        if (!(event instanceof SkyblockAbilityUseEvent abilityUseEvent)) {
            return;
        }
        double shouldPercentage = level * (1 - (level * 0.1));

        abilityUseEvent.setCostMultiplier(shouldPercentage);
    }
}
