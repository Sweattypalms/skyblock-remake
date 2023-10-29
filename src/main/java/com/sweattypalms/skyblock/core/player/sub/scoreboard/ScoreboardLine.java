package com.sweattypalms.skyblock.core.player.sub.scoreboard;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardLine {
    private Team team;
    private String entry;
    private Objective objective;

//    public ScoreboardLine(Scoreboard scoreboard, String entryName, int score, Objective obj) {
//        entryName = PlaceholderFormatter.format(entryName);
//        this.entry = entryName;
//        this.team = scoreboard.registerNewTeam("team_" + entryName.hashCode());
//        this.objective = obj;
//
//        team.addEntry(entry);
//        objective.getScore(entry).setScore(score);
//    }

    public ScoreboardLine(ScoreboardManager manager, int score) {
        this.entry = manager.generateUniqueEntry();
        this.team = manager.getBukkitScoreboard().registerNewTeam("team_" + entry.hashCode());
        this.objective = manager.getBukkitObjective();

        team.addEntry(entry);
        objective.getScore(entry).setScore(score);
    }

    public ScoreboardLine(ScoreboardManager manager, String text, int score) {
        text = PlaceholderFormatter.format(text);
        this.entry = manager.generateUniqueEntry();
        this.team = manager.getBukkitScoreboard().registerNewTeam("team_" + entry.hashCode());
        this.objective = manager.getBukkitObjective();

        team.addEntry(entry);
        objective.getScore(entry).setScore(score);

        this.updateText(text);
    }

    private String[] splitToPrefixSuffix(String input) {
        int maxchars = 16;

        String prefix = "", suffix = "";

        if (input.length() <= maxchars) {
            return new String[]{input, ""};
        }

        prefix = input.substring(0, maxchars);
        suffix = input.substring(maxchars);

        boolean ewc = prefix.endsWith("§");

        if (ewc) {
            prefix = input.substring(0, maxchars - 1);
            suffix = input.substring(maxchars - 1);
        } else {
            String lastColor = ChatColor.getLastColors(prefix);
            if (!lastColor.isEmpty()) {
                suffix = lastColor + suffix;
            }
        }

        return new String[]{prefix, suffix};
    }

    public void updateText(String text) {
        text = PlaceholderFormatter.format(text);
        String[] split = splitToPrefixSuffix(text);

        team.setPrefix(split[0]);
        team.setSuffix(split[1]);
    }

    public void removeFromSB() {
        assert objective.getScoreboard() != null;
        objective.getScoreboard().resetScores(entry); // This will remove the score associated with the entry
        team.removeEntry(entry);  // This will remove the team
    }

}
