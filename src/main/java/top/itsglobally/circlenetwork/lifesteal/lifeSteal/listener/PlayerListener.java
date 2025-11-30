package top.itsglobally.circlenetwork.lifesteal.lifeSteal.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.data.Items;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.MessageUtil;
import top.nontage.nontagelib.annotations.AutoListener;

@AutoListener
public class PlayerListener implements Listener, Global {


    @EventHandler
    public void join(PlayerJoinEvent e) {

    }

    @EventHandler
    public void die(PlayerDeathEvent e) {
        Player p = e.getPlayer();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        double max = p.getMaxHealth();

        if (max - 2 <= cfg.lowestHpToBan) {
            MessageUtil.broadcast("&c" + p.getName() + " was banned from the server because their hp hit zero!");
            Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "ban " + p.getName() + " &cYour max health hit zero! Thanks for playing!"
            );
            return;
        }

        p.setMaxHealth(max - 2);

        e.getDrops().add(Items.HEART());
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat(MessageUtil.formatMessage("&r " +
                        pm.getPrefixedName(e.getPlayer()) +
                        "&r » %2$s"
        ));
    }


}
