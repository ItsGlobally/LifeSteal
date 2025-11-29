package top.itsglobally.circlenetwork.lifesteal.lifeSteal;

import org.bukkit.plugin.java.JavaPlugin;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Manager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.ConfigManager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.ManagerRegistry;
import top.nontage.nontagelib.command.NontageCommandLoader;
import top.nontage.nontagelib.listener.ListenerRegister;

public final class LifeSteal extends JavaPlugin {

    private static LifeSteal plugin;
    private ManagerRegistry mr;

    @Override
    public void onEnable() {
        plugin = this;
        mr = new ManagerRegistry(this);
        mr.init("top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager");
        NontageCommandLoader.registerAll(this);
        ListenerRegister.registerAll(this);
        mr.get(ConfigManager.class).getConfig().reload();
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
}
