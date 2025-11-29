package top.itsglobally.circlenetwork.lifesteal.lifeSteal.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.MessageUtil;
import top.nontage.nontagelib.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class Items {
    public static ItemStack HEART = new ItemBuilder(Material.NETHER_STAR)
            .addLore("&cRight click to add &cheart&d!")
            .setName("&cHeart") .onRightClick(player -> {
                player.setMaxHealth(player.getMaxHealth() + 2);
                MessageUtil.sendMessage(player, "&dAdded 1 &cheart&d!");
            })
            .build();

    private static final List<ItemStack> allItems = Arrays.asList(
            HEART
    );

    public static ItemStack findByName(String name) {
        return allItems.stream()
                .filter(stack -> stack.hasItemMeta() &&
                        stack.getItemMeta().hasDisplayName() &&
                        stack.getItemMeta().getDisplayName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
