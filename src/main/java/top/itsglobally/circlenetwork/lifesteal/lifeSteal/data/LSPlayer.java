package top.itsglobally.circlenetwork.lifesteal.lifeSteal.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LSPlayer implements Global {

    private final Map<UUID, LSPlayer> fighting;
    private final Player player;
    private final UUID uuid;
    private final Map<LSPlayer, BukkitTask> fightingsud;


    public LSPlayer(Player p) {
        this.fightingsud = new HashMap<>();
        this.fighting = new HashMap<>();
        this.player = p;
        this.uuid = p.getUniqueId();
    }

    public Map<UUID, LSPlayer> getFighting() {
        return fighting;
    }

    public void addFighting(LSPlayer target) {
        fighting.put(target.getUuid(), target);
    }

    public void removeFighting(LSPlayer target) {
        fighting.remove(target.getUuid());
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<LSPlayer, BukkitTask> getFightingsuds() {
        return fightingsud;
    }
    public BukkitTask getFightingsud(LSPlayer target) {
        return fightingsud.get(target);
    }
    public void addFightingsud(LSPlayer target) {
        BukkitTask obt = getFightingsud(target);
        if (obt != null) removeFightsud(target);
        addFighting(target);
        BukkitTask bt = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            removeFightsud(target);
        }, 20 * 15L);
        fightingsud.put(target, bt);
    }
    public void removeFightsud(LSPlayer target) {
        BukkitTask bt = getFightingsud(target);
        if (bt == null || bt.isCancelled()) return;
        bt.cancel();
        removeFighting(target);
        fightingsud.remove(target);
    }
}
