package com.sweattypalms.skyblock.core.player.sub.bonus;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class BonusManager {

    private final Map<String, List<Bonus>> bonuses = new ConcurrentHashMap<>();
    public BonusManager(SkyblockPlayer player) {}

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

    public List<Bonus> getBonuses(String key) {
        return this.bonuses.getOrDefault(key, new ArrayList<>());
    }
}
