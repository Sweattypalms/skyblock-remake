package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.Listeners.PlayerListeners;
import com.sweattypalms.skyblock.commands.MainCommandHandler;
import com.sweattypalms.skyblock.gui.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Skyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
         registerListeners();
         registerCommands();
         long end = System.currentTimeMillis() - start;
         getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Skyblock has been enabled! This took " + ChatColor.YELLOW + end + "ms");
    }

    @Override
    public void onDisable() {

    }

    public void registerListeners(){
       Bukkit.getServer().getPluginManager().registerEvents(new GuiListener() , this);
       Bukkit.getServer().getPluginManager().registerEvents(new PlayerListeners() , this);
    }

    public void registerCommands(){
        Objects.requireNonNull(getCommand("test")).setExecutor(new MainCommandHandler());
    }

}
