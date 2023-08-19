package com.sweattypalms.skyblock.core.player;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.PassiveAbility;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter.formatDouble;

public class SkyblockPlayer {
    static HashMap<UUID, SkyblockPlayer> players = new HashMap<>();
    @Getter
    private final Random random = new Random();
    @Getter
    Map<Stats, Double> baseStats = new HashMap<>();
    @Getter
    Map<Stats, Double> maxStats = new HashMap<>();
    @Getter
    Map<Stats, Double> liveStats = new HashMap<>();
    @Getter
    Map<String, List<Bonus>> bonuses = new HashMap<>();
    @Getter
    private Player player;
    private BukkitTask tickRunnable;

    public SkyblockPlayer(Player player) {
        this.player = player;
        players.put(player.getUniqueId(), this);
        Arrays.stream(Stats.values()).toList().forEach(stat -> {
            this.baseStats.put(stat, stat.getBaseValue());
        });
        this.maxStats = new HashMap<>(this.baseStats);
        this.liveStats = new HashMap<>(this.baseStats);
        init();
    }

    public static SkyblockPlayer getSkyblockPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public static SkyblockPlayer newPlayer(Player player) {
        return new SkyblockPlayer(player);
    }

    private void init() {

        this.tickRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimerAsynchronously(SkyBlock.getInstance(), 0, 1);

        initHealth();
    }

    private void initHealth() {
        player.setHealthScale(20);
        AttributeInstance attribute = this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(this.maxStats.get(Stats.HEALTH));
        this.player.setHealth(this.maxStats.get(Stats.HEALTH));
    }

    private void tick() {
        if (this.player.isDead() || !this.player.isOnline()) {
            SkyblockPlayer.players.remove(this.player.getUniqueId());
            this.tickRunnable.cancel();
        }

        this.cleanupExpiredBonuses();
        this.manageStats();
        this.calculateStats();
        this.actionBar();
    }

    private void manageStats() {
//        TODO: Add regeneration. (Health, Mana)
        setLiveStat(Stats.HEALTH, this.player.getHealth());
        player.setFoodLevel(20);
        player.setSaturation(20);
    }

    private void calculateStats() {
/*
    Needed to be calculated:
        Talismans
        Pets
        Fairy Souls
        Slayer Bonuses
        Dungeons Bonuses
        Reforges
        Enchantments
        Potions
 */
        Map<Stats, Double> stats = new HashMap<>();


        this.baseStats.forEach((stat, baseValue) -> {
            final double[] value = {baseValue};
            /* -------- ARMOR & ITEMS -------- */
            getInventoryItems().forEach((skyblockItemType, itemStack) -> {
                value[0] += getStat(stat, itemStack);
            });
            stats.put(stat, value[0]);
            /* -------- ARMOR & ITEMS -------- */

            /* TODO: Add passive abilities and others */

        });

        double oldMaxHealth = this.maxStats.get(Stats.HEALTH);
        double oldCurrentHealth = this.liveStats.get(Stats.HEALTH);
        this.maxStats.putAll(stats);
        healthCorrection(oldMaxHealth, oldCurrentHealth);


        List<PassiveAbility> passiveAbilities = new ArrayList<>();

        // Check for passive abilities from the equipped items
        getInventoryItems().forEach((skyblockItemType, itemStack) -> {
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
                if (fullSetBonus.isFullSetEquipped(this)) {
                    ability.onTick(this);
                }
            } else {
                ability.onTick(this);
            }
        }

        // Apply the bonuses
        for (List<Bonus> bonuses : this.bonuses.values()) {
            for (Bonus bonus : bonuses) {
                if (!bonus.isExpired()) {
                    double value = stats.get(bonus.getStat());
                    value += bonus.getValue();
                    stats.put(bonus.getStat(), value);
                }
            }
        }
//        for (Bonus bonus : bonuses.values()) {
//            if (!bonus.isExpired()) {
//                double value = stats.get(bonus.getStat());
//                value += bonus.getValue();
//                this.maxStats.put(bonus.getStat(), this.maxStats.get(bonus.getStat()) + bonus.getValue());
//            }
//        }
    }

    /**
     * Make the health full if it was full before
     *
     * @param oldMaxHealth     Old max health
     * @param oldCurrentHealth Old current health
     */
    private void healthCorrection(double oldMaxHealth, double oldCurrentHealth) {
        double maxHealth = this.maxStats.get(Stats.HEALTH);
        double currentHealth = this.liveStats.get(Stats.HEALTH);
        AttributeInstance attribute = this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(maxHealth);
        double correction1Percent = oldMaxHealth * 0.01; // This is because of the double precision
        if (oldCurrentHealth >= oldMaxHealth - correction1Percent) {
            this.player.setHealth(maxHealth);
        }
    }

    /**
     * <!> Override in DungeonPlayer <!>
     * Get stat from item
     *
     * @param stat Stat to get
     * @param item Item to get stat from
     * @return Stat value
     */
    private double getStat(Stats stat, ItemStack item) {
        /* Add reforges */
        return PDCHelper.getDouble(item, stat.name().toLowerCase());
    }

