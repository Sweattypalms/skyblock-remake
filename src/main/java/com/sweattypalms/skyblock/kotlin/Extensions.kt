package com.sweattypalms.skyblock.kotlin

import com.sweattypalms.skyblock.core.player.SkyblockPlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

public fun Player.getSkyblockPlayer(): SkyblockPlayer {
    return SkyblockPlayer.getSkyblockPlayer(this)
}

fun something(uuid: UUID) {
    val bukkitPlayer = Bukkit.getPlayer(uuid)
    bukkitPlayer?.getSkyblockPlayer()
}