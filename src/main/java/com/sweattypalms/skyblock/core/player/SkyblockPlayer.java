
package com.sweattypalms.skyblock.core.player;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.helpers.DamageCalculator;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.player.sub.*;
import com.sweattypalms.skyblock.core.player.sub.bonus.BonusManager;
import com.sweattypalms.skyblock.core.player.sub.scoreboard.ScoreboardManager;
import com.sweattypalms.skyblock.core.player.sub.skillz.SkillManager;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import com.sweattypalms.skyblock.core.player.sub.stats.StatsManager;
import com.sweattypalms.skyblock.core.regions.RegionManager;
import com.sweattypalms.skyblock.core.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Getter
public class SkyblockPlayer {
    private static final HashMap<UUID, SkyblockPlayer> players = new HashMap<>();

    private final Random random = new Random();
    private final StatsManager statsManager;
    private final InventoryManager inventoryManager;
    private final BonusManager bonusManager;
    private final CooldownManager cooldownManager;
    private final ActionBarManager actionBarManager;
    private final ScoreboardManager scoreboardManager;
    private final SlayerManager slayerManager;
    private final SkillManager skillManager;
    private final CurrencyManager currencyManager;
    private final Player player;
    private BukkitTask tickRunnable;

    @Getter @Setter
    private Regions lastKnownRegion = null;

    /**
     * This is used to get last use time of abilities
     */
    private final Map<String, Long> cooldowns = new HashMap<>();

    public SkyblockPlayer(Player player) {
        this.player = player;

        this.statsManager = new StatsManager(this);
        this.inventoryManager = new InventoryManager(this);
        this.bonusManager = new BonusManager(this);
        this.cooldownManager = new CooldownManager(this);
        this.actionBarManager = new ActionBarManager(this);
        this.slayerManager = new SlayerManager(this);
        this.skillManager = new SkillManager(this);
        this.currencyManager = new CurrencyManager(this);
        // Should be last because it uses the other managers
        this.scoreboardManager = new ScoreboardManager(this);

        players.put(player.getUniqueId(), this);
        init();
    }

    public static SkyblockPlayer getSkyblockPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public static void newPlayer(Player player) {
        new SkyblockPlayer(player);
    }

    private void init() {

        String displayName = "$c[OWNER] " + this.player.getName();
        displayName = PlaceholderFormatter.format(displayName);
        this.player.setDisplayName(displayName);

        this.tickRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimerAsynchronously(SkyBlock.getInstance(), 0, 1);

        this.statsManager.initHealth();
    }

    private int tickCount = 0;
    private void tick() {
        if (!this.player.isOnline()) {
            SkyblockPlayer.players.remove(this.player.getUniqueId());
            this.tickRunnable.cancel();
        }
        if (this.player.isDead()){
            return;
        }
        this.tickCount++;

        if (this.tickCount % 20 != 0) return;

        this.bonusManager.cleanupExpiredBonuses();
        this.statsManager.tick();
        this.actionBarManager.tick();

        this.scoreboardManager.updateScoreboard();
        RegionManager.updatePlayerRegion(this);
    }

    /**
     *  Damage the player
     *  TODO: Add various damage types
     * @param damage Damage to deal (With reduction)
     */
    public void damage(double damage) {
        if (this.player.getGameMode() != GameMode.SURVIVAL)
            return;
        this.player.setHealth(
                Math.max(
                        this.player.getHealth() - damage,
                        0
                )
        );
    }

    public void damageWithReduction(double damage){
        double defense = this.statsManager.getMaxStats().get(Stats.DEFENSE);
        double finalDamage = DamageCalculator.calculateDamageReduction(defense, damage);
        this.damage(finalDamage);
    }

    /**
     * Send auto formatted message to player
     * @param s Message to send
     */
    public void sendMessage(String s) {
        this.player.sendMessage(PlaceholderFormatter.format(s));
    }

    public long getLastUseTime(String key){
        return this.cooldowns.getOrDefault(key, 0L);
    }

    public void setLastUseTime(String key, long time){
        this.cooldowns.put(key, time);
    }
}
