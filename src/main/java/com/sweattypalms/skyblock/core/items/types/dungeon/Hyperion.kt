package com.sweattypalms.skyblock.core.items.types.dungeon

import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent
import com.sweattypalms.skyblock.core.items.builder.Rarity
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager.TELEPORT_ABILITY
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ICooldown
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost
import com.sweattypalms.skyblock.core.player.SkyblockPlayer
import com.sweattypalms.skyblock.core.player.sub.stats.Stats
import org.bukkit.Material
import org.bukkit.event.Event

const val ID = "hyperion"
val STATS = mapOf(
    Stats.DAMAGE to 260.0,
    Stats.STRENGTH to 150.0
)

class Hyperion : SkyblockItem(
    ID,
    "Hyperion",
    Material.IRON_SWORD,
    null,
    STATS,
    Rarity.LEGENDARY,
    SkyblockItemType.SWORD
), IHasAbility {

    class ShadowWarp : TELEPORT_ABILITY(10), ICooldown {
        override fun getName(): String {
            return "Shadow Warp"
        }

        override fun getDescription(): MutableList<String> {
            return mutableListOf(
                "$7Creates a space distortion \$e10",
                "$7blocks ahead of you that sucks all",
                "$7enemies around it. Use this ability",
                "$7again within \$e5$7 seconds to detonate",
                "$7the warp and deal damage to enemies",
                "$7near it."
            )
        }

        override fun apply(event: Event) {
            if (event !is SkyblockInteractEvent) return
            if (super.isOnCooldown(event.skyblockPlayer)) return

            super<ICooldown>.apply(event)
            super<TELEPORT_ABILITY>.apply(event)
        }

        override fun getCooldown(skyblockPlayer: SkyblockPlayer?): Long = 10_000

        override fun getCost(): MutableMap<Stats, Double> = mutableMapOf(
            Stats.INTELLIGENCE to 300.0
        )
    }

    override fun getAbilities(): MutableList<Ability> = mutableListOf(ShadowWarp())
}
