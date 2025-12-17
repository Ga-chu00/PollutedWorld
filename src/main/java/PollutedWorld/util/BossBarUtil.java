package PollutedWorld.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class BossBarUtil {

    public static BossBar createYellow(String title) {
        BossBar bar = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SOLID);
        bar.setProgress(1.0);
        return bar;
    }

    public static BossBar createGreen(String title) {
        BossBar bar = Bukkit.createBossBar(title, BarColor.GREEN, BarStyle.SOLID);
        bar.setProgress(1.0);
        return bar;
    }
}
