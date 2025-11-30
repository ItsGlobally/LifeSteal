package top.itsglobally.circlenetwork.lifesteal.lifeSteal;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import top.itsglobally.circlenetwork.lifesteal.lifeSteal.data.Items;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Manager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.ConfigManager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.ManagerRegistry;
import top.nontage.nontagelib.command.NontageCommandLoader;
import top.nontage.nontagelib.listener.ListenerRegister;
import top.nontage.nontagelib.utils.item.ItemBuilder;

import java.util.Iterator;
public final class LifeSteal extends JavaPlugin {

    private static LifeSteal plugin;
    private ManagerRegistry mr;
    private static LuckPerms luckPerms;

    @Override
    public void onEnable() {

        plugin = this;
        mr = new ManagerRegistry(this);

        mr.init("top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager");
        NontageCommandLoader.registerAll(this);
        ListenerRegister.registerAll(this);

        mr.get(ConfigManager.class).getConfig().reload();
        ItemBuilder.init(plugin);

        luckPerms = LuckPermsProvider.get();

        //Items.bannedItems.add(Material.);

        Items.bannedPotionTypes.add(PotionType.STRENGTH);
        removeBannedRecipes();
    }

    private void removeBannedRecipes() {
        Iterator<Recipe> it = Bukkit.recipeIterator();

        while (it.hasNext()) {
            Recipe r = it.next();
            if (r == null || r.getResult() == null) continue;

            ItemStack result = r.getResult();

            if (Items.bannedItems.contains(result.getType())) {
                it.remove();
                continue;
            }

            if (isBannedPotion(result)) {
                it.remove();
            }
        }
    }

    private boolean isBannedPotion(ItemStack item) {
        if (item == null) return false;
        if (!(item.getItemMeta() instanceof PotionMeta meta)) return false;

        PotionType type = meta.getBasePotionType();
        return type != null && Items.bannedPotionTypes.contains(type);
    }
    public boolean isBanned(ItemStack item) {
        if (item == null) return false;

        if (Items.bannedItems.contains(item.getType())) return true;

        return isBannedPotion(item);
    }

    @Override
    public void onDisable() {
        mr.get(ConfigManager.class).getConfig().save();
    }

    public static LifeSteal getPlugin() {
        return plugin;
    }

    public ManagerRegistry getManagerRegistry() {
        return mr;
    }

    public <T extends Manager> T getManager(Class<T> tClass) {
        return mr.get(tClass);
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }
}