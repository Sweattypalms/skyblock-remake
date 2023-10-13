package com.sweattypalms.skyblock.slayers.events;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.ISlayerMob;

public class SlayerFinishEvent extends SlayerEvent{
    public SlayerFinishEvent(ISlayerMob slayerMob, SkyblockPlayer skyblockPlayer) {
        super(slayerMob, skyblockPlayer);
    }
}
