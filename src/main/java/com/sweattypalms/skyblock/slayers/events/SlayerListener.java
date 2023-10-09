package com.sweattypalms.skyblock.slayers.events;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SlayerListener implements Listener {

    @EventHandler
    public void onSlayerStartEvent(SlayerStartEvent event) {


        if(event.getSkyblockPlayer().getSlayerManager().getActiveSlayer() != null) {
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
}
