package top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager;

import org.bukkit.Bukkit;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.data.LSPlayer;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Manager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.MessageUtil;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class PlayerManager implements Manager, Global {

    private HashMap<UUID, LSPlayer> players;

    @Override
    public void init() {
        players = new HashMap<>();
    }
    public void addPlayer(Player p) {
        LSPlayer lsp = new LSPlayer(p);
        players.put(p.getUniqueId(), lsp);
    }
    public void addPlayer(UUID uuid) {
        addPlayer(Objects.requireNonNull(Bukkit.getPlayer(uuid)));
    }
    public LSPlayer getLifeStealPlayer(UUID u) {
        return players.get(u);
    }
    public LSPlayer getLifeStealPlayer(@NotNull Player p) {
        return getLifeStealPlayer(p.getUniqueId());
    }
}
