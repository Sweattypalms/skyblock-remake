package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.PlayerManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import com.sweattypalms.skyblock.core.player.sub.stats.StatsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import static com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter.formatDouble;

public class ActionBarManager extends PlayerManager {

    public ActionBarManager(SkyblockPlayer player) {
        super(player);
    }

    /**
     * Triggered every 20 ticks
     */
    public void tick() {
        String space = "     ";
        String healthComponent = getHealthComponent();
        String defenceComponent = getDefenceComponent();
        defenceComponent = defenceComponent.isEmpty() ? "" : space + defenceComponent;
        String intelligenceComponent = space + getIntelligenceComponent();

        this.skyblockPlayer.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent(healthComponent + defenceComponent + intelligenceComponent));
    }

    private String getHealthComponent() {
        StatsManager statsManager = this.skyblockPlayer.getStatsManager();
        double absorption = statsManager.getMaxStat(Stats.ABSORPTION);
        double maxHealth = statsManager.getMaxStat(Stats.HEALTH);
        String healthString = formatDouble(maxHealth);
        double currentHealth = this.skyblockPlayer.getPlayer().getHealth();
        if (currentHealth > maxHealth) currentHealth = maxHealth;
        String currentHealthString = formatDouble(currentHealth + absorption);

        ChatColor color = absorption > 0 ? ChatColor.GOLD : ChatColor.RED;
        return color + Stats.HEALTH.getSymbol() + " " + currentHealthString + " / " + healthString;
    }

    private String getDefenceComponent() {
        double maxDefence = this.skyblockPlayer.getStatsManager().getMaxStats().get(Stats.DEFENSE);
        if (maxDefence == 0) return "";
        String defenceString = formatDouble(maxDefence);
        return ChatColor.GREEN + Stats.DEFENSE.getSymbol() + " " + defenceString;
    }

    private String getIntelligenceComponent() {
        double maxIntelligence = this.skyblockPlayer.getStatsManager().getMaxStats().get(Stats.INTELLIGENCE);
        String intelligenceString = formatDouble(maxIntelligence);
        double currentIntelligence = this.skyblockPlayer.getStatsManager().getLiveStats().get(Stats.INTELLIGENCE);
        if (currentIntelligence > maxIntelligence) currentIntelligence = maxIntelligence;
        String currentIntelligenceString = formatDouble(currentIntelligence);

        return ChatColor.AQUA + Stats.INTELLIGENCE.getSymbol() + " " + currentIntelligenceString + " / " + intelligenceString;
    }
}
