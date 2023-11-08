package com.sweattypalms.skyblock.core.player;

public abstract class PlayerManager {
    protected final SkyblockPlayer skyblockPlayer;

    public PlayerManager(SkyblockPlayer skyblockPlayer) {
        this.skyblockPlayer = skyblockPlayer;
    }

    public SkyblockPlayer getSkyblockPlayer() {
        return this.skyblockPlayer;
    }
}
