package top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager;

import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Manager;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.ConfigRegister;
import top.nontage.nontagelib.config.BaseConfig;

public class ConfigManager implements Manager, Global {

    private Config cfg;
    @Override
    public void init() {
        cfg = ConfigRegister.register(new Config(), "config");
    }

    public static class Config extends BaseConfig {
        public final int lowestHpToBan = 0;
        public final int lowestWithdrawHP = 2;

        public int getLowestHpToBan() {
            return lowestHpToBan;
        }

        public int getLowestWithdrawHP() {
            return lowestWithdrawHP;
        }
    }

    public Config getConfig() {
        return cfg;
    }
}
