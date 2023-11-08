package com.sweattypalms.skyblock.core.enchants;

import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.ITriggerableEnchant;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import net.minecraft.util.Tuple;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.*;

public class EnchantManager {
    public static final Map<String, Enchantment> ENCHANTMENTS = new HashMap<>();


    public static void init() {
        Reflections reflections = new Reflections("com.sweattypalms.skyblock.core.enchants.impl");

        Set<Class<? extends Enchantment>> enchantmentClasses = reflections.getSubTypesOf(Enchantment.class);

        enchantmentClasses.forEach(clazz -> {

            if (Modifier.isAbstract(clazz.getModifiers())) {
                return;
            }

            try {
                Enchantment enchantment = clazz.getDeclaredConstructor().newInstance();
                ENCHANTMENTS.put(enchantment.getId(), enchantment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void registerEnchantment(Enchantment enchantment) {
        ENCHANTMENTS.put(enchantment.getId(), enchantment);
    }

    public static Enchantment getEnchantment(String id) throws IllegalArgumentException {
        if (!ENCHANTMENTS.containsKey(id)) {
            throw new IllegalArgumentException("Enchantment with id " + id + " does not exist!");
        }
        return ENCHANTMENTS.get(id);
    }

    public static void triggerEnchantment(String id, Event event) {
        Enchantment enchantment = ENCHANTMENTS.get(id);
//        if (enchantment instanceof ITriggerable triggerableEnchant) {
//            triggerableEnchant.trigger(event);
//        }
    }

    public static Optional<Integer> getEnchantment(ItemStack item, String id) {
        String enchants = PDCHelper.getString(item, "enchants");
        if (enchants.isEmpty()) {
            return Optional.empty();
        }

        enchants = enchants.replace("[", "").replace("]", "");
        List<String> enchantList = List.of(enchants.split(","));

        for (String enchant : enchantList) {
            String[] enchantData = enchant.split(":");
            if (enchantData[0].equals(id)) {
                return Optional.of(Integer.parseInt(enchantData[1]));
            }
        }

        return Optional.empty();
    }

    public static boolean hasEnchantment(ItemStack item, String id) {
        return getEnchantment(item, id).isPresent();
    }

    public static void addEnchantment(ItemStack item, String id, int level) {
        String enchants = PDCHelper.getString(item, "enchants");
        if (enchants.isEmpty()) {
            enchants = "[" + id + ":" + level + "]";
        } else {
            enchants = enchants.replace("[", "").replace("]", "");
            List<String> enchantList = getEnchantmentList(id, level, enchants);

            enchants = "[" + String.join(",", enchantList) + "]";
        }

        PDCHelper.setString(item, "enchants", enchants);
    }



    @NotNull
    private static List<String> getEnchantmentList(String id, int level, String enchants) {
        List<String> enchantList = new ArrayList<>(List.of(enchants.split(",")));

        boolean found = false;
        for (int i = 0; i < enchantList.size(); i++) {
            String enchant = enchantList.get(i);
            String[] enchantData = enchant.split(":");
            if (enchantData[0].equals(id)) {
                enchantList.set(i, id + ":" + level);
                found = true;
                break;
            }
        }

        if (!found) {
            enchantList.add(id + ":" + level);
        }
        return enchantList;
    }

    public static List<Tuple<Enchantment, Integer>> getEnchantments(ItemStack item) {
        String enchants = PDCHelper.getString(item, "enchants");
        if (enchants.isEmpty()) {
            return Collections.emptyList();
        }

        enchants = enchants.replace("[", "").replace("]", "");
        List<String> enchantList = List.of(enchants.split(","));

        List<Tuple<Enchantment, Integer>> enchantments = new ArrayList<>();
        for (String enchant : enchantList) {
            String[] enchantData = enchant.split(":");
            Enchantment enchantment = getEnchantment(enchantData[0]);
            if (enchantment != null) {
                enchantments.add(new Tuple<>(enchantment, Integer.parseInt(enchantData[1])));
            }
        }

        return enchantments;
    }


    public static void run(Player player, Event event) {
        ItemStack item = player.getInventory().getItemInMainHand();

        List<Tuple<Enchantment, Integer>> enchantments = getEnchantments(item);

        for (Tuple<Enchantment, Integer> enchantment : enchantments) {
            if (enchantment.a() instanceof ITriggerableEnchant triggerableEnchant && triggerableEnchant.should(event)) {
                triggerableEnchant.execute(enchantment.b(), event);
            }
        }

    }
}
