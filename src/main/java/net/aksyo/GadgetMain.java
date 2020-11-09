package net.aksyo;

import net.aksyo.commands.LaserGunCommand;
import net.aksyo.listeners.PlayerInteractListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GadgetMain extends JavaPlugin {

    private static GadgetMain instance;

    private PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        instance = this;

        //Used for debug
        getCommand("laser").setExecutor(new LaserGunCommand());
        pluginManager.registerEvents(new PlayerInteractListener(), this);

    }

    @Override
    public void onDisable() {

    }

    /**
     * Called whenever a BukkitRunnable is used
     *
     * @return The main class instance
     */
    public final static GadgetMain getInstance() {
        return instance;
    }


}
