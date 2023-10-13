package com.sweattypalms.skyblock.ui;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerEvent;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class GUIItemClickEvent extends SkyblockPlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final ClickableItem clickedItem;
    @Getter
    private final ItemStack cursorItem;
    @Getter
    private final SkyblockPlayer skyblockPlayer;

    private boolean cancelled;

    public GUIItemClickEvent(ClickableItem clickedItem, ItemStack cursorItem, SkyblockPlayer skyblockPlayer) {
        this.clickedItem = clickedItem;
        this.cursorItem = cursorItem;
        this.skyblockPlayer = skyblockPlayer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
