package top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces;

import net.luckperms.api.LuckPerms;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.LifeSteal;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.ConfigManager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.ManagerRegistry;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.PlayerManager;

public interface Global {
    LifeSteal plugin = LifeSteal.getPlugin();
    ManagerRegistry mr = LifeSteal.getPlugin().getManagerRegistry();
    ConfigManager cm = mr.get(ConfigManager.class);
    ConfigManager.Config cfg = cm.getConfig();
    PlayerManager pm = mr.get(PlayerManager.class);
    LuckPerms luckperms = plugin.getLuckPerms();
}
