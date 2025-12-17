package PollutedWorld.radiation;

import PollutedWorld.potion.AntidoteManager;
import PollutedWorld.region.RegionManager;
import PollutedWorld.util.BossBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RadiationManager {

    private final Plugin plugin;
    private final RegionManager region;
    private final AntidoteManager antidote;

    // TYLKO żółte bossbary skażenia
    private final Map<UUID, BossBar> bars = new HashMap<>();

    public RadiationManager(Plugin plugin, RegionManager region, AntidoteManager antidote) {
        this.plugin = plugin;
        this.region = region;
        this.antidote = antidote;
        start();
    }

    private void start() {
        Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {

            for (Player p : Bukkit.getOnlinePlayers()) {

                boolean inSafeZone = region.isInSafeZone(p.getLocation());
                boolean hasAntidote = antidote.isProtected(p);

                // ☣ SKAŻONY TEREN
                if (!inSafeZone) {

                    // ŻÓŁTY bossbar ZAWSZE w skażeniu
                    if (!bars.containsKey(p.getUniqueId())) {
                        BossBar bar = BossBarUtil.createYellow("§e☣ Strefa promieniowania");
                        bar.addPlayer(p);
                        bars.put(p.getUniqueId(), bar);
                    }

                    // Obrażenia TYLKO jeśli brak antyradiacji
                    if (!hasAntidote) {
                        p.damage(1.0);
                    }

                } else {
                    // 🟢 Gracz w safezone → usuń żółty bossbar
                    BossBar bar = bars.remove(p.getUniqueId());
                    if (bar != null) {
                        bar.removeAll();
                    }
                }
            }

        }, 0L, 40L);
    }
}
