package top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces;

import top.itsglobally.circlenetwork.lifesteal.lifeSteal.LifeSteal;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.ConfigManager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.ManagerRegistry;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager.PlayerManager;

public interface Global {
    LifeSteal plugin = LifeSteal.getPlugin();
    ManagerRegistry mr = LifeSteal.getPlugin().getManagerRegistry();
    ConfigManager cm = mr.get(ConfigManager.class);
    ConfigManager.Config cfg = cm.getConfig();
    PlayerManager pm = mr.get(PlayerManager.class);
}
