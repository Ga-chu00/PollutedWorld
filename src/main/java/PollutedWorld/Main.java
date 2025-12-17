package PollutedWorld;


import PollutedWorld.potion.AntidoteBrewingListener;
import PollutedWorld.potion.AntidoteManager;
import PollutedWorld.radiation.RadiationManager;
import PollutedWorld.region.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;


    @Override
    public void onEnable() {
        instance = this;

        // Config (jeśli potrzebny)
        saveDefaultConfig();

        // Menedżery
        RegionManager regionManager = new RegionManager(this);
        AntidoteManager antidoteManager = new AntidoteManager(this);


        // Komendy (OP only sprawdzane w klasach)
        getCommand("safezone").setExecutor(regionManager);


        // Eventy
        Bukkit.getPluginManager().registerEvents(antidoteManager, this);
        Bukkit.getPluginManager().registerEvents(new AntidoteBrewingListener(), this);
        Bukkit.getPluginManager().registerEvents(new AntidoteBrewingListener(), this);

        // Radiacja (scheduler)
        new RadiationManager(this, regionManager, antidoteManager);

        getLogger().info("PollutedWorld uruchomiony poprawnie!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PollutedWorld wylaczony.");
    }

    public static Main getInstance() {
        return instance;
    }

}
