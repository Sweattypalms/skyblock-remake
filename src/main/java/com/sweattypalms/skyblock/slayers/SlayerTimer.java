package com.sweattypalms.skyblock.slayers;

import com.sweattypalms.skyblock.api.Hologram;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import org.bukkit.ChatColor;

public class SlayerTimer {
    private final SkyblockMob host;
    private Hologram hologram;

    public SlayerTimer(SkyblockMob host) {
        this.host = host;

        spawnTimerHologram();
    }

    private void spawnTimerHologram() {

        if (this.host.getEntityInstance() == null) {
            this.host.onSpawn(() -> {
                this.hologram = new Hologram(
                        ChatColor.RED + "04:00",
                        this.host,
                        0.2
                );
                hologram.start();
            });
        } else {
            this.hologram = new Hologram(
                    ChatColor.RED + "04:00",
                    this.host,
                    0.2
            );
            hologram.start();
        }
    }

    public void updateTimer(long timeLeft) {
        hologram.updateName(ChatColor.RED + String.format("%02d:%02d", timeLeft / 60, timeLeft % 60));
    }
}
