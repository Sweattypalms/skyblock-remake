package com.sweattypalms.skyblock.slayers.gui;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.ui.ClickableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SlayerTierClickableItem {
    public enum Difficulty {
        BEGINNER,
        STRONG,
        CHALLENGING,
        DEADLY,
        EXCRUCIATING,
    }

    private final Material material;
    private final String name;
    private final Difficulty difficulty;
    private final double health;
    private final double damage;
    private final List<String> lore;
    private final int reward;
    private final String rewardType;
    private final int costToStart;
    private final Consumer<Player> onClick;

    public SlayerTierClickableItem(Material material, String name, Difficulty difficulty, double health, double damage, List<String> lore, int reward, String rewardType, int costToStart, Consumer<Player> onClick) {
        this.material = material;
        this.name = name;
        this.difficulty = difficulty;
        this.health = health;
        this.damage = damage;
        this.lore = lore;
        this.reward = reward;
        this.rewardType = rewardType;
        this.costToStart = costToStart;
        this.onClick = onClick;
    }

    public ClickableItem forPlayer(Player player) {
        ItemStack item = new ItemStack(this.material);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;

        int tier = this.difficulty.ordinal() + 1;

        String romanTier = PlaceholderFormatter.toRomanNumeral(tier);
        String displayName = String.format("$a%s %s", this.name, romanTier);
        meta.setDisplayName(PlaceholderFormatter.format(displayName));

        List<String> lore = new ArrayList<>();
        lore.add("$8" + PlaceholderFormatter.capitalize(this.difficulty.name()));
        lore.add("$7");
        lore.add("$7Health: $c" + PlaceholderFormatter.formatDecimalCSV(this.health));
        lore.add("$7Damage: $c" + PlaceholderFormatter.formatDecimalCSV(this.damage));
        lore.add("$7");
        lore.addAll(PlaceholderFormatter.format(this.lore));
        lore.add("$7");
        lore.add("$7Reward: $d" + this.reward + " " + this.rewardType + " XP");
        lore.add(" $8+ Boss drops");
        lore.add("$7");
        lore.add("$7Cost to start: $6" + PlaceholderFormatter.formatDecimalCSV(this.costToStart) + " coins");
        lore.add("$7");
        lore.add("$eClick to slay!");

        lore = PlaceholderFormatter.format(lore);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return new ClickableItem(item, this.onClick);
    }


    public static class Builder {
        private Material material;
        private String name;
        private Difficulty difficulty;
        private double health;
        private double damage;
        private List<String> lore = new ArrayList<>();
        private int reward;
        private String rewardType;
        private int costToStart;
        private Consumer<Player> onClick;

        public Builder withMaterial(Material material) {
            this.material = material;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder withHealth(double health) {
            this.health = health;
            return this;
        }

        public Builder withDamage(double damage) {
            this.damage = damage;
            return this;
        }

        public Builder withLore(List<String> lore) {
            this.lore = lore;
            return this;
        }

        public Builder withReward(int reward) {
            this.reward = reward;
            return this;
        }

        public Builder withRewardType(String rewardType) {
            this.rewardType = rewardType;
            return this;
        }

        public Builder withCostToStart(int cost) {
            this.costToStart = cost;
            return this;
        }

        public Builder withOnClick(Consumer<Player> onClick) {
            this.onClick = onClick;
            return this;
        }

        public SlayerTierClickableItem build() {
            return new SlayerTierClickableItem(material, name, difficulty, health, damage, lore, reward, rewardType, costToStart, onClick);
        }
    }
}
