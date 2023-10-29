package com.sweattypalms.skyblock.kotlin

import com.sweattypalms.skyblock.core.player.SkyblockPlayer
import org.bukkit.entity.Player

public fun Player.getSkyblockPlayer(): SkyblockPlayer {
    return SkyblockPlayer.getSkyblockPlayer(this)
}