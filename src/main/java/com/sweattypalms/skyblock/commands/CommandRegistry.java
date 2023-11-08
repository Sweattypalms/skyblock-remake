package com.sweattypalms.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CommandRegistry {
    private static CommandRegistry instance;
    private final Map<String, MethodContainer> commands = new HashMap<>();

    public CommandRegistry() {
        instance = this;
    }

    public static CommandRegistry getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Cannot call getInstance() before the plugin has been enabled!");
        }
        return instance;
    }

    public int getCommandsAmt() {
        return this.commands.size();
    }

    public void registerAll() {
        //Reflections reflections = new Reflections("com.sweattypalms.skyblock.commands.handlers");
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.sweattypalms.skyblock.commands.handlers"))
                .setScanners(Scanners.MethodsAnnotated));

        Set<Method> methods = reflections.getMethodsAnnotatedWith(Command.class);

        for (Method method : methods) {
            Object instance;
            try {
                instance = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                register(instance);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (MethodContainer container : commands.values()) {
            registerCommandWithBukkit(container);
        }
    }

    private void registerCommandWithBukkit(MethodContainer container) {
        try {
            Command cmdInfo = container.commandMethod.getAnnotation(Command.class);

            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(cmdInfo.name(), new BukkitCommand(cmdInfo.name()) {
                @Override
                public boolean execute(org.bukkit.command.CommandSender sender, String commandLabel, String[] args) {
                    // This method will be overridden regardless by the CommandPreProcess event
                    return true;
                }
            });

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.printf("Failed to register command %s%n", container.commandMethod.getName());
            throw new RuntimeException(e);
        }
    }

    private void register(Object obj) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command commandInfo = method.getAnnotation(Command.class);
                commands.put(commandInfo.name().toLowerCase(), new MethodContainer(obj, method));

                for (String alias : commandInfo.aliases()) {
                    commands.put(alias.toLowerCase(), new MethodContainer(obj, method));
                }
            }
        }
        // Run tab completer afterwards because the command may not be registered before.
        for (Method method : obj.getClass().getDeclaredMethods()) {
            // Register tab completer methods
            if (method.isAnnotationPresent(TabCompleter.class)) {
                TabCompleter completerInfo = method.getAnnotation(TabCompleter.class);
                MethodContainer container = commands.get(completerInfo.command().toLowerCase());

                if (container != null) {
                    container.tabCompleterMethod = method;
                } else {
                    // Handle case where there's a completer for a non-existent command
                    System.out.println(ChatColor.YELLOW + "[Warning] TabCompleter for non-existent command: " + completerInfo.command());
                }
            }
        }
    }

    public boolean executeCommand(Player player, String command, String[] args) {
        MethodContainer container = commands.get(command.toLowerCase());
        if (container == null) return false;

        Command cmdInfo = container.commandMethod.getAnnotation(Command.class);
        if (cmdInfo.op() && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        // First try to get a commandMethod with args of (Player player, String[] args)
        try {
            container.commandMethod.invoke(container.instance, player, args);
            return true;
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            // If first failed, try to get a commandMethod with args of (Player player)
            try {
                container.commandMethod.invoke(container.instance, player);
                return true;
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException _i) {
            }
        }

        return false;
    }

    public List<String> handleTabCompletion(CommandSender sender, String command, String[] args) {
        MethodContainer container = commands.get(command.toLowerCase());
        if (container != null && container.tabCompleterMethod != null) {
            try {
                return (List<String>) container.tabCompleterMethod.invoke(container.instance, sender, args);
            } catch (Exception e) {
                // Handle the exception gracefully, possibly log it for debugging
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public static class MethodContainer {
        public Method commandMethod;
        public Method tabCompleterMethod;
        public Object instance;

        public MethodContainer(Object instance, Method commandMethod) {
            this.instance = instance;
            this.commandMethod = commandMethod;
        }
    }
}
