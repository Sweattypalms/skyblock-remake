package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.Slayer;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SlayerManager {
    private Slayer activeSlayer;

    @Setter
    private int gatheredXp;

    @Setter
    private long lastMaddoxBatphoneUse = 0L;

    @Setter
    private int failedBatphoneAttempts = 0;

    public void addFailedBatphoneAttempt() {
        this.failedBatphoneAttempts++;
    }


    public SlayerManager(SkyblockPlayer skyblockPlayer) {
    }

    public void setActiveSlayer(Slayer slayer) {
        this.activeSlayer = slayer;
    }

    public void cancelSlayer() {
        this.activeSlayer = null;
    }
}
