package com.sweattypalms.skyblock.core.player.sub.scoreboard;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.SlayerManager;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import com.sweattypalms.skyblock.core.regions.Regions;
import com.sweattypalms.skyblock.slayers.Slayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ScoreboardManager {
    private static final String SCOREBOARD_TITLE = "§6§lSKYBLOCK";
    private static final String OBJECTIVE_NAME = "sb";
    private static final String OBJECTIVE_CRITERIA = "dummy";
    private final SkyblockPlayer player;
    private Scoreboard bukkitScoreboard;
    private Objective bukkitObjective;
    private Map<Section, List<ScoreboardLine>> sectionLines;
    private int currentColorIndex = 0;


    public ScoreboardManager(SkyblockPlayer player) {
        this.player = player;
        this.sectionLines = new HashMap<>();
        setupScoreboard();
    }

    private void setupScoreboard() {
        assert Bukkit.getScoreboardManager() != null;
        this.bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.bukkitObjective = bukkitScoreboard.registerNewObjective(OBJECTIVE_NAME, OBJECTIVE_CRITERIA, SCOREBOARD_TITLE);
        this.bukkitObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        setupSkyblockInfoSection();
        setupLocationSection();
        setupCurrencySection();
        setupActivity();
        setupFooter();
    }

    private void setupSkyblockInfoSection() {
        List<ScoreboardLine> lines = new ArrayList<>();

        lines.add(new ScoreboardLine(this, 14));
        lines.add(new ScoreboardLine(this, 13));
        lines.add(new ScoreboardLine(this, getSkyblockSeasonDate(), 12));
        lines.add(new ScoreboardLine(this, getSkyblockTime(), 11));

        sectionLines.put(Section.SKYBLOCK_INFO, lines);
    }

    private void setupLocationSection() {
        List<ScoreboardLine> lines = new ArrayList<>();

        lines.add(new ScoreboardLine(this, 10));

        sectionLines.put(Section.LOCATION, lines);
    }

    private void setupCurrencySection() {
        List<ScoreboardLine> lines = new ArrayList<>();

        lines.add(new ScoreboardLine(this, 9)); // spacing
        lines.add(new ScoreboardLine(this, 8)); // coins
        lines.add(new ScoreboardLine(this, 7)); // bits
        lines.add(new ScoreboardLine(this, 6)); // spacing


        sectionLines.put(Section.CURRENCY, lines);
    }

    private void setupActivity() {
        List<ScoreboardLine> lines = new ArrayList<>();

        lines.add(new ScoreboardLine(this, "Objective", 5)); // activity
        lines.add(new ScoreboardLine(this, " $eGet Good ☠", 4)); // activity

        sectionLines.put(Section.ACTIVITY, lines);
    }

    private void setupFooter() {
        List<ScoreboardLine> lines = new ArrayList<>();

        lines.add(new ScoreboardLine(this, 1));
        lines.add(new ScoreboardLine(this, " $6◆ $cmc.sweattypalms.me $6◆", 0));

        sectionLines.put(Section.FOOTER, lines);
    }

    public void updateSkyblockInfo() {
        List<ScoreboardLine> lines = sectionLines.get(Section.SKYBLOCK_INFO);
        lines.get(0).updateText(String.format(" $7 %s %s", getDateTime(), getInstance()));
    }

    public void updateLocation() {
        List<ScoreboardLine> lines = sectionLines.get(Section.LOCATION);
        lines.get(0).updateText(getRegion());
    }

    public void updateCurrency() {
        List<ScoreboardLine> lines = sectionLines.get(Section.CURRENCY);

        double coins = this.player.getCurrencyManager().getPurseBalance();
        double bits = this.player.getCurrencyManager().getBits();

        String coinsFormatted = PlaceholderFormatter.formatDecimalCSV(coins);
        String bitsFormatted = PlaceholderFormatter.formatDecimalCSV(bits);

        lines.get(1).updateText(String.format(" $fPurse: $6%s", coinsFormatted));
        lines.get(2).updateText(String.format(" $fBits: $b%s", bitsFormatted));
    }

    public void updateActivity() {
        List<ScoreboardLine> lines = sectionLines.get(Section.ACTIVITY);
        DragonManager dragonManager = DragonManager.getInstance();
        SlayerManager slayerManager = player.getSlayerManager();

        if (lines.size() > 2) {
            lines.get(2).removeFromSB();
            lines.remove(2);
        }


        if (dragonManager.isDragonActive()) {
            double currentDragonHealth = dragonManager.getActiveDragon().getEntityInstance().getHealth();
            String formattedHealth = PlaceholderFormatter.formatDecimalCSV(currentDragonHealth);

            double playerDamage = dragonManager.getPlayerDamage(player.getPlayer().getUniqueId());
            String formattedDamage = PlaceholderFormatter.formatDecimalCSV(playerDamage);

            lines.get(0).updateText(" $fDragon Health: $a" + formattedHealth + Stats.HEALTH.getSymbol());
            lines.get(1).updateText(" $fYour Damage: $c" + formattedDamage);

            sectionLines.put(Section.ACTIVITY, lines);

            return;
        }

        if (slayerManager.getActiveSlayer() != null) {
            Slayer activeSlayer = slayerManager.getActiveSlayer();

            lines.get(0).updateText("$fSlayer Quest");

            StringBuilder slayerType = new StringBuilder(activeSlayer.slayerType().toString());
            String[] slayerTypeSplit = slayerType.toString().split("_");
            slayerType = new StringBuilder();
            for (String s : slayerTypeSplit) {
                slayerType.append(PlaceholderFormatter.capitalize(s)).append(" ");
            }

            String romanLvl = PlaceholderFormatter.toRomanNumeral(activeSlayer.level());

            String line1, line2;
            if (slayerManager.getBoss() == null) {
                line1 = "$5" + slayerType + romanLvl;
                line2 = "$7($e" + slayerManager.getGatheredXp() + "$7/$c" + activeSlayer.xpRequiredToSpawn() + "$7) Combat XP";
            } else {
                line1 = "$c" + slayerType + romanLvl;
                line2 = "$eSlay the boss!";
            }

            lines.get(1).updateText(line1);
            lines.add(new ScoreboardLine(this, line2, 3));

            sectionLines.put(Section.ACTIVITY, lines);

            return;
        }


        lines.get(0).updateText("Objective");
        lines.get(1).updateText("$eSkill Tissue!");
    }

    public void updateScoreboard() {
        updateSkyblockInfo();
        updateLocation();
        updateCurrency();
        updateActivity();

        player.getPlayer().setScoreboard(bukkitScoreboard);
    }

    public String generateUniqueEntry() {
        ChatColor[] colors = ChatColor.values();
        int prefixColor = currentColorIndex / colors.length;
        int suffixColor = currentColorIndex % colors.length;

        String entry = "" + colors[prefixColor] + colors[suffixColor];

        currentColorIndex++;
        return entry;
    }

    /* -------------------- GETTERS -------------------- */
    private String getDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    private String getInstance() {
        return " $8instance_0";
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

    public enum Section {SKYBLOCK_INFO, LOCATION, CURRENCY, ACTIVITY, FOOTER}
}

