package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import org.bukkit.inventory.ItemStack;

public interface IAbilityActivator {
    double getBaseAbilityDamage();
    double getAbilityScaling();
}
