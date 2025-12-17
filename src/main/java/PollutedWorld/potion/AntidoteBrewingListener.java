package PollutedWorld.potion;

import PollutedWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.Arrays;

public class AntidoteBrewingListener implements Listener {

    @EventHandler
    public void onBrew(BrewEvent event) {

        BrewerInventory inv = event.getContents();
        ItemStack ingredient = inv.getIngredient();


        if (ingredient == null || ingredient.getType() != Material.GLOWSTONE_DUST) {
            return;
        }

        // CZEKAMY 1 TICK
        Bukkit.getScheduler().runTaskLater(
                Main.getInstance(),
                () -> applyAntidote(inv),
                1L
        );
    }

    private void applyAntidote(BrewerInventory inv) {

        for (int i = 0; i < 3; i++) {

            ItemStack potion = inv.getItem(i);
            if (potion == null || potion.getType() != Material.POTION) continue;

            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            if (meta == null) continue;

            meta.setDisplayName("§aPłyn lugola");
            meta.setLore(Arrays.asList(
                    "§7Odporność na promieniowanie",
                    "§7Czas działania: §a180 sekund"
            ));

            potion.setItemMeta(meta);
            inv.setItem(i, potion);
        }
    }
}
