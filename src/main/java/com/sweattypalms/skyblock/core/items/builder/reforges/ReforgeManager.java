package com.sweattypalms.skyblock.core.items.builder.reforges;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import org.reflections.Reflections;

import javax.annotation.Nullable;
import java.util.*;

public class ReforgeManager {
    public static Map<String, Reforge> REFORGES_LIST = new HashMap<>();

    public static void init(){
        Reflections reflections = new Reflections("com.sweattypalms.skyblock.core.items.builder.reforges.impl");
        Set<Class<? extends Reforge>> reforgeClasses = reflections.getSubTypesOf(Reforge.class);

        for (Class<? extends Reforge> clazz : reforgeClasses) {
            try {
                Reforge reforge = clazz.getDeclaredConstructor().newInstance();
                REFORGES_LIST.put(reforge.getName().toLowerCase(), reforge);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static List<String> getReforgeLore(@Nullable Reforge reforge) {
        if (reforge == null) return new ArrayList<>();

        List<String> lore = new ArrayList<>();
        if (reforge instanceof IAdvancedReforge advancedReforge) {
            lore.add("$9" + reforge.getName() + " Bonus");
            lore.addAll(advancedReforge.getLore());
        }
        lore = PlaceholderFormatter.format(lore);
        return lore;
    }

    public static Reforge getReforge(String name){
        if(REFORGES_LIST.containsKey(name)){
            return REFORGES_LIST.get(name);
        }
        return null;
    }
}
