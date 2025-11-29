package top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils;

import top.itsglobally.circlenetwork.lifesteal.lifeSteal.LifeSteal;
import top.nontage.nontagelib.config.BaseConfig;

import java.io.File;

public class ConfigRegister {
    private static final LifeSteal plugin = LifeSteal.getPlugin();
    private static File configDir;

    public static <T extends BaseConfig> T register(T config, String name) {
        configDir = plugin.getDataFolder();
        if (!configDir.exists()) configDir.mkdirs();
        File file = new File(configDir, name + ".yml");
        config.initFile(file);
        config.reload();
        return config;
    }
}
