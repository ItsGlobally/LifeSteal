package top.itsglobally.circlenetwork.lifesteal.lifeSteal.manager;

import org.reflections.Reflections;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.LifeSteal;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ManagerRegistry {

    private final LifeSteal plugin;
    private final Map<Class<? extends Manager>, Manager> managers = new HashMap<>();

    public ManagerRegistry(LifeSteal plugin) {
        this.plugin = plugin;
    }

    public void init(String basePackage) {
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends Manager>> classes = reflections.getSubTypesOf(Manager.class);

        for (Class<? extends Manager> clazz : classes) {
            try {
                Manager manager = clazz.getDeclaredConstructor().newInstance();
                manager.init();
                managers.put(clazz, manager);
                plugin.getLogger().info("Loaded manager: " + clazz.getSimpleName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T extends Manager> T get(Class<T> clazz) {
        return clazz.cast(managers.get(clazz));
    }
}