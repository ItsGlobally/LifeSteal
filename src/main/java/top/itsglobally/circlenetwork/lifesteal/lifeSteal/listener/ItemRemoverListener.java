package top.itsglobally.circlenetwork.lifesteal.lifeSteal.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.nontage.nontagelib.annotations.AutoListener;

@AutoListener
public class ItemRemoverListener implements Listener, Global {
    @EventHandler
    public void onBrew(BrewEvent e) {
        for (ItemStack item : e.getContents().getContents()) {
            if (plugin.isBanned(item)) {
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (plugin.isBanned(e.getItem().getItemStack())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMobDrop(EntityDropItemEvent e) {
        if (plugin.isBanned(e.getItemDrop().getItemStack())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLoot(LootGenerateEvent e) {
        e.getLoot().removeIf(plugin::isBanned);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (plugin.isBanned(e.getCurrentItem())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack[] contents = e.getPlayer().getInventory().getContents();

        for (ItemStack item : contents) {
            if (plugin.isBanned(item)) {
                e.getPlayer().getInventory().remove(item);
            }
        }
    }

}
