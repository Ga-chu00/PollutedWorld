package PollutedWorld.config;

import PollutedWorld.Main;

public class ConfigManager {

    private final Main plugin;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
    }

    public double getRadiationDamage() {
        return plugin.getConfig().getDouble("radiation.damage", 1.0);
    }

    public int getAntidoteDuration() {
        return plugin.getConfig().getInt("antidote.duration", 180);
    }

    public void save() {
        plugin.saveConfig();
    }

    public Main getPlugin() {
        return plugin;
    }
}
