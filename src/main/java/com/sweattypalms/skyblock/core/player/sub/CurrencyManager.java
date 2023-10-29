package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CurrencyManager {

    private final SkyblockPlayer player;

    private double bankBalance;
    private double purseBalance;
    private double bits;
    public CurrencyManager(SkyblockPlayer player) {
        this.player = player;
    }

    public void addBankBalance(double amount) {
        this.bankBalance += amount;
    }

    public void addPurseBalance(double amount) {
        this.purseBalance += amount;
    }

    public void addBits(double amount) {
        this.bits += amount;
    }

}
