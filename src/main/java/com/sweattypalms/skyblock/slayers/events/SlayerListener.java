package com.sweattypalms.skyblock.slayers.events;

import com.sweattypalms.skyblock.core.events.def.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockXpEvent;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.SlayerManager;
import com.sweattypalms.skyblock.slayers.Slayer;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SlayerListener implements Listener {

    private static String getSlayerLevelMessage(SlayerManager slayerManager) {
        Slayer activeSlayer = slayerManager.getActiveSlayer();
        String completedSlayer = activeSlayer.slayerType().getAlternateName();

        /* TODO: Implement Slayer Levels */
        int playerCurrentLevel = 1;
        int nextLevelIn = 100_000;

        String nextLevelInStr = PlaceholderFormatter.formatDecimalCSV(nextLevelIn);

        return String.format(
                "  $e%s LVL %s $5 - $7Next LVL in $d%s XP$7!",
                completedSlayer,
                playerCurrentLevel,
                nextLevelInStr
        );
    }

    @EventHandler
    public void onSlayerStartEvent(SlayerStartEvent event) {
        if (event.getSkyblockPlayer().getSlayerManager().getActiveSlayer() != null) {
            event.setCancelled(true);

            event.getSkyblockPlayer().sendMessage(" $c$lYou already have an active slayer quest!");
            return;
        }

        String startedMessage = " $5$lSLAYER QUEST STARTED!";
        String slayerNameMessage =
                String.format("  $5⇒ $7Slay $c%s Combat XP $7worth of %s.",
                        PlaceholderFormatter.formatDecimalCSV(event.getSlayer().xpRequiredToSpawn()),
                        event.getSlayer().slayerType().getMobType()
                );

        String finalMessage = startedMessage + "\n" + slayerNameMessage;
        finalMessage = PlaceholderFormatter.format(finalMessage);

        Player player = event.getSkyblockPlayer().getPlayer();
        player.sendMessage(finalMessage);

        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 10, 1);

        event.getSkyblockPlayer().getSlayerManager().setActiveSlayer(event.getSlayer());
    }

    @EventHandler
    public void onSlayerFailEvent(SlayerFailEvent event) {
        String failedMessage = " $c$lSLAYER QUEST FAILED!";
        String reasonMessage = "  $5⇒ $7" + event.getReason();

        String finalMessage = failedMessage + "\n" + reasonMessage;
        finalMessage = PlaceholderFormatter.format(finalMessage);

        Player player = event.getSkyblockPlayer().getPlayer();
        player.sendMessage(finalMessage);
    }

    @EventHandler
    public void onXpGain(SkyblockXpEvent event) {
        if (!(event.getReason() instanceof SkyblockDeathEvent deathEvent)) return;

        SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();
        LivingEntity deadEntity = deathEvent.getDeadEntity();

        SlayerManager slayerManager = skyblockPlayer.getSlayerManager();

        if (slayerManager.getActiveSlayer() == null) return;

        Slayer activeSlayer = slayerManager.getActiveSlayer();

        if (!activeSlayer.slayerType().validEntity(deadEntity.getType())) return;

        double xpToGain = event.getXp();

        slayerManager.addGatheredXp((int) xpToGain, deadEntity);
    }

    @EventHandler
    public void onSlayerDeath(SkyblockDeathEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        SlayerManager slayerManager = skyblockPlayer.getSlayerManager();
        EntityLiving entityLiving = ((CraftLivingEntity) event.getDeadEntity()).getHandle();

        if (entityLiving != slayerManager.getBoss()) return;

        String message = " $a$lSLAYER QUEST COMPLETE!";

        String slayerLevelMessage = getSlayerLevelMessage(slayerManager);

        String finalMessage = message + "\n" + slayerLevelMessage;

        skyblockPlayer.sendMessage(finalMessage);

        slayerManager.finishSlayer();
    }

}
