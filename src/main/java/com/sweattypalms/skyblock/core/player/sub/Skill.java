package com.sweattypalms.skyblock.core.player.sub;

public record Skill(SkillType skillType, int level, double xp) {
    public enum SkillType {
        FARMING,
        MINING,
        COMBAT,
        FORAGING,
        FISHING,
        ENCHANTING,
        ALCHEMY,
        CARPENTRY,
        RUNECRAFTING,
        SOCIAL,
        TAMING,
        ;
    }


}
