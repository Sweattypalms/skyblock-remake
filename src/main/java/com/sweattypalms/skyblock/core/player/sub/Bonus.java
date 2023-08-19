package com.sweattypalms.skyblock.core.player.sub;

public class Bonus {
    private final Stats stat;
    private final double value;
    private long expiryTime;

    public Bonus(Stats stat, double value, long durationMillis) {
        this.stat = stat;
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + durationMillis;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
    public boolean isExpired() {
        return System.currentTimeMillis() > this.expiryTime;
    }

    public Stats getStat() {
        return stat;
    }

    public double getValue() {
        return value;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
