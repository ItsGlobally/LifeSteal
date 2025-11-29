package top.itsglobally.circlenetwork.lifesteal.lifeSteal;

import org.bukkit.plugin.java.JavaPlugin;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Manager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.ManagerRegistry;
import top.nontage.nontagelib.command.NontageCommandLoader;
import top.nontage.nontagelib.listener.ListenerRegister;

public final class LifeSteal extends JavaPlugin {

    private static LifeSteal plugin;
    private ManagerRegistry mr;

    @Override
    public void onEnable() {
        plugin = this;
        mr = new ManagerRegistry(this);
        NontageCommandLoader.registerAll(this);
        ListenerRegister.registerAll(this);
    }

    @Override
    public void onDisable() {
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
