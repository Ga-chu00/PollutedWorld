package PollutedWorld.potion;

import PollutedWorld.Main;
import PollutedWorld.util.BossBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AntidoteManager implements Listener {

    private final Main plugin;

    // czas w sekundach
    private final int DURATION = 180;

    // aktywne antyradiacje
    private final Map<UUID, Integer> timeLeft = new HashMap<>();
    private final Map<UUID, BossBar> bars = new HashMap<>();

    public AntidoteManager(Main plugin) {
        this.plugin = plugin;
        startTimer();
    }

    // 🔹 WYKRYWANIE WYPICIA MIKSTURY
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event) {

        if (!event.getItem().hasItemMeta()) return;
        if (!event.getItem().getItemMeta().hasDisplayName()) return;

        if (!event.getItem().getItemMeta().getDisplayName().equals("§aPłyn lugola")) {
            return;
        }

        Player p = event.getPlayer();

        // 🔁 jeśli już była → odnów czas
        timeLeft.put(p.getUniqueId(), DURATION);

        // 🟢 jeśli NIE MA bossbara → stwórz
        if (!bars.containsKey(p.getUniqueId())) {
            BossBar bar = BossBarUtil.createGreen("§aOdporność na promieniowanie: " + DURATION + "s");
            bar.addPlayer(p);
            bars.put(p.getUniqueId(), bar);
        }
    }

    // 🔹 ODLICZANIE CZASU (JEDEN scheduler)
    private void startTimer() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            for (UUID uuid : new HashMap<>(timeLeft).keySet()) {

                int left = timeLeft.get(uuid) - 1;
                Player p = Bukkit.getPlayer(uuid);

                if (p == null || left <= 0) {
                    // koniec działania
                    timeLeft.remove(uuid);
                    BossBar bar = bars.remove(uuid);
                    if (bar != null) bar.removeAll();
                    continue;
                }

                timeLeft.put(uuid, left);

                BossBar bar = bars.get(uuid);
                if (bar != null) {
                    bar.setTitle("§aOdporność na promieniowanie: " + left + "s");
                    bar.setProgress(Math.max(0.0, left / (double) DURATION));
                }

            }

        }, 20L, 20L);
    }

    // 🔹 sprawdzane przez RadiationManager
    public boolean isProtected(Player p) {
        return timeLeft.containsKey(p.getUniqueId());
    }
}
