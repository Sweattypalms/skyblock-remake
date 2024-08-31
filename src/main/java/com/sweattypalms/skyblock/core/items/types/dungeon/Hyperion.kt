package com.sweattypalms.skyblock.core.items.types.dungeon

import com.sweattypalms.skyblock.api.sequence.Sequence
import com.sweattypalms.skyblock.api.sequence.SequenceAction
import com.sweattypalms.skyblock.api.sequence.SequencedAction
import com.sweattypalms.skyblock.api.sequence.TimedAction
import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent.DamageType
import com.sweattypalms.skyblock.core.items.builder.Rarity
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability
import com.sweattypalms.skyblock.core.items.builder.abilities.AbilityManager.TELEPORT_ABILITY
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ICooldown
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IUsageCost
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob
import com.sweattypalms.skyblock.core.player.SkyblockPlayer
import com.sweattypalms.skyblock.core.player.sub.bonus.Bonus
import com.sweattypalms.skyblock.core.player.sub.stats.Stats
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity
import org.bukkit.event.Event

const val ID = "hyperion"
const val COOLDOWN = 10L
val STATS = mapOf(
    Stats.DAMAGE to 260.0,
    Stats.STRENGTH to 150.0,
    Stats.INTELLIGENCE to 350.0,
    Stats.FEROCITY to 30.0,
)


@Suppress("unused")
class Hyperion : SkyblockItem(
    ID,
    "Hyperion",
    Material.IRON_SWORD,
    null,
    STATS,
    Rarity.LEGENDARY,
    SkyblockItemType.SWORD
), IHasAbility {

    class Implosion: ITriggerableAbility, IUsageCost, ICooldown {
        override fun getName(): String = "Implosion"

        override fun getDescription(): MutableList<String> = mutableListOf(
            "$7Deals \$c10,000 $7damage to nearby enemies."
        )
        override fun apply(event: Event?) {
            if (event !is SkyblockInteractEvent) return
            if (super.isOnCooldown(event.skyblockPlayer)) return
            super<ICooldown>.apply(event)

            ability(event)
        }

        private fun ability(event: SkyblockInteractEvent) {
            val player = event.skyblockPlayer.player
            val location = player.location
            val world = location.world ?: return

            val range = 5.0
            val entities = world.getNearbyEntities(location, range, range, range)

            for (entity in entities) {
                if (entity !is CraftLivingEntity) continue
                if (entity.handle !is ISkyblockMob) continue

                val damageEvent = SkyblockPlayerDamageEntityEvent(entity, player, DamageType.ABILITY)
                damageEvent.abilityItem = event.skyblockPlayer.inventoryManager.itemInHand
                damageEvent.ability = this

                Bukkit.getPluginManager().callEvent(damageEvent)
            }

            player.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 5f, 1f)
            player.world.spawnParticle<Any>(
                Particle.EXPLOSION_LARGE,
                location.add(0.0, 0.5, 0.0),
                6,
                0.0,
                0.0,
                0.0,
                6.0,
                null,
                true
            )
        }

        override fun getCooldown(skyblockPlayer: SkyblockPlayer?): Long = COOLDOWN

        override fun getTriggerType(): TriggerType {
            return TriggerType.RIGHT_CLICK
        }

        override fun trigger(event: Event?): Boolean {
            if (event !is SkyblockInteractEvent) return false

            return event.interactType == TriggerType.RIGHT_CLICK
        }

        override fun getCost(): MutableMap<Stats, Double> {
            return mutableMapOf(
                Stats.INTELLIGENCE to 300.0
            )
        }

    }

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

        override fun getCooldown(skyblockPlayer: SkyblockPlayer?): Long = COOLDOWN

        override fun getCost(): MutableMap<Stats, Double> = mutableMapOf(
            Stats.INTELLIGENCE to 300.0
        )
    }
    class WitherShield : ITriggerableAbility, IUsageCost, ICooldown {
        override fun getName(): String = "Wither Shield"

        override fun getDescription(): MutableList<String> = mutableListOf(
            "$7Reduces damage taken by \$e30%",
            "$7for \$e5$7 seconds. Also grants",
            "$7an absorption shield that gives",
            "\$b200%$7 of your Critical Damage",
            "$7as health, after \$e5$7 seconds",
            "\$a50%$7 of the shield is",
            "$7converted into healing."
        )

        override fun apply(event: Event?) {
            if (event !is SkyblockInteractEvent) return
            if (super.isOnCooldown(event.skyblockPlayer)) return

            super<ICooldown>.apply(event)

            ability(event)
        }

        private fun ability(event: SkyblockInteractEvent) {
            val skyblockPlayer = event.skyblockPlayer

            val absorptionAmt = skyblockPlayer.statsManager.getMaxStat(Stats.CRIT_DAMAGE) * 1.5
            skyblockPlayer.bonusManager.setBonus(
                "hyperion.wither_shield.absorption",
                Bonus(Stats.ABSORPTION, absorptionAmt, 5_000)
            ) {
                val statsManager = skyblockPlayer.statsManager
                val absorption = statsManager.getLiveStat(Stats.ABSORPTION) / 2
                val newHealth = (statsManager.getLiveStat(Stats.HEALTH) + absorption).coerceAtLeast(
                    statsManager.getMaxStat(Stats.HEALTH)
                )

                val player = skyblockPlayer.player

                player.health = newHealth

                player.playSound(
                    player.location,
                    Sound.ENTITY_PLAYER_LEVELUP,
                    1f,
                    2f
                )
            }
        }


        override fun getTriggerType(): TriggerType {
            return TriggerType.RIGHT_CLICK
        }

        override fun trigger(event: Event?): Boolean {
            if (event !is SkyblockInteractEvent) return false

            return event.interactType == TriggerType.RIGHT_CLICK
        }

        override fun getCost(): MutableMap<Stats, Double> = mutableMapOf(
            Stats.INTELLIGENCE to 150.0
        )
        override fun getCooldown(skyblockPlayer: SkyblockPlayer?): Long = COOLDOWN

    }
    override fun getAbilities(): MutableList<Ability> = mutableListOf(ShadowWarp(), Implosion(), WitherShield())
}
