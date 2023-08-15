package com.sweattypalms.skyblock.core.stats;

import com.sweattypalms.skyblock.SkyBlock;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkyblockPlayer {
    static HashMap<UUID, SkyblockPlayer> players = new HashMap<>();
    @Getter
    private final Player player;
    @Getter
    Map<Stats, Double> baseStats = new HashMap<>();
    @Getter
    Map<Stats, Double> maxStats = new HashMap<>();
    @Getter
    Map<Stats, Double> liveStats = new HashMap<>();
    public SkyblockPlayer(Player player) {
        this.player = player;
        players.put(player.getUniqueId(), this);
        Arrays.stream(Stats.values()).toList().forEach(stat -> {
            this.baseStats.put(stat, stat.getBaseValue());
        });
        this.maxStats = new HashMap<>(this.baseStats);
        this.liveStats = new HashMap<>(this.baseStats);
        init();

        new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimerAsynchronously(SkyBlock.getInstance(), 0, 1);

    }

    public static SkyblockPlayer getSkyblockPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public static SkyblockPlayer newPlayer(Player player) {
        return new SkyblockPlayer(player);
    }

    private void init() {
        initHealth();
    }

    private void initHealth() {
        player.setHealthScale(20);
        AttributeInstance attribute = this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(this.maxStats.get(Stats.HEALTH));
        this.player.setHealth(this.maxStats.get(Stats.HEALTH));
    }

    private void tick() {
        this.manageStats();
        this.actionBar();
    }

    private void manageStats() {
        setLiveStat(Stats.HEALTH, this.player.getHealth());
        player.setFoodLevel(20);
        player.setSaturation(20);
    }

    public void setMaxStat(Stats stat, double value) {
        double maxStats = this.maxStats.get(stat);
        double liveStats = this.liveStats.get(stat);
        if (maxStats >= liveStats) {
            this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(value);
            this.player.setHealth(value);
        }
        this.maxStats.put(stat, value);
    }

    public void setLiveStat(Stats stat, double value) {
        this.liveStats.put(stat, value);
    }

    private void actionBar() {
        String space = "        ";
        String healthComponent = getHealthComponent();
        String defenceComponent = getDefenceComponent();

        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent(healthComponent + space + defenceComponent + space + getIntelligenceComponent()));
    }

    private String getHealthComponent() {
        double maxHealth = this.maxStats.get(Stats.HEALTH);
        String healthString = formatDouble(maxHealth);
        double currentHealth = this.player.getHealth();
        if (currentHealth > maxHealth) currentHealth = maxHealth;
        String currentHealthString = formatDouble(currentHealth);
        return ChatColor.RED + Stats.HEALTH.getSymbol() + " " + currentHealthString + " / " + healthString;
    }

    private String getDefenceComponent() {
        double maxDefence = this.maxStats.get(Stats.DEFENCE);
        String defenceString = formatDouble(maxDefence);
        return ChatColor.GREEN + Stats.DEFENCE.getSymbol() + " " + defenceString;
    }

    private String getIntelligenceComponent() {
        double maxIntelligence = this.maxStats.get(Stats.INTELLIGENCE);
        String intelligenceString = formatDouble(maxIntelligence);
        double currentIntelligence = this.liveStats.get(Stats.INTELLIGENCE);
        if (currentIntelligence > maxIntelligence) currentIntelligence = maxIntelligence;
        String currentIntelligenceString = formatDouble(currentIntelligence);

        return ChatColor.AQUA + Stats.INTELLIGENCE.getSymbol() + " " + currentIntelligenceString + " / " + intelligenceString;
    }

    private String formatDouble(double v1) {
        return String.format("%.1f", v1).replace(".0", "");
    }

}
