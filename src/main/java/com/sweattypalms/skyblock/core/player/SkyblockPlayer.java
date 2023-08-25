package com.sweattypalms.skyblock.core.player;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.helpers.DamageCalculator;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.player.sub.*;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class SkyblockPlayer {
    private static final HashMap<UUID, SkyblockPlayer> players = new HashMap<>();

    @Getter
    private final Random random = new Random();
    @Getter
    private final StatsManager statsManager;
    @Getter
    private final InventoryManager inventoryManager;
    @Getter
    private final BonusManager bonusManager;
    @Getter
    private final ActionBarManager actionBarManager;
    @Getter
    private final ScoreboardManager scoreboardManager;
    @Getter
    private final Player player;
    private BukkitTask tickRunnable;


    public SkyblockPlayer(Player player) {
        this.player = player;

        this.statsManager = new StatsManager(this);
        this.inventoryManager = new InventoryManager(this);
        this.bonusManager = new BonusManager(this);
        this.actionBarManager = new ActionBarManager(this);
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
    private void tick() {
        if (!this.player.isOnline()) {
            SkyblockPlayer.players.remove(this.player.getUniqueId());
            this.tickRunnable.cancel();
        }
        if(this.player.isDead()){
            return;
        }
        this.bonusManager.cleanupExpiredBonuses();
        this.statsManager.tick();
        this.actionBarManager.actionBar();

        this.scoreboardManager.updateScoreboard();
    }

    /**
     *  Damage the player
     *  TODO: Add various damage types
     * @param damage Damage to deal (With reduction)
     */
    public void damage(double damage) {
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


}

