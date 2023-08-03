package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.stats.Stats;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public class SimpleSkyblockItem extends SkyblockItem {
    public SimpleSkyblockItem(Builder<?> builder) {
        super(
                builder.id,
                builder.displayName,
                builder.material,
                builder.staticLore,
                builder.stats,
                builder.baseRarity,
                builder.itemType,
                builder.abilities
        );
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    @Override
    public List<Ability> getAbilities() {
        return null;
    }

    public static class Builder<T extends Builder<T>>{
        private String id;
        private String displayName;
        private Material material;
        private List<String> staticLore;
        private Map<Stats, Double> stats;
        private Rarity baseRarity;
        private SkyblockItemType itemType;
        private List<Ability> abilities;

        public T id(String id) {
            this.id = id;
            return self();
        }

        public T displayName(String displayName) {
            this.displayName = displayName;
            return self();
        }

        public T material(Material material) {
            this.material = material;
            return self();
        }

        public T staticLore(List<String> staticLore) {
            this.staticLore = staticLore;
            return self();
        }

        public T stats(Map<Stats, Double> stats) {
            this.stats = stats;
            return self();
        }

        public T baseRarity(Rarity baseRarity) {
            this.baseRarity = baseRarity;
            return self();
        }

        public T itemType(SkyblockItemType itemType) {
            this.itemType = itemType;
            return self();
        }

        public T abilities(List<Ability> abilities) {
            this.abilities = abilities;
            return self();
        }

        // this is unchecked, but safe in this context
        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        public SimpleSkyblockItem build() {
            return new SimpleSkyblockItem(this);
        }
    }
}
