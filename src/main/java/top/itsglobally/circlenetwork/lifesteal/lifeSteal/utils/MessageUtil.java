package top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {

    public static void sendMessage(Player player, Component message) {
        player.sendMessage(message);
    }

    public static void sendMessage(Player player, String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        sendMessage(player, component);
    }

    public static void sendMessage(Player player1, Player player2, String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        sendMessage(player1, component);
        sendMessage(player2, component);
    }
    public static void broadcast(String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        Bukkit.broadcast(component);
    }
    public static void sendActionBar(Player player, String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        player.sendActionBar(component);
    }

    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(
                ChatColor.translateAlternateColorCodes('&', title),
                ChatColor.translateAlternateColorCodes('&', subtitle)
        );
    }


    public static String formatMessage(String message) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        return component.toString();
    }

    public static List<String> formatList(List<String> ls) {
        List<String> nls = new ArrayList<>();
        ls.forEach(s -> nls.add(formatMessage(s)));
        return nls;
    }
}