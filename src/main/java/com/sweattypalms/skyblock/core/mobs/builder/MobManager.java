package com.sweattypalms.skyblock.core.mobs.builder;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class MobManager {

    public static Map<String, Class<? extends ISkyblockMob>> MOBS_LIST = new HashMap<>();



    public static void init(){
        Reflections reflections = new Reflections("com.sweattypalms.skyblock.core.mobs.regions");
        for (Class<? extends ISkyblockMob> clazz : reflections.getSubTypesOf(ISkyblockMob.class)) {
            try {
                if (clazz.isInterface()) continue; // For abstract classes

                String id = clazz.getDeclaredField("ID").get(null).toString();
                MOBS_LIST.put(id, clazz);
            } catch (Exception e) {
                System.out.println("Failed to load mob: " + clazz.getName());
                System.out.println("Are you missing the ID field?");
            }
        }

        System.out.println("Loaded " + MOBS_LIST.size() + " mobs.");
    }

    public static SkyblockMob getInstance(String id) throws IllegalArgumentException{
        try {
            return new SkyblockMob(id, MOBS_LIST.get(id));
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown mob id: " + id);
        }
    }

//    public static SkyblockMob getMob(String id) throws IllegalArgumentException{
//        try {
//
//            return SkyblockMobTypes.valueOf(id.toUpperCase()).getInstance();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Unknown mob id: " + id);
//        }
//    }
}