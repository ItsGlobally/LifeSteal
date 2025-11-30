package top.itsglobally.circlenetwork.lifesteal.lifeSteal.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.data.LSPlayer;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.MessageUtil;
import top.nontage.nontagelib.annotations.AutoListener;
@AutoListener
public class CombatListener implements Listener, Global {
    @EventHandler
    public void quit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        LSPlayer lsp = pm.getLifeStealPlayer(p);

        if (!lsp.getFighting().isEmpty()) {
            p.setHealth(0);
        }

        pm.removePlayer(p);
    }
    @EventHandler
    public void join(PlayerJoinEvent e) {
        pm.addPlayer(e.getPlayer());
    }
    @EventHandler
    public void damagedbyentity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        if (!(e.getDamager() instanceof Player damager)) return;

        LSPlayer plsp = pm.getLifeStealPlayer(p);
        LSPlayer damagerLsp = pm.getLifeStealPlayer(damager);

        if (plsp.getFighting().get(damager.getUniqueId()) != null) MessageUtil.sendMessage(p, "&cYou are now combat with " + damager.getName() + ". Do not logout.");
        if (damagerLsp.getFighting().get(p.getUniqueId()) != null) MessageUtil.sendMessage(p, "&cYou are now combat with " + p.getName() + ". Do not logout.");
        plsp.addFightingsud(damagerLsp);
        damagerLsp.addFightingsud(plsp);
    }

}
