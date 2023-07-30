package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.commands.MainCommandHandler;
import com.sweattypalms.skyblock.commands.UtilCommandHandler;
import com.sweattypalms.skyblock.core.listeners.PlayerJoinListener;
import com.sweattypalms.skyblock.core.stats.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class SkyBlock extends JavaPlugin {

    private static SkyBlock instance;

    public boolean debug = true;

    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();
         registerListeners();
         registerCommands();
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
                new PlayerJoinListener()
        );
        listeners.forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    @SuppressWarnings("ConstantConditions")
    public void registerCommands(){
        MainCommandHandler mainCommandHandler = new MainCommandHandler();
        getCommand("test").setExecutor(mainCommandHandler);

        UtilCommandHandler utilCommandHandler = new UtilCommandHandler();
        getCommand("gms").setExecutor(utilCommandHandler);
        getCommand("gmc").setExecutor(utilCommandHandler);
        getCommand("gmss").setExecutor(utilCommandHandler);
    }

    public static SkyBlock getInstance() {
        return instance;
    }


}