    public void maxStats(Stats stat, double value) {
        double maxStats = this.maxStats.get(stat);
        double liveStats = this.liveStats.get(stat);
        if (maxStats >= liveStats && stat == Stats.HEALTH) {
            AttributeInstance attribute = this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            assert attribute != null;
            attribute.setBaseValue(value);
            this.player.setHealth(value);
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

    private void actionBar() {
        String space = "        ";
        String healthComponent = getHealthComponent();
        String defenceComponent = getDefenceComponent();
        defenceComponent = defenceComponent.equals("") ? "" : space + defenceComponent;
        String intelligenceComponent = space + getIntelligenceComponent();

        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent(healthComponent + defenceComponent + intelligenceComponent));
    }

    private String getHealthComponent() {
        double maxHealth = this.maxStats.get(Stats.HEALTH);
        String healthString = formatDouble(maxHealth);
        double currentHealth = this.player.getHealth();
        if (currentHealth > maxHealth) currentHealth = maxHealth;
        String currentHealthString = formatDouble(currentHealth);
        return ChatColor.RED + Stats.HEALTH.getSymbol() + " " + currentHealthString + " / " + healthString;
    }

    private String getDefenceComponent() {
        double maxDefence = this.maxStats.get(Stats.DEFENCE);
        if (maxDefence == 0) return "";
        String defenceString = formatDouble(maxDefence);
        return ChatColor.GREEN + Stats.DEFENCE.getSymbol() + " " + defenceString;
    }

    private String getIntelligenceComponent() {
        double maxIntelligence = this.maxStats.get(Stats.INTELLIGENCE);
        String intelligenceString = formatDouble(maxIntelligence);
        double currentIntelligence = this.liveStats.get(Stats.INTELLIGENCE);
        if (currentIntelligence > maxIntelligence) currentIntelligence = maxIntelligence;
        String currentIntelligenceString = formatDouble(currentIntelligence);

        return ChatColor.AQUA + Stats.INTELLIGENCE.getSymbol() + " " + currentIntelligenceString + " / " + intelligenceString;
    }



    public SkyblockItem getSkyblockItemInHand() {
        ItemStack itemStack = this.player.getInventory().getItemInMainHand();
        String id = PDCHelper.getString(itemStack, "id");
        return ItemManager.ITEMS_LIST.get(id);
    }

    public HashMap<SkyblockItemType, ItemStack> getInventoryItems() {
        HashMap<SkyblockItemType, ItemStack> items = new HashMap<>();
        final boolean[] handSlotVisited = {false};
        Arrays.stream(SkyblockItemType.values()).toList().forEach(skyblockItemType -> {
            if (skyblockItemType.getSlot() == null) return;
            if (skyblockItemType.getSlot() == EquipmentSlot.HAND && handSlotVisited[0]) return;
            handSlotVisited[0] = true;
            ItemStack itemStack = this.player.getInventory().getItem(skyblockItemType.getSlot());
            SkyblockItem skyblockItemFromItemstack = SkyblockItem.fromItemStack(itemStack);
            List<SkyblockItemType> armorTypes = new ArrayList<>(List.of(
                    SkyblockItemType.HELMET,
                    SkyblockItemType.CHESTPLATE,
                    SkyblockItemType.LEGGINGS,
                    SkyblockItemType.BOOTS
            ));
            if (
                    skyblockItemFromItemstack != null
                            && armorTypes.contains(skyblockItemFromItemstack.getItemType())
                            && skyblockItemType.getSlot().equals(EquipmentSlot.HAND)) return;
            if (PDCHelper.hasString(itemStack, "id"))
                items.put(skyblockItemType, itemStack);
        });
        return items;
    }

    public HashMap<SkyblockItemType, SkyblockItem> getInventorySkyblockItems() {
        HashMap<SkyblockItemType, SkyblockItem> items = new HashMap<>();
        final boolean[] handSlotVisited = {false};
        Arrays.stream(SkyblockItemType.values()).toList().forEach(skyblockItemType -> {
            if (skyblockItemType.getSlot() == null) return;
            if (skyblockItemType.getSlot() == EquipmentSlot.HAND && handSlotVisited[0]) return;
            handSlotVisited[0] = true;
            ItemStack itemStack = this.player.getInventory().getItem(skyblockItemType.getSlot());
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(itemStack);
            if (skyblockItem != null)
                items.put(skyblockItemType, skyblockItem);
        });
        return items;
    }

    public FullSetBonus getEquippedFullSetBonus() {
        for (SkyblockItem skyblockItem : getInventorySkyblockItems().values()) {
            if (skyblockItem instanceof IHasAbility) {
                for (Ability ability : ((IHasAbility) skyblockItem).getAbilities()) {
                    if (ability instanceof FullSetBonus fullSetBonus) {
                        if (fullSetBonus.isFullSetEquipped(this)) {
                            return fullSetBonus;
                        }
                    }
                }
            }
        }
        return null;
    }

}

