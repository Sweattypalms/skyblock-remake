package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.PassiveAbility;
import com.sweattypalms.skyblock.core.items.builder.reforges.Reforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StatsManager {
    @Getter
    Map<Stats, Double> baseStats = new HashMap<>();
    @Getter
    Map<Stats, Double> maxStats = new HashMap<>();
    @Getter
    Map<Stats, Double> liveStats = new HashMap<>();
    int regenTick = 0;
    private final SkyblockPlayer player;

    public StatsManager(SkyblockPlayer player) {
        this.player = player;

        Arrays.stream(Stats.values()).toList().forEach(stat -> this.baseStats.put(stat, stat.getBaseValue()));
        this.maxStats = new HashMap<>(this.baseStats);
        this.liveStats = new HashMap<>(this.baseStats);
    }

    public void initHealth() {
        Player player = this.player.getPlayer();
        player.setHealthScale(20);
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(this.maxStats.get(Stats.HEALTH));
        player.setHealth(this.maxStats.get(Stats.HEALTH));
    }

    /**
     * {@link SkyblockPlayer#tick()} calls this function every 20 ticks.
     */
    public void tick() {
        regenTick++;
        manageStats();
        calculateStats();
        if (regenTick % 2 == 0) {
            regenerate();
            regenTick = 0;
        }
    }

    public void manageStats() {
        Player player = this.player.getPlayer();
        setLiveStat(Stats.HEALTH, player.getHealth());
        player.setFoodLevel(20);
        player.setSaturation(20);
    }

    public void calculateStats() {
/*
    Needed to be calculated:
        Talismans
        Pets
        Fairy Souls
        Slayer Bonuses
        Dungeons Bonuses
        Enchantments
        Potions
 */
        Map<Stats, Double> stats = new ConcurrentHashMap<>();


        this.baseStats.forEach((stat, baseValue) -> {
            final double[] value = {baseValue};
            /* -------- ARMOR & ITEMS -------- */
            player.getInventoryManager().getInventoryItems().forEach((skyblockItemType, itemStack) -> value[0] += getStat(stat, itemStack));
            stats.put(stat, value[0]);
            /* -------- ARMOR & ITEMS -------- */

        });

        double oldMaxHealth = this.maxStats.get(Stats.HEALTH);
        double oldCurrentHealth = this.liveStats.get(Stats.HEALTH);
        this.maxStats.putAll(stats);


        List<PassiveAbility> passiveAbilities = new ArrayList<>();

        // Check for passive abilities from the equipped items
        player.getInventoryManager().getInventoryItems().forEach((skyblockItemType, itemStack) -> {
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(itemStack);
            if (skyblockItem instanceof IHasAbility) {
                for (Ability ability : ((IHasAbility) skyblockItem).getAbilities()) {
                    if (ability instanceof PassiveAbility passiveAbility) {
                        passiveAbilities.add(passiveAbility);
                    }
                }
            }
        });

        // To not include duplicates
        Set<PassiveAbility> passiveAbilitiesSet = new HashSet<>(passiveAbilities);
        passiveAbilities.clear();

        // Apply the passive abilities
        for (PassiveAbility ability : passiveAbilitiesSet) {
            if (ability instanceof FullSetBonus fullSetBonus) {
                if (fullSetBonus.isFullSetEquipped(player)) {
                    ability.onTick(player);
                }
            } else {
                ability.onTick(player);
            }
        }

        // Apply the bonuses
        for (List<Bonus> bonuses : this.player.getBonusManager().getBonuses().values()) {
            for (Bonus bonus : bonuses) {
                if (!bonus.isExpired()) {
                    double value = stats.get(bonus.getStat());
                    value += bonus.getValue();
                    stats.put(bonus.getStat(), value);
                }
            }
        }

        healthCorrection(oldMaxHealth, oldCurrentHealth);
    }


    /**
     * Make the health full if it was full before
     *
     * @param oldMaxHealth     Old max health
     * @param oldCurrentHealth Old current health
     */
    private void healthCorrection(double oldMaxHealth, double oldCurrentHealth) {
        Player player = this.player.getPlayer();
        double maxHealth = this.maxStats.get(Stats.HEALTH);
        double currentHealth = this.liveStats.get(Stats.HEALTH);
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(maxHealth);
        double correction1Percent = oldMaxHealth * 0.01; // This is because of the funny
        if (oldCurrentHealth >= oldMaxHealth - correction1Percent) {
            player.setHealth(maxHealth);
        }
    }

    public void regenerate() {
        regenerateHealth();
        regenerateIntelligence();
    }

    private void regenerateHealth() {
        double maxHealth = this.maxStats.get(Stats.HEALTH);
        double currentHealth = this.liveStats.get(Stats.HEALTH);

        double baseRegen = (maxHealth * 0.01) + 1.5;
        double multiplier = this.maxStats.get(Stats.HEALTH_REGEN) / 100.0; // 100 is default
        double healthToRegenerate = baseRegen * multiplier;

        double newHealth = currentHealth + healthToRegenerate;

        newHealth = Math.min(newHealth, maxHealth);

        this.player.getPlayer().setHealth(newHealth);
    }

    private void regenerateIntelligence() {
        double maxMana = this.maxStats.get(Stats.INTELLIGENCE);
        double currentMana = this.liveStats.get(Stats.INTELLIGENCE);

        double baseRegen = (maxMana * 0.02);
        double multiplier = this.maxStats.getOrDefault(Stats.MANA_REGEN, 100.0) / 100.0; // Default to 1 if not set

        double manaToRegenerate = baseRegen * multiplier;

        double newMana = currentMana + manaToRegenerate;

        newMana = Math.min(newMana, maxMana);

        this.setLiveStat(Stats.INTELLIGENCE, newMana);
    }



    /* ----------- GETTERS / SETTERS ----------- */

    /**
     * <!> Override in DungeonPlayer <!>
     * Get stat from item
     *
     * @param stat Stat to get
     * @param item Item to get stat from
     * @return Stat value
     */
    private double getStat(Stats stat, ItemStack item) {
        double value = 0;
        String reforgeString = PDCHelper.getOrDefault(item, "reforge", "none");
        Reforge reforge = ReforgeManager.getReforge(reforgeString);
        if(reforge != null){
            Rarity rarity = Rarity.valueOf(PDCHelper.getOrDefault(item, "rarity", "COMMON"));
            value += reforge.getReforgeStats(rarity).getOrDefault(stat, 0.0);
        }
        value += PDCHelper.getDouble(item, "stat." + stat.name().toLowerCase());
        return value;
    }

    public void maxStats(Stats stat, double value) {
        double maxStats = this.maxStats.get(stat);
        double liveStats = this.liveStats.get(stat);
        if (maxStats >= liveStats && stat == Stats.HEALTH) {
            AttributeInstance attribute = this.player.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
            assert attribute != null;
            attribute.setBaseValue(value);
            this.player.getPlayer().setHealth(value);
        }
        this.maxStats.put(stat, value);
    }

    public void setMaxStat(Stats stat, double value) {
        this.maxStats.put(stat, value);
    }

    public void setLiveStat(Stats stat, double value) {
        this.liveStats.put(stat, value);
    }

    public void setBaseStat(Stats stat, double value) {
        this.baseStats.put(stat, value);
    }

    /**
     * Get the effective health of the player
     * @return double Effective health
     */
    public double getEffectiveHealth() {
        double health = this.liveStats.get(Stats.HEALTH);
        double defense = this.liveStats.get(Stats.DEFENSE);

        return health * (1 + (defense / 100));
    }
}
