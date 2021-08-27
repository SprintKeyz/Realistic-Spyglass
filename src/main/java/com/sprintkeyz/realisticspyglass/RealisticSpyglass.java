package com.sprintkeyz.realisticspyglass;

import com.sprintkeyz.realisticspyglass.spyglass.PlayerLooking;
import com.sprintkeyz.realisticspyglass.spyglass.SpyglassCommand;
import com.sprintkeyz.realisticspyglass.spyglass.Sun;
import org.bukkit.plugin.java.JavaPlugin;

public class RealisticSpyglass extends JavaPlugin {

    public static RealisticSpyglass instance;

    @Override
    @SuppressWarnings("all")
    public void onEnable() {
        instance = this;
        setConfig();
        Sun.setSunLocations();
        new PlayerLooking().startChecking();
        getCommand("spyglass").setExecutor(new SpyglassCommand());
        getLogger().info("Thanks for downloading realistic spyglasses!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RIP realistic spyglasses!");
        saveConfig();
    }

    public static RealisticSpyglass getInstance() {
        return instance;
    }

    public void setConfig() {
        getConfig().addDefault("enabled", true);
        getConfig().addDefault("debug-messages", false);
        getConfig().addDefault("blind-time", 20);
        getConfig().options().copyDefaults(true);
    }

}
