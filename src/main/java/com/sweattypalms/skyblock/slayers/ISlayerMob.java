package com.sweattypalms.skyblock.slayers;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;

import java.util.List;


/**
 * NOTE: Slayer's are 4 minutes;
 */

public interface ISlayerMob {
    List<SkyblockItem> getConfirmedDrops();

    List<SlayerDrop> getRngDrops();


    int getSlayerXpReward();

    boolean requirementsMet(SkyblockPlayer skyblockPlayer);

    long getStartTime();

    SkyblockPlayer getOwnerPlayer();
    void setOwnerPlayer(SkyblockPlayer skyblockPlayer);
}
