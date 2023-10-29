package com.sweattypalms.skyblock.core.events.def;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.skillz.Skill;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SkyblockXpEvent extends SkyblockPlayerEvent{
    private static final HandlerList handlerList = new HandlerList();
    @Getter
    private final double xp;
    @Getter
    private final Skill.SkillType skillType;
    @Getter
    private final SkyblockPlayer skyblockPlayer;
    @Getter
    private final Event reason;

    public SkyblockXpEvent(double xp, Skill.SkillType skillType, SkyblockPlayer skyblockPlayer, Event reason) {
        this.xp = xp;
        this.skillType = skillType;
        this.skyblockPlayer = skyblockPlayer;
        this.reason = reason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
