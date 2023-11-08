package com.sweattypalms.skyblock.core.enchants.builder;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;

public abstract class PassiveEnchantment extends Enchantment{
    public PassiveEnchantment(String id) {
        super(id);
    }

    public abstract void onTick(SkyblockPlayer player);
}