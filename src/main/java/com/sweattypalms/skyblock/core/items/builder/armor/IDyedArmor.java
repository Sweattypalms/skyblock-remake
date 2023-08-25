package com.sweattypalms.skyblock.core.items.builder.armor;

import org.bukkit.Color;

public interface IDyedArmor {

    /**
     * Gets the color of the dyed armor using a hex code.
     *
     * @return the color of the armor
     */
    default Color getColor() {
        String hexCode = getHexColor();
        int rgb = Integer.parseInt(hexCode, 16);
        return Color.fromRGB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }

    /**
     * Gets the hex color code for the dyed armor.
     *
     * @return the hex color code (without the '#' prefix)
     */
    String getHexColor();
}