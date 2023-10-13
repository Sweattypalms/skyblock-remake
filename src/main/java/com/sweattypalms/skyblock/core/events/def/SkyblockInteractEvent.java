package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class SkyblockInteractEvent extends SkyblockPlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final SkyblockPlayer skyblockPlayer;
    private final TriggerType interactType;

    public SkyblockInteractEvent(SkyblockPlayer skyblockPlayer, TriggerType interactType) {
        this.skyblockPlayer = skyblockPlayer;
        this.interactType = interactType;
    }

    private Block interactedBlock;
    public Block getInteractedBlock() {
        return interactedBlock;
    }
    public void setInteractedBlock(Block interactedBlock) {
        this.interactedBlock = interactedBlock;
    }

    @Override
    public SkyblockPlayer getSkyblockPlayer() {
        return this.skyblockPlayer;
    }

    public TriggerType getInteractType() {
        return this.interactType;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
