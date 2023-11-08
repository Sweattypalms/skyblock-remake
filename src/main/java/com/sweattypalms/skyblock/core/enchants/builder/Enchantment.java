package com.sweattypalms.skyblock.core.enchants.builder;

import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

/**
 * Represents an enchantment <br/>
 * Look at {@link com.sweattypalms.skyblock.core.enchants.impl.Telekinesis} for an example
 */
@Getter
public abstract class Enchantment {
    private final String id;
    private final int maxLevel;

    /**
     * Create a new enchantment
     * Defaults to max level 5
     * @param id The id of the enchantment
     */
    public Enchantment(String id) {
        this.id = id;
        this.maxLevel = 5;
    }

    /**
     * Create a new enchantment
     * @param id The id of the enchantment
     * @param maxLevel The max level of the enchantment
     */
    public Enchantment(String id, int maxLevel) {
        this.id = id;
        this.maxLevel = maxLevel;
    }

    /**
     * Get the name of the enchantment
     * @return The name of the enchantment
     */
    public abstract String getName();

    /**
     * Get the description of the enchantment
     * @param level The level of the enchantment
     * @return The description of the enchantment
     */
    public abstract List<String> getDescription(int level);

    /**
     * Get the list of item types that the enchantment can be applied to
     * @return The applicable item types for the enchantment
     */
    public abstract List<SkyblockItemType> getApplicableItems();

    public static int getLevel(ItemStack itemStack, Enchantment enchantment) {
        Optional<Integer> level = EnchantManager.getEnchantment(itemStack, enchantment.getId());
        return level.orElse(0);
    }
}
