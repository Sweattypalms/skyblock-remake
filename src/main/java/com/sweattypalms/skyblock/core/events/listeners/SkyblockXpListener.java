package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.def.SkyblockXpEvent;
import com.sweattypalms.skyblock.core.player.sub.skillz.SkillManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class SkyblockXpListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onXpEvent(SkyblockXpEvent event) {
        SkillManager skillManager = event.getSkyblockPlayer().getSkillManager();
        skillManager.updateSkill(event.getSkillType(), event.getXp());
    }


}
