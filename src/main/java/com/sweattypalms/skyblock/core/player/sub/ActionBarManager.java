package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import static com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter.formatDouble;

public class ActionBarManager {
    private final SkyblockPlayer player;

    public ActionBarManager(SkyblockPlayer player) {
        this.player = player;
    }


    /**
     * Triggered every 20 ticks
     */
    public void tick() {
        String space = "        ";
        String healthComponent = getHealthComponent();
        String defenceComponent = getDefenceComponent();
        defenceComponent = defenceComponent.equals("") ? "" : space + defenceComponent;
        String intelligenceComponent = space + getIntelligenceComponent();

        this.player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent(healthComponent + defenceComponent + intelligenceComponent));
    }

    private String getHealthComponent() {
        double maxHealth = this.player.getStatsManager().getMaxStats().get(Stats.HEALTH);
        String healthString = formatDouble(maxHealth);
        double currentHealth = this.player.getPlayer().getHealth();
        if (currentHealth > maxHealth) currentHealth = maxHealth;
        String currentHealthString = formatDouble(currentHealth);
        return ChatColor.RED + Stats.HEALTH.getSymbol() + " " + currentHealthString + " / " + healthString;
    }

    private String getDefenceComponent() {
        double maxDefence = this.player.getStatsManager().getMaxStats().get(Stats.DEFENSE);
        if (maxDefence == 0) return "";
        String defenceString = formatDouble(maxDefence);
        return ChatColor.GREEN + Stats.DEFENSE.getSymbol() + " " + defenceString;
    }

    private String getIntelligenceComponent() {
        double maxIntelligence = this.player.getStatsManager().getMaxStats().get(Stats.INTELLIGENCE);
        String intelligenceString = formatDouble(maxIntelligence);
        double currentIntelligence = this.player.getStatsManager().getLiveStats().get(Stats.INTELLIGENCE);
        if (currentIntelligence > maxIntelligence) currentIntelligence = maxIntelligence;
        String currentIntelligenceString = formatDouble(currentIntelligence);

        return ChatColor.AQUA + Stats.INTELLIGENCE.getSymbol() + " " + currentIntelligenceString + " / " + intelligenceString;
    }


}
