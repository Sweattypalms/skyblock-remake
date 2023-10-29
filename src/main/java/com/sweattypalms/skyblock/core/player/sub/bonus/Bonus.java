package com.sweattypalms.skyblock.core.player.sub.bonus;

import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import lombok.Getter;

@Getter
public class Bonus {
    private final Stats stat;
    private final double value;
    private long expiryTime;
    public Bonus(Stats stat, double value, long durationMillis) {
        this.stat = stat;
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + durationMillis;
    }

    public void setCanExpire(boolean canExpire) {
        if (!canExpire) this.expiryTime = Long.MAX_VALUE;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
    public boolean isExpired() {
        return System.currentTimeMillis() > this.expiryTime;
    }
}
