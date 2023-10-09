package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.Slayer;
import lombok.Getter;
import lombok.Setter;

public class SlayerManager {

    private final SkyblockPlayer skyblockPlayer;

    @Getter
    private Slayer activeSlayer;

    @Getter @Setter
    private int gatheredXp;


    @Getter @Setter
    private long lastMaddoxBatphoneUse = 0L;

    @Getter @Setter
    private int failedBatphoneAttempts = 0;

    public void addFailedBatphoneAttempt() {
        this.failedBatphoneAttempts++;
    }


    public SlayerManager(SkyblockPlayer skyblockPlayer) {
        this.skyblockPlayer = skyblockPlayer;
    }

    public void setActiveSlayer(Slayer slayer) {
        this.activeSlayer = slayer;
    }

    public void cancelSlayer() {
        this.activeSlayer = null;
    }
}
