package com.sweattypalms.skyblock.ui;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ClickableItem {
    @Getter
    private ItemStack item;
    private Consumer<Player> onClick;

    public ClickableItem(ItemStack item, Consumer<Player> onClick) {
        this.item = item;
        this.onClick = onClick;
    }

    public void click(Player player){
        this.onClick.accept(player);
    }

}
