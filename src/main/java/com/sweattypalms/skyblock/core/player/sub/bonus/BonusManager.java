package com.sweattypalms.skyblock.core.player.sub.bonus;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BonusManager {

    private final Map<String, List<BonusWithCleanup>> bonuses = new ConcurrentHashMap<>();


    /**
     * Returns a map of all bonuses, organized by their keys.
     *
     * @return A map where the key is the bonus key and the value is a list of associated Bonus objects.
     */
    public Map<String, List<Bonus>> getBonuses() {
        return bonuses.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(BonusWithCleanup::getBonus)
                                .collect(Collectors.toList())
                ));
    }

    public BonusManager(SkyblockPlayer player) {}

    /**
     * <!> This will add the bonus to the stat <!>
     *
     * @param key  Key of the bonus, ex) "aote.speed"
     * @param bonus Bonus to add
     */
    public void setBonus(String key, Bonus bonus) {
        setBonus(key, bonus, null);
    }

    /**
     * <!> This will add the bonus to the stat with a cleanup function <!>
     *
     * @param key  Key of the bonus, ex) "aote.speed"
     * @param bonus Bonus to add
     * @param cleanupFunction Optional cleanup function to be called when the bonus is removed or expires
     */
    public void setBonus(String key, Bonus bonus, Consumer<Bonus> cleanupFunction) {
        List<BonusWithCleanup> currentBonuses = this.bonuses.getOrDefault(key, new ArrayList<>());

        if (currentBonuses.isEmpty() || currentBonuses.get(0).bonus().isExpired()) {
            currentBonuses.clear();
            currentBonuses.add(new BonusWithCleanup(bonus, cleanupFunction));
        } else {
            currentBonuses.get(0).bonus().setExpiryTime(bonus.getExpiryTime());
        }

        this.bonuses.put(key, currentBonuses);
    }

    public void setStackingBonus(String key, Bonus bonus) {
        setStackingBonus(key, bonus, null);
    }

    public void setStackingBonus(String key, Bonus bonus, Consumer<Bonus> cleanupFunction) {
        List<BonusWithCleanup> currentBonuses = this.bonuses.getOrDefault(key, new ArrayList<>());
        currentBonuses.add(new BonusWithCleanup(bonus, cleanupFunction));
        this.bonuses.put(key, currentBonuses);
    }

    public void cleanupExpiredBonuses() {
        for (String key : bonuses.keySet()) {
            List<BonusWithCleanup> bonusList = bonuses.get(key);
            List<BonusWithCleanup> expiredBonuses = new ArrayList<>();

            for (BonusWithCleanup bonusWithCleanup : bonusList) {
                if (bonusWithCleanup.bonus().isExpired()) {
                    expiredBonuses.add(bonusWithCleanup);
                }
            }

            for (BonusWithCleanup expiredBonus : expiredBonuses) {
                if (expiredBonus.cleanupFunction() != null) {
                    expiredBonus.cleanupFunction().accept(expiredBonus.bonus());
                }
            }

            bonusList.removeAll(expiredBonuses);
        }
    }

    public List<Bonus> getBonuses(String key) {
        return this.bonuses.getOrDefault(key, new ArrayList<>()).stream()
                .map(BonusWithCleanup::bonus)
                .toList();
    }

    private record BonusWithCleanup(@Getter Bonus bonus, @Getter Consumer<Bonus> cleanupFunction) {}
}