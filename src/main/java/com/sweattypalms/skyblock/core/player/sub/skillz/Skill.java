package com.sweattypalms.skyblock.core.player.sub.skillz;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Skill {
    private double xp;

    public Skill(double xp) {
        this.xp = xp;
    }

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
    }

    public int getCurrentLevel() {
        // Using a reverse search because players with a lot of XP will likely be at a high level
        for (int i = SkillData.LEVEL_DATA.size() - 1; i >= 0; i--) {
            if (xp >= SkillData.LEVEL_DATA.get(i).cumulativeXp()) {
                return i;
            }
        }
        return 0; // This shouldn't happen but is a safe default
    }

    public double getXpRequiredForNextLevel() {
        int currentLevel = getCurrentLevel();
        if(currentLevel == SkillData.LEVEL_DATA.size() - 1) return 0; // Max level
        return SkillData.LEVEL_DATA.get(currentLevel + 1).xpRequired();
    }

    public double getCoinsReward() {
        int currentLevel = getCurrentLevel();
        return SkillData.LEVEL_DATA.get(currentLevel).coinsReward();
    }


    public void addXp(double xp) {
        this.xp += xp;
    }



    /* -------------------------- SKILL DATA -------------------------- */

    public class SkillData {
        public static final Map<Integer, SkillLevelData> LEVEL_DATA = new HashMap<>();

        /**
         * Level data
         */
        static {
//                                                  Level 	XP Required    Cumulative XP  Coins
            LEVEL_DATA.put(0, new SkillLevelData(0, 0, 0, 0));
            LEVEL_DATA.put(1, new SkillLevelData(1, 50, 50, 100));
            LEVEL_DATA.put(2, new SkillLevelData(2, 125, 175, 250));
            LEVEL_DATA.put(3, new SkillLevelData(3, 200, 375, 500));
            LEVEL_DATA.put(4, new SkillLevelData(4, 300, 675, 750));
            LEVEL_DATA.put(5, new SkillLevelData(5, 500, 1175, 1000));
            LEVEL_DATA.put(6, new SkillLevelData(6, 750, 1925, 2000));
            LEVEL_DATA.put(7, new SkillLevelData(7, 1000, 2925, 3000));
            LEVEL_DATA.put(8, new SkillLevelData(8, 1500, 4425, 4000));
            LEVEL_DATA.put(9, new SkillLevelData(9, 2000, 6425, 5000));
            LEVEL_DATA.put(10, new SkillLevelData(10, 3500, 9925, 7500));
            LEVEL_DATA.put(11, new SkillLevelData(11, 5000, 14925, 10000));
            LEVEL_DATA.put(12, new SkillLevelData(12, 7500, 22425, 15000));
            LEVEL_DATA.put(13, new SkillLevelData(13, 10000, 32425, 20000));
            LEVEL_DATA.put(14, new SkillLevelData(14, 15000, 47425, 25000));
            LEVEL_DATA.put(15, new SkillLevelData(15, 20000, 67425, 30000));
            LEVEL_DATA.put(16, new SkillLevelData(16, 30000, 97425, 40000));
            LEVEL_DATA.put(17, new SkillLevelData(17, 50000, 147425, 50000));
            LEVEL_DATA.put(18, new SkillLevelData(18, 75000, 222425, 65000));
            LEVEL_DATA.put(19, new SkillLevelData(19, 100000, 322425, 80000));
            LEVEL_DATA.put(20, new SkillLevelData(20, 200000, 522425, 100000));
            LEVEL_DATA.put(21, new SkillLevelData(21, 300000, 822425, 125000));
            LEVEL_DATA.put(22, new SkillLevelData(22, 400000, 1222425, 150000));
            LEVEL_DATA.put(23, new SkillLevelData(23, 500000, 1722425, 175000));
            LEVEL_DATA.put(24, new SkillLevelData(24, 600000, 2322425, 200000));
            LEVEL_DATA.put(25, new SkillLevelData(25, 700000, 3022425, 225000));
            LEVEL_DATA.put(26, new SkillLevelData(26, 800000, 3822425, 250000));
            LEVEL_DATA.put(27, new SkillLevelData(27, 900000, 4722425, 275000));
            LEVEL_DATA.put(28, new SkillLevelData(28, 1000000, 5722425, 300000));
            LEVEL_DATA.put(29, new SkillLevelData(29, 1100000, 6822425, 325000));
            LEVEL_DATA.put(30, new SkillLevelData(30, 1200000, 8022425, 350000));
            LEVEL_DATA.put(31, new SkillLevelData(31, 1300000, 9322425, 375000));
            LEVEL_DATA.put(32, new SkillLevelData(32, 1400000, 10722425, 400000));
            LEVEL_DATA.put(33, new SkillLevelData(33, 1500000, 12222425, 425000));
            LEVEL_DATA.put(34, new SkillLevelData(34, 1600000, 13822425, 450000));
            LEVEL_DATA.put(35, new SkillLevelData(35, 1700000, 15522425, 475000));
            LEVEL_DATA.put(36, new SkillLevelData(36, 1800000, 17322425, 500000));
            LEVEL_DATA.put(37, new SkillLevelData(37, 1900000, 19222425, 550000));
            LEVEL_DATA.put(38, new SkillLevelData(38, 2000000, 21222425, 600000));
            LEVEL_DATA.put(39, new SkillLevelData(39, 2100000, 23322425, 650000));
            LEVEL_DATA.put(40, new SkillLevelData(40, 2200000, 25522425, 700000));
            LEVEL_DATA.put(41, new SkillLevelData(41, 2300000, 27822425, 750000));
            LEVEL_DATA.put(42, new SkillLevelData(42, 2400000, 30222425, 800000));
            LEVEL_DATA.put(43, new SkillLevelData(43, 2500000, 32722425, 850000));
            LEVEL_DATA.put(44, new SkillLevelData(44, 2600000, 35322425, 900000));
            LEVEL_DATA.put(45, new SkillLevelData(45, 2750000, 38072425, 1000000));
            LEVEL_DATA.put(46, new SkillLevelData(46, 2900000, 40972425, 1000000));
            LEVEL_DATA.put(47, new SkillLevelData(47, 3100000, 44072425, 1000000));
            LEVEL_DATA.put(48, new SkillLevelData(48, 3400000, 47472425, 1000000));
            LEVEL_DATA.put(49, new SkillLevelData(49, 3700000, 51172425, 1000000));
            LEVEL_DATA.put(50, new SkillLevelData(50, 4000000, 55172425, 1000000));
            LEVEL_DATA.put(51, new SkillLevelData(51, 4300000, 59472425, 1000000));
            LEVEL_DATA.put(52, new SkillLevelData(52, 4600000, 64072425, 1000000));
            LEVEL_DATA.put(53, new SkillLevelData(53, 4900000, 68972425, 1000000));
            LEVEL_DATA.put(54, new SkillLevelData(54, 5200000, 74172425, 1000000));
            LEVEL_DATA.put(55, new SkillLevelData(55, 5500000, 79672425, 1000000));
            LEVEL_DATA.put(56, new SkillLevelData(56, 5800000, 85472425, 1000000));
            LEVEL_DATA.put(57, new SkillLevelData(57, 6100000, 91572425, 1000000));
            LEVEL_DATA.put(58, new SkillLevelData(58, 6400000, 97972425, 1000000));
            LEVEL_DATA.put(59, new SkillLevelData(59, 6700000, 104672425, 1000000));
            LEVEL_DATA.put(60, new SkillLevelData(60, 7000000, 111672425, 1000000));
        }
    }
    public record SkillLevelData (int level, double xpRequired, double cumulativeXp, double coinsReward) {}
}
