package com.robertx22.age_of_exile.datapacks.loaders;

import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.database.registry.SlashRegistryType;
import com.robertx22.age_of_exile.database.data.EntityConfig;
import com.robertx22.age_of_exile.datapacks.generators.SlashDatapackGenerator;

public class EntityConfigsDatapackLoader extends BaseDataPackLoader<EntityConfig> {
    static String ID = "entity_config";

    public EntityConfigsDatapackLoader() {
        super(SlashRegistryType.ENTITY_CONFIGS, ID, x -> EntityConfig.EMPTY
            .fromJson(x));
    }

    @Override
    public SlashDatapackGenerator getDataPackGenerator() {
        return new SlashDatapackGenerator<EntityConfig>(SlashRegistry.EntityConfigs()
            .getSerializable(), ID);
    }
}
