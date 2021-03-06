package com.robertx22.age_of_exile.mmorpg.registers.common;

import com.robertx22.age_of_exile.database.registry.ISlashRegistryEntry;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.database.registry.SlashRegistryContainer;
import com.robertx22.age_of_exile.config.base.ISerializedConfig;
import com.robertx22.age_of_exile.config.base_player_stat.BasePlayerStatSerial;

import java.util.HashMap;
import java.util.List;

public class ConfigRegister {

    public static HashMap<Config, ISerializedConfig> CONFIGS = new HashMap<>();
    public static HashMap<Config, List<String>> SAVED_JSONS = new HashMap<>();

    public enum Config {
        BASE_PLAYER_STATS
    }

    public static void registerCustomConfigs() {

        CONFIGS.put(Config.BASE_PLAYER_STATS, BasePlayerStatSerial.INSTANCE);

        unregisterFlaggedEntries(); // call first

        generateIfEmpty();

        CONFIGS.values()
            .forEach(x -> x.autoFixProblems());

        load();

    }

    // should be called only on server, then packets sent to client
    private static void load() {
        CONFIGS.values()
            .forEach(x -> x.loadOnServer());
    }

    private static void generateIfEmpty() {
        CONFIGS.values()
            .forEach(x -> x.generateIfEmpty());
    }

    public static void unregisterFlaggedEntries() {

        for (SlashRegistryContainer container : SlashRegistry.getAllRegistries()) {

            List<ISlashRegistryEntry> list = container.getList();

            for (ISlashRegistryEntry entry : list) {
                if (entry.unregisterBeforeConfigsLoad()) {
                    container.unRegister(entry);
                }
            }

        }

    }

}
