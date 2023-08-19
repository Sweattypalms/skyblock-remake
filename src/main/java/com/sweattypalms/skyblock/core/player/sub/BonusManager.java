package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BonusManager {
    private final SkyblockPlayer player;

    @Getter
    private Map<String, List<Bonus>> bonuses = new HashMap<>();

    public BonusManager(SkyblockPlayer player) {
        this.player = player;
    }

    /**
     * <!> This will add the bonus to the stat <!>
     *
     * @param key  Key of the bonus, ex) "aote.speed"
     * @param bonus Bonus to add
     */
    public void setBonus(String key, Bonus bonus) {
        List<Bonus> currentBonuses = this.bonuses.getOrDefault(key, new ArrayList<>());

        if (currentBonuses.isEmpty() || currentBonuses.get(0).isExpired()) {
            currentBonuses.clear();
            currentBonuses.add(bonus);
        } else {
            currentBonuses.get(0).setExpiryTime(bonus.getExpiryTime());
        }

        this.bonuses.put(key, currentBonuses);
    }


    public void setStackingBonus(String key, Bonus bonus) {
        List<Bonus> currentBonuses = this.bonuses.getOrDefault(key, new ArrayList<>());
        currentBonuses.add(bonus);
        this.bonuses.put(key, currentBonuses);
    }

    public void cleanupExpiredBonuses() {
        for (String key : bonuses.keySet()) {
            bonuses.get(key).removeIf(Bonus::isExpired);
        }
    }

}
