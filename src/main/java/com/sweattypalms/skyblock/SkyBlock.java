package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.commands.CommandRegistry;
import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager;
import com.sweattypalms.skyblock.core.mobs.builder.MobManager;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.world.WorldManager;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;

import static com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager.REFORGES_LIST;


// TODO: Work on skills + loot.
public final class SkyBlock extends JavaPlugin {

    private static SkyBlock instance;

    public static SkyBlock getInstance() {
        return instance;
    }

    public boolean debug = true;

    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();

        this.registerServer();

        // Init the plugin asynchronously to speed up
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            registerCommands();
            registerListeners();
            registerCraft();
            configs();

            long end = System.currentTimeMillis() - start;
            System.out.println(ChatColor.GREEN + "Skyblock has been enabled! This took " + ChatColor.YELLOW + end + "ms");
        });

        drawAscii();

        Bukkit.getOnlinePlayers().forEach(SkyblockPlayer::newPlayer);
    }

    private void registerServer() {
        WorldManager.init();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, WorldManager::tick, 0L, 1L);

        new DragonManager();
    }

    private void registerCraft() {
        System.out.println("Registering items...");
        ItemManager.init();
        System.out.println(ChatColor.GREEN + "Successfully loaded " + ItemManager.ITEMS_LIST.size() + " items.");

        System.out.println("Registering mobs...");
        MobManager.init();
        System.out.println(ChatColor.GREEN + "Successfully loaded " + MobManager.MOBS_LIST.size() + " mobs.");

        System.out.println("Registering reforges...");
        ReforgeManager.init();
        System.out.println(ChatColor.GREEN + "Successfully loaded " + ReforgeManager.REFORGES_LIST.size() + " reforges.");

        System.out.println("Registering enchantments...");
         EnchantManager.init();
        System.out.println(ChatColor.GREEN + "Successfully loaded " + EnchantManager.ENCHANTMENTS.size() + " enchantments.");
    }

    public void registerListeners() {
        System.out.println("Registering listeners...");
        long start = System.currentTimeMillis();
        Reflections reflections = new Reflections("com.sweattypalms.skyblock");
        Set<Class<? extends Listener>> listenerClasses = reflections.getSubTypesOf(org.bukkit.event.Listener.class);

        for (Class<? extends org.bukkit.event.Listener> clazz : listenerClasses) {
            try {
                org.bukkit.event.Listener listenerInstance = clazz.getDeclaredConstructor().newInstance();
                Bukkit.getPluginManager().registerEvents(listenerInstance, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Registered listeners in %sms\n", System.currentTimeMillis() - start);
        System.out.println(ChatColor.GREEN + "Successfully registered " + listenerClasses.size() + " listeners.");
    }

    public void registerCommands() {
        System.out.println("Registering commands...");
        CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerAll();
        System.out.println(ChatColor.GREEN + "Successfully registered " + commandRegistry.getCommandsAmt() + " commands.");
    }

    private void configs() {
        File configuration = new File(this.getDataFolder(), "skyblock_config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configuration);
        config.set("ratio", true);
        try {
            config.save(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawAscii() {
        int width = 220;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setFont(new Font("SansSerif", Font.BOLD, 24));
        graphics.drawString("SWEATTY", 10, 20);


        System.out.println("\n\n\n");
        for (int y = 0; y < height; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < width; x++) {
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : "*");
            }
            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(stringBuilder);
        }
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "Skyblock has been disabled!");
    }
}
