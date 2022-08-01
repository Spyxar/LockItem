package com.spyxar.lockitem;

import java.io.File;
import java.io.IOException;

import Commands.MainCommand;

import TabCompleters.MainTabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.DropEventListener;
import Listeners.PlaceEventListener;

public class LockItem extends JavaPlugin
{
    private static LockItem pluginInstance;

    public static ItemLocker itemLocker;

    public static String pluginVersion;

    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable()
    {
        pluginInstance = this;
        pluginVersion = "v" + pluginInstance.getDescription().getVersion();
        createCustomConfig();
        itemLocker = new ItemLocker(this);
        getServer().getPluginManager().registerEvents(new DropEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlaceEventListener(), this);
        this.getCommand("lockitem").setExecutor(new MainCommand());
        this.getCommand("lockitem").setTabCompleter(new MainTabCompleter());
    }

    @Override
    public void onDisable() {}

    public static LockItem getInstance()
    {
        return pluginInstance;
    }

    public FileConfiguration getCustomConfig()
    {
        return this.customConfig;
    }

    public void createCustomConfig()
    {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists())
        {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        customConfig = new YamlConfiguration();
        try
        {
            customConfig.load(customConfigFile);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }
}