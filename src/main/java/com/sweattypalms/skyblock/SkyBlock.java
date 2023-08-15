package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.commands.MainCommandHandler;
import com.sweattypalms.skyblock.commands.UtilCommandHandler;
import com.sweattypalms.skyblock.core.events.listeners.SkyblockDamageListener;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.events.listeners.EntityDamageEntityListener;
import com.sweattypalms.skyblock.core.events.listeners.PlayerJoinListener;
import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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
    }

    @Override
    public void onDisable() {

    }

    public void registerListeners(){
        List<Listener> listeners = List.of(
                new PlayerJoinListener(),
                new EntityDamageEntityListener(),
                new SkyblockDamageListener()
        );
        listeners.forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    @SuppressWarnings("ConstantConditions")
    public void registerCommands(){
        MainCommandHandler mainCommandHandler = new MainCommandHandler();
        getCommand("test").setExecutor(mainCommandHandler);
        getCommand("item").setExecutor(mainCommandHandler);
        getCommand("mob").setExecutor(mainCommandHandler);

        UtilCommandHandler utilCommandHandler = new UtilCommandHandler();
        getCommand("gms").setExecutor(utilCommandHandler);
        getCommand("gmc").setExecutor(utilCommandHandler);
        getCommand("gmss").setExecutor(utilCommandHandler);
    }
    public void registerItems(){
        ItemManager.initSimpleItems();
    }

    public static SkyBlock getInstance() {
        return instance;
    }


}
