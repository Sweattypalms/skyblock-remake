package com.sweattypalms.skyblock.api.sequence;

import com.sweattypalms.skyblock.SkyBlock;
import lombok.Getter;
import org.bukkit.Bukkit;

public class TimedAction {
    private final Runnable action;
    private final Runnable onFinish;
    @Getter
    private final int time;

    public TimedAction(Runnable action, int time, Runnable onFinish) {
        this.action = action;
        this.time = time;
        this.onFinish = onFinish;
    }
    public void perform() {
        action.run();
        Bukkit.getScheduler().runTaskLater(SkyBlock.getInstance(), onFinish, time);
    }
}
