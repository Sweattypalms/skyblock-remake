package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IPersonalizedDescription {
    /**
     * Get the description of the ability for the player, personalized to the player instead of a static description
     * @param skyblockPlayer The player to get the description for
     * @param executor The item that is executing the ability
     * @return The personalized description
     */
    List<String> getDescription(SkyblockPlayer skyblockPlayer, ItemStack executor);
}
