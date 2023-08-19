package com.sweattypalms.skyblock.core.player;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.player.sub.ActionBarManager;
import com.sweattypalms.skyblock.core.player.sub.BonusManager;
import com.sweattypalms.skyblock.core.player.sub.InventoryManager;
import com.sweattypalms.skyblock.core.player.sub.StatsManager;
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
    private final Player player;
    private BukkitTask tickRunnable;


    public SkyblockPlayer(Player player) {
        this.player = player;

        this.statsManager = new StatsManager(this);
        this.inventoryManager = new InventoryManager(this);
        this.bonusManager = new BonusManager(this);
        this.actionBarManager = new ActionBarManager(this);

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
        this.tickRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimerAsynchronously(SkyBlock.getInstance(), 0, 1);

        this.statsManager.initHealth();
    }

    private void tick() {
        if (this.player.isDead() || !this.player.isOnline()) {
            SkyblockPlayer.players.remove(this.player.getUniqueId());
            this.tickRunnable.cancel();
        }
        this.bonusManager.cleanupExpiredBonuses();
        this.statsManager.tick();
        this.actionBarManager.actionBar();
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

}

