package top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager;

import net.luckperms.api.model.user.User;
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
    public void removePlayer(Player p) {
        removePlayer(p.getUniqueId());
    }
    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }
    public String getPrefix(Player p) {
        User user = luckperms.getUserManager().getUser(p.getUniqueId());
        if (user == null) return "";
        String prefix = user.getCachedData().getMetaData().getPrefix();
        return prefix != null ? prefix : "";
    }

    public String getPrefixColor(Player p) {
        User user = luckperms.getUserManager().getUser(p.getUniqueId());
        if (user == null) return "&f";
        String prefixColor = user.getCachedData().getMetaData().getMetaValue("prefixcolor");
        if (prefixColor != null) return prefixColor;
        String prefix = getPrefix(p);
        return prefix.length() >= 2 ? prefix.substring(0, 2) : "&f";
    }

    public String getPrefixedName(Player p) {
        return getPrefix(p) + p.getName();
    }
}
