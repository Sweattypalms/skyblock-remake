package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.commands.MainCommandHandler;
import com.sweattypalms.skyblock.commands.UtilCommandHandler;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;
import java.util.Set;

public final class SkyBlock extends JavaPlugin {

    private static SkyBlock instance;

    public boolean debug = true;

    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();
         registerListeners();
         registerCommands();
         registerItems();
         long end = System.currentTimeMillis() - start;
         if (debug) {
             getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Skyblock has been enabled! This took " + ChatColor.YELLOW + end + "ms");
         }

        Bukkit.getOnlinePlayers().forEach(SkyblockPlayer::newPlayer);
        configs();
    }

    @Override
    public void onDisable() {

    }

    public void registerListeners(){
        Reflections reflections = new Reflections("com.sweattypalms.skyblock.core.events.listeners");
        Set<Class<? extends Listener>> listenerClasses = reflections.getSubTypesOf(org.bukkit.event.Listener.class);

        for (Class<? extends org.bukkit.event.Listener> clazz : listenerClasses) {
            try {
                org.bukkit.event.Listener listenerInstance = clazz.getDeclaredConstructor().newInstance();
                Bukkit.getPluginManager().registerEvents(listenerInstance, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        List<Listener> listeners = List.of(
//                new UtilityListener(),
//                new EntityDamageEntityListener(),
//                new SkyblockDamageListener()
//        );
//        listeners.forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    @SuppressWarnings("ConstantConditions")
    public void registerCommands(){
        MainCommandHandler mainCommandHandler = new MainCommandHandler();
        getCommand("test").setExecutor(mainCommandHandler);
        getCommand("item").setExecutor(mainCommandHandler);
        getCommand("mob").setExecutor(mainCommandHandler);
        getCommand("stat").setExecutor(mainCommandHandler);

        UtilCommandHandler utilCommandHandler = new UtilCommandHandler();
        getCommand("gms").setExecutor(utilCommandHandler);
        getCommand("gmc").setExecutor(utilCommandHandler);
        getCommand("gmss").setExecutor(utilCommandHandler);
    }
    public void registerItems(){
        ItemManager.initSimpleItems();
    }

    private void configs(){
        File configuration = new File(this.getDataFolder(), "skyblock_config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configuration);
        if(!configuration.exists()){
            config.set("ratio", true);
            try {
                config.save(configuration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static SkyBlock getInstance() {
        return instance;
    }


}
