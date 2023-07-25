package com.sweattypalms.skyblock.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.time.LocalDate;

public class ScoreBoards {

    private static final String[] scores = {ChatColor.RED.toString(), ChatColor.YELLOW.toString(), ChatColor.GOLD.toString(), ChatColor.GREEN.toString(), ChatColor.DARK_AQUA.toString(), ChatColor.BLUE.toString(), ChatColor.WHITE.toString()};

    public static void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy", ChatColor.YELLOW + "SkyBlock");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team date = scoreboard.registerNewTeam("date");
        date.addEntry(scores[1]);
        LocalDate local = LocalDate.now();
        String[] values = local.toString().split("-");
        String format = values[1] + "/" + values[2] + "/" + values[0];
        date.setPrefix(ChatColor.GRAY + format);
        objective.getScore(scores[1]).setScore(9);

        Team season = scoreboard.registerNewTeam("season");
        season.addEntry(scores[2]);
        season.setPrefix(ChatColor.WHITE + "Early Spring");
        season.setSuffix(ChatColor.WHITE + " 12th");
        objective.getScore(scores[2]).setScore(7);

        Team time = scoreboard.registerNewTeam("time");
        time.addEntry(scores[3]);
        time.setPrefix(ChatColor.GRAY + "12:00am");
        time.setSuffix(" ☀");
        objective.getScore(scores[3]).setScore(6);

        Team location = scoreboard.registerNewTeam("location");
        location.addEntry(scores[4]);
        location.setPrefix(ChatColor.GRAY + "⏣ ");
        location.setSuffix(ChatColor.AQUA + "Village");
        objective.getScore(scores[4]).setScore(5);

        Team purse = scoreboard.registerNewTeam("purse");
        purse.addEntry(scores[0]);
        purse.setSuffix(ChatColor.GOLD + "" + 0);
        purse.setPrefix("Purse: " + ChatColor.GOLD);
        objective.getScore(scores[0]).setScore(3);

        Team bits = scoreboard.registerNewTeam("bits");
        bits.addEntry(scores[5]);
        bits.setPrefix(ChatColor.WHITE + "Bits: ");
        bits.setSuffix(ChatColor.AQUA + "0");
        objective.getScore(scores[5]).setScore(2);

        objective.getScore(ChatColor.YELLOW + "mc.SkyItem.net").setScore(0);
        objective.getScore("").setScore(1);
        objective.getScore(" ").setScore(4);
        objective.getScore("  ").setScore(8);

        player.setScoreboard(scoreboard);
    }
}
