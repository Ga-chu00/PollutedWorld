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

    // Żółty bossbar dla skażenia
    private final Map<UUID, BossBar> yellowBars = new HashMap<>();

    public RadiationManager(Plugin plugin, RegionManager region, AntidoteManager antidote) {
        this.plugin = plugin;
        this.region = region;
        this.antidote = antidote;
        start();
    }

    private void start() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            for (Player p : Bukkit.getOnlinePlayers()) {

                boolean safe = region.isInSafeZone(p.getLocation());
                boolean protectedByPotion = antidote.isProtected(p); // Czy gracz ma miksturę

                // Tworzymy lub aktualizujemy żółty bossbar skażenia
                if (!safe) {
                    if (!yellowBars.containsKey(p.getUniqueId())) {
                        BossBar bar = BossBarUtil.createYellow("§e☣ Strefa promieniowania");
                        bar.addPlayer(p);
                        yellowBars.put(p.getUniqueId(), bar);
                    }

                    // Zadajemy obrażenia tylko jeśli gracz NIE ma mikstury
                    if (!protectedByPotion) {
                        p.damage(1.0);
                    }

                } else {
                    // Jeśli gracz jest w strefie bezpiecznej, usuwamy bossbar
                    BossBar bar = yellowBars.remove(p.getUniqueId());
                    if (bar != null) bar.removeAll();
                }

                // Zielony bossbar mikstury działa niezależnie w AntidoteManager
            }

        }, 0L, 40L); // co 2 sekundy (40 ticków)
    }
}






