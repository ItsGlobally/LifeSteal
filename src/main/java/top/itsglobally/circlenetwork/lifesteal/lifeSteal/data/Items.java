package top.itsglobally.circlenetwork.lifesteal.lifeSteal.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.MessageUtil;
import top.nontage.nontagelib.utils.item.ItemBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Items implements Global {

    public static ItemStack HEART() {
        return new ItemBuilder(Material.NETHER_STAR)
                .addLore("&cRight click to add &cheart&d!")
                .setName("&cHeart")
                .onRightClick(player -> {
                    player.setMaxHealth(player.getMaxHealth() + 2);
                    MessageUtil.sendMessage(player, "&dAdded 1 &cheart&d!");
                    ItemStack hand = player.getInventory().getItemInMainHand();
                    if (hand.isSimilar(Items.HEART())) {
                        hand.setAmount(hand.getAmount() - 1);
                        if (hand.getAmount() <= 0) {
                            player.getInventory().setItemInMainHand(null);
                        } else {
                            player.getInventory().setItemInMainHand(hand);
                        }
                    }
                })
                .build();
    }


    public static List<ItemStack> getAllItems() {
        return List.of(HEART());
    }

    public static ItemStack findByName(String name) {
        return getAllItems().stream()
                .filter(stack -> stack.hasItemMeta() &&
                        stack.getItemMeta().hasDisplayName() &&
                        stack.getItemMeta().getDisplayName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public static Set<Material> bannedItems = new HashSet<>();
    public static Set<PotionType> bannedPotionTypes = new HashSet<>();

}
