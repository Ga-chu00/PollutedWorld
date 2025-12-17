package PollutedWorld.region;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.configuration.file.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegionManager implements CommandExecutor {

    private final Map<String, Region> regions = new HashMap<String, Region>();
    private final Map<UUID, Location> pos1 = new HashMap<UUID, Location>();
    private final Map<UUID, Location> pos2 = new HashMap<UUID, Location>();

    private final File file;
    private final FileConfiguration cfg;

    public RegionManager(JavaPlugin plugin) {

        file = new File(plugin.getDataFolder(), "region.yml");
        if (!file.exists()) {
            try {
                plugin.getDataFolder().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(file);
        load();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if (!p.isOp()) return true;

        if (args.length == 0) {
            p.sendMessage("/safezone pos1 | pos2 | create <nazwa>");
            return true;
        }

        if (args[0].equalsIgnoreCase("pos1")) {
            pos1.put(p.getUniqueId(), p.getLocation());
            p.sendMessage("§aPos1 ustawione");
        }

        if (args[0].equalsIgnoreCase("pos2")) {
            pos2.put(p.getUniqueId(), p.getLocation());
            p.sendMessage("§aPos2 ustawione");
        }

        if (args[0].equalsIgnoreCase("create") && args.length >= 2) {

            Location a = pos1.get(p.getUniqueId());
            Location b = pos2.get(p.getUniqueId());

            if (a == null || b == null) return true;

            Region r = new Region(
                    args[1],
                    a.getWorld().getName(),
                    Math.min(a.getBlockX(), b.getBlockX()),
                    Math.max(a.getBlockX(), b.getBlockX()),
                    Math.min(a.getBlockZ(), b.getBlockZ()),
                    Math.max(a.getBlockZ(), b.getBlockZ())
            );

            regions.put(args[1], r);
            save(args[1], r);
            p.sendMessage("§aStrefa utworzona");
        }
        return true;
    }

    public boolean isInSafeZone(Location l) {
        for (Region r : regions.values()) {
            if (r.getWorld().equals(l.getWorld().getName())
                    && r.contains(l.getBlockX(), l.getBlockZ())) {
                return true;
            }
        }
        return false;
    }

    private void save(String name, Region r) {
        String p = "regions." + name;
        cfg.set(p + ".world", r.getWorld());
        cfg.set(p + ".minX", r.getMinX());
        cfg.set(p + ".maxX", r.getMaxX());
        cfg.set(p + ".minZ", r.getMinZ());
        cfg.set(p + ".maxZ", r.getMaxZ());
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    private void load() {
        if (!cfg.isConfigurationSection("regions")) return;

        for (String k : cfg.getConfigurationSection("regions").getKeys(false)) {
            regions.put(k, new Region(
                    k,
                    cfg.getString("regions." + k + ".world"),
                    cfg.getInt("regions." + k + ".minX"),
                    cfg.getInt("regions." + k + ".maxX"),
                    cfg.getInt("regions." + k + ".minZ"),
                    cfg.getInt("regions." + k + ".maxZ")
            ));
        }
    }
}
