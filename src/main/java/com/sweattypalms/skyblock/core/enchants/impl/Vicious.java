package com.sweattypalms.skyblock.core.enchants.impl;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.PassiveEnchantment;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.bonus.Bonus;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Vicious extends PassiveEnchantment {
    public Vicious() {
        super("vicious", 7);
    }

    @Override
    public String getName() {
        return "Vicious";
    }

    @Override
    public List<String> getDescription(int level) {
        return List.of(
                "Grants $c+" + level + Stats.FEROCITY.getSymbol() + " Ferocity"
        );
    }

    @Override
    public List<SkyblockItemType> getApplicableItems() {
        return SkyblockItemType.getHandheld();
    }

    @Override
    public void onTick(SkyblockPlayer player) {
        ItemStack item = player.getInventoryManager().getItemInHand();
        Bonus bonus = new Bonus(Stats.FEROCITY, getLevel(item, this), 1000);

        player.getBonusManager().setBonus("enchants.vicious", bonus);
    }
}
