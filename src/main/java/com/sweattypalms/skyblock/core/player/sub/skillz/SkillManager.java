package com.sweattypalms.skyblock.core.player.sub.skillz;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;


import java.util.HashMap;
import java.util.Map;

import com.sweattypalms.skyblock.core.player.sub.skillz.Skill.SkillType;

public class SkillManager {

    private final Map<SkillType, Skill> skills = new HashMap<>();

    private final SkyblockPlayer skyblockPlayer;

    public SkillManager(SkyblockPlayer skyblockPlayer) {
        this.skyblockPlayer = skyblockPlayer;
        for (Skill.SkillType value : Skill.SkillType.values()) {
            skills.put(value, new Skill(0));
        }
    }

    /**
     *
     * @param skillType The type of skill
     * @param xp The amount of xp to add
     */
    public void updateSkill(SkillType skillType, double xp) {
        Skill skill = skills.get(skillType);
        skill.addXp(xp);
        skills.put(skillType, skill);
    }
}
