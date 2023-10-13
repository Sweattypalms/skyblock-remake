package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.regions.Regions;
import com.sweattypalms.skyblock.slayers.Slayer;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScoreboardManager {

    private final SkyblockPlayer player;
    private final Scoreboard bukkitScoreboard;
    private final Objective bukkitObjective;
    private final List<Map<String, String>> sections = new ArrayList<>();
    private final Map<Integer, String> scoreCache = new HashMap<>();
    int tick = 0;
    private int score = 13;

    public ScoreboardManager(SkyblockPlayer player) {
        this.player = player;
        this.bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.bukkitObjective = bukkitScoreboard.registerNewObjective("sb", "dummy", "§6§lSKYBLOCK");
        this.bukkitObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        initializeSections();
        displayBoard();
        this.player.getPlayer().setScoreboard(bukkitScoreboard);
    }

    private void initializeSections() {
        sections.clear();
        sections.add(skyblockInfoSection());
        sections.add(locationSection());
        sections.add(currencySection());
        sections.add(activitySection());
        sections.add(footerSection());
    }

    private Map<String, String> skyblockInfoSection() {
        Map<String, String> section = new LinkedHashMap<>();
        section.put("date", " $7" + getDateTime() + " " + getInstance());
        section.put("spacing1", " $1");
        section.put("seasonDate", getSkyblockSeasonDate());
        section.put("skyblockTime", getSkyblockTime());
        return section;
    }

    private Map<String, String> locationSection() {
        Map<String, String> section = new LinkedHashMap<>();
        section.put("region", getRegion());
        return section;
    }

    private Map<String, String> currencySection() {
        Map<String, String> section = new LinkedHashMap<>();
        double placeholderCoins = 1_879_000_000.0;
        double placeholderBits = 257_000.0;
        String coins = PlaceholderFormatter.formatDecimalCSV(placeholderCoins);
        String bits = PlaceholderFormatter.formatDecimalCSV(placeholderBits);

        section.put("spacing2", " $2");
        section.put("purse", " $fPurse: $6" + coins);
        section.put("bits", " $fBits: $b" + bits);
        return section;
    }

    private Map<String, String> activitySection() {
        Map<String, String> section = new LinkedHashMap<>();
        DragonManager dragonManager = DragonManager.getInstance();

        section.put("spacing4", " $4");

        SlayerManager slayerManager = player.getSlayerManager();

        if (slayerManager.getActiveSlayer() != null) {
            Slayer activeSlayer = slayerManager.getActiveSlayer();
            section.put("slayer_quest", "$fSlayer Quest");
            StringBuilder slayerType = new StringBuilder(activeSlayer.slayerType().toString());
            String[] slayerTypeSplit = slayerType.toString().split("_");
            slayerType = new StringBuilder();
            for (String s : slayerTypeSplit) {
                slayerType.append(PlaceholderFormatter.capitalize(s)).append(" ");
            }

            String romanLvl = PlaceholderFormatter.toRomanNumeral(activeSlayer.level());

            if(slayerManager.getBoss() == null) {
                section.put("quest_name", "$5" + slayerType + romanLvl);
                section.put("slayer_xp", "$7($e" + slayerManager.getGatheredXp() + "/$c" + activeSlayer.xpRequiredToSpawn() + "$7) Combat XP");
            }else {
                section.put("slayer_boss", "$c" + slayerType + romanLvl);
                section.put("slay_the_boss", "$eSlay the boss!");
            }
            return section;
        }

        if (dragonManager.isDragonActive()) {
            double currentHealth = dragonManager.getActiveDragon().getEntityInstance().getHealth();
            section.put("dragonHealth", "$fDragon Health: $a" + PlaceholderFormatter.formatDecimalCSV(currentHealth) + Stats.HEALTH.getSymbol());

            double playerDamage = dragonManager.getPlayerDamage(player.getPlayer().getUniqueId());
            section.put("playerDamage", "$fYour Damage: $c" + PlaceholderFormatter.formatDecimalCSV(playerDamage));
            return section;
        }



        section.put("objective", " $fObjective");
        section.put("message", " $eGet good :)");

        return section;
    }

    private Map<String, String> footerSection() {
        Map<String, String> section = new LinkedHashMap<>();
        section.put("spacing3", " $3$l");
        section.put("footer", " $6◆ $cmc.sweattypalms.com $6◆");
        return section;
    }

    private void displayBoard() {
        score = getScore();
        for (Map<String, String> section : sections) {
            for (String value : section.values()) {
                setScore(value, score--);
            }
        }
    }

    public int getScore(){
        return 14;
//        int maxScore = 0;
//
//        for (Map<String, String> section : sections) {
//            for (String ignored : section.values()) {
//                maxScore++;
//            }
//        }
//
//        return maxScore;
    }

    public void updateScoreboard() {
        this.score = 14;
        initializeSections(); // Refresh sections
        displayBoard();
    }

    private void setScore(String scoreText, int value) {
//        String formattedScore = PlaceholderFormatter.format(score);
//        bukkitObjective.getScore(formattedScore).setScore(value);
        String formattedScore = PlaceholderFormatter.format(scoreText);

        if (!formattedScore.equals(scoreCache.get(value))) {

            String oldEntry = scoreCache.get(value);
            if (oldEntry != null) {
                bukkitScoreboard.resetScores(oldEntry);
            }

            bukkitObjective.getScore(formattedScore).setScore(value);

            // update the cache
            scoreCache.put(value, formattedScore);
        }

    }
    /* -------------------- GETTERS -------------------- */

    private String getDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    private String getInstance() {
        return "$8instance_0";
    }

    private String getSkyblockSeasonDate() {
        return " $fEarly Summer $e31st";
    }

    private String getSkyblockTime() {
        return " $e☀ $7" + "12:00pm";
    }

    private String getRegion() {
        Regions region = this.player.getLastKnownRegion();
        if (region != null) {
            return " $7⏣ " + region.getDisplayName();
        } else {
            return " $7⏣ " + "Unknown";
        }
    }
}